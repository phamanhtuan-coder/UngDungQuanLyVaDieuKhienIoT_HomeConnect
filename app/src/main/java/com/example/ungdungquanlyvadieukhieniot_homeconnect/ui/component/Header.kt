package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Giao diện Header (TopAppBar)
 * -----------------------------------------
 * Người viết: Phạm Anh Tuấn
 * Ngày viết: 29/11/2024
 * Lần cập nhật cuối: 29/11/2024
 * -----------------------------------------
 * Input: Chuỗi loại cho header
 * Output: TopAppBar
 *
 *
 * ---------------------------------------
 */
@Preview(showBackground = true)
@Composable
fun Header(
    type: String = "Home",
    title: String = "",
) {
    when (type) {
        "Home" -> HomeHeader()
        "Back" -> BackHeader(title)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackHeader(
    title: String,
) {
        return TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Blue,
            ),
            /*
            * Hiển thị tên màn hình hiện tại
             */
            title = {

                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = title,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )




            },
            navigationIcon = {
                IconButton(
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .clip(CircleShape),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        /*TODO*/
                    }
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            /*
            * Hiển thị Nút thông báo
             */
            actions = {
                IconButton(
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .clip(CircleShape),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                }
            }
        )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeHeader() {
    return TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue,
        ),
        /*
        * Hiển thị câu chào tên người dùng
         */
        title = {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "Good Morning,",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Username", color = Color.Black, fontSize = 16.sp)

            }


        },
        /*
        * Hiển thị Nút thông báo
         */
        actions = {
            IconButton(
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .clip(CircleShape),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                onClick = { /*TODO*/ }
            ) {
                Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
            }
        }
    )
}