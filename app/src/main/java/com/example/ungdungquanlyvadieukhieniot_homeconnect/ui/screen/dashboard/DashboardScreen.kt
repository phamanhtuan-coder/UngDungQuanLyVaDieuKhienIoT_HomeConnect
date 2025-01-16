package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screens

import android.app.Application
import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Tv
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SpaceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.SharedViewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.Screens
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.dashboard.DashboardViewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.DeviceState
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.DeviceViewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.SpaceState
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


/**
 * Giao diện màn hình Thống kê (Dashboard Screen)
 *  -----------------------------------------
 *  - Người viết: Phạm Anh Tuấn
 *  - Ngày viết: 10/11/2024
 *  - Lần cập nhật cuối: 13/12/2024
 *  * -----------------------------------------
 *
 *  * @return Scaffold chứa toàn bộ nội dung của màn hình Thống kê.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val selectedTab = remember { mutableStateOf(0) }
    val chartOptions = listOf("Sử dụng điện", "Nhiệt độ", "Độ ẩm", "Khí ga")
    val selectedChart = remember { mutableStateOf(0) }

    val context = LocalContext.current
    val application = context.applicationContext as Application
    val viewModel2 = remember {
        DeviceViewModel(application, context)
    }

    var spaces by remember { mutableStateOf<List<SpaceResponse>>(emptyList()) } // Lắng nghe danh sách thiết bị
    val spacesListState by viewModel2.spacesListState.collectAsState()
    var spacesID by remember { mutableStateOf(0) }

    when(spacesListState){
        is SpaceState.Error ->{
            Log.d("Error Dashboard: ",  (spacesListState as SpaceState.Error).error)
        }
        is SpaceState.Idle ->{
            //Todo
        }
        is SpaceState.Loading -> {
            //Todo
        }
        is SpaceState.Success -> {
            spaces = (spacesListState as SpaceState.Success).spacesList
            Log.d("Dashboard: ", (spacesListState as SpaceState.Success).spacesList.toString())
            // Đặt spacesID mặc định là SpaceID của space đầu tiên nếu chưa có giá trị
            if (spaces.isNotEmpty() && spacesID == 0) {
                spacesID = spaces.first().SpaceID
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel2.loadDevices(spacesID)
    }

    LaunchedEffect(spacesID) {
        viewModel2.loadDevices(spacesID)
    }

    LaunchedEffect(viewModel.houseId) {
        val houseId = viewModel.houseId.value // Truy xuất giá trị từ MutableState
        Log.e("houseIdState trong Dashboard: ", houseId.toString())
        if (houseId != null) {
            viewModel2.getSpacesByHomeId(houseId) // Chỉ gọi nếu houseId không null
        } else {
            Log.e("DashboardScreen", "Không có ID nhà được chọn")
        }
    }

    var devices by remember { mutableStateOf<List<DeviceResponse>>(emptyList()) } // Lắng nghe danh sách thiết bị
    val deviceListState by viewModel2.deviceListState.collectAsState()

    when(deviceListState){
        is DeviceState.Error ->{
            Log.d("Error",  (deviceListState as DeviceState.Error).error)
        }
        is DeviceState.Idle ->{
            //Todo
        }
        is DeviceState.Loading -> {
            //Todo
        }
        is DeviceState.Success -> {
            devices=(deviceListState as DeviceState.Success).deviceList
            Log.d("List Device", (deviceListState as DeviceState.Success).deviceList.toString())
        }
    }

    return Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Header(
                navController = navController,
                type = "Back",
                title = "Thống kê",
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

                // Biểu đồ và tab biểu đồ
                Column(modifier = Modifier.fillMaxWidth()) {
                    TabRow(selectedTabIndex = selectedChart.value) {
                        chartOptions.forEachIndexed { index, option ->
                            Tab(
                                selected = selectedChart.value == index,
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

                    MpAndroidChart(
                        title = chartOptions[selectedChart.value],
                        data = when (selectedChart.value) {
                            0 -> listOf(50f, 75f, 100f, 80f, 120f, 95f, 110f)
                            1 -> listOf(22f, 24f, 23f, 25f, 26f, 24f, 23f)
                            else -> listOf(40f, 45f, 50f, 48f, 46f, 49f, 47f)
                        },
                        labels = listOf("Th 2", "Th 3", "Th 4", "Th 5", "Th 6", "Th 7", "CN")
                    )
                }

                // Kiểm tra danh sách spaces
                if (spaces.isNotEmpty()) {
                    // Tab phòng nếu có dữ liệu
                    TabRow(selectedTabIndex = selectedTab.value) {
                        spaces.forEachIndexed { index, tab ->
                            Tab(
                                selected = selectedTab.value == index,
                                onClick = {
                                    selectedTab.value = index
                                    spacesID = tab.SpaceID
                                          },
                                text = {
                                    Text(text = tab.Name, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                                },
                            )
                        }
                    }
                } else {
                    // Hiển thị thông báo nếu không có phòng
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

                // Danh sách thiết bị
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(devices) { device ->
                        DeviceItem(
                            device = device,
                            onDetailsClick = { /* Điều hướng đến trang điều khiển */
                                Log.e("device.DeviceID", device.DeviceID.toString())
                                Log.e("device.SpaceID", device.SpaceID.toString())
                                navController.navigate("${Screens.DashboardDeviceScreen.route}/${device.SpaceID}/${device.DeviceID}")
                            })
                    }
                }
            }
        }
    )
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

// Lớp dữ liệu ví dụ
data class Device(
    val name: String,
    val type: String,
    val status: String,
    val icon: ImageVector,
    val powerUsage: Float,
    val temperature: Float
)

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
