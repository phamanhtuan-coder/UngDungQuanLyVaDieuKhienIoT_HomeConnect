package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository

import android.content.Context
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api.RetrofitClient
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.CreateHouseRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.HouseResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.HousesListPesponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SpaceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.UpdateHouseRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.UpdateHouseResponse

class HouseRepository (private val context: Context){
    private val apiService = RetrofitClient.apiService

    suspend fun getListHome(): List<HouseResponse> {
        val sharedPrefs =context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token=  sharedPrefs.getString("JWT_TOKEN", "") ?: ""
        return apiService.getListHome(token = "Bearer $token")
    }

    suspend fun getHouses(): List<HousesListPesponse> {
        // Lấy token từ SharedPreferences
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""

        // Gọi API với token
        return apiService.getHouses(token = "Bearer $token")
    }

    suspend fun updateHouse(houseID: Int, request: UpdateHouseRequest): UpdateHouseResponse {
        // Lấy token từ SharedPreferences
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""

        // Gọi API với token
        return apiService.updateHouse(houseID, request, token = "Bearer $token")
    }

    suspend fun createHouse(request: CreateHouseRequest): CreateHouseResponse {
        // Lấy token từ SharedPreferences
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""

        // Gọi API với token
        return apiService.createHouse(request, "Bearer $token")
    }
}