package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.HouseResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.HouseRepository
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.SpaceRepository
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.SpaceState
import kotlinx.coroutines.flow.MutableStateFlow
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