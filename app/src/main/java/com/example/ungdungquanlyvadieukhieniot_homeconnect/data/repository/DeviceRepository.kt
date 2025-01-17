package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository

import android.content.Context
import android.util.Log
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api.RetrofitClient
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AttributeRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AttributeResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LinkDeviceRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LinkedDeviceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ToggleRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ToggleResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.UnlinkResponse

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

    suspend fun postAttributeDevice(
        deviceId: Int,
        brightness: Int,
        color: String
    ): AttributeResponse {
        val sharedPrefs =context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token=  sharedPrefs.getString("JWT_TOKEN", "") ?: ""
        val attributeRequest = AttributeRequest(brightness = brightness, color = color)
        return apiService.postAttributes(deviceId, attributeRequest, token = "Bearer $token")
    }

    suspend fun postUnlink(deviceId: Int): UnlinkResponse {
        val sharedPrefs =context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token=  sharedPrefs.getString("JWT_TOKEN", "") ?: ""
        return apiService.postUnlink(deviceId, token = "Bearer $token")
    }

    suspend fun linkDevice(deviceId: String, spaceId: String, deviceName: String): LinkedDeviceResponse {
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""

        Log.d("LinkDeviceRepository", "Token: Bearer $token")
        Log.d("LinkDeviceRepository", "Payload: deviceId = $deviceId, spaceId = $spaceId, deviceName = $deviceName")

        return apiService.linkDevice(
            body = LinkDeviceRequest(deviceId, spaceId, deviceName),
            token = "Bearer $token"
        ).also {
            Log.d("LinkDeviceRepository", "API Response: ${it.message}, Device: ${it.device}")
        }
    }
}