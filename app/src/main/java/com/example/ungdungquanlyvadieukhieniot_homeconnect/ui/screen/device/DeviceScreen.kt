package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.HouseSelection
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme


/** Giao diện màn hình Device Screen
 * -----------------------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 3/12/2024
 * Lần cập nhật cuối: 9/12/2024
 * -----------------------------------------
 * Input:
 *
 * Output: Scaffold
 *
 * ---------------------------------------
 */

/**
 * Sủa lại phần giao diện cho thiết bị tablet
 * ---------------------------
 * Lần cập nhật 9/12/2024
 * ---------------------------
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceScreen(
    navController: NavHostController
) {
    AppTheme {
        val configuration = LocalConfiguration.current
        val screenWidthDp = configuration.screenWidthDp.dp
        val isTablet = screenWidthDp >= 600.dp
        var selectedTabIndex by remember { mutableStateOf(0) }

        val colorScheme = MaterialTheme.colorScheme

        // Lấy mật độ để tính px
        val cornerRadiusPx = with(LocalDensity.current) { 16.dp.toPx() }

        Scaffold(
            containerColor = colorScheme.background,
            modifier = Modifier.fillMaxSize(),
            topBar = {
                /*
            * Hiển thị Header
             */
                Header(
                    navController = navController,
                    type = "Back",
                    title = "Trang cá nhân"
                )

            },
            bottomBar = {
                /*
            * Hiển thị Thanh Menu dưới cùng
             */
                MenuBottom(navController)
            },
            content = { innerPadding ->
                var widgetWidth by remember { mutableStateOf(0) }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize() // Đảm bảo chiếm toàn bộ không gian
                            .imePadding() // Tự động thêm khoảng trống khi bàn phím xuất hiện
                            .verticalScroll(rememberScrollState()) // Cho phép cuộn
                            .padding(innerPadding)
                    ) {
                        // Nội dung bên dưới
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = colorScheme.background)
                                .height(150.dp)
                        ) {
                            // Hộp màu xanh dương
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .offset(y = -9.dp)
                                    .height(110.dp)
                                    .background(
                                        color = colorScheme.primary,
                                        shape = RoundedCornerShape(bottomStartPercent = 60)
                                    )
                                    .zIndex(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .width(300.dp)
                                        .align(Alignment.Center)
                                ) {
                                    HouseSelection(
                                        houses = listOf("House 1", "House 2", "House 3"),
                                        onManageHouseClicked = { /* TODO: Navigate */ }
                                    )
                                }
                            }
                            // Hộp màu xanh với góc lõm
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 100.dp)
                                    .width(40.dp)
                                    .height(40.dp)
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


                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = colorScheme.background,
                                            shape = RoundedCornerShape(topEndPercent = 100)
                                        )
                                        .width(40.dp)
                                        .height(40.dp)
                                        .zIndex(2f) // Z-index cao hơn
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(topEndPercent = 100)) // Clip nội dung ScrollableTabRow
                                    ) {
                                        CustomScrollableTabRow() // Đặt ScrollableTabRow vào đây
                                    }
                                }
                            }
                        }
                        Row(
                            modifier = Modifier
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically // Căn giữa theo chiều dọc
                        ) {
                            Text(
                                text = "Số lượng thiết bị:",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = colorScheme.onBackground,
                                modifier = Modifier.padding(end = 4.dp) // Thêm khoảng cách giữa Text và Box
                            )
                            Box(
                                modifier = Modifier
                                    .size(24.dp) // Kích thước Box
                                    .background(
                                        color = Color.DarkGray,
                                        shape = RoundedCornerShape(4.dp)
                                    ), // Màu nền và góc bo
                                contentAlignment = Alignment.Center // Căn Text nằm giữa Box
                            ) {
                                Text(
                                    text = "4",
                                    color = colorScheme.onPrimary, // Màu chữ
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = colorScheme.background),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            //ToDo: Đổi thành Lazy Collumn
                            Column(
                                modifier = Modifier
                                    .onGloballyPositioned { coordinates ->
                                        // Lấy Width của Widget
                                        widgetWidth = coordinates.size.width
                                    }
                                    .wrapContentSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                SmartCard(isTablet, false, navController)
                                SmartCard(isTablet, navController = navController)
                                SmartCard(isTablet, false, navController)
                                SmartCard(isTablet, navController = navController)
                            }
                        }
                    }
                }


            }
        )
    }

}

@Composable
fun CustomScrollableTabRow() {
    var selectedTabIndex by remember { mutableStateOf(0) }

    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 8.dp,
            modifier = Modifier
                .background(colorScheme.background, RoundedCornerShape(16.dp)), // Nền và bo góc
            containerColor = colorScheme.background, // Màu nền bên trong TabRow
            indicator = { tabPositions ->
                SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    height = 3.dp, // Độ cao con trỏ
                    color = colorScheme.primary // Màu con trỏ
                )
            }
        ) {
            (1..10).forEach { index ->
                Tab(
                    selected = selectedTabIndex == index - 1,
                    onClick = { selectedTabIndex = index - 1 },
                    text = {
                        Text(
                            "Tab $index",
                            color = if (selectedTabIndex == index - 1) Color.Black else Color.Gray, // Đổi màu chữ
                        )
                    },
                    selectedContentColor = colorScheme.onBackground, // Màu khi được chọn
                    unselectedContentColor = colorScheme.onBackground // Màu khi không được chọn
                )
            }
        }
    }
}


@Composable
fun SmartCard(isTablet: Boolean, switchState: Boolean = true, navController: NavHostController) {
    val endPadding = 32.dp
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
    Card(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = {
            //Todo: Xử lý chuyển tới trang chi tiết thiết bị
            navController.navigate("device_detail")
        }
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "Smart Lamp",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = colorScheme.onPrimary
                    )
                    Text(
                        text = "Dining Room | Tue Thu",
                        fontSize = 12.sp,
                        color = colorScheme.onSecondary
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(40.dp),
                    contentAlignment = Alignment.Center
                ) {
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
                            uncheckedTrackColor = colorScheme.onSecondary.copy(alpha = 0.8f),
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.wrapContentWidth()
            ) {
                DeviceInfoSection("8 pm", "8 am", endPadding)

                if (isTablet) {
                    ExtraInfoSection("Điện áp hoạt động", "5V", endPadding / 2)
                    ExtraInfoSection("Dòng tiêu thụ", "20mA", endPadding / 2)
                }

                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButtonBox("\uD83D\uDDD1")
                    Spacer(modifier = Modifier.height(4.dp))
                    IconButtonBox("\u270E")
                }
            }
        }
    }
}
}




@Composable
fun DeviceInfoSection(fromTime: String, toTime: String, endPadding: Dp) {
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconBox("\uD83D\uDCA1", colorScheme.background)
            TimeInfo("from", fromTime)
            DividerLine(endPadding)
            TimeInfo("to", toTime)
            DividerLine(endPadding)
        }
    }
}

@Composable
fun ExtraInfoSection(label: String, value: String, endPadding: Dp) {
    Column(
        modifier = Modifier.padding(end = endPadding),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = label, fontSize = 12.sp, color = Color.Gray)
        Text(text = value, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
    DividerLine(endPadding)
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
fun TimeInfo(label: String, time: String) {
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        Column(
            modifier = Modifier.padding(end = 12.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(text = label, fontSize = 12.sp, color = colorScheme.onPrimary)
            Text(
                text = time,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun DividerLine(endPadding: Dp) {
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        Box(
            modifier = Modifier
                .padding(end = endPadding)
                .width(1.dp)
                .height(50.dp)
                .background(colorScheme.onSecondary)
        )
    }
}

@Composable
fun IconButtonBox(icon: String) {
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(colorScheme.background, RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = icon, fontSize = 12.sp, color = colorScheme.onBackground)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DeviceScreenPreview() {
    DeviceScreen(navController = rememberNavController())
}