package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceScreen() {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val isTablet = screenWidthDp >= 600.dp

    // Lấy mật độ để tính px
    val cornerRadiusPx = with(LocalDensity.current) { 16.dp.toPx() }

    Scaffold(
        topBar = {
            if (isTablet) {
                // Nội dung topBar cho tablet
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.LightGray)
                        .height(190.dp)
                ) {
                    // Hộp màu xanh dương
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(color = Color.Blue, shape = RoundedCornerShape(bottomStartPercent = 24))
                            .zIndex(1f)
                    )

                    // Hộp màu xanh lá cây với góc lõm
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 150.dp)
                            .width(40.dp)
                            .height(40.dp)
                    ) {
                        // Box màu vàng (ở dưới)
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                                .align(Alignment.TopEnd)
                                .background(color = Color.Blue)
                                .zIndex(1f) // Z-index thấp hơn
                        )

                        // Box màu xanh lá cây (ở trên)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.LightGray, shape = RoundedCornerShape(topEndPercent = 100))
                                .width(40.dp)
                                .height(40.dp)
                                .zIndex(2f) // Z-index cao hơn
                        ) {
                            Column (
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(start = 12.dp),
                                verticalArrangement = Arrangement.Bottom
                            ) {
                                Spacer(modifier = Modifier.height(18.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically // Căn giữa theo chiều dọc
                                ) {
                                    Text(
                                        text = "Số lượng thiết bị:",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                        modifier = Modifier.padding(end = 4.dp) // Thêm khoảng cách giữa Text và Box
                                    )
                                    Box(
                                        modifier = Modifier
                                            .size(24.dp) // Kích thước Box
                                            .background(color = Color.DarkGray, shape = RoundedCornerShape(4.dp)), // Màu nền và góc bo
                                        contentAlignment = Alignment.Center // Căn Text nằm giữa Box
                                    ) {
                                        Text(
                                            text = "4",
                                            color = Color.White, // Màu chữ
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    }

                }
            }
        },
        content = {
            // Nội dung bên dưới
            Column(
                modifier = Modifier
                    .padding(top = 190.dp)
                    .fillMaxSize()
                    .background(color = Color.LightGray)
            ) {
                SmartLampCard()
            }
        }
    )
}

@Preview
@Composable
fun SmartLampCard() {
    Card(
        modifier = Modifier
            .wrapContentSize() // Chỉ chiếm không gian nội dung
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White), // Nền màu trắng
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, // Căn chỉnh khoảng cách giữa các phần tử
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()// Đảm bảo Row chỉ chiếm chiều ngang nội dung
            ) {
                Column {
                    Text(text = "Smart Lamp", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = "Dining Room | Tue Thu", fontSize = 12.sp, color = Color.Gray)
                }
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFF795548), RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "ON", fontSize = 8.sp, color = Color.White, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceBetween, // Căn chỉnh khoảng cách giữa các phần tử
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()// Đảm bảo Row chỉ chiếm chiều ngang nội dung
            ) {
                Row (
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row (
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color(0xFFFFE082), RoundedCornerShape(8.dp)), // Màu vàng nhạt cho icon
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "\uD83D\uDCA1", fontSize = 20.sp) // Biểu tượng đèn
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(horizontalAlignment = Alignment.Start) {
                            Text(text = "from", fontSize = 12.sp, color = Color.Gray)
                            Text(text = "8 pm", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        }
                    }

                    Spacer(modifier = Modifier.width(70.dp))

                    Box(
                        modifier = Modifier
                            .width(1.dp) // Độ rộng của đường
                            .height(50.dp) // Độ cao của đường
                            .background(Color.Gray) // Màu của đường
                    )

                    Spacer(modifier = Modifier.width(70.dp))

                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "to", fontSize = 12.sp, color = Color.Gray)
                        Text(text = "8 am", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.width(35.dp))

                    Box(
                        modifier = Modifier
                            .width(1.dp) // Độ rộng của đường
                            .height(50.dp) // Độ cao của đường
                            .background(Color.Gray) // Màu của đường
                    )

                    Spacer(modifier = Modifier.width(24.dp))

                    Column (
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(Color.LightGray, RoundedCornerShape(4.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "\uD83D\uDDD1", fontSize = 12.sp) // Icon delete
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(Color.LightGray, RoundedCornerShape(4.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "\u270E", fontSize = 12.sp) // Icon edit
                        }
                    }
                }
            }
        }
    }
}
