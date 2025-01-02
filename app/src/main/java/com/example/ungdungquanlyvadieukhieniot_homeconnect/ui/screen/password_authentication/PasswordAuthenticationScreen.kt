package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.password_authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme

/**
 * Màn hình Xác thực mật khẩu
 * ----------------------------
 * Người viết: Phạm Xuân Nhân
 * Ngày viết: 04/12/2024
 * Lần cập nhật cuối: 31/12/2024
 * ----------------------------
 * @param navController: Đối tượng điều khiển điều hướng
 * @return Scaffold chứa toàn bộ nội dung màn hình xác thực mật khẩu
 */

@Composable
fun PasswordAuthenticationScreen(navController: NavHostController) {
    //Todo: bổ sung logic và bổ sung navigation khi di chuyển
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        val passwordState = remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        val configuration = LocalConfiguration.current
        val isTablet = configuration.screenWidthDp >= 600

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background),
            containerColor = colorScheme.background
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = if (isTablet) 32.dp else 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            ) {

                // Tiêu đề
                Text(
                    text = "Xác thực mật khẩu",
                    fontSize = if (isTablet) 28.sp else 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.primary
                )
                Text(
                    text = "Vui lòng nhập mật khẩu để tiếp tục.",
                    fontSize = 14.sp,
                    color = colorScheme.onBackground.copy(alpha = 0.6f)
                )

                // Trường nhập mật khẩu
                OutlinedTextField(
                    value = passwordState.value,
                    onValueChange = { passwordState.value = it },
                    placeholder = { Text("Nhập mật khẩu của bạn") },
                    singleLine = true,
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
                    shape = RoundedCornerShape(25),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = colorScheme.onBackground,  // Màu text khi TextField được focus
                        unfocusedTextColor = colorScheme.onBackground.copy(alpha = 0.7f),  // Màu text khi TextField không được focus
                        focusedContainerColor = colorScheme.onPrimary,
                        unfocusedContainerColor = colorScheme.onPrimary,
                        focusedIndicatorColor = colorScheme.primary,
                        unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))

                // Nút xác thực mật khẩu
                Button(
                    onClick = {
                        /* TODO: Xử lý xác thực mật khẩu */
                    },
                    modifier = Modifier
                        .width(if (isTablet) 300.dp else 200.dp)
                        .height(if (isTablet) 56.dp else 48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "Xác thực",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorScheme.onPrimary
                    )
                }

                // Nút quay lại màn hình trước đó
                TextButton(onClick = {
                    navController.popBackStack()
                }) {
                    Text(
                        text = "Quay lại",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PasswordAuthenticationScreenPreview() {
    PasswordAuthenticationScreen(navController = rememberNavController())
}