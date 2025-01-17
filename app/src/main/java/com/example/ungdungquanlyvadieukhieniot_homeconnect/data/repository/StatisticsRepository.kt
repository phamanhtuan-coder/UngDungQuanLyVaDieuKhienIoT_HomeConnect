package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository

import android.content.Context
import android.util.Log
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api.RetrofitClient
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DailyAverageSensorResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DailyPowerUsageResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DailyPowerUsageResponse2
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DailySensorAveragesResponse

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

    suspend fun getDailyRoomPowerUsage(spaceId: Int, startDate: String, endDate: String): DailyPowerUsageResponse2 {
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: throw Exception("Token không tồn tại")

        Log.d("StatisticsRepository", "Token: Bearer $token")
        Log.d("StatisticsRepository", "Request: spaceId=$spaceId, startDate=$startDate, endDate=$endDate")

        return apiService.getDailyRoomPowerUsage(spaceId, startDate, endDate, "Bearer $token")
    }

    suspend fun getDailyRoomAveragesSensor(spaceId: Int, startDate: String, endDate: String): DailySensorAveragesResponse {
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: throw Exception("Token không tồn tại")

        Log.d("StatisticsRepository", "Token: Bearer $token")
        Log.d("StatisticsRepository", "Request: spaceId=$spaceId, startDate=$startDate, endDate=$endDate")

        return apiService.getDailyRoomAveragesSensor(spaceId, startDate, endDate, "Bearer $token")
    }
}
