package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.logs

import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.base.ApiResponse

data class Log(
    val id: Int,
    val deviceId: Int,
    val deviceName: String,
    val deviceType: Int,
    val timestamp: String,
    val details: Any
)
