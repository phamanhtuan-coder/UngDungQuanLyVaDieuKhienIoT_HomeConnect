package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.device
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.base.IBaseResponse

data class DeviceTokenResponse(
    override val message: String,
    override val error: String? = null,
    val success: Boolean
) : IBaseResponse
