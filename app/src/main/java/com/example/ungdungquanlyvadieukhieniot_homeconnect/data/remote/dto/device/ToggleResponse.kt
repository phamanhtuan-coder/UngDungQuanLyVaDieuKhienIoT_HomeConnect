package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.device
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.base.IBaseResponse

data class ToggleResponse(
    override val message: String,
    override val error: String?,
    val device: Device
) : IBaseResponse
