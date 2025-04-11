package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.alert

data class AlertDetail(
    val alertId: Int,
    val deviceId: Int,
    val spaceId: Int,
    val typeId: Int,
    val message: String,
    val timestamp: String,
    val status: Boolean,
    val alertTypeId: Int,
    val alertType: AlertType
)
