package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material3.Icon
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_operating.DividerLine
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_operating.IconBox
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_operating.TimeInfo

@Composable
fun SmartCard(
    isTablet: Boolean,
    deviceName: String,
    deviceLocation: String,
    switchState: Boolean,
    onToggle: () -> Unit,
    onNavigateDetail: () -> Unit,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme
    val endPadding = 32.dp

    Card(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .padding(6.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = onNavigateDetail
    ) {
        Column(modifier = Modifier.padding(6.dp)) {
            // Tiêu đề và công tắc
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = deviceName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = colorScheme.onPrimary
                    )
                    Text(
                        text = deviceLocation,
                        fontSize = 12.sp,
                        color = colorScheme.onSecondary
                    )
                }
                Switch(
                    checked = switchState,
                    onCheckedChange = { onToggle() },
                    thumbContent = {
                        Icon(
                            imageVector = if (switchState) Icons.Filled.Check else Icons.Filled.Close,
                            contentDescription = "On/Off Switch",
                            tint = if (switchState) colorScheme.onPrimary else colorScheme.onSecondary.copy(alpha = 0.8f)
                        )
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = colorScheme.primary,
                        checkedTrackColor = colorScheme.onPrimary,
                        uncheckedThumbColor = colorScheme.secondary,
                        uncheckedTrackColor = colorScheme.onSecondary.copy(alpha = 0.8f),
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Thông tin thêm + icon chức năng
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                DeviceInfoSection("8 pm", "8 am", endPadding)

                if (isTablet) {
                    ExtraInfoSection("Điện áp", "5V", endPadding / 2)
                    ExtraInfoSection("Dòng", "20mA", endPadding / 2)
                }

                Column(horizontalAlignment = Alignment.End) {
                    IconButtonBox("\uD83D\uDDD1", onClick = onDelete)
                    Spacer(modifier = Modifier.height(4.dp))
                    IconButtonBox("\u270E", onClick = onEdit)
                }
            }
        }
    }
}

@Composable
fun DeviceInfoSection(fromTime: String, toTime: String, endPadding: Dp) {
    val colorScheme = MaterialTheme.colorScheme
    Row {
        IconBox("\uD83D\uDCA1", colorScheme.background)
        TimeInfo("from", fromTime)
        DividerLine(endPadding)
        TimeInfo("to", toTime)
        DividerLine(endPadding)
    }
}

@Composable
fun ExtraInfoSection(title: String, value: String, padding: Dp) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(end = padding)) {
        Text(text = title, fontSize = 10.sp, color = MaterialTheme.colorScheme.onPrimary.copy(0.8f))
        Text(text = value, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
fun IconButtonBox(iconText: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(24.dp),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(text = iconText, fontSize = 12.sp)
    }
}


@Preview(showBackground = true)
@Composable
fun SmartDeviceCardPreview() {
    MaterialTheme {
        SmartCard(
            isTablet = true,
            deviceName = "Smart Fan",
            deviceLocation = "Phòng khách",
            switchState = true,
            onToggle = { /* Bật tắt */ },
            onNavigateDetail = {  },
            onDelete = { /* Xoá thiết bị */ },
            onEdit = { /* Sửa thiết bị */ }
        )

    }
}
