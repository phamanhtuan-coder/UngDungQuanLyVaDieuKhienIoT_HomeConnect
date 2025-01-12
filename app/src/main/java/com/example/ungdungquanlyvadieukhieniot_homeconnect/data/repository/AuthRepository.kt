package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository

import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api.RetrofitClient
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LoginRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LoginResponse

class AuthRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun login(email: String, password: String): LoginResponse {
        // Tạo request
        val request = LoginRequest(
            Email = email,
            PasswordHash = password
        )
        // Gọi API
        return apiService.login(request)
    }


}