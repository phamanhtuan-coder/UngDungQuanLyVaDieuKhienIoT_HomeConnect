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
            // Welcome screen as the initial entry point
            composable(Screens.Welcome.route) {
                WelcomeScreen(navController)
            }

            // Login and Signup screens
            composable(Screens.Login.route) {
                LoginScreen(navController)
            }

            composable(Screens.Register.route) {
                SignUpScreen(navController)
            }

            navigation(startDestination = Screens.Home.route, route = "home_graph") {
                composable(Screens.Home.route) {
                    HomeScreen(navController)
                }
            }

            navigation(startDestination = Screens.Dashboard.route, route = "dashboard_graph") {
                composable(Screens.Dashboard.route) {
                    DashboardScreen(navController)
                }
            }

            // ... other nested graphs (devices, profile, settings) ...
        }
    }