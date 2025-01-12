package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class EmailResponse(
    val success: Boolean,
    val exists: Boolean?,
    val message: String
)