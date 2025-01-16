import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimeSelectionDropdown(
    onTabSelected: (String) -> Unit,
    onManageTimeClicked: (String, String) -> Unit
) {
    val timeOptions = listOf("Ngày", "Tuần", "Tháng", "Khoảng thời gian")

    var selectedItem by remember {
        mutableStateOf(timeOptions.first())
    }
    var isDropdownExpanded by remember { mutableStateOf(false) }
    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(colorScheme.surface)
                .clickable {
                    isDropdownExpanded = !isDropdownExpanded
                }
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedItem,
                    color = colorScheme.onSurface,
                    fontSize = 18.sp
                )
                Icon(
                    imageVector = if (isDropdownExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = colorScheme.primary
                )
            }
        }

        if (isDropdownExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorScheme.surfaceVariant)
                    .padding(vertical = 4.dp)
            ) {
                timeOptions.forEach { option ->
                    Text(
                        text = option,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedItem = option
                                isDropdownExpanded = false
                                if (option == "Khoảng thời gian") {
                                    onManageTimeClicked("", "")
                                } else {
                                    onTabSelected(option)
                                }
                            }
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        fontSize = 16.sp,
                        color = colorScheme.onSurface
                    )
                }
            }
        }
    }
}


