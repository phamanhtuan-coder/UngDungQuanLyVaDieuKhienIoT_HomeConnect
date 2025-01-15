package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class User(
    val UserID: Int,
    val Name: String,
    val Email: String,
    val Phone: String,
    val Address: String,
    val DateOfBirth: String,
    val EmailVerified: Boolean,
    val ProfileImage: String
    // Todo: ... các trường khác nếu cần
)