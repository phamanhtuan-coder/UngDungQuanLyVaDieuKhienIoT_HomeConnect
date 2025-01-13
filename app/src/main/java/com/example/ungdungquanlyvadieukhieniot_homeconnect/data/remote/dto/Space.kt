package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class Space(
    val SpaceID: Int,
    val HouseID: Int,
    val Name: String,
    val IsDeleted: Int,
    val CreatedAt: String,
    val UpdatedAt: String,
    val House: House
)