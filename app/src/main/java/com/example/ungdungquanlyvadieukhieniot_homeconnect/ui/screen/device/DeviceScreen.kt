package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
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
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.NutHome

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceScreen() {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val isTablet = screenWidthDp >= 600.dp
    var selectedTabIndex by remember { mutableStateOf(0) }

    // Lấy mật độ để tính px
    val cornerRadiusPx = with(LocalDensity.current) { 16.dp.toPx() }

    Scaffold(
        containerColor = Color.LightGray,
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
        content = { innerPadding ->
            val paddingInPx = with(LocalDensity.current) {
                innerPadding.calculateTopPadding().roundToPx()
            }
            if (isTablet) {
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
                                .height(150.dp)
                        ) {
                            // Hộp màu xanh dương
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .offset(y = -9.dp)
                                    .height(110.dp)
                                    .background(color = Color.Blue, shape = RoundedCornerShape(bottomStartPercent = 60))
                                    .zIndex(1f)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxWidth()
                                            .padding(start = 20.dp, top = 10.dp),
                                        horizontalArrangement = Arrangement.SpaceEvenly,
                                        verticalAlignment = Alignment.Bottom
                                    ) {
                                        WeatherInfoItem(
                                            icon = "🌤", // Biểu tượng thời tiết
                                            description = "Sunny",
                                            temperature = null
                                        )
                                        WeatherInfoItem(
                                            icon = null,
                                            description = "Temp Outdoor",
                                            temperature = "28°C"
                                        )
                                        WeatherInfoItem(
                                            icon = null,
                                            description = "Temp Indoor",
                                            temperature = "22°C"
                                        )
                                    }
                                    OutlinedTextField(
                                        value = "", // Giá trị mặc định
                                        onValueChange = { /* Read-only TextField, so no updates here */ },
                                        placeholder = { Text("Địa chỉ", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(start = 20.dp, top = 16.dp, end = 16.dp)
                                            .clickable { /* Handle dropdown expansion */ },
                                        readOnly = true, // Không cho phép nhập tay
                                        trailingIcon = {
                                            Icon(
                                                imageVector = Icons.Default.KeyboardArrowDown,
                                                contentDescription = null,
                                                modifier = Modifier.size(36.dp),
                                                tint = Color.Blue
                                            )
                                        },
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            containerColor = Color.LightGray,
                                            focusedBorderColor = Color.LightGray, // Màu viền khi được focus
                                            unfocusedBorderColor = Color.Black, // Màu viền khi không được focus
                                            disabledBorderColor = Color.LightGray // Màu viền khi bị disabled
                                        ),
                                        shape = RoundedCornerShape(12.dp)
                                    )

                                }
                            }
                            // Hộp màu xanh lá cây với góc lõm
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .offset(y = 1.dp)
                                    .padding(top = 100.dp)
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
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(topEndPercent = 100)) // Clip nội dung ScrollableTabRow
                                    ) {
                                        CustomScrollableTabRow() // Đặt ScrollableTabRow vào đây
                                    }
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.LightGray)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(8.dp),
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
                            Column {
                                SmartLampCard(isTablet)
                                SmartLampCard(isTablet)
                                SmartLampCard(isTablet)
                                SmartLampCard(isTablet)
                            }
                        }
                    }
                }
            }
            else {
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
                                .height(150.dp)
                        ) {
                            // Hộp màu xanh dương
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .offset(y = -9.dp)
                                    .height(110.dp)
                                    .background(color = Color.Blue, shape = RoundedCornerShape(bottomStartPercent = 60))
                                    .zIndex(1f)
                            ) {
                                Box {
                                    OutlinedTextField(
                                        value = "", // Giá trị mặc định
                                        onValueChange = { /* Read-only TextField, so no updates here */ },
                                        placeholder = { Text("tp.Hồ Chí Mính", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 20.dp, top = 16.dp, end = 16.dp)
                                            .clickable { /* Handle dropdown expansion */ },
                                        readOnly = true, // Không cho phép nhập tay
                                        trailingIcon = {
                                            Icon(
                                                imageVector = Icons.Default.KeyboardArrowDown,
                                                contentDescription = null,
                                                modifier = Modifier.size(36.dp),
                                                tint = Color.Blue
                                            )
                                        },
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            containerColor = Color.LightGray,
                                            focusedBorderColor = Color.LightGray, // Màu viền khi được focus
                                            unfocusedBorderColor = Color.Black, // Màu viền khi không được focus
                                            disabledBorderColor = Color.LightGray // Màu viền khi bị disabled
                                        ),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    // Dropdown menu logic tương tự ở đây
                                }
                            }
                            // Hộp màu xanh lá cây với góc lõm
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .offset(y = 1.dp)
                                    .padding(top = 100.dp)
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
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(topEndPercent = 100)) // Clip nội dung ScrollableTabRow
                                    ) {
                                        CustomScrollableTabRow() // Đặt ScrollableTabRow vào đây
                                    }
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.LightGray)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(8.dp),
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
                            Column {
                                SmartLampCard(isTablet)
                                SmartLampCard(isTablet)
                                SmartLampCard(isTablet)
                                SmartLampCard(isTablet)
                            }
                        }
                    }
                }
            }
        }
    )
}


@Composable
fun CustomScrollableTabRow() {
    var selectedTabIndex by remember { mutableStateOf(0) }


    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        edgePadding = 8.dp,
        modifier = Modifier
            .background(Color.LightGray, RoundedCornerShape(16.dp)), // Nền và bo góc
        containerColor = Color.LightGray, // Màu nền bên trong TabRow
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = Color.Blue, // Màu con trỏ
                height = 3.dp // Độ cao con trỏ
            )
        }
    ) {
        (1..10).forEach { index ->
            Tab(
                selected = selectedTabIndex == index - 1,
                onClick = { selectedTabIndex = index - 1 },
                text = {
                    Text(
                        "Tab $index",
                        color = if (selectedTabIndex == index - 1) Color.Black else Color.Gray, // Đổi màu chữ
                    )
                },
                selectedContentColor = Color.Black, // Màu khi được chọn
                unselectedContentColor = Color.Gray // Màu khi không được chọn
            )
        }
    }
}

@Composable
fun SmartLampCard(isTablet: Boolean) {
    val endpadding = if (isTablet) 128.dp else 64.dp
    Card(
        modifier = Modifier
            .wrapContentSize() // Chỉ chiếm không gian nội dung
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White), // Nền màu trắng
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
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
                        .padding(end = 8.dp)
                        .size(40.dp)
                    ,
                    contentAlignment = Alignment.Center
                ) {
                    Switch(checked = false,
                        onCheckedChange = {},
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
                        Row (
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(end = 30.dp)
                                    .size(40.dp)
                                    .background(Color(0xFFFFE082), RoundedCornerShape(8.dp)), // Màu vàng nhạt cho icon
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "\uD83D\uDCA1", fontSize = 20.sp) // Biểu tượng đèn
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(
                                modifier = Modifier.padding(end = 12.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(text = "from", fontSize = 12.sp, color = Color.Gray)
                                Text(text = "8 pm", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            }

                            Box(
                                modifier = Modifier
                                    .padding(end = endpadding)
                                    .width(1.dp) // Độ rộng của đường
                                    .height(50.dp) // Độ cao của đường
                                    .background(Color.Gray) // Màu của đường
                            )
                            Row (
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column (
                                    modifier = Modifier
                                        .padding(end = 12.dp),
                                    horizontalAlignment = Alignment.End,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(text = "to", fontSize = 12.sp, color = Color.Gray)
                                    Text(text = "8 am", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                }


                                Box(
                                    modifier = Modifier
                                        .padding(end = endpadding)
                                        .width(1.dp) // Độ rộng của đường
                                        .height(50.dp) // Độ cao của đường
                                        .background(Color.Gray), // Màu của đường
                                )
                            }

                            if (isTablet) {
                                Row (
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column (
                                        modifier = Modifier
                                            .padding(end = 12.dp),
                                        horizontalAlignment = Alignment.End,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(text = "Điện áp hoạt động", fontSize = 12.sp, color = Color.Gray)
                                        Text(text = "5V", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                    }

                                    Box(
                                        modifier = Modifier
                                            .padding(end = endpadding)
                                            .width(1.dp) // Độ rộng của đường
                                            .height(50.dp) // Độ cao của đường
                                            .background(Color.Gray), // Màu của đường
                                    )
                                }

                                Row (
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column (
                                        modifier = Modifier
                                            .padding(end = 12.dp),
                                        horizontalAlignment = Alignment.End,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(text = "Dòng tiêu thụ", fontSize = 12.sp, color = Color.Gray)
                                        Text(text = "20mA", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                    }

                                    Box(
                                        modifier = Modifier
                                            .width(1.dp) // Độ rộng của đường
                                            .height(50.dp) // Độ cao của đường
                                            .background(Color.Gray), // Màu của đường
                                    )
                                }
                            }

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
    }
}

@Composable
fun WeatherInfoItem(icon: String?, description: String, temperature: String?) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, // Căn giữa
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        icon?.let {
            Text(
                text = it,
                fontSize = 30.sp, // Kích thước lớn cho biểu tượng
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        temperature?.let {
            Text(
                text = it,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        BasicText(
            text = description,
            style = TextStyle(
                fontSize = 20.sp, // Nhỏ hơn cho mô tả
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        )
    }
}
