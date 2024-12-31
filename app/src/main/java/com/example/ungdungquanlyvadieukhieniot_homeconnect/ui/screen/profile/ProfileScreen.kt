package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.profile

//import com.vmadalin.easypermissions.EasyPermissions
import android.Manifest
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.WarningDialog
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.Screens
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavHostController
) {
    AppTheme {
        val configuration = LocalConfiguration.current
        val isTablet = configuration.screenWidthDp >= 600
        val colorScheme = MaterialTheme.colorScheme

        val nameState = remember { mutableStateOf("Nguyễn Thanh Sang") }
        val phoneState = remember { mutableStateOf("0123456789") }
        val locationState = remember { mutableStateOf("TP. Hồ Chí Minh") }
        val emailState = remember { mutableStateOf("example@gmail.com") }
        var selectedDate by remember { mutableStateOf("01/01/2004") }
        val imageUrl = remember { mutableStateOf("") }
        val isVerified = remember { mutableStateOf(true) }
        val dateCreated = remember { mutableStateOf("01/12/2024") }

        var showDatePicker by remember { mutableStateOf(false) }
        val datePickerState = rememberDatePickerState()
        var showAlertDialog by remember { mutableStateOf(false) }

        if (showAlertDialog) {
            WarningDialog(
                title = "Cảnh báo",
                text = "Hành động này sẽ đăng xuất bạn ra khỏi ứng dụng. Bạn có chắc chắn không?",
                onConfirm = {
                    //Todo: Đăng xuất
                    navController.navigate(Screens.Welcome.route) {
                        popUpTo(Screens.Welcome.route) { inclusive = true }
                    }
                },
                onDismiss = { showAlertDialog = false }
            )
        }

        val context = LocalContext.current

        Scaffold(
            topBar = {
                Header(
                    navController = navController,
                    type = "Back",
                    title = "Trang cá nhân"
                )
            },
            bottomBar = {
                MenuBottom(navController = navController)
            },
            containerColor = colorScheme.background
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .background(colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Cột chứa thông tin cá nhân và biểu tượng
                Column {
                    // Hộp chính với nền trắng
                    Box(
                        modifier = Modifier
                            .padding(top = if (isTablet) 120.dp else 64.dp)
                            .size(
                                width = if (isTablet) 480.dp else 350.dp,
                                height = if (isTablet) 280.dp else 180.dp
                            )
                            .shadow(
                                elevation = 1.dp,
                                shape = RoundedCornerShape(25),
                                clip = false,
                                ambientColor = colorScheme.secondary.copy(alpha = 0.2f),
                                spotColor = colorScheme.primary.copy(alpha = 0.2f)
                            )
                            .background(
                                color = colorScheme.secondary.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(25)
                            ),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        // Vòng tròn ngoài màu trắng
                        Box(
                            modifier = Modifier
                                .size(if (isTablet) 130.dp else 80.dp)
                                .offset(y = if (isTablet) (-40).dp else (-20).dp)
                                .background(color = Color.White, shape = CircleShape)
                                .clickable {
                                    val permissions = when {
                                        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                                            arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
                                        }

                                        Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                                        }

                                        else -> {
                                            arrayOf(
                                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                                            )
                                        }
                                    }

//                                    if (EasyPermissions.hasPermissions(context, *permissions)) {
//                                        // TODO: Mở màn hình chọn hình ảnh từ bộ nhớ thiết bị
//                                    } else {
//                                        EasyPermissions.requestPermissions(
//                                            context as androidx.fragment.app.FragmentActivity,
//                                            "Ứng dụng cần quyền truy cập bộ nhớ để tải hình ảnh",
//                                            123, // Mã request
//                                            *permissions
//                                        )
//                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (imageUrl.value.isBlank().not()) {
                                AsyncImage(
                                    model = imageUrl,
                                    contentDescription = "Avatar",
                                    modifier = Modifier
                                        .size(if (isTablet) 120.dp else 70.dp)
                                        .background(color = Color.Gray, shape = CircleShape)
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .size(if (isTablet) 120.dp else 70.dp)
                                        .background(
                                            color = colorScheme.primary.copy(alpha = 0.8f),
                                            shape = CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = "Avatar",
                                        colorFilter = ColorFilter.tint(colorScheme.onPrimary),
                                        modifier = Modifier.size(if (isTablet) 70.dp else 40.dp)
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .offset(
                                        x = (if (isTablet) 10 else 10).dp,
                                        y = (if (isTablet) 40 else 25).dp
                                    ),
                                contentAlignment = Alignment.TopEnd
                            ) {
                                Icon(
                                    imageVector = if (isVerified.value) Icons.Default.CheckCircle else Icons.Default.CheckCircleOutline,
                                    contentDescription = "Verified",
                                    tint = Color.Green,
                                    modifier = Modifier
                                        .size((if (isTablet) 70 else 40).dp)
                                        .padding(
                                            start = if (isTablet) 16.dp else 8.dp,
                                            end = if (isTablet) 16.dp else 8.dp,
                                            top = if (isTablet) 8.dp else 2.dp,
                                            bottom = if (isTablet) 8.dp else 2.dp
                                        )
                                )
                            }
                        }

                        // Cột chứa thông tin tên và email
                        Column(
                            modifier = Modifier.padding(top = if (isTablet) 120.dp else 70.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            // Hiển thị tên người dùng
                            Text(
                                text = nameState.value,
                                fontWeight = FontWeight.Bold,
                                fontSize = if (isTablet) 40.sp else 25.sp,
                                color = colorScheme.onSecondary
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            // Hiển thị ngày tham gia
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Ngày tạo tài khoản: " + dateCreated.value,
                                    color = colorScheme.onSecondary,
                                    fontSize = if (isTablet) 24.sp else 20.sp
                                )
                            }
                            // Hộp chứa các nút
                            Box(
                                modifier = Modifier
                                    .padding(top = if (isTablet) 24.dp else 14.dp),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .size(
                                            width = if (isTablet) 480.dp else 350.dp,
                                            height = (if (isTablet) 90.dp else 60.dp) * 2
                                        )
                                        .align(Alignment.Center),
                                ) {
                                    // Nút "Đổi mât khẩu"
                                    Box(
                                        modifier = Modifier
                                            .size(
                                                width = if (isTablet) 260.dp else 190.dp,
                                                height = if (isTablet) 90.dp else 60.dp
                                            )
                                            .clip(CutCornerShape(topEnd = if (isTablet) 90.dp else 60.dp))
                                            .align(Alignment.CenterStart)
                                            .background(
                                                colorScheme.primary,
                                                shape = RoundedCornerShape(
                                                    topStart = 0.dp,
                                                    topEnd = 0.dp,
                                                    bottomStart = 12.dp,
                                                    bottomEnd = 0.dp
                                                )
                                            ),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Button(
                                            onClick = {
                                                //ToDo: Xử lý khi bấm đổi mật khẩu
                                            },
                                            modifier = Modifier.fillMaxSize(),
                                            shape = RoundedCornerShape(bottomStart = 12.dp),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = colorScheme.primary,
                                                contentColor = colorScheme.onPrimary
                                            )
                                        ) {
                                            Text(
                                                modifier = Modifier.padding(horizontal = if (isTablet) 30.dp else 20.dp),
                                                text = "Đổi mật khẩu",
                                                textAlign = TextAlign.Center,
                                                fontSize = if (isTablet) 18.sp else 14.sp
                                            )
                                        }
                                    }
                                    // Nút "Đăng xuất"
                                    Box(
                                        modifier = Modifier
                                            .size(
                                                width = if (isTablet) 260.dp else 190.dp,
                                                height = if (isTablet) 90.dp else 60.dp
                                            )
                                            .clip(CutCornerShape(bottomStart = (if (isTablet) 90.dp else 60.dp) / 1.5f))
                                            .align(Alignment.CenterEnd)
                                            .background(
                                                colorScheme.error,
                                                shape = RoundedCornerShape(
                                                    topStart = 0.dp,
                                                    bottomStart = 0.dp,
                                                    bottomEnd = 12.dp
                                                )
                                            ),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Button(
                                            onClick = {
                                            /* TODO: Xử lý sự kiện nút bấm đăng xuất */
                                                showAlertDialog = true
                                            },
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clip(CutCornerShape(bottomStart = (if (isTablet) 90.dp else 60.dp) / 1.5f)),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = colorScheme.error,
                                                contentColor = colorScheme.onError
                                            ),
                                            shape = RoundedCornerShape(bottomEnd = 12.dp),
                                            elevation = ButtonDefaults.buttonElevation(0.dp)
                                        ) {
                                            Text(
                                                text = "Đăng xuất",
                                                textAlign = TextAlign.Center,
                                                fontSize = if (isTablet) 18.sp else 14.sp
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))


                // Information Fields
                Column(
                    modifier = Modifier
                        .padding(horizontal = if (isTablet) 32.dp else 16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    OutlinedTextField(
                        value = nameState.value,
                        onValueChange = { nameState.value = it },
                        label = { Text("Họ và tên") },
                        shape = RoundedCornerShape(25),
                        singleLine = true,
                        modifier = Modifier
                            .width(if (isTablet) 500.dp else 400.dp)
                            .height(if (isTablet) 80.dp else 70.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = colorScheme.onPrimary,
                            unfocusedContainerColor = colorScheme.onPrimary,
                            focusedIndicatorColor = colorScheme.primary,
                            unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = null
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                    )

                    OutlinedTextField(
                        value = phoneState.value,
                        onValueChange = { phoneState.value = it },
                        label = { Text("Số điện thoại") },
                        shape = RoundedCornerShape(25),
                        singleLine = true,
                        modifier = Modifier
                            .width(if (isTablet) 500.dp else 400.dp)
                            .height(if (isTablet) 80.dp else 70.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = colorScheme.onPrimary,
                            unfocusedContainerColor = colorScheme.onPrimary,
                            focusedIndicatorColor = colorScheme.primary,
                            unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.PhoneAndroid,
                                contentDescription = null
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Next
                        ),
                    )

                    OutlinedTextField(
                        value = locationState.value,
                        onValueChange = { locationState.value = it },
                        label = { Text("Nơi sống hiện tại") },
                        shape = RoundedCornerShape(25),
                        singleLine = true,
                        modifier = Modifier
                            .width(if (isTablet) 500.dp else 400.dp)
                            .height(if (isTablet) 80.dp else 70.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = colorScheme.onPrimary,
                            unfocusedContainerColor = colorScheme.onPrimary,
                            focusedIndicatorColor = colorScheme.primary,
                            unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.AddLocation,
                                contentDescription = null
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                    )

                    OutlinedTextField(
                        value = emailState.value,
                        onValueChange = {},
                        label = { Text("Email") },
                        shape = RoundedCornerShape(25),
                        singleLine = true,
                        readOnly = true,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Verified",
                                tint = colorScheme.primary
                            )
                        },
                        modifier = Modifier
                            .width(if (isTablet) 500.dp else 400.dp)
                            .height(if (isTablet) 80.dp else 70.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = colorScheme.onPrimary,
                            unfocusedContainerColor = colorScheme.onPrimary,
                            focusedIndicatorColor = colorScheme.primary,
                            unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Email,
                                contentDescription = null
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                    )


                    // Trường ngày sinh
                    OutlinedTextField(
                        value = selectedDate, // Chỗ này bị bug chưa load được ngày từ biến
                        onValueChange = { /* Not needed for read-only fields */ },
                        label = { Text("Ngày sinh (dd/mm/yyyy)") },
                        shape = RoundedCornerShape(25),
                        singleLine = true,
                        readOnly = true,
                        trailingIcon = {
                            // Nút chọn ngày
                            IconButton(onClick = { showDatePicker = !showDatePicker }) {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = "Chọn ngày"
                                )
                            }
                        },
                        modifier = Modifier
                            .width(if (isTablet) 500.dp else 400.dp)
                            .height(if (isTablet) 80.dp else 70.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = colorScheme.onPrimary,
                            unfocusedContainerColor = colorScheme.onPrimary,
                            focusedIndicatorColor = colorScheme.primary,
                            unfocusedIndicatorColor = colorScheme.onBackground.copy(alpha = 0.5f)
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.DateRange,
                                contentDescription = null
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                    )

                    if (showDatePicker) {
                        Popup(
                            onDismissRequest = { showDatePicker = false },
                            alignment = Alignment.TopStart
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .offset(y = 64.dp)
                                    .shadow(elevation = 4.dp)
                                    .background(colorScheme.background)
                                    .padding(16.dp)
                            ) {
                                DatePicker(
                                    state = datePickerState,
                                    showModeToggle = false
                                )
                            }
                        }
                    }

                    //Nếu thay dđổi ngày thì update
                    LaunchedEffect(datePickerState.selectedDateMillis) {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            selectedDate = formatter.format(Date(millis))
                            showDatePicker = false //Ẩn date picker
                        }
                    }


                }
                // Save Button
                Button(
                    onClick = {
                        // TODO: Xử lý lưu thông tin
                    },
                    modifier = Modifier
                        .width(if (isTablet) 300.dp else 200.dp)
                        .height(if (isTablet) 56.dp else 48.dp)
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Lưu thông tin")
                }

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}