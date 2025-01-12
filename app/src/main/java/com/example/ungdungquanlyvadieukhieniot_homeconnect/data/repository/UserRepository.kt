package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository

import android.content.Context
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api.RetrofitClient
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
}