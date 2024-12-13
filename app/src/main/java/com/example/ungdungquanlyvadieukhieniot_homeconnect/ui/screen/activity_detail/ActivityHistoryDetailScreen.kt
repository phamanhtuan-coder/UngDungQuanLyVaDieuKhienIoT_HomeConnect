package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.activity_detail


import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/** Giao diện màn hình Cập nhật Mật Khẩu (Update PassWord Screen)
 * -----------------------------------------
 * Người viết: Phạm Xuân Nhân
 * Ngày viết: 09/12/2024
 * Lần cập nhật cuối: 10/12/2024
 * -----------------------------------------
 * Input: Không có
 *
 * Output: Column chứa các thành phần giao diện của màn hình Cập nhật Mật Khẩu
 */

@Preview(showBackground = true)
@Composable
fun ActivityHistoryScreenDetailScreen() {
    var time by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp

    // Xác định xem nếu chúng ta đang ở chế độ ngang
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    //  kích thước phản hồi
    val horizontalPadding = when {
        screenWidthDp < 360.dp -> 8.dp
        screenWidthDp < 600.dp -> 16.dp
        else -> 32.dp
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),

        )
    {
        Box(modifier = Modifier.align(Alignment.TopCenter)) {
            Column(
                modifier = Modifier
                    .padding(horizontal = horizontalPadding)
                    .verticalScroll(rememberScrollState())
                    .heightIn(max = 700.dp)
                    .widthIn(max = 600.dp),
                verticalArrangement = if (isLandscape) Arrangement.Center else Arrangement.SpaceBetween,

            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }

                    Text(
                        text = "Tên hoạt động",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))


                OutlinedTextField(
                    value = time,
                    onValueChange = { time = it },
                    label = { Text("Thời gian") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))


                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("Nội dung hoạt động") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { /* Xóa logic */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    ) {
                        Text(text = "Xóa", color = Color.Black)
                    }

                    Button(
                        onClick = { /*Quay lại logic */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    ) {
                        Text(text = "Quay lại", color = Color.Black)
                    }
                }
            }
        }
    }
}