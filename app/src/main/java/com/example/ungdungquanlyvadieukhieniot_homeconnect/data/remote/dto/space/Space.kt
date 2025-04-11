package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.space
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.base.ISpaceBase

data class Space(
    override val spaceId: Int,
    override val name: String,
    override val houseId: Int
) : ISpaceBase