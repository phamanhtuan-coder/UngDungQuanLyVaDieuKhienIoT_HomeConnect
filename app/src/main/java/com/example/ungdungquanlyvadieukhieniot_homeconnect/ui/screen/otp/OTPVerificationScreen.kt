package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.otp

import android.util.Log
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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.Screens
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme

@Composable
fun OtpScreen(
    navController: NavHostController,
    email: String,
    viewModel: OTPViewModel = viewModel()

) {
    val sendOTPState by viewModel.sendOtpState.collectAsState()
    var sendSuccesful = ""

    LaunchedEffect(Unit) {
        viewModel.sendOTP(email)
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
            LaunchedEffect(Unit) {
                navController.navigate("${Screens.NewPassword.route}?email=$email")
            }
        }
        is OTPState.Error -> {
            verifyOTPMessage = "Xác thực OTP thất bại! Mã OTP không đúng."
        }
        is OTPState.Loading -> {
            verifyOTPMessage = "Đang xác thực mã OTP..."
        }
        else -> {
            verifyOTPMessage = "Mã OTP có hiệu lực trong 5 phút."
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
                    text = "Nhập mã OTP",
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
                    text = "Vui lòng nhập mã OTP vừa được gửi tới Email",
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
                    TextButton(
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
                }


                LaunchedEffect(Unit) {
                    focusRequesters[0].requestFocus()
                }

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
    OtpScreen(navController = rememberNavController(), email = "")
}