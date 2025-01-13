package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class House(
    val HouseID: Int,
    val UserID: Int,
    val Name: String,
    val Address: String,
    val IconName: String,
    val IconColor: String,
    val IsDeleted: Int,
    val CreatedAt: String,
    val UpdatedAt: String
)