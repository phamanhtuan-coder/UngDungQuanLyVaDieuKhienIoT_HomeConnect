package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class UpdateHouseResponse(
    val message: String,
    val house: HouseDetail
)

data class HouseDetail(
    val HouseID: Int,
    val UserID: Int,
    val Name: String,
    val Address: String,
    val IconName: String,
    val IconColor: String,
    val IsDeleted: Int,
    val createdAt: String,
    val updatedAt: String
)

