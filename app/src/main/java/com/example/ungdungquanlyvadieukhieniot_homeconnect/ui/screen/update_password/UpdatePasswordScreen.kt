package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.update_password

import android.app.Application
import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ChangePasswordRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.User
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.access_point_connection.isTablet
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.settings.SettingsScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.signup.SignUpState
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.signup.SignUpViewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.validation.ValidationUtils

/** Giao diện màn hình Cập nhật Mật Khẩu (Update PassWord Screen)
 * -----------------------------------------
 * Người viết: Phạm Xuân Nhân
 * Ngày viết: 04/12/2024
 * Lần cập nhật cuối: 05/12/2024
 * -----------------------------------------
 * Input: Không có
 *
 * Output: Column chứa các thành phần giao diện của màn hình Cập nhật Mật Khẩu
 */

@Composable
fun UpdatePasswordScreen(
    navController: NavHostController,
    userId: Int
) {
    val context = LocalContext.current
    val application = context.applicationContext as Application
    val viewModel = remember {
        UpdatePasswordViewModel(application, context)
    }

    val updatePasswordState by viewModel.updatePasswordState.collectAsState()

    when(updatePasswordState){
        is UpdatePasswordState.Error ->{
            Text((updatePasswordState as UpdatePasswordState.Error).error, color = Color.Red)
            Log.e("Error",  (updatePasswordState as UpdatePasswordState.Error).error)
        }
        is UpdatePasswordState.Idle ->{
            //Todo
        }
        is UpdatePasswordState.Loading -> {
            CircularProgressIndicator()
        }
        is UpdatePasswordState.Success -> {
            Text((updatePasswordState as UpdatePasswordState.Success).message, color = Color.Red)
            //navController.navigate(Screens.Login.route)
            Log.d("Success", (updatePasswordState as UpdatePasswordState.Success).message)
        }
    }

    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        val configuration = LocalConfiguration.current
        val screenHeightDp = configuration.screenHeightDp.dp
        val screenWidthDp = configuration.screenWidthDp.dp

        // Trạng thái cho các trường mật khẩu
        var passwordState by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }

        var passwordState2 by remember { mutableStateOf("") }
        var passwordVisible2 by remember { mutableStateOf(false) }

        var passwordState3 by remember { mutableStateOf("") }
        var passwordVisible3 by remember { mutableStateOf(false) }

        // Biến trạng thái lưu thông báo lỗi
        var passwordError by remember { mutableStateOf("") }
        var passwordNewError by remember { mutableStateOf("") }
        var passwordConfirmError by remember { mutableStateOf("") }

        var changePasswordRequest by remember { mutableStateOf<ChangePasswordRequest?>(null) }

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
        var bien = false

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background),

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
                    //Thiếu Image
                    Text(
                        text = "Cập nhật Mật Khẩu",
                        fontSize = if (isTablet()) 28.sp else 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    //Căn lại tỉ lệ chữ
                    Text(
                        text = "Mật khẩu mới của bạn phải khác với mật khẩu đã sử dụng trước đó.",
                        fontSize = 14.sp,
                        color  = colorScheme.onBackground.copy(alpha = 0.6f),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    //MẬT KHẨU CŨ

                    //Sửa lại cac textfield cho có thêm icon ẩn hiện mật khẩu
                    OutlinedTextField(
                        shape = RoundedCornerShape(25),
                        singleLine = true,
                        value = passwordState,
                        onValueChange = {
                            passwordState = it
                            passwordError = ValidationUtils.validatePassword(it) // Kiểm tra mật khẩu
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
                            focusedTextColor = colorScheme.onBackground,  // Màu text khi TextField được focus
                            unfocusedTextColor = colorScheme.onBackground.copy(alpha = 0.7f),  // Màu text khi TextField không được focus
                            focusedContainerColor = colorScheme.onPrimary,
                            unfocusedContainerColor = colorScheme.onPrimary,
                            focusedIndicatorColor = colorScheme.primary,
                            unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // MẬT KHẨU MỚI
                    OutlinedTextField(
                        shape = RoundedCornerShape(25),
                        singleLine = true,
                        value = passwordState2,
                        onValueChange = {
                            passwordState2 = it
                            passwordNewError = ValidationUtils.validatePassword(it) // Kiểm tra mật khẩu mới
                        },
                        placeholder = { Text("Mật khẩu mới:") },
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
                            focusedTextColor = colorScheme.onBackground,  // Màu text khi TextField được focus
                            unfocusedTextColor = colorScheme.onBackground.copy(alpha = 0.7f),  // Màu text khi TextField không được focus
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
                        value = passwordState3,
                        onValueChange = {
                            passwordState3 = it
                            passwordConfirmError = ValidationUtils.validateConfirmPassword(passwordState2, it) // Kiểm tra mật khẩu xác nhận
                        },
                        placeholder = { Text("Nhập lại mật khẩu mới:") },
                        leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible3 = !passwordVisible3 }) {
                                Icon(
                                    imageVector = if (passwordVisible3) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = if (passwordVisible3) "Hide password" else "Show password"
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible3) VisualTransformation.None else PasswordVisualTransformation(),
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
                    Spacer(modifier = Modifier.height(16.dp))

                    //ToDo: Trả về các điều kiện cần đạt được cácd mật khẩu


                    // Nút đăng nhập
                    // Nút Đặt lại mật khẩu
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
                            text = "Đặt lại mật khẩu",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorScheme.onPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Nút quay lại màn hình đăng nhập
                    TextButton(onClick = {
                        //ToDo: Sử lý xự kiện quay lại
                        if (passwordError.isEmpty() && passwordNewError.isEmpty() && passwordConfirmError.isEmpty()) {
                            // Gửi yêu cầu đổi mật khẩu
                            changePasswordRequest = ChangePasswordRequest(
                                oldPassword = passwordState,
                                newPassword = passwordState2,
                            )
                            viewModel.updatePassword(userId, changePasswordRequest!!)

                            // Hiển thị Toast thành công
                            Toast.makeText(context, "Đặt lại mật khẩu thành công!", Toast.LENGTH_SHORT).show()
                        } else {
                            // Hiển thị lỗi bằng Toast
                            if (passwordError.isNotEmpty()) {
                                Toast.makeText(context, passwordError, Toast.LENGTH_SHORT).show()
                            } else if (passwordNewError.isNotEmpty()) {
                                Toast.makeText(context, passwordNewError, Toast.LENGTH_SHORT).show()
                            } else if (passwordConfirmError.isNotEmpty()) {
                                Toast.makeText(context, passwordConfirmError, Toast.LENGTH_SHORT).show()
                            }
                        }
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
}

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun UpdatePasswordScreenPreview() {
    UpdatePasswordScreen(navController = rememberNavController(), -1)
}