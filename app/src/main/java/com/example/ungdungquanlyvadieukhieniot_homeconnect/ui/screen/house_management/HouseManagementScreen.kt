package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.house_management

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Castle
import androidx.compose.material.icons.filled.Cottage
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.LocalLibrary
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Villa
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.Header
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component.MenuBottom


/**
 * Màn hình quản lý nhà
 * --------------------------------------------
 * Người viết: Phạm Anh Tuấn
 * Ngày viết: 11/12/2024
 * Lần cập nhật cuối: 13/12/2024
 *
 * --------------------------------------------
 * @param modifier Modifier
 * @return Scaffold Màn hình quản lý nhà
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HouseManagementScreen(
    modifier: Modifier = Modifier
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val isTablet = screenWidth > 600

    val isPopupVisible = remember { mutableStateOf(false) }
    val isEditing = remember { mutableStateOf(false) }
    val editingData = remember { mutableStateOf(HouseData()) }

    return Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Header("Back", "Quản lý nhà")
        },
        bottomBar = {
            MenuBottom()
        },
        floatingActionButton = {
            // FAB action nếu có
        },
        floatingActionButtonPosition = FabPosition.End,
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),

            ) {
                Text(
                    text = "Danh sách nhà",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3 * 100.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(5) { index ->
                        CardHouse(isTablet) { name, address, icon ->
                            editingData.value = HouseData(name, address, icon)
                            isEditing.value = true
                            isPopupVisible.value = true
                        }
                    }
                }
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            isEditing.value = false
                            editingData.value = HouseData()
                            isPopupVisible.value = true
                        },
                        modifier = Modifier
                            .padding(top = 16.dp, end = if (isTablet) 60.dp else 16.dp)
                            .align(Alignment.CenterEnd)
                    ) {
                        Text(
                            text = "Thêm nhà",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }


                if (isPopupVisible.value) {
                    AddHousePopup(
                        houseData = editingData.value,
                        isEditing = isEditing.value,
                        onDismiss = { isPopupVisible.value = false },
                        onAddOrUpdateHouse = { name, address, icon, color ->
                            isPopupVisible.value = false
                        },
                        isTablet = isTablet
                    )
                }
            }
        }
    )
}

/**
 * Hiển thị card thông tin nhà
 * --------------------------------------------
 * Người viết: Phạm Anh Tuấn
 * Ngày viết: 11/12/2024
 * Lần cập nhật cuối: 13/12/2024
 *
 * --------------------------------------------
 * @param isTablet trạng thái thiết bị là tablet hay không
 * @param onEdit hàm xử lý khi click vào card
 * @return Card chứa thông tin nhà
 */
@Composable
fun CardHouse(isTablet: Boolean, onEdit: (String, String, String) -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        return Card(
            modifier = Modifier
                .widthIn(max = if (isTablet) 600.dp else 400.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                        tint = Color(0xFF2196F3),
                        modifier = Modifier.padding(end = 16.dp)
                    )
                    Text(
                        text = "House 1",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                IconButton(
                    onClick = { onEdit("House 1", "Address 1", "Home") }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = Color(0xFF2196F3)
                    )
                }
            }
        }
    }
}


/**
 * Hiển thị popup thêm hoặc chỉnh sửa nhà
 * --------------------------------------------
 * Người viết: Phạm Anh Tuấn
 * Ngày viết: 11/12/2024
 * Lần cập nhật cuối: 13/12/2024
 *
 * --------------------------------------------
 *  @param houseData thông tin nhà
 *  @param isEditing trạng thái chỉnh sửa
 *  @param onDismiss hàm xử lý khi dismiss popup
 *  @param onAddOrUpdateHouse hàm xử lý khi thêm hoặc cập nhật nhà
 *  @param isTablet trạng thái thiết bị là tablet hay không
 *
 *  @return AlertDialog chứa thông tin nhà
 */
@Composable
fun AddHousePopup(
    houseData: HouseData,
    isEditing: Boolean,
    onDismiss: () -> Unit,
    onAddOrUpdateHouse: (String, String, String, Color) -> Unit,
    isTablet: Boolean
) {
    val houseName = remember { mutableStateOf(houseData.name) }
    val houseAddress = remember { mutableStateOf(houseData.address) }
    val selectedIcon = remember { mutableStateOf(houseData.icon) }
    val selectedColor = remember { mutableStateOf(houseData.color ?: Color.Transparent) }

    val icons = listOf(
        Pair(Icons.Default.Home, "Nhà"),
        Pair(Icons.Default.Work, "Cơ quan"),
        Pair(Icons.Default.School, "Trường"),
        Pair(Icons.Default.AccountBalance, "Ngân hàng"),
        Pair(Icons.Default.Apartment, "Căn hộ"),
        Pair(Icons.Default.Hotel, "Khách sạn"),
        Pair(Icons.Default.Villa, "Biệt thự"),
        Pair(Icons.Default.Cottage, "Nhà gỗ"),
        Pair(Icons.Default.Castle, "Lâu đài"),
        Pair(Icons.Default.LocalLibrary, "Thư viện")
    )

    val colors = listOf(
        Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Cyan,
        Color.Magenta, Color.Gray, Color.Black, Color.White, Color(0xFF2196F3)
    )

    return AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                onAddOrUpdateHouse(houseName.value, houseAddress.value, selectedIcon.value, selectedColor.value)
            }) {
                Text(if (isEditing) "Lưu" else "Thêm nhà")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Hủy")
            }
        },
        title = {
            Text(
                text = if (isEditing) "Chỉnh sửa nhà" else "Thêm nhà mới",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        },
        text = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = if (isTablet) 32.dp else 16.dp), // Padding adjustment
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(if (isTablet) 0.6f else 1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = houseName.value,
                        onValueChange = { houseName.value = it },
                        label = { Text("Tên nhà") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = houseAddress.value,
                        onValueChange = { houseAddress.value = it },
                        label = { Text("Địa chỉ nhà") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    IconPicker(
                        icons = icons,
                        selectedIcon = selectedIcon
                    )
                    Text(
                        text = "Chọn màu sắc:",
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(bottom = 8.dp, top = 16.dp)
                    )
                    Row(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        colors.forEach { color ->
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(color, CircleShape)
                                    .border(
                                        width = if (selectedColor.value == color) 1.dp else 0.dp,
                                        color = if (selectedColor.value == color) Color.Black else Color.Transparent,
                                        shape = CircleShape
                                    )
                                    .clickable { selectedColor.value = color }
                            )
                        }
                    }
                }
            }
        }

    )
}

/**
 * Hiển thị danh sách icon để chọn
 * --------------------------------------------
 * Người viết: Phạm Anh Tuấn
 * Ngày viết: 11/12/2024
 * Lần cập nhật cuối: 13/12/2024
 *
 * --------------------------------------------
 *  @param icons danh sách icon
 *  @param selectedIcon icon được chọn
 *  @return Column chứa danh sách icon
 *
 */
@Composable
fun IconPicker(
    icons: List<Pair<ImageVector, String>>,
    selectedIcon: MutableState<String>
) {
    return Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Chọn biểu tượng:",
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
            content = {
                items(icons.size) { index ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                selectedIcon.value = icons[index].second
                            }
                    ) {
                        Icon(
                            imageVector = icons[index].first,
                            contentDescription = null,
                            tint = if (selectedIcon.value == icons[index].second) Color(0xFF2196F3) else Color.Gray,
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            text = icons[index].second,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        )
    }
}


//Tạm thời, sau này sẽ thay bằng viewmodel
data class HouseData(
    val name: String = "",
    val address: String = "",
    val icon: String = "",
    val color: Color? = null
)