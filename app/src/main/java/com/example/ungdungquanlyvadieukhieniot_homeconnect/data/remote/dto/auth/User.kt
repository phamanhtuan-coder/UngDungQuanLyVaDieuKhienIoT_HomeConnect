package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.auth

data class User(
    val userID: Int,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val dateOfBirth: String,
    val emailVerified: Boolean,
    val profileImage: String?
)
