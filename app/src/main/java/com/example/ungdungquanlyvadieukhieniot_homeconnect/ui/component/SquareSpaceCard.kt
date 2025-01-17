package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SpaceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme

/**
 * Space Card UI Component
 * -----------------------------------------
 * - Author: Phạm Anh Tuấn
 * - Created: 29/11/2024
 * - Last Updated: 11/12/2024
 * -----------------------------------------
 * @param space: Space data to display
 * @return Card containing space information
 * ---------------------------------------
 */
@Composable
fun SpaceCard(
    space: SpaceResponse,
) {
    // Obtain the ViewModel
    val viewModel: SquareSpaceCardViewModel = viewModel()

    // Trigger data fetching when the Composable is first composed or when space.SpaceID changes
    LaunchedEffect(key1 = space.SpaceID) {
        viewModel.getSpaceDetail(space.SpaceID)
    }

    // Collect the state from the ViewModel
    val spaceDetailState by viewModel.spaceDetailState.collectAsState()

    // Local state variables to manage UI data
    var temperature by remember { mutableStateOf(randomInt(25, 40)) }
    var icon by remember { mutableStateOf(Icons.Default.Home) }
    var spaceName by remember { mutableStateOf(space.Name) }
    var deviceCount by remember { mutableStateOf(randomInt(0, 10)) }

    // Update local state based on ViewModel's state
    when (val state = spaceDetailState) {
        is SpaceState.Success -> {
            temperature = randomInt(25, 40) // Replace with actual data
            icon = Icons.Default.Home // Replace with dynamic icons if available
            spaceName = state.space.Name
            deviceCount = randomInt(0, 10) // Replace with actual data
        }

        is SpaceState.Error -> {
            val error = state.error
            Log.e("SpaceCard", "Error fetching space detail: $error")
            // Optionally, show error UI
        }

        is SpaceState.Loading -> {
            Log.d("SpaceCard", "Loading space detail...")
            // Optionally, show loading UI
        }

        is SpaceState.Idle -> {
            // Initial state; already using default or passed-in values
        }
    }

    // UI Rendering
    AppTheme {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .size(160.dp)
                .clip(RoundedCornerShape(12.dp))
                .shadow(4.dp, RoundedCornerShape(12.dp)),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF5F5F5),
                contentColor = Color.Black
            ),
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Temperature Display
                    TemperatureDisplay(temperature = temperature)

                    // Icon Display
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color(0xFF2196F3),
                        modifier = Modifier
                            .size(48.dp)
                            .padding(8.dp)
                    )

                    // Space Name Display
                    Text(
                        text = spaceName,
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    // Device Count Display
                    Text(
                        text = "$deviceCount Devices Connected",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        )
    }
}

/**
 * Temperature Display Composable
 * @param temperature: Current temperature to display
 */
@Composable
fun TemperatureDisplay(temperature: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = when {
                    temperature >= 30 -> Color(0xFFE91E63) // Hot
                    temperature <= 10 -> Color(0xFF2196F3) // Cold
                    else -> Color(0xFF4CAF50) // Moderate
                },
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = "$temperature °C",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * Generates a random integer between [from] (inclusive) and [to] (exclusive).
 * Used here for placeholder data.
 */
fun randomInt(from: Int, to: Int): Int {
    return (from until to).random()
}