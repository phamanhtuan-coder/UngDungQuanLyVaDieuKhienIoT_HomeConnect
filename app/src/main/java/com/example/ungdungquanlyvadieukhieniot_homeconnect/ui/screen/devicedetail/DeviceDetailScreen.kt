package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.devicedetail


import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.ungdungquanlyvadieukhieniot_homeconnect.R
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.NutHome

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DeviceDetailScreen() {
    return Scaffold (
        topBar = {
            /*
            * Hiển thị Header
             */
            Header()
        },
        bottomBar = {
            /*
            * Hiển thị Thanh Menu dưới cùng
             */
            MenuBottom()
        },
        floatingActionButton = {
            /*
            * Hiển thị Nút Home
             */
            NutHome()
        },
        floatingActionButtonPosition = FabPosition.Center,
        containerColor = Color.LightGray,
        content = { innerPadding ->
            Box (
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize() // Đảm bảo chiếm toàn bộ không gian
                        .fillMaxHeight()
                        .imePadding() // Tự động thêm khoảng trống khi bàn phím xuất hiện
                        .verticalScroll(rememberScrollState()) // Cho phép cuộn
                        .padding(innerPadding)
                ) {
                    // Nội dung bên dưới
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.LightGray)
                            .height(450.dp)
                    ) {
                        // Hộp màu xanh dương
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y = -8.dp)
                                .height(410.dp)
                                .background(
                                    color = Color.Blue,
                                    shape = RoundedCornerShape(bottomStart = 40.dp)
                                )
                                .zIndex(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(bottomStart = 40.dp))
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    Row (
                                        modifier = Modifier.padding(top = 8.dp, end = 12.dp)
                                    ) {
                                        Column (
                                            modifier = Modifier
                                                .padding(start = 12.dp, end = 12.dp)
                                                .fillMaxWidth()
                                                .background(color = Color.Blue)
                                                .weight(0.2f),
                                            horizontalAlignment = Alignment.Start,
                                            verticalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(text = "Dining Room", color = Color.LightGray)
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Switch(checked = true,
                                                onCheckedChange = { },
                                                thumbContent = {
                                                    if (true) {
                                                        Icon(
                                                            imageVector = Icons.Filled.Check,
                                                            contentDescription = ""
                                                        )
                                                    } else {
                                                        Icon(
                                                            imageVector = Icons.Filled.Close,
                                                            contentDescription = ""
                                                        )
                                                    }
                                                }
                                            )
                                            Row (
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                horizontalArrangement = Arrangement.Start,
                                                verticalAlignment = Alignment.Bottom
                                            ) {
                                                Text("80", fontWeight = FontWeight.Bold, fontSize = 50.sp, color = Color.White)
                                                Text("%", fontWeight = FontWeight.Bold, fontSize = 25.sp, modifier = Modifier.offset(y = -8.dp), color = Color.White)
                                            }
                                            Text("Brightness", color = Color.LightGray, fontSize = 20.sp)
                                            Spacer(modifier = Modifier.height(8.dp))
                                        }
                                        Image(
                                            painter = painterResource(id = R.drawable.lamp),
                                            modifier = Modifier.size(150.dp),
                                            contentDescription = ""
                                        )
                                    }
                                    Column (
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 12.dp, end = 12.dp)
                                            .background(color = Color.Blue)
                                    ) {
                                        Text("Insensity", color = Color.LightGray)
                                        Row (
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Center, // Căn giữa các thành phần theo chiều ngang
                                            verticalAlignment = Alignment.CenterVertically // Căn giữa theo chiều dọc
                                        ){
                                            Image(
                                                painter = painterResource(id = R.drawable.bulboff),
                                                modifier = Modifier.padding(end = 8.dp).size(24.dp).align(Alignment.CenterVertically),
                                                contentDescription = ""
                                            )
                                            Slider(value = 80f, onValueChange = {},
                                                steps = 50, valueRange = 0f..100f, modifier = Modifier.width(300.dp))
                                            Image(
                                                painter = painterResource(id = R.drawable.bulb),
                                                modifier = Modifier.padding(start = 8.dp).size(24.dp).align(Alignment.CenterVertically),
                                                contentDescription = ""
                                            )
                                        }
                                    }
                                    Box(
                                        modifier = Modifier
                                            .padding(start = 12.dp, end = 12.dp)
                                            .fillMaxWidth() // Chiều rộng đầy đủ
                                            .height(1.dp) // Chiều cao của đường (mỏng)
                                            .background(Color.LightGray) // Màu của đường
                                    )
                                    Column (
                                        modifier = Modifier
                                            .padding(top = 24.dp, start = 12.dp, end = 12.dp)
                                            .fillMaxSize(),
                                        horizontalAlignment = Alignment.Start,
                                        verticalArrangement = Arrangement.Top
                                    ) {
                                        Text("Usages", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 15.sp)
                                        Row (
                                            modifier = Modifier
                                                .padding(top = 12.dp)
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text("Use Today", color = Color.LightGray)
                                            Row {
                                                Text("50", fontSize = 20.sp, modifier = Modifier.offset(y= -5.dp), color = Color.LightGray)
                                                Spacer(modifier = Modifier.width(2.dp))
                                                Text("watt", color = Color.LightGray)
                                            }
                                        }
                                        Row (
                                            modifier = Modifier
                                                .padding(top = 12.dp)
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text("Use Week", color = Color.LightGray)
                                            Row {
                                                Text("500", fontSize = 20.sp, modifier = Modifier.offset(y= -5.dp), color = Color.LightGray)
                                                Spacer(modifier = Modifier.width(2.dp))
                                                Text("kwh", color = Color.LightGray)
                                            }
                                        }
                                        Row (
                                            modifier = Modifier
                                                .padding(top = 12.dp)
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text("Use Month", color = Color.LightGray)
                                            Row {
                                                Text("5000", fontSize = 20.sp, modifier = Modifier.offset(y= -5.dp), color = Color.LightGray)
                                                Spacer(modifier = Modifier.width(2.dp))
                                                Text("kwh", color = Color.LightGray)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        // Hộp màu xanh lá cây với góc lõm
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y = 1.dp)
                                .padding(top = 400.dp)
                                .width(40.dp)
                                .height(50.dp)
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
                                    .background(
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(topEndPercent = 50)
                                    )
                                    .width(50.dp)
                                    .height(50.dp)
                                    .zIndex(2f) // Z-index cao hơn
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(topEndPercent = 100)) // Clip nội dung ScrollableTabRow
                                ) {

                                }
                            }
                        }
                    }
                }
            }
        }
    )
}
