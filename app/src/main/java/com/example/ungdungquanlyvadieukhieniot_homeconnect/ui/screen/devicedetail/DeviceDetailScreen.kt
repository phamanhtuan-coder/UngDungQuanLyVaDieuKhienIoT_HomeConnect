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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.RectangleShape
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
import androidx.compose.ui.platform.LocalConfiguration

/** Giao diện màn hình Device Detail (DeviceDetailScreen)
 * -----------------------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 6/12/2024
 * Lần cập nhật cuối: 7/12/2024
 * -----------------------------------------
 * Input:
 *
 * Output: Scaffold
 *
 * ---------------------------------------
 */
@Composable
fun DeviceDetailScreen() {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    // Theo hướng dẫn Material Design, thường 600dp trở lên được xem là tablet
    if (screenWidthDp >= 600) {
        // Layout dành cho tablet
        DeviceDetailTabletScreen()
    } else {
        // Layout dành cho phone
        DeviceDetailPhoneScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DeviceDetailPhoneScreen() {
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
                                    .clip(RoundedCornerShape(bottomStart = 40.dp)) // Làm bo tròn góc dưới bên trái của Box
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize() // Chiếm toàn bộ kích thước của Box
                                ) {
                                    // Phần đầu tiên: Hiển thị thông tin "Dining Room"
                                    Row(
                                        modifier = Modifier.padding(top = 8.dp, end = 12.dp) // Canh lề trên và phải
                                    ) {
                                        // Cột chứa các thông tin của phòng
                                        Column(
                                            modifier = Modifier
                                                .padding(start = 12.dp, end = 12.dp) // Canh lề hai bên
                                                .fillMaxWidth() // Chiều rộng đầy đủ
                                                .background(color = Color.Blue) // Nền màu xanh dương
                                                .weight(0.2f), // Chiếm 20% trọng lượng của Row
                                            horizontalAlignment = Alignment.Start,
                                            verticalArrangement = Arrangement.SpaceBetween // Các thành phần cách đều nhau
                                        ) {
                                            Text(text = "Dining Room", color = Color.LightGray) // Tiêu đề
                                            Spacer(modifier = Modifier.height(4.dp)) // Khoảng cách giữa các thành phần

                                            // Switch bật/tắt đèn
                                            Switch(
                                                checked = true, // Trạng thái mặc định của Switch là bật
                                                onCheckedChange = { }, // Không có logic thay đổi ở đây
                                                thumbContent = {
                                                    if (true) { // Nội dung của nút trong Switch khi bật
                                                        Icon(
                                                            imageVector = Icons.Filled.Check,
                                                            contentDescription = "" // Icon dấu check
                                                        )
                                                    } else { // Nội dung khi tắt
                                                        Icon(
                                                            imageVector = Icons.Filled.Close,
                                                            contentDescription = "" // Icon dấu X
                                                        )
                                                    }
                                                }
                                            )

                                            // Hiển thị phần trăm độ sáng
                                            Row(
                                                modifier = Modifier.fillMaxWidth(), // Chiều rộng đầy đủ
                                                horizontalArrangement = Arrangement.Start,
                                                verticalAlignment = Alignment.Bottom // Canh các thành phần theo đáy
                                            ) {
                                                Text(
                                                    "80", fontWeight = FontWeight.Bold, fontSize = 50.sp, color = Color.White
                                                ) // Số phần trăm
                                                Text(
                                                    "%", fontWeight = FontWeight.Bold, fontSize = 25.sp,
                                                    modifier = Modifier.offset(y = -8.dp), // Đẩy lên trên một chút
                                                    color = Color.White
                                                )
                                            }
                                            Text("Brightness", color = Color.LightGray, fontSize = 20.sp) // Nhãn cho độ sáng
                                            Spacer(modifier = Modifier.height(8.dp)) // Khoảng cách dưới cùng
                                        }

                                        // Hình ảnh đèn
                                        Image(
                                            painter = painterResource(id = R.drawable.lamp),
                                            modifier = Modifier.size(150.dp), // Kích thước hình ảnh
                                            contentDescription = "" // Mô tả cho hình ảnh
                                        )
                                    }

                                    // Thanh trượt để điều chỉnh cường độ sáng
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 12.dp, end = 12.dp)
                                            .background(color = Color.Blue) // Nền màu xanh dương
                                    ) {
                                        Text("Insensity", color = Color.LightGray) // Tiêu đề cường độ sáng
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Center, // Căn giữa các thành phần
                                            verticalAlignment = Alignment.CenterVertically // Căn giữa theo chiều dọc
                                        ) {
                                            Image(
                                                painter = painterResource(id = R.drawable.bulboff),
                                                modifier = Modifier
                                                    .padding(end = 8.dp)
                                                    .size(24.dp), // Hình ảnh bóng đèn tắt
                                                contentDescription = ""
                                            )
                                            Slider(
                                                value = 80f, onValueChange = {}, // Thanh trượt giá trị mặc định là 80
                                                steps = 50, valueRange = 0f..100f, modifier = Modifier.width(300.dp)
                                            )
                                            Image(
                                                painter = painterResource(id = R.drawable.bulb),
                                                modifier = Modifier
                                                    .padding(start = 8.dp)
                                                    .size(24.dp), // Hình ảnh bóng đèn bật
                                                contentDescription = ""
                                            )
                                        }
                                    }

                                    // Đường kẻ phân cách mỏng
                                    Box(
                                        modifier = Modifier
                                            .padding(start = 12.dp, end = 12.dp)
                                            .fillMaxWidth()
                                            .height(1.dp)
                                            .background(Color.LightGray) // Màu xám nhạt cho đường kẻ
                                    )

                                    // Phần hiển thị thông tin sử dụng điện
                                    Column(
                                        modifier = Modifier
                                            .padding(top = 24.dp, start = 12.dp, end = 12.dp)
                                            .fillMaxSize(), // Chiếm toàn bộ kích thước còn lại
                                        horizontalAlignment = Alignment.Start,
                                        verticalArrangement = Arrangement.Top
                                    ) {
                                        Text(
                                            "Usages", fontWeight = FontWeight.Bold,
                                            color = Color.White, fontSize = 15.sp
                                        ) // Tiêu đề Usage

                                        // Thông tin "Use Today"
                                        Row(
                                            modifier = Modifier
                                                .padding(top = 12.dp)
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text("Use Today", color = Color.LightGray) // Nhãn
                                            Row {
                                                Text(
                                                    "50", fontSize = 20.sp,
                                                    modifier = Modifier.offset(y = -5.dp),
                                                    color = Color.LightGray
                                                ) // Giá trị
                                                Spacer(modifier = Modifier.width(2.dp))
                                                Text("watt", color = Color.LightGray)
                                            }
                                        }

                                        // Thông tin "Use Week"
                                        Row(
                                            modifier = Modifier
                                                .padding(top = 12.dp)
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text("Use Week", color = Color.LightGray)
                                            Row {
                                                Text(
                                                    "500", fontSize = 20.sp,
                                                    modifier = Modifier.offset(y = -5.dp),
                                                    color = Color.LightGray
                                                )
                                                Spacer(modifier = Modifier.width(2.dp))
                                                Text("kwh", color = Color.LightGray)
                                            }
                                        }

                                        // Thông tin "Use Month"
                                        Row(
                                            modifier = Modifier
                                                .padding(top = 12.dp)
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text("Use Month", color = Color.LightGray)
                                            Row {
                                                Text(
                                                    "5000", fontSize = 20.sp,
                                                    modifier = Modifier.offset(y = -5.dp),
                                                    color = Color.LightGray
                                                )
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
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth() // Đảm bảo Row chiếm toàn bộ chiều rộng
                                            .padding(top = 12.dp, start = 12.dp, end = 8.dp), // Khoảng cách bên trong Row
                                        horizontalArrangement = Arrangement.SpaceBetween, // Đẩy các phần tử ra hai bên
                                        verticalAlignment = Alignment.CenterVertically // Căn giữa theo chiều dọc
                                    ) {
                                        // Nội dung bên trái (Schedule + số 3)
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = "Schedule",
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 18.sp
                                            )
                                            Spacer(modifier = Modifier.width(4.dp)) // Khoảng cách giữa "Schedule" và Box
                                            Box(
                                                modifier = Modifier
                                                    .size(20.dp) // Kích thước Box
                                                    .background(color = Color.Blue, shape = RoundedCornerShape(6.dp)), // Màu nền
                                                contentAlignment = Alignment.Center // Đưa nội dung bên trong vào giữa Box
                                            ) {
                                                Text(
                                                    text = "3", // Nội dung số 3
                                                    color = Color.White, // Màu chữ
                                                    fontWeight = FontWeight.Bold,
                                                    style = androidx.compose.ui.text.TextStyle(fontSize = 12.sp) // Kích thước chữ
                                                )
                                            }
                                        }

                                        // Nội dung bên phải (Icon trong Box)
                                        Box(
                                            modifier = Modifier
                                                .padding(end = 12.dp)
                                                .size(36.dp) // Kích thước Box
                                                .background(color = Color.White, shape = RoundedCornerShape(6.dp)), // Màu nền và góc bo
                                            contentAlignment = Alignment.Center // Đưa Icon vào giữa Box
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Add,
                                                contentDescription = "Add",
                                                modifier = Modifier.size(24.dp) // Kích thước Icon
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Column {
                        SmartLampCard(false)
                        SmartLampCard(false)
                        SmartLampCard(false)
                        SmartLampCard(false)
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DeviceDetailTabletScreen() {
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
// Box chính bao quanh toàn bộ giao diện
                            Box(
                                modifier = Modifier
                                    .fillMaxSize() // Chiếm toàn bộ màn hình
                                    .clip(RoundedCornerShape(bottomStart = 40.dp)) // Bo góc phía dưới bên trái
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize() // Chiếm toàn bộ kích thước của Box
                                ) {
                                    // Phần Header: Hiển thị thông tin phòng và đèn
                                    Row(
                                        modifier = Modifier.padding(top = 8.dp, end = 12.dp) // Canh lề trên và phải
                                    ) {
                                        // Cột bên trái: Thông tin về phòng và độ sáng
                                        Column(
                                            modifier = Modifier
                                                .padding(start = 12.dp, end = 12.dp) // Canh lề trái và phải
                                                .fillMaxWidth() // Chiều rộng đầy đủ
                                                .background(color = Color.Blue) // Nền xanh
                                                .weight(0.2f), // Trọng lượng chiếm 20% của Row
                                            horizontalAlignment = Alignment.Start,
                                            verticalArrangement = Arrangement.SpaceBetween // Phân bố các thành phần cách đều nhau
                                        ) {
                                            Text(text = "Dining Room", color = Color.LightGray) // Tiêu đề
                                            Spacer(modifier = Modifier.height(4.dp)) // Khoảng cách

                                            // Switch bật/tắt đèn với icon
                                            Switch(
                                                checked = true,
                                                onCheckedChange = { }, // Hàm xử lý khi thay đổi trạng thái (để trống)
                                                thumbContent = { // Nội dung bên trong nút Switch
                                                    if (true) { // Trạng thái bật
                                                        Icon(imageVector = Icons.Filled.Check, contentDescription = "")
                                                    } else { // Trạng thái tắt
                                                        Icon(imageVector = Icons.Filled.Close, contentDescription = "")
                                                    }
                                                }
                                            )

                                            // Hiển thị mức độ sáng (80%)
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.Start, // Căn trái
                                                verticalAlignment = Alignment.Bottom // Căn dưới
                                            ) {
                                                Text(
                                                    "80", fontWeight = FontWeight.Bold, fontSize = 50.sp, color = Color.White
                                                ) // Số phần trăm
                                                Text(
                                                    "%", fontWeight = FontWeight.Bold, fontSize = 25.sp,
                                                    modifier = Modifier.offset(y = -8.dp), // Đẩy chữ % lên trên
                                                    color = Color.White
                                                )
                                            }
                                            Text("Brightness", color = Color.LightGray, fontSize = 20.sp) // Nhãn Brightness
                                            Spacer(modifier = Modifier.height(8.dp))
                                        }

                                        // Hình ảnh chiếc đèn ở cột bên phải
                                        Image(
                                            painter = painterResource(id = R.drawable.lamp),
                                            modifier = Modifier.size(150.dp), // Kích thước hình
                                            contentDescription = "" // Không có mô tả
                                        )
                                    }

                                    // Thanh Slider điều chỉnh cường độ sáng và các ngày trong tuần
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 12.dp, end = 12.dp)
                                            .background(color = Color.Blue) // Nền xanh
                                    ) {
                                        Text("Insensity", color = Color.LightGray) // Tiêu đề cường độ

                                        // Hàng chứa thanh trượt và biểu tượng bóng đèn
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween, // Căn cách đều các thành phần
                                            verticalAlignment = Alignment.CenterVertically // Căn giữa theo chiều dọc
                                        ) {
                                            // Hình bóng đèn tắt
                                            Image(
                                                painter = painterResource(id = R.drawable.bulboff),
                                                modifier = Modifier.padding(end = 8.dp).size(24.dp),
                                                contentDescription = ""
                                            )

                                            // Thanh trượt (Slider) giá trị 80
                                            Slider(
                                                value = 80f, onValueChange = {}, // Xử lý khi trượt (để trống)
                                                steps = 50, valueRange = 0f..100f, modifier = Modifier.width(300.dp)
                                            )

                                            // Hình bóng đèn bật
                                            Image(
                                                painter = painterResource(id = R.drawable.bulb),
                                                modifier = Modifier.padding(start = 8.dp).size(24.dp),
                                                contentDescription = ""
                                            )

                                            // Các ngày trong tuần (M, T, W, ... S)
                                            listOf("M", "T", "W", "T", "F", "S", "S").forEach { day ->
                                                Box(
                                                    modifier = Modifier
                                                        .background(
                                                            color = Color.White, // Nền trắng
                                                            shape = RoundedCornerShape(8.dp) // Bo tròn góc hộp
                                                        )
                                                        .padding(16.dp), // Khoảng cách bên trong Box
                                                    contentAlignment = Alignment.Center // Căn giữa nội dung
                                                ) {
                                                    Text(
                                                        text = day, // Hiển thị chữ ngày
                                                        color = Color.Black, // Màu chữ
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                }
                                            }
                                        }
                                    }

                                    // Dòng kẻ phân cách
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Box(
                                        modifier = Modifier
                                            .padding(start = 12.dp, end = 12.dp)
                                            .fillMaxWidth()
                                            .height(1.dp)
                                            .background(Color.LightGray) // Màu xám nhạt
                                    )

                                    // Phần Usage: Thông tin sử dụng điện
                                    Column(
                                        modifier = Modifier
                                            .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                                            .fillMaxSize(),
                                        horizontalAlignment = Alignment.Start,
                                        verticalArrangement = Arrangement.Top
                                    ) {
                                        Text("Usages", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 15.sp)

                                        // Thông tin "Use Today"
                                        Row(
                                            modifier = Modifier
                                                .padding(top = 12.dp)
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text("Use Today", color = Color.LightGray)
                                            Row {
                                                Text("50", fontSize = 20.sp, modifier = Modifier.offset(y = -5.dp), color = Color.LightGray)
                                                Spacer(modifier = Modifier.width(2.dp))
                                                Text("watt", color = Color.LightGray)
                                            }
                                        }

                                        // Thông tin "Use Week"
                                        Row(
                                            modifier = Modifier
                                                .padding(top = 12.dp)
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text("Use Week", color = Color.LightGray)
                                            Row {
                                                Text("500", fontSize = 20.sp, modifier = Modifier.offset(y = -5.dp), color = Color.LightGray)
                                                Spacer(modifier = Modifier.width(2.dp))
                                                Text("kwh", color = Color.LightGray)
                                            }
                                        }

                                        // Thông tin "Use Month"
                                        Row(
                                            modifier = Modifier
                                                .padding(top = 12.dp)
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text("Use Month", color = Color.LightGray)
                                            Row {
                                                Text("5000", fontSize = 20.sp, modifier = Modifier.offset(y = -5.dp), color = Color.LightGray)
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
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth() // Đảm bảo Row chiếm toàn bộ chiều rộng
                                            .padding(top = 12.dp, start = 12.dp, end = 8.dp), // Khoảng cách bên trong Row
                                        horizontalArrangement = Arrangement.SpaceBetween, // Đẩy các phần tử ra hai bên
                                        verticalAlignment = Alignment.CenterVertically // Căn giữa theo chiều dọc
                                    ) {
                                        // Nội dung bên trái (Schedule + số 3)
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = "Schedule",
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 18.sp
                                            )
                                            Spacer(modifier = Modifier.width(4.dp)) // Khoảng cách giữa "Schedule" và Box
                                            Box(
                                                modifier = Modifier
                                                    .size(20.dp) // Kích thước Box
                                                    .background(color = Color.Blue, shape = RoundedCornerShape(6.dp)), // Màu nền
                                                contentAlignment = Alignment.Center // Đưa nội dung bên trong vào giữa Box
                                            ) {
                                                Text(
                                                    text = "3", // Nội dung số 3
                                                    color = Color.White, // Màu chữ
                                                    fontWeight = FontWeight.Bold,
                                                    style = androidx.compose.ui.text.TextStyle(fontSize = 12.sp) // Kích thước chữ
                                                )
                                            }
                                        }

                                        // Nội dung bên phải (Icon trong Box)
                                        Box(
                                            modifier = Modifier
                                                .padding(end = 12.dp)
                                                .size(36.dp) // Kích thước Box
                                                .background(color = Color.White, shape = RoundedCornerShape(6.dp)), // Màu nền và góc bo
                                            contentAlignment = Alignment.Center // Đưa Icon vào giữa Box
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Add,
                                                contentDescription = "Add",
                                                modifier = Modifier.size(24.dp) // Kích thước Icon
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Column {
                        SmartLampCard(true)
                        SmartLampCard(true)
                        SmartLampCard(true)
                        SmartLampCard(true)
                    }
                }
            }
        }
    )
}

@Composable
fun SmartLampCard(isTablet: Boolean) {
    val endpadding = if (isTablet) 110.dp else 64.dp
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