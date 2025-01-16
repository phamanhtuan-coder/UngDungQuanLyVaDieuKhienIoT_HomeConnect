package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class DailyPowerUsageResponse(
    val dailyPowerUsages: List<DailyPowerUsageData>
)

data class DailyPowerUsageData(
    val date: String,
    val energyConsumed: Float,
    val powerRating: Float,
    val totalOnTimeHours: Float
)
