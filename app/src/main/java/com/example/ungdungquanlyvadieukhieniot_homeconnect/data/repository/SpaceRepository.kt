package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api.ApiService.UpdateSpaceRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api.RetrofitClient
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.CreateSpaceRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.CreateSpaceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SpaceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SpaceResponse2

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

    suspend fun getSpaces(houseId: Int): List<SpaceResponse2> {
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""
        return apiService.getSpaces(houseId, "Bearer $token")
    }

    suspend fun updateSpace(spaceId: Int, name: String): SpaceResponse3 {
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""

        // Log để kiểm tra dữ liệu trước khi gọi API
        Log.d("UpdateSpaceRepository", "Token: Bearer $token")
        Log.d("UpdateSpaceRepository", "Payload: SpaceID = $spaceId, Name = $name")

        return apiService.updateSpace(
            spaceId = spaceId,
            body = UpdateSpaceRequest(Name = name),
            token = "Bearer $token"
        )
    }

    suspend fun createSpace(houseId: Int, name: String): CreateSpaceResponse {
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""

        Log.d("CreateSpaceRepository", "Token: Bearer $token")
        Log.d("CreateSpaceRepository", "Payload: HouseID = $houseId, Name = $name")

        return apiService.createSpace(
            body = CreateSpaceRequest(HouseID = houseId, Name = name),
            token = "Bearer $token"
        ).also {
            Log.d("CreateSpaceRepository", "API Response: ${it.message}, Space: ${it.space}")
        }
    }
}