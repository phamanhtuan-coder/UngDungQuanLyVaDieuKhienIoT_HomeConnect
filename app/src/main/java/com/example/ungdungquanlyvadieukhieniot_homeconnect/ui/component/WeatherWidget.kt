package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalTime

/**
 * Weather Information UI
 * -----------------------------------------
 * Author: Phạm Anh Tuấn
 * Created: 11/12/2024
 * Last Updated: 13/12/2024
 * -----------------------------------------
 */

@Preview(showBackground = true)
@Composable
fun WeatherInfo() {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val isTablet = screenWidth > 600

    val iconThoiGian =
        if (isDayTime()) Icons.Filled.WbSunny else Icons.Filled.NightsStay
    val iconThoiTiet = Icons.Filled.WbSunny
    val thoiGianHienTai = "Sun, 12 Dec 2024 10:00 AM"
    val thoiTietHienTai = "Sunny"
    val nhietDoHienTai = "25°C"
    val viTriHienTai = "Hanoi, Vietnam"
    val doAm = "80"
    val tamNhin = "10"
    val tocDoGio = "5"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF6EC6FF), Color(0xFF2196F3))
                ),
                shape = RoundedCornerShape(bottomStartPercent = 25, bottomEndPercent = 25)
            )
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(
                    color = Color.White.copy(alpha = 0.7f),
                    shape = RoundedCornerShape(25.dp)
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = iconThoiTiet,
                    contentDescription = null,
                    tint = Color(0xFF424242),
                    modifier = Modifier.size(if (isTablet) 80.dp else 40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = thoiGianHienTai,
                        color = Color(0xFF424242),
                        fontSize = 14.sp
                    )
                    Text(
                        text = thoiTietHienTai,
                        color = Color(0xFF424242),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = viTriHienTai,
                        color = Color(0xFF424242),
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = nhietDoHienTai,
                        color = Color(0xFF424242),
                        fontSize = if (isTablet) 40.sp else 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = iconThoiGian,
                        contentDescription = null,
                        tint = Color(0xFF424242),
                        modifier = Modifier.size(if (isTablet) 32.dp else 28.dp)
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp),
                thickness = 1.dp,
                color = Color(0xFFBDBDBD)
            )
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 100.dp),
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalArrangement = Arrangement.Center
            ) {
                item {
                    WeatherWidget(
                        icon = Icons.Filled.WaterDrop,
                        value = "$doAm%",
                        label = "Độ ẩm",
                        isTablet = isTablet
                    )
                }
                item {
                    WeatherWidget(
                        icon = Icons.Filled.Visibility,
                        value = "$tamNhin km",
                        label = "Tầm nhìn",
                        isTablet = isTablet
                    )
                }
                item {
                    WeatherWidget(
                        icon = Icons.Filled.Air,
                        value = "$tocDoGio km/h",
                        label = "Phong tốc",
                        isTablet = isTablet
                    )
                }
            }

        }
    }
}

/**
 * Weather Widget
 * Người tạo: Phạm Anh Tuấn
 * Ngày tạo: 11/12/2024
 * Lần cập nhật cuối: 13/12/2024
 * ---------------------------------------
 * @param icon: Icon of the weather widget
 * @param value: Value of the weather widget
 * @param label: Label of the weather widget
 * @param isTablet: Boolean value to check if the device is a tablet
 * @return Card chứa thông tin thời tiết
 * ---------------------------------------
 */
@Composable
fun WeatherWidget(icon: ImageVector, value: String, label: String, isTablet: Boolean) {
    return Card(
        modifier = Modifier
            .padding(if (isTablet) 12.dp else 5.dp)
            .width(if (isTablet) 150.dp else 100.dp)
            .height(if (isTablet) 80.dp else 45.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.8f)
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier
                .background(
                    color = Color.White.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(15.dp)
                )
                .fillMaxSize()
                .padding(horizontal = if (isTablet) 16.dp else 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            // Icon Box
            Box(
                modifier = Modifier
                    .size(if (isTablet) 45.dp else 22.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color(0xFF424242),
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = label,
                    color = Color(0xFF424242),
                    fontSize = if (isTablet) 24.sp else 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = value,
                    color = Color(0xFF424242),
                    fontSize = if (isTablet) 20.sp else 12.sp,
                )
            }
        }
    }
}


// Kiểm tra thời gian hiện tại có phải là ban ngày hay không
fun isDayTime(): Boolean {
    val hour = LocalTime.now().hour
    return hour in 6..18
}