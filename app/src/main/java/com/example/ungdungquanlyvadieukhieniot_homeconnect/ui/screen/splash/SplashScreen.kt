package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.splash

import com.example.ungdungquanlyvadieukhieniot_homeconnect.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Màn hình Splash hiển thị logo và tên công ty
 * -----------------------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 30/11/2023
 * Lần cập nhật cuối: 30/11/2023
 * -----------------------------------------
 * Input: Không có
 *
 * Output: Hiển thị màn hình Splash với logo ở giữa và tên Nhóm ở đáy
 *
 * ---------------------------------------
 */

@Preview
@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        // Hiển thị logo ở giữa màn hình
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Mô tả hình ảnh",
            modifier = Modifier.align(Alignment.Center) // Căn giữa
        )

        // Hiển thị tên nhóm ở đáy màn hình
        Text(
            text = "CKC F.IT SmartNet Solutions",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomCenter) // Căn ở đáy và giữa màn hình
                .padding(bottom = 42.dp) // Cách đáy 42dp
        )
    }

}

