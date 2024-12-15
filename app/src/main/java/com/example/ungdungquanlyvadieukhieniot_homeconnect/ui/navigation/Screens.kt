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
    object UpdateProfile: Screens("update_profile")
    object UpdateDevice: Screens("update_device")
    object AddDevice: Screens("add_device")
    object Device: Screens("device")
    object Setting: Screens("setting")

}