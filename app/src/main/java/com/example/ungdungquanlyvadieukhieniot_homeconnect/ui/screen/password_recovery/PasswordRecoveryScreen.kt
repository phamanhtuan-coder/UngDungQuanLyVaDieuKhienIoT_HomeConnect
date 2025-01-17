package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.password_recovery

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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.Screens
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.validation.ValidationUtils

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
fun PasswordRecoveryScreen(
    navController: NavHostController,
    viewModel: PasswordRecoveryViewModel = viewModel()
) {
    val checkEmailState by viewModel.checkEmailState.collectAsState()
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        val emailState = remember { mutableStateOf("0306221391@caothang.edu.vn") }
        val configuration = LocalConfiguration.current
        val isTablet = configuration.screenWidthDp >= 600

        // Biến trạng thái để lưu thông báo lỗi
        val emailErrorState = remember { mutableStateOf("") }
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
                    onValueChange = {
                        emailState.value = it
                        // Kiểm tra email ngay khi giá trị thay đổi
                        emailErrorState.value = ValidationUtils.validateEmail(it)
                    },
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
                        focusedTextColor = colorScheme.onBackground,  // Màu text khi TextField được focus
                        unfocusedTextColor = colorScheme.onBackground.copy(alpha = 0.7f),  // Màu text khi TextField không được focus
                        focusedContainerColor = colorScheme.onPrimary,
                        unfocusedContainerColor = colorScheme.onPrimary,
                        focusedIndicatorColor = colorScheme.primary,
                        unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                    ),
                    leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null) }
                )
                Text(
                    text = emailErrorState.value,
                    fontSize = 14.sp,
                    color = if (emailErrorState.value == "Email hợp lệ.") Color.Green else colorScheme.error
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Nút khôi phục mật khẩu
                Button(
                    onClick = {
                        viewModel.checkEmail(emailState.value)
                    },
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

                when (checkEmailState) {
                    is CheckEmailState.Success -> {
                        LaunchedEffect(Unit) {
                            navController.navigate(Screens.OTP.createRoute("reset_password", emailState.value))
                        }

                    }

                    is CheckEmailState.Error -> {
                        emailErrorState.value = (checkEmailState as CheckEmailState.Error).message
                    }

                    is CheckEmailState.Loading -> {
                        CircularProgressIndicator()
                    }

                    is CheckEmailState.Idle -> {
                        // Do nothing
                    }
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