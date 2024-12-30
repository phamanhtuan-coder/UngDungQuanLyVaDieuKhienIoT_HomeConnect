package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.otp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.Screens
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme

@Composable
fun OtpScreen(navController: NavHostController) {
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        val configuration = LocalConfiguration.current
        val isTablet = configuration.screenWidthDp >= 600
        val otpLength = 6 // Độ dài mã OTP (số ô nhập OTP)
        val otpValue =
            remember { Array(otpLength) { TextFieldValue("") } } // Khởi tạo danh sách các TextFieldValue cho mỗi ô OTP
        val focusRequesters =
            List(otpLength) { FocusRequester() } // Danh sách FocusRequester để quản lý focus cho từng ô OTP

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
                    text = "Vui lòng nhập mã OTP vừa được gửi tới Email",
                    fontSize = 14.sp,
                    color = colorScheme.onBackground.copy(alpha = 0.6f)
                )


                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    repeat(otpLength) { index ->
                        OutlinedTextField(
                            value = otpValue[index], // Current OTP value
                            onValueChange = {
                                //ToDo: Xử lý khi nhập mã OTP vào đây
                                    input ->
                                if (input.text.length <= 1) { // Limit input to one character
                                    otpValue[index] =
                                        TextFieldValue(input.text, TextRange(input.text.length))

                                    // Move to the next field if not empty and not the last index
                                    if (input.text.isNotEmpty() && index < otpLength - 1) {
                                        focusRequesters[index + 1].requestFocus()
                                    }

                                    // Handle backspace and move focus back
                                    if (input.text.isEmpty() && index > 0) {
                                        focusRequesters[index - 1].requestFocus()
                                    }
                                }
                            },
                            modifier = Modifier
                                .size(if (isTablet) 60.dp else 50.dp)
                                .focusRequester(focusRequesters[index]), // Attach focusRequester
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
                            colors = MaterialTheme.colorScheme.run {
                                TextFieldDefaults.colors(
                                    focusedTextColor = onBackground,
                                    unfocusedTextColor = onBackground,
                                    focusedContainerColor = onPrimary,
                                    unfocusedContainerColor = onPrimary,
                                    focusedIndicatorColor = primary,
                                    unfocusedIndicatorColor = onBackground.copy(alpha = 0.5f)
                                )
                            }
                        )
                    }
                }

                LaunchedEffect(Unit) {
                    focusRequesters[0].requestFocus() // Focus the first field
                }



                Text(
                    text = "Mã OTP có hiệu lực trong 5 phút.",
                    fontSize = 12.sp,
                    color = colorScheme.onBackground.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.height(24.dp))


                // Nút xác nhận OTP
                Button(
                    onClick = { /* TODO: Xử lý xác nhận OTP */ },
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

                // Nút gửi lại OTP
                TextButton(
                    onClick = {

                    },
                    content =
                    {
                        Text(
                            text = "Gửi lại mã OTP",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorScheme.primary,
                        )
                    },
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )


                // Chuyển tới đăng nhập
                Row(
                    modifier = Modifier.fillMaxWidth(if (isTablet) 0.8f else 0.9f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Bạn muốn đăng nhập?",
                        fontSize = 14.sp,
                        color = colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    TextButton(onClick = { navController.navigate(Screens.Login.route) }) {
                        Text(
                            text = "Đăng nhập",
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
fun OtpScreenPreview() {
    OtpScreen(navController = rememberNavController())
}