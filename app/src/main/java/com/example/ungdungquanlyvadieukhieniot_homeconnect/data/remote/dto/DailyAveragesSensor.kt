package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class DailyAverageSensorResponse(
    val dailyAverages: List<DailyAverageData>
)

data class DailyAverageData(
    val date: String,
    val averageGas: Float,
    val averageTemperature: Float,
    val averageHumidity: Float
)
