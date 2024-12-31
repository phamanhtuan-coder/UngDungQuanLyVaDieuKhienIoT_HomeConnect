package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.notification_detail

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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
    navController: NavHostController
) {
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        val titleNotification = remember { mutableStateOf("Tên thông báo") }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = colorScheme.background,
            topBar = {
                Header(
                    type = "Notification",
                    title = "Chi tiết thông báo",
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
                                        // Tiêu đề "TÊN THÔNG BÁO" nằm giữa toàn màn hình
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            contentAlignment = Alignment.Center // Căn giữa nội dung trong Box
                                        ) {
                                            Text(
                                                text = titleNotification.value,
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
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Filled.DateRange,
                                                    contentDescription = "Calendar Icon",
                                                    tint = Color.Black
                                                )
                                                Spacer(modifier = Modifier.width(4.dp))

                                                // Tên sự kiện
                                                Text(
                                                    text = "QCITE Festival",
                                                    fontSize = 18.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.Black
                                                )
                                            }

                                            Spacer(modifier = Modifier.height(4.dp))

                                            // Hàng chứa ngày, số sự kiện và ngăn cách bằng dấu chấm
                                            Row(
                                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                // Ngày
                                                Text(
                                                    text = "March 11, 2024",
                                                    fontSize = 14.sp,
                                                    color = Color.Gray
                                                )

                                                // Dấu chấm ngăn cách
                                                Text(
                                                    text = "•",
                                                    fontSize = 14.sp,
                                                    color = Color.Gray
                                                )

                                                // Số sự kiện
                                                Text(
                                                    text = "5 Events",
                                                    fontSize = 14.sp,
                                                    color = Color.Gray
                                                )
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(8.dp))
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
                                            .background(color = Color.Blue)
                                            .zIndex(1f)  // Z-index thấp hơn
                                    )

                                    // Box màu xám bo tròn góc lõm trên cùng bên phải
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(24.dp)
                                            .background(
                                                color = Color.LightGray,
                                                shape = RoundedCornerShape(topEndPercent = 50)
                                            )
                                            .zIndex(2f), // Z-index cao hơn
                                        contentAlignment = Alignment.Center // Căn Row vào giữa Box
                                    ) {}
                                }
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            NotificationDetailScreen(
                                content = "Nội dung thông báo chi tiết sẽ được hiển thị tại đây. Người dùng có thể đọc thông báo và lựa chọn xóa nếu cần thiết.",
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun NotificationDetailScreen(
    content: String,
) {
    Column (
        modifier = Modifier
            .width(500.dp)
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        // Hộp chứa nội dung thông báo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .border(1.dp, Color(0xFFFFA000), shape = RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            // Hiển thị nội dung thông báo
            Text(
                text = content,
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Start
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nút "XÓA THÔNG BÁO"
        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFA000) // Màu nút xóa
            )
        ) {
            Text(
                text = "XÓA THÔNG BÁO",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificationDetailPreview() {
    DetailNotification(navController = rememberNavController())
}