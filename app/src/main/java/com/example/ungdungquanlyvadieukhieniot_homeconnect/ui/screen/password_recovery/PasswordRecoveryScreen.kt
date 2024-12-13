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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
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
 * Lần cập nhật cuối: 2/12/2024
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

/**
 * Các phần đã thêm/sửa
 * -----------------------------------------
 * Lần cập nhật: 2/12/2024
 * -----------------------------------------
 * 1. Sử dụng `lineHeight` để tăng khoảng cách giữa các dòng văn bản.
 * 2. Tăng `fontSize` và chỉnh màu chữ trong `OutlinedTextField`.
 * 3. Tính chiều rộng động với `onGloballyPositioned` để đồng bộ bố cục.
 * 4. Cải thiện căn chỉnh và thêm hành động nhấn cho liên kết "Quay lại".
 */

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordRecoveryScreen() {
    // Biến lưu kích thước chiều rộng của Text (px và dp)
    var textWidthPx by remember { mutableStateOf(0) }
    var textWidthDp by remember { mutableStateOf(0.dp) }

    // Chuyển đổi kích thước từ px sang dp khi kích thước thay đổi
    val density = LocalDensity.current
    LaunchedEffect(textWidthPx) {
        textWidthDp = with(density) { textWidthPx.toDp() }
    }

    // Giao diện chính
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .imePadding() // Tự động thêm khoảng trống khi bàn phím xuất hiện
            .verticalScroll(rememberScrollState()) // Cho phép cuộn
            .background(color = Color.White), // Nền trắng
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Cột chứa toàn bộ nội dung màn hình
        Column(
            modifier = Modifier.padding(
                top = 38.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 38.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo ứng dụng
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo ứng dụng HomeConnect",
                modifier = Modifier.size(100.dp)
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
                textAlign = TextAlign.Center,
                lineHeight = 24.sp, // Thêm khoảng cách giữa các dòng
                fontSize = 16.sp,
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        // Lưu chiều rộng của Text sau khi bố trí (đơn vị px)
                        textWidthPx = coordinates.size.width
                    }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Cột chứa trường nhập email và nút hành động
            Column(
                modifier = Modifier.width(textWidthDp) // Chiều rộng khớp với Text
            ) {
                // Tiêu đề "Email"
                Text(
                    text = "Email",
                    fontSize = 16.sp
                )

                // Trường nhập email
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text(
                            text = "Nhập email của bạn",
                            fontSize = 16.sp // Tăng kích thước chữ placeholder
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF6534BB), // Màu viền khi focus
                        unfocusedBorderColor = Color.Gray, // Màu viền khi không focus
                        errorBorderColor = Color.Red // Màu viền khi có lỗi
                    ),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 18.sp, // Tăng kích thước chữ trong trường nhập liệu
                        color = Color.Black // Màu chữ khi nhập liệu
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .height(58.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Nút "Khôi phục mật khẩu"
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .height(58.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6534BB),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Khôi phục mật khẩu",
                        fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Hàng ngang chứa liên kết "Quay lại"
                Row(
                    modifier = Modifier.width(textWidthDp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = {}),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

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
    }
}



