package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository

import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api.RetrofitClient
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.EmailRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.EmailResponse


class OTPRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun checkEmail(email: String): EmailResponse {
        // Tạo request
        val request = EmailRequest(
            email = email
        )
        // Gọi API
        return apiService.checkEmail(request)
    }

    suspend fun sendOTP(email: String): EmailResponse {
        // Tạo request
        val request = EmailRequest(
            email = email
        )
        // Gọi API
        return apiService.sendOTP(request)
    }

    suspend fun verifyOTP(email: String, otp: String): EmailResponse {
        // Tạo request
        val request = EmailRequest(
            email = email,
            otp = otp
        )
        // Gọi API
        return apiService.verifyOTP(request)
    }
}