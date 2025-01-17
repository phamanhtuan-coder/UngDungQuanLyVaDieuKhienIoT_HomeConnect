package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository

import android.content.Context
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api.RetrofitClient
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ChangePasswordRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ChangePasswordResponce
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceTokenRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceTokenResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.NewPasswordRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.NewPasswordResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SharedWithResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.UserRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.UserResponse

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

    suspend fun putInfoProfile(userId: Int, user: UserRequest): UserResponse {
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""

        return apiService.putInfoProfile(userId, user, token = "Bearer $token")
    }

    suspend fun putChangePassword(userId: Int, changePasswordRequest: ChangePasswordRequest): ChangePasswordResponce {
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""

        return apiService.putChangePassword(userId, changePasswordRequest, token = "Bearer $token")
    }

    suspend fun getSharedWith(userId: Int): List<SharedWithResponse> {
        val sharedPrefs = context.getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)
        val token = sharedPrefs.getString("JWT_TOKEN", "") ?: ""

        return apiService.sharedWith(userId, token = "Bearer $token")
    }
}