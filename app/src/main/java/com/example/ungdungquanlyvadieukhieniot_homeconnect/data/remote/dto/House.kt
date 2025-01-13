package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class House(
    val houseID: Int,
    val userID: Int,
    val name: String,
    val address: String,
    val iconName: String,
    val iconColor: String,
    val isDeleted: Int,
    val createdAt: String,
    val updatedAt: String
)