package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screens

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
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
    navController: NavHostController
) {
    val tabs = listOf("Phòng khách", "Phòng ngủ", "Nhà bếp")
    val selectedTab = remember { mutableStateOf(0) }
    val chartOptions = listOf("Sử dụng điện", "Nhiệt độ", "Độ ẩm")
    val selectedChart = remember { mutableStateOf(0) }

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

                // Tab phòng
                TabRow(selectedTabIndex = selectedTab.value) {
                    tabs.forEachIndexed { index, tab ->
                        Tab(
                            selected = selectedTab.value == index,
                            onClick = { selectedTab.value = index },
                            text = {
                                Text(text = tab, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                            },
                        )
                    }
                }

                // Danh sách thiết bị
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(getDevicesForRoom(selectedTab.value)) { device ->
                        DeviceItem(
                            device = device,
                            onDetailsClick = { /* Điều hướng đến trang điều khiển */ })
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
fun DeviceItem(device: Device, onDetailsClick: () -> Unit) {
    return Card(
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
                Icon(
                    imageVector = device.icon,
                    contentDescription = device.name,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = device.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Loại: ${device.type}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "Trạng thái: ${device.status}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "Sử dụng điện: ${device.powerUsage} W",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "Nhiệt độ: ${device.temperature}°C",
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


@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(rememberNavController())
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

fun getDevicesForRoom(roomIndex: Int): List<Device> {
    // Dữ liệu mẫu
    return when (roomIndex) {
        0 -> listOf(
            Device("Đèn phòng khách", "Bóng đèn", "Bật", Icons.Filled.Lightbulb, 50f, 0f),
            Device("TV", "Tivi", "Tắt", Icons.Filled.Tv, 0f, 0f)
        )

        1 -> listOf(
            Device("Điều hòa phòng ngủ", "Máy điều hòa", "Bật", Icons.Filled.AcUnit, 150f, 22f)
        )

        2 -> listOf(
            Device("Lò nướng nhà bếp", "Lò nướng", "Tắt", Icons.Filled.Home, 0f, 0f)
        )

        else -> emptyList()
    }
}