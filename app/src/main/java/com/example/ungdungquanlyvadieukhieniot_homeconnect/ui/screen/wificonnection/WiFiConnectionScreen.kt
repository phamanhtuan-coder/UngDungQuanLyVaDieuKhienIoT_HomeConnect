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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.zIndex
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.NutHome
import kotlin.times

// Data class cho LayoutConfig
data class LayoutConfig(
    val outerPadding: Dp,
    val textFieldSpacing: Dp,
    val headingFontSize: TextUnit,
    val textFontSize: TextUnit,
    val contentWidth: Dp,
    val iconSize: Dp,
    val boxHeight: Dp,
    val cornerBoxSize: Dp,          // Kích thước cho Box góc lõm
    val cornerBoxRadius: Int,       // Bo tròn góc cho Box góc lõm
    val dialogPadding: Dp           // Padding cho AlertDialog
)

// Tính toán LayoutConfig dựa trên kích thước màn hình
@Composable
fun rememberResponsiveLayoutConfig(): LayoutConfig {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    return LayoutConfig(
        outerPadding = screenWidth * 0.05f,                    // 5% chiều rộng màn hình
        textFieldSpacing = 8.dp + screenHeight * 0.01f,              // 1% chiều cao màn hình
        headingFontSize = (12 + screenWidth.value * 0.04f).sp, // Tỷ lệ font size theo chiều rộng
        textFontSize = (10 + screenWidth.value * 0.03f).sp,    // Font size mô tả
        contentWidth = screenWidth * 0.8f,                    // Chiếm 80% chiều rộng màn hình
        iconSize = screenWidth * 0.08f,                       // 8% của chiều rộng màn hình
        boxHeight = screenHeight * 0.1f,                      // 10% của chiều cao màn hình
        cornerBoxSize = screenWidth * 0.1f,                   // Kích thước cố định cho Box góc lõm
        cornerBoxRadius = 50,                                 // Phần trăm bo góc
        dialogPadding = screenWidth * 0.04f                   // Padding cho hộp thoại
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun WiFiConnectionScreen() {
    val layoutConfig = rememberResponsiveLayoutConfig() // Lấy LayoutConfig
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.LightGray,
        topBar = { Header() },
        bottomBar = { MenuBottom() },
        floatingActionButton = { NutHome() },
        floatingActionButtonPosition = FabPosition.Center,
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
//                    .padding(bottom = layoutConfig.outerPadding) // Padding linh hoạt
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
                    ) {
                        Column {
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
                                        .padding(horizontal = layoutConfig.outerPadding, vertical = layoutConfig.textFieldSpacing),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        "KẾT NỐI",
                                        fontSize = layoutConfig.headingFontSize,
                                        color = Color.White
                                    )
                                    Text(
                                        "ĐIỂM TRUY CẬP",
                                        fontSize = layoutConfig.headingFontSize,
                                        color = Color.White
                                    )

                                    Spacer(modifier = Modifier.height(layoutConfig.textFieldSpacing))

                                    OutlinedTextField(
                                        value = "",
                                        onValueChange = {},
                                        placeholder = { Text("ID thiết bị của bạn là:") },
                                        singleLine = true,
                                        modifier = Modifier.width(layoutConfig.contentWidth),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            containerColor = Color.White,             // Màu nền của TextField
                                            focusedBorderColor = Color.Black,         // Màu viền khi focus
                                            unfocusedBorderColor = Color.Gray,        // Màu viền khi không focus
                                            cursorColor = Color.Black,                // Màu con trỏ nhập liệu
                                            focusedLabelColor = Color.Black,           // Màu Label khi focus
                                            unfocusedLabelColor = Color.LightGray      // Màu Label khi không focus
                                        )
                                    )

                                    Spacer(modifier = Modifier.height(layoutConfig.textFieldSpacing))

                                    OutlinedTextField(
                                        value = "",
                                        onValueChange = {},
                                        placeholder = { Text("Tên thiết bị của bạn là:") },
                                        singleLine = true,
                                        modifier = Modifier.width(layoutConfig.contentWidth),
                                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                            containerColor = Color.White,             // Màu nền của TextField
                                            focusedBorderColor = Color.Black,         // Màu viền khi focus
                                            unfocusedBorderColor = Color.Gray,        // Màu viền khi không focus
                                            cursorColor = Color.Black,                // Màu con trỏ nhập liệu
                                            focusedLabelColor = Color.Black,           // Màu Label khi focus
                                            unfocusedLabelColor = Color.LightGray      // Màu Label khi không focus
                                        )
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(layoutConfig.cornerBoxSize)
                            ) {
                                // Box màu vàng (ở dưới)
                                Box(
                                    modifier = Modifier
                                        .size(layoutConfig.cornerBoxSize)
                                        .align(Alignment.TopEnd)
                                        .background(color = Color.Blue)
                                        .zIndex(1f)
                                )

                                // Box màu xám với góc lõm (ở trên)
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = Color.LightGray,
                                            shape = RoundedCornerShape(topEndPercent = layoutConfig.cornerBoxRadius)
                                        )
                                        .height(layoutConfig.cornerBoxSize)
                                        .zIndex(2f)
                                ) {
                                    IconButton(
                                        onClick = { showDialog = true },
                                        modifier = Modifier
                                            .align(Alignment.CenterEnd)
                                            .padding(end = layoutConfig.outerPadding)
                                            .size(layoutConfig.iconSize)
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
                }

                // Công tắc Wi-Fi
                item {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = layoutConfig.outerPadding)
                            .width(layoutConfig.contentWidth),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.width(layoutConfig.contentWidth),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Wi-Fi:", fontSize = layoutConfig.textFontSize)
                            Switch(checked = true, onCheckedChange = {})
                        }
                    }
                }

                // Danh sách các mạng Wi-Fi khả dụng
                item {
                    val wifiList = listOf(
                        "AP-DenThongMinh_A1-SLB_001",
                        "AP-DenThongMinh_A1-SLB_002",
                        "AP-DenThongMinh_A1-SLB_003"
                    )
                    Column(
                        modifier = Modifier
                            .padding(horizontal = layoutConfig.outerPadding)
                            .width(layoutConfig.contentWidth)
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
            // Đặt AlertDialog ngoài LazyColumn
            if (showDialog) {
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
                                .padding(horizontal = layoutConfig.dialogPadding, vertical = layoutConfig.textFieldSpacing)
                        ) {
                            item {
                                Text(
                                    "Bạn hãy chọn điểm truy cập (Access Point) của thiết bị bạn muốn kết nối.\n" +
                                            "Tên của điểm truy cập sẽ có cú pháp: AP-{Tên_thiết_bị}-{ID_thiết_bị}.",
                                    fontSize = layoutConfig.textFontSize,
                                    lineHeight = layoutConfig.textFontSize * 1.2,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.height(layoutConfig.textFieldSpacing))

                                Text(
                                    "Tên và Id thiết bị sẽ được hiển thị ở bên dưới",
                                    lineHeight = layoutConfig.textFontSize * 1.2,
                                    fontSize = layoutConfig.textFontSize,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.height(layoutConfig.textFieldSpacing))

                                Text(
                                    "Ví dụ: ",
                                    fontSize = layoutConfig.textFontSize,
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = layoutConfig.textFontSize * 1.2,
                                    color = Color.Black
                                )
                                Text(
                                    "AP-DenThongMinh_A1-SLB_001",
                                    fontSize = layoutConfig.textFontSize,
                                    lineHeight = layoutConfig.textFontSize * 1.2,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                )
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



