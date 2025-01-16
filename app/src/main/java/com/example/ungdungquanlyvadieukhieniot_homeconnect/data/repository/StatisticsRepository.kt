package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository

import android.content.Context
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api.RetrofitClient
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AverageSensorResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DailyAverageSensorResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DailyPowerUsageResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DailySensorRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.RangeSensorRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.WeeklyAverageSensorResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.WeeklySensorRequest

class StatisticsRepository(private val context: Context) {
    private val apiService = RetrofitClient.apiService

    suspend fun getDailyAveragesSensor(deviceId: Int, startDate: String, endDate: String): DailyAverageSensorResponse {
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""
        return apiService.getDailyAveragesSensor(deviceId, startDate, endDate, "Bearer $token")
    }

    suspend fun getDailyPowerUsages(deviceId: Int, startDate: String, endDate: String): DailyPowerUsageResponse {
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""
        return apiService.getDailyPowerUsages(deviceId, startDate, endDate, "Bearer $token")
    }

    private fun getToken(): String {
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        return sharedPrefs.getString("JWT_TOKEN", "") ?: ""
    }

    suspend fun calculateDailyAverageSensor(request: DailySensorRequest): AverageSensorResponse {
        return apiService.calculateDailyAverageSensor(request, "Bearer ${getToken()}")
    }

    suspend fun calculateWeeklyAverageSensor(request: WeeklySensorRequest): AverageSensorResponse {
        return apiService.calculateWeeklyAverageSensor(request, "Bearer ${getToken()}")
    }

    suspend fun calculateAverageSensorForRange(request: RangeSensorRequest): AverageSensorResponse {
        return apiService.calculateAverageSensorForRange(request, "Bearer ${getToken()}")
    }

    suspend fun getWeeklyAverageSensor(deviceId: Int): WeeklyAverageSensorResponse {
        return apiService.getWeeklyAverageSensor(deviceId, "Bearer ${getToken()}")
    }

}
