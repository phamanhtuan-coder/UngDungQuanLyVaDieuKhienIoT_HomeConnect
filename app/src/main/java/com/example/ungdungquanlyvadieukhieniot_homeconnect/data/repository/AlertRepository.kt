package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository

import android.content.Context
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api.RetrofitClient
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AlertResponse


class AlertRepository(private val context: Context) {
    private val apiService = RetrofitClient.apiService

    suspend fun getAllByUser(): List<AlertResponse> {

        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""

        // Gọi API
        return apiService.getAllNotification(token = token)
    }
}