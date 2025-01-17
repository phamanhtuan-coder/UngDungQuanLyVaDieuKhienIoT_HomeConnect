package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

// Request và Response cho Daily Room Power Usage
data class DailyPowerUsageResponse2(
    val message: String,
    val data: List<PowerUsageData>
)

data class PowerUsageData(
    val date: String,
    val totalEnergyConsumed: String
)