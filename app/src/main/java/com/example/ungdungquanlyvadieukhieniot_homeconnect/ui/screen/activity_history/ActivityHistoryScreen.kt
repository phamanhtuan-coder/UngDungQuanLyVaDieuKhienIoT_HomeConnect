package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.activity_history


import android.app.Application
import android.content.res.Configuration
import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.FormattedLightDetails
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.FormattedLog
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.FormattedSensorDetails
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LogDetailNavArg
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.Screens
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme
import com.google.gson.Gson
import java.util.Locale

/** Giao diện màn hình Lịch sử hoạt động (Activity History Screen)
 * -----------------------------------------
 * Người viết: Phạm Xuân Nhân
 * Ngày viết: 11/12/2024
 * Lần cập nhật cuối: 12/12/2024
 * -----------------------------------------
 * Input: Không có
 *
 * Output: Column chứa các thành phần giao diện của màn hình Lịch sử hoạt động
 *
 *Người cập nhật: Nguyễn Thanh Sang
 *Ngày viết: 31/12/2024
 *---------------------
 * - Thêm phần navigation
 * - Sửa giao diện Lịch sử hoạt động
 */

@Composable
fun ActivityHistoryScreen(
  navController: NavHostController,
  deviceId: Int? = -1
) {
    AppTheme {
        val title by remember { mutableStateOf("Lịch sử hoạt động") }
        val colorScheme = MaterialTheme.colorScheme
        val configuration = LocalConfiguration.current
        val screenWidthDp = configuration.screenWidthDp.dp

        // Xác định xem nếu chúng ta đang ở chế độ ngang
        val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        //  kích thước phản hồi
        val horizontalPadding = when {
            screenWidthDp < 360.dp -> 8.dp
            screenWidthDp < 600.dp -> 16.dp
            else -> 32.dp
        }
        val context = LocalContext.current
        val application = context.applicationContext as Application
        val viewModel = remember {
            ActivityHistoryViewModel(application, context)
        }
        val logsState by viewModel.logsState.collectAsState()
        Log.d("ActivityHistory", "DeviceId received: $deviceId")

        LaunchedEffect(deviceId) {
            if (deviceId != -1) {
                if (deviceId != null) {
                    viewModel.getDeviceLogs(deviceId)
                }
            }
        }


        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = colorScheme.background,
            topBar = {
                Header(
                    navController = navController,
                    type = "Back",
                    title = title,
                )


            },
            bottomBar = {
                MenuBottom(navController)
            },
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(colorScheme.background),
                )
                {
                    Box(modifier = Modifier.align(Alignment.Center)) {
                        when (logsState) {
                            is LogsState.Loading -> {
                                Log.d("ActivityHistory", "Loading state")
                            }
                            is LogsState.Success -> {
                                val logs = (logsState as LogsState.Success).logs
                                Log.d("ActivityHistory", "Success state with ${logs.size} logs")
                                LazyColumn( modifier = Modifier
                                    .padding(horizontal = horizontalPadding)
                                    .heightIn(max = 700.dp)
                                    .widthIn(max = 600.dp),
                                    verticalArrangement = if (isLandscape) Arrangement.Center else Arrangement.SpaceBetween) {
                                    items(logs) { log ->
                                        CardActivityHistory(
                                            navController = navController,
                                            log = log,
                                            onItemClick = {
                                                val details = when (log.deviceType) {
                                                    1 -> { // Báo cháy
                                                        val sensorDetails = log.details as? FormattedSensorDetails
                                                        "Gas: ${sensorDetails?.gas}ppm\nNhiệt độ: ${sensorDetails?.temperature}°C\nĐộ ẩm: ${sensorDetails?.humidity}%"
                                                    }
                                                    2 -> { // Đèn
                                                        val lightDetails = log.details as? FormattedLightDetails
                                                        "Trạng thái: ${if (lightDetails?.powerStatus == true) "Bật" else "Tắt"}"
                                                    }
                                                    else -> "Không có thông tin chi tiết"
                                                }

                                                val logDetail = LogDetailNavArg(
                                                    logId = log.id,
                                                    deviceName = log.deviceName,
                                                    deviceType = log.deviceType,
                                                    timestamp = log.timestamp,
                                                    details = details
                                                )

                                                val jsonString = Uri.encode(Gson().toJson(logDetail))
                                                navController.navigate(Screens.ActivityHistoryDetail.createRoute(jsonString))
                                            }
                                        )
                                    }
                                }
                            }
                            is LogsState.Error -> {
                                Log.e("ActivityHistory", "Error state:")
                            }
                            else -> {}
                        }

                    }
                }
            }
        )
    }
}
@Composable
fun CardActivityHistory(
    navController: NavHostController,
    log: FormattedLog,  // Thay đổi tham số để nhận FormattedLog
    onItemClick: () -> Unit
) {
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        Box(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable(onClick = onItemClick)
                .background(colorScheme.secondary, RoundedCornerShape(8.dp))
                .border(2.dp, colorScheme.onBackground, RoundedCornerShape(8.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = log.deviceName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.onSecondary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = formatDate(log.timestamp),
                    fontSize = 14.sp,
                    color = colorScheme.onSecondary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = getDetailString(log),
                    fontSize = 14.sp,
                    color = colorScheme.onSecondary
                )
            }
        }
    }
}

fun formatDate(dateString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val outputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        outputFormat.timeZone = TimeZone.getDefault()
        val date = inputFormat.parse(dateString)
        outputFormat.format(date!!)
    } catch (e: Exception) {
        dateString
    }
}
private fun getDetailString(log: FormattedLog): String {
    return when (log.deviceType) {
        1 -> { // Báo cháy
            val sensorDetails = log.details as? FormattedSensorDetails
            "Gas: ${sensorDetails?.gas}ppm, Nhiệt độ: ${sensorDetails?.temperature}°C"
        }
        2 -> { // Đèn
            val lightDetails = log.details as? FormattedLightDetails
            "Trạng thái: ${if (lightDetails?.powerStatus == true) "Bật" else "Tắt"}"
        }
        else -> "Không có thông tin chi tiết"
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ActivityHistoryScreenPreview() {
    ActivityHistoryScreen(navController = rememberNavController())
}