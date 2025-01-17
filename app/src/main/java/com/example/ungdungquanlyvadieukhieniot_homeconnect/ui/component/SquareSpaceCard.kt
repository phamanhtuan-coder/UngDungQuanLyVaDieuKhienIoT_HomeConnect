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
 * Giao diện Thẻ phòng
 * -----------------------------------------
 * - Người viết: Phạm Anh Tuấn
 * - Ngày viết: 29/11/2024
 * - Lần cập nhật cuối: 11/12/2024
 * -----------------------------------------
 * @param temperature: Nhiệt độ phòng
 * @param icon: Hình ảnh biểu tượng phòng
 * @param spaceName: Tên phòng
 * @param deviceCount: Số lượng thiết bị kết nối
 * @return Card chứa thông tin phòng
 * ---------------------------------------
 *
 */
@Composable
fun SpaceCard(
    space: SpaceResponse,
    viewModel: SquareSpaceCardViewModel = viewModel()

) {
    var temperature by remember { mutableStateOf(25) }
    var icon by remember { mutableStateOf(Icons.Default.Home) }
    var spaceName by remember { mutableStateOf(space.Name) }
    var deviceCount by remember { mutableStateOf(0) }


    LaunchedEffect(key1 = space.SpaceID) {
        viewModel.getSpaceDetail(space.SpaceID)
    }
    val spaceDetailState by viewModel.spaceDetailState.collectAsState()
    var spaceDetail by remember { mutableStateOf<SpaceState>(SpaceState.Idle) }

    when (spaceDetailState) {
        is SpaceState.Success -> {
            spaceDetail = (spaceDetailState as SpaceState.Success)
            temperature = randomInt(25, 40) //Todo: Lấy dữ liệu sau này
            icon = Icons.Default.Home //Todo: Lấy dữ liệu sau này
            spaceName = (spaceDetailState as SpaceState.Success).space.Name
            deviceCount = randomInt(0, 10) //Todo: Lấy dữ liệu sau này
        }

        is SpaceState.Error -> {
            val error = (spaceDetailState as SpaceState.Error).error
            Log.e("SpaceCard", "Error fetching space detail: $error")
        }

        is SpaceState.Loading -> {
            Log.d("SpaceCard", "Loading space detail...")
        }

        is SpaceState.Idle -> {
        }
    }


    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = if (temperature >= 30) Color(0xFFE91E63) else if (temperature <= 10) Color(
                                    0xFF2196F3
                                ) else Color(0xFF4CAF50),
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

                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color(0xFF2196F3),
                        modifier = Modifier
                            .size(48.dp)
                            .padding(8.dp)
                    )


                    Text(
                        text = spaceName,
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
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

fun randomInt(from: Int, to: Int): Int {
    return (from until to).random()
}