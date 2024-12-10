package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Devices

import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Giao diện Thanh Menu Bottom (BottomAppBar)
 * -----------------------------------------
 * Người viết: Phạm Anh Tuấn
 * Ngày viết: 29/11/2024
 * Lần cập nhật cuối: 11/12/2024
 * -----------------------------------------
 * Input: Không có
 * Output: BottomAppBar
 *
 *
 * ---------------------------------------
 */
@Preview(showBackground = true)
@Composable
fun MenuBottom() {
    var selectedIndex by remember { mutableStateOf(0) }
    val items = listOf(
        "Dashboard" to Icons.Filled.PieChart,
        "Devices" to Icons.Filled.Devices,
        "Personal" to Icons.Filled.Person,
        "Settings" to Icons.Filled.Settings
    )
    val screenWidth = LocalConfiguration.current.screenWidthDp

    BottomAppBar(
        tonalElevation = 4.dp,
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.height(110.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Top
        ) {
            // Dashboard and Devices
            items.subList(0, 2).forEachIndexed { index, item ->
                MenuItem(
                    text = item.first,
                    icon = item.second,
                    isSelected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    isTablet = screenWidth > 600
                )
            }

            // Spacer FAB
            Spacer(modifier = Modifier.width(96.dp))

            // Personal and Settings
            items.subList(2, 4).forEachIndexed { index, item ->
                MenuItem(
                    text = item.first,
                    icon = item.second,
                    isSelected = selectedIndex == index + 2,
                    onClick = { selectedIndex = index + 2 },
                    isTablet = screenWidth > 600
                )
            }
        }
    }
}

@Composable
fun MenuItem(
    text: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
    isTablet: Boolean,
    textSize: TextUnit = 14.sp,
    iconSize: Dp = 36.dp
) {
    if (isTablet) {
        // Tablet Layout:
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
                modifier = Modifier
                    .size(iconSize)
                    .shadow(if (isSelected) 6.dp else 0.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
                fontSize = textSize,
                maxLines = 1
            )
        }
    } else {
        // Mobile Layout:
        Column(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val mobileIconSize = if (isSelected) 22.dp else 20.dp
            val mobileTextSize = if (isSelected) 12.sp else 10.sp
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
                modifier = Modifier
                    .size(mobileIconSize)
                    .shadow(if (isSelected) 6.dp else 0.dp)
            )
            Text(
                text = text,
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray,
                fontSize = mobileTextSize,
                maxLines = 1
            )
        }
    }
}