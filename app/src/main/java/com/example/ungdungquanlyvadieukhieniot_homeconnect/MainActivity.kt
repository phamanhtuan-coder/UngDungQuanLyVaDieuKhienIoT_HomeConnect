package com.example.ungdungquanlyvadieukhieniot_homeconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.NavigationGraph
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var navController: NavHostController
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            AppTheme {
                navController = rememberNavController()
                NavigationGraph(navController)
            }
        }
    }
}