package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalTime.now

/**
 * Giao diện thông tin thời tiết
 * -----------------------------------------
 * Người viết:
 * - Phạm Anh Tuấn
 * Ngày viết:
 * - 11/12/2024
 * Lần cập nhật cuối:
 * - 11/12/2024
 * -----------------------------------------
 * Input:
 * - Không có
 *
 * Output:
 * - Card chứa thông tin thời tiết
 * ---------------------------------------
 *
 */

@Preview(showBackground = true)
@Composable
fun WeatherInfo() {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val isTablet = screenWidth > 600

    // Temp
    val iconThoiGian = if (isDayTime()) Icons.Filled.WbSunny else Icons.Filled.NightsStay // Ban ngày/ban đêm
    val iconThoiTiet = Icons.Filled.WbSunny // Logic hình ảnh thời tiết
    val thoiGianHienTai =  "Sun, 12 Dec 2024 10:00 AM" // Thời gian hiện tại
    val thoiTietHienTai = "Nắng" // Thời tiết hiện tại
    val nhietDoHienTai = "25°C" // Nhiệt độ thực
    val viTriHienTai = "Hanoi, Vietnam" //vị trí thực
    val doAm= "80" // Độ ẩm, tầm nhìn, tốc độ gió
    val tamNhin= "10" // Độ ẩm, tầm nhìn, tốc độ gió
    val tocDoGio= "5" // Độ ẩm, tầm nhìn, tốc độ gió

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Blue, shape = RoundedCornerShape(
                bottomStartPercent = 25,
                bottomEndPercent = 25
            ))

        ,


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = iconThoiTiet,
                contentDescription = null,
                tint = Color.Gray,
                modifier =  Modifier.size( if(isTablet) 80.dp  else 40.dp)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = thoiGianHienTai,
                    color = Color.Black,
                    fontSize = 16.sp
                )
                Text(
                    text = thoiTietHienTai,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = viTriHienTai,
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = nhietDoHienTai,
                    color = Color.Black,
                    fontSize = if (isTablet) 40.sp else 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = iconThoiGian,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = if(isTablet) Modifier.size(32.dp) else Modifier.size(28.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 8.dp),
            thickness = 1.dp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            WeatherWidget(
                icon = Icons.Filled.WaterDrop,
                value = doAm+ "%",
                label = "Độ ẩm",
                isTablet = isTablet
            )

            WeatherWidget(
                icon = Icons.Filled.Visibility,
                value = tamNhin+" km",
                label = "Tầm nhìn",
                isTablet = isTablet
            )

            WeatherWidget(
                icon = Icons.Filled.Air,
                value = tocDoGio+ " km/h",
                label = "Tốc độ gió",
                isTablet = isTablet
            )
        }
    }
}

@Composable
fun WeatherWidget(icon: ImageVector, value: String, label: String, isTablet: Boolean) {
    Card(
        modifier = Modifier
            .width(if (isTablet) 240.dp else 130.dp)
            .padding(8.dp)
            .background(Color.Transparent,
                shape = RoundedCornerShape(25.dp),
                ),

        shape = RoundedCornerShape(25.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.2f) // Glass effect
        )
    ) {
        if(isTablet) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(36.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = label,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = value,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }

            }
        }else{
        Column(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
                    .size(24.dp)
            )
            Text(
                text = label,
                color = Color.White,
                fontSize =  14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = value,
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }

}
}

fun isDayTime(): Boolean {
    val hour = now().hour
    return hour in 6..18
}