package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.api.RetrofitClient
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


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
    val context = LocalContext.current

    val weatherData = remember { mutableStateOf<WeatherResponse?>(null) }
    val apiKey = "93231902cfd746368a9121412251701" // Thay bằng API Key thực tế
    val locationState = remember { mutableStateOf("Hanoi") } // Giá trị mặc định

    // Lấy vị trí hiện tại
    LaunchedEffect(Unit) {
        getCurrentLocation(context) { location ->
            locationState.value = location // Cập nhật vị trí hiện tại
        }
    }

    // Gọi API
    LaunchedEffect(Unit) {
        RetrofitClient.instance.getCurrentWeather(apiKey, locationState.toString())
            .enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if (response.isSuccessful) {
                        weatherData.value = response.body()
                    } else {
//                        Log.e("WeatherAPI", "API Error: ${response.code}")
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Log.e("WeatherAPI", "Network Error: ${t.message}")
                }
            })
    }

    weatherData.value?.let { data ->
        val weatherCondition = data.current.condition.text ?: "Unknown"
        val iconThoiTiet = getWeatherIcon(weatherCondition)
        val iconThoiGian =
            if (isDayTime()) Icons.Filled.WbSunny else Icons.Filled.NightsStay
        val thoiGianHienTai = formatDateTime(data.location.localtime)
        val thoiTietHienTai = data.current.condition.text
        val nhietDoHienTai = "${data.current.temp_c}°C"
        val viTriHienTai = "${data.location.name}, ${data.location.country}"
        val doAm = "${data.current.humidity}"
        val tamNhin = "${data.current.vis_km}"
        val tocDoGio = "${data.current.wind_kph}"

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
                        imageVector = iconThoiGian,
                        contentDescription = null,
                        tint = Color(0xFF424242),
                        modifier = Modifier.size(if (isTablet) 80.dp else 40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = thoiGianHienTai.toString(),
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
                            imageVector = iconThoiTiet,
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
                            label = "Phong",
                            isTablet = isTablet
                        )
                    }
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

// Hàm trả về biểu tượng dựa trên trạng thái thời tiết
fun getWeatherIcon(weatherCondition: String): ImageVector {
    return when (weatherCondition.lowercase()) {
        "sunny", "clear" -> Icons.Filled.WbSunny
        "partly cloudy", "cloudy" -> Icons.Filled.Visibility
        "rain", "showers", "drizzle" -> Icons.Filled.WaterDrop
        "snow", "sleet" -> Icons.Filled.NightsStay // Sử dụng icon phù hợp
        "thunderstorm" -> Icons.Filled.Air
        else -> Icons.Filled.Visibility // Biểu tượng mặc định
    }
}

fun getCurrentLocation(context: Context, onLocationResult: (String) -> Unit) {
    val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    // Kiểm tra quyền truy cập vị trí
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        onLocationResult("Permission Denied")
        return
    }

    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
        if (location != null) {
            val latitude = location.latitude
            val longitude = location.longitude
            onLocationResult("$latitude,$longitude") // Kết quả trả về
        } else {
            onLocationResult("Unable to get location")
        }
    }.addOnFailureListener {
        onLocationResult("Failed to fetch location")
    }
}

fun formatDateTime(input: String): String {
    // Định dạng đầu vào (theo API trả về)
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    // Định dạng đầu ra (mong muốn)
    val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    // Phân tích chuỗi ngày giờ và định dạng lại
    val dateTime = LocalDateTime.parse(input, inputFormatter)
    return dateTime.format(outputFormatter)
}