package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.base

data class MessageResponse(
    override val message: String,
    override val error: String?
) : IBaseResponse
