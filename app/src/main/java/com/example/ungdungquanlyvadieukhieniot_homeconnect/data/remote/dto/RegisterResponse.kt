package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto



data class RegisterResponse(
    val message: String,
    val user: RegisterRequest,
    val error: String
)
