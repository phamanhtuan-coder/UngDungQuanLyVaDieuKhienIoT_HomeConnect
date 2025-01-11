package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api

import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LoginRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LoginResponse
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
}