package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation

import DashboardDeviceScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.SharedViewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.access_point_connection.AccessPointConnectionScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.activity_detail.ActivityHistoryScreenDetailScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.activity_history.ActivityHistoryScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.add_device.AddDeviceScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.add_space.AddSpaceScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.all_notifications.NotificationScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.DeviceScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_sharing_list.DeviceSharingListScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.home.HomeScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.house_management.HouseManagementScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.login.LoginScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.new_password.NewPasswordScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.notification_detail.DetailNotification
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.onboard_welcome.WelcomeScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.otp.OtpScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.password_authentication.PasswordAuthenticationScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.password_recovery.PasswordRecoveryScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.profile.ProfileScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.settings.SettingsScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.share_device.ShareDeviceScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.signup.SignUpScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.space.SpaceScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.update_password.UpdatePasswordScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.wifi_connection.WifiConnectionScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screens.DashboardScreen

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    val sharedViewModel: SharedViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Screens.Welcome.route
    ) {
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
        composable(
            route = Screens.OTP.route,
            arguments = listOf(
                navArgument("type") {
                    type = NavType.StringType
                },
                navArgument("email") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: "reset_password"
            val email = backStackEntry.arguments?.getString("email") ?: ""

            val onVerificationSuccess = {
                when (type) {
                    "reset_password" -> {
                        navController.navigate("${Screens.NewPassword.route}?email=$email")
                    }

                    "email_verification" -> {
                        navController.navigate(Screens.Profile.route)
                    }
//                    "transaction" -> {
//                        navController.navigate(Screens.TransactionSuccess.route)
//                    }
                }
            }

            OtpScreen(
                navController = navController,
                email = email,
                title = when (type) {
                    "reset_password" -> "Nhập mã OTP"
                    "email_verification" -> "Xác thực Email"
                    "transaction" -> "Xác thực giao dịch"
                    else -> "Nhập mã OTP"
                },
                description = when (type) {
                    "reset_password" -> "Vui lòng nhập mã OTP vừa được gửi tới Email"
                    "email_verification" -> "Nhập mã xác thực được gửi tới email của bạn"
                    "transaction" -> "Nhập mã OTP để xác nhận giao dịch"
                    else -> "Vui lòng nhập mã OTP vừa được gửi tới Email"
                },
                onVerificationSuccess = onVerificationSuccess
            )
        }
        // HomeScreen
        navigation(startDestination = Screens.Home.route, route = "home_graph") {
            composable(Screens.Home.route) {
                HomeScreen(navController = navController, sharedViewModel = sharedViewModel)
            }
        }

        //Dashboard Screen
        navigation(startDestination = Screens.Dashboard.route, route = "dashboard_graph") {
            composable(Screens.Dashboard.route) {
                DashboardScreen(navController, sharedViewModel)
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
                DeviceScreen(navController = navController, sharedViewModel = sharedViewModel)
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
//            composable(Screens.DeviceDetail.route) {
//                DeviceDetailScreen(navController)
//            }

        //Access Point Screen
        composable(
            route = "${Screens.AccessPoint.route}?id={id}&name={name}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            val name = backStackEntry.arguments?.getString("name") ?: ""
            AccessPointConnectionScreen(navController, id, name)
        }


        //Add Device Screen
        composable(Screens.AddDevice.route) {
            AddDeviceScreen(navController, sharedViewModel)
        }

        //All Notifications Screen
        //Todo: Lấy dữ liệu thông báo từ server
        composable(Screens.AllNotifications.route) {
            NotificationScreen(
                navController
            )
        }

        //House Management Screen
        composable(Screens.HouseManagement.route) {
            HouseManagementScreen(navController)
        }


        //Notification Detail Screen
        //Todo: Lấy id notification để hiển thị thông tin chi tiết
        composable(
            route = Screens.NotificationDetail.route + "?id={id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            DetailNotification(navController, id)
        }



        //Todo: Lấy dữ liêu id để vào kết nối wifi
        composable(Screens.WifiConnection.route) {
            WifiConnectionScreen(navController)
        }

        //Spaces Screen
        composable(Screens.Spaces.route) {
            SpaceScreen(navController, sharedViewModel)
        }

        //Add Space Screen
        composable(Screens.AddSpace.route) {
            AddSpaceScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }


        composable(
            route = "${Screens.NewPassword.route}?email={email}",
            arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            NewPasswordScreen(navController, email)
        }
        //Password Authentication Screen
        composable(Screens.PasswordAuth.route) {
            PasswordAuthenticationScreen(navController)
        }

        composable(
            route = "${Screens.UpdatePassword.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("id") ?: -1
            UpdatePasswordScreen(navController, userId)
        }

//            composable(Screens.FireAlarmDetail.route) {
//                FireAlarmDetailScreen(navController)
//            }

        composable(
            route = "${Screens.DashboardDeviceScreen.route}/{spaceID}/{id}",
            arguments = listOf(
                navArgument("spaceID") { type = NavType.IntType }, // Thêm spaceID
                navArgument("id") { type = NavType.IntType }       // Tham số id
            )
        ) { backStackEntry ->
            // Lấy giá trị từ arguments
            val spaceID = backStackEntry.arguments?.getInt("spaceID") ?: -1
            val id = backStackEntry.arguments?.getInt("id") ?: -1

            // Truyền spaceID và id vào DashboardDeviceScreen
            DashboardDeviceScreen(navController = navController, spaceId = spaceID, id = id)
        }


        composable("device/{typeID}/{id}") { backStackEntry ->
            val typeID = backStackEntry.arguments?.getString("typeID")?.toIntOrNull()
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()

                // Sử dụng Factory để ánh xạ typeID tới màn hình
                val screen = DeviceScreenFactory.getScreen(typeID ?: 0)
                screen(navController, id)
            }
        composable(
            route = Screens.ActivityHistory.route,
            arguments = listOf(
                navArgument("deviceId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val deviceId = backStackEntry.arguments?.getInt("deviceId") ?: -1
            ActivityHistoryScreen(
                navController = navController,
                deviceId = deviceId
            )
        }

        composable(
            route = Screens.ActivityHistoryDetail.route,
            arguments = listOf(
                navArgument("logDetails") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val logDetailsJson = backStackEntry.arguments?.getString("logDetails") ?: ""
            ActivityHistoryScreenDetailScreen(
                navController = navController,
                logDetailsJson = logDetailsJson
            )
        }




        composable(
            route = Screens.AddSharedUser.route + "?id={id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: -1
            ShareDeviceScreen(navController, id)
        }

        composable(
            route = Screens.SharedUsers.route + "?id={id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: -1
            DeviceSharingListScreen(navController, id)
        }
        // Todo:... other nested graphs (devices, profile, settings) ...
    }
}