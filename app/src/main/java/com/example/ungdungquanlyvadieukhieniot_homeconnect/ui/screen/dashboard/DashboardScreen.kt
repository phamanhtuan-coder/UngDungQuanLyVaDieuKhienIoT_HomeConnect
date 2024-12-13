package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom

@Composable
fun DashboardScreen() {
    val tabs = listOf("Living Room", "Bedroom", "Kitchen")
    val selectedTab = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {

            Header(
                type = "Back",
                title="Thống kê",
                onNotificationClick = { /* TODO */ },
                onBackClick = { /* TODO */ }
            )
        },
        bottomBar = {
            MenuBottom()
        }

    ) {
        Column(modifier = Modifier.fillMaxSize().padding(it)) {

            // Chart Section
            LineChart(title = "Power Usage", data = listOf(50f, 75f, 100f, 80f, 120f, 95f, 110f), labels = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"))

            // Tab Menu
            TabRow(selectedTabIndex = selectedTab.value) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = selectedTab.value == index,
                        onClick = { selectedTab.value = index },
                        text = {
                            Text(text = tab, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        },
                    )
                }
            }

            // Content Area
            Box(modifier = Modifier.fillMaxSize()) {
                when (selectedTab.value) {
                    0 -> RoomContent(roomName = "Living Room")
                    1 -> RoomContent(roomName = "Bedroom")
                    2 -> RoomContent(roomName = "Kitchen")
                }
            }
        }
    }


}

@Composable
fun RoomContent(roomName: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$roomName Controls",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Example room controls
        ControlItem(icon = Icons.Filled.Lightbulb, label = "Lights", description = "Adjust brightness", onClick = { /* TODO */ })
        Spacer(modifier = Modifier.height(8.dp))
        ControlItem(icon = Icons.Filled.Tv, label = "TV", description = "Change channels", onClick = { /* TODO */ })
        Spacer(modifier = Modifier.height(8.dp))
        ControlItem(icon = Icons.Filled.Home, label = "Air Conditioner", description = "Set temperature", onClick = { /* TODO */ })
    }
}

@Composable
fun ControlItem(icon: ImageVector, label: String, description: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = label,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = description,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
            Button(onClick = onClick, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) {
                Text(text = "Control", color = Color.White)
            }
        }
    }
}

@Composable
fun LineChart(title: String, data: List<Float>, labels: List<String>, color: Color = MaterialTheme.colorScheme.primary) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))
        Canvas(modifier = Modifier.fillMaxWidth().height(200.dp)) {
            val maxValue = (data.maxOrNull() ?: 1f) * 1.2f
            val stepX = size.width / (labels.size - 1)
            val stepY = size.height / maxValue

            // Draw Lines
            for (i in 1 until data.size) {
                drawLine(
                    color = color,
                    start = Offset((i - 1) * stepX, size.height - data[i - 1] * stepY),
                    end = Offset(i * stepX, size.height - data[i] * stepY),
                    strokeWidth = 4f
                )
            }

            // Draw Points
            data.forEachIndexed { index, value ->
                drawCircle(
                    color = color,
                    center = Offset(index * stepX, size.height - value * stepY),
                    radius = 8f
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}