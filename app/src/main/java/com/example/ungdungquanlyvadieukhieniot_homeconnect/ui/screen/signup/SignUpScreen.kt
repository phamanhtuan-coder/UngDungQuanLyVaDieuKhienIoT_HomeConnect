package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.signup

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme






/**
 * Màn hình đăng ký V2
 * ---------------------
 * Người viết: Phạm Xuân Nhân
 * Ngày viết: 2/12/2024
 * Lần cập nhật cuối: 15/12/2024
 * ---------------------
 * @param navController: Đối tượng điều khiển điều hướng
 *
 * -----------------------
 * Người cập nhật: Phạm Anh Tuấn
 * Ngày cập nhật: 15/12/2024
 * -----------------------
 * Nội dung cập nhật:
 *  - Thay đổi bố cục màn hình
 *  - Bổ sung chức năng chọn ảnh đại diện
 *  - Bổ sung chức năng kiểm tra thông tin nhập vào
 *  - Bổ sung chức năng hiển thị thông báo lỗi
 *  - Bổ sung chức năng chuyển đổi giữa hai bước đăng ký
 *  - Bổ sung chức năng kiểm tra quyền truy cập bộ nhớ
 *  - Bổ sung chức năng kiểm tra định dạng ảnh
 *  - Bổ sung chức năng kiểm tra thiết bị là máy tính bảng hay điện thoại
 *  - Bổ sung chức năng chuyển hướng đến màn hình chính
 */
@SuppressLint("InlinedApi")
@Composable
fun SignUpScreen(navController: NavHostController) {

    AppTheme {
        // Danh sách biến cần thiết
        val context = LocalContext.current
        val configuration = LocalConfiguration.current
        val isTablet = configuration.screenWidthDp >= 600

        val colorScheme = MaterialTheme.colorScheme
        var passwordVisible by remember { mutableStateOf(false) }
        var confirmPasswordVisible by remember { mutableStateOf(false) }

        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        var address by remember { mutableStateOf("") }
        var avatarUri by remember { mutableStateOf<Uri?>(null) }

        var stage by remember { mutableStateOf(1) }
        var errorMessage by remember { mutableStateOf("") }



        // Hàm chọn ảnh từ bộ nhớ
        val imagePickerLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let {
                    val mimeType = context.contentResolver.getType(it)
                    if (mimeType == "image/jpeg" || mimeType == "image/png") {
                        avatarUri = it
                        errorMessage = ""
                    } else {
                        errorMessage = "Chỉ chấp nhận định dạng JPEG hoặc PNG."
                    }
                }
            }






        // Hàm kiểm tra thông tin nhập vào
        fun validateInput(): Boolean {
            errorMessage = ""
            when {
                name.isBlank() || !name.matches(Regex("^[a-zA-Z\\s]+$")) -> errorMessage =
                    "Tên không được chứa ký tự đặc biệt hoặc số."

                email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                    .matches() -> errorMessage = "Email không hợp lệ."

                phoneNumber.isBlank() || !phoneNumber.matches(Regex("^[0-9]{10,11}$")) -> errorMessage =
                    "Số điện thoại không hợp lệ."

                password.length < 8 || !password.matches(Regex(".*[A-Z].*")) || !password.matches(
                    Regex(".*[a-z].*")
                ) || !password.matches(Regex(".*\\d.*")) || !password.matches(Regex(".*[@#\$%^&+=].*")) -> errorMessage =
                    "Mật khẩu cần ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt."

                confirmPassword != password -> errorMessage = "Mật khẩu nhập lại không khớp."
                avatarUri == null -> errorMessage = "Vui lòng chọn ảnh đại diện."
            }
            return errorMessage.isEmpty()
        }

       Scaffold (
            modifier = Modifier
                .padding(vertical = 32.dp)
                .fillMaxSize()
                .background(colorScheme.background),

        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .padding(horizontal = if (isTablet) 32.dp else 16.dp)
                    .verticalScroll(rememberScrollState())
                    .imePadding(),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Title
                Text(
                    text = if (stage == 1) "Đăng ký - Bước 1" else "Đăng ký - Bước 2",
                    fontSize = if (isTablet) 28.sp else 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.primary
                )
                Text(
                    text = "Hãy hoàn thành thông tin để tạo tài khoản",
                    fontSize = 14.sp,
                    color = colorScheme.onBackground.copy(alpha = 0.6f)
                )

                if (stage == 1) {
                    // Name Input
                    OutlinedTextField(
                        shape = RoundedCornerShape(25),
                        singleLine = true,
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Họ tên") },
                        leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) },
                        modifier = Modifier
                            .fillMaxWidth(if (isTablet) 0.8f else 0.9f)
                            .height(if (isTablet) 60.dp else 50.dp)
                    )

                    // Email Input
                    OutlinedTextField(
                        shape = RoundedCornerShape(25),
                        singleLine = true,
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier
                            .fillMaxWidth(if (isTablet) 0.8f else 0.9f)
                            .height(if (isTablet) 60.dp else 50.dp)
                    )

                    // Phone Number Input
                    OutlinedTextField(
                        shape = RoundedCornerShape(25),
                        singleLine = true,
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        label = { Text("Số điện thoại") },
                        leadingIcon = { Icon(Icons.Filled.Phone, contentDescription = null) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier
                            .fillMaxWidth(if (isTablet) 0.8f else 0.9f)
                            .height(if (isTablet) 60.dp else 50.dp)
                    )
                    // Address Input
                    OutlinedTextField(
                        shape = RoundedCornerShape(25),
                        singleLine = true,
                        value = address,
                        onValueChange = { address = it },
                        label = { Text("Địa chỉ") },
                        leadingIcon = { Icon(Icons.Filled.Home, contentDescription = null) },
                        modifier = Modifier
                            .fillMaxWidth(if (isTablet) 0.8f else 0.9f)
                            .height(if (isTablet) 60.dp else 50.dp)
                    )
                } else {


                    //TODO: Sửa lại logic chọn hình ảnh
                    // Avatar Upload
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(horizontal = if (isTablet) 32.dp else 16.dp)
                            .imePadding()
                            .fillMaxWidth(if (isTablet) 0.8f else 0.9f)
                    ) {
                        Text(
                            text = "Chọn ảnh đại diện",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorScheme.primary,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        avatarUri?.let {
                            Image(
                                painter = rememberAsyncImagePainter(it),
                                contentDescription = "Avatar Preview",
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, colorScheme.primary, CircleShape)
                                    .background(colorScheme.onSurface.copy(alpha = 0.1f))
                            )
                        } ?: Box( // Hiển thị placeholder khi chưa chọn ảnh
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                                .border(2.dp, colorScheme.primary.copy(alpha = 0.5f), CircleShape)
                                .background(colorScheme.onSurface.copy(alpha = 0.05f))
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Default Avatar",
                                tint = colorScheme.onBackground.copy(alpha = 0.5f),
                                modifier = Modifier.size(64.dp)
                            )
                        }

                    }
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedButton(
                            onClick = {

                            },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .width(200.dp)
                                .height(48.dp),

                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, colorScheme.primary)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Upload,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp),
                                tint = colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Tải lên ảnh", color = colorScheme.primary, fontSize = 16.sp)
                        }




                    // Password Input
                        OutlinedTextField(
                            shape = RoundedCornerShape(25),
                            singleLine = true,
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Mật khẩu") },
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
                                .fillMaxWidth(if (isTablet) 0.8f else 0.9f)
                                .height(if (isTablet) 60.dp else 50.dp)
                        )

                        // Confirm Password Input
                        OutlinedTextField(
                            shape = RoundedCornerShape(25),
                            singleLine = true,
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            label = { Text("Nhập lại mật khẩu") },
                            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
                            trailingIcon = {
                                IconButton(onClick = {
                                    confirmPasswordVisible = !confirmPasswordVisible
                                }) {
                                    Icon(
                                        imageVector = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                        contentDescription = if (confirmPasswordVisible) "Hide password" else "Show password"
                                    )
                                }
                            },
                            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            modifier = Modifier
                                .fillMaxWidth(if (isTablet) 0.8f else 0.9f)
                                .height(if (isTablet) 60.dp else 50.dp)
                        )
                    }

                    // Error Message Display
                    if (errorMessage.isNotBlank()) {
                        Text(
                            text = errorMessage,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(if (isTablet) 0.8f else 0.9f).padding(horizontal = 16.dp)
                ) {
                    if (stage == 2) {
                        OutlinedButton(
                            onClick = { stage = 1 },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Quay lại")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                    }

                    Button(
                        onClick = {
                            if (stage == 1) {
                                stage = 2
                            } else {
                                if (validateInput()) {
                                    // TODO: Thay thế bằng logic đăng ký tài khoản
                                    navController.navigate("home")
                                }
                            }
                        },
                        modifier = Modifier.weight(if(stage ==2) 1f else 0.5f),
                        colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(if (stage == 1) "Tiếp tục" else "Đăng ký")
                    }


                }


                }
                Spacer(modifier = Modifier.height(16.dp))




            }

        }

    }




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(navController = rememberNavController())
}