package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.NutHome

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
@Preview(showBackground = true)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    return Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            /*
            * Hiển thị Header
             */ Header()

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
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(0.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
            ) {
                /*
                * Hiển thị thông tin thời tiết
                 */
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(8.dp)
                        .background(Color.Blue)
                        .clip(
                            RoundedCornerShape(
                                bottomStartPercent = 25,
                                bottomEndPercent = 25
                            )
                        ),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(RoundedCornerShape(25))
                            .fillMaxWidth()
                            .background(Color.LightGray),
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Filled.Info, contentDescription = "null")
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = "May 16, 2023 10:05 am",
                                    color = Color.Black,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = "Cloudy",
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = "Hanoi, Vietnam", color = Color.Black, fontSize = 16.sp)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "25°C", color = Color.Black, fontSize = 40.sp)
                        }
                        Spacer(modifier = Modifier.height(3.dp))
                        HorizontalDivider(
                            modifier = modifier.padding(horizontal = 5.dp),
                            thickness = 1.dp,
                            color = Color.Blue
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            /*
                            * Thông tin về độ ẩm
                             */
                            Card(
                                modifier = Modifier
                                    .background(Color.White),
                                content = {
                                    Column(
                                        modifier = Modifier.padding(2.dp),
                                        verticalArrangement = Arrangement.SpaceBetween,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 2.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            IconButton(
                                                modifier = Modifier
                                                    .padding(horizontal = 2.dp)
                                                    .clip(CircleShape),
                                                colors = IconButtonDefaults.iconButtonColors(
                                                    containerColor = Color.White,
                                                    contentColor = Color.Black
                                                ),
                                                onClick = { /*TODO*/ }
                                            ) {
                                                Icon(
                                                    Icons.Filled.Notifications,
                                                    contentDescription = ""
                                                )
                                            }
                                            Spacer(modifier = Modifier.width(3.dp))
                                            Text(
                                                text = "97%",
                                                color = Color.Black,
                                                fontSize = 16.sp
                                            )
                                        }
                                        Row(
                                            modifier = Modifier.padding(horizontal = 2.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        )
                                        {
                                            Text(
                                                text = "Humidity",
                                                color = Color.Black,
                                                fontSize = 16.sp
                                            )
                                        }

                                    }
                                }
                            )
                            /*
                            * Thông tin về tầm nhìn
                            */
                            Card(
                                modifier = Modifier
                                    .background(Color.White),
                                content = {
                                    Column(
                                        modifier = Modifier.padding(2.dp),
                                        verticalArrangement = Arrangement.SpaceBetween,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 2.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            IconButton(
                                                modifier = Modifier
                                                    .padding(horizontal = 2.dp)
                                                    .clip(CircleShape),
                                                colors = IconButtonDefaults.iconButtonColors(
                                                    containerColor = Color.White,
                                                    contentColor = Color.Black
                                                ),
                                                onClick = { /*TODO*/ }
                                            ) {
                                                Icon(
                                                    Icons.Filled.Notifications,
                                                    contentDescription = ""
                                                )
                                            }
                                            Spacer(modifier = Modifier.width(3.dp))
                                            Text(
                                                text = "7 km",
                                                color = Color.Black,
                                                fontSize = 16.sp
                                            )
                                        }
                                        Row(
                                            modifier = Modifier.padding(horizontal = 2.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        )
                                        {
                                            Text(
                                                text = "Visibility",
                                                color = Color.Black,
                                                fontSize = 16.sp
                                            )
                                        }

                                    }
                                }
                            )
                            /*
                           * Thông tin về gió
                           */
                            Card(
                                modifier = Modifier
                                    .background(Color.White),
                                content = {
                                    Column(
                                        modifier = Modifier.padding(2.dp),
                                        verticalArrangement = Arrangement.SpaceBetween,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 2.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            IconButton(
                                                modifier = Modifier
                                                    .padding(horizontal = 2.dp)
                                                    .clip(CircleShape),
                                                colors = IconButtonDefaults.iconButtonColors(
                                                    containerColor = Color.White,
                                                    contentColor = Color.Black
                                                ),
                                                onClick = { /*TODO*/ }
                                            ) {
                                                Icon(
                                                    Icons.Filled.Notifications,
                                                    contentDescription = ""
                                                )
                                            }
                                            Spacer(modifier = Modifier.width(3.dp))
                                            Text(
                                                text = "3 km/h",
                                                color = Color.Black,
                                                fontSize = 16.sp
                                            )
                                        }
                                        Row(
                                            modifier = Modifier.padding(horizontal = 2.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        )
                                        {
                                            Text(
                                                text = "NE Wind",
                                                color = Color.Black,
                                                fontSize = 16.sp
                                            )
                                        }

                                    }
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Column {
                        Row(
                            modifier = Modifier.padding(8.dp).fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "Spaces",
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(150.dp))
                            TextButton(
                                onClick = { /*TODO*/ },
                                colors = ButtonColors(
                                    containerColor = Color.White,
                                    contentColor = Color.Blue,
                                    disabledContainerColor = Color.White,
                                    disabledContentColor = Color.Gray
                                )
                            ) {
                                Text(text = "Xem thêm", color = Color.Blue)
                            }

                        }
                        LazyRow {
                            items(5) {
                                Card(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .clip(RoundedCornerShape(5))
                                        .background(Color.LightGray)
                                        .width(150.dp)
                                        .height(150.dp),
                                    content = {
                                        Column(
                                            modifier = Modifier.fillMaxSize()
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .padding(10.dp)
                                                    .background(Color.Magenta),
                                            ) {
                                                Text(
                                                    text = "19 C",
                                                    color = Color.White,
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                            Spacer(modifier = Modifier.height(5.dp))
                                            Image(
                                                imageVector = Icons.Filled.Info,
                                                contentDescription = "null",
                                                modifier = Modifier
                                                    .align(Alignment.CenterHorizontally)
                                                    .padding(8.dp)
                                                    .fillMaxWidth()
                                            )
                                            Text(
                                                text = "Living Room",
                                                color = Color.Black,
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold,
                                                textAlign = TextAlign.Center
                                            )
                                            Text(
                                                text = "2 Devices",
                                                color = Color.Black,
                                                fontSize = 14.sp,
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }

                }

            }


        }
    )

}