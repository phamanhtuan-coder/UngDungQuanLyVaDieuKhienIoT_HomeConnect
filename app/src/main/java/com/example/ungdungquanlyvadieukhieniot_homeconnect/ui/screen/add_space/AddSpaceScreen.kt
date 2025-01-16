package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.add_space

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.SpaceRepository
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.HouseSelection
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.SharedViewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.Screens
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.space.SpaceViewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.validation.ValidationUtils

@Composable
fun AddSpaceScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val context = LocalContext.current
    val spaceViewModel = remember {
        SpaceViewModel(SpaceRepository(context))
    }
    // Biến trạng thái để lưu giá trị nhập và thông báo lỗi
    var spaceName by remember { mutableStateOf("") }
    var spaceNameError by remember { mutableStateOf("") }
    var houseId by remember { mutableStateOf(-1) }
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
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier = Modifier
                                .width(if (isTablet) 500.dp else 400.dp)
                                .padding(16.dp)
                        ) {
                            // Ô nhập Tên Space
                            OutlinedTextField(
                                value = spaceName,
                                onValueChange = {
                                    spaceName = it
                                    spaceNameError = ValidationUtils.validateSpaceName(it)
                                },
                                leadingIcon = {
                                    Icon(Icons.Filled.LocationOn, contentDescription = null)
                                },
                                singleLine = true,
                                shape = RoundedCornerShape(25),
                                placeholder = { Text("Tên Space") },
                                isError = spaceNameError.isNotEmpty(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(if (isTablet) 80.dp else 70.dp),
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = colorScheme.onBackground,
                                    unfocusedTextColor = colorScheme.onBackground.copy(alpha = 0.7f),
                                    focusedContainerColor = colorScheme.onPrimary,
                                    unfocusedContainerColor = colorScheme.onPrimary,
                                    focusedIndicatorColor = colorScheme.primary,
                                    unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                                )
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            HouseSelection(
                                sharedViewModel = sharedViewModel,
                                //houses = listOf("House 1", "House 2", "House 3"),
                                onManageHouseClicked = { navController.navigate(Screens.HouseManagement.route) },
                                onTabSelected = {id ->
                                    houseId = id.toInt()
                                }
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            // Nút Thêm Space
                            Button(
                                onClick = {
                                    if (spaceName.isBlank() || houseId == -1) {
                                        Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                                    } else {
                                        spaceViewModel.createSpace(houseId.toInt(), spaceName)
                                        navController.popBackStack() // Quay lại màn hình trước đó
                                    }
                                },
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth()
                                    .height(if (isTablet) 56.dp else 48.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
                                shape = RoundedCornerShape(50)
                            ) {
                                Text(
                                    "Thêm Space",
                                    color = colorScheme.onPrimary
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}
