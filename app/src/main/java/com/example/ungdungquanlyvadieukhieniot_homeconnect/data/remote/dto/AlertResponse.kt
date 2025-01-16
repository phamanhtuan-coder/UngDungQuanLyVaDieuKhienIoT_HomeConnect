package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class AlertResponse(
    val AlertID: Int,
    val DeviceID: Int,
    val SpaceID: Int,
    val TypeID: Int,
    val Message: String,
    val Timestamp: String,
    val Status: Boolean,
    val AlertTypeID: Int,
    val Device: Device
)

data class AlertDetail(
    val AlertID: Int,
    val DeviceID: Int,
    val SpaceID: Int,
    val TypeID: Int,
    val Message: String,
    val Timestamp: String,
    val Status: Boolean,
    val AlertTypeID: Int,
    val AlertType: AlertType
)


data class AlertType(
    val AlertTypeName: String
)