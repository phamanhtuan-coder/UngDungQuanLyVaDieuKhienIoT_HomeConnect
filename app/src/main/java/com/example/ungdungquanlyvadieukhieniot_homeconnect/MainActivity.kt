package com.example.ungdungquanlyvadieukhieniot_homeconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.home.HomeScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.UngDungQuanLyVaDieuKhienIoT_HomeConnectTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            UngDungQuanLyVaDieuKhienIoT_HomeConnectTheme {

            }
        }
    }
}