package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository

import android.content.Context
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api.RetrofitClient
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AttributeRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AttributeResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ToggleRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ToggleResponse

class DeviceRepository (private val context: Context) {
    private val apiService = RetrofitClient.apiService

    suspend fun getInfoDevice(deviceId: Int): DeviceResponse {
        val sharedPrefs =context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token=  sharedPrefs.getString("JWT_TOKEN", "") ?: ""
        return apiService.getInfoDevice(deviceId, token = "Bearer $token")
    }

    suspend fun postToggleDevice(deviceId: Int, toggleRequest: ToggleRequest): ToggleResponse {
        val sharedPrefs =context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token=  sharedPrefs.getString("JWT_TOKEN", "") ?: ""
        return apiService.toggleDevice(deviceId, toggleRequest, token = "Bearer $token")
    }

    suspend fun postAttributeDevice(deviceId: Int, attributeRequest: AttributeRequest): AttributeResponse {
        val sharedPrefs =context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token=  sharedPrefs.getString("JWT_TOKEN", "") ?: ""
        return apiService.postAttributes(deviceId, attributeRequest, token = "Bearer $token")
    }
}