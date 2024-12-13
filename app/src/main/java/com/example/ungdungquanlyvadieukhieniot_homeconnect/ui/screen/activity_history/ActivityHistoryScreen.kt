package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.activityHistory


import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** Giao diện màn hình Lịch sử hoạt động (Activity History Screen)
 * -----------------------------------------
 * Người viết: Phạm Xuân Nhân
 * Ngày viết: 11/12/2024
 * Lần cập nhật cuối: 12/12/2024
 * -----------------------------------------
 * Input: Không có
 *
 * Output: Column chứa các thành phần giao diện của màn hình Lịch sử hoạt động
 */

@Preview(showBackground = true)
@Composable
fun ActivityHistoryScreen() {
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


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),

        )
    {
        Box(modifier = Modifier.align(Alignment.Center)) {
            Column(
                modifier = Modifier
                    .padding(horizontal = horizontalPadding)
                    .verticalScroll(rememberScrollState())
                    .heightIn(max = 700.dp)
                    .widthIn(max = 600.dp),
                verticalArrangement = if (isLandscape) Arrangement.Center else Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                    Text(
                        text = "Lịch sử hoạt động",
                        fontSize = 20.sp,
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                CardActivityHistory(label = "Hoạt động 1", time = "12/10/2024", detail = "Đèn sáng" )
                CardActivityHistory(label = "Hoạt động 2", time = "12/10/2024", detail = "Đèn tắt")
                CardActivityHistory(label = "Hoạt động 3", time = "12/10/2024", detail = "Đèn sáng")
                CardActivityHistory(label = "Hoạt động 4", time = "12/10/2024" , detail = "Đèn tắt")

                Spacer(modifier = Modifier.weight(1f))

            } }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardActivityHistory(label: String, time: String,detail:String) {
    Box (modifier = Modifier.padding(vertical = 8.dp)
        .border(2.dp, Color.Black, RoundedCornerShape(8.dp))
        ) {
        Column( modifier = Modifier .fillMaxWidth()
            .padding(16.dp)
        ) { Text(text = label, fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = time, fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = detail, fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp)) }
    }
}