package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.wifi_connection

import android.content.Context
import android.graphics.drawable.Icon
import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.Screens
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.access_point_connection.isTablet
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.access_point_connection.rememberResponsiveLayoutConfig
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.validation.ValidationUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

/** Giao diện màn hình WifiConnection (WifiConnectionScreen)
 * -----------------------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 31/12/2024
 * Lần cập nhật cuối: 31/12/2024
 * -----------------------------------------
 * @param navController Đối tượng điều khiển điều hướng.
 * @return Scaffold chứa toàn bộ nội dung
 */
@Composable
fun WifiConnectionScreen(
    navController: NavHostController
) {
    val layoutConfig = rememberResponsiveLayoutConfig() // Lấy LayoutConfig

    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        val context = LocalContext.current

        val ssidState = remember { mutableStateOf("") }
        val ssidErrorState = remember { mutableStateOf("") }

        val passwordState = remember { mutableStateOf("") }
        val passwordErrorState = remember { mutableStateOf("") }
        val passwordVisible = remember { mutableStateOf(false) }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = colorScheme.background,
            topBar = {
                Header(navController, "Back", "Kết nối wifi")
            },
            bottomBar = {
                MenuBottom(navController)
            },
            content = { innerPadding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize() // Chiếm toàn bộ kích thước của màn hình
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.Center, // Sắp xếp các item theo chiều dọc, bắt đầu từ trên xuống.
                    horizontalAlignment = Alignment.CenterHorizontally // Căn chỉnh các item theo chiều ngang vào giữa.
                ) {
                    // Tiêu đề
                    item {
                        // Cột chứa các phần tử con
                        Column (
                            modifier = Modifier
                                .fillMaxSize()
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
                                // Văn bản tiêu đề "ĐIỂM TRUY CẬP"
                                Text(
                                    "Kết nối wifi",
                                    fontSize = layoutConfig.headingFontSize, // Font size linh hoạt
                                    fontWeight = FontWeight.Bold,
                                    color = colorScheme.onBackground
                                )

                                // Khoảng cách giữa tiêu đề và TextField
                                Spacer(modifier = Modifier.height(layoutConfig.textFieldSpacing))

                                // Ô nhập liệu đầu tiên - SSID
                                OutlinedTextField(
                                    shape = RoundedCornerShape(25),
                                    singleLine = true,
                                    value = ssidState.value,
                                    onValueChange = {
                                        ssidState.value = it
                                        // Gọi hàm kiểm tra khi giá trị thay đổi
                                        ssidErrorState.value = ValidationUtils.validateSSID(it)
                                    },
                                    placeholder = { Text("SSID:") },
                                    leadingIcon = { Icon(Icons.Filled.Wifi, contentDescription = null) },
                                    modifier = Modifier
                                        .width(if (isTablet()) 500.dp else 400.dp)
                                        .height(if (isTablet()) 80.dp else 70.dp),
                                    colors = TextFieldDefaults.colors(
                                        focusedTextColor = colorScheme.onBackground,  // Màu text khi TextField được focus
                                        unfocusedTextColor = colorScheme.onBackground.copy(alpha = 0.7f),  // Màu text khi TextField không được focus
                                        focusedContainerColor = colorScheme.onPrimary,
                                        unfocusedContainerColor = colorScheme.onPrimary,
                                        focusedIndicatorColor = colorScheme.primary,
                                        unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                                    )
                                )

                                Spacer(modifier = Modifier.height(layoutConfig.textFieldSpacing))

                                // Trường nhập mật khẩu
                                OutlinedTextField(
                                    shape = RoundedCornerShape(25),
                                    singleLine = true,
                                    value = passwordState.value,
                                    onValueChange = {
                                        passwordState.value = it
                                        // Gọi hàm kiểm tra mật khẩu khi giá trị thay đổi
                                        passwordErrorState.value = ValidationUtils.validatePassword(it)
                                    },
                                    placeholder = { Text("Mật khẩu:") },
                                    leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
                                    trailingIcon = {
                                        IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                                            Icon(
                                                imageVector = if (passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                                contentDescription = if (passwordVisible.value) "Hide password" else "Show password"
                                            )
                                        }
                                    },
                                    visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                    modifier = Modifier
                                        .width(if (isTablet()) 500.dp else 400.dp)
                                        .height(if (isTablet()) 80.dp else 70.dp),
                                    colors = TextFieldDefaults.colors(
                                        focusedTextColor = colorScheme.onBackground,  // Màu text khi TextField được focus
                                        unfocusedTextColor = colorScheme.onBackground.copy(alpha = 0.7f),  // Màu text khi TextField không được focus
                                        focusedContainerColor = colorScheme.onPrimary,
                                        unfocusedContainerColor = colorScheme.onPrimary,
                                        focusedIndicatorColor = colorScheme.primary,
                                        unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                                    )
                                )

                                Spacer(modifier = Modifier.height(layoutConfig.textFieldSpacing))

                                // Nút đăng nhập
                                Button(
                                    onClick = {
                                        /* TODO: Xử lý khi nhấn nút kết nối, kiểm tra và báo lỗi v.v và quây trở lại màn hình trước đó*/
                                        // Kiểm tra dữ liệu đầu vào
                                        if (ssidState.value.isEmpty() || passwordState.value.isEmpty()) {
                                            // Báo lỗi nếu SSID hoặc mật khẩu rỗng
                                            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin Wi-Fi!", Toast.LENGTH_SHORT).show()
                                        } else {
                                            // Gửi thông tin Wi-Fi qua UDP
                                            // Gọi hàm
                                            sendWiFiCredentialsUDP(context, "192.168.4.1", 4210, ssidState.value, passwordState.value)

                                            // Hiển thị thông báo thành công (giả sử việc gửi luôn thành công)
                                            Toast.makeText(context, "Đã gửi thông tin Wi-Fi!", Toast.LENGTH_SHORT).show()

                                            // Quay lại màn hình trước đó (nếu cần)
                                            navController.popBackStack()
                                        }
                                    },
                                    modifier = Modifier
                                        .width(if (isTablet()) 300.dp else 200.dp)
                                        .height(if (isTablet()) 56.dp else 48.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
                                    shape = RoundedCornerShape(50)
                                ) {
                                    Text(
                                        text = "Kết Nối",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = colorScheme.onPrimary
                                    )
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun WifiConnectionScreenPreview() {
    WifiConnectionScreen(navController = rememberNavController())
}

fun sendWiFiCredentialsUDP(context: Context, espIp: String, port: Int, ssid: String, password: String) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val socket = DatagramSocket()
            val address = InetAddress.getByName(espIp)

            val data = "$ssid,$password"
            val buffer = data.toByteArray()
            val packet = DatagramPacket(buffer, buffer.size, address, port)

            socket.send(packet)
            println("Gói UDP đã được gửi đến $espIp:$port")

            // Đọc phản hồi từ ESP
            val responseBuffer = ByteArray(256)
            val responsePacket = DatagramPacket(responseBuffer, responseBuffer.size)
            socket.receive(responsePacket)
            val response = String(responsePacket.data, 0, responsePacket.length)
            println("Phản hồi từ ESP: $response")

            socket.close()
        } catch (e: Exception) {
            println("Lỗi khi gửi hoặc nhận gói UDP: ${e.message}")
        }
    }
}
