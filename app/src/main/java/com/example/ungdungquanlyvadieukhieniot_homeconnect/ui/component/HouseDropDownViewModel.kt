package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.HouseResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.HouseRepository
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.SpaceRepository
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.SpaceState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class HouseState{
    object Idle :  HouseState()              // Chưa làm gì
    object Loading :  HouseState()            // Đang loading
    data class Success(val houseList: List<HouseResponse>) : HouseState()
    data class Error(val error: String) : HouseState()
}

class HouseDropDownViewModel(application: Application, context: Context) : AndroidViewModel(application) {
    private val repository = HouseRepository(context) // Repository để quản lý

    private val _houseListState = MutableStateFlow<HouseState>(HouseState.Idle)
    val houseListState = _houseListState.asStateFlow()

    /**
     * Lấy danh sách House
     */
    fun getListHouse() {
        viewModelScope.launch {
            try {
                _houseListState.value = HouseState.Loading
                val response = repository.getListHome()
                _houseListState.value = HouseState.Success(response)
            } catch (e: Exception) {
                Log.e("DeviceViewModel", "Error fetching spaces: ${e.message}")
                _houseListState.value = HouseState.Error(e.message ?: "Danh sach load thất bại!")
            }
        }
    }
}

class SharedViewModel : ViewModel() {

    // Lưu trữ houseId
    private val _houseId = MutableStateFlow<Int?>(null)
    val houseId: StateFlow<Int?> get() = _houseId

    // Lưu trữ userId
    private val _userId = MutableStateFlow<Int?>(null)
    val userId: StateFlow<Int?> get() = _userId

    // Thiết lập houseId
    fun setHouseId(id: Int) {
        _houseId.value = id
    }

    // Thiết lập userId
    fun setUserId(id: Int) {
        _userId.value = id
    }

    // Xóa houseId
    fun clearHouseId() {
        _houseId.value = null // Xóa giá trị khi không cần nữa
    }

    // Xóa userId
    fun clearUserId() {
        _userId.value = null // Xóa giá trị khi không cần nữa
    }

    // Xóa cả houseId và userId
    fun clearAll() {
        _houseId.value = null
        _userId.value = null
    }
}
