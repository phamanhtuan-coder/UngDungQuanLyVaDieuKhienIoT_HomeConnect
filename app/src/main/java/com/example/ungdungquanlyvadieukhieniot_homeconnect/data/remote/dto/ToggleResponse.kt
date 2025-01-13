package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class ToggleResponse(
    val message: String,
    var device: DeviceResponse,
    val error: String
)
