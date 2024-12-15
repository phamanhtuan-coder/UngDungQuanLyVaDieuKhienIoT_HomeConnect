package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.DeviceScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.home.HomeScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.login.LoginScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.onboard_welcome.WelcomeScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.profile.ProfileScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screens.DashboardScreen

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Welcome.route
    ){
        // Định nghĩa các màn hình trong ứng dụng
        // Màn hình chính (Home)
        composable(Screens.Home.route) {
            HomeScreen(navController)
        }
        // Màn hình đăng nhập
        composable(Screens.Login.route) {
            LoginScreen(navController)
        }
        // Màn hình Welcome
        composable(Screens.Welcome.route) {
            WelcomeScreen(navController)
        }
        // Màn hình cài đặt
        composable(Screens.Settings.route) {
            //SettingsScreen(navController)
        }
        // Màn hình thông tin cá nhân
        composable(Screens.Profile.route) {
            ProfileScreen()
        }
        // Màn hình Dashboard
        composable(Screens.Dashboard.route) {
            DashboardScreen(navController)
        }
        // Màn hình Thiết bị
        composable(Screens.Devices.route) {
            DeviceScreen()
        }
    }
}