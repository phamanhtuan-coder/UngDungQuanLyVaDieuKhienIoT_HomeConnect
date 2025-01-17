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
    object OTP : Screens("otp/{type}/{email}") {
        fun createRoute(type: String, email: String) = "otp/$type/$email"
    }
    object PasswordAuth: Screens("password_auth")
    object Profile: Screens("profile")
    object AddDevice: Screens("add_device")
    object Devices: Screens("devices")
    object DeviceDetail : Screens("device_detail")
    object AccessPoint: Screens("access_point")
    object AllNotifications : Screens("all_notifications")
    object NotificationDetail : Screens("notification_detail")
    object HouseManagement : Screens("house_management")
    object ActivityHistory : Screens("activity_history?deviceId={deviceId}") {
        fun createRoute(deviceId: Int): String {
            return "activity_history?deviceId=$deviceId"
        }
    }
    object ActivityHistoryDetail : Screens("activity_history_detail/{logDetails}") {
        fun createRoute(logDetails: String): String {
            return "activity_history_detail/$logDetails"
        }
    }
    object WifiConnection: Screens("wifi_connection")
    object Spaces : Screens("spaces")
    object AddSpace : Screens("add_space")
    object FireAlarmDetail : Screens("fire_alarm_detail")
    object SharedUsers : Screens("shared_users")
    object AddSharedUser : Screens("add_shared_user")

    object DashboardDeviceScreen: Screens("dashboard_device")
    object Settings: Screens("settings")
    object Dashboard: Screens("dashboard")

}