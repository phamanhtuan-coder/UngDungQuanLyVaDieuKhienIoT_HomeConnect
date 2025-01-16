package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

// LogDetailNavArg.kt
data class LogDetailNavArg(
    val logId: Int,
    val deviceName: String,
    val deviceType: Int,
    val timestamp: String,
    val details: String
)