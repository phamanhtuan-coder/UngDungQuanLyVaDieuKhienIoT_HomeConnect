package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.add_device

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Room
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.ungdungquanlyvadieukhieniot_homeconnect.R
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.NutHome
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.CustomScrollableTabRow
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.SmartCard
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.WeatherInfoItem


/** Giao diện màn hình Thêm thiết bị (AddDeviceScreen)
 * -----------------------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 17/12/2024
 * Lần cập nhật cuối: 17/12/2024
 * -----------------------------------------
 * Input:
 *
 * Output: Scaffold
 *
 * ---------------------------------------
 */
@Preview
@Composable
fun AddDeviceScreens() {
    return Scaffold(
        containerColor = Color.LightGray,
        topBar = {
            /*
            * Hiển thị Header
             */
            Header()
        },
        bottomBar = {
            /*
            * Hiển thị Thanh Menu dưới cùng
             */
            MenuBottom()
        },
        floatingActionButton = {
            /*
            * Hiển thị Nút Home
             */
            NutHome()
        },
        floatingActionButtonPosition = FabPosition.Center,
        content = { innerPadding ->
            var widgetWidth by remember { mutableStateOf(0) }
            Box (
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column (
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
                            .background(color = Color.LightGray)
                            .height(150.dp)
                    ) {
                        // Hộp màu xanh dương
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y = -9.dp)
                                .height(110.dp)
                                .background(color = Color.Blue, shape = RoundedCornerShape(bottomStartPercent = 60))
                                .zIndex(1f)
                        ) {
                            Box (
                                modifier = Modifier
                                    .wrapContentSize()
                                    .align(Alignment.Center)
                            ){
                                // Tiêu đề chính
                                Text(
                                    text = "Thêm thiết bị",
                                    fontSize = 36.sp, // Cỡ chữ lớn
                                    fontWeight = FontWeight.Bold, // Chữ đậm
                                    color = Color.White // Màu đen
                                )
                            }
                        }
                        // Hộp màu xanh lá cây với góc lõm
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
                                    .background(color = Color.Blue)
                                    .zIndex(1f) // Z-index thấp hơn
                            )

                            // Box màu xanh lá cây (ở trên)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = Color.LightGray, shape = RoundedCornerShape(topEndPercent = 100))
                                    .width(40.dp)
                                    .height(40.dp)
                                    .zIndex(2f) // Z-index cao hơn
                            ) {
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxSize() // Chiếm toàn bộ kích thước màn hình
                            .background(Color.LightGray) // Đặt màu nền tổng thể (màu xanh nhạt)
                            .padding(8.dp) // Khoảng cách viền xung quanh màn hình
                    ) {
                        // Màn hình "Chia sẻ thiết bị" (nằm bên phải)
                        Box(
                            modifier = Modifier
                                .weight(1f) // Chiếm 50% chiều rộng của Row
                                .background(Color.LightGray, shape = RoundedCornerShape(12.dp)) // Nền trắng với góc bo tròn 12dp
                                .padding(16.dp) // Khoảng cách bên trong Box
                        ) {
                            // Nội dung của màn hình
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally, // Căn giữa theo chiều ngang
                                verticalArrangement = Arrangement.Center, // Căn giữa theo chiều dọc
                                modifier = Modifier.fillMaxSize() // Chiếm toàn bộ kích thước của Box
                            ) {


                                // Logo ứng dụng
                                Image(
                                    painter = painterResource(id = R.drawable.logo), // Hình ảnh logo
                                    contentDescription = "Logo ứng dụng", // Mô tả cho logo
                                    modifier = Modifier.size(150.dp) // Kích thước logo (100x100dp)
                                )
                                Spacer(modifier = Modifier.height(16.dp)) // Khoảng cách dưới logo

                                // Cột chứa các ô nhập liệu và nút gửi yêu cầu
                                Column(
                                    modifier = Modifier
                                        .width(400.dp) // Đặt chiều rộng cố định cho cột
                                ) {
                                    // Ô nhập ID thiết bị
                                    OutlinedTextField(
                                        value = "", // Giá trị hiện tại (để trống)
                                        onValueChange = {}, // Hàm xử lý khi nhập liệu (chưa triển khai)
                                        leadingIcon = { // Biểu tượng ở bên trái ô nhập liệu
                                            Icon(Icons.Filled.Person, contentDescription = null) // Icon người dùng
                                        },
                                        label = { Text("ID Thiết bị") }, // Nhãn cho ô nhập liệu
                                        modifier = Modifier.fillMaxWidth() // Chiều rộng đầy đủ
                                    )
                                    Spacer(modifier = Modifier.height(8.dp)) // Khoảng cách dưới ô nhập ID

                                    // Ô nhập Email tài khoản
                                    OutlinedTextField(
                                        value = "", // Giá trị hiện tại (để trống)
                                        onValueChange = {}, // Hàm xử lý khi nhập liệu (chưa triển khai)
                                        leadingIcon = { // Biểu tượng ở bên trái ô nhập liệu
                                            Icon(Icons.Default.Devices, contentDescription = null) // Icon email
                                        },
                                        label = { Text("Tên thiết bị") }, // Nhãn cho ô nhập liệu
                                        modifier = Modifier.fillMaxWidth(), // Chiều rộng đầy đủ
                                        visualTransformation = PasswordVisualTransformation() // Ẩn văn bản nếu cần thiết
                                    )
                                    Spacer(modifier = Modifier.height(8.dp)) // Khoảng cách dưới ô nhập Email
                                    // Trường "Ngày sinh"
                                    OutlinedTextField(
                                        value = "",
                                        leadingIcon = { // Biểu tượng ở bên trái ô nhập liệu
                                            Icon(Icons.Default.Room, contentDescription = null) // Icon email
                                        },
                                        onValueChange = { /* TODO: Xử lý thay đổi giá trị */ },
                                        label = { Text("Chon phòng") },
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        trailingIcon = {
                                            Icon(
                                                imageVector = Icons.Default.KeyboardArrowDown,
                                                contentDescription = null
                                            )
                                        }
                                    )
                                    Spacer(modifier = Modifier.height(16.dp)) // Khoảng cách dưới ô nhập Email

                                    // Nút gửi yêu cầu
                                    Button(
                                        onClick = { /* TODO */ }, // Hàm xử lý khi nhấn nút (chưa triển khai)
                                        colors = ButtonDefaults.buttonColors(Color(0xFFFF9800)), // Màu nền nút cam
                                        modifier = Modifier.fillMaxWidth() // Chiều rộng đầy đủ
                                    ) {
                                        Text("GỬI YÊU CẦU", color = Color.White) // Nội dung và màu chữ của nút
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