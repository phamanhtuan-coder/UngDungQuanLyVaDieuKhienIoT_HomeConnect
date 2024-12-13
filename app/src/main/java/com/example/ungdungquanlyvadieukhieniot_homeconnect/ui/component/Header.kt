package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalTime



@Composable
fun getGreeting(): String {
    val hour = LocalTime.now().hour
    return when (hour) {
        in 6..11 -> "Good Morning,"
        in 12..17 -> "Good Afternoon,"
        else -> "Good Evening,"
    }
}


/**
 * Modernized Header (TopAppBar)
 * -----------------------------------------
 * Người viết: Phạm Anh Tuấn
 * Ngày viết: 29/11/2024
 * Ngày cập nhật gần nhất: 13/12/2024
 * -----------------------------------------
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackHeader(
    title: String,
    onBackClick: () -> Unit,
    onNotificationClick: () -> Unit
) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeHeader(username: String, onNotificationClick: () -> Unit) {
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

@Composable
fun RoundedIconButton(icon: ImageVector, description: String, onClick: () -> Unit) {
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