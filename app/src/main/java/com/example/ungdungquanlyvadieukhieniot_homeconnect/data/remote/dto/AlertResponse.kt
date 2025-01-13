package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class AlertResponse(
    val alertID: Int,
    val deviceID: Int,
    val spaceID: Int,
    val typeID: Int,
    val message: String,
    val timestamp: String,
    val status: Boolean,
    val alertTypeID: Int,
    val device: Device
)