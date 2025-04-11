package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.statistics
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.base.IPowerUsageData

data class PowerUsageData(
    override val energyConsumed: Double,
    override val powerRating: Double,
    override val totalOnTimeHours: Double
) : IPowerUsageData
