package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.new_password

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.Screens
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.access_point_connection.isTablet
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.wifi_connection.WifiConnectionScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme

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

// sửa lại các chỗ lỗi giống UpdatePasswordScreen
@Composable
fun NewPasswordScreen(
    navController: NavHostController
) {
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        val configuration = LocalConfiguration.current
        val screenHeightDp = configuration.screenHeightDp.dp
        val screenWidthDp = configuration.screenWidthDp.dp
        val passwordState = remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        val passwordState2 = remember { mutableStateOf("") }
        var passwordVisible2 by remember { mutableStateOf(false) }

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

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background),
            containerColor = colorScheme.background
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
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
                            fontSize = if (isTablet()) 28.sp else 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorScheme.primary,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Mật khẩu mới của bạn phải khác với mật khẩu đã sử dụng trước đó.",
                            fontSize = 14.sp,
                            color  = colorScheme.onBackground.copy(alpha = 0.6f),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        // MẬT KHẨU MỚI
                        // Trường nhập mật khẩu
                        OutlinedTextField(
                            shape = RoundedCornerShape(25),
                            singleLine = true,
                            value = passwordState.value,
                            onValueChange = {
                                passwordState.value = it
                            },
                            placeholder = { Text("Mật khẩu:") },
                            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
                            trailingIcon = {
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                                    )
                                }
                            },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            modifier = Modifier
                                .width(if (isTablet()) 500.dp else 400.dp)
                                .height(if (isTablet()) 80.dp else 70.dp),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = colorScheme.onPrimary,
                                unfocusedContainerColor = colorScheme.onPrimary,
                                focusedIndicatorColor = colorScheme.primary,
                                unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        // NHẬP LẠI MẬT KHẨU MỚI
                        OutlinedTextField(
                            shape = RoundedCornerShape(25),
                            singleLine = true,
                            value = passwordState2.value,
                            onValueChange = {
                                passwordState2.value = it
                            },
                            placeholder = { Text("Nhập lại mật khẩu mới:") },
                            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
                            trailingIcon = {
                                IconButton(onClick = { passwordVisible2 = !passwordVisible2 }) {
                                    Icon(
                                        imageVector = if (passwordVisible2) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                        contentDescription = if (passwordVisible2) "Hide password" else "Show password"
                                    )
                                }
                            },
                            visualTransformation = if (passwordVisible2) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            modifier = Modifier
                                .width(if (isTablet()) 500.dp else 400.dp)
                                .height(if (isTablet()) 80.dp else 70.dp),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = colorScheme.onPrimary,
                                unfocusedContainerColor = colorScheme.onPrimary,
                                focusedIndicatorColor = colorScheme.primary,
                                unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Nút đăng nhập
                        Button(
                            onClick = {
                                /* TODO: Xử lý khi nhấn nút kết nối, kiểm tra và báo lỗi v.v và quây trở lại màn hình trước đó*/
                            },
                            modifier = Modifier
                                .width(if (isTablet()) 300.dp else 200.dp)
                                .height(if (isTablet()) 56.dp else 48.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
                            shape = RoundedCornerShape(50)
                        ) {
                            Text(
                                text = "Đổi mật khẩu",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = colorScheme.onPrimary
                            )
                        }

                        // Nút quay lại màn hình đăng nhập
                        TextButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Text(
                                text = "Quay lại màn hình đăng nhập",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun NewPasswordScreenPreiview(){
    NewPasswordScreen(navController = rememberNavController())
}