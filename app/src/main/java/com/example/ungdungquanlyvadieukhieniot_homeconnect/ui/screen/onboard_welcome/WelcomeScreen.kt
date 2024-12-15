package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.onboard_welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ungdungquanlyvadieukhieniot_homeconnect.R

/**
 * Màn hình Welcome hiển thị logo và thông điệp chào mừng
 * -----------------------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 30/11/2024
 * Lần cập nhật cuối: 15/12/2024
 * -----------------------------------------
 * Input: Không có
 *
 * Output: Column chứa logo, thông điệp chào mừng và nút truy cập
 *
 * ---------------------------------------
 * Người cập nhật: Phạm Anh Tuấn
 * Ngày cập nhật: 15/12/2024
 *
 * Nội dung cập nhật:
 *  - Cải thiện giao diện hiển thị text và thêm nút truy cập
 */

@Preview(showBackground = true)
@Composable
fun WelcomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF3D8CF0)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo ứng dụng HomeConnect",
            modifier = Modifier.size(500.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Welcome Message
        Text(
            text = "Chào mừng bạn đến với HomeConnect!",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { /* TODO: Xử lý sự kiện đang98 nhập */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(50),
                modifier = Modifier.size(width = 150.dp, height = 50.dp)
            ) {
                Text(
                    text = "Đăng nhập",
                    style = TextStyle(
                        color = Color(0xFF3D8CF0),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,

                        )
                )
            }

            Button(
                onClick = { /* TODO: Xử lý sự kiện đăng ký*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(50),
                modifier = Modifier.size(width = 150.dp, height = 50.dp)
            ) {
                Text(
                    text = "Đăng ký",
                    style = TextStyle(
                        color = Color(0xFF3D8CF0),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}