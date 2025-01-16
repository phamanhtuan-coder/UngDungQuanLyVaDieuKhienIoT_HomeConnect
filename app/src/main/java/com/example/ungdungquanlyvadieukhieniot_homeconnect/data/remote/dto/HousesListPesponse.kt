package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class HousesListPesponse(
    val HouseID: Int,
    val Name: String,
    val Address: String,
    val IconName: String,
    val IconColor: String,
    val Spaces: List<Spaces>
)

data class Spaces(
    val SpaceID: Int,
    val Name: String
)
