package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Castle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Cottage
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.LocalLibrary
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Villa
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.access_point_connection.isTablet
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.access_point_connection.rememberResponsiveLayoutConfig
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.wifi_connection.WifiConnectionScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme
import com.example.ungdungquanlyvadieukhieniot_homeconnect.R
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.Screens

/** Giao diện màn hình Settings Screen (SettingsScreen)
 * -----------------------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 31/12/2024
 * Lần cập nhật cuối: 31/12/2024
 * -----------------------------------------
 * @param navController Đối tượng điều khiển điều hướng.
 * @return Scaffold chứa toàn bộ nội dung
 */
@Composable
fun SettingsScreen(
    navController: NavHostController
) {
    val layoutConfig = rememberResponsiveLayoutConfig() // Lấy LayoutConfig

    AppTheme {
        // Trạng thái lưu trữ card được chọn
        val colorScheme = MaterialTheme.colorScheme
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = colorScheme.background,
            topBar = {
                Header(navController, "Back", "Các cài đặt")
            },
            bottomBar = {
                MenuBottom(navController)
            },
            content = { innerPadding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize() // Chiếm toàn bộ kích thước của màn hình
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.Top, // Sắp xếp các item theo chiều dọc, bắt đầu từ trên xuống.
                    horizontalAlignment = Alignment.CenterHorizontally // Căn chỉnh các item theo chiều ngang vào giữa.
                ) {
                    // Tiêu đề
                    item {
                        // Cột chứa các phần tử con
                        Column (
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            // Cột chứa văn bản tiêu đề và các TextField
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth() // Chiếm toàn bộ chiều rộng
                                    .padding(
                                        horizontal = layoutConfig.outerPadding, // Padding ngang linh hoạt
                                        vertical = layoutConfig.textFieldSpacing // Padding dọc linh hoạt
                                    ),
                                horizontalAlignment = Alignment.CenterHorizontally // Căn giữa các phần tử con theo chiều ngang
                            ) {
                                CardSettings(
                                    icon = Icons.Default.Password,
                                    title = "Đổi mật khẩu",
                                    onClick = {
                                        //ToDo: Kiểm tra dữ liệu, di chuyển đền màn hình cài đặt password.
                                        navController.navigate(Screens.UpdatePassword.route)
                                    }
                                )

                                CardSettings(
                                    icon = Icons.Default.Notifications,
                                    title = "Thông báo",
                                    onClick = {
                                        //ToDo: Kiểm tra dữ liệu, di chuyển đền màn hình cài đặt thông báo.
                                    }
                                )

                                CardSettings(
                                    icon = Icons.Default.Apps,
                                    title = "Ứng dụng",
                                    onClick = {
                                        //ToDo: Kiểm tra dữ liệu, di chuyển đền màn hình cài đặt ứng dụng.
                                    }
                                )

                                CardSettings(
                                    icon = Icons.Default.ExitToApp,
                                    title = "Đăng xuất",
                                    onClick = {
                                        //ToDo: Kiểm tra dữ liệu, di chuyển đền màn hình cài đặt đăng suất.
                                    }
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun CardSettings(icon: ImageVector, title: String, onClick: () -> Unit) {
    val colorScheme = MaterialTheme.colorScheme

    Row(
        modifier = Modifier
            .clickable {onClick ()}
            .width(if (isTablet()) 500.dp else 400.dp)
            .height(if (isTablet()) 80.dp else 70.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = colorScheme.onBackground,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        // Title
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = colorScheme.onBackground,
            modifier = Modifier.weight(1f)
        )
        // Arrow Icon
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = colorScheme.onBackground,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(navController = rememberNavController())
}