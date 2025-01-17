package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class DailyAverageSensorRequest(
    val deviceId: Int,
    val date: String
)

data class DailyAverageSensorResponse2(
    val message: String,
    val data: SensorData
)

data class SensorData2(
    val averageGas: Double,
    val averageTemperature: Double,
    val averageHumidity: Double
)

data class DailyPowerUsageRequest(
    val deviceId: Int,
    val date: String
)

data class DailyPowerUsageResponse3(
    val message: String,
    val data: PowerUsageData2
)

data class PowerUsageData2(
    val energyConsumed: Double,
    val powerRating: Double,
    val totalOnTimeHours: Double
)