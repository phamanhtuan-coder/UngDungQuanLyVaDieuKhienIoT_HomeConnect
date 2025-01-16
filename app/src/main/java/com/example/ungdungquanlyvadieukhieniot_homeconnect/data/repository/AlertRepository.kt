package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository

import android.content.Context
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api.RetrofitClient
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.Alert
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AlertDetail
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AlertResponse


class AlertRepository(private val context: Context) {
    private val apiService = RetrofitClient.apiService

    suspend fun getAllByUser(): List<AlertResponse> {

        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""

        // Gọi API
        return apiService.getAllNotification(token = "Bearer $token")
    }

    suspend fun getAlertById(alertId: Int): AlertDetail {
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""

        // Gọi API
        return apiService.getAlertById(alertId = alertId, token = "Bearer $token")
    }

    suspend fun readNotification(alertId: Int): Alert {
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""
        // Gọi API
        return apiService.readNotification(alertId = alertId, token = "Bearer $token")
    }

}