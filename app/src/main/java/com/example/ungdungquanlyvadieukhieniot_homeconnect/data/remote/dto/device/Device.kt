package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.device
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.base.ApiResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.base.IDeviceBase

data class Device(
    override val deviceId: Int,
    override val name: String,
    override val typeId: Int,
    override val spaceId: Int,
    override val powerStatus: Boolean,
    override val attribute: String
) : IDeviceBase
