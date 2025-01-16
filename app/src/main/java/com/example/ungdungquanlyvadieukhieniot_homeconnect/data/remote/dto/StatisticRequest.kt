package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

// Request and Response Models
data class DailySensorRequest(val deviceId: Int, val date: String)
data class WeeklySensorRequest(val deviceId: Int)
data class RangeSensorRequest(val deviceId: Int, val startDate: String, val endDate: String)
//data class DailyPowerUsageResponse(val dailyPowerUsages: List<PowerUsage>)
data class AverageSensorResponse(val message: String, val data: SensorData)
//data class WeeklyAverageSensorResponse(val weeklyAverage: String, val date: String)
//data class PowerUsage(val date: String, val energyConsumed: Double, val powerRating: Double, val totalOnTimeHours: Double)
data class SensorData(val averageGas: Double, val averageTemperature: Double, val averageHumidity: Double)