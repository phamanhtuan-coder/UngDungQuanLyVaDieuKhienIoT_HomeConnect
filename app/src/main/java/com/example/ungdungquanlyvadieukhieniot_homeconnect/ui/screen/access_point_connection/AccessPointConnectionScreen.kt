package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.access_point_connection

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme

/** Giao diện màn hình Access Point Connection Screen (AccessPointConnectionScreen
 * -----------------------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 07/12/2024
 * Lần cập nhật cuối: 09/12/2024
 * -----------------------------------------
 * @param navController Đối tượng điều khiển điều hướng.
 * @return Scaffold chứa toàn bộ nội dung
 * -----------------------------------------
 * Sủa lại phần giao diện
 *
 * ---------------------------
 * Lần cập nhật 30/12/2024
 * Người sửa: Phạm Anh Tuấn
 * ---------------------------
 */
@Composable
fun AccessPointConnectionScreen(
    navController: NavHostController
) {
    val layoutConfig = rememberResponsiveLayoutConfig() // Lấy LayoutConfig
    var showDialog by remember { mutableStateOf(false) }

    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = colorScheme.background,
            topBar = {
                Header(navController, "Back", "Kết nối với thiết bị")
                     },
            bottomBar = {
                MenuBottom(navController)
                        },
            content = { innerPadding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize() // Chiếm toàn bộ kích thước của màn hình
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
                                .background(color = colorScheme.background),
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
                                        Text(
                                            "Kết nối",
                                            fontSize = layoutConfig.headingFontSize, // Font size linh hoạt
                                            color = colorScheme.onPrimary
                                        )
                                        // Văn bản tiêu đề "ĐIỂM TRUY CẬP"
                                        Text(
                                            "điểm truy cập",
                                            fontSize = layoutConfig.headingFontSize, // Font size linh hoạt
                                            color = colorScheme.onPrimary
                                        )

                                        // Khoảng cách giữa tiêu đề và TextField
                                        Spacer(modifier = Modifier.height(layoutConfig.textFieldSpacing))

                                        // Ô nhập liệu đầu tiên - ID thiết bị
                                        OutlinedTextField(
                                            value = "",
                                            onValueChange = {
                                                //Todo: Hiển thị ID thiết bị
                                            },
                                            placeholder = { Text("ID thiết bị của bạn là:") },
                                            singleLine = true,
                                            modifier = Modifier.width(layoutConfig.contentWidth),
                                            colors = TextFieldDefaults.colors(
                                                focusedContainerColor = colorScheme.onPrimary,
                                                unfocusedContainerColor = colorScheme.onPrimary,
                                                focusedIndicatorColor = colorScheme.primary,
                                                unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                                            )
                                        )

                                        Spacer(modifier = Modifier.height(layoutConfig.textFieldSpacing))

                                        OutlinedTextField(
                                            value = "",
                                            onValueChange = {
                                                //Todo: Hiển thị tên thiết bị
                                            },
                                            placeholder = { Text("Tên thiết bị của bạn là:") },
                                            singleLine = true,
                                            modifier = Modifier.width(layoutConfig.contentWidth),
                                            colors = TextFieldDefaults.colors(
                                                focusedContainerColor = colorScheme.onPrimary,
                                                unfocusedContainerColor = colorScheme.onPrimary,
                                                focusedIndicatorColor = colorScheme.primary,
                                                unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                                            )
                                        )
                                    }
                                }
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
                                            .background(color = colorScheme.primary)
                                            .zIndex(1f)  // Z-index thấp hơn
                                    )

                                    // Box màu xám bo tròn góc lõm trên cùng bên phải
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(
                                                color = colorScheme.background,
                                                shape = RoundedCornerShape(topEndPercent = layoutConfig.cornerBoxRadius)
                                            )
                                            .height(layoutConfig.cornerBoxSize) // Chiều cao linh hoạt
                                            .zIndex(2f), // Z-index cao hơn
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .padding(horizontal = layoutConfig.outerPadding)
                                                .width(layoutConfig.contentWidth), // Độ rộng linh hoạt theo LayoutConfig
                                            horizontalArrangement = Arrangement.SpaceBetween,     // Các thành phần được bố trí cách xa nhau
                                            verticalAlignment = Alignment.CenterVertically        // Căn giữa theo chiều dọc
                                        ) {
                                            Box() {

                                            }
                                            // Nút Icon thông tin
                                            IconButton(
                                                onClick = { showDialog = true },
                                                modifier = Modifier
                                                    .size(layoutConfig.iconSize),
                                                colors = IconButtonDefaults.iconButtonColors(colorScheme.primary)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Info,
                                                    contentDescription = "Info Icon",
                                                    tint = colorScheme.onPrimary
                                                )
                                            }
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
                                Switch(checked = true, onCheckedChange = {
                                    //Todo: Bật/Tắt Wi-Fi
                                })
                            }
                            WiFiCard(
                                wifiName = "ABC",
                                isConnected = true,
                                onClick = {
                                    //Todo: Kết nối mạng Wi-Fi với thiết bị
                                }
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
                                    onClick = {
                                        //Todo: Kết nối mạng Wi-Fi với thiết bị
                                    }
                                )
                            }
                        }
                    }
                }
                // Đặt AlertDialog ngoài LazyColumn để hiển thị hộp thoại khi `showDialog` == true
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            showDialog = false
                        }, // Đóng hộp thoại khi nhấn ra ngoài hoặc nút đóng
                        confirmButton = {
                            // Nút xác nhận (Đóng)
                            TextButton(onClick = { showDialog = false }) {
                                Text("Đóng", color = colorScheme.primary)
                            }
                        },
                        shape = RoundedCornerShape(12.dp), // Bo tròn các góc của hộp thoại
                        text = {
                            // Nội dung của hộp thoại
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = layoutConfig.dialogPadding,
                                        vertical = layoutConfig.textFieldSpacing
                                    )
                            ) {
                                item {
                                    // Đoạn văn bản mô tả hướng dẫn truy cập
                                    Text(
                                        "Bạn hãy chọn điểm truy cập (Access Point) của thiết bị bạn muốn kết nối.\n" +
                                                "Tên của điểm truy cập sẽ có cú pháp: AP-{Tên_thiết_bị}-{ID_thiết_bị}.",
                                        fontSize = layoutConfig.textFontSize,
                                        lineHeight = layoutConfig.textFontSize * 1.2,
                                        color = colorScheme.onSecondary,
                                    )
                                    Spacer(modifier = Modifier.height(layoutConfig.textFieldSpacing))

                                    // Đoạn văn bản hướng hướng dẫn xem tên và ID thiết bị
                                    Text(
                                        "Tên và Id thiết bị sẽ được hiển thị ở bên dưới",
                                        lineHeight = layoutConfig.textFontSize * 1.2,
                                        fontSize = layoutConfig.textFontSize,
                                        color = colorScheme.onSecondary,
                                    )
                                    Spacer(modifier = Modifier.height(layoutConfig.textFieldSpacing))

                                    // Văn bản ví dụ tiêu đề
                                    Text(
                                        "Ví dụ: ",
                                        fontSize = layoutConfig.textFontSize,
                                        fontWeight = FontWeight.Bold,
                                        lineHeight = layoutConfig.textFontSize * 1.2,
                                        color =  colorScheme.onSecondary,
                                    )
                                    Text(
                                        "AP-DenThongMinh_A1-SLB_001",
                                        fontSize = layoutConfig.textFontSize,
                                        lineHeight = layoutConfig.textFontSize * 1.2,
                                        color =  colorScheme.onSecondary,
                                    )
                                }
                            }
                        }
                    )
                }
            }
        )
    }
}

@Composable
fun WiFiCard(
    wifiName: String, // Tên mạng Wi-Fi hiển thị
    isConnected: Boolean, // Trạng thái kết nối (true nếu đã kết nối, false nếu chưa)
    onClick: () -> Unit // Callback khi Card được nhấn
) {
    // Card chứa thông tin Wi-Fi
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .border(1.dp, colorScheme.onSecondary, RoundedCornerShape(8.dp))
                .clickable { onClick() },
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = colorScheme.secondary),
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
                        tint = colorScheme.onSecondary,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        // Hiển thị tên mạng Wi-Fi
                        Text(
                            text = wifiName,
                            fontSize = 16.sp,
                            maxLines = 1, // Giới hạn 1 dòng
                            overflow = TextOverflow.Ellipsis, // Thêm "..." nếu quá dài
                            color = colorScheme.onSecondary,
                            modifier = Modifier.widthIn(max = 200.dp) // Giới hạn chiều rộng
                        )
                        if (isConnected) {
                            Text(
                                text = "Đã kết nối",
                                fontSize = 12.sp,
                                color = colorScheme.onSecondary,
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
                            tint = colorScheme.onSecondary,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = "Arrow Icon",
                        tint = colorScheme.onSecondary,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun AccessPointConnectionScreenPreview() {
    AccessPointConnectionScreen(navController = rememberNavController())
}