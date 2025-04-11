package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.shared

data class AddSharedUserRequest(
    val deviceId: Int,
    val email: String,
    val permissionId: Int
)

