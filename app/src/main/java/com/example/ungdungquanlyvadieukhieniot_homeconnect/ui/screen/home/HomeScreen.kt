package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.DeviceCard
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.HouseSelection
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.NutHome
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.SpaceCard
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.WeatherInfo

/** Giao diện màn hình Trang chủ (Home Screen)
 * -----------------------------------------
 * Người viết: Phạm Anh Tuấn
 * Ngày viết: 29/11/2024
 * Lần cập nhật cuối: 29/11/2024
 * -----------------------------------------
 * Input:
 * @param modifier Modifier mở rộng để áp dụng cho layout (đã gán giá trị mặc dịnh).
 *
 * Output: Scaffold
 *
 * ---------------------------------------
 */
@Preview(showBackground = true,showSystemUi = true)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    return Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            /*
            * Hiển thị Header
             */
            Header("Home")
        },
        bottomBar = {
            /*
            * Hiển thị Thanh Menu dưới cùng
             */
            MenuBottom()
        },
        floatingActionButton = {
            /*
            * Hiển thị FAB action nếu coó
             */

        },
        floatingActionButtonPosition = FabPosition.End,
        content = {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .verticalScroll(rememberScrollState())
            ) {
                WeatherInfo()

                Spacer(modifier = Modifier.height(8.dp))

                // Không gian của nhà đang chọn
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HouseSelection(
                            houses = listOf("House 1", "House 2", "House 3"),
                            onManageHouseClicked = { /* TODO: Navigate */ }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Không gian",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        TextButton(
                            onClick = { /* TODO: Navigate */ },
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = Color.Blue
                            )
                        ) {
                            Text(text = "Xem thêm")
                        }
                    }

                    LazyRow(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(5) {
                            SpaceCard()
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Thiết bị hoạt động
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Thiết bị hoạt động",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        TextButton(
                            onClick = { /* TODO: Navigate */ },
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = Color.Blue
                            )
                        ) {
                            Text(text = "Xem thêm")
                        }
                    }

                    LazyRow(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(5) {
                            DeviceCard()
                        }
                    }
                }
            }




        }
    )

}