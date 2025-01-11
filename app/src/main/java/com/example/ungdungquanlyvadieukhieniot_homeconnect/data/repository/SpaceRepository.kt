package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository

import android.app.Application
import android.content.Context
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api.RetrofitClient
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SpaceResponse

class SpaceRepository(private val context: Context) {
    private val apiService = RetrofitClient.apiService

    suspend fun getSpacesByHomeId(homeId: Int): List<SpaceResponse> {
        val sharedPrefs =context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token=  sharedPrefs.getString("JWT_TOKEN", "") ?: ""
        return RetrofitClient.apiService.getSpacesByHomeId(homeId, token = "Bearer $token")
    }

    suspend fun getDevices(spaceId: Int): List<DeviceResponse> {
        val sharedPrefs =context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token=  sharedPrefs.getString("JWT_TOKEN", "") ?: ""
        return apiService.getDevices(spaceId, token = "Bearer $token")
    }
}