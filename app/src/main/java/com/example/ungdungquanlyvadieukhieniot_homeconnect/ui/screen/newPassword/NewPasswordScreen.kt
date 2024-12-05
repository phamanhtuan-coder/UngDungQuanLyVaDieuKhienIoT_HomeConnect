package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.NewPassword

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

/** Giao diện màn hình Tạo mật khẩu mới (NewPassword Screen)
 * -----------------------------------------
 * Người viết: Phạm Xuân Nhân
 * Ngày viết: 03/12/2024
 * Lần cập nhật cuối: 04/12/2024
 * -----------------------------------------
 * Input: Không có
 *
 * Output: Column chứa các thành phần giao diện của màn hình Tạo mật khẩu mới
 */

@Preview(showBackground = true)
@Composable
fun NewPasswordScreen() {
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
    var bien=false

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),

        )
    {
        Box(modifier = Modifier.align(Alignment.Center)) {
            Column(
                modifier = Modifier
                    .padding(horizontal = horizontalPadding)
                    .verticalScroll(rememberScrollState())
                    .heightIn(max = 800.dp)
                    .widthIn(max = 600.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = if (isLandscape) Arrangement.Center else Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Tạo khẩu mới",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Mật khẩu mới của bạn phải khác với mật khẩu đã sử dụng trước đó.",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))

                // MẬT KHẨU MỚI
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("MẬT KHẨU MỚI") },
                    leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Lock Icon") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))

                // NHẬP LẠI MẬT KHẨU MỚI
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = { Text("NHẬP LẠI MẬT KHẨU MỚI ") },
                    leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Lock Icon") },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))

                // điều kiện
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = verticalSpacing),

                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text =if (bien) "✔ Phải có ít nhất 8 ký tự" else "✘ Phải có ít nhất 8 ký tự",
                        color = if (bien) Color.Green else Color.Red,
                        fontSize = 12.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = verticalSpacing),

                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (bien) "✔ Phải chứa một ký tự đặc biệt" else "✘ Phải chứa một ký tự đặc biệt" ,
                        color = if (bien) Color.Green else Color.Red,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Nút Đặt lại mật khẩu
                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Đặt lại mật khẩu")
                }
                Spacer(modifier = Modifier.height(16.dp))

                TextButton(onClick = { }) {
                    Text("⬅ Quay lại đăng nhập")
                }
            }
        }
    }
}