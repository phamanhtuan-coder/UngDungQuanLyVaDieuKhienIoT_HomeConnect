package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.DeviceScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.home.HomeScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.login.LoginScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.onboard_welcome.WelcomeScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.password_recovery.PasswordRecoveryScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.profile.ProfileScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.signup.SignUpScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screens.DashboardScreen

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
        NavHost(
            navController = navController,
            startDestination = Screens.Welcome.route
        ){
            // Welcome screen
            composable(Screens.Welcome.route) {
                WelcomeScreen(navController)
            }

            // Login screens
            composable(Screens.Login.route) {
                LoginScreen(navController)
            }

            // Recover password Screen
            composable(Screens.RecoverPassword.route){
                PasswordRecoveryScreen(navController)
            }

            // Signup Screen
            composable(Screens.Register.route) {
                SignUpScreen(navController)
            }

            // HomeScreen
            navigation(startDestination = Screens.Home.route, route = "home_graph") {
                composable(Screens.Home.route) {
                    HomeScreen(navController)
                }
            }

            //Dashboard Screen
            navigation(startDestination = Screens.Dashboard.route, route = "dashboard_graph") {
                composable(Screens.Dashboard.route) {
                    DashboardScreen(navController)
                }
            }

            // Todo:... other nested graphs (devices, profile, settings) ...
        }
    }