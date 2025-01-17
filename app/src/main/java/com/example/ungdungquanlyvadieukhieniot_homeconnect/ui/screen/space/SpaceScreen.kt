package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.space

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.SpaceRepository
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.HouseSelection
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.SharedViewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.Screens
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_sharing_list.isTablet
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme


/** Giao diện màn hình Space Screen
 * -----------------------------------------
 * Người viết: Phạm Anh Tuấn
 * Ngày viết: 31/12/2024
 * Lần cập nhật cuối: 31/12/2024
 * -----------------------------------------
 * @param navController Đối tượng điều khiển điều hướng.
 *
 * @return Scaffold chứa giao diện màn hình Danh sách Space
 *
 * ---------------------------------------
 *
 */
@Composable
fun SpaceScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
) {

    val context = LocalContext.current
    // Khởi tạo ViewModel bên trong hàm
    val viewModel = remember {
        SpaceViewModel(SpaceRepository(context))
    }

    val spaceState by viewModel.spaceState.collectAsState() // Theo dõi trạng thái dữ liệu spaces
    val spaceCount = remember { mutableStateOf("") }

    // Khi có thay đổi trong danh sách spaces, cập nhật `spaceCount`
    LaunchedEffect(spaceState) {
        try {
            if (spaceState is SpaceState.Success) {
                val spaces = (spaceState as SpaceState.Success).spaces
                spaceCount.value = spaces.size.toString()
                Log.d("SpaceScreen", "Danh sách Spaces: $spaces")
            }
        } catch (e: Exception) {
            Log.e("SpaceScreen", "Lỗi khi xử lý SpaceState: ${e.message}")
        }
    }


    AppTheme {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Header(
                    navController = navController,
                    type = "Back",
                    title = "Danh sách Spaces"
                )
            },
            bottomBar = {
                MenuBottom(navController)
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screens.AddSpace.route)
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add Space",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    contentColor = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
            },
            floatingActionButtonPosition = FabPosition.End,
            content = { innerPadding ->
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .imePadding()
                            .padding(innerPadding)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = colorScheme.background)
                                .wrapContentHeight()
                                .defaultMinSize(minHeight = 150.dp)
                        ) {
                            Column {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .defaultMinSize(minHeight = 110.dp)
                                        .background(
                                            color = colorScheme.primary,
                                            shape = RoundedCornerShape(bottomStart = 38.dp)
                                        )
                                        .zIndex(1f),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .width(if (isTablet()) 550.dp else 300.dp)
                                            .align(Alignment.Center)
                                    ) {
                                        HouseSelection(
                                            sharedViewModel = sharedViewModel,
                                            onManageHouseClicked = {
                                                navController.navigate(Screens.HouseManagement.route)
                                            },
                                            onTabSelected = { id ->
                                                viewModel.getSpaces(id) // Lấy danh sách spaces khi chọn houseId
                                            }
                                        )
                                    }
                                }


                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    // Box màu vàng (ở dưới)
                                    Box(
                                        modifier = Modifier
                                            .width(40.dp)
                                            .height(40.dp)
                                            .align(Alignment.TopEnd)
                                            .background(color = colorScheme.primary)
                                            .zIndex(1f) // Z-index thấp hơn
                                    ){}

                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(
                                                color = colorScheme.background,
                                                shape = RoundedCornerShape(topEndPercent = 100)
                                            )
                                            .zIndex(2f)
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .padding(start = 32.dp, top = 4.dp, bottom = 4.dp)
                                                .fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = "Số lượng spaces:",
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = colorScheme.onBackground
                                            )
                                            Box(
                                                modifier = Modifier
                                                    .padding(start = 8.dp)
                                                    .size(32.dp)
                                                    .background(
                                                        color = colorScheme.primary,
                                                        shape = CircleShape
                                                    ),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = spaceCount.value,
                                                    color = colorScheme.onPrimary,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        when (spaceState) {
                            is SpaceState.Loading -> {
                                CircularProgressIndicator()
                                Log.d("SpaceScreen", "Đang tải danh sách Spaces...")
                            }
                            is SpaceState.Success -> {
                                val spaces = (spaceState as SpaceState.Success).spaces
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp)
                                ) {
                                    items(spaces) { space ->
                                        SpaceCard(
                                            isTablet = isTablet(),
                                            roomName = space.Name,
                                            spaceId = space.SpaceID,
                                            temperature = 24.0,
                                            totalDevices = 10,
                                            activeDevices = 5,
                                            isAllDevicesOn = true,
                                            onSwitchToggled = { isOn ->
                                                println("${space.Name}: ${if (isOn) "Bật" else "Tắt"} toàn bộ thiết bị!")
                                            },
                                            onRename = { spaceId, newName ->
                                                viewModel.updateSpace(spaceId, newName) // Gọi ViewModel để cập nhật tên
                                            }
                                        )
                                    }
                                }
                            }
                            is SpaceState.Error -> {
                                val error = (spaceState as SpaceState.Error).error
                                Text(
                                    text = "Lỗi: $error",
                                    color = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.padding(16.dp)
                                )
                                Log.e("SpaceScreen", "Lỗi: $error")
                            }
                            is SpaceState.UpdateSuccess -> {
                                val message = (spaceState as SpaceState.UpdateSuccess).message
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                viewModel.getSpaces((spaceState as SpaceState.UpdateSuccess).updatedSpace.HouseID)
                            }
                            else -> {
                                Log.d("SpaceScreen", "Trạng thái không xác định")
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun SpaceCard(
    isTablet: Boolean,
    roomName: String,
    spaceId: Int,
    temperature: Double,
    totalDevices: Int,
    activeDevices: Int,
    isAllDevicesOn: Boolean,
    onSwitchToggled: (Boolean) -> Unit,
    onRename: (Int, String) -> Unit // Callback để xử lý đổi tên
) {
    val colorScheme = MaterialTheme.colorScheme

    // Trạng thái để hiển thị Dialog đổi tên
    var showRenameDialog by remember { mutableStateOf(false) }
    var newName by remember { mutableStateOf(roomName) }

    if (showRenameDialog) {
        RenameDialog(
            initialName = roomName,
            onDismiss = { showRenameDialog = false },
            onConfirm = { updatedName ->
                onRename(spaceId, updatedName)
                showRenameDialog = false
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = if (isTablet) 24.dp else 16.dp, vertical = 8.dp)
            .background(color = colorScheme.secondary, shape = RoundedCornerShape(16.dp))
            .clickable { showRenameDialog = true } // Nhấn vào card để mở Dialog
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon phòng
            Box(
                modifier = Modifier
                    .size(if (isTablet) 80.dp else 60.dp)
                    .background(color = colorScheme.onPrimary, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = roomName.first().toString(),
                    color = colorScheme.primary,
                    fontSize = if (isTablet) 32.sp else 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Chi tiết phòng
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = roomName,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = if (isTablet) 20.sp else 16.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = colorScheme.onSecondary
                )
                Text(
                    text = "Nhiệt độ: $temperature °C",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = when {
                        temperature >= 30 -> Color(0xFFE91E63)
                        temperature <= 10 -> Color(0xFF2196F3)
                        else -> Color(0xFF4CAF50)
                    }
                )
                Text(
                    text = "Thiết bị: $activeDevices/$totalDevices đang hoạt động",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorScheme.onSecondary
                )
            }

            // Nút Switch bật/tắt toàn bộ thiết bị
            Switch(
                checked = isAllDevicesOn,
                onCheckedChange = { newState ->
                    onSwitchToggled(newState)
                },
                thumbContent = {
                    Icon(
                        imageVector = if (isAllDevicesOn) Icons.Filled.Check else Icons.Filled.Close,
                        contentDescription = "On/Off Switch",
                        tint = if (isAllDevicesOn) colorScheme.onPrimary else colorScheme.onSecondary.copy(alpha = 0.8f)
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
    }
}


@Composable
fun RenameDialog(
    initialName: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var textFieldValue by remember { mutableStateOf(initialName) }

    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Đổi tên Space")
        },
        text = {
            Column {
                Text("Nhập tên mới cho Space:")
                androidx.compose.material3.TextField(
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it },
                    singleLine = true,
                    placeholder = { Text("Tên mới") }
                )
            }
        },
        confirmButton = {
            androidx.compose.material3.Button(onClick = { onConfirm(textFieldValue) }) {
                Text("Xác nhận")
            }
        },
        dismissButton = {
            androidx.compose.material3.Button(onClick = onDismiss) {
                Text("Hủy")
            }
        }
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SpaceScreenPreview() {
    SpaceScreen(navController = rememberNavController(), sharedViewModel = SharedViewModel())
}