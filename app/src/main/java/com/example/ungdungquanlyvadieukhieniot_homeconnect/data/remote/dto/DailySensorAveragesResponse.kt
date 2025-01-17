package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

// Request và Response cho Daily Room Sensor Averages
data class DailySensorAveragesResponse(
    val message: String,
    val data: List<SensorAverageData>
)

data class SensorAverageData(
    val date: String,
    val averageGas: Double,
    val averageTemperature: Double,
    val averageHumidity: Double
)

