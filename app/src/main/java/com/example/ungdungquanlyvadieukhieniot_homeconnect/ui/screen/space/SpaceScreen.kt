package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.space

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.HouseSelection
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.WarningDialog
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.Screens
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme


/** Giao diện màn hình Space Screen
 * -----------------------------------------
 * Người viết: Phạm Anh Tuấn
 * Ngày viết: 31/12/2024
 * Lần cập nhật cuối: 31/12/2024
 * -----------------------------------------
 * @param navController Đối tượng điều khiển điều hướng.
 *
 * @return Scaffold chứa giao diện màn hình Danh sách Space
 *
 * ---------------------------------------
 *
 */
@Composable
fun SpaceScreen(
    navController: NavHostController
) {
    val spaceCount = remember { mutableStateOf("5") }

    AppTheme {
        val configuration = LocalConfiguration.current
        val screenWidthDp = configuration.screenWidthDp.dp
        val isTablet = screenWidthDp >= 600.dp

        val colorScheme = MaterialTheme.colorScheme

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
                    title = "Danh sách Spaces"
                )

            },
            bottomBar = {
                /*
            * Hiển thị Thanh Menu dưới cùng
             */
                MenuBottom(navController)
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        //ToDo: Chuyển tới trang thêm thiết bị
                        navController.navigate(Screens.AddSpace.route)
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add Space",
                            tint = colorScheme.primary
                        )
                    },
                    containerColor = colorScheme.onPrimary,
                    contentColor = colorScheme.primary,
                    shape = CircleShape
                )
            },
            floatingActionButtonPosition = FabPosition.End,
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
                                    .offset(y = (-9).dp)
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
                                        .width(if (isTablet) 550.dp else 300.dp)
                                        .align(Alignment.Center)
                                ) {
                                    HouseSelection(
                                        houses = listOf("House 1", "House 2", "House 3"),
                                        onManageHouseClicked = {
                                            /* TODO: Navigate */
                                            navController.navigate(Screens.HouseManagement.route)
                                        }
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
                                            .clip(RoundedCornerShape(topEndPercent = 100)) // Clip nội dung ScrollableTabRow
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .padding(8.dp),
                                            verticalAlignment = Alignment.CenterVertically // Căn giữa theo chiều dọc
                                        ) {
                                            Text(
                                                text = "Số lượng spaces:",
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = colorScheme.onBackground,
                                                modifier = Modifier.padding(end = 4.dp) // Thêm khoảng cách giữa Text và Box
                                            )
                                            Box(
                                                modifier = Modifier
                                                    .size(24.dp) // Kích thước Box
                                                    .background(
                                                        color = colorScheme.primary,
                                                        shape = RoundedCornerShape(4.dp)
                                                    ), // Màu nền và góc bo
                                                contentAlignment = Alignment.Center // Căn Text nằm giữa Box
                                            ) {
                                                Text(
                                                    text = spaceCount.value,
                                                    color = colorScheme.onPrimary, // Màu chữ
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = colorScheme.background),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .onGloballyPositioned { coordinates ->
                                        widgetWidth = coordinates.size.width
                                    }
                                    .wrapContentSize()
                                    .heightIn(min = 0.dp, max = 500.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
                            ) {
                                items(spaceCount.value.toInt()) { index ->
                                    SpaceCard(
                                        isTablet = isTablet,
                                        roomName = "Phòng $index",
                                        temperature = (20.0 + index),
                                        totalDevices = 10 + index,
                                        activeDevices = 5 + index,
                                        isAllDevicesOn = index % 2 == 0,
                                        onSwitchToggled = { isOn ->
                                            // Handle toggle switch
                                            println("Phòng $index: Toàn bộ thiết bị đã ${if (isOn) "bật" else "tắt"}!")
                                        }
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
fun SpaceCard(
    isTablet: Boolean,
    roomName: String,
    temperature: Double,
    totalDevices: Int,
    activeDevices: Int,
    isAllDevicesOn: Boolean,
    onSwitchToggled: (Boolean) -> Unit
) {

    AppTheme {
        val colorScheme = MaterialTheme.colorScheme

        var switchState by remember { mutableStateOf(isAllDevicesOn) }
        var showAlertDialog by remember { mutableStateOf(false) }

        if (showAlertDialog) {
            WarningDialog(
                title = "Cảnh báo",
                text = "Hành động này sẽ tắt/mở toàn bộ thiết bị trong phòng $roomName. Bạn có chắc chắn không?",
                onConfirm = {
                    onSwitchToggled(switchState)
                    showAlertDialog = false
                },
                onDismiss = { showAlertDialog = false }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = if (isTablet) 24.dp else 16.dp, vertical = 8.dp)
                .background(color = colorScheme.secondary, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Room Icon
                Box(
                    modifier = Modifier
                        .size(if (isTablet) 80.dp else 60.dp)
                        .background(color = colorScheme.onPrimary, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = roomName.first()
                            .toString(), // Display the first letter of the room name
                        color = colorScheme.primary,
                        fontSize = if (isTablet) 32.sp else 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Room Details
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = roomName,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = if (isTablet) 20.sp else 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = colorScheme.onSecondary
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "Nhiệt độ: ",
                            style = MaterialTheme.typography.bodyMedium,
                            color = colorScheme.onSecondary
                        )
                        Text(
                            text = "$temperature °C",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (temperature >= 30) Color(0xFFE91E63) else if (temperature <= 10) Color(
                                0xFF2196F3
                            ) else Color(0xFF4CAF50)
                        )
                    }
                    Text(
                        text = "Thiết bị: $activeDevices/$totalDevices đang hoạt động",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorScheme.onSecondary
                    )
                }

                Switch(
                    checked = isAllDevicesOn,
                    onCheckedChange = { newState ->
                        switchState = newState
                        showAlertDialog = true
                    },
                    thumbContent = {
                        Icon(
                            imageVector = if (isAllDevicesOn) Icons.Filled.Check else Icons.Filled.Close,
                            contentDescription = "On/Off Switch",
                            tint = if (isAllDevicesOn) colorScheme.onPrimary else colorScheme.onSecondary.copy(
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
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SpaceScreenPreview() {
    SpaceScreen(navController = rememberNavController())
}