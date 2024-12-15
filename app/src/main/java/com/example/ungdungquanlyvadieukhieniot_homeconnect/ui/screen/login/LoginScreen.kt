package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Màn hình đăng nhập
 * ---------------------
 * Người viết: Phạm Xuân Nhân
 * Ngày viết: 2/12/2024
 * Lần cập nhật cuối: 15/12/2024
 * ---------------------
 * @return Box chứa toàn bộ nội dung màn hình đăng nhập
 * ---------------------
 * Nguười cập nhật: Phạm Anh Tuấn
 * Ngày cập nhật: 15/12/2024
 * Nội dung cập nhật:
 * - Thêm chức năng hiển thị mật khẩu
 * - Thêm chức năng chuyển tới màn hình đăng ký
 * - Thêm chức năng chuyển tới màn hình quên mật khẩu
 * - Thêm chức năng kiểm tra màn hình hiển thị là máy tính bảng hay điện thoại
 * - Chỉnh sửa layout cho phù hợp với thiết kế
 * ---------------------
 */
@Composable
fun LoginScreen() {
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600

    val backgroundColor = Color(0xFFF2F2F2)
    val primaryColor = Color(0xFFFFA726)
    val textColor = Color(0xFF333333)
    val secondaryTextColor = Color(0xFF666666)

    var passwordVisible by remember { mutableStateOf(false) }

   return Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = if (isTablet) 32.dp else 16.dp)
                .verticalScroll(rememberScrollState())
                .imePadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            // Tiêu đề đăng nhập
            Text(
                text = "Đăng nhập",
                fontSize = if (isTablet) 28.sp else 24.sp,
                fontWeight = FontWeight.Bold,
                color = primaryColor
            )
            Text(
                text = "Hãy đăng nhập để tiếp tục",
                fontSize = 14.sp,
                color = secondaryTextColor
            )

            // Trường nhập Email
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            // Trường nhập mật khẩu
            OutlinedTextField(
                value = "",
                onValueChange = {
                    /* TODO: Xử lý sự kiện khi nhập mật khẩu */
                },
                label = { Text("Mật khẩu") },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            // Nút quên mật khẩu
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { /* TODO: Xử lý khi nhấn nút quên mật khẩu */ }) {
                    Text(
                        text = "Quên mật khẩu?",
                        fontSize = 14.sp,
                        color = primaryColor
                    )
                }
            }

            // Nút đăng nhập
            Button(
                onClick = { /* TODO: Xử lý khi nhấn nút đăng nhập */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (isTablet) 56.dp else 48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = "Đăng nhập",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            // Chuyển tới đăng ký
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Chưa có tài khoản?",
                    fontSize = 14.sp,
                    color = textColor
                )
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(onClick = { /* TODO: Xử lý khi nhấn nút đăng ký */ }) {
                    Text(
                        text = "Đăng ký",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = primaryColor
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}