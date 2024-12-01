package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.dangnhap

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
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
 */


@Preview(showBackground = true)
@Composable
fun LoginScreen() {
    return Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(
            text = "Đăng nhập",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 40.dp)
        )
        Text(
            text = "Hãy đăng nhập để tiếp tục",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Email
        OutlinedTextField(
            value = "",
            onValueChange = {
                /* xử lý thay đổi giá trị */
            },
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email Icon") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {
                     /* xử lý thay đổi giá trị */                },
                label = { Text("Mật khẩu") },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Lock Icon") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .matchParentSize()
            )


            TextButton(
                onClick = {
                /* xử lý hiện mật khẩu */
                },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 12.dp).size(70.dp)
            ) {
                Text(text = "QUÊN", color = Color(0xFFFFA726))
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Nút Login
        Button(
            onClick = { /* xử lý Login */ },
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .align(Alignment.End)
                .padding(end = 12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA726)),
            shape = RoundedCornerShape(25.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                //Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "ĐĂNG NHẬP",
                    color = Color.White,
                    fontSize = 18.sp,
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

        Spacer(modifier = Modifier.height(16.dp))

        //Nút tới đăng ký
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Chưa có tài khoản?",
                color = Color.Gray,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            TextButton(onClick = {
            /* xử lý tới đăng ký */
            }) {
                Text(
                    text = "Đăng ký",
                    color = Color(0xFFFFA726),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}