package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.access_point_connection.AccessPointConnectionScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.activity_detail.ActivityHistoryScreenDetailScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.activity_history.ActivityHistoryScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.add_device.AddDeviceScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.add_space.AddSpaceScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.all_notifications.Notification
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.all_notifications.NotificationScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.DeviceScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail.DeviceDetailScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.home.HomeScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.house_management.HouseManagementScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.login.LoginScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.notification_detail.DetailNotification
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.onboard_welcome.WelcomeScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.otp.OtpScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.password_authentication.PasswordAuthenticationScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.password_recovery.PasswordRecoveryScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.profile.ProfileScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.settings.SettingsScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.signup.SignUpScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.space.SpaceScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.wifi_connection.WifiConnectionScreen
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
            composable(Screens.RecoverPassword.route) {
                PasswordRecoveryScreen(navController)
            }

            // Signup Screen
            composable(Screens.Register.route) {
                SignUpScreen(navController)
            }

            // OTP Screen
            composable(Screens.OTP.route) {
                OtpScreen(navController)
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

            //Profile Screen
            navigation(startDestination = Screens.Profile.route, route = "profile_graph") {
                composable(Screens.Profile.route) {
                    ProfileScreen(navController)
                }
            }

            //Devices Screen
            navigation(startDestination = Screens.Devices.route, route = "devices_graph") {
                composable(Screens.Devices.route) {
                    DeviceScreen(navController)
                }
            }

            //Settings Screen
            navigation(startDestination = Screens.Settings.route, route = "settings_graph") {
                composable(Screens.Settings.route) {
                    SettingsScreen(navController)
                }
            }

            //Device Detail Screen
            //Todo: Tạm thời dẫn tới placeholder, sau này cần truyền Id của thiết bị vào để hiển thị thông tin chi tiết của thiết bị
            composable(Screens.DeviceDetail.route) {
                DeviceDetailScreen(navController)
            }

            //Access Point Screen
            //Todo: Lấy dữ lieu thiết bị để hiển thị thông tin kết nối
            composable(Screens.AccessPoint.route) {
                AccessPointConnectionScreen(navController)
            }

            //Add Device Screen
            composable(Screens.AddDevice.route) {
                AddDeviceScreen(navController)
            }

            //All Notifications Screen
            //Todo: Lấy dữ liệu thông báo từ server
            composable(Screens.AllNotifications.route) {
                NotificationScreen(
                    navController, listOf(
                        Notification("New Feature Alert!", "We've introduced new features.", false),
                        Notification("System Update", "Your app has been updated.", true),
                        Notification("Reminder", "Your meeting is scheduled at 3 PM.", false)
                    )
                )
            }

            //House Management Screen
            composable(Screens.HouseManagement.route) {
                HouseManagementScreen(navController)
            }

            //Activity History
            //Todo: Lấy dữ liêu id để hiển thị thông tin lịch sử
            composable(Screens.ActivityHistory.route) {
                ActivityHistoryScreen(navController)
            }

            //Notification Detail Screen
            //Todo: Lấy id notification để hiển thị thông tin chi tiết
            composable(Screens.NotificationDetail.route) {
                DetailNotification(navController)
            }

            //Todo: Lấy dữ liêu id để hiển thị chi tiết thông tin lịch sử
            composable(Screens.ActivityHistoryDetail.route) {
                ActivityHistoryScreenDetailScreen(navController)
            }

            //Todo: Lấy dữ liêu id để vào kết nối wifi
            composable(Screens.WifiConnection.route) {
                WifiConnectionScreen(navController)
            }

            //Spaces Screen
            composable(Screens.Spaces.route) {
                SpaceScreen(navController)
            }

            //Add Space Screen
            composable(Screens.AddSpace.route) {
                AddSpaceScreen(navController)
            }

            //Password Authentication Screen
            composable(Screens.PasswordAuth.route) {
                PasswordAuthenticationScreen(navController)
            }


            // Todo:... other nested graphs (devices, profile, settings) ...
        }
    }