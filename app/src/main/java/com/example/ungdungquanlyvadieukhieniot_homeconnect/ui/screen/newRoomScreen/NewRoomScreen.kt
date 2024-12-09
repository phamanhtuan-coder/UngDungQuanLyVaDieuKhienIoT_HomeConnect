package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.newRoomScreen


import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType


/** Giao diện màn hình Tạo phòng mới (New Room Screen)
 * -----------------------------------------
 * Người viết: Phạm Xuân Nhân
 * Ngày viết: 08/12/2024
 * Lần cập nhật cuối: 09/12/2024
 * -----------------------------------------
 * Input: Không có
 *
 * Output: Column chứa các thành phần giao diện của màn hình tạo phòng mới
 */


@Preview(showBackground = true)


@Composable
fun NewRoomScreen() {
    // Nhận cấu hình màn hình
    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp.dp
    val screenWidthDp = configuration.screenWidthDp.dp

    // Xác định xem nếu chúng ta đang ở chế độ ngang
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE


    //  kích thước phản hồi
    val horizontalPadding = when {
        screenWidthDp < 360.dp -> 8.dp
        screenWidthDp < 600.dp -> 16.dp
        else -> 32.dp
    }

    val verticalSpacing = when {
        screenHeightDp < 600.dp -> 8.dp
        screenHeightDp < 800.dp -> 12.dp
        else -> 16.dp
    }

    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    val options = listOf("Option 1", "Option 2", "Option 3")
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.align(Alignment.Center)) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(horizontal = horizontalPadding)
                    .verticalScroll(rememberScrollState())
                    .heightIn(max = 800.dp)
                    .widthIn(max = 600.dp),
                verticalArrangement = if (isLandscape) Arrangement.Center else Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(verticalSpacing)
                ) {
                    Text(
                        text = "Tạo phòng mới",
                        fontSize = if (isLandscape) 24.sp else 32.sp,
                        fontWeight = FontWeight.Bold
                    )

                }

                Spacer(modifier = Modifier.height(verticalSpacing * 2))

                // ID phòng
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = { Text("ID phòng") },
                    leadingIcon = { Icon(Icons.Filled.Info, contentDescription = "Info Icon") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(verticalSpacing * 2))

                // Tên phòng
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = { Text("Tên phòng") },
                    leadingIcon = { Icon(Icons.Filled.Info, contentDescription = "Info Icon") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(verticalSpacing))

                //Chọn thiết bị chưa có phòng

                OutlinedTextField(
                    value = selectedOption,
                    onValueChange = { selectedOption = it },
                    label = { Text("Chọn thiết bị chưa có phòng") },
                    leadingIcon = { Icon(Icons.Filled.Info, contentDescription = "Info Icon") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = !expanded },
                    readOnly = true
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .background(Color.White)
                        .padding(horizontal = horizontalPadding)
                        .verticalScroll(rememberScrollState())
                        .heightIn(max = 800.dp)
                        .widthIn(max = 600.dp),

                ) {
                    options.forEach { option ->
                        DropdownMenuItem(text = { Text(option) },
                            onClick = {
                                selectedOption = option
                                expanded = false
                            })
                    }
                }
                Spacer(modifier = Modifier.height(verticalSpacing))

                // Nút Tạo phòng
                Button(
                    onClick = { /* xử lý tạo phòng */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(if (isLandscape) 40.dp else 50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA726)),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Tạo phòng",
                            color = Color.White,
                            fontSize = if (isLandscape) 16.sp else 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
                TextButton(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(if (isLandscape) 40.dp else 50.dp),
                ) {
                    Text("⬅ Quay lại")
                }



                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}



