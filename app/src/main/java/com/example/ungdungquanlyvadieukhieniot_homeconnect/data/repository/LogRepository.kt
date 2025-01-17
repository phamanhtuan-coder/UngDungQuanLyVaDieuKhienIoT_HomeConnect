package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository

import android.content.Context
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api.RetrofitClient
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LogLastest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LogResponse

class LogRepository(private val context: Context) {
    private val apiService = RetrofitClient.apiService

    suspend fun getLastestToggle(DeviceID: Int): LogLastest {
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""
        return apiService.getLatestToggle(DeviceID, token = "Bearer $token")
    }

    suspend fun getLatestUpdateAttributes(DeviceID: Int): LogLastest {
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""
        return apiService.getLatestUpdateAttributes(DeviceID, token = "Bearer $token")
    }

    suspend fun getLatestSensor(DeviceID: Int): LogLastest {
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""
        return apiService.getLatestSensor(DeviceID, token = "Bearer $token")
    }

    suspend fun getDeviceLogs(deviceId: Int): List<LogResponse> {
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""
        return apiService.getDeviceLogs(deviceId=deviceId,token = "Bearer $token")
    }
}