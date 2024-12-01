package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.password_recovery

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import com.example.ungdungquanlyvadieukhieniot_homeconnect.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/**
 * Màn hình Khôi phục mật khẩu
 * -----------------------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 1/12/2024
 * Lần cập nhật cuối: 1/12/2024
 * -----------------------------------------
 *
 * Mô tả:
 * - Hiển thị giao diện để người dùng khôi phục mật khẩu.
 * - Gồm các thành phần: logo, tiêu đề, hướng dẫn, trường nhập email, nút "Khôi phục mật khẩu", và liên kết "Quay lại để đăng nhập".
 *
 * Input:
 * - Người dùng nhập email vào trường "Nhập email của bạn".
 *
 * Output:
 * - Giao diện hiển thị nút hành động khôi phục mật khẩu.
 * - Liên kết quay lại để chuyển về màn hình đăng nhập.
 */


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordRecoveryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding() // Tự động thêm khoảng trống khi bàn phím xuất hiện
            .verticalScroll(rememberScrollState())
            .background(color = Color.White), // Đặt nền trắng
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Một cột con chứa toàn bộ các thành phần bên trong
        Column(
            modifier = Modifier
                .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 32.dp) // Cột con chiếm toàn bộ chiều rộng
        ) {
            // Cột chứa phần tiêu đề
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo ứng dụng
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo ứng dụng HomeConnect"
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tiêu đề "Khôi phục mật khẩu"
                Text(
                    text = "Khôi phục mật khẩu?",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Văn bản hướng dẫn khôi phục mật khẩu
                Text(
                    text = "Đừng lo, chúng tôi sẽ gửi hướng dẫn đặt lại (mật khẩu) cho bạn.",
                    textAlign = TextAlign.Center, // Căn giữa văn bản
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Tiêu đề "Email"
            Text(text = "Email", fontSize = 16.sp)

            // Trường nhập email với viền xung quanh
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    // Nhãn "Nhập email của bạn"
                    Text(
                        text = "Nhập email của bạn",
                        fontSize = 20.sp
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF6534BB),// Màu viền khi focus
                    unfocusedBorderColor = Color.Gray,     // Màu viền khi không focus
                    disabledBorderColor = Color.Gray, // Màu viền khi bị vô hiệu hóa
                    errorBorderColor = Color.Red           // Màu viền khi có lỗi
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .height(56.dp),
                singleLine = false, // Cho phép nhập nhiều dòng
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email, // Hiển thị bàn phím nhập email
                    imeAction = ImeAction.Done // Nút "Done" trên bàn phím
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nút "Khôi phục mật khẩu"
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6534BB), // Màu nền nút
                    contentColor = Color.White
                )
            ) {
                // Văn bản trên nút
                Text(
                    text = "Khôi phục mật khẩu",
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Hàng ngang chứa biểu tượng mũi tên và văn bản "Quay lại"
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* Xử lý sự kiện khi nhấn vào */ }
            ) {
                // Biểu tượng mũi tên quay lại
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Văn bản "Quay lại để đăng nhập"
                Text(
                    text = "Quay lại để đăng nhập",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}
