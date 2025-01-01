package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail_for_fire_alarm

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.R
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.Screens
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.access_point_connection.isTablet
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail.DayPicker
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail.DeviceDetailPhoneScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail.EndlessRollingPadlockTimePicker
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail.GradientSlider
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme

@Composable
fun FireAlarmDetailScreen(
    navController: NavHostController
) {
    AppTheme {
        var rowWidth by remember { mutableStateOf(0) }
        var smokeLevel by remember { mutableStateOf(20) }
        var temperature by remember { mutableStateOf(50) }
        var coLevel by remember { mutableStateOf(-1) }
        var showDialog by remember { mutableStateOf(false) }
        var switchState by remember { mutableStateOf(true) }
        val statusList = listOf("Bình thường", "Báo động", "Lỗi")// Trạng thái
        val status by remember { mutableStateOf(0) }

        val colorScheme = MaterialTheme.colorScheme
        Scaffold(
            topBar = {
                /*
            * Hiển thị Header
             */
                Header(
                    navController = navController,
                    type = "Back",
                    title = "Chi tiết thiết bị"
                )
            },
            bottomBar = {
                /*
            * Hiển thị Thanh Menu dưới cùng
             */
                MenuBottom(navController)
            },
            containerColor = colorScheme.background,
            modifier = Modifier.fillMaxSize(),
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize() // Đảm bảo chiếm toàn bộ không gian
                            .fillMaxHeight()
                            .imePadding() // Tự động thêm khoảng trống khi bàn phím xuất hiện
                            .verticalScroll(rememberScrollState()) // Cho phép cuộn
                            .padding(innerPadding)
                    ) {
                        // Nội dung bên dưới
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = colorScheme.background)
                                .wrapContentHeight()
                        ) {
                            Column {
                                // Hộp màu xanh dương
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .background(
                                            color = colorScheme.primary,
                                            shape = RoundedCornerShape(bottomStart = 40.dp)
                                        )
                                        .zIndex(1f)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(RoundedCornerShape(bottomStart = 40.dp)) // Làm bo tròn góc dưới bên trái của Box
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize() // Chiếm toàn bộ kích thước của Box
                                        ) {
                                            // Phần đầu tiên: Hiển thị thông tin "Dining Room"
                                            Row(
                                                modifier = Modifier.padding(
                                                    top = 8.dp,
                                                    end = 12.dp
                                                ) // Canh lề trên và phải
                                            ) {
                                                // Cột chứa các thông tin của phòng
                                                Column(
                                                    modifier = Modifier
                                                        .padding(
                                                            start = 12.dp,
                                                            end = 12.dp
                                                        ) // Canh lề hai bên
                                                        .fillMaxWidth() // Chiều rộng đầy đủ
                                                        .background(color = colorScheme.primary) // Nền màu xanh dương
                                                        .weight(0.2f), // Chiếm 20% của Row
                                                    horizontalAlignment = Alignment.Start,
                                                    verticalArrangement = Arrangement.SpaceBetween // Các thành phần cách đều nhau
                                                ) {
                                                    Text(
                                                        text = "Dining Room",
                                                        color = colorScheme.onPrimary // Màu chữ trắng
                                                    ) // Tiêu đề
                                                    Spacer(modifier = Modifier.height(4.dp)) // Khoảng cách giữa các thành phần

                                                    // Switch bật/tắt đèn
                                                    Switch(
                                                        checked = switchState,
                                                        onCheckedChange = {
                                                            //Todo: Xử lý tắt mở thiết bị
                                                        },
                                                        thumbContent = {
                                                            Icon(
                                                                imageVector = if (switchState) Icons.Filled.Check else Icons.Filled.Close,
                                                                contentDescription = "On/Off Switch",
                                                                tint = if (switchState) colorScheme.onPrimary else colorScheme.onSecondary.copy(
                                                                    alpha = 0.8f
                                                                )
                                                            )
                                                        },
                                                        colors = SwitchDefaults.colors(
                                                            checkedThumbColor = colorScheme.primary,
                                                            checkedTrackColor = colorScheme.onPrimary,
                                                            uncheckedThumbColor = colorScheme.secondary,
                                                            uncheckedTrackColor = colorScheme.onSecondary.copy(
                                                                alpha = 0.8f
                                                            ),
                                                        )
                                                    )
                                                    Text(
                                                        "Trạng thái hiện tại: ",
                                                        color = colorScheme.onPrimary,
                                                        fontSize = 16.sp
                                                    ) // Nhãn cho độ sáng
                                                    // Hiển thị phần trăm độ sáng
                                                    Row(
                                                        modifier = Modifier.fillMaxWidth(), // Chiều rộng đầy đủ
                                                        horizontalArrangement = Arrangement.Start,
                                                        verticalAlignment = Alignment.Bottom // Canh các thành phần theo đáy
                                                    ) {
                                                        Text(
                                                            statusList[status],
                                                            fontWeight = FontWeight.Bold,
                                                            fontSize = 25.sp,
                                                            color = colorScheme.onPrimary
                                                        ) // Số phần trăm
                                                    }
                                                    Spacer(modifier = Modifier.height(8.dp)) // Khoảng cách dưới cùng
                                                }

                                                SingleColorCircleWithDividers(selectedStatus = statusList[status], dividerCount = 12)
                                            }
                                            Spacer(modifier = Modifier.height(16.dp))
                                        }
                                    }
                                }
                                // Hộp màu xanh lá cây với góc lõm
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                ) {
                                    // Box màu vàng (ở dưới)
                                    Box(
                                        modifier = Modifier
                                            .width(40.dp)
                                            .height(40.dp)
                                            .align(Alignment.TopEnd)
                                            .background(color = colorScheme.primary)
                                            .zIndex(1f) // Z-index thấp hơn
                                    )

                                    // Box màu xanh lá cây (ở trên)
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(
                                                color = colorScheme.background,
                                                shape = RoundedCornerShape(topEndPercent = 50)
                                            )
                                            .width(50.dp)
                                            .height(50.dp)
                                            .zIndex(2f) // Z-index cao hơn
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clip(RoundedCornerShape(topEndPercent = 100)) // Clip nội dung ScrollableTabRow
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth() // Đảm bảo Row chiếm toàn bộ chiều rộng
                                                    .padding(
                                                        top = 12.dp,
                                                        start = 12.dp,
                                                        end = 8.dp
                                                    ), // Khoảng cách bên trong Row
                                                horizontalArrangement = Arrangement.SpaceBetween, // Đẩy các phần tử ra hai bên
                                                verticalAlignment = Alignment.CenterVertically // Căn giữa theo chiều dọc
                                            ) {
                                                // Nội dung bên phải (Icon trong Box)
                                                Button(
                                                    onClick = {
                                                        showDialog = true
                                                    },
                                                    modifier = Modifier
                                                        .size(24.dp), // Kích thước tổng thể của Button
                                                    shape = CircleShape, // Đảm bảo Button có dạng hình tròn
                                                    contentPadding = PaddingValues(0.dp), // Loại bỏ padding mặc định
                                                    colors = ButtonDefaults.buttonColors(
                                                        containerColor = colorScheme.onPrimary,
                                                        contentColor = colorScheme.primary
                                                    )
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.Info,
                                                        contentDescription = "Info",
                                                        modifier = Modifier.size(24.dp), // Kích thước của Icon
                                                        tint = colorScheme.primary
                                                    )
                                                }
                                                Button(
                                                    onClick = {
                                                        //Todo: Xử lý khi nhấn nút Wifi
                                                        navController.navigate(Screens.AccessPoint.route)
                                                    },
                                                    modifier = Modifier
                                                        .size(24.dp), // Kích thước tổng thể của Button
                                                    shape = CircleShape, // Đảm bảo Button có dạng hình tròn
                                                    contentPadding = PaddingValues(0.dp), // Loại bỏ padding mặc định,
                                                    colors = ButtonDefaults.buttonColors(
                                                        containerColor = colorScheme.onPrimary,
                                                        contentColor = colorScheme.primary

                                                    )
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.Wifi,
                                                        contentDescription = "Wifi",
                                                        modifier = Modifier.size(24.dp), // Kích thước của Icon
                                                        tint = colorScheme.primary

                                                    )
                                                }
                                                if (showDialog) {
                                                    AlertDialog(
                                                        onDismissRequest = {
                                                            showDialog = false
                                                        }, // Đóng Dialog khi chạm ngoài
                                                        title = { Text(text = "Thông tin thiết bị") },
                                                        text = {
                                                            Column {
                                                                Text("ID Thiết bị: 001")
                                                                Text("Tên thiết bị: Đèn LED phòng khách")
                                                                Text("Loại thiết bị: Đèn chiếu sáng")
                                                            }
                                                        },
                                                        confirmButton = {
                                                            Button(onClick = {
                                                                showDialog = false
                                                            }) {
                                                                Text("Đóng")
                                                            }
                                                        }
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .onSizeChanged { size ->
                                    rowWidth = size.width // Lấy kích thước của Row
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Column(
                                modifier = Modifier
                                    .widthIn(max = if (isTablet()) 700.dp else 400.dp)
                                    .padding(horizontal = 12.dp)
                                    .wrapContentWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {

                                // Sử dụng hàm InfoRow để hiển thị thông tin
                                InfoRow(
                                    label = "Khói:",
                                    value = "$smokeLevel",
                                    unit = "ppm",
                                    stateColor = when {
                                        smokeLevel > 50 -> Color.Red
                                        smokeLevel < 0 -> Color.Yellow
                                        else -> Color.Green
                                    },
                                    stateText = when {
                                        smokeLevel > 50 -> "!"
                                        smokeLevel < 0 -> "?"
                                        else -> "✓"
                                    }
                                )

                                Slider(
                                    value = 40f,
                                    onValueChange = {
                                        //Todo: Xử lý khi thay đổi giá trị
                                    }, // Thanh trượt giá trị mặc định là 80
                                    steps = 50,
                                    valueRange = 0f..100f,
                                    colors = SliderDefaults.colors(
                                        thumbColor = colorScheme.onPrimary,
                                        activeTrackColor = colorScheme.onPrimary,
                                        activeTickColor = colorScheme.onBackground,
                                        inactiveTrackColor = colorScheme.onBackground,
                                        inactiveTickColor = colorScheme.onSecondary
                                    )
                                )

                                InfoRow(
                                    label = "Nhiệt độ:",
                                    value = "$temperature",
                                    unit = "°C",
                                    stateColor = when {
                                        temperature > 40 -> Color.Red
                                        temperature < 0 -> Color.Yellow
                                        else -> Color.Green
                                    },
                                    stateText = when {
                                        temperature > 40 -> "!"
                                        temperature < 0 -> "?"
                                        else -> "✓"
                                    }
                                )
                                Slider(
                                    value = 90f,
                                    onValueChange = {
                                        //Todo: Xử lý khi thay đổi giá trị
                                    }, // Thanh trượt giá trị mặc định là 80
                                    steps = 50,
                                    valueRange = 0f..100f,
                                    colors = SliderDefaults.colors(
                                        thumbColor = colorScheme.onPrimary,
                                        activeTrackColor = colorScheme.onPrimary,
                                        activeTickColor = colorScheme.onBackground,
                                        inactiveTrackColor = colorScheme.onBackground,
                                        inactiveTickColor = colorScheme.onSecondary
                                    )
                                )
                                InfoRow(
                                    label = "CO:",
                                    value = "$coLevel",
                                    unit = "ppm",
                                    stateColor = when {
                                        coLevel > 40 -> Color.Red
                                        coLevel < 0 -> Color.Yellow
                                        else -> Color.Green
                                    },
                                    stateText = when {
                                        coLevel > 40 -> "!"
                                        coLevel < 0 -> "?"
                                        else -> "✓"
                                    }
                                )
                                Slider(
                                    value = 60f,
                                    onValueChange = {
                                        //Todo: Xử lý khi thay đổi giá trị
                                    }, // Thanh trượt giá trị mặc định là 80
                                    steps = 50,
                                    valueRange = 0f..100f,
                                    colors = SliderDefaults.colors(
                                        thumbColor = colorScheme.onPrimary,
                                        activeTrackColor = colorScheme.onPrimary,
                                        activeTickColor = colorScheme.onBackground,
                                        inactiveTrackColor = colorScheme.onBackground,
                                        inactiveTickColor = colorScheme.onSecondary
                                    )
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .then(Modifier.width(rowWidth.dp)),
                                horizontalArrangement = Arrangement.spacedBy(
                                    8.dp,
                                    alignment = Alignment.CenterHorizontally
                                ), // Khoảng cách giữa các nút
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Button(
                                    onClick = {
                                        //Todo: Xử lý khi nhấn nút Lịch sử
                                        navController.navigate(Screens.ActivityHistory.route)
                                    },
                                    modifier = Modifier
                                        .weight(0.5f) // Chia đều không gian
                                        .width(if (isTablet()) 300.dp else 200.dp)
                                        .height(if (isTablet()) 56.dp else 48.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
                                    shape = RoundedCornerShape(50)
                                ) {
                                    Text(
                                        text = "Lịch sử hoạt động",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp
                                    )
                                }

                                Button(
                                    onClick = {
                                        //Todo: Xử lý khi nhấn nút Gỡ kết nối
                                    },
                                    modifier = Modifier
                                        .weight(0.5f) // Chia đều không gian
                                        .width(if (isTablet()) 300.dp else 200.dp)
                                        .height(if (isTablet()) 56.dp else 48.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = colorScheme.error),
                                    shape = RoundedCornerShape(50)
                                ) {
                                    Text(
                                        text = "Gỡ kết nối",
                                        fontWeight = FontWeight.Bold,
                                        color = colorScheme.onError
                                    )
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FireAlarmDetailScreenPreview() {
    FireAlarmDetailScreen(navController = rememberNavController())
}

@Composable
fun SingleColorCircleWithDividers(selectedStatus: String, dividerCount: Int) {
    Box(
        modifier = Modifier.size(200.dp),
        contentAlignment = Alignment.Center // Đảm bảo nội dung bên trong căn giữa
    ) {
        Canvas(modifier = Modifier.size(200.dp)) {
            val radius = size.minDimension / 2 - 20f // Trừ bớt để tránh bị cắt
            val center = Offset(size.width / 2, size.height / 2)

            // Xác định màu dựa theo trạng thái
            val color = when (selectedStatus) {
                "Bình thường" -> Color.Green
                "Báo động" -> Color.Red
                "Lỗi" -> Color.Yellow
                else -> Color.Gray
            }

            // Vẽ vòng tròn với màu trạng thái
            drawCircle(
                color = color,
                radius = radius,
                center = center,
                style = Stroke(width = 40f) // Độ dày của vòng tròn
            )

            // Vẽ các đường gạch phân cách
            val lineAngleStep = 360f / dividerCount
            for (i in 0 until dividerCount) {
                val angle = Math.toRadians((-90 + i * lineAngleStep).toDouble())
                val startX = center.x + (radius - 20f) * Math.cos(angle).toFloat()
                val startY = center.y + (radius - 20f) * Math.sin(angle).toFloat()
                val endX = center.x + (radius + 20f) * Math.cos(angle).toFloat()
                val endY = center.y + (radius + 20f) * Math.sin(angle).toFloat()

                drawLine(
                    color = Color.Black,
                    start = Offset(startX, startY),
                    end = Offset(endX, endY),
                    strokeWidth = 4f
                )
            }
        }

        // Icon trung tâm (nếu cần)
        Image(
            painter = painterResource(id = R.drawable.fire),
            contentDescription = null,
            modifier = Modifier.size(100.dp) // Kích thước biểu tượng
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SegmentedCirclePreview() {
    val statusList = listOf("Bình thường", "Báo động", "Lỗi")
    SingleColorCircleWithDividers(selectedStatus = statusList[0], dividerCount = 12)
}

@Composable
// Hàm hiển thị từng hàng thông tin
fun InfoRow(label: String, value: String, unit: String, stateColor: Color, stateText: String) {
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        Row(
            modifier = Modifier
                .wrapContentWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f),
                color = colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium
            )
            Row(
                modifier = Modifier.weight(2f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = value,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = unit,
                    fontSize = 12.sp,
                    modifier = Modifier.offset(y = 3.dp),
                    color = colorScheme.onBackground,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(stateColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stateText,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}