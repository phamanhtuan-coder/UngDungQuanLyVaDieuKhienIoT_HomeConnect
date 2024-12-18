package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.password_recovery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme

/**
 * Màn hình Khôi phục mật khẩu
 * ----------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 01/12/2024
 * Lần cập nhật cuối: 18/12/2024
 * ----------------------------
 * @param navController: Đối tượng điều khiển điều hướng
 * @return Scafold chứa toàn bộ nội dung màn hình khôi phục mật khẩu
 * ----------------------------
 * Người cập nhật: Phạm Anh Tuấn
 * Ngày cập nhật: 18/12/2024
 * Nội dung cập nhật:
 * - Sử dụng AppTheme cho màu sắc
 * - Đồng bộ bố cục với màn hình đăng nhập
 * - Bổ sung chức năng quay lại màn hình đăng nhập
 */

@Composable
fun PasswordRecoveryScreen(navController: NavHostController) {
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        val emailState = remember { mutableStateOf("") }
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
                    text = "Khôi phục mật khẩu",
                    fontSize = if (isTablet) 28.sp else 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.primary
                )
                Text(
                    text = "Vui lòng nhập email để nhận liên kết khôi phục.",
                    fontSize = 14.sp,
                    color = colorScheme.onBackground.copy(alpha = 0.6f)
                )

                // Trường nhập email
                OutlinedTextField(
                    value = emailState.value,
                    onValueChange = { emailState.value = it },
                    label = { Text("Email") },
                    placeholder = { Text("Nhập email của bạn") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .width(if (isTablet) 500.dp else 400.dp)
                        .height(if (isTablet) 80.dp else 70.dp),
                    shape = RoundedCornerShape(25),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = colorScheme.onPrimary,
                        unfocusedContainerColor = colorScheme.onPrimary,
                        focusedIndicatorColor = colorScheme.primary,
                        unfocusedIndicatorColor= colorScheme.onBackground.copy(alpha = 0.5f)
                    )
                )

                // Nút khôi phục mật khẩu
                Button(
                    onClick = { /* TODO: Xử lý khôi phục mật khẩu */ },
                    modifier = Modifier
                        .width(if (isTablet) 300.dp else 200.dp)
                        .height(if (isTablet) 56.dp else 48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "Khôi phục mật khẩu",
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PasswordRecoveryScreenPreview() {
    PasswordRecoveryScreen(navController = rememberNavController())
}