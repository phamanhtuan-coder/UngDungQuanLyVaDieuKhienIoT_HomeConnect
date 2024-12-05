package com.example.ungdungquanlyvadieukhieniot_homeconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.editprofile.EditProfileScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.login.LoginScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.newpass.NewPasswordScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.signup.SignupScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.UngDungQuanLyVaDieuKhienIoT_HomeConnectTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UngDungQuanLyVaDieuKhienIoT_HomeConnectTheme {
                //NewPasswordScreen()
                //SignupScreen()
               //LoginScreen()
               // HomeScreen()
                EditProfileScreen()

            }
        }
    }
}