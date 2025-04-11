package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.house
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.space.Space

data class HousesListResponse(
    val houseId: Int,
    val name: String,
    val address: String,
    val iconName: String,
    val iconColor: String,
    val spaces: List<Space>
)
