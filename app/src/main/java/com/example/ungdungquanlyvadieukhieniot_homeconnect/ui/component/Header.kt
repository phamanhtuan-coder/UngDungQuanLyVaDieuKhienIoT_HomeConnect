package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme
import java.time.LocalTime

/**
 * Lấy lời chào dựa vào thời gian hiện tại
 * -----------------------------------------
 * - Người viết: Phạm Anh Tuấn
 * - Ngày viết: 29/11/2024
 * - Lần cập nhật cuối: 13/12/2024
 * -----------------------------------------
 * @return Lời chào dựa vào thời gian hiện tại
 * ---------------------------------------
 */
@Composable
fun getGreeting(): String {
    val hour = LocalTime.now().hour
    return when (hour) {
        in 6..11 -> "Chào buổi sáng,"
        in 12..14 -> "Chào buổi trưa,"
        in 15..18 -> "Chào buổi chiều,"
        else -> "Chào buổi tối,"
    }
}


/**
 * Header cho màn hình Home hoặc Back
 * -----------------------------------------
 * - Người viết: Phạm Anh Tuấn
 * - Ngày viết: 29/11/2024
 * - Ngày cập nhật gần nhất: 13/12/2024
 * -----------------------------------------
 *
 * @param type: Loại Header (Home hoặc Back)
 * @param title: Tiêu đề của Header
 * @param username: Tên người dùng
 * @param onBackClick: Hàm xử lý khi click vào nút Back
 * @param onNotificationClick: Hàm xử lý khi click vào nút Notification
 *
 * @return TopAppBar chứa thông tin Header
 */
@Preview(showBackground = true)
@Composable
fun Header(
    type: String = "Home",
    title: String = "",
    username: String = "Username",
    onBackClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {}
) {
    when (type) {
        "Home" -> HomeHeader(username, onNotificationClick)
        "Back" -> BackHeader(title, onBackClick, onNotificationClick)
    }
}

/**
 * Header cho màn hình cần nút Back
 * -----------------------------------------
 * Người viết: Phạm Anh Tuấn
 * Ngày viết: 29/11/2024
 * Ngày cập nhật gần nhất: 13/12/2024
 * -----------------------------------------
 * @param title: Tiêu đề của Header
 * @param onBackClick: Hàm xử lý khi click vào nút Back
 * @param onNotificationClick: Hàm xử lý khi click vào nút Notification
 * @return TopAppBar chứa thông tin Header
 * ---------------------------------------
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackHeader(
    title: String,
    onBackClick: () -> Unit,
    onNotificationClick: () -> Unit
) {
    AppTheme {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            title = {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            navigationIcon = {
                RoundedIconButton(
                    icon = Icons.AutoMirrored.Filled.ArrowBack,
                    description = "Back",
                    onClick = onBackClick
                )
            },
            actions = {
                RoundedIconButton(
                    icon = Icons.Filled.Notifications,
                    description = "Notifications",
                    onClick = onNotificationClick
                )
            }
        )
    }
}

/**
 * Header cho màn hình Home
 * -----------------------------------------
 * Người viết: Phạm Anh Tuấn
 * Ngày viết: 29/11/2024
 * Ngày cập nhật gần nhất: 13/12/2024
 * -----------------------------------------
 * @param username: Tên người dùng
 * @param onNotificationClick: Hàm xử lý khi click vào nút Notification
 * @return TopAppBar chứa thông tin Header
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeHeader(username: String, onNotificationClick: () -> Unit) {
    AppTheme {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            title = {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = getGreeting(),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = username,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                        fontSize = 14.sp
                    )
                }
            },
            actions = {
                RoundedIconButton(
                    icon = Icons.Filled.Notifications,
                    description = "Notifications",
                    onClick = onNotificationClick
                )
            }
        )
    }
}

@Composable
fun RoundedIconButton(icon: ImageVector, description: String, onClick: () -> Unit) {
    AppTheme {
    IconButton(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .background(MaterialTheme.colorScheme.onPrimary, shape = CircleShape),
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = MaterialTheme.colorScheme.primary
        )
    }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun HeaderPhonePreview() {
    Header(type = "Home", username = "Alice")
}

@Preview(showBackground = true, widthDp = 720)
@Composable
fun HeaderTabletPreview() {
    Header(type = "Back", title = "Settings", username = "Bob")
}