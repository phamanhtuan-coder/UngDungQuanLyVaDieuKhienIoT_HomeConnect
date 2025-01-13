package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.DefaultScreen

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme

@Composable
fun DefaultScreen(navController: NavHostController) {
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme

        Scaffold(
            topBar = {
                /*
            * Hiển thị Header
             */
                Header(
                    navController = navController,
                    type = "Back",
                    title = "Chi tiết thiết bị"
                )
            },
            bottomBar = {
                /*
            * Hiển thị Thanh Menu dưới cùng
             */
                MenuBottom(navController)
            },
            containerColor = colorScheme.background,
            modifier = Modifier.fillMaxSize(),
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .background(color = colorScheme.background)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Không tìm thấy màn hình phù hợp",
                        style = MaterialTheme.typography.titleMedium,
                        color = colorScheme.onBackground
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_TABLET)
@Composable
fun DefaultScreenPreview() {
    DefaultScreen(navController = rememberNavController())
}
