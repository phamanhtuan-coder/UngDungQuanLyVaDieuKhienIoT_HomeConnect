package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.weather

data class Current(
    val temp_c: Double,
    val condition: Condition,
    val wind_kph: Double,
    val humidity: Int,
    val vis_km: Double
)
