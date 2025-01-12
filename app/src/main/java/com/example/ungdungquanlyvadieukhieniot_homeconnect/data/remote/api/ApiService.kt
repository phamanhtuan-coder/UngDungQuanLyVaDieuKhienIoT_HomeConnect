package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api

import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.EmailRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.EmailResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LoginRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LoginResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.NewPasswordRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.NewPasswordResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SpaceResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/api/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("/api/spaces/{spaceId}/devices")
    suspend fun getDevices(@Path("spaceId") spaceId: Int, @Header("Authorization") token: String): List<DeviceResponse>

    @GET("/api/spaces/{homeId}")
    suspend fun getSpacesByHomeId(@Path("homeId") homeId: Int, @Header("Authorization") token: String): List<SpaceResponse>

    @POST("/api/otp/check-email")
    suspend fun checkEmail(@Body request: EmailRequest): EmailResponse

    @POST("/api/otp/send")
    suspend fun sendOTP(@Body request: EmailRequest): EmailResponse

    @POST("/api/otp/verify")
    suspend fun verifyOTP(@Body request: EmailRequest): EmailResponse

    @POST("/api/users/reset-password")
    suspend fun newPassword(@Body request: NewPasswordRequest): NewPasswordResponse

}