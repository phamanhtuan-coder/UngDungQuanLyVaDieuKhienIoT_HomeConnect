package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
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
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.DeviceScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/** Giao diện màn hình Device Detail (DeviceDetailScreen)
 * -----------------------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 6/12/2024
 * Lần cập nhật cuối: 10/12/2024
 * -----------------------------------------
 * @param navController: NavController để điều hướng giữa các màn hình
 *
 * @return Scaffold màn hình chi tiết thiét bị
 *
 * -----------------------------------------
 * Người cập nhật: Phạm Anh Tuấn
 * Ngày viết: 30/12/2024
 * -----------------------------------------
 * Chỉnh sửa giao diện
 * ---------------------------------------
 *
 * Người cập nhật: Nguyễn Thanh Sang
 * Ngày viết: 31/12/2024
 * ---------------------
 * Thêm phần navigation
 */
@Composable
fun DeviceDetailScreen(
    navController: NavHostController
) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    // Theo hướng dẫn Material Design, thường 600dp trở lên được xem là tablet
    if (screenWidthDp >= 600) {
        // Layout dành cho tablet
        DeviceDetailTabletScreen(navController)
    } else {
        // Layout dành cho phone
        DeviceDetailPhoneScreen(navController)
    }
}

@Composable
fun DeviceDetailPhoneScreen(
    navController: NavHostController
) {
    AppTheme {
        var rowWidth by remember { mutableStateOf(0) }
        var selectedTimeBegin by remember { mutableStateOf("12:00 AM") }
        var selectedTimeEnd by remember { mutableStateOf("12:00 AM") }
        var showDialogTimePickerBegin by remember { mutableStateOf(false) }
        var showDialogTimePickerEnd by remember { mutableStateOf(false) }
        var showDialog by remember { mutableStateOf(false) }
        var switchState by remember { mutableStateOf(true) }

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
                                                        .weight(0.2f), // Chiếm 20% trọng lượng của Row
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

                                                    // Hiển thị phần trăm độ sáng
                                                    Row(
                                                        modifier = Modifier.fillMaxWidth(), // Chiều rộng đầy đủ
                                                        horizontalArrangement = Arrangement.Start,
                                                        verticalAlignment = Alignment.Bottom // Canh các thành phần theo đáy
                                                    ) {
                                                        Text(
                                                            "80",
                                                            fontWeight = FontWeight.Bold,
                                                            fontSize = 50.sp,
                                                            color = colorScheme.onPrimary
                                                        ) // Số phần trăm
                                                        Text(
                                                            "%",
                                                            fontWeight = FontWeight.Bold,
                                                            fontSize = 25.sp,
                                                            modifier = Modifier.offset(y = (-8).dp), // Đẩy lên trên một chút
                                                            color = colorScheme.onPrimary
                                                        )
                                                    }
                                                    Text(
                                                        "Brightness",
                                                        color = colorScheme.onPrimary,
                                                        fontSize = 20.sp
                                                    ) // Nhãn cho độ sáng
                                                    Spacer(modifier = Modifier.height(8.dp)) // Khoảng cách dưới cùng
                                                }

                                                // Hình ảnh đèn
                                                Image(
                                                    painter = painterResource(id = R.drawable.lamp),
                                                    modifier = Modifier.size(150.dp), // Kích thước hình ảnh
                                                    contentDescription = "",// Mô tả cho hình ảnh
                                                    colorFilter = ColorFilter.tint(colorScheme.onPrimary) // Màu chữ trắng
                                                )
                                            }

                                            // Thanh trượt để điều chỉnh cường độ sáng
                                            Column(
                                                modifier = Modifier
                                                    .width(rowWidth.dp)
                                                    .padding(start = 12.dp, end = 12.dp)
                                                    .background(color = colorScheme.primary) // Nền màu xanh dương
                                            ) {
                                                Text(
                                                    "Insensity",
                                                    color = colorScheme.onPrimary
                                                ) // Tiêu đề cường độ sáng
                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.Center, // Căn giữa các thành phần
                                                    verticalAlignment = Alignment.CenterVertically // Căn giữa theo chiều dọc
                                                ) {
                                                    Image(
                                                        painter = painterResource(id = R.drawable.bulboff),
                                                        modifier = Modifier
                                                            .padding(end = 8.dp)
                                                            .size(24.dp), // Hình ảnh bóng đèn tắt
                                                        contentDescription = "",
                                                        colorFilter = ColorFilter.tint(colorScheme.onPrimary) // Màu chữ trắng
                                                    )
                                                    Slider(
                                                        value = 80f,
                                                        onValueChange = {
                                                            //Todo: Xử lý khi thay đổi giá trị
                                                        }, // Thanh trượt giá trị mặc định là 80
                                                        steps = 50,
                                                        valueRange = 0f..100f,
                                                        modifier = Modifier.width(300.dp),
                                                        colors = SliderDefaults.colors(
                                                            thumbColor = colorScheme.onPrimary,
                                                            activeTrackColor = colorScheme.onPrimary,
                                                            activeTickColor = colorScheme.secondary,
                                                            inactiveTrackColor = colorScheme.secondary,
                                                            inactiveTickColor = colorScheme.onSecondary
                                                        )
                                                    )
                                                    Image(
                                                        painter = painterResource(id = R.drawable.bulb),
                                                        modifier = Modifier
                                                            .padding(start = 8.dp)
                                                            .size(24.dp), // Hình ảnh bóng đèn bật
                                                        contentDescription = "",
                                                    )
                                                }
                                            }

                                            // Đường kẻ phân cách mỏng
                                            Box(
                                                modifier = Modifier
                                                    .padding(start = 12.dp, end = 12.dp)
                                                    .fillMaxWidth()
                                                    .height(1.dp)
                                                    .background(colorScheme.onPrimary) // Màu xám nhạt cho đường kẻ
                                            )

                                            // Phần hiển thị thông tin sử dụng điện
                                            Column(
                                                modifier = Modifier
                                                    .padding(
                                                        top = 24.dp,
                                                        start = 12.dp,
                                                        end = 12.dp
                                                    )
                                                    .fillMaxSize(), // Chiếm toàn bộ kích thước còn lại
                                                horizontalAlignment = Alignment.Start,
                                                verticalArrangement = Arrangement.Top
                                            ) {
                                                GradientSlider()
                                            }
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
                                .wrapContentWidth()
                                .onSizeChanged { size ->
                                    rowWidth = size.width // Lấy kích thước của Row
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Select Time",
                                fontSize = 24.sp,
                                color = colorScheme.onBackground,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = selectedTimeBegin,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colorScheme.onBackground,
                                    modifier = Modifier
                                        .clickable { showDialogTimePickerBegin = true }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "To",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colorScheme.onBackground,
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = selectedTimeEnd,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colorScheme.onBackground,
                                    modifier = Modifier
                                        .clickable { showDialogTimePickerEnd = true }
                                )
                            }

                            if (showDialogTimePickerBegin) {
                                Dialog(onDismissRequest = { showDialogTimePickerBegin = false }) {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                colorScheme.onBackground,
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                            .padding(16.dp)
                                            .wrapContentSize()
                                    ) {
                                        EndlessRollingPadlockTimePicker { hour, minute, amPm ->
                                            selectedTimeBegin =
                                                "$hour:${minute.toString().padStart(2, '0')} $amPm"
                                            showDialog = false // Đóng dialog sau khi chọn xong
                                        }
                                    }
                                }
                            }
                            if (showDialogTimePickerEnd) {
                                Dialog(onDismissRequest = { showDialogTimePickerEnd = false }) {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                colorScheme.background,
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                            .padding(16.dp)
                                            .wrapContentSize()
                                    ) {
                                        EndlessRollingPadlockTimePicker { hour, minute, amPm ->
                                            selectedTimeEnd =
                                                "$hour:${minute.toString().padStart(2, '0')} $amPm"
                                            showDialog = false // Đóng dialog sau khi chọn xong
                                        }
                                    }
                                }
                            }
                            DayPicker()

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
                                        .weight(1f) // Chia đều không gian
                                        .width(200.dp)
                                        .height(48.dp),
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
                                        .weight(1f) // Chia đều không gian
                                        .width(200.dp)
                                        .height(48.dp),
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

@Composable
fun DeviceDetailTabletScreen(navController: NavHostController) {
    var rowWidth by remember { mutableStateOf<Int?>(null) }
    var selectedTimeBegin by remember { mutableStateOf("12:00 AM") }
    var selectedTimeEnd by remember { mutableStateOf("12:00 AM") }
    var showDialogTimePickerBegin by remember { mutableStateOf(false) }
    var showDialogTimePickerEnd by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    var switchState by remember { mutableStateOf(true) }


    AppTheme {

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
                        .wrapContentHeight()
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
                                    // Box chính bao quanh toàn bộ giao diện
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize() // Chiếm toàn bộ màn hình
                                            .clip(RoundedCornerShape(bottomStart = 40.dp)) // Bo góc phía dưới bên trái
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize() // Chiếm toàn bộ kích thước của Box
                                        ) {
                                            // Phần Header: Hiển thị thông tin phòng và đèn
                                            Row(
                                                modifier = Modifier.padding(
                                                    top = 8.dp,
                                                    end = 12.dp
                                                ) // Canh lề trên và phải
                                            ) {
                                                // Cột bên trái: Thông tin về phòng và độ sáng
                                                Column(
                                                    modifier = Modifier
                                                        .padding(
                                                            start = 12.dp,
                                                            end = 12.dp
                                                        ) // Canh lề trái và phải
                                                        .fillMaxWidth() // Chiều rộng đầy đủ
                                                        .background(color = colorScheme.primary) // Nền xanh
                                                        .weight(0.2f), // Trọng lượng chiếm 20% của Row
                                                    horizontalAlignment = Alignment.Start,
                                                    verticalArrangement = Arrangement.SpaceBetween // Phân bố các thành phần cách đều nhau
                                                ) {
                                                    Text(
                                                        text = "Dining Room",
                                                        color = colorScheme.onPrimary,
                                                    ) // Tiêu đề
                                                    Spacer(modifier = Modifier.height(4.dp)) // Khoảng cách

                                                    // Switch bật/tắt đèn với icon
                                                    Switch(
                                                        checked = switchState,
                                                        onCheckedChange = {
                                                            //Todo: Xử lý khi tắt mở
                                                        }, // Hàm xử lý khi thay đổi trạng thái (để trống)
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

                                                    // Hiển thị mức độ sáng (80%)
                                                    Row(
                                                        modifier = Modifier.fillMaxWidth(),
                                                        horizontalArrangement = Arrangement.Start, // Căn trái
                                                        verticalAlignment = Alignment.Bottom // Căn dưới
                                                    ) {
                                                        Text(
                                                            "80",
                                                            fontWeight = FontWeight.Bold,
                                                            fontSize = 50.sp,
                                                            color = colorScheme.onPrimary
                                                        ) // Số phần trăm
                                                        Text(
                                                            "%",
                                                            fontWeight = FontWeight.Bold,
                                                            fontSize = 25.sp,
                                                            modifier = Modifier.offset(y = -8.dp), // Đẩy chữ % lên trên
                                                            color = colorScheme.onPrimary
                                                        )
                                                    }
                                                    Text(
                                                        "Brightness",
                                                        color = colorScheme.onPrimary,
                                                        fontSize = 20.sp
                                                    ) // Nhãn Brightness
                                                    Spacer(modifier = Modifier.height(8.dp))
                                                }

                                                // Hình ảnh chiếc đèn ở cột bên phải
                                                Image(
                                                    painter = painterResource(id = R.drawable.lamp),
                                                    modifier = Modifier.size(150.dp), // Kích thước hình
                                                    contentDescription = "", // Không có mô tả
                                                    colorFilter = ColorFilter.tint(colorScheme.onPrimary) // Màu chữ trắng
                                                )
                                            }

                                            // Thanh Slider điều chỉnh cường độ sáng và các ngày trong tuần
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(start = 12.dp, end = 12.dp)
                                                    .background(color = colorScheme.primary) // Nền xanh
                                            ) {
                                                Text(
                                                    "Insensity",
                                                    color = colorScheme.onPrimary,
                                                ) // Tiêu đề cường độ

                                                Column(
                                                    modifier = Modifier
                                                        .fillMaxWidth(),
                                                    horizontalAlignment = Alignment.CenterHorizontally,
                                                    verticalArrangement = Arrangement.Center
                                                ) {
                                                    // Hàng chứa thanh trượt và biểu tượng bóng đèn
                                                    Row(
                                                        modifier = Modifier
                                                            .width(500.dp)
                                                            .onSizeChanged { size ->
                                                                rowWidth = size.width
                                                            },
                                                        horizontalArrangement = Arrangement.Center, // Căn cách đều các thành phần
                                                        verticalAlignment = Alignment.CenterVertically // Căn giữa theo chiều dọc
                                                    ) {
                                                        // Hình bóng đèn tắt
                                                        Image(
                                                            painter = painterResource(id = R.drawable.bulboff),
                                                            modifier = Modifier
                                                                .padding(end = 8.dp)
                                                                .size(24.dp),
                                                            contentDescription = ""
                                                        )

                                                        // Thanh trượt (Slider) giá trị 80
                                                        Slider(
                                                            value = 80f,
                                                            onValueChange = {
                                                                //Todo: Xử lý logic chọn độ sáng
                                                            }, // Xử lý khi trượt (để trống)
                                                            steps = 50,
                                                            valueRange = 0f..100f,
                                                            modifier = Modifier.width(400.dp),
                                                            colors = SliderDefaults.colors(
                                                                thumbColor = colorScheme.onPrimary,
                                                                activeTrackColor = colorScheme.onPrimary,
                                                                activeTickColor = colorScheme.secondary,
                                                                inactiveTrackColor = colorScheme.secondary,
                                                                inactiveTickColor = colorScheme.onSecondary
                                                            )
                                                        )

                                                        // Hình bóng đèn bật
                                                        Image(
                                                            painter = painterResource(id = R.drawable.bulb),
                                                            modifier = Modifier
                                                                .padding(start = 8.dp)
                                                                .size(24.dp),
                                                            contentDescription = ""
                                                        )
                                                    }
                                                }
                                            }

                                            // Dòng kẻ phân cách
                                            Spacer(modifier = Modifier.height(2.dp))
                                            Box(
                                                modifier = Modifier
                                                    .padding(start = 12.dp, end = 12.dp)
                                                    .fillMaxWidth()
                                                    .height(1.dp)
                                                    .background(colorScheme.onPrimary)
                                            )

                                            Column(
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                Column(
                                                    modifier = Modifier
                                                        .padding(
                                                            top = 12.dp,
                                                            start = 12.dp,
                                                            end = 12.dp
                                                        )
                                                        .width(500.dp),
                                                    horizontalAlignment = Alignment.CenterHorizontally,
                                                    verticalArrangement = Arrangement.Center
                                                ) {
                                                    GradientSlider()
                                                }
                                            }
                                        }
                                    }
                                }
                                // Hộp màu xanh lá cây với góc lõm
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                ) {

                                    Box(
                                        modifier = Modifier
                                            .width(40.dp)
                                            .height(40.dp)
                                            .align(Alignment.TopEnd)
                                            .background(color = colorScheme.primary)
                                            .zIndex(1f) // Z-index thấp hơn
                                    )


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
                                                        .size(36.dp), // Kích thước tổng thể của Button
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
                                                        modifier = Modifier.size(36.dp), // Kích thước của Icon,
                                                        tint = colorScheme.primary
                                                    )
                                                }
                                                Button(
                                                    onClick = {
                                                        //Todo: Xử lý khi nhấn nút Wifi
                                                        navController.navigate(Screens.AccessPoint.route)
                                                    },
                                                    modifier = Modifier
                                                        .size(36.dp), // Kích thước tổng thể của Button
                                                    shape = CircleShape, // Đảm bảo Button có dạng hình tròn
                                                    contentPadding = PaddingValues(0.dp), // Loại bỏ padding mặc định
                                                    colors = ButtonDefaults.buttonColors(
                                                        containerColor = colorScheme.onPrimary,
                                                        contentColor = colorScheme.primary
                                                    )
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.Wifi,
                                                        contentDescription = "Wifi",
                                                        modifier = Modifier.size(36.dp),// Kích thước của Icon
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
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Column(
                                modifier = Modifier
                                    .width(500.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Select Time",
                                    fontSize = 24.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(bottom = 16.dp)
                                )
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = selectedTimeBegin,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                        modifier = Modifier
                                            .clickable { showDialogTimePickerBegin = true }
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "To",
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = selectedTimeEnd,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                        modifier = Modifier
                                            .clickable { showDialogTimePickerEnd = true }
                                    )
                                }

                                if (showDialogTimePickerBegin) {
                                    Dialog(onDismissRequest = {
                                        showDialogTimePickerBegin = false
                                    }) {
                                        Box(
                                            modifier = Modifier
                                                .background(
                                                    Color.Black,
                                                    shape = RoundedCornerShape(12.dp)
                                                )
                                                .padding(16.dp)
                                                .wrapContentSize()
                                        ) {
                                            EndlessRollingPadlockTimePicker { hour, minute, amPm ->
                                                selectedTimeBegin = "$hour:${
                                                    minute.toString().padStart(2, '0')
                                                } $amPm"
                                                showDialog = false // Đóng dialog sau khi chọn xong
                                            }
                                        }
                                    }
                                }
                                if (showDialogTimePickerEnd) {
                                    Dialog(onDismissRequest = { showDialogTimePickerEnd = false }) {
                                        Box(
                                            modifier = Modifier
                                                .background(
                                                    Color.Black,
                                                    shape = RoundedCornerShape(12.dp)
                                                )
                                                .padding(16.dp)
                                                .wrapContentSize()
                                        ) {
                                            EndlessRollingPadlockTimePicker { hour, minute, amPm ->
                                                selectedTimeEnd = "$hour:${
                                                    minute.toString().padStart(2, '0')
                                                } $amPm"
                                                showDialog = false // Đóng dialog sau khi chọn xong
                                            }
                                        }
                                    }
                                }
                                DayPicker()

                                Row(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .then(if (rowWidth != null) Modifier.width(rowWidth!!.dp) else Modifier.fillMaxWidth()),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp), // Khoảng cách giữa các nút
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Button(
                                        onClick = {
                                            //Todo: Xử lý khi nhấn nút lịch sử hoạt động
                                            navController.navigate(Screens.ActivityHistory.route)
                                        },
                                        modifier = Modifier
                                            .weight(1f) // Chia đều không gian
                                            .width(300.dp)
                                            .height(48.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
                                        shape = RoundedCornerShape(50)
                                    ) {
                                        Text(
                                            text = "Lịch sử hoạt động",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp,
                                            color = colorScheme.onPrimary
                                        )
                                    }

                                    Button(
                                        onClick = {
                                            //Todo: Xử lý khi nhấn nút gỡ liên kết
                                        },
                                        modifier = Modifier
                                            .weight(1f) // Chia đều không gian
                                            .width(300.dp)
                                            .height(48.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = colorScheme.error),
                                        shape = RoundedCornerShape(50)
                                    ) {
                                        Text(
                                            text = "Gỡ kết nối",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp,
                                            color = colorScheme.onError
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun GradientSlider() {
    var sliderPosition by remember { mutableStateOf(0f) } // Giá trị thanh kéo từ 0 đến 1

    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Thanh Slider có nền gradient (kích thước nhỏ)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp) // Điều chỉnh chiều cao của thanh Gradient
                    .drawWithCache {
                        val gradient = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Red,
                                Color.Yellow,
                                Color.Green,
                                Color.Cyan,
                                Color.Blue,
                                Color.Magenta,
                                Color.Red
                            ),
                            startX = 0f,
                            endX = size.width
                        )
                        onDrawBehind {
                            drawRect(brush = gradient, size = size)
                        }
                    }
            ) {
                Slider(
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp), // Kích thước Slider (track bar nhỏ hơn)
                    colors = SliderDefaults.colors(
                        thumbColor = colorScheme.onPrimary,
                        activeTrackColor = colorScheme.onPrimary,
                        activeTickColor = colorScheme.secondary,
                        inactiveTrackColor = colorScheme.secondary,
                        inactiveTickColor = colorScheme.onSecondary
                    )
                )
            }
        }
    }

}

// Hàm tính màu tại vị trí slider
@Composable
fun calculateGradientColor(position: Float): Color {
    val colors = listOf(
        Color.Red, Color.Yellow, Color.Green, Color.Cyan, Color.Blue, Color.Magenta, Color.Red
    )
    val index = (position * (colors.size - 1)).toInt()
    val startColor = colors[index]
    val endColor = colors[(index + 1).coerceAtMost(colors.size - 1)]
    val fraction = position * (colors.size - 1) - index
    return lerp(startColor, endColor, fraction)
}

@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun EndlessRollingPadlockTimePicker(
    modifier: Modifier = Modifier,
    onTimeSelected: (hour: Int, minute: Int, amPm: String) -> Unit // Callback trả kết quả ra ngoài
) {
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        val hoursList = (1..12).toList()
        val minutesList = (0..59).toList()
        val amPmList = listOf("AM", "PM")

        //Giá trị muốn hiên khi mở đầu
        val initialHour = 9
        val initialMinute = 0
        val initialAmPm = "AM" // Hoặc giá trị mong muốn

        // Kiểm tra giá trị AM/PM
        val amPmIndex = amPmList.indexOf(initialAmPm)
        if (amPmIndex == -1) {
            throw IllegalArgumentException("Invalid value for AM/PM: $initialAmPm. Allowed values: ${amPmList.joinToString()}")
        }

        // Tính toán chỉ số khởi tạo
        val hourState = rememberLazyListState(
            initialFirstVisibleItemIndex = (100 * hoursList.size / 2) + hoursList.indexOf(initialHour) - 1
        )
        val minuteState = rememberLazyListState(
            initialFirstVisibleItemIndex = (100 * minutesList.size / 2) + minutesList.indexOf(initialMinute) - 1
        )
        val amPmState = rememberLazyListState(
            initialFirstVisibleItemIndex = (100 * amPmList.size / 2) + amPmIndex -1
        )

        Box(
            modifier = modifier
                .wrapContentHeight()
                .background(colorScheme.background, shape = RoundedCornerShape(16.dp))
                .border(2.dp, Color.Gray, RoundedCornerShape(16.dp))
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(

            ) {
                // Cột giờ
                RollingColumn(
                    state = hourState,
                    items = hoursList,
                    modifier = Modifier.width(70.dp),
                    label = { hour -> hour.toString() }
                )

                // Dấu :
                LazyColumn(
                    modifier = modifier
                        .height(140.dp) // Chiều cao đồng bộ
                        .background(colorScheme.background.copy(alpha = 0.8f)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item{
                        Box(
                            modifier = Modifier
                                .wrapContentWidth()
                                .offset(y = 5.dp)
                                .height(50.dp), // Chiều cao cố định
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = ":",
                                fontSize = 36.sp,
                                color = colorScheme.onBackground,
                                modifier = Modifier
                                    .wrapContentSize()// Chiều rộng cố định
                                    .padding(horizontal = 4.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                // Cột phút
                RollingColumn(
                    state = minuteState,
                    items = minutesList,
                    modifier = Modifier.width(70.dp),
                    label = { minute -> minute.toString().padStart(2, '0') }
                )

                // Cột AM/PM
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width(70.dp)
                        .height(140.dp) // Đồng bộ chiều cao với các cột khác
                ) {
                    RollingColumn(
                        state = amPmState,
                        items = amPmList,
                        modifier = Modifier.align(Alignment.Center),
                        label = { amPm -> amPm },
                        fontSize = 36.sp // Đảm bảo AM/PM nổi bật
                    )
                }
            }
        }

        // Snap Effect
        LaunchedEffect(hourState.isScrollInProgress, minuteState.isScrollInProgress, amPmState.isScrollInProgress) {
            if (!hourState.isScrollInProgress && !minuteState.isScrollInProgress && !amPmState.isScrollInProgress) {
                val selectedHour = hoursList[(hourState.firstVisibleItemIndex + 1) % hoursList.size]
                val selectedMinute = minutesList[(minuteState.firstVisibleItemIndex + 1) % minutesList.size]
                val selectedAmPm = amPmList[(amPmState.firstVisibleItemIndex + 1) % amPmList.size]

                // Trả về kết quả khi cuộn xong
                onTimeSelected(selectedHour, selectedMinute, selectedAmPm)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EndlessRollingPadlockTimePickerPreview() {
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        var selectedTimeBegin by remember { mutableStateOf("12:00 AM") }
        Box(
            modifier = Modifier
                .background(
                    colorScheme.background,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp)
                .wrapContentSize()
        ) {
            EndlessRollingPadlockTimePicker { hour, minute, amPm ->
                selectedTimeBegin =
                    "$hour:${minute.toString().padStart(2, '0')} $amPm"
            }
        }
    }
}

@Composable
fun <T> RollingColumn(
    state: LazyListState,
    items: List<T>,
    modifier: Modifier = Modifier,
    label: (T) -> String,
    fontSize: TextUnit = 36.sp // Thay đổi kiểu từ Int sang TextUnit
) {
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        // Snap behavior để giữ mục ở trung tâm khi cuộn
        val snappingBehavior = rememberSnapFlingBehavior(state)

        LazyColumn(
            state = state,
            flingBehavior = snappingBehavior, // Thêm hiệu ứng snap
            modifier = modifier
                .height(140.dp) // Chiều cao đồng bộ
                .background(colorScheme.background.copy(alpha = 0.8f)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items.size * 100) { index ->
                val item = items[index % items.size]
                // Xác định mục được chọn
                val isSelected = (index % items.size == (state.firstVisibleItemIndex + 1) % items.size)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp), // Chiều cao cố định
                    contentAlignment = Alignment.Center,
                ) {Text(
                        text = label(item),
                        fontSize = if (isSelected) fontSize else fontSize * 0.8f, // Giảm font size cho mục không được chọn
                        color = colorScheme.onBackground.copy(alpha = if (isSelected) 1f else 0.4f),
                        modifier = Modifier
                            .padding(vertical = if (isSelected) 6.dp else 4.dp)
                    )
                }
            }
        }
    }
}

suspend fun snapToCenter(scope: CoroutineScope, state: LazyListState, size: Int) {
    if (!state.isScrollInProgress) {
        val index = state.firstVisibleItemIndex % size
        scope.launch { state.scrollToItem(index + size * 50) }
    }
}

@Preview
@Composable
fun DayPicker() {
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        // Danh sách các thứ trong tuần
        val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

        // State lưu trữ các ngày đã được chọn
        var selectedDays by remember { mutableStateOf(setOf<String>()) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Select Days",
                fontSize = 24.sp,
                color = colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Hiển thị các ngày trong tuần dưới dạng Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                daysOfWeek.forEach { day ->
                    DayItem(
                        day = day,
                        isSelected = selectedDays.contains(day),
                        onDayClicked = {
                            selectedDays = if (selectedDays.contains(day)) {
                                selectedDays - day
                            } else {
                                selectedDays + day
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Hiển thị kết quả các ngày đã chọn
            Text(
                text = "Selected Days: ${selectedDays.joinToString(", ")}",
                fontSize = 16.sp,
                color = colorScheme.onSecondary
            )
        }
    }
}

@Composable
fun DayItem(day: String, isSelected: Boolean, onDayClicked: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(50.dp)
            .background(
                color = if (isSelected) Color(0xFF4CAF50) else Color(0xFFEEEEEE),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onDayClicked() }
    ) {
        Text(
            text = day,
            fontSize = 16.sp,
            color = if (isSelected) Color.White else Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PhonePreview() {
    DeviceDetailPhoneScreen(navController = rememberNavController())
}


@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_TABLET)
@Composable
fun TabletPreview() {
    DeviceDetailTabletScreen(navController = rememberNavController())
}