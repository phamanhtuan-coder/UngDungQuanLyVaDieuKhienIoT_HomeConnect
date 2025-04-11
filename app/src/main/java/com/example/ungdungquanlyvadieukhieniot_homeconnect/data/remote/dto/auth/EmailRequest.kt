package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.auth

data class EmailRequest(
    val email: String,
    val otp: String? = null
)