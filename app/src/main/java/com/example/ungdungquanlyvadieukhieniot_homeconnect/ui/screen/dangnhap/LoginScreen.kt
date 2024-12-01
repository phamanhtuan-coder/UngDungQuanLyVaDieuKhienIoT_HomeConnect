package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.dangnhap

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
 * Input:
 *
 *
 * Output:
 *
 * ---------------------------------------
 */


@Preview(showBackground = true)
@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),

    ) {

        Text(
            text = "Login",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 40.dp)
        )
        Text(
            text = "Please sign in to continue.",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
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
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Lock Icon") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .matchParentSize()
            )


            TextButton(
                onClick = { /* xử lý hiện mật khẩu */ },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 12.dp).size(70.dp)
            ) {
                Text(text = "FORGOT", color = Color(0xFFFFA726))
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // nút Login
        Button(
            onClick = { /* xử lýLogin */ },
            modifier = Modifier
                .width(150.dp)
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

                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "LOGIN",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Arrow Icon",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //nút tới đăng ký
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have an account?",
                color = Color.Gray,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            TextButton(onClick = { /* xử lý tới đăng ký */ }) {
                Text(
                    text = "Sign up",
                    color = Color(0xFFFFA726),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}



