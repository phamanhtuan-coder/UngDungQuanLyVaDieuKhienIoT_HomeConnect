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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SharedWithResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SpaceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.DeviceCard
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.HouseSelection
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.SharedViewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.SpaceCard
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.WeatherInfo
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.Screens
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.DeviceViewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.SpaceState
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.profile.InfoProfileState
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.profile.ProfileScreenViewModel
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
    var selectedTabIndex by remember { mutableStateOf(0) }

    val context = LocalContext.current
    val application = context.applicationContext as Application
    val viewModelDevice = remember {
        DeviceViewModel(application, context)
    }

    var spaces by remember { mutableStateOf<List<SpaceResponse>>(emptyList()) } // Lắng nghe danh sách thiết bị
    val spacesListState by viewModelDevice.spacesListState.collectAsState()

    when (spacesListState) {
        is SpaceState.Error -> {
            Log.d("Error", (spacesListState as SpaceState.Error).error)
        }

        is SpaceState.Idle -> {
            //Todo
        }

        is SpaceState.Loading -> {
            //Todo
        }

        is SpaceState.Success -> {
            spaces = (spacesListState as SpaceState.Success).spacesList
            Log.d("List Device", spaces.toString())

            // Sử dụng LaunchedEffect để gọi loadDevices một lần khi spaces thay đổi
            if (selectedTabIndex == 0 && spaces.isNotEmpty()) {
                LaunchedEffect(spaces.first().SpaceID) {
                    viewModelDevice.loadDevices(spaces.first().SpaceID)
                }
            }
        }
    }
    val viewModelProfile = remember {
        ProfileScreenViewModel(application, context)
    }
    var userId by remember { mutableStateOf(0) }
    val infoProfileState by viewModelProfile.infoProfileState.collectAsState()
    LaunchedEffect(Unit) {
        viewModelProfile.getInfoProfile()
    }
    when (infoProfileState) {
        is InfoProfileState.Loading -> {

        }

        is InfoProfileState.Success -> {
            userId = (infoProfileState as InfoProfileState.Success).user.UserID
            Log.d("InfoProfileState", userId.toString())
        }

        is InfoProfileState.Error -> {
            Log.d("InfoProfileState", (infoProfileState as InfoProfileState.Error).error)
        }

        else -> {}
    }


    val viewModel = remember {
        SharedWithViewModel(application, context)
    }
    val state by viewModel.sharedWithState.collectAsState()
    var sharedUsers by remember { mutableStateOf<List<SharedWithResponse>?>(emptyList()) }

    LaunchedEffect(userId) {
        viewModel.fetchSharedWith(userId)
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
                            onManageHouseClicked = { navController.navigate(Screens.HouseManagement.route) },
                            onTabSelected = {id ->
                                viewModelDevice.getSpacesByHomeId(id)
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
                        items(spaces.size) {
                            SpaceCard(
                                space = spaces[it]
                            )
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
                            text = "Thiết bị được chia sẻ",
                            color = colorScheme.onBackground,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        TextButton(
                            onClick = { /* TODO: Navigate tới màn hình toàn b thiết bị */ },
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = Color.Blue
                            )
                        ) {
                            Text(text = "")
                        }
                    }

                    fun getType(typeId: Int): String {
                        return when (typeId) {
                            1 -> "Báo cháy" // Fire
                            2 -> "Đèn led" // Light
                            else -> "Không xác định"
                        }
                    }
                    Log.d("SharedUsers", sharedUsers.toString())
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