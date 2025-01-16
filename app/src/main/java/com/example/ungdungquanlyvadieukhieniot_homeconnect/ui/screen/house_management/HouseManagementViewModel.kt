package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.house_management

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.HousesListPesponse
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
}