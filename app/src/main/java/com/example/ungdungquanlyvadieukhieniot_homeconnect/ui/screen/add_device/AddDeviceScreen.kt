package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.add_device

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Room
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme


/** Giao diện màn hình Thêm thiết bị (AddDeviceScreen)
 * -----------------------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 17/12/2024
 * Lần cập nhật cuối: 17/12/2024
 * -----------------------------------------
 * @param navController: Đối tượng điều khiển dẫn hướng trong Compose Navigation
 * @return Scaffold: Giao diện màn hình thêm thiết bị
 *
 * ---------------------------------------
 */
@Composable
fun AddDeviceScreen(
    navController: NavHostController
) {
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600
    AppTheme {

        val colorScheme = MaterialTheme.colorScheme
        Scaffold(
            containerColor =colorScheme.background,
            topBar = {
                /*
            * Hiển thị Header
             */
                Header(navController, "Back", "Liên kết thiết bị")
            },
            bottomBar = {
                /*
            * Hiển thị Thanh Menu dưới cùng
             */
                MenuBottom(navController)
            },
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize() // Đảm bảo chiếm toàn bộ không gian
                            .imePadding() // Tự động thêm khoảng trống khi bàn phím xuất hiện
                            .verticalScroll(rememberScrollState()) // Cho phép cuộn
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center, // Căn giữa theo chiều dọc
                        horizontalAlignment = Alignment.CenterHorizontally // Căn giữa theo chiều ngang
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize() // Chiếm toàn bộ kích thước màn hình
                                .background(colorScheme.background) // Đặt màu nền tổng thể
                                .padding(8.dp) // Khoảng cách viền xung quanh màn hình
                        ) {
                            // Màn hình "Chia sẻ thiết bị" (nằm bên phải)
                            Box(
                                modifier = Modifier
                                    .weight(1f) // Chiếm 50% chiều rộng của Row
                                    .background(
                                        colorScheme.background, // Màu nền
                                        shape = RoundedCornerShape(12.dp)
                                    ) // Nền trắng với góc bo tròn 12dp
                                    .padding(16.dp) // Khoảng cách bên trong Box
                            ) {
                                // Nội dung của màn hình
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally, // Căn giữa theo chiều ngang
                                    verticalArrangement = Arrangement.Center, // Căn giữa theo chiều dọc
                                    modifier = Modifier.fillMaxSize() // Chiếm toàn bộ kích thước của Box
                                ) {

                                    // Cột chứa các ô nhập liệu và nút gửi yêu cầu
                                    Column(
                                        modifier = Modifier
                                            .width(400.dp) // Đặt chiều rộng cố định cho cột
                                    ) {
                                        // Ô nhập ID thiết bị
                                        OutlinedTextField(
                                            value = "", // Giá trị hiện tại (để trống)
                                            onValueChange = {
                                                //Todo: Xử lý thay đổi giá trị
                                            }, // Hàm xử lý khi nhập liệu (chưa triển khai)
                                            leadingIcon = { // Biểu tượng ở bên trái ô nhập liệu
                                                Icon(
                                                    Icons.Filled.Person,
                                                    contentDescription = null
                                                ) // Icon người dùng
                                            },
                                            shape = RoundedCornerShape(25),
                                            singleLine = true,
                                            placeholder = { Text("ID Thiết bị") }, // Nhãn cho ô nhập liệu
                                            modifier = Modifier
                                                .width(if (isTablet) 500.dp else 400.dp)
                                                .height(if (isTablet) 80.dp else 70.dp),
                                            colors = TextFieldDefaults.colors(
                                                focusedContainerColor = colorScheme.onPrimary,
                                                unfocusedContainerColor = colorScheme.onPrimary,
                                                focusedIndicatorColor = colorScheme.primary,
                                                unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                                            )
                                        )
                                        Spacer(modifier = Modifier.height(8.dp)) // Khoảng cách dưới ô nhập ID

                                        OutlinedTextField(
                                            value = "", // Giá trị hiện tại (để trống)
                                            onValueChange = {
                                                //todo: Xử lý thay đổi giá trị
                                            }, // Hàm xử lý khi nhập liệu (chưa triển khai)
                                            leadingIcon = { // Biểu tượng ở bên trái ô nhập liệu
                                                Icon(
                                                    Icons.Default.Devices,
                                                    contentDescription = null
                                                ) // Icon
                                            },
                                            singleLine = true,
                                            shape = RoundedCornerShape(25),
                                            placeholder = { Text("Tên thiết bị") }, // Nhãn cho ô nhập liệu
                                            modifier = Modifier
                                                .width(if (isTablet) 500.dp else 400.dp)
                                                .height(if (isTablet) 80.dp else 70.dp),
                                            colors = TextFieldDefaults.colors(
                                                focusedContainerColor = colorScheme.onPrimary,
                                                unfocusedContainerColor = colorScheme.onPrimary,
                                                focusedIndicatorColor = colorScheme.primary,
                                                unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                                            ),
                                        )
                                        Spacer(modifier = Modifier.height(8.dp)) // Khoảng cách dưới ô nhập Email

                                        OutlinedTextField(
                                            value = "",
                                            leadingIcon = { // Biểu tượng ở bên trái ô nhập liệu
                                                Icon(
                                                    Icons.Default.Room,
                                                    contentDescription = null
                                                )
                                            },
                                            singleLine = true,
                                            shape = RoundedCornerShape(25),
                                            onValueChange = { /* TODO: Xử lý thay đổi giá trị */ },
                                            placeholder = { Text("Chon phòng") },
                                            modifier = Modifier
                                                .width(if (isTablet) 500.dp else 400.dp)
                                                .height(if (isTablet) 80.dp else 70.dp),
                                            colors = TextFieldDefaults.colors(
                                                focusedContainerColor = colorScheme.onPrimary,
                                                unfocusedContainerColor = colorScheme.onPrimary,
                                                focusedIndicatorColor = colorScheme.primary,
                                                unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                                            ),
                                            trailingIcon = {
                                                //Todo: Khi nhấn vào thì thành dropdown box
                                                Icon(
                                                    imageVector = Icons.Default.KeyboardArrowDown,
                                                    contentDescription = null
                                                )
                                            }
                                        )
                                        Spacer(modifier = Modifier.height(16.dp)) // Khoảng cách dưới ô nhập Email

                                        // Nút gửi yêu cầu
                                        Button(
                                            onClick = { /* TODO: xử lý thêm thiết bị */ }, // Hàm xử lý khi nhấn nút (chưa triển khai)
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally) // Canh lề phải
                                                .width(if (isTablet) 300.dp else 200.dp)
                                                .height(if (isTablet) 56.dp else 48.dp),
                                            colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
                                            shape = RoundedCornerShape(50)
                                        ) {
                                            Text(
                                                "Liên kết",
                                                color = colorScheme.onPrimary,
                                            ) // Nội dung và màu chữ của nút
                                        }
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AddDeviceScreen(navController = rememberNavController())
}