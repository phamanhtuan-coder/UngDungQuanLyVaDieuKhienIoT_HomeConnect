import android.app.Application
import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.PowerUsageData
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.PowerUsageData2
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SensorData
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.DeviceState
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.DeviceViewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail.CalculationState
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screens.IconBox
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screens.LimitedWordsText
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// Giả sử các import & ViewModel, DTO,... đã giống trong code cũ
// --------------------------------------
// 1) Thay đổi ở DashboardDeviceScreen
// --------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardDeviceScreen(
    navController: NavHostController,
    spaceId: Int,
    id: Int,
) {
    val context = LocalContext.current
    val application = context.applicationContext as Application

    // Thay vì “selectedTab”, ta có 2 state rõ ràng:
    //   - selectedDataType: 0->điện, 1->nhiệt, 2->ẩm, 3->ga
    //   - selectedTimeRange: 0->Ngày (3 tháng), 1->Tuần (7 ngày), 2->Tháng (30 ngày), 3->Custom
    val selectedDataType = remember { mutableStateOf(0) }
    val selectedTimeRange = remember { mutableStateOf(0) }

    // Dùng để hiển thị ngày bắt đầu/kết thúc khi user chọn “Khoảng thời gian”
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    // Device
    var devices by remember { mutableStateOf<List<DeviceResponse>>(emptyList()) }
    var deviceId by remember { mutableStateOf(id) }

    // ViewModel
    val viewModel2 = remember { DeviceViewModel(application, context) }
    val viewModel3 = remember { DashboardViewModel(application, context) }

    // Lấy date format
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    // Lấy data cho danh sách device
    val deviceListState by viewModel2.deviceListState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel2.loadDevices(spaceId)
    }

    // Gọi loadDevices ngay khi vào màn hình
    LaunchedEffect(spaceId) {
        viewModel2.loadDevices(spaceId)
    }
    // Bắt sự kiện khi deviceListState thay đổi
    when (deviceListState) {
        is DeviceState.Loading -> { /* ... */ }
        is DeviceState.Error -> { /* ... */ }
        is DeviceState.Success -> {
            devices = (deviceListState as DeviceState.Success).deviceList
        }
        else -> {}
    }

    // State cho dữ liệu chart
    var chartData by remember { mutableStateOf<List<Float>>(emptyList()) }
    var chartLabels by remember { mutableStateOf<List<String>>(emptyList()) }

    // State listen về kết quả của DashboardViewModel
    val statisticsState by viewModel3.statisticsState.collectAsState()

    // Mỗi lần “selectedDataType”, “selectedTimeRange” hoặc “deviceId” đổi => gọi LaunchedEffect
    LaunchedEffect(selectedDataType.value, selectedTimeRange.value, deviceId, startDate, endDate) {
        // Tính ra khoảng thời gian start - end dựa theo selectedTimeRange
        val calendar = Calendar.getInstance()
        val currentDate = dateFormat.format(calendar.time)

        val (start, end) = when (selectedTimeRange.value) {
            0 -> { // Ngày => 3 tháng gần đây
                calendar.add(Calendar.DAY_OF_YEAR, -15)
                val s = dateFormat.format(calendar.time)
                Pair(s, currentDate)
            }
            1 -> { // Tuần => 7 ngày gần đây
                calendar.add(Calendar.DAY_OF_YEAR, -7)
                val s = dateFormat.format(calendar.time)
                Pair(s, currentDate)
            }
            2 -> { // Tháng => 30 ngày
                calendar.add(Calendar.DAY_OF_YEAR, -30)
                val s = dateFormat.format(calendar.time)
                Pair(s, currentDate)
            }
            3 -> {
                // Khoảng thời gian custom => user sẽ gán startDate, endDate
                // Nếu startDate & endDate rỗng => chưa chọn => return
                if (startDate.isBlank() || endDate.isBlank()) return@LaunchedEffect
                Pair(startDate, endDate)
            }
            else -> Pair("", "")
        }

        // Kiểm tra “loại dữ liệu”
        when (selectedDataType.value) {
            3 -> {
                // Loại 0 => Sử dụng điện -> gọi getDailyPowerUsages
                if (deviceId > 0 && start.isNotEmpty() && end.isNotEmpty()) {
                    viewModel3.getDailyPowerUsages(deviceId, start, end)
                    Log.e("Sử dụng điện" , "Đã vào")
                }
            }
            else -> {
                // Các loại khác => Nhiệt độ (1), Ẩm (2), Ga (3)
                // Gọi getDailyAveragesForLastThreeMonths ->
                // HOẶC getDailyAveragesForCustomTimeRange tuỳ ViewModel bạn có
                // Ở đây minh hoạ gọi chung getDailyAveragesForLastThreeMonths
                // sau đó tuỳ “selectedDataType” mà hiển thị
                // -> bạn có thể tách API tương tự “DailyAverages cho custom” tuỳ ý
                if (start.isNotEmpty() && end.isNotEmpty()) {
                    // Giả sử ViewModel bạn có method getDailyAverages trong khoảng
                    // Thay thế:
                    viewModel3.getDailyAveragesSensor(deviceId, start, end)

                }
            }
        }
    }

    // Lắng nghe “kết quả” (statisticsState) => cập nhật chart
    LaunchedEffect(statisticsState) {
        when (statisticsState) {
            is StatisticsState.DailyPowerUsageSuccess -> {
                // Trường hợp “Sử dụng điện”
                if (selectedDataType.value == 3) {
                    Log.e("Sử dụng điện 2" , "Đã vào")
                    val response = (statisticsState as StatisticsState.DailyPowerUsageSuccess).data
                    Log.d("DEBUG", "Updating chart for Power Usage: ${response.dailyPowerUsages}")
                    chartLabels = response.dailyPowerUsages.map { it.date }
                    chartData = response.dailyPowerUsages.map { it.energyConsumed }
                }
            }
            is StatisticsState.DailyAveragesSuccess -> {
                val data = (statisticsState as StatisticsState.DailyAveragesSuccess).data.dailyAverages
                chartLabels = data.map { it.date }

                chartData = data.map { dailyData ->
                    when (selectedDataType.value) {
                        0 -> dailyData.averageTemperature
                        1 -> dailyData.averageHumidity
                        2 -> dailyData.averageGas
                        else -> 0f
                    }
                }
            }
            is StatisticsState.Error -> {
                // Nếu `chartLabels` có sẵn, giữ nguyên số lượng phần tử với giá trị `0f`
                if (chartLabels.isNotEmpty()) {
                    chartData = chartLabels.map { 0f }
                } else {
                    // Nếu không có `chartLabels`, hiển thị mặc định 7 phần tử với `0f`
                    chartData = List(7) { 0f } // Tùy số lượng phần tử bạn muốn
                    chartLabels = List(7) { "N/A" } // Đặt label là "N/A"
                }
            }

            else -> {
//                val data = (statisticsState as StatisticsState.DailyAveragesSuccess).data.dailyAverages
//                // Loading, Error hoặc Idle
//                // có thể reset chartData = emptyList() nếu muốn
//                chartData = data.map { 0f }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            // Tuỳ bạn tái sử dụng Header
            Header(
                navController = navController,
                type = "Back",
                title = "Thống kê",
            )
        },
        bottomBar = {
            // Tuỳ bạn tái sử dụng MenuBottom
            MenuBottom(navController)
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Top
            ) {
                // 1) TabRow “loại dữ liệu” (điện/ nhiệt/ ẩm/ ga)
                val chartOptions = listOf("Nhiệt độ", "Độ ẩm", "Khí ga", "Sử dụng điện")
                TabRow(
                    selectedTabIndex = selectedDataType.value,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    chartOptions.forEachIndexed { index, option ->
                        Tab(
                            selected = (selectedDataType.value == index),
                            onClick = { selectedDataType.value = index },
                            text = {
                                Text(
                                    text = option,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        )
                        Log.e("selectedDataType.value", selectedDataType.value.toString())
                    }
                }

                // 3) Hiển thị biểu đồ
                MpAndroidChart(
                    title = when (selectedDataType.value) {
                        3 -> "Sử dụng điện"
                        0 -> "Nhiệt độ"
                        1 -> "Độ ẩm"
                        2 -> "Khí ga"
                        else -> ""
                    },
                    data = chartData,
                    labels = chartLabels
                )

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
                                Log.e("Time start end", startDate.toString() + endDate.toString())
                                // Sau khi user chọn xong, ta “ép” selectedTimeRange = 3
                                selectedTimeRange.value = 3
                            }
                        )
                    }
                )

                // 4) Hiển thị khoảng thời gian đã chọn (nếu user chọn Custom)
                if (selectedTimeRange.value == 3 && startDate.isNotEmpty() && endDate.isNotEmpty()) {
                    Text(
                        text = "Khoảng thời gian: $startDate - $endDate",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 8.dp, start = 16.dp)
                    )
                }

                // 5) Danh sách device
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(devices) { device ->
                        DeviceItem(
                            device = device,
                            onDetailsClick = {
                                Log.e("DeviceItem", "Chọn device ${device.DeviceID}")
                                deviceId = device.DeviceID
                            }
                        )
                    }
                }
            }
        }
    )
}

// --------------------------------------
// 2) Thay đổi ở TimeSelectionDropdown
// --------------------------------------
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

// --------------------------------------
// 4) MpAndroidChart giữ nguyên
// --------------------------------------
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

                    xAxis.apply {
                        textColor = colorScheme.onBackground.toArgb()
                        textSize = 12f
                        position = XAxis.XAxisPosition.BOTTOM
                        setDrawGridLines(false)
                    }
                    axisLeft.apply {
                        textColor = colorScheme.onBackground.toArgb()
                        textSize = 12f
                    }
                    axisRight.apply {
                        textColor = colorScheme.onBackground.toArgb()
                        textSize = 12f
                    }
                    legend.apply {
                        textColor = colorScheme.onBackground.toArgb()
                        textSize = 14f
                        formSize = 10f
                    }

                    invalidate()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 16.dp),
            update = { chart ->
                chart.data = lineData
                chart.description.text = title
                chart.invalidate()
            }
        )
    }
}

// --------------------------------------
// 5) DeviceItem giữ nguyên
// --------------------------------------
@Composable
fun DeviceItem(
    device: DeviceResponse,
    onDetailsClick: () -> Unit,
) {
    val now = Calendar.getInstance()
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val context = LocalContext.current
    val application = context.applicationContext as Application

    // 1) Tạo ViewModel
    val viewModel = remember { DashboardViewModel(application, context) }

    // 2) Lắng nghe calculationState từ ViewModel
    val calcState = viewModel.calculationState.collectAsState()

    // 3) Tạo biến cục bộ để lưu kết quả SensorData và PowerUsageData
    val sensorData = remember { mutableStateOf<SensorData?>(null) }
    val powerData = remember { mutableStateOf<PowerUsageData2?>(null) }

    // 4) Gọi 2 hàm tính toán khi composable khởi tạo
    LaunchedEffect(Unit) {
        viewModel.calculateDailyAverageSensor(device.DeviceID, sdf.format(now.time))
        viewModel.calculateDailyPowerUsage(device.DeviceID, sdf.format(now.time))
    }

    // 5) Dựa trên state, cập nhật biến cục bộ
    when (val state = calcState.value) {
        is CalculationState.Idle -> {
            // Chưa tính toán hoặc chưa có gì
        }
        is CalculationState.Loading -> {
            // Đang load, bạn có thể hiển thị ProgressIndicator nếu muốn
        }
        is CalculationState.AverageSensorSuccess -> {
            // Lấy dữ liệu SensorData -> lưu vào sensorData
            sensorData.value = state.data
        }
        is CalculationState.PowerUsageSuccess -> {
            // Lấy dữ liệu PowerUsageData -> lưu vào powerData
            powerData.value = state.data
        }
        is CalculationState.Error -> {
            // Khi xảy ra lỗi -> tuỳ bạn hiển thị
            // Text(text = "Lỗi: ${state.error}")
            Log.e("CalculationState.Error", (state as CalculationState.Error).toString())
        }
    }

    // ----------------------------------------------
    // Tạo vài hàm tiện ích format hiển thị
    // ----------------------------------------------
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
            else -> "Không xác định"
        }
    }

    fun getPower(power: Boolean): String {
        return if (power) "Bật" else "Tắt"
    }

    // ----------------------------------------------
    // Chuẩn bị giá trị hiển thị, nếu null => 0f
    // ----------------------------------------------
    val powerRatingValue = powerData.value?.powerRating ?: 0f
    val avgTemperatureValue = sensorData.value?.averageTemperature ?: 0f

    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = colorScheme.surface)
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
                            color = colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                        Text(
                            text = "Trạng thái: ${getPower(device.PowerStatus)}",
                            fontSize = 14.sp,
                            color = colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                        // Lấy powerRatingValue, nếu null thì 0f
                        Text(
                            text = "Sử dụng điện: $powerRatingValue W",
                            fontSize = 14.sp,
                            color = colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                        // Lấy avgTemperatureValue, nếu null thì 0f
                        Text(
                            text = "Nhiệt độ: $avgTemperatureValue °C",
                            fontSize = 14.sp,
                            color = colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
                Button(
                    onClick = onDetailsClick,
                    colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary)
                ) {
                    Text(text = "Chi tiết", color = Color.White)
                }
            }
        }
    }
}

// --------------------------------------
// 6) Preview
// --------------------------------------
@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardDeviceScreen(
        navController = rememberNavController(),
        spaceId = 1,
        id = 1,
    )
}
