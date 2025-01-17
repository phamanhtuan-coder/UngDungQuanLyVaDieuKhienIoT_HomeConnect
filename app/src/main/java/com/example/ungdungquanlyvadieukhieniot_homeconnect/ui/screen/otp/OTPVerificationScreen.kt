package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.otp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme

@Composable
fun OtpScreen(
    navController: NavHostController,
    email: String,
    onVerificationSuccess: () -> Unit, // Thêm callback này
    title: String = "Nhập mã OTP", // Cho phép tùy chỉnh tiêu đề
    description: String = "Vui lòng nhập mã OTP vừa được gửi tới Email", // Cho phép tùy chỉnh mô tả
    viewModel: OTPViewModel = viewModel()
) {
    val sendOTPState by viewModel.sendOtpState.collectAsState()
    var sendSuccesful = ""

    LaunchedEffect(Unit) {
        viewModel.sendOTP(email)
    }

    val verifyEmailState by viewModel.verifyEmailState.collectAsState()
    var verifyEmailMessage = ""
    when (verifyEmailState) {
        is VerifyEmailState.Success -> {
            LaunchedEffect(Unit) {
                onVerificationSuccess()
            }
        }

        is VerifyEmailState.Error -> {
            verifyEmailMessage = "Xác thực Email thất bại! Email không tồn tại."
        }

        is VerifyEmailState.Loading -> {
            verifyEmailMessage = "Đang xác thực Email..."
        }

        else -> {
            // Khong lam gi
        }
    }

    when (sendOTPState) {
        is OTPState.Success -> {
            sendSuccesful = "Mã OTP đã được gửi tới Email của bạn."
        }

        is OTPState.Error -> {
            sendSuccesful = "Gửi mã OTP thất bại! Email không tồn tại."
        }

        is OTPState.Loading -> {
            sendSuccesful = "Đang gửi mã OTP..."
        }

        else -> {

        }
    }

    val verifyOTPState by viewModel.verifyOtpState.collectAsState()
    var verifyOTPMessage = ""
    when (verifyOTPState) {
        is OTPState.Success -> {

            when (title) {
                "Xác nhận Email" -> {
                    viewModel.confirmEmail(email)
                }

                else -> {
                    LaunchedEffect(Unit) {
                        onVerificationSuccess()
                    }
                }
            }

        }

        is OTPState.Error -> {
            verifyOTPMessage = "Xác thực OTP thất bại! Mã OTP không đúng."
        }

        is OTPState.Loading -> {
            CircularProgressIndicator()
        }

        else -> {
        }
    }

    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        val configuration = LocalConfiguration.current
        val isTablet = configuration.screenWidthDp >= 600
        val otpLength = 6 // Độ dài mã OTP
        val otpValue = remember { mutableStateListOf(*Array(otpLength) { "" }) } // Sử dụng MutableStateList
        val focusRequesters = List(otpLength) { FocusRequester() }

        Scaffold(
            containerColor = colorScheme.background
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = if (isTablet) 32.dp else 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
            ) {
                // Tiêu đề
                Text(
                    text = title,
                    fontSize = if (isTablet) 28.sp else 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.primary
                )
                Text(
                    text = sendSuccesful,
                    fontSize = 14.sp,
                    color =
                    when (sendOTPState) {
                        is OTPState.Success -> Color.Green
                        is OTPState.Loading -> Color.Yellow
                        else -> colorScheme.error
                    }
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = colorScheme.onBackground.copy(alpha = 0.6f)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(otpLength) { index ->
                        OutlinedTextField(
                            value = otpValue[index], // Lấy giá trị từ danh sách
                            onValueChange = { input ->
                                if (input.length <= 1 && input.all { it.isDigit() }) {
                                    otpValue[index] = input
                                    if (input.isNotEmpty() && index < otpLength - 1) {
                                        focusRequesters[index + 1].requestFocus()
                                    } else if (input.isEmpty() && index > 0) {
                                        focusRequesters[index - 1].requestFocus()
                                    }
                                }
                            },
                            modifier = Modifier
                                .size(if (isTablet) 60.dp else 50.dp)
                                .focusRequester(focusRequesters[index]),
                            singleLine = true,
                            textStyle = MaterialTheme.typography.bodyLarge.copy(
                                color = colorScheme.onBackground,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = if (index == otpLength - 1) ImeAction.Done else ImeAction.Next
                            ),
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = colorScheme.onBackground,
                                unfocusedTextColor = colorScheme.onBackground.copy(alpha = 0.7f),
                                focusedContainerColor = colorScheme.onPrimary,
                                unfocusedContainerColor = colorScheme.onPrimary,
                                focusedIndicatorColor = colorScheme.primary,
                                unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                            ),
                        )

                    }


                }


                TextButton(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 16.dp),
                    onClick = {
                        otpValue.forEachIndexed { index, _ ->
                            otpValue[index] = ""
                        }
                        focusRequesters[0].requestFocus()
                        viewModel.sendOTP(email)
                        verifyOTPMessage = ""
                        sendSuccesful = ""
                    }
                ) {
                    Text(
                        text = "Gửi lại",
                        fontSize = 14.sp,
                        color = colorScheme.primary
                    )
                }


                LaunchedEffect(Unit) {
                    focusRequesters[0].requestFocus()
                }


                if (title == "Xác nhận Email") {
                    Text(
                        text = verifyEmailMessage,
                        fontSize = 14.sp,
                        color = when (verifyEmailState) {
                            is VerifyEmailState.Idle -> Color.Green
                            is VerifyEmailState.Loading -> Color.Yellow
                            else -> colorScheme.error
                        }
                    )
                } else {
                    Text(
                        text = verifyOTPMessage,
                        fontSize = 14.sp,
                        color =
                        when (verifyOTPState) {
                            is OTPState.Idle -> Color.Green
                            is OTPState.Loading -> Color.Yellow
                            else -> colorScheme.error
                        }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        viewModel.verifyOTP(email, otpValue.joinToString(""))
                    },
                    modifier = Modifier.size(
                        width = if (isTablet) 300.dp else 200.dp,
                        height = if (isTablet) 56.dp else 48.dp
                    ),
                    colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
                    shape = MaterialTheme.shapes.large
                ) {
                    Text(
                        text = "Xác nhận",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OtpScreenPreview() {
    OtpScreen(navController = rememberNavController(), email = "", onVerificationSuccess = {})
}