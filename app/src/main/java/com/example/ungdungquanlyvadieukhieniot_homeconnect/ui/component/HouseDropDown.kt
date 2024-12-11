package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Thanh dropdown chọn House
 * -----------------------------------------
 * Người viết: Phạm Anh Tuấn
 * Ngày viết: 10/12/2024
 * Lần cập nhật cuối: 11/12/2024
 *
 * -----------------------------------------
 *
 * @param houses: Danh sách các House
 *
 * @return Column chứa thanh dropdown chọn House
 *
 * ---------------------------------------
 */
@Preview(showBackground = true)
@Composable
fun HouseSelection(
    houses: List<String> = listOf("House 1", "House 2", "House 3")

) {
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(houses.first()) }

   return Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5F5F5))
                .clickable { isDropdownExpanded = !isDropdownExpanded }
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedItem,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    imageVector = if (isDropdownExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown Icon",
                    tint = Color(0xFF2196F3)
                )
            }
        }

        if (isDropdownExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF5F5F5))
                    .padding(vertical = 4.dp)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
            ) {
                houses.forEach { house ->
                    Text(
                        text = house,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedItem = house
                                isDropdownExpanded = false
                            }
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}