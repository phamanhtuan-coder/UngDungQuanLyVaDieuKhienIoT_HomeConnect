package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.notification_detail

import android.app.Application
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AlertDetail
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AlertType
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme

/** Giao diện màn hình Chi tiết thông báo (DetailNotification)
 * -----------------------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 10/12/2024
 * Lần cập nhật cuối: 31/12/2024
 * -----------------------------------------
 * @param navController Đối tượng điều khiển điều hướng.
 * @return Scaffold chứa toàn bộ nội dung
 * ---------------------------------------
 */
@Composable
fun DetailNotification(
    navController: NavHostController,
    AlertID: Int
) {

    val context = LocalContext.current
    val application = context.applicationContext as Application
    val viewModel = remember {
        NotificationViewModel(application, context)
    }

    val notificationState by viewModel.alertState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllByUser(AlertID)
    }

    var Alert by remember {
        mutableStateOf(
            AlertDetail(
                AlertID = 0,
                DeviceID = 0,
                SpaceID = 0,
                TypeID = 0,
                Message = "",
                Timestamp = "",
                Status = false,
                AlertTypeID = 0,
                AlertType = AlertType(AlertTypeName = "")
            ),
        )
    }
    var error by remember { mutableStateOf("") }

    when (notificationState) {
        is NotificationState.Success -> {
            val alert = (notificationState as NotificationState.Success).alert
            Log.d("Thành công: ", alert.toString())
            Alert = alert
        }

        is NotificationState.Error -> {
            error = (notificationState as NotificationState.Error).error
        }

        is NotificationState.Loading -> {
            CircularProgressIndicator()
        }

        is NotificationState.Idle -> {
            // Do nothing
        }
    }

    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = colorScheme.background,
            topBar = {
                Header(
                    type = "Notification",
                    title = if (Alert.TypeID == 1) "Chi tiết cảnh báo" else "Chi tiết thông báo",
                    navController = navController
                )
            },
            bottomBar = {
                MenuBottom(navController = navController)
            },
            content = { innerPadding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding),
//                    .padding(bottom = layoutConfig.outerPadding) // Padding linh hoạt
                    verticalArrangement = Arrangement.Top, // Sắp xếp các item theo chiều dọc, bắt đầu từ trên xuống.
                    horizontalAlignment = Alignment.CenterHorizontally // Căn chỉnh các item theo chiều ngang vào giữa.
                ) {
                    item {
                        // Box lớn chứa phần tiêu đề và các thành phần bên trong
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight() // Chiều cao vừa đủ với nội dung bên trong
                                .background(color = colorScheme.background)
                        ) {
                            // Cột chứa các phần tử con
                            Column {
                                // Hộp màu xanh dương bo tròn góc dưới bên trái
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth() // Chiếm toàn bộ chiều rộng
                                        .wrapContentHeight() // Chiều cao vừa đủ với nội dung
                                        .background(
                                            color = colorScheme.primary,
                                            shape = RoundedCornerShape(bottomStart = 40.dp)
                                        )
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(12.dp) // Padding ngoài cùng
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            contentAlignment = Alignment.Center // Căn giữa nội dung trong Box
                                        ) {
                                            Text(
                                                text = Alert.AlertType.AlertTypeName,
                                                fontSize = 32.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = colorScheme.onPrimary
                                            )
                                        }

                                        Spacer(modifier = Modifier.height(16.dp))

                                        // Hàng chứa tiêu đề và thông tin ngày giờ nằm bên trái
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(start = 12.dp) // Padding bên trái cho phần ngày giờ
                                        ) {
                                            // Hàng chứa biểu tượng và tên sự kiện
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Filled.Info,
                                                    contentDescription = "Staus",
                                                    tint = colorScheme.onPrimary
                                                )
                                                Spacer(modifier = Modifier.width(4.dp))

                                                // Tên vị trí
                                                Text(
                                                    text = if (Alert.Status) "Đã xem" else "Chưa xem",
                                                    fontSize = 18.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = colorScheme.onPrimary
                                                )
                                            }

                                            Spacer(modifier = Modifier.height(4.dp))

                                            // Hàng chứa ngày, số sự kiện và ngăn cách bằng dấu chấm
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                // Ngày
                                                Icon(
                                                    imageVector = Icons.Filled.AccessTime,
                                                    contentDescription = "Time",
                                                    tint = colorScheme.onPrimary
                                                )
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Text(
                                                    text = Alert.Timestamp,
                                                    fontSize = 14.sp,
                                                    color = colorScheme.onPrimary
                                                )


                                            }
                                        }

                                    }
                                }
                                // Box chứa góc lõm màu xám
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight() // Chiều cao linh hoạt theo LayoutConfig
                                ) {
                                    // Box màu vàng nhỏ nằm trên góc phải
                                    Box(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .align(Alignment.TopEnd)
                                            .background(color = colorScheme.primary)
                                            .zIndex(1f)  // Z-index thấp hơn
                                    )

                                    // Box màu xám bo tròn góc lõm trên cùng bên phải
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(24.dp)
                                            .background(
                                                color = colorScheme.background,
                                                shape = RoundedCornerShape(topEndPercent = 50)
                                            )
                                            .zIndex(2f), // Z-index cao hơn
                                        contentAlignment = Alignment.Center // Căn Row vào giữa Box
                                    ) {

                                    }
                                }
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .border(
                                        1.dp,
                                        colorScheme.onBackground,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .padding(12.dp)
                            ) {
                                // Hiển thị nội dung thông báo
                                Text(
                                    text = Alert.Message,
                                    fontSize = 16.sp,
                                    color = colorScheme.onBackground,
                                    softWrap = true,
                                    lineHeight = TextUnit.Unspecified,
                                    textAlign = TextAlign.Start
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Nút "ĐỌC THÔNG BÁO"
                            Button(
                                onClick = {
                                    // Todo: Xử lý đọc thông báo
                                    viewModel.readNotification(alertId = Alert.AlertID)
                                },
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(48.dp),
                                shape = RoundedCornerShape(50),
                                enabled = !Alert.Status,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorScheme.primary// Màu nút xóa
                                )
                            ) {
                                Text(
                                    text = "Xác nhận đã đọc",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificationDetailPreview() {
    DetailNotification(navController = rememberNavController(), AlertID = 0)
}