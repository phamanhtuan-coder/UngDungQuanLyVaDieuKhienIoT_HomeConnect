package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class CreateSpaceResponse(
    val message: String,
    val space: CreatedSpace
)

data class CreatedSpace(
    val IsDeleted: Boolean,
    val SpaceID: Int,
    val HouseID: Int,
    val Name: String,
    val updatedAt: String,
    val createdAt: String
)
