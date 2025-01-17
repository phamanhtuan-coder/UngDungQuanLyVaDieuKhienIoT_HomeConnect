package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screens

import android.app.Application
import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SpaceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.SharedViewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.Screens
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.dashboard.StatisticsState
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.dashboard.StatisticsViewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.DeviceState
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.DeviceViewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.SpaceState
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Giao diện màn hình Thống kê (Dashboard Screen)
 *  -----------------------------------------
 *  - Người viết: Phạm Anh Tuấn
 *  - Ngày viết: 10/11/2024
 *  - Lần cập nhật cuối: 13/12/2024
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val context = LocalContext.current
    val application = context.applicationContext as Application

    // --- 1) ViewModel quản lý danh sách phòng / thiết bị ---
    val deviceViewModel = remember { DeviceViewModel(application, context) }

    // --- 2) ViewModel quản lý thống kê (API) ---
    val statisticsRepository = remember {
        com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.StatisticsRepository(context)
    }
    val statisticsViewModel = remember { StatisticsViewModel(statisticsRepository) }

    // --- 3) State cho chart ---
    var chartData by remember { mutableStateOf(listOf<Float>()) }
    var chartLabels by remember { mutableStateOf(listOf<String>()) }

    // --- 4) State cho tab “Sử dụng điện, Nhiệt độ, Độ ẩm, Khí ga” ---
    val chartOptions = listOf("Sử dụng điện", "Nhiệt độ", "Độ ẩm", "Khí ga")
    val selectedChart = remember { mutableStateOf(0) }

    // --- 5) State cho “khoảng thời gian” (ngày, tuần, tháng, custom) ---
    val selectedTimeRange = remember { mutableStateOf(0) }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    // --- 6) State cho phòng & thiết bị ---
    var spaces by remember { mutableStateOf<List<SpaceResponse>>(emptyList()) }
    var spacesID by remember { mutableStateOf(0) }

    // Lắng nghe state khi load Spaces
    val spacesListState by deviceViewModel.spacesListState.collectAsState()

    // Lắng nghe houseId => load Spaces
    LaunchedEffect(viewModel.houseId) {
        val houseId = viewModel.houseId.value
        Log.e("DashboardScreen", "houseId = $houseId")
        if (houseId != null) {
            deviceViewModel.getSpacesByHomeId(houseId)
        }
    }

    // Khi spacesListState thay đổi
    when (spacesListState) {
        is SpaceState.Success -> {
            spaces = (spacesListState as SpaceState.Success).spacesList
            if (spaces.isNotEmpty() && spacesID == 0) {
                spacesID = spaces.first().SpaceID
            }
        }
        is SpaceState.Error -> {
            Log.e("DashboardScreen", "Lỗi load phòng: ${(spacesListState as SpaceState.Error).error}")
        }
        else -> {}
    }

    // --- 7) Lấy danh sách thiết bị cho phòng đã chọn (spacesID) ---
    var devices by remember { mutableStateOf<List<DeviceResponse>>(emptyList()) }
    val deviceListState by deviceViewModel.deviceListState.collectAsState()

    // Mỗi khi thay đổi “spacesID”, loadDevices
    LaunchedEffect(spacesID) {
        if (spacesID > 0) {
            deviceViewModel.loadDevices(spacesID)
        }
    }

    when (deviceListState) {
        is DeviceState.Success -> {
            devices = (deviceListState as DeviceState.Success).deviceList
        }
        is DeviceState.Error -> {
            Log.e("DashboardScreen", "Lỗi load thiết bị: ${(deviceListState as DeviceState.Error).error}")
        }
        else -> {}
    }

    // --- 8) Lắng nghe state “Statistics” từ StatisticsViewModel
    val statisticsState by statisticsViewModel.statisticsState.collectAsState()

    // Mỗi khi “statisticsState” thay đổi => cập nhật chartData/ chartLabels
    LaunchedEffect(statisticsState) {
        when (statisticsState) {
            is StatisticsState.PowerUsageSuccess -> {
                // Sử dụng điện
                val data = (statisticsState as StatisticsState.PowerUsageSuccess).data
                chartLabels = data.map { it.date }
                // totalEnergyConsumed là kiểu String => convert sang float
                chartData = data.map { it.totalEnergyConsumed.toFloatOrNull() ?: 0f }
            }
            is StatisticsState.SensorAveragesSuccess -> {
                // Cảm biến (nhiệt, ẩm, ga)
                val data = (statisticsState as StatisticsState.SensorAveragesSuccess).data
                chartLabels = data.map { it.date }
                // Tuỳ selectedChart.value => 1=nhiệt, 2=ẩm, 3=ga
                chartData = data.map {
                    when (selectedChart.value) {
                        1 -> it.averageTemperature.toFloat()
                        2 -> it.averageHumidity.toFloat()
                        3 -> it.averageGas.toFloat()
                        else -> 0f
                    }
                }
            }
            is StatisticsState.Error -> {
                Log.e("DashboardScreen", "Lỗi lấy thống kê: ${(statisticsState as StatisticsState.Error).error}")
                chartLabels = emptyList()
                chartData = emptyList()
            }
            else -> {}
        }
    }

    // --- 9) Mỗi lần user đổi “tab” (Sử dụng điện, Nhiệt độ, Độ ẩm, Ga) hoặc “khoảng thời gian”, ta gọi API ---
    // Giả sử logic => Lấy theo “spacesID” (phòng) thay vì deviceID
    // => T tuỳ backend yêu cầu:
    //    + fetchDailyRoomPowerUsage(spaceId, start, end)
    //    + fetchDailyRoomAveragesSensor(spaceId, start, end)
    // => Hoặc tách ra: deviceID => fetchDailyDevice...
    LaunchedEffect(selectedChart.value, selectedTimeRange.value, spacesID, startDate, endDate) {
        if (spacesID <= 0) return@LaunchedEffect
        // Tính start/end dựa vào selectedTimeRange
        val (sDate, eDate) = calculateStartEndDate(selectedTimeRange.value, startDate, endDate)
        if (sDate.isEmpty() || eDate.isEmpty()) return@LaunchedEffect

        when (selectedChart.value) {
            0 -> {
                // Sử dụng điện
                statisticsViewModel.fetchDailyRoomPowerUsage(
                    spaceId = spacesID,
                    startDate = sDate,
                    endDate = eDate
                )
            }
            else -> {
                // 1 -> nhiệt, 2 -> ẩm, 3 -> ga
                statisticsViewModel.fetchDailyRoomAveragesSensor(
                    spaceId = spacesID,
                    startDate = sDate,
                    endDate = eDate
                )
            }
        }
    }

    // --- UI ---
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Header(
                navController = navController,
                type = "Back",
                title = "Thống kê"
            )
        },
        bottomBar = {
            MenuBottom(navController)
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 1) TabRow “chartOptions” (Sử dụng điện / Nhiệt / Ẩm / Ga)
                Column(modifier = Modifier.fillMaxWidth()) {
                    TabRow(selectedTabIndex = selectedChart.value) {
                        chartOptions.forEachIndexed { index, option ->
                            Tab(
                                selected = (selectedChart.value == index),
                                onClick = { selectedChart.value = index },
                                text = {
                                    Text(
                                        text = option,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // 2) Hiển thị biểu đồ MPAndroidChart
                    MpAndroidChart(
                        title = chartOptions[selectedChart.value],
                        data = chartData,
                        labels = chartLabels
                    )
                }

                // 3) Dropdown thời gian
                if (spaces.isNotEmpty()) {
                    // 2) Dropdown “khoảng thời gian” (Ngày/Tuần/Tháng/Khoảng thời gian)
                    TimeSelectionDropdown(
                        selectedTimeRange = selectedTimeRange.value,
                        onTimeRangeChange = { newValue ->
                            selectedTimeRange.value = newValue
                            // Nếu user chọn lại “khoảng thời gian”
                            // => startDate, endDate nên reset (nếu cần)
                            if (newValue != 3) {
                                startDate = ""
                                endDate = ""
                            }
                        },
                        onManageTimeClicked = {  // mở DatePicker
                            showDatePickerForCustomRange(
                                context,
                                onResult = { start, end ->
                                    startDate = start
                                    endDate = end
                                    // Sau khi user chọn xong, ta “ép” selectedTimeRange = 3
                                    selectedTimeRange.value = 3
                                }
                            )
                        }
                    )

                    // 4) TabRow để chọn phòng
                    val selectedTab = remember { mutableStateOf(0) }
                    TabRow(selectedTabIndex = selectedTab.value) {
                        spaces.forEachIndexed { index, tab ->
                            Tab(
                                selected = (selectedTab.value == index),
                                onClick = {
                                    selectedTab.value = index
                                    spacesID = tab.SpaceID
                                },
                                text = {
                                    Text(
                                        text = tab.Name,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            )
                        }
                    }
                } else {
                    // Nếu chưa có phòng
                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Không có phòng nào được tìm thấy.",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    }
                }

                // 5) Danh sách thiết bị
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(devices) { device ->
                        DeviceItem(device = device) {
                            // onDetailsClick => điều hướng
                            navController.navigate("${Screens.DashboardDeviceScreen.route}/${device.SpaceID}/${device.DeviceID}")
                        }
                    }
                }
            }
        }
    )
}

/**
 * Tính toán startDate & endDate dựa theo “selectedTimeRange”
 * @param timeRange 0=3 tháng, 1=7 ngày, 2=30 ngày, 3=custom
 */
private fun calculateStartEndDate(
    timeRange: Int,
    customStart: String,
    customEnd: String
): Pair<String, String> {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val now = Calendar.getInstance()

    return when (timeRange) {
        0 -> {
            // 15 ngày gần đây
            val endDate = sdf.format(now.time)
            now.add(Calendar.DAY_OF_YEAR, -15)
            val startDate = sdf.format(now.time)
            Pair(startDate, endDate)
        }
        1 -> {
            // 7 ngày
            val endDate = sdf.format(now.time)
            now.add(Calendar.DAY_OF_YEAR, -7)
            val startDate = sdf.format(now.time)
            Pair(startDate, endDate)
        }
        2 -> {
            // 30 ngày
            val endDate = sdf.format(now.time)
            now.add(Calendar.DAY_OF_YEAR, -30)
            val startDate = sdf.format(now.time)
            Pair(startDate, endDate)
        }
        3 -> {
            // custom => Dùng user chọn
            if (customStart.isBlank() || customEnd.isBlank()) {
                Pair("", "")
            } else {
                Pair(customStart, customEnd)
            }
        }
        else -> Pair("", "")
    }
}

// Biểu đồ dùng thư viện MPAndroidChart
@Composable
fun MpAndroidChart(title: String, data: List<Float>, labels: List<String>) {
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        val entries = data.mapIndexed { index, value -> Entry(index.toFloat(), value) }
        val dataSet = LineDataSet(entries, title).apply {
            color = android.graphics.Color.BLUE
            valueTextColor = colorScheme.onBackground.toArgb()
            lineWidth = 4f
        }
        val lineData = LineData(dataSet)

        AndroidView(
            factory = { context ->
                LineChart(context).apply {
                    this.data = lineData
                    description.text = title

                    // Cấu hình trục X
                    xAxis.apply {
                        textColor = colorScheme.onBackground.toArgb() // Màu chữ trên trục X
                        textSize = 12f // Kích thước chữ trên trục X
                        position = XAxis.XAxisPosition.BOTTOM // Hiển thị trục X bên dưới
                        setDrawGridLines(false) // Ẩn các đường lưới dọc
                    }

                    // Cấu hình trục Y trái
                    axisLeft.apply {
                        textColor = colorScheme.onBackground.toArgb() // Màu chữ trên trục Y trái
                        textSize = 12f // Kích thước chữ trên trục Y trái
                    }

                    // Cấu hình trục Y phải (nếu cần)
                    axisRight.apply {
                        textColor = colorScheme.onBackground.toArgb() // Màu chữ trên trục Y phải
                        textSize = 12f // Kích thước chữ trên trục Y phải
                    }

                    // Cấu hình Legend
                    legend.apply {
                        textColor = colorScheme.onBackground.toArgb() // Màu chữ của Legend
                        textSize = 14f // Kích thước chữ
                        formSize = 10f // Kích thước của hình
                    }

                    invalidate() // Redraw chart
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 16.dp),
            update = { chart ->
                //Cập nhật dữ liệu khi data thay đổi
                chart.data = lineData
                chart.description.text = title
                chart.invalidate() // Trigger redraw
            }
        )
    }
}


// Mỗi thiết bị trong danh sách
/**
 * Giao diện một thiết bị trong danh sách
 * -----------------------------------------
 * - Người viết: Phạm Anh Tuấn
 * - Ngày viết: 11/12/2024
 * - Lần cập nhật cuối: 13/12/2024
 * -----------------------------------------
 * @param device: Dữ liệu thiết bị
 * @param onDetailsClick: Hàm xử lý khi nhấn vào nút "Chi tiết"
 * @return Card chứa thông tin thiết bị
 */
@Composable
fun DeviceItem(device: DeviceResponse, onDetailsClick: () -> Unit) {
    fun getIconForType(typeId: Int): String {
        return when (typeId) {
            1 -> "\uD83D\uDD25" // Fire
            2 -> "\uD83D\uDCA1" // Light
            else -> "❓"         // Biểu tượng mặc định
        }
    }

    fun getType(typeId: Int): String {
        return when (typeId) {
            1 -> "Báo cháy" // Fire
            2 -> "Đèn led" // Light
            else -> "Không xác định"         // Biểu tượng mặc định
        }
    }

    fun getPower(power: Boolean): String {
        return when (power) {
            true -> "Bật" // Fire
            false -> "Tắt" // Light
            else -> "Không xác định"         // Biểu tượng mặc định
        }
    }

    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconBox(getIconForType(device.TypeID), colorScheme.background)
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {

                        LimitedWordsText(device.Name, 3)

                        Text(
                            text = "Loại: ${getType(device.TypeID)}",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                        Text(
                            text = "Trạng thái: ${getPower(device.PowerStatus)}",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                        Text(
                            text = "Sử dụng điện: 20 W",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                        Text(
                            text = "Nhiệt độ: 20°C",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
                Button(
                    onClick = onDetailsClick,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "Chi tiết", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun IconBox(icon: String, color: Color) {
    Box(
        modifier = Modifier
            .padding(end = 30.dp)
            .size(40.dp)
            .background(color, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = icon, fontSize = 20.sp)
    }
}

@Composable
fun LimitedWordsText(text: String, maxWords: Int) {
    // Cắt văn bản nếu vượt quá số từ giới hạn
    val words = text.split(" ")
    val limitedText = if (words.size > maxWords) {
        words.take(maxWords).joinToString(" ") + "..." // Ghép lại với dấu "..."
    } else {
        text // Nếu không vượt quá, hiển thị văn bản gốc
    }

    // Hiển thị văn bản
    Text(
        text = limitedText,
        softWrap = true,
        overflow = TextOverflow.Ellipsis,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
fun TimeSelectionDropdown(
    selectedTimeRange: Int,
    onTimeRangeChange: (Int) -> Unit,
    onManageTimeClicked: () -> Unit
) {
    // 0 -> Ngày (3 tháng), 1 -> Tuần (7 ngày), 2 -> Tháng (30 ngày), 3 -> Khoảng thời gian
    val timeOptions = listOf("Ngày", "Tuần", "Tháng", "Khoảng thời gian")

    var isDropdownExpanded by remember { mutableStateOf(false) }
    val colorScheme = MaterialTheme.colorScheme

    // Lấy item text hiện tại
    val currentText = timeOptions.getOrElse(selectedTimeRange) { "Ngày" }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(colorScheme.surface)
                .clickable {
                    isDropdownExpanded = !isDropdownExpanded
                }
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = currentText,
                    color = colorScheme.onSurface,
                    fontSize = 18.sp
                )
                Icon(
                    imageVector = if (isDropdownExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = colorScheme.primary
                )
            }
        }

        if (isDropdownExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorScheme.surfaceVariant)
                    .padding(vertical = 4.dp)
            ) {
                timeOptions.forEachIndexed { index, option ->
                    Text(
                        text = option,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                isDropdownExpanded = false
                                if (index == 3) {
                                    // Người dùng chọn “Khoảng thời gian” => gọi hàm mở DatePicker
                                    onManageTimeClicked()
                                } else {
                                    onTimeRangeChange(index)
                                }
                            }
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        fontSize = 16.sp,
                        color = colorScheme.onSurface
                    )
                }
            }
        }
    }
}

// --------------------------------------
// 3) Hàm hiển thị DatePickerDialog
// --------------------------------------
fun showDatePickerForCustomRange(
    context: Context,
    onResult: (String, String) -> Unit
) {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val calendar = Calendar.getInstance()

    // Mở date picker để chọn start date
    DatePickerDialog(
        context,
        { _, year, month, day ->
            val startCalendar = Calendar.getInstance()
            startCalendar.set(year, month, day)
            val startDateStr = dateFormat.format(startCalendar.time)

            // Sau khi chọn xong start date => mở date picker lần 2 để chọn end date
            DatePickerDialog(
                context,
                { _, eYear, eMonth, eDay ->
                    val endCalendar = Calendar.getInstance()
                    endCalendar.set(eYear, eMonth, eDay)
                    val endDateStr = dateFormat.format(endCalendar.time)
                    onResult(startDateStr, endDateStr)
                },
                startCalendar.get(Calendar.YEAR),
                startCalendar.get(Calendar.MONTH),
                startCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}