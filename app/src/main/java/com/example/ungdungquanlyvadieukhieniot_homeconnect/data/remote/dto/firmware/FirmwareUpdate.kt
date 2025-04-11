package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.firmware

data class FirmwareUpdate(
    val id: Int,
    val deviceId: Int,
    val version: String,
    val updatedAt: String
)