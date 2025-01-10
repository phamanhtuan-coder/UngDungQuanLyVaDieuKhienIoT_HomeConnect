package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api

import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LoginRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/api/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}