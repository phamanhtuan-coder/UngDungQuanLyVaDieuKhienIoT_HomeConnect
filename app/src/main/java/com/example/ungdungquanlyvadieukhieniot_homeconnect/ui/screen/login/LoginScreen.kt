package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.login

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType


/** Giao diện màn hình Đăng Nhập (Login Screen)
 * -----------------------------------------
 * Người viết: Phạm Xuân Nhân
 * Ngày viết: 01/12/2024
 * Lần cập nhật cuối: 01/12/2024
 * -----------------------------------------
 * Input: Không có
 *
 * Output: Column chứa các thành phần giao diện của màn hình đăng nhập
 * ---------------------------------------
 * Người cập nhật: Phạm Anh Tuấn
 * Ngày cập nhật: 01/12/2024
 * ---------------------------------------
 * Nội dung cập nhật:
 *  - Dịch sang tiếng việt
 *  - Bỏ các biến tạm
 *  - Chỉnh sửa bố cục giao diện
 *   ---------------------------------------
 *  Người cập nhật: Phạm Xuân Nhân
 *  Ngày cập nhật: 02/12/2024
 *  ---------------------------------------
 *  Nội dung cập nhật:
 *  - responsive
 */


@Preview(showBackground = true)


@Composable
fun ResponsiveLoginScreen() {
    // Nhận cấu hình màn hình
    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp.dp
    val screenWidthDp = configuration.screenWidthDp.dp

    // Xác định xem nếu chúng ta đang ở chế độ ngang
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE


    //  kích thước phản hồi
    val horizontalPadding = when {
        screenWidthDp < 360.dp -> 8.dp
        screenWidthDp < 600.dp -> 16.dp
        else -> 32.dp
    }

    val verticalSpacing = when {
        screenHeightDp < 600.dp -> 8.dp
        screenHeightDp < 800.dp -> 12.dp
        else -> 16.dp
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = horizontalPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = if (isLandscape) Arrangement.Center else Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(verticalSpacing)
            ) {
                Text(
                    text = "Đăng nhập",
                    fontSize = if (isLandscape) 24.sp else 32.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Hãy đăng nhập để tiếp tục",
                    fontSize = if (isLandscape) 14.sp else 16.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(verticalSpacing * 2))

            // Email
            OutlinedTextField(
                value = "",
                onValueChange = {  },
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email Icon") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(verticalSpacing))

            // Mật khẩu
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (isLandscape) 50.dp else 60.dp)
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Mật khẩu") },
                    leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Lock Icon") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.matchParentSize(),
                    singleLine = true
                )

                TextButton(
                    onClick = { /* xử lý quên */ },
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Text(
                        text = "QUÊN",
                        color = Color(0xFFFFA726),
                        fontSize = if (isLandscape) 12.sp else 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(verticalSpacing))

            // Nút Login
            Button(
                onClick = { /* xử lý login */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (isLandscape) 40.dp else 50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA726)),
                shape = RoundedCornerShape(25.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ĐĂNG NHẬP",
                        color = Color.White,
                        fontSize = if (isLandscape) 16.sp else 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Arrow Icon",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(verticalSpacing))

            // Nút tới đăng ký
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Chưa có tài khoản?",
                    color = Color.Gray,
                    fontSize = if (isLandscape) 12.sp else 14.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(
                    onClick = { /* xử lý tới đăng ký */ },
                    modifier = Modifier.width(if (isLandscape) 50.dp else 60.dp)
                ) {
                    Text(
                        text = "Đăng ký",
                        color = Color(0xFFFFA726),
                        fontSize = if (isLandscape) 11.sp else 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}



