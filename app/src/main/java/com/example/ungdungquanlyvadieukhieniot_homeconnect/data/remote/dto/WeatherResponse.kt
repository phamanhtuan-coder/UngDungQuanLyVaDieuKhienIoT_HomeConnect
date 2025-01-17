package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class WeatherResponse(
    val location: Location,
    val current: Current
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val localtime: String
)

data class Current(
    val temp_c: Double,
    val condition: Condition,
    val wind_kph: Double,
    val humidity: Int,
    val vis_km: Double
)

data class Condition(
    val text: String,
    val icon: String
)
