package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.DefaultScreen.DefaultScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail.DeviceDetailScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail_for_fire_alarm.FireAlarmDetailScreen

object DeviceScreenFactory {
    private val screenMap: Map<Int, @Composable (NavHostController, Int?) -> Unit> = mapOf(
        1 to { navController, id -> DeviceDetailScreen(navController,id) },
        2 to { navController, id -> FireAlarmDetailScreen(navController, id) },
    )

    fun getScreen(typeID: Int): @Composable (NavHostController, Int?) -> Unit {
        return screenMap[typeID] ?: { navController, _ -> DefaultScreen(navController) }
    }
}
