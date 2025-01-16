package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

data class SharedUser(
    val PermissionID: Int,
    val DeviceID: Int,
    val SharedWithUserID: Int,
    val SharedWithUser: SharedUserDetail,
    val CreatedAt: String,

    )

data class SharedUserDetail(
    val UserID: Int,
    val Name: String,
    val Email: String
)

data class SharedUserRequest(
    val sharedWithUserEmail: String
)

data class RevokeShare(
    val permissionId: Int
)