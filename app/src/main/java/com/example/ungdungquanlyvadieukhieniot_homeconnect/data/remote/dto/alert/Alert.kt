package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.alert
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.base.ApiResponse

data class Alert(
    val id: Int,
    val deviceId: Int,
    val spaceId: Int,
    val typeId: Int,
    val message: String,
    val timestamp: String,
    val status: Boolean,
    val alertTypeName: String
)
typealias AlertResponse = ApiResponse<Alert>
typealias AlertListResponse = ApiResponse<List<Alert>>
