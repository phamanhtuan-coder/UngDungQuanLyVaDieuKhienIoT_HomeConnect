package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.house_management

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.CreateHouseRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.HouseDetail
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.HousesListPesponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.UpdateHouseRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.HouseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class HouseManagementState {
    object Idle : HouseManagementState()               // Chưa làm gì
    object Loading : HouseManagementState()            // Đang loading
    data class Success(val houses: List<HousesListPesponse>) : HouseManagementState()
    data class Error(val error: String?) : HouseManagementState()
}

sealed class UpdateHouseState {
    object Idle : UpdateHouseState()
    object Loading : UpdateHouseState()
    data class Success(val message: String, val house: HouseDetail) : UpdateHouseState()
    data class Error(val error: String) : UpdateHouseState()
}

sealed class CreateHouseState {
    object Idle : CreateHouseState()
    object Loading : CreateHouseState()
    data class Success(val message: String, val house: HouseDetail1, val space: DefaultSpace) : CreateHouseState()
    data class Error(val error: String) : CreateHouseState()
}

class HouseManagementViewModel(application: Application, context: Context) : AndroidViewModel(application) {

    private val repository = HouseRepository(context)

    // StateFlow để UI lắng nghe trạng thái
    private val _houseManagementState = MutableStateFlow<HouseManagementState>(HouseManagementState.Idle)
    val houseManagementState = _houseManagementState.asStateFlow()

    // Hàm lấy danh sách Houses
    fun fetchHouses() {
        _houseManagementState.value = HouseManagementState.Loading
        viewModelScope.launch {
            try {
                val houses = repository.getHouses()
                _houseManagementState.value = HouseManagementState.Success(houses)
            } catch (e: Exception) {
                Log.e("HouseManagementViewModel", "Fetch houses error: ${e.message}")
                _houseManagementState.value =
                    HouseManagementState.Error(e.message ?: "Không thể tải danh sách houses!")
            }
        }
    }

    private val _updateHouseState = MutableStateFlow<UpdateHouseState>(UpdateHouseState.Idle)
    val updateHouseState = _updateHouseState.asStateFlow()

    fun updateHouse(houseId: Int, request: UpdateHouseRequest) {
        _updateHouseState.value = UpdateHouseState.Loading
        viewModelScope.launch {
            try {
                // Gọi hàm cập nhật từ Repository
                val response = repository.updateHouse(houseId, request)
                _updateHouseState.value = UpdateHouseState.Success(response.message, response.house)
            } catch (e: Exception) {
                _updateHouseState.value = UpdateHouseState.Error(e.message ?: "Đã xảy ra lỗi")
            }
        }
    }

    private val _createHouseState = MutableStateFlow<CreateHouseState>(CreateHouseState.Idle)
    val createHouseState = _createHouseState.asStateFlow()

    fun createHouse(request: CreateHouseRequest) {
        _createHouseState.value = CreateHouseState.Loading
        viewModelScope.launch {
            try {
                val response = repository.createHouse(request)
                _createHouseState.value = CreateHouseState.Success(response.message, response.house, response.space)
            } catch (e: Exception) {
                _createHouseState.value = CreateHouseState.Error(e.message ?: "Đã xảy ra lỗi")
            }
        }
    }
}