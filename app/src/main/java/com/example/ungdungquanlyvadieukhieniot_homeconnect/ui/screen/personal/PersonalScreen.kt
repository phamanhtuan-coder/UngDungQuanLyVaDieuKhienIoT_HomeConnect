package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.personal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.NutHome


/**
 * Giao diện màn hình Trang cá nhân (Personal Screen)
 * -----------------------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 29/11/2024
 * Lần cập nhật cuối: 9/12/2024
 * -----------------------------------------
 * Input:
 *
 * Output: Scaffold
 * ---------------------------------------
 */
/**
 * Sủa lại phần giao diện cho thiết bị tablet
 * ---------------------------
 * Lần cập nhật 9/12/2024
 * ---------------------------
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PersonalScreen() {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp // Lấy chiều rộng màn hình theo dp
    val isTablet = screenWidthDp >= 600.dp // Xác định thiết bị là tablet hay điện thoại

    // Điều chỉnh kích thước và phông chữ dựa trên loại thiết bị
    val mainBoxWidth = if (isTablet) 480.dp else 350.dp
    val mainBoxHeight = if (isTablet) 280.dp else 180.dp
    val circleSize = if (isTablet) 130.dp else 80.dp
    val innerCircleSize = if (isTablet) 120.dp else 70.dp
    val topPadding = if (isTablet) 100.dp else 64.dp
    val nameFontSize = if (isTablet) 40.sp else 25.sp
    val emailFontSize = if (isTablet) 24.sp else 20.sp
    val buttonWidth = if (isTablet) 260.dp else 190.dp
    val buttonHeight = if (isTablet) 90.dp else 60.dp
    val buttonFontSize = if (isTablet) 18.sp else 14.sp
    val circleOffsetY = if (isTablet) (-40).dp else (-20).dp
    val topColumnPadding = if (isTablet) 120.dp else 70.dp
    val boxPaddingTop = if (isTablet) 24.dp else 14.dp
    // val dropdownMenuPadding = if (isTablet) 24.dp else 14.dp (Có thể sử dụng sau)

    // Sử dụng Scaffold để bố trí giao diện
    return Scaffold(
        bottomBar = {
            // Hiển thị Thanh Menu dưới cùng
            MenuBottom()
        },
        floatingActionButton = {
            // Hiển thị Nút Home
            NutHome()
        },
        floatingActionButtonPosition = FabPosition.Center,
        content = { innerPadding ->
            // Cột chính chứa toàn bộ nội dung
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding) // Áp dụng padding từ Scaffold
                    .imePadding() // Tự động thêm khoảng trống khi bàn phím xuất hiện
                    .verticalScroll(rememberScrollState()) // Cho phép cuộn nội dung
                    .background(color = Color.LightGray), // Màu nền
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Cột chứa thông tin cá nhân và biểu tượng
                Column {
                    // Hộp chính với nền trắng
                    Box(
                        modifier = Modifier
                            .padding(top = topPadding)
                            .size(width = mainBoxWidth, height = mainBoxHeight)
                            .shadow(
                                elevation = 8.dp,
                                shape = RoundedCornerShape(12.dp),
                                clip = false
                            )
                            .background(color = Color.White, shape = RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        // Vòng tròn ngoài màu trắng
                        Box(
                            modifier = Modifier
                                .size(circleSize)
                                .offset(y = circleOffsetY)
                                .background(color = Color.White, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            // Vòng tròn trong màu xám (ảnh đại diện)
                            Box(
                                modifier = Modifier
                                    .size(innerCircleSize)
                                    .background(color = Color.Gray, shape = CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                // Chữ viết tắt tên trong vòng tròn
                                Text(
                                    text = "NT",
                                    color = Color.White,
                                    fontSize = if (isTablet) 32.sp else 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            // Biểu tượng xác nhận (CheckCircle)
                            Box(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .offset(
                                        x = (if (isTablet) 10 else 10).dp,
                                        y = (if (isTablet) 40 else 25).dp
                                    ),
                                contentAlignment = Alignment.TopEnd
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Verified",
                                    tint = Color.Green,
                                    modifier = Modifier
                                        .size((if (isTablet) 70 else 40).dp)
                                        .padding(
                                            start = if (isTablet) 16.dp else 8.dp,
                                            end = if (isTablet) 16.dp else 8.dp,
                                            top = if (isTablet) 8.dp else 2.dp,
                                            bottom = if (isTablet) 8.dp else 2.dp
                                        )
                                )
                            }
                        }

                        // Cột chứa thông tin tên và email
                        Column(
                            modifier = Modifier.padding(top = topColumnPadding),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            // Hiển thị tên người dùng
                            Text(
                                text = "Nguyễn Thanh Sang",
                                fontWeight = FontWeight.Bold,
                                fontSize = nameFontSize,
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            // Hiển thị email
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "example@gmail.com", // Nếu email dài quá thì hiện "..." phần dư ra
                                    fontSize = emailFontSize
                                )
                            }
                            // Hộp chứa các nút
                            Box(
                                modifier = Modifier
                                    .padding(top = boxPaddingTop),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .size(width = mainBoxWidth, height = buttonHeight * 2)
                                        .align(Alignment.Center),
                                ) {
                                    // Nút "Trang cá nhân"
                                    Box(
                                        modifier = Modifier
                                            .size(width = buttonWidth, height = buttonHeight)
                                            .clip(CutCornerShape(topEnd = buttonHeight))
                                            .align(Alignment.CenterStart)
                                            .background(
                                                Color.LightGray,
                                                shape = RoundedCornerShape(
                                                    topStart = 0.dp,
                                                    topEnd = 0.dp,
                                                    bottomStart = 12.dp,
                                                    bottomEnd = 0.dp
                                                )
                                            ),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Button(
                                            onClick = {},
                                            modifier = Modifier.fillMaxSize(),
                                            shape = RoundedCornerShape(bottomStart = 12.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color.LightGray,
                                                contentColor = Color.Black
                                            )
                                        ) {
                                            Text(
                                                modifier = Modifier.padding(horizontal = if (isTablet) 50.dp else 35.dp),
                                                text = "Trang cá nhân",
                                                textAlign = TextAlign.Center,
                                                color = Color.Black,
                                                fontSize = buttonFontSize
                                            )
                                        }
                                    }
                                    // Nút "HomeConnect"
                                    Box(
                                        modifier = Modifier
                                            .size(width = buttonWidth, height = buttonHeight)
                                            .clip(CutCornerShape(bottomStart = buttonHeight / 1.5f))
                                            .align(Alignment.CenterEnd)
                                            .background(
                                                Color.LightGray,
                                                shape = RoundedCornerShape(
                                                    topStart = 0.dp,
                                                    bottomStart = 0.dp,
                                                    bottomEnd = 12.dp
                                                )
                                            ),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Button(
                                            onClick = { /* TODO: Xử lý sự kiện nút bấm */ },
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clip(CutCornerShape(bottomStart = buttonHeight / 1.5f)),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color.LightGray,
                                                contentColor = Color.Black
                                            ),
                                            shape = RoundedCornerShape(bottomEnd = 12.dp),
                                            elevation = ButtonDefaults.buttonElevation(0.dp)
                                        ) {
                                            Text(
                                                text = "HomeConnect",
                                                textAlign = TextAlign.Center,
                                                color = Color.Black,
                                                fontSize = buttonFontSize
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Tiêu đề "Thông tin cá nhân"
                    Text(
                        text = "Thông tin cá nhân",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding()
                            .align(Alignment.Start)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Biến để tính toán chiều cao cột
                    val itemHeight = 56 // Chiều cao ước tính cho mỗi mục
                    val maxVisibleItems = 9 // Số lượng mục tối đa hiển thị mà không cần cuộn
                    val totalItems = 9 // Tổng số mục trong biểu mẫu
                    val columnHeight = if (totalItems <= maxVisibleItems) {
                        (totalItems * itemHeight)
                    } else {
                        (maxVisibleItems * itemHeight)
                    }

                    // Cột chứa biểu mẫu thông tin cá nhân
                    Column(
                        modifier = Modifier
                            .size(width = mainBoxWidth, height = columnHeight.dp)
                            .padding(bottom = if (isTablet) 35.dp else 25.dp)
                            .shadow(
                                elevation = 8.dp,
                                shape = RoundedCornerShape(12.dp),
                                clip = false
                            )
                            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    ) {
                        Spacer(modifier = Modifier.height(12.dp))

                        // Trường "Họ và tên"
                        OutlinedTextField(
                            value = "",
                            onValueChange = { /* TODO: Xử lý thay đổi giá trị */ },
                            label = { Text("Họ và tên") },
                            modifier = Modifier
                                .height(itemHeight.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Trường "Số điện thoại"
                        OutlinedTextField(
                            value = "",
                            onValueChange = { /* TODO: Xử lý thay đổi giá trị */ },
                            label = { Text("Số điện thoại") },
                            modifier = Modifier
                                .height(itemHeight.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Trường "Nơi sống hiện tại" (Dropdown)
                        Box {
                            OutlinedTextField(
                                value = "",
                                onValueChange = { /* TODO: Xử lý thay đổi giá trị */ },
                                label = { Text("Nơi sống hiện tại") },
                                modifier = Modifier
                                    .height(itemHeight.dp)
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .clickable { /* TODO: Mở rộng danh sách */ },
                                readOnly = true,
                            )
                            // TODO: Thêm logic cho menu dropdown
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = "",
                            onValueChange = { /* TODO: Xử lý thay đổi giá trị */ },
                            label = { Text("Email") },
                            modifier = Modifier
                                .height(itemHeight.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .clickable { /* TODO: Mở rộng danh sách */ },
                            readOnly = true,
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Trường "Ngày sinh"
                        OutlinedTextField(
                            value = "",
                            onValueChange = { /* TODO: Xử lý thay đổi giá trị */ },
                            label = { Text("Ngày sinh (dd/mm/yyyy)") },
                            modifier = Modifier
                                .height(itemHeight.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = null
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Nút "Lưu thông tin"
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .height(itemHeight.dp),
                            onClick = {
                                // TODO: Xử lý sự kiện lưu thông tin
                            },
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                "Lưu thông tin",
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    );
}