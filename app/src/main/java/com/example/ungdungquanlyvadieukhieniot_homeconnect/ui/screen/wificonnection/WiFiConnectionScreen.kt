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

/** Giao diện màn hình Trang chủ (Home Screen)
 * -----------------------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 07/12/2024
 * Lần cập nhật cuối: 08/12/2024
 * -----------------------------------------
 * Input:
 *
 * Output: Scaffold
 *
 * ---------------------------------------
 */

data class LayoutConfig(
    val outerPadding: Dp,  // Padding bên ngoài, thường được tính theo tỉ lệ chiều rộng màn hình
    val textFieldSpacing: Dp, // Khoảng cách giữa các thành phần, dựa trên chiều cao màn hình
    val headingFontSize: TextUnit, // Kích thước chữ tiêu đề, tỉ lệ theo chiều rộng màn hình
    val textFontSize: TextUnit, // Kích thước chữ mô tả hoặc nội dung
    val contentWidth: Dp, // Chiều rộng của nội dung chính (ví dụ: TextField)
    val iconSize: Dp, // Kích thước của icon trong giao diện
    val boxHeight: Dp, // Chiều cao của các Box (ví dụ: hộp chứa góc lõm)
    val cornerBoxSize: Dp,          // Kích thước cho Box góc lõm
    val cornerBoxRadius: Int,       // Bo tròn góc cho Box góc lõm
    val dialogPadding: Dp           // Padding cho AlertDialog
)

// Tính toán LayoutConfig dựa trên kích thước màn hình
@Composable
fun rememberResponsiveLayoutConfig(): LayoutConfig {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp // Lấy chiều rộng màn hình
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp // Lấy chiều cao màn hình

    return LayoutConfig(
        outerPadding = screenWidth * 0.05f,                   // Padding bên ngoài bằng 5% chiều rộng màn hình
        textFieldSpacing = 8.dp + screenHeight * 0.01f,       // Khoảng cách giữa các thành phần = 8dp + 1% chiều cao màn hình
        headingFontSize = (12 + screenWidth.value * 0.04f).sp,// Font size tiêu đề dựa trên chiều rộng màn hình
        textFontSize = (10 + screenWidth.value * 0.03f).sp,   // Font size nội dung dựa trên chiều rộng màn hình
        contentWidth = screenWidth * 0.8f,                    // Chiều rộng của nội dung chính bằng 80% chiều rộng màn hình
        iconSize = screenWidth * 0.08f,                       // Kích thước icon bằng 8% chiều rộng màn hình
        boxHeight = screenHeight * 0.1f,                      // Chiều cao Box là 10% chiều cao màn hình
        cornerBoxSize = screenWidth * 0.1f,                   // Kích thước cho Box góc lõm bằng 10% chiều rộng màn hình
        cornerBoxRadius = 50,                                 // Độ bo góc cho Box góc lõm (theo phần trăm)
        dialogPadding = screenWidth * 0.04f                   // Padding cho AlertDialog bằng 4% chiều rộng màn hình
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun WiFiConnectionScreen() {
    val layoutConfig = rememberResponsiveLayoutConfig() // Lấy LayoutConfig
    var showDialog by remember { mutableStateOf(false) }

    return Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.LightGray,
        topBar = { Header() },
        bottomBar = { MenuBottom() },
        floatingActionButton = { NutHome() },
        floatingActionButtonPosition = FabPosition.Center,
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize() // Chiếm toàn bộ kích thước của màn hình
//                    .padding(bottom = layoutConfig.outerPadding) // Padding linh hoạt
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Top, // Sắp xếp các item theo chiều dọc, bắt đầu từ trên xuống.
                horizontalAlignment = Alignment.CenterHorizontally // Căn chỉnh các item theo chiều ngang vào giữa.
            ) {
                // Tiêu đề
                item {
                    // Box lớn chứa phần tiêu đề và các thành phần bên trong
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight() // Chiều cao vừa đủ với nội dung bên trong
                            .background(color = Color.LightGray)
                    ) {
                        // Cột chứa các phần tử con
                        Column {
                            // Hộp màu xanh dương bo tròn góc dưới bên trái
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth() // Chiếm toàn bộ chiều rộng
                                    .wrapContentHeight() // Chiều cao vừa đủ với nội dung
                                    .background(
                                        color = Color.Blue,
                                        shape = RoundedCornerShape(bottomStart = 40.dp)
                                    )
                            ) {
                                // Cột chứa văn bản tiêu đề và các TextField
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth() // Chiếm toàn bộ chiều rộng
                                        .padding(
                                            horizontal = layoutConfig.outerPadding, // Padding ngang linh hoạt
                                            vertical = layoutConfig.textFieldSpacing // Padding dọc linh hoạt
                                        ),
                                    horizontalAlignment = Alignment.CenterHorizontally // Căn giữa các phần tử con theo chiều ngang
                                ) {
                                    // Văn bản tiêu đề "KẾT NỐI"
                                    Text(
                                        "KẾT NỐI",
                                        fontSize = layoutConfig.headingFontSize, // Font size linh hoạt
                                        color = Color.White
                                    )
                                    // Văn bản tiêu đề "ĐIỂM TRUY CẬP"
                                    Text(
                                        "ĐIỂM TRUY CẬP",
                                        fontSize = layoutConfig.headingFontSize, // Font size linh hoạt
                                        color = Color.White
                                    )

                                    // Khoảng cách giữa tiêu đề và TextField
                                    Spacer(modifier = Modifier.height(layoutConfig.textFieldSpacing))

                                    // Ô nhập liệu đầu tiên - ID thiết bị
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
                            // Box chứa góc lõm màu xám
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(layoutConfig.cornerBoxSize * 0.9f) // Chiều cao linh hoạt theo LayoutConfig
                            ) {
                                // Box màu vàng nhỏ nằm trên góc phải
                                Box(
                                    modifier = Modifier
                                        .size(layoutConfig.cornerBoxSize)
                                        .align(Alignment.TopEnd)
                                        .background(color = Color.Blue)
                                        .zIndex(1f)  // Z-index thấp hơn
                                )

                                // Box màu xám bo tròn góc lõm trên cùng bên phải
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = Color.LightGray,
                                            shape = RoundedCornerShape(topEndPercent = layoutConfig.cornerBoxRadius)
                                        )
                                        .height(layoutConfig.cornerBoxSize) // Chiều cao linh hoạt
                                        .zIndex(2f) // Z-index cao hơn
                                ) {
                                    // Nút Icon thông tin
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
                    // Cột chứa nội dung công tắc Wi-Fi
                    Column(
                        modifier = Modifier
                            .padding(horizontal = layoutConfig.outerPadding) // Padding ngang linh hoạt theo LayoutConfig
                            .width(layoutConfig.contentWidth),               // Độ rộng linh hoạt theo LayoutConfig
                        horizontalAlignment = Alignment.CenterHorizontally   // Căn giữa theo chiều ngang
                    ) {
                        // Hàng ngang chứa nhãn và công tắc Wi-Fi
                        Row(
                            modifier = Modifier.width(layoutConfig.contentWidth), // Độ rộng linh hoạt theo LayoutConfig
                            horizontalArrangement = Arrangement.SpaceBetween,     // Các thành phần được bố trí cách xa nhau
                            verticalAlignment = Alignment.CenterVertically        // Căn giữa theo chiều dọc
                        ) {
                            Text("Wi-Fi:", fontSize = layoutConfig.textFontSize)
                            Switch(checked = true, onCheckedChange = {})
                        }
                        WiFiCard(
                            wifiName = "ABC",
                            isConnected = true,
                            onClick = {}
                        )
                    }
                }

                // Danh sách các mạng Wi-Fi khả dụng
                item {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = layoutConfig.outerPadding) // Padding ngang linh hoạt theo LayoutConfig
                            .width(layoutConfig.contentWidth),               // Độ rộng linh hoạt theo LayoutConfig
                        horizontalAlignment = Alignment.CenterHorizontally   // Căn giữa theo chiều ngang
                    ) {
                        Spacer(modifier = Modifier.height(layoutConfig.textFieldSpacing))
                        Row(
                            modifier = Modifier.width(layoutConfig.contentWidth), // Độ rộng linh hoạt theo LayoutConfig
                            horizontalArrangement = Arrangement.SpaceBetween,     // Các thành phần được bố trí cách xa nhau
                            verticalAlignment = Alignment.CenterVertically        // Căn giữa theo chiều dọc
                        ) {
                            Text("Available networks", fontSize = layoutConfig.textFontSize)
                            Icon(Icons.Default.Refresh, contentDescription = "")
                        }
                        Spacer(modifier = Modifier.height(layoutConfig.textFieldSpacing))
                    }
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
            // Đặt AlertDialog ngoài LazyColumn để hiển thị hộp thoại khi `showDialog` == true
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false }, // Đóng hộp thoại khi nhấn ra ngoài hoặc nút đóng
                    confirmButton = {
                        // Nút xác nhận (Đóng)
                        TextButton(onClick = { showDialog = false }) {
                            Text("Đóng", color = Color.Blue)
                        }
                    },
                    shape = RoundedCornerShape(12.dp), // Bo tròn các góc của hộp thoại
                    text = {
                        // Nội dung của hộp thoại
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = layoutConfig.dialogPadding, vertical = layoutConfig.textFieldSpacing)
                        ) {
                            item {
                                // Đoạn văn bản mô tả hướng dẫn truy cập
                                Text(
                                    "Bạn hãy chọn điểm truy cập (Access Point) của thiết bị bạn muốn kết nối.\n" +
                                            "Tên của điểm truy cập sẽ có cú pháp: AP-{Tên_thiết_bị}-{ID_thiết_bị}.",
                                    fontSize = layoutConfig.textFontSize,
                                    lineHeight = layoutConfig.textFontSize * 1.2,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.height(layoutConfig.textFieldSpacing))

                                // Đoạn văn bản hướng hướng dẫn xem tên và ID thiết bị
                                Text(
                                    "Tên và Id thiết bị sẽ được hiển thị ở bên dưới",
                                    lineHeight = layoutConfig.textFontSize * 1.2,
                                    fontSize = layoutConfig.textFontSize,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.height(layoutConfig.textFieldSpacing))

                                // Văn bản ví dụ tiêu đề
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
    wifiName: String, // Tên mạng Wi-Fi hiển thị
    isConnected: Boolean, // Trạng thái kết nối (true nếu đã kết nối, false nếu chưa)
    onClick: () -> Unit // Callback khi Card được nhấn
) {
    // Card chứa thông tin Wi-Fi
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
        // Nội dung của Card là Row chia thành 2 phần: thông tin mạng và biểu tượng
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically, // Canh giữa theo chiều dọc
            horizontalArrangement = Arrangement.SpaceBetween // Canh các phần tử cách nhau đều
        ) {
            // Phần biểu tượng và thông tin Wi-Fi bên trái
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon( // Biểu tượng Wi-Fi
                    imageVector = Icons.Default.Wifi,
                    contentDescription = "Wi-Fi Icon",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    // Hiển thị tên mạng Wi-Fi
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



