package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository

import android.content.Context
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api.RetrofitClient
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceTokenRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceTokenResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.NewPasswordRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.NewPasswordResponse

class UserRepository(private val context: Context) {
    private val apiService = RetrofitClient.apiService

    suspend fun newPassword(email: String, password: String): NewPasswordResponse {
        // Tạo request
        val request = NewPasswordRequest(
            email = email,
            newPassword = password
        )
        // Gọi API
        return apiService.newPassword(request)
    }

    suspend fun sendToken(deviceToken: String): DeviceTokenResponse {
        // Tạo request chứa Device Token cho Firebase
        val request = DeviceTokenRequest(
            deviceToken = deviceToken
        )
        //Lấy token JWT từ SharedPreferences
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""

        // Gọi API
        return apiService.sendToken(token = "Bearer $token", request)
    }
}