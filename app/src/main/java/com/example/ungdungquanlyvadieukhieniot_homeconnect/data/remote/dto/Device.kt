package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class Device(
    val deviceID: Int,
    val typeID: Int,
    val spaceID: Int,
    val userID: Int,
    val name: String,
    val powerStatus: Boolean,
    val attribute: String,
    val wifiSSID: String,
    val wifiPassword: String,
    val isDeleted: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val deviceType: DeviceType,
    val space: Space
)