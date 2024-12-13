package com.example.ungdungquanlyvadieukhieniot_homeconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.home.HomeScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.house_management.HouseManagementScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screens.DashboardScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.UngDungQuanLyVaDieuKhienIoT_HomeConnectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UngDungQuanLyVaDieuKhienIoT_HomeConnectTheme {
          DashboardScreen()
//                HomeScreen()
//                HouseManagementScreen()


            }
        }
    }
}