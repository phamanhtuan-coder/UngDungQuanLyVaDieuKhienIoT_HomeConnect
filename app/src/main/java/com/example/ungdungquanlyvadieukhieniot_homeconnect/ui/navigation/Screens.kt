package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation

sealed class Screens(
    val route: String
){
    object Home: Screens("home")
    object Login: Screens("login")
    object Welcome : Screens("welcome")
    object Register: Screens("register")
    object RecoverPassword: Screens("recover_password")
    object UpdatePassword: Screens("update_password")
    object NewPassword: Screens("new_password")
    object OTP: Screens("otp")
    object PasswordAuth: Screens("password_auth")
    object Profile: Screens("profile")
    object AddDevice: Screens("add_device")
    object Devices: Screens("devices")
    object DeviceDetail : Screens("device_detail")
    object AccessPoint: Screens("access_point")
    object AllNotifications : Screens("all_notifications")
    object NotificationDetail : Screens("notification_detail")
    object HouseManagement : Screens("house_management")
    object ActivityHistory : Screens("activity_history")
    object ActivityHistoryDetail : Screens("activity_history_detail")
    object WifiConnection: Screens("wifi_connection")
    object Spaces : Screens("spaces")
    object AddSpace : Screens("add_space")

    object Settings: Screens("settings")
    object Dashboard: Screens("dashboard")

}