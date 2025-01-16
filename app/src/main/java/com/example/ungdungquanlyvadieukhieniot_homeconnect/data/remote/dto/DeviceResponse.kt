package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class DeviceResponse(
    val DeviceID: Int,
    val TypeID: Int,
    val SpaceID: Int,
    val Name: String,
    var PowerStatus: Boolean,
    val Attribute: String
)