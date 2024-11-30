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
import androidx.compose.ui.unit.sp

/**
 * Màn hình Splash hiển thị logo và tên công ty
 * -----------------------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 30/11/2024
 * Lần cập nhật cuối: 1/12/2024
 * -----------------------------------------
 * Input: Không có
 *
 * Output: Column chứa logo, tên ứng dụng và tên công ty
 *
 * ---------------------------------------
 * Người cập nhật: Phạm Anh Tuấn
 * Ngày cập nhật: 1/12/2024
 *
 * Nội dung cập nhật:
 *  - Thêm chú thích
 *  - Thêm hàm @Preview
 *  - Thêm text tên App
 *  - Chuyển Box thành Column
 *
 */


@Preview(showBackground = true)
@Composable
fun SplashScreen() {
   return Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
       //Căn giữa các thành phần
        verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo ứng dụng HomeConnect"
        )
       Spacer(modifier = Modifier.height(8.dp))
       Text(
           text = "HomeConnect",
           fontWeight = FontWeight.ExtraBold,
           fontSize = 20.sp
         )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "CKC F.IT SmartNet Solutions",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }

}