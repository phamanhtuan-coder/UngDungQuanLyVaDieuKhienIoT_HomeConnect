package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.sharedevice

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ungdungquanlyvadieukhieniot_homeconnect.R


/** Giao diện màn hình Share Device (ShareDeviceScreen)
 * -----------------------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 07/12/2024
 * Lần cập nhật cuối: 07/12/2024
 * -----------------------------------------
 * Input:
 *
 * Output: Row
 *
 * ---------------------------------------
 */
@Composable
fun ShareDeviceScreens() {
    return Row(
        modifier = Modifier
            .fillMaxSize() // Chiếm toàn bộ kích thước màn hình
            .background(Color(0xFFE6F0FF)) // Đặt màu nền tổng thể (màu xanh nhạt)
            .padding(8.dp) // Khoảng cách viền xung quanh màn hình
    ) {
        // Màn hình "Chia sẻ thiết bị" (nằm bên phải)
        Box(
            modifier = Modifier
                .weight(1f) // Chiếm 50% chiều rộng của Row
                .background(Color.White, shape = RoundedCornerShape(12.dp)) // Nền trắng với góc bo tròn 12dp
                .padding(16.dp) // Khoảng cách bên trong Box
        ) {
            // Nội dung của màn hình
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, // Căn giữa theo chiều ngang
                verticalArrangement = Arrangement.Center, // Căn giữa theo chiều dọc
                modifier = Modifier.fillMaxSize() // Chiếm toàn bộ kích thước của Box
            ) {
                // Tiêu đề chính
                Text(
                    text = "CHIA SẼ THIẾT BỊ",
                    fontSize = 24.sp, // Cỡ chữ lớn
                    fontWeight = FontWeight.Bold, // Chữ đậm
                    color = Color.Black // Màu đen
                )
                Spacer(modifier = Modifier.height(8.dp)) // Khoảng cách dưới tiêu đề

                // Logo ứng dụng
                Image(
                    painter = painterResource(id = R.drawable.logo), // Hình ảnh logo
                    contentDescription = "Logo ứng dụng", // Mô tả cho logo
                    modifier = Modifier.size(100.dp) // Kích thước logo (100x100dp)
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
                            Icon(Icons.Filled.Email, contentDescription = null) // Icon email
                        },
                        label = { Text("Email tài khoản") }, // Nhãn cho ô nhập liệu
                        modifier = Modifier.fillMaxWidth(), // Chiều rộng đầy đủ
                        visualTransformation = PasswordVisualTransformation() // Ẩn văn bản nếu cần thiết
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

