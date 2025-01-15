package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api

import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AlertResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AttributeRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AttributeResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceTokenRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceTokenResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.EmailRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.EmailResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.HouseResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LoginRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LoginResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.NewPasswordRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.NewPasswordResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.RegisterRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.RegisterResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SpaceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ToggleRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ToggleResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.UnlinkResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.User
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.UserRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("/api/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("/api/spaces/{spaceId}/devices")
    suspend fun getDevices(@Path("spaceId") spaceId: Int, @Header("Authorization") token: String): List<DeviceResponse>

    @GET("/api/spaces/{homeId}")
    suspend fun getSpacesByHomeId(@Path("homeId") homeId: Int, @Header("Authorization") token: String): List<SpaceResponse>

    @POST("/api/auth/register")
    suspend fun register(@Body request: RegisterRequest) : RegisterResponse

    @GET("/api/houses")
    suspend fun  getListHome(@Header("Authorization") token: String): List<HouseResponse>

    @POST("/api/otp/check-email")
    suspend fun checkEmail(@Body request: EmailRequest): EmailResponse

    @POST("/api/otp/send")
    suspend fun sendOTP(@Body request: EmailRequest): EmailResponse

    @POST("/api/otp/verify")
    suspend fun verifyOTP(@Body request: EmailRequest): EmailResponse

    @POST("/api/users/reset-password")
    suspend fun newPassword(@Body request: NewPasswordRequest): NewPasswordResponse

    @POST("/api/auth/update-device-token")
    suspend fun sendToken(
        @Header("Authorization") token: String,
        @Body request: DeviceTokenRequest
    ): DeviceTokenResponse

    @GET("/api/alerts/getAllByUser")
    suspend fun getAllNotification(@Header("Authorization") token: String): List<AlertResponse>
    @GET("/api/devices/{deviceId}")
    suspend fun getInfoDevice(@Path("deviceId") deviceId: Int, @Header("Authorization") token: String) : DeviceResponse

    @POST("/api/devices/{deviceId}/toggle")
    suspend fun toggleDevice(@Path("deviceId") deviceId: Int, @Body toggle: ToggleRequest, @Header("Authorization") token: String) : ToggleResponse

    @POST("/api/devices/{deviceId}/attributes")
    suspend fun postAttributes(@Path("deviceId") deviceId: Int, @Body attribute: AttributeRequest, @Header("Authorization") token: String) : AttributeResponse

    @POST("/api/devices/{deviceId}/unlink")
    suspend fun postUnlink(@Path("deviceId") deviceId: Int, @Header("Authorization") token: String) : UnlinkResponse

    @GET("/api/auth/me")
    suspend fun getInfoProfile(@Header("Authorization") token: String) : User

    @PUT("/api/users/{userId}")
    suspend fun putInfoProfile(@Path("userId") userId: Int, @Body user: UserRequest, @Header("Authorization") token: String) : UserResponse
}