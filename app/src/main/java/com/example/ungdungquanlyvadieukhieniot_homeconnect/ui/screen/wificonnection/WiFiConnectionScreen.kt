package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.wificonnection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material.icons.filled.ArrowForwardIos // Biểu tượng mũi tên
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.zIndex
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.NutHome

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun WiFiConnectionScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { Header() },
        bottomBar = { MenuBottom() },
        floatingActionButton = { NutHome() },
        floatingActionButtonPosition = FabPosition.Center,
        content = { innerPadding ->
            val screenWidth = LocalConfiguration.current.screenWidthDp.dp
            val screenHeight = LocalConfiguration.current.screenHeightDp.dp

            // Kích thước
            val outerPadding = 6.dp + screenWidth * 0.05f
            val textFieldSpacing = 8.dp + screenHeight * 0.01f
            val headingFontSize = (12 + screenWidth.value * 0.06f).sp
            val textFontSize = (3 + screenWidth.value * 0.035f).sp
            val contentWidth = (120.dp + screenWidth) * 0.7f

            var showDialog by remember { mutableStateOf(false) }

            // Thay thế Column bằng LazyColumn
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = outerPadding)
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Tiêu đề
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(color = Color.LightGray)
                            .height(450.dp)
                    ) {
                        // Hộp màu xanh dương
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight() // Chiều cao vừa với nội dung bên trong
                                .background(color = Color.LightGray)
                        ) {
                            // Hộp màu xanh dương
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .background(
                                        color = Color.Blue,
                                        shape = RoundedCornerShape(bottomStart = 40.dp)
                                    )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 8.dp), // Canh lề các thành phần
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    // Tiêu đề
                                    Text("KẾT NỐI", fontSize = 24.sp, color = Color.White)
                                    Text("ĐIỂM TRUY CẬP", fontSize = 24.sp, color = Color.White)

                                    Spacer(modifier = Modifier.height(8.dp))

                                    // TextField 1
                                    OutlinedTextField(
                                        value = "",
                                        onValueChange = {},
                                        label = { Text("ID thiết bị của bạn là:") },
                                        singleLine = true,
                                        modifier = Modifier.fillMaxWidth(0.8f) // Chiếm 80% chiều rộng màn hình
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    // TextField 2
                                    OutlinedTextField(
                                        value = "",
                                        onValueChange = {},
                                        label = { Text("Tên thiết bị của bạn là:") },
                                        singleLine = true,
                                        modifier = Modifier.fillMaxWidth(0.8f) // Chiếm 80% chiều rộng màn hình
                                    )
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
                                    .padding(start = outerPadding, end = outerPadding)
                                    .width(50.dp)
                                    .height(50.dp)
                                    .zIndex(2f) // Z-index cao hơn
                            ) {
                                IconButton(
                                    onClick = { showDialog = true },
                                    modifier = Modifier
                                        .align(Alignment.CenterEnd)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = "Info Icon",
                                        tint = Color.Gray
                                    )
                                }
                            }
                        }
                    }
                }

                // Hộp thoại bật lên
                if (showDialog) {
                    item {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            confirmButton = {
                                TextButton(onClick = { showDialog = false }) {
                                    Text("Đóng", color = Color.Blue)
                                }
                            },
                            shape = RoundedCornerShape(12.dp),
                            text = {
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = outerPadding, end = outerPadding, bottom = outerPadding)
                                ) {
                                    item {
                                        Text(
                                            "Bạn hãy chọn điểm truy cập (Access Point) của thiết bị bạn muốn kết nối.\n" +
                                                    "Tên của điểm truy cập sẽ có cú pháp: AP-{Tên_thiết_bị}-{ID_thiết_bị}.",
                                            fontSize = textFontSize,
                                            lineHeight = textFontSize * 1.2,
                                            color = Color.Black
                                        )
                                        Spacer(modifier = Modifier.height(textFieldSpacing))

                                        Text(
                                            "Tên và Id thiết bị sẽ được hiển thị ở bên dưới",
                                            lineHeight = textFontSize * 1.2,
                                            fontSize = textFontSize,
                                            color = Color.Black
                                        )
                                        Spacer(modifier = Modifier.height(textFieldSpacing))

                                        Text(
                                            "Ví dụ: ",
                                            fontSize = textFontSize,
                                            fontWeight = FontWeight.Bold,
                                            lineHeight = textFontSize * 1.2,
                                            color = Color.Black
                                        )
                                        Spacer(modifier = Modifier.width(textFieldSpacing))
                                        Text(
                                            "AP-DenThongMinh_A1-SLB_001",
                                            fontSize = textFontSize,
                                            lineHeight = textFontSize * 1.2,
                                            color = Color.Black
                                        )
                                    }
                                }
                            }
                        )
                    }
                }

                // Công tắc Wi-Fi
                item {
                    Column (
                        modifier = Modifier
                            .padding(start = outerPadding, end = outerPadding)
                            .width(contentWidth),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .width(contentWidth),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Wi-Fi: ", fontSize = textFontSize)
                            Switch(checked = true, onCheckedChange = {})
                        }
                        WiFiCard(wifiName = "ABC", isConnected = true, onClick = {})
                    }
                }

                // Danh sách các mạng Wi-Fi khả dụng
                item {
                    Row (
                        modifier = Modifier
                            .padding(start = outerPadding, end = outerPadding)
                            .padding(top = 8.dp)
                            .width(contentWidth),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Available networks:", fontSize = textFontSize)

                        Icon(
                            imageVector = Icons.Default.Refresh, // Biểu tượng refresh
                            contentDescription = "Refresh Icon",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(36.dp) // Kích thước của icon
                                .clickable(
                                    onClick = {}
                                )
                        )
                    }
                }

                item {
                    val wifiList = listOf(
                        "AP-DenThongMinh_A1-SLB_001",
                        "AP-DenThongMinh_A1-SLB_002",
                        "AP-DenThongMinh_A1-SLB_003"
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = outerPadding, end = outerPadding)
                            .width(contentWidth)
                    ) {
                        for (wifiName in wifiList) {
                            WiFiCard(
                                wifiName = wifiName,
                                isConnected = false,
                                onClick = {}
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun WiFiCard(
    wifiName: String,
    isConnected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .border(1.dp, Color(0xFFFFC107), RoundedCornerShape(8.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Biểu tượng và thông tin mạng
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Wifi,
                    contentDescription = "Wi-Fi Icon",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    // Tên Wi-Fi với cắt chuỗi dài
                    Text(
                        text = wifiName,
                        fontSize = 16.sp,
                        maxLines = 1, // Giới hạn 1 dòng
                        overflow = TextOverflow.Ellipsis, // Thêm "..." nếu quá dài
                        color = Color.Black,
                        modifier = Modifier.widthIn(max = 200.dp) // Giới hạn chiều rộng
                    )
                    if (isConnected) {
                        Text(
                            text = "Đã kết nối",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }

            }

            // Biểu tượng bên phải
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (!isConnected) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Locked Icon",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = "Arrow Icon",
                    tint = Color.Black
                )
            }
        }
    }
}



