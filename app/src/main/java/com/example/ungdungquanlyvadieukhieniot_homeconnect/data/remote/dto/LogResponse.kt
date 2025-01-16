package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

// LogResponse.kt
data class LogResponse(
    val LogID: Int,
    val DeviceID: Int,
    val UserID: Int,
    val SpaceID: Int,
    val Action: String,
    val Timestamp: String,
    val Details: String,
    val Device: DeviceLog
)

data class DeviceLog(
    val DeviceID: Int,
    val TypeID: Int,
    val SpaceID: Int,
    val UserID: Int,
    val Name: String,
    val PowerStatus: Boolean,
    val Attribute: String,
    val WifiSSID: String,
    val WifiPassword: String,
    val IsDeleted: Boolean,
    val createdAt: String,
    val updatedAt: String
)

data class ActionData(
    val fromDevice: Boolean,
    val type: String
)

data class SensorDetails(
    val type: String,
    val gas: Int,
    val temperature: Double,
    val humidity: Int
)

data class LightDetails(
    val action: String,
    val powerStatus: Boolean
)

data class FormattedLog(
    val id: Int,
    val deviceName: String,
    val deviceType: Int,
    val actionType: String?,
    val timestamp: String,
    val details: Any?
)

data class FormattedSensorDetails(
    val gas: Int,
    val temperature: Double,
    val humidity: Int
)

data class FormattedLightDetails(
    val action: String,
    val powerStatus: Boolean
)