package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class RegisterRequest (
    val Name: String,
    val Email: String,
    val PasswordHash: String,
    val Phone: String,
    val Address: String,
    val DateOfBirth: String,
    val ProfileImage: String
)