package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class SharedWithResponse(
    val PermissionID: Int,
    val DeviceID: Int,
    val SharedWithUserID: Int,
    val CreatedAt: String,
    val Device: DeviceShare
)

data class DeviceShare(
    val DeviceID: Int,
    val Name: String,
    val TypeID: Int
)

