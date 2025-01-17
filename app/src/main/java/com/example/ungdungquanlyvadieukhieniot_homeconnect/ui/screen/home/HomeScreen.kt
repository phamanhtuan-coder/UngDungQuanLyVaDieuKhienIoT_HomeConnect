package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.home

import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SharedWithResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.DeviceCard
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.HouseSelection
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.SharedViewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.SpaceCard
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.WeatherInfo
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.Screens
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme

/** Giao diện màn hình Trang chủ (Home Screen)
 * -----------------------------------------
 * Người viết: Phạm Anh Tuấn
 * Ngày viết: 29/11/2024
 * Lần cập nhật cuối: 29/11/2024
 * -----------------------------------------

 * @param modifier Modifier mở rộng để áp dụng cho layout (đã gán giá trị mặc dịnh).
 * @param navController Đối tượng điều khiển điều hướng.
 * @return Scaffold chứa toàn bộ nội dung của màn hình Trang chủ.
 *
 * ---------------------------------------
 */
@Composable
fun HomeScreen(
    sharedViewModel: SharedViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val application = context.applicationContext as Application
    val viewModel = remember {
        SharedWithViewModel(application, context)
    }
    val state by viewModel.sharedWithState.collectAsState()
    var sharedUsers by remember { mutableStateOf<List<SharedWithResponse>?>(emptyList()) }

    LaunchedEffect(Unit) {
        viewModel.fetchSharedWith(22)
    }

    when (state) {
        is SharedWithState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is SharedWithState.Success -> {
           sharedUsers = (state as SharedWithState.Success).sharedWithResponse
            Log.e("SharedWithState.Success", "Thành công")
        }
        is SharedWithState.Error -> {
            val errorMessage = (state as SharedWithState.Error).error
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            }
            Log.e("SharedWithState.Error", "Thất bại")
        }
        else -> {}
    }

    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        Scaffold(containerColor = colorScheme.background,
        modifier = modifier.fillMaxSize(),
        topBar = {
            /*
            * Hiển thị Header
             */
            Header(navController, "Home")
        },
        bottomBar = {
            /*
            * Hiển thị Thanh Menu dưới cùng
             */
            MenuBottom(navController)
        },
        content = {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                WeatherInfo()

                Spacer(modifier = Modifier.height(8.dp))

                // Không gian của nhà đang chọn
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HouseSelection(
                            sharedViewModel = sharedViewModel,
                            //houses = listOf("House 1", "House 2", "House 3"),
                            onManageHouseClicked = { navController.navigate(Screens.HouseManagement.route) },
                            onTabSelected = {id ->

                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Không gian",
                            color = colorScheme.onBackground,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        TextButton(
                            onClick = {
                                navController.navigate(Screens.Spaces.route)
                            },
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = Color.Blue
                            )
                        ) {
                            Text(text = "Xem thêm")
                        }
                    }

                    LazyRow(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(5) {
                            SpaceCard()
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Thiết bị hoạt động
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Thiết bị hoạt động",
                            color = colorScheme.onBackground,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        TextButton(
                            onClick = { /* TODO: Navigate */ },
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = Color.Blue
                            )
                        ) {
                            Text(text = "Xem thêm")
                        }
                    }

                    fun getType(typeId: Int): String {
                        return when (typeId) {
                            1 -> "Báo cháy" // Fire
                            2 -> "Đèn led" // Light
                            else -> "Không xác định"
                        }
                    }

                    sharedUsers?.let { response ->
                        LazyRow(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(response) { device ->
                                DeviceCard(
                                    device,
                                    navController,
                                    deviceName = device.Device.Name,
                                    deviceType = getType(device.Device.TypeID),
                                )
                            }
                        }
                    }
                }
            }
        }
    )
    }
}