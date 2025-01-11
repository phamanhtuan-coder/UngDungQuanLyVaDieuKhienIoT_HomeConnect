package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository

import android.util.Log
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
}