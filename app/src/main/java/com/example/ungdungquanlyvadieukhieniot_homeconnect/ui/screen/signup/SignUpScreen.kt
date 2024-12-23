package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.signup

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.Screens
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme

@SuppressLint("InlinedApi")
@Composable
fun SignUpScreen(navController: NavHostController) {

    AppTheme {
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


        fun validateInput(): Boolean {
            errorMessage = ""
            when {
                name.isBlank() || !name.matches(Regex("^[a-zA-Z\\s'-]+\$")) -> errorMessage =
                    "Tên không được chứa ký tự đặc biệt hoặc số."

                email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                    .matches() -> errorMessage = "Email không hợp lệ."

                phoneNumber.isBlank() || !phoneNumber.matches(Regex("^[0-9]{10,11}\$")) -> errorMessage =
                    "Số điện thoại không hợp lệ."

                password.length < 8 || !password.matches(Regex(".*[A-Z].*")) && !password.matches(
                    Regex(".*[a-z].*")
                )
                        || !password.matches(Regex(".*\\d.*")) || !password.matches(Regex(".*[@#$%^&+=].*")) -> errorMessage =
                    "Mật khẩu cần ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt."

                confirmPassword != password -> errorMessage = "Mật khẩu nhập lại không khớp."
                avatarUri == null -> errorMessage = "Vui lòng chọn ảnh đại diện."
            }
            return errorMessage.isEmpty()
        }

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background),
            containerColor = MaterialTheme.colorScheme.background,
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
                    OutlinedTextField(
                        shape = RoundedCornerShape(25),
                        singleLine = true,
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Họ tên") },
                        leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) },
                        modifier = Modifier
                            .width(if (isTablet) 500.dp else 400.dp)
                            .height(if (isTablet) 80.dp else 70.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = colorScheme.onPrimary,
                            unfocusedContainerColor = colorScheme.onPrimary,
                            focusedIndicatorColor = colorScheme.primary,
                            unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                        )
                    )

                    OutlinedTextField(
                        shape = RoundedCornerShape(25),
                        singleLine = true,
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier
                            .width(if (isTablet) 500.dp else 400.dp)
                            .height(if (isTablet) 80.dp else 70.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = colorScheme.onPrimary,
                            unfocusedContainerColor = colorScheme.onPrimary,
                            focusedIndicatorColor = colorScheme.primary,
                            unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                        )
                    )

                    OutlinedTextField(
                        shape = RoundedCornerShape(25),
                        singleLine = true,
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        label = { Text("Số điện thoại") },
                        leadingIcon = { Icon(Icons.Filled.Phone, contentDescription = null) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier
                            .width(if (isTablet) 500.dp else 400.dp)
                            .height(if (isTablet) 80.dp else 70.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = colorScheme.onPrimary,
                            unfocusedContainerColor = colorScheme.onPrimary,
                            focusedIndicatorColor = colorScheme.primary,
                            unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                        )
                    )

                    OutlinedTextField(
                        shape = RoundedCornerShape(25),
                        singleLine = true,
                        value = address,
                        onValueChange = { address = it },
                        label = { Text("Địa chỉ") },
                        leadingIcon = { Icon(Icons.Filled.Home, contentDescription = null) },
                        modifier = Modifier
                            .width(if (isTablet) 500.dp else 400.dp)
                            .height(if (isTablet) 80.dp else 70.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = colorScheme.onPrimary,
                            unfocusedContainerColor = colorScheme.onPrimary,
                            focusedIndicatorColor = colorScheme.primary,
                            unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                        )
                    )
                } else {
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
                                contentScale = ContentScale.Crop,
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

                    val requiredPermissions = when {
                        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                            arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
                        }

                        else -> {
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                        }
                    }

                    val isPermissionGranted = remember {
                        mutableStateOf(
                            requiredPermissions.all {
                                ContextCompat.checkSelfPermission(
                                    context,
                                    it
                                ) == PackageManager.PERMISSION_GRANTED
                            }
                        )
                    }

                    val permissionLauncher = rememberLauncherForActivityResult(
                        ActivityResultContracts.RequestMultiplePermissions()
                    ) { permissions ->
                        isPermissionGranted.value = permissions.all { it.value }
                    }

                    val openSettingsLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartActivityForResult()
                    ) { /* No action needed; simply opens the settings */ }

                    if (!isPermissionGranted.value) {
                        errorMessage = "Ứng dụng cần quyền truy cập bộ nhớ để chọn ảnh đại diện."
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Button(
                                onClick = {
                                    permissionLauncher.launch(requiredPermissions)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                shape = RoundedCornerShape(50)
                            ) {
                                Text(
                                    "Yêu cầu cấp quyền",
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = {
                                    val intent =
                                        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                            data =
                                                Uri.fromParts("package", context.packageName, null)
                                        }
                                    openSettingsLauncher.launch(intent)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                                shape = RoundedCornerShape(50)
                            ) {
                                Text("Mở cài đặt", color = MaterialTheme.colorScheme.onSecondary)
                            }
                        }
                    } else {
                        OutlinedButton(
                            onClick = {
                                imagePickerLauncher.launch("image/*")
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
                            .width(if (isTablet) 500.dp else 400.dp)
                            .height(if (isTablet) 80.dp else 70.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = colorScheme.onPrimary,
                            unfocusedContainerColor = colorScheme.onPrimary,
                            focusedIndicatorColor = colorScheme.primary,
                            unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                        )
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
                            .width(if (isTablet) 500.dp else 400.dp)
                            .height(if (isTablet) 80.dp else 70.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = colorScheme.onPrimary,
                            unfocusedContainerColor = colorScheme.onPrimary,
                            focusedIndicatorColor = colorScheme.primary,
                            unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                        )
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
                    horizontalArrangement = Arrangement.spacedBy(
                        6.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(if (isTablet) 0.8f else 0.9f)
                        .padding(horizontal = 16.dp)
                ) {
                    if (stage == 2) {
                        OutlinedButton(
                            onClick = { stage = 1 },
                            modifier = Modifier
                                .width(if (isTablet) 300.dp else 200.dp)
                                .height(if (isTablet) 56.dp else 48.dp),
                        ) {
                            Text("Quay lại")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                    }

                    Button(
                        onClick = {
                            if (stage == 1) {
                                stage = 2
                                errorMessage = ""
                            } else if (stage == 2 && validateInput()) {
                                // TODO: Xử lý sang màn hình xác thực OTP
                                navController.navigate(Screens.Home.route)
                            }
                        },
                        enabled = (stage == 1 || (stage == 2 && validateInput())),
                        modifier = Modifier
                            .width(if (isTablet) 300.dp else 200.dp)
                            .height(if (isTablet) 56.dp else 48.dp)
                            .align(Alignment.CenterVertically),
                        colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(if (stage == 1) "Tiếp tục" else "Đăng ký")
                    }



                }
                Spacer(modifier = Modifier.height(16.dp))
                // Chuyển tới đăng nhập
                Row(
                    modifier = Modifier.fillMaxWidth(if (isTablet) 0.8f else 0.9f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Đã có tài khoản?",
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
fun SignUpScreenPreview() {
    SignUpScreen(navController = rememberNavController())
}