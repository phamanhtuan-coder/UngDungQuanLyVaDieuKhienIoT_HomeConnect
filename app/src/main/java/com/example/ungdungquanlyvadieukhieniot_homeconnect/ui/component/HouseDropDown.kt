package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component

import android.app.Application
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.HouseResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SpaceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.access_point_connection.isTablet
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.DeviceViewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.SpaceState
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme

/**
 * Thanh dropdown chọn House
 * -----------------------------------------
 * - Người viết: Phạm Anh Tuấn
 * - Ngày viết: 10/12/2024
 * - Lần cập nhật cuối: 11/12/2024
 *
 * -----------------------------------------
 *
 * @param houses: Danh sách các House
 * @param onManageHouseClicked: Hàm xử lý khi click vào nút "Quản lý danh sách nhà"
 * @return Column chứa thanh dropdown chọn House
 *
 * ---------------------------------------
 *
 */
@Composable
fun HouseSelection(
    sharedViewModel: SharedViewModel,
    onTabSelected: (Int) -> Unit,
    onManageHouseClicked: () -> Unit = {} // Callback for managing house
) {
    AppTheme {
        val context = LocalContext.current
        val application = context.applicationContext as Application
        val viewModel = remember {
            HouseDropDownViewModel(application, context)
        }

        var houses by remember { mutableStateOf<List<HouseResponse>>(emptyList()) } // Lắng nghe danh sách thiết bị
        val housesListState by viewModel.houseListState.collectAsState()
        LaunchedEffect(1) {
//        selectedSpaceId?.let { spaceId ->
//            viewModel.loadDevices(spaceId) // Tải danh sách thiết bị khi Space thay đổi
//        }
            viewModel.getListHouse()
        }
        var selectedItem by remember {
            mutableStateOf<HouseResponse>(houses.firstOrNull() ?: HouseResponse(HouseID = 0, Name = "Không có nhà"))
        }

        when(housesListState){
            is HouseState.Error ->{
                Log.d("Error",  (housesListState as SpaceState.Error).error)
            }
            is HouseState.Idle ->{
                //Todo
            }
            is HouseState.Loading -> {
                //Todo
            }
            is HouseState.Success -> {
                houses = (housesListState as HouseState.Success).houseList
                Log.d("List House", houses.toString())

                // Kiểm tra nếu danh sách `houses` trống
                if (houses.isEmpty()) {
                    selectedItem = HouseResponse(HouseID = -1, Name = "Không có nhà")
                    sharedViewModel.setHouseId(selectedItem.HouseID)
                } else {
                    selectedItem = houses.first()
                    onTabSelected(selectedItem.HouseID)
                    sharedViewModel.setHouseId(selectedItem.HouseID)
                }
            }
        }

        var isDropdownExpanded by remember { mutableStateOf(false) }
        val colorScheme = MaterialTheme.colorScheme
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorScheme.surface)
                    .clickable {
                        isDropdownExpanded = !isDropdownExpanded
                    }
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedItem.Name,
                        color = colorScheme.onSurface,
                        fontSize = 18.sp
                    )
                    Icon(
                        imageVector = if (isDropdownExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        tint = colorScheme.primary
                    )
                }
            }

            if (isDropdownExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(colorScheme.surfaceVariant)
                        .padding(vertical = 4.dp)
                ) {
                    houses.forEach { house ->
                        Text(
                            text = house.Name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedItem = house
                                    onTabSelected(house.HouseID)
                                    sharedViewModel.setHouseId(selectedItem.HouseID)
                                    isDropdownExpanded = false
                                }
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            fontSize = 16.sp,
                            color = colorScheme.onSurface
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable {
                                onManageHouseClicked()
                            }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(if (isTablet()) 56.dp else 48.dp)
                                .background(colorScheme.primary, RoundedCornerShape(18.dp)),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Quản lý danh sách nhà",
                                color = colorScheme.onPrimary,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

            }
        }
    }
}