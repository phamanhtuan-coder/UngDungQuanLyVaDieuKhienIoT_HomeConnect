package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.devicedetail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DeviceDetailScreen() {
    return Scaffold (
        topBar = {
            
        },
        content = { innerPadding ->
            val test = innerPadding
        }
    )
}