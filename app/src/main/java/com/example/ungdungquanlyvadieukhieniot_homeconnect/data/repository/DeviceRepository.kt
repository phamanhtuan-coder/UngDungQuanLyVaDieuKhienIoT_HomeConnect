package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository

import android.content.Context
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api.RetrofitClient
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceResponse

class DeviceRepository (private val context: Context) {
    private val apiService = RetrofitClient.apiService

    suspend fun getInfoDevice(deviceId: Int): DeviceResponse {
        val sharedPrefs =context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token=  sharedPrefs.getString("JWT_TOKEN", "") ?: ""
        return apiService.getInfoDevice(deviceId, token = "Bearer $token")
    }
}