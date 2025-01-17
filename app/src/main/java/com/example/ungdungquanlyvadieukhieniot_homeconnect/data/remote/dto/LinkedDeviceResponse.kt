package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class LinkedDeviceResponse(
    val message: String,
    val device: LinkedDevice
)

data class LinkedDevice(
    val DeviceID: Int,
    val TypeID: Int,
    val SpaceID: String, // Chuyển SpaceID thành String
    val UserID: Int,
    val Name: String,
    val PowerStatus: Boolean,
    val Attribute: String,
    val WifiSSID: String,
    val WifiPassword: String,
    val IsDeleted: Boolean,
    val createdAt: String,
    val updatedAt: String
)
