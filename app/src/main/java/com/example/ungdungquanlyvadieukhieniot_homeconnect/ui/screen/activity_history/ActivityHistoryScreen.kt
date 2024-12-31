package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.activity_history


import android.content.res.Configuration
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.login.LoginScreen
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme

/** Giao diện màn hình Lịch sử hoạt động (Activity History Screen)
 * -----------------------------------------
 * Người viết: Phạm Xuân Nhân
 * Ngày viết: 11/12/2024
 * Lần cập nhật cuối: 12/12/2024
 * -----------------------------------------
 * Input: Không có
 *
 * Output: Column chứa các thành phần giao diện của màn hình Lịch sử hoạt động
 *
 *Người cập nhật: Nguyễn Thanh Sang
 *Ngày viết: 31/12/2024
 *---------------------
 * - Thêm phần navigation
 * - Sửa giao diện Lịch sử hoạt động
 */

@Composable
fun ActivityHistoryScreen(
  navController: NavHostController
) {
    AppTheme {
        val title by remember { mutableStateOf("Lịch sử hoạt động") }
        val colorScheme = MaterialTheme.colorScheme
        val configuration = LocalConfiguration.current
        val screenWidthDp = configuration.screenWidthDp.dp

        // Xác định xem nếu chúng ta đang ở chế độ ngang
        val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        //  kích thước phản hồi
        val horizontalPadding = when {
            screenWidthDp < 360.dp -> 8.dp
            screenWidthDp < 600.dp -> 16.dp
            else -> 32.dp
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = colorScheme.background,
            topBar = {
                Header(
                    navController = navController,
                    type = "Back",
                    title = title,
                )


            },
            bottomBar = {
                MenuBottom(navController)
            },
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(colorScheme.background),
                )
                {
                    Box(modifier = Modifier.align(Alignment.Center)) {
                        LazyColumn (
                            modifier = Modifier
                                .padding(horizontal = horizontalPadding)
                                .heightIn(max = 700.dp)
                                .widthIn(max = 600.dp),
                            verticalArrangement = if (isLandscape) Arrangement.Center else Arrangement.SpaceBetween
                        ) {
                            items(5) {
                                CardActivityHistory(label = "Hoạt động 1", time = "12/10/2024", detail = "Đèn sáng" )
                            }
                        }
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardActivityHistory(label: String, time: String,detail:String) {
    AppTheme {
        val colorScheme = MaterialTheme.colorScheme
        Box(

            modifier = Modifier
                .padding(vertical = 8.dp)
                .background(colorScheme.secondary, RoundedCornerShape(8.dp) )
                .border(2.dp, colorScheme.onBackground, RoundedCornerShape(8.dp))
            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = colorScheme.onSecondary)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = time, fontSize = 14.sp, color = colorScheme.onSecondary)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = detail, fontSize = 14.sp, color = colorScheme.onSecondary)
                Spacer(modifier = Modifier.height(4.dp)) }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ActivityHistoryScreenPreview() {
    ActivityHistoryScreen(navController = rememberNavController())
}