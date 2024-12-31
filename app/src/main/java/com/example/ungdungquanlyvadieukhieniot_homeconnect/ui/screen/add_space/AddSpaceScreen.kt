package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.add_space

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme

@Composable
fun AddSpaceScreen(
    navController: NavHostController
) {
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600
    AppTheme {

        val colorScheme = MaterialTheme.colorScheme
        Scaffold(
            containerColor = colorScheme.background,
            topBar = {
                Header(navController, "Back", "Thêm Space")
            },
            bottomBar = {
                MenuBottom(navController)
            },
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .imePadding()
                            .verticalScroll(rememberScrollState())
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center, // Căn giữa theo chiều dọc
                        horizontalAlignment = Alignment.CenterHorizontally // Căn giữa theo chiều ngang
                    ) {


                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(colorScheme.background)
                                .padding(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(
                                        colorScheme.background,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(16.dp)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .width(if (isTablet) 500.dp else 400.dp)
                                    ) {
                                        OutlinedTextField(
                                            value = "",
                                            onValueChange = {
                                                // TODO: Handle name input
                                            },
                                            leadingIcon = {
                                                Icon(
                                                    Icons.Filled.LocationOn,
                                                    contentDescription = null
                                                )
                                            },
                                            label = { Text("Tên Space") },
                                            modifier = Modifier
                                                .width(if (isTablet) 500.dp else 400.dp)
                                                .height(if (isTablet) 80.dp else 70.dp),
                                            colors = TextFieldDefaults.colors(
                                                focusedContainerColor = colorScheme.onPrimary,
                                                unfocusedContainerColor = colorScheme.onPrimary,
                                                focusedIndicatorColor = colorScheme.primary,
                                                unfocusedIndicatorColor = colorScheme.onBackground.copy(
                                                    alpha = 0.5f
                                                )
                                            )
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))

                                        OutlinedTextField(
                                            value = "",
                                            onValueChange = {
                                                // TODO: Handle house selection input
                                            },
                                            leadingIcon = {
                                                Icon(
                                                    Icons.Filled.Home,
                                                    contentDescription = null
                                                )
                                            },
                                            label = { Text("Chọn Nhà") },
                                            modifier = Modifier
                                                .width(if (isTablet) 500.dp else 400.dp)
                                                .height(if (isTablet) 80.dp else 70.dp),
                                            colors = TextFieldDefaults.colors(
                                                focusedContainerColor = colorScheme.onPrimary,
                                                unfocusedContainerColor = colorScheme.onPrimary,
                                                focusedIndicatorColor = colorScheme.primary,
                                                unfocusedIndicatorColor = colorScheme.onBackground.copy(
                                                    alpha = 0.5f
                                                )
                                            ),
                                            trailingIcon = {
                                                Icon(
                                                    imageVector = Icons.Default.KeyboardArrowDown,
                                                    contentDescription = null
                                                )
                                            }
                                        )
                                        Spacer(modifier = Modifier.height(16.dp))

                                        Button(
                                            onClick = { /* TODO: Handle add space logic */ },
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                                .width(if (isTablet) 300.dp else 200.dp)
                                                .height(if (isTablet) 56.dp else 48.dp),
                                            colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
                                            shape = RoundedCornerShape(50)
                                        ) {
                                            Text(
                                                "Thêm space",
                                                color = colorScheme.onPrimary
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddSpaceScreenPreview() {
    AddSpaceScreen(navController = rememberNavController())
}