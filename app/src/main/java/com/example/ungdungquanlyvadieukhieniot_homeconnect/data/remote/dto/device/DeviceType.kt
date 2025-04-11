package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.device

data class DeviceType(
    val typeId: Int,
    val typeName: String,
    val attributes: String,
    val rules: String,
    val isDeleted: Boolean,
    val createdAt: String,
    val updatedAt: String
)
