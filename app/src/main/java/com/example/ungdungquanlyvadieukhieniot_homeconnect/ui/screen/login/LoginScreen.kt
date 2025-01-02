package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.Screens
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme

/**
 * Màn hình đăng nhập
 * ---------------------
 * Người viết: Phạm Xuân Nhân
 * Ngày viết: 2/12/2024
 * Lần cập nhật cuối: 15/12/2024
 * ---------------------
 * @param navController: Đối tượng điều khiển điều hướng
 * @return Box chứa toàn bộ nội dung màn hình đăng nhập
 * ---------------------
 * Người cập nhật: Phạm Anh Tuấn
 * Ngày cập nhật: 15/12/2024
 * Nội dung cập nhật:
 * - Thêm chức năng hiển thị mật khẩu
 * - Thêm chức năng chuyển tới màn hình đăng ký
 * - Thêm chức năng chuyển tới màn hình quên mật khẩu
 * - Thêm chức năng kiểm tra màn hình hiển thị là máy tính bảng hay điện thoại
 * - Chỉnh sửa layout cho phù hợp với thiết kế
 * ---------------------
 */

@Composable
fun LoginScreen(
    navController: NavHostController
) {
    AppTheme {
        val configuration = LocalConfiguration.current
        val isTablet = configuration.screenWidthDp >= 600
        val colorScheme = MaterialTheme.colorScheme
        val emailState = remember { mutableStateOf("") }
        val passwordState = remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background),
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = if (isTablet) 32.dp else 16.dp)
                    .verticalScroll(rememberScrollState())
                    .imePadding(),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Tiêu đề đăng nhập
                Text(
                    text = "Đăng nhập",
                    fontSize = if (isTablet) 28.sp else 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.primary
                )
                Text(
                    text = "Hãy đăng nhập để tiếp tục",
                    fontSize = 14.sp,
                    color = colorScheme.onBackground.copy(alpha = 0.6f)
                )

                // Trường nhập Email
                OutlinedTextField(
                    shape = RoundedCornerShape(25),
                    singleLine = true,
                    value = emailState.value,
                    onValueChange = { emailState.value = it },
                    placeholder = { Text("Nhập email của bạn") },
                    leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .width(if (isTablet) 500.dp else 400.dp)
                        .height(if (isTablet) 80.dp else 70.dp),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = colorScheme.onBackground,  // Màu text khi TextField được focus
                        unfocusedTextColor = colorScheme.onBackground.copy(alpha = 0.7f),  // Màu text khi TextField không được focus
                        focusedContainerColor = colorScheme.onPrimary,
                        unfocusedContainerColor = colorScheme.onPrimary,
                        focusedIndicatorColor = colorScheme.primary,
                        unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                    )
                )

                // Trường nhập mật khẩu
                OutlinedTextField(
                    shape = RoundedCornerShape(25),
                    singleLine = true,
                    value = passwordState.value,
                    onValueChange = {
                        passwordState.value = it
                    },
                    placeholder = { Text("Mật khẩu") },
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
                        .width(if (isTablet) 500.dp else 400.dp)
                        .height(if (isTablet) 80.dp else 70.dp),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = colorScheme.onBackground,  // Màu text khi TextField được focus
                        unfocusedTextColor = colorScheme.onBackground.copy(alpha = 0.7f),  // Màu text khi TextField không được focus
                        focusedContainerColor = colorScheme.onPrimary,
                        unfocusedContainerColor = colorScheme.onPrimary,
                        focusedIndicatorColor = colorScheme.primary,
                        unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                    )
                )

                // Nút quên mật khẩu
                Row(
                    modifier = Modifier
                        .width(if (isTablet) 500.dp else 400.dp)
                        .height(if (isTablet) 80.dp else 70.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        modifier = Modifier.weight(1f),
                        onClick = {
                        navController.navigate(Screens.RecoverPassword.route)
                    }) {
                        Text(
                            text = "Quên mật khẩu?",
                            fontSize = 14.sp,
                            color = colorScheme.primary
                        )
                    }
                }

                // Nút đăng nhập
                Button(
                    onClick = {
                        /* TODO: Xử lý khi nhấn nút đăng nhập, kiểm tra và báo lỗi v.v*/
                        navController.navigate("home_graph") {
                            // Remove welcome and login from back stack
                            popUpTo(Screens.Welcome.route) {
                                inclusive = true
                            }
                        }
                    },
                    modifier = Modifier
                        .width(if (isTablet) 300.dp else 200.dp)
                        .height(if (isTablet) 56.dp else 48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "Đăng nhập",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorScheme.onPrimary
                    )
                }

                // Chuyển tới đăng ký
                Row(
                    modifier = Modifier.fillMaxWidth(if (isTablet) 0.8f else 0.9f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Chưa có tài khoản?",
                        fontSize = 14.sp,
                        color = colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    TextButton(onClick = { navController.navigate(Screens.Register.route) }) {
                        Text(
                            text = "Đăng ký",
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}