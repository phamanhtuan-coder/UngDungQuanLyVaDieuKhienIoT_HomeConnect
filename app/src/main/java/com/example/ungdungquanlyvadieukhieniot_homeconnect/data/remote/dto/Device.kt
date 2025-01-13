package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class Device(
    val DeviceID: Int,
    val TypeID: Int,
    val SpaceID: Int,
    val UserID: Int,
    val Name: String,
    val PowerStatus: Boolean,
    val Attribute: String,
    val WifiSSID: String,
    val WifiPassword: String,
    val IsDeleted: Boolean,
    val CreatedAt: String,
    val UpdatedAt: String,
    val DeviceType: DeviceType,
    val Space: Space
)