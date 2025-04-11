package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DeviceStatusBadge(status: String, modifier: Modifier = Modifier) {
    val color = when (status.lowercase()) {
        "đang bật" -> Color.Green
        "đang tắt" -> Color.Gray
        "mất kết nối" -> Color.Red
        else -> Color.LightGray
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color.copy(alpha = 0.2f))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(text = status, color = color, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDeviceStatusBadge() {
    Column(modifier = Modifier.padding(16.dp)) {
        DeviceStatusBadge("Đang bật")
        Spacer(modifier = Modifier.height(8.dp))
        DeviceStatusBadge("Đang tắt")
        Spacer(modifier = Modifier.height(8.dp))
        DeviceStatusBadge("Mất kết nối")
    }
}

