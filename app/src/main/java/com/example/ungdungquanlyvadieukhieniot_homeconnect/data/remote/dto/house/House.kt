package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.house
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.base.IHouseBase

data class House(
    override val houseId: Int,
    override val name: String,
    override val address: String
) : IHouseBase


