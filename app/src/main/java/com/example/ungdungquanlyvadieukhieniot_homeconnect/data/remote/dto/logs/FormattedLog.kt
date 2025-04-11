package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.logs

data class FormattedLog(
    val id: Int,
    val deviceName: String,
    val deviceType: Int,
    val timestamp: String,
    val details: Any
)
