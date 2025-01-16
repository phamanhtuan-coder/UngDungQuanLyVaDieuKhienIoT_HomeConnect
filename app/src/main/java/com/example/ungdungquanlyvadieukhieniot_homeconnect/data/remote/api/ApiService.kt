package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api

import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.Alert
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AlertDetail
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AlertResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AttributeRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AttributeResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AverageSensorResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ChangePasswordRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ChangePasswordResponce
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DailyAverageSensorResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DailyPowerUsageResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DailySensorRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceTokenRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceTokenResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.EmailRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.EmailResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.HouseResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LogLastest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LoginRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LoginResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.NewPasswordRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.NewPasswordResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.RangeSensorRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.RegisterRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.RegisterResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SharedUser
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SharedUserRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SpaceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ToggleRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ToggleResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.UnlinkResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.User
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.UserRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.UserResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.WeeklyAverageSensorResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.WeeklySensorRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

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


    @GET("/api/alerts/{alertId}")
    suspend fun getAlertById(
        @Path("alertId") alertId: Int,
        @Header("Authorization") token: String
    ): AlertDetail

    @PUT("/api/alerts/{alertId}/resolve")
    suspend fun readNotification(
        @Path("alertId") alertId: Int,
        @Header("Authorization") token: String
    ): Alert


    @GET("/api/alerts/search")
    suspend fun searchAlerts(
        @Query("q") query: String,
        @Header("Authorization") token: String
    ): List<AlertResponse>

    @GET("/api/logs/latestToggle/{deviceId}")
    suspend fun getLatestToggle(
        @Path("deviceId") deviceId: Int,
        @Header("Authorization") token: String
    ): LogLastest

    @GET("/api/logs/latestUpdateAttributes/{deviceId}")
    suspend fun getLatestUpdateAttributes(
        @Path("deviceId") deviceId: Int,
        @Header("Authorization") token: String
    ): LogLastest

    @GET("/api/logs/latestSensor/{deviceId}")
    suspend fun getLatestSensor(
        @Path("deviceId") deviceId: Int,
        @Header("Authorization") token: String
    ): LogLastest




    @GET("/api/auth/me")
    suspend fun getInfoProfile(@Header("Authorization") token: String) : User

    @PUT("/api/users/{userId}")
    suspend fun putInfoProfile(@Path("userId") userId: Int, @Body user: UserRequest, @Header("Authorization") token: String) : UserResponse

    @PUT("/api/users/{userId}/change-password")
    suspend fun putChangePassword(@Path("userId") userId: Int, @Body changePasswordRequest: ChangePasswordRequest, @Header("Authorization") token: String) : ChangePasswordResponce

    // Lấy thống kê trung bình hàng ngày trong khoảng thời gian
    @GET("/api/statistics/daily-averages-sensor/{deviceId}/{startDate}/{endDate}")
    suspend fun getDailyAveragesSensor(
        @Path("deviceId") deviceId: Int,
        @Path("startDate") startDate: String,
        @Path("endDate") endDate: String,
        @Header("Authorization") token: String
    ): DailyAverageSensorResponse

    // Lấy tiêu thụ điện hàng ngày trong khoảng thời gian
    @GET("/api/statistics/daily-power-usages/{deviceId}/{startDate}/{endDate}")
    suspend fun getDailyPowerUsages(
        @Path("deviceId") deviceId: Int,
        @Path("startDate") startDate: String,
        @Path("endDate") endDate: String,
        @Header("Authorization") token: String
    ): DailyPowerUsageResponse

    // Tính trung bình sensor hàng ngày
    @POST("/api/statistics/calculate-daily-average-sensor")
    suspend fun calculateDailyAverageSensor(
        @Body request: DailySensorRequest,
        @Header("Authorization") token: String
    ): AverageSensorResponse

    // Tính trung bình sensor hàng tuần
    @POST("/api/statistics/calculate-weekly-average-sensor")
    suspend fun calculateWeeklyAverageSensor(
        @Body request: WeeklySensorRequest,
        @Header("Authorization") token: String
    ): AverageSensorResponse

    // Tính trung bình sensor theo khoảng thời gian
    @POST("/api/statistics/calculate-average-sensor-for-range")
    suspend fun calculateAverageSensorForRange(
        @Body request: RangeSensorRequest,
        @Header("Authorization") token: String
    ): AverageSensorResponse

    // Lấy thống kê sensor hàng tuần gần nhất
    @GET("/api/statistics/weekly-average-sensor/{deviceId}")
    suspend fun getWeeklyAverageSensor(
        @Path("deviceId") deviceId: Int,
        @Header("Authorization") token: String
    ): WeeklyAverageSensorResponse

    @DELETE("/api/sharedpermissions/revoke/{permissionId}")
    suspend fun revokePermission(
        @Path("permissionId") permissionId: Int, // Sử dụng Int thay vì RevokeShare
        @Header("Authorization") token: String
    ): Response<Unit>

    @GET("/api/sharedpermissions/{deviceId}/shared-users")
    suspend fun getSharedUsers(
        @Path("deviceId") deviceId: Int,
        @Header("Authorization") token: String
    ): List<SharedUser>

    @POST("/api/sharedpermissions/{deviceId}/share")
    suspend fun shareDevice(
        @Path("deviceId") deviceId: Int,
        @Body sharedWithUserEmail: SharedUserRequest,
        @Header("Authorization") token: String
    ): Response<Unit>


}