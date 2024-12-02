package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.otp

import com.example.ungdungquanlyvadieukhieniot_homeconnect.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Màn hình Xác nhận mã OTP
 * -----------------------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 1/12/2024
 * Lần cập nhật cuối: 3/12/2024
 * -----------------------------------------
 *
 * Mô tả:
 * - Hiển thị giao diện để người dùng nhập mã OTP được gửi qua email hoặc điện thoại.
 * - Gồm các thành phần: logo ứng dụng, tiêu đề, hướng dẫn, email hiển thị, các ô nhập mã OTP, nút xác nhận mã OTP,
 *   liên kết gửi lại mã OTP và đếm ngược thời gian.
 *
 * Input:
 * - Người dùng nhập mã OTP vào các ô nhập liệu.
 *
 * Output:
 * - Giao diện kiểm tra mã OTP hợp lệ hay không.
 * - Nút xác nhận có chiều rộng bằng với hàng chứa các ô OTP.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun OTPVerificationScreen() {
    val otpLength = 4 // Độ dài mã OTP (số ô nhập OTP)
    val otpValue = remember { Array(otpLength) { TextFieldValue("") } } // Khởi tạo danh sách các TextFieldValue cho mỗi ô OTP
    val focusRequesters = List(otpLength) { FocusRequester() } // Danh sách FocusRequester để quản lý focus cho từng ô OTP
    var rowWidth by remember { mutableStateOf(0) } // Chiều rộng hàng chứa ô OTP
    val density = LocalDensity.current.density // Mật độ điểm ảnh

    // Giao diện chính của màn hình
    Column(
        modifier = Modifier
            .background(color = Color.White) // Nền trắng cho màn hình
            .verticalScroll(rememberScrollState()) // Hỗ trợ cuộn dọc
            .fillMaxSize(), // Chiếm toàn bộ màn hình
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(top = 38.dp, bottom = 38.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo ứng dụng
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo ứng dụng",
                modifier = Modifier.size(100.dp) // Kích thước logo
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Tiêu đề
            Text(
                text = "Đăng ký",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Hướng dẫn nhập OTP
            Text(
                text = "Nhập mã đã được gửi đến",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = "example@gmail.com", // Email hiển thị
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable {}, // Placeholder cho sự kiện click
                fontSize = 16.sp,
                color = Color.Blue
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Các ô nhập OTP
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp), // Khoảng cách giữa các ô
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.onSizeChanged { size ->
                    rowWidth = size.width // Lưu kích thước hàng để sử dụng cho nút xác nhận
                }
            ) {
                repeat(otpLength) { index ->
                    OutlinedTextField(
                        value = otpValue[index], // Giá trị của ô OTP hiện tại
                        onValueChange = { input ->
                            otpValue[index] = TextFieldValue(input.text, TextRange(input.text.length)) // Cập nhật giá trị khi nhập
                        },
                        modifier = Modifier
                            .size(56.dp) // Kích thước ô OTP
                            .focusRequester(focusRequesters[index]), // Quản lý focus của ô
                        singleLine = true, // Chỉ cho phép nhập một dòng
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp), // Góc bo tròn
                        textStyle = androidx.compose.ui.text.TextStyle(
                            color = Color.Black,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color(0xFFF0F0F0), // Màu nền của ô
                            focusedBorderColor = Color.Blue, // Màu viền khi được chọn
                            unfocusedBorderColor = Color.Gray // Màu viền khi không được chọn
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number // Sử dụng bàn phím số
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Liên kết gửi lại mã OTP
            Text(
                text = "Bạn không nhận được mã OTP?",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = "Gửi lại mã",
                color = Color.Blue,
                fontSize = 16.sp,
                modifier = Modifier.clickable {} // Placeholder cho sự kiện click
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Nút xác nhận OTP
            Button(
                onClick = {}, // Placeholder cho sự kiện nhấn nút
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp), // Góc bo tròn của nút
                modifier = Modifier
                    .width((rowWidth / density).dp) // Chiều rộng nút bằng hàng chứa ô OTP
                    .height(48.dp) // Chiều cao nút
            ) {
                Text(
                    text = "Xác nhận OTP",
                    fontSize = 16.sp
                )
            }
        }
    }
}
