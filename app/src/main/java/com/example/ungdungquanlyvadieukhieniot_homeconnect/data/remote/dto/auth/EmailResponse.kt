package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.auth
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.base.IBaseResponse

data class EmailResponse(
    override val message: String,
    override val error: String? = null,
    val success: Boolean,
    val exists: Boolean?
) : IBaseResponse

