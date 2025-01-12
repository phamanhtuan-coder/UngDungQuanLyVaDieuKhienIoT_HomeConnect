package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository

import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api.RetrofitClient
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LoginRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LoginResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.RegisterRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.RegisterResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.User

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

    suspend fun register(user: RegisterRequest): RegisterResponse {
        // Tạo request
        val request = RegisterRequest(
            Name = user.Name,
            Email = user.Email,
            PasswordHash = user.PasswordHash,
            Phone = user.Phone,
            Address = user.Address,
            DateOfBirth = user.DateOfBirth,
            ProfileImage = user.ProfileImage,
        )
        // Gọi API
        return apiService.register(request)
    }
}