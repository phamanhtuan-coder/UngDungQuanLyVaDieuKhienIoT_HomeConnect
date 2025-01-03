package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.access_point_connection

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.NutHome
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail_for_fire_alarm.FireAlarmDetailScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme

/** Giao diện màn hình Access Point Connection Screen (AccessPointConnectionScreen
 * -----------------------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 07/12/2024
 * Lần cập nhật cuối: 10/12/2024
 * -----------------------------------------
 * Input:
 *
 * Output: Scaffold
 *
 * ---------------------------------------
 */

@Composable
fun isTablet(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.smallestScreenWidthDp >= 600
}

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
        contentWidth = if(isTablet()) 400.dp + screenWidth * 0.1f else 300.dp,                    // Chiều rộng của nội dung chính bằng 80% chiều rộng màn hình
        iconSize = screenWidth * 0.04f,                       // Kích thước icon bằng 8% chiều rộng màn hình
        boxHeight = screenHeight * 0.1f,                      // Chiều cao Box là 10% chiều cao màn hình
        cornerBoxSize = screenWidth * 0.1f,                   // Kích thước cho Box góc lõm bằng 10% chiều rộng màn hình
        cornerBoxRadius = 50,                                 // Độ bo góc cho Box góc lõm (theo phần trăm)
        dialogPadding = screenWidth * 0.04f                   // Padding cho AlertDialog bằng 4% chiều rộng màn hình
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceSharingListScreen(
    navController: NavHostController
) {
    AppTheme {
        val layoutConfig = rememberResponsiveLayoutConfig() // Lấy LayoutConfig
        var showDialog by remember { mutableStateOf(false) }
        val colorScheme = MaterialTheme.colorScheme
        Scaffold(
            topBar = {
                /*
            * Hiển thị Header
             */
                Header(
                    navController = navController,
                    type = "Back",
                    title = "Chi tiết thiết bị"
                )
            },
            bottomBar = {
                /*
            * Hiển thị Thanh Menu dưới cùng
             */
                MenuBottom(navController)
            },
            containerColor = colorScheme.background,
            modifier = Modifier.fillMaxSize(),
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
                                    if (isTablet()) {
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
                                            // Văn bản tiêu đề "TÀI KHOẢN ĐÃ ĐƯỢC CHIA SẼ"
                                            Text(
                                                "TÀI KHOẢN ĐÃ ĐƯỢC CHIA SẼ",
                                                fontSize = layoutConfig.headingFontSize, // Font size linh hoạt
                                                color = colorScheme.onPrimary
                                            )
                                        }
                                    } else {
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

                                            // Văn bản tiêu đề "TÀI KHOẢN ĐÃ"
                                            Text(
                                                "TÀI KHOẢN ĐÃ",
                                                fontSize = layoutConfig.headingFontSize, // Font size linh hoạt
                                                color = colorScheme.onPrimary
                                            )
                                            // Văn bản tiêu đề "ĐƯỢC CHIA SẼ"
                                            Text(
                                                "ĐƯỢC CHIA SẼ",
                                                fontSize = layoutConfig.headingFontSize, // Font size linh hoạt
                                                color = colorScheme.onPrimary
                                            )
                                        }
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
                                        contentAlignment = Alignment.Center // Căn Row vào giữa Box
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth() // Đảm bảo Row chiếm toàn bộ chiều rộng
                                                .padding(
                                                    top = 12.dp,
                                                    start = 12.dp,
                                                    end = 8.dp
                                                ), // Khoảng cách bên trong Row
                                            horizontalArrangement = Arrangement.SpaceBetween, // Đẩy các phần tử ra hai bên
                                            verticalAlignment = Alignment.CenterVertically // Căn giữa theo chiều dọc
                                        ) {
                                            // Nội dung bên phải (Icon trong Box)
                                            Button(
                                                onClick = {
                                                    showDialog = true
                                                },
                                                modifier = Modifier
                                                    .size(24.dp), // Kích thước tổng thể của Button
                                                shape = CircleShape, // Đảm bảo Button có dạng hình tròn
                                                contentPadding = PaddingValues(0.dp), // Loại bỏ padding mặc định
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = colorScheme.onPrimary,
                                                    contentColor = colorScheme.primary
                                                )
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Delete,
                                                    contentDescription = "Delete",
                                                    modifier = Modifier.size(24.dp), // Kích thước của Icon
                                                    tint = colorScheme.primary
                                                )
                                            }

                                            Button(
                                                onClick = {
                                                    showDialog = true
                                                },
                                                modifier = Modifier
                                                    .size(24.dp), // Kích thước tổng thể của Button
                                                shape = CircleShape, // Đảm bảo Button có dạng hình tròn
                                                contentPadding = PaddingValues(0.dp), // Loại bỏ padding mặc định
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = colorScheme.onPrimary,
                                                    contentColor = colorScheme.primary
                                                )
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Add,
                                                    contentDescription = "Add",
                                                    modifier = Modifier.size(24.dp), // Kích thước của Icon
                                                    tint = colorScheme.primary
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
                        Column (
                            modifier = Modifier
                                .wrapContentHeight()
                                .padding(horizontal = layoutConfig.outerPadding) // Padding ngang linh hoạt theo LayoutConfig
                                .width(layoutConfig.contentWidth),               // Độ rộng linh hoạt theo LayoutConfig
                            horizontalAlignment = Alignment.CenterHorizontally   // Căn giữa theo chiều ngang
                        ) {
                            JobCard(
                                companyName = "Dribbble",
                                jobTitle = "UI Designer",
                                location = "Yogyakarta",
                                jobType = "Fulltime",
                                appliedDate = "June 3, 2021",
                                appliedStatus = "APPLIED"
                            )

                            JobCard(
                                companyName = "Dribbble",
                                jobTitle = "UI Designer",
                                location = "Yogyakarta",
                                jobType = "Fulltime",
                                appliedDate = "June 3, 2021",
                                appliedStatus = "APPLIED"
                            )

                            JobCard(
                                companyName = "Dribbble",
                                jobTitle = "UI Designer",
                                location = "Yogyakarta",
                                jobType = "Fulltime",
                                appliedDate = "June 3, 2021",
                                appliedStatus = "APPLIED"
                            )
                        }
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DeviceSharingListScreenPreview() {
    DeviceSharingListScreen(navController = rememberNavController())
}

@Composable
fun JobCard(
    companyName: String,
    jobTitle: String,
    location: String,
    jobType: String,
    appliedDate: String,
    appliedStatus: String
) {
    AppTheme {
        // Lấy thông tin layout responsive từ config
        val colorScheme = MaterialTheme.colorScheme
        val layoutConfig = rememberResponsiveLayoutConfig()
        Card(
            modifier = Modifier
                .padding(top = layoutConfig.textFieldSpacing)
                .clickable(onClick = {})
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = colorScheme.primary),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(layoutConfig.outerPadding)
                    .fillMaxWidth()
            ) {
                // Row đầu tiên: Hiển thị logo người dùng được chia sẽ
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Vùng hiển thị Logo
                    Box(
                        modifier = Modifier
                            .size(layoutConfig.iconSize * 2)
                            .background(Color(0xFFE74C3C), shape = RoundedCornerShape(50))
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Tên người dùng được chia sẽ
                    Text(
                        text = companyName,
                        fontSize = layoutConfig.textFontSize/ 1.5f,
                        color = colorScheme.onPrimary
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        // Tiêu đề của phần chia sẽ
                        Text(
                            text = jobTitle,
                            fontSize = layoutConfig.headingFontSize/1.5f ,
                            fontWeight = FontWeight.Bold,
                            color = colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        // Location & Job Type
                        Row (
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = location,
                                fontSize = layoutConfig.textFontSize/1.5f,
                                color = colorScheme.onPrimary
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = "• $jobType",
                                fontSize = layoutConfig.textFontSize/1.5f,
                                color = Color(0xFFE74C3C),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    // Nội dung bên phải (Icon trong Box)
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .size(36.dp), // Kích thước tổng thể của Button
                        shape = CircleShape, // Đảm bảo Button có dạng hình tròn
                        contentPadding = PaddingValues(0.dp), // Loại bỏ padding mặc định
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp) // Icon kích thước từ layoutConfig
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color(0xFFFFEDED),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(vertical = 4.dp, horizontal = 12.dp)
                    ) {
                        Text(
                            text = appliedStatus,
                            color = Color(0xFFE74C3C),
                            fontSize = layoutConfig.textFontSize/1.2f,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Text(
                        text = "Applied at $appliedDate",
                        fontSize = layoutConfig.textFontSize/ 2,
                        color = colorScheme.onPrimary
                    )
                }
            }
        }
    }
}