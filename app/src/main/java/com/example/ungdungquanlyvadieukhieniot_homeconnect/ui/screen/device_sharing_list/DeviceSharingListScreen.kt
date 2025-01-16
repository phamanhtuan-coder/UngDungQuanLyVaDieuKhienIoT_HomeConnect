package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_sharing_list

import android.app.Application
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SharedUser
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.WarningDialog
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.Screens
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme

/** Giao diện màn hình Access Point Connection Screen (AccessPointConnectionScreen
 * -----------------------------------------
 * Người viết: Nguyễn Thanh Sang
 * Ngày viết: 07/12/2024
 * Lần cập nhật cuối: 10/12/2024
 * -----------------------------------------
 * Input:
 *
 * Output: Scaffold
 *
 * ---------------------------------------
 */

@Composable
fun isTablet(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.smallestScreenWidthDp >= 600
}

data class LayoutConfig(
    val outerPadding: Dp,  // Padding bên ngoài, thường được tính theo tỉ lệ chiều rộng màn hình
    val textFieldSpacing: Dp, // Khoảng cách giữa các thành phần, dựa trên chiều cao màn hình
    val headingFontSize: TextUnit, // Kích thước chữ tiêu đề, tỉ lệ theo chiều rộng màn hình
    val textFontSize: TextUnit, // Kích thước chữ mô tả hoặc nội dung
    val contentWidth: Dp, // Chiều rộng của nội dung chính (ví dụ: TextField)
    val iconSize: Dp, // Kích thước của icon trong giao diện
    val boxHeight: Dp, // Chiều cao của các Box (ví dụ: hộp chứa góc lõm)
    val cornerBoxSize: Dp,          // Kích thước cho Box góc lõm
    val cornerBoxRadius: Int,       // Bo tròn góc cho Box góc lõm
    val dialogPadding: Dp           // Padding cho AlertDialog
)

// Tính toán LayoutConfig dựa trên kích thước màn hình
@Composable
fun rememberResponsiveLayoutConfig(): LayoutConfig {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp // Lấy chiều rộng màn hình
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp // Lấy chiều cao màn hình

    return LayoutConfig(
        outerPadding = screenWidth * 0.05f,                   // Padding bên ngoài bằng 5% chiều rộng màn hình
        textFieldSpacing = 8.dp + screenHeight * 0.01f,       // Khoảng cách giữa các thành phần = 8dp + 1% chiều cao màn hình
        headingFontSize = (12 + screenWidth.value * 0.04f).sp,// Font size tiêu đề dựa trên chiều rộng màn hình
        textFontSize = (10 + screenWidth.value * 0.03f).sp,   // Font size nội dung dựa trên chiều rộng màn hình
        contentWidth = if(isTablet()) 400.dp + screenWidth * 0.1f else 300.dp,                    // Chiều rộng của nội dung chính bằng 80% chiều rộng màn hình
        iconSize = screenWidth * 0.04f,                       // Kích thước icon bằng 8% chiều rộng màn hình
        boxHeight = screenHeight * 0.1f,                      // Chiều cao Box là 10% chiều cao màn hình
        cornerBoxSize = screenWidth * 0.1f,                   // Kích thước cho Box góc lõm bằng 10% chiều rộng màn hình
        cornerBoxRadius = 50,                                 // Độ bo góc cho Box góc lõm (theo phần trăm)
        dialogPadding = screenWidth * 0.04f                   // Padding cho AlertDialog bằng 4% chiều rộng màn hình
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceSharingListScreen(
    navController: NavHostController,
    deviceID: Int = 0
) {

    val context = LocalContext.current
    val application = context.applicationContext as Application
    val viewModel = remember {
        DeviceSharingViewModel(application, context)
    }

    val sharedUsersState by viewModel.sharedUsersState.collectAsState()
    var sharedUsersList by remember { mutableStateOf(emptyList<SharedUser>()) }
    var selectedPermissionId by remember { mutableStateOf(-1) }

    LaunchedEffect(Unit) {
        viewModel.getSharedUsers(deviceID)
    }

    when (sharedUsersState) {
        is DeviceSharingState.Idle -> {
            // Do nothing
        }

        is DeviceSharingState.Loading -> {
            CircularProgressIndicator()
        }

        is DeviceSharingState.Success -> {
            sharedUsersList = (sharedUsersState as DeviceSharingState.Success).sharedUsers
        }

        is DeviceSharingState.Error -> {
            // Show error
            Log.e(
                "DeviceSharingListScreen",
                "Error: ${(sharedUsersState as DeviceSharingState.Error).error}"
            )
        }
    }

    val revokePermissionState by viewModel.revokePermissionState.collectAsState()

    when (revokePermissionState) {
        is DeviceSharingActionState.Idle -> {
            // Do nothing
        }

        is DeviceSharingActionState.Loading -> {
            CircularProgressIndicator()
        }

        is DeviceSharingActionState.Success -> {
            LaunchedEffect(Unit) {
                viewModel.getSharedUsers(deviceID)
            }
        }

        is DeviceSharingActionState.Error -> {
            Log.e(
                "DeviceSharingListScreen",
                "Error: ${(revokePermissionState as DeviceSharingActionState.Error).error}"
            )
        }
    }

    AppTheme {
        val layoutConfig = rememberResponsiveLayoutConfig() // Lấy LayoutConfig
        var showDialog by remember { mutableStateOf(false) }
        val colorScheme = MaterialTheme.colorScheme



        Scaffold(
            topBar = {
                /*
            * Hiển thị Header
             */
                Header(
                    navController = navController,
                    type = "Back",
                    title = "Chi tiết thiết bị"
                )
            },
            bottomBar = {
                /*
            * Hiển thị Thanh Menu dưới cùng
             */
                MenuBottom(navController)
            },
            floatingActionButton = {

                FloatingActionButton(
                    containerColor = colorScheme.primary,
                    onClick = {
                        navController.navigate(Screens.AddSharedUser.route + "?id=$deviceID")
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Thêm người dùng",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp),

                            )
                    }
                )

            },
            containerColor = colorScheme.background,
            modifier = Modifier.fillMaxSize(),
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize() // Chiếm toàn bộ kích thước của màn hình
                        //                    .padding(bottom = layoutConfig.outerPadding) // Padding linh hoạt
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState()), // Cho phép cuộn nếu nội dung vượt khỏi m
                    verticalArrangement = Arrangement.Top, // Sắp xếp các item theo chiều dọc, bắt đầu từ trên xuống.
                    horizontalAlignment = Alignment.CenterHorizontally // Căn chỉnh các item theo chiều ngang vào giữa.
                ) {
                    // Tiêu đề
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight() // Chiều cao vừa đủ với nội dung bên trong
                                .background(color = colorScheme.background)
                        ) {
                            // Cột chứa các phần tử con
                            Column {
                                // Hộp màu xanh dương bo tròn góc dưới bên trái
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth() // Chiếm toàn bộ chiều rộng
                                        .wrapContentHeight() // Chiều cao vừa đủ với nội dung
                                        .background(
                                            color = colorScheme.primary,
                                            shape = RoundedCornerShape(bottomStart = 40.dp)
                                        )
                                ) {
                                    if (isTablet()) {
                                        // Cột chứa văn bản tiêu đề và các TextField
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth() // Chiếm toàn bộ chiều rộng
                                                .padding(
                                                    horizontal = layoutConfig.outerPadding, // Padding ngang linh hoạt
                                                    vertical = layoutConfig.textFieldSpacing // Padding dọc linh hoạt
                                                ),
                                            horizontalAlignment = Alignment.CenterHorizontally // Căn giữa các phần tử con theo chiều ngang
                                        ) {
                                            // Văn bản tiêu đề "TÀI KHOẢN ĐÃ ĐƯỢC CHIA SẼ"
                                            Text(
                                                "TÀI KHOẢN ĐÃ ĐƯỢC CHIA SẺ",
                                                fontSize = layoutConfig.headingFontSize, // Font size linh hoạt
                                                color = colorScheme.onPrimary
                                            )
                                        }
                                    } else {
                                        // Cột chứa văn bản tiêu đề và các TextField
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth() // Chiếm toàn bộ chiều rộng
                                                .padding(
                                                    horizontal = layoutConfig.outerPadding, // Padding ngang linh hoạt
                                                    vertical = layoutConfig.textFieldSpacing // Padding dọc linh hoạt
                                                ),
                                            horizontalAlignment = Alignment.CenterHorizontally // Căn giữa các phần tử con theo chiều ngang
                                        ) {

                                            // Văn bản tiêu đề "TÀI KHOẢN ĐÃ"
                                            Text(
                                                "TÀI KHOẢN ĐÃ",
                                                fontSize = layoutConfig.headingFontSize, // Font size linh hoạt
                                                color = colorScheme.onPrimary
                                            )
                                            // Văn bản tiêu đề "ĐƯỢC CHIA SẼ"
                                            Text(
                                                "ĐƯỢC CHIA SẺ",
                                                fontSize = layoutConfig.headingFontSize, // Font size linh hoạt
                                                color = colorScheme.onPrimary
                                            )
                                        }
                                    }
                                }
                                // Box chứa góc lõm màu xám
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(layoutConfig.cornerBoxSize * 0.9f) // Chiều cao linh hoạt theo LayoutConfig
                                ) {
                                    // Box màu vàng nhỏ nằm trên góc phải
                                    Box(
                                        modifier = Modifier
                                            .size(layoutConfig.cornerBoxSize)
                                            .align(Alignment.TopEnd)
                                            .background(color = colorScheme.primary)
                                            .zIndex(1f)  // Z-index thấp hơn
                                    )

                                    // Box màu xám bo tròn góc lõm trên cùng bên phải
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(
                                                color = colorScheme.background,
                                                shape = RoundedCornerShape(topEndPercent = layoutConfig.cornerBoxRadius)
                                            )
                                            .height(layoutConfig.cornerBoxSize) // Chiều cao linh hoạt
                                            .zIndex(2f), // Z-index cao hơn
                                        contentAlignment = Alignment.Center // Căn Row vào giữa Box
                                    ) {

                                    }
                                }
                            }
                        }


                    // Cột chứa nội dung công tắc Wi-Fi
                    LazyColumn(
                            modifier = Modifier
                                .wrapContentHeight()
                                .heightIn(
                                    min = 300.dp,
                                    max = 600.dp
                                ) // Chiều cao tối thiểu và tối đa
                                .padding(horizontal = layoutConfig.outerPadding) // Padding ngang linh hoạt theo LayoutConfig
                                .width(layoutConfig.contentWidth),               // Độ rộng linh hoạt theo LayoutConfig
                            horizontalAlignment = Alignment.CenterHorizontally   // Căn giữa theo chiều ngang
                        ) {
                        items(sharedUsersList.count()) { index ->
                            SharedUserCard(
                                userName = sharedUsersList[index].SharedWithUser.Name,
                                userEmail = sharedUsersList[index].SharedWithUser.Email,
                                sharedDate = sharedUsersList[index].CreatedAt,
                                permissionId = sharedUsersList[index].PermissionID,
                                onRevokeClick = { id ->
                                    selectedPermissionId = id
                                    showDialog = true
                                }
                            )

                        }
                    }
                }

            }
        )
        if (showDialog) {
            WarningDialog(
                title = "Xác nhận",
                text = "Bạn có chắc chắn muốn thu hồi quyền chia sẻ?",
                onConfirm = {
                    viewModel.revokePermission(selectedPermissionId)
                    showDialog = false
                    viewModel.getSharedUsers(deviceID)
                },
                onDismiss = { showDialog = false }
            )
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DeviceSharingListScreenPreview() {
    DeviceSharingListScreen(navController = rememberNavController())
}

@Composable
fun SharedUserCard(
    userName: String,
    userEmail: String,
    sharedDate: String,
    permissionId: Int,
    onRevokeClick: (Int) -> Unit
) {
    AppTheme {
        // Lấy thông tin layout responsive từ config
        val colorScheme = MaterialTheme.colorScheme
        val layoutConfig = rememberResponsiveLayoutConfig()
        Card(
            modifier = Modifier
                .padding(top = layoutConfig.textFieldSpacing)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(layoutConfig.outerPadding)
                    .fillMaxWidth()
            ) {
                // Hàng đầu tiên: Hiển thị tên và email người dùng
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Vùng hiển thị Avatar hoặc Icon
                    Box(
                        modifier = Modifier
                            .size(layoutConfig.iconSize * 2)
                            .background(Color(0xFFBDC3C7), shape = RoundedCornerShape(50))
                            .clip(CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = userName.firstOrNull()?.toString() ?: "",
                            color = Color.White,
                            fontSize = layoutConfig.textFontSize
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        // Tên người dùng
                        Text(
                            text = userName,
                            fontSize = layoutConfig.textFontSize,
                            fontWeight = FontWeight.Bold,
                            color = colorScheme.onSurface
                        )
                        // Email người dùng
                        Text(
                            text = userEmail,
                            fontSize = layoutConfig.textFontSize * 0.9f,
                            color = colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Ngày chia sẻ quyền
                Text(
                    text = "Chia sẻ ngày: $sharedDate",
                    fontSize = layoutConfig.textFontSize * 0.9f,
                    color = colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Nút thu hồi gỡ bỏ quyền
                Button(
                    onClick = {
                        onRevokeClick(permissionId)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE74C3C)
                    ),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.RemoveCircle,
                        contentDescription = "Gỡ bỏ",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Gỡ bỏ quyền",
                        color = Color.White,
                        fontSize = layoutConfig.textFontSize * 0.9f
                    )
                }
            }
        }
    }
}