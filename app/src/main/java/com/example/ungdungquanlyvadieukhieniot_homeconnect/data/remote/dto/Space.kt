package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class Space(
    val spaceID: Int,
    val houseID: Int,
    val name: String,
    val isDeleted: Int,
    val createdAt: String,
    val updatedAt: String,
    val house: House
)