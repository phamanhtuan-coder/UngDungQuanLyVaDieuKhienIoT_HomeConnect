package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/** Giao diện nút Home (FAB Home)
 * -----------------------------------------
 * Người viết: Phạm Anh Tuấn
 * Ngày viết: 29/11/2024
 * Lần cập nhật cuối: 29/11/2024
 * -----------------------------------------
 * Input: Không có
 * Output: FloatingActionButton
 *
 *
 * ---------------------------------------
 */
@Preview(showBackground = true)
@Composable
fun NutHome() {
  return  FloatingActionButton(
        modifier = Modifier.offset(y = 52.dp),
        shape = CircleShape,
        onClick = {
            /*TODO*/
        },
        content = { Icon(Icons.Filled.Home, contentDescription = "Home") }
    )
}