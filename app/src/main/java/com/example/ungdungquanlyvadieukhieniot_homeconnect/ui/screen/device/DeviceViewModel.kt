package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device

import android.app.Application
import android.content.Context

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SpaceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.SpaceRepository
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class DeviceState{
    object Idle : DeviceState()               // Chưa làm gì
    object Loading : DeviceState()            // Đang loading
    data class Success(val deviceList: List<DeviceResponse>) :DeviceState()
    data class Error(val error: String) : DeviceState()
}

sealed class SpaceState{
    object Idle :  SpaceState()              // Chưa làm gì
    object Loading :  SpaceState()            // Đang loading
    data class Success(val spacesList: List<SpaceResponse>) : SpaceState()
    data class Error(val error: String) : SpaceState()
}

class DeviceViewModel(application: Application, context: Context) : AndroidViewModel(application) {
    private val repository = SpaceRepository(context) // Repository để quản lý cả Spaces và Devices

    private val _spacesListState = MutableStateFlow<SpaceState>(SpaceState.Idle)
    val spacesListState = _spacesListState.asStateFlow()
    /**
     * Lấy danh sách Spaces theo Home ID
     */
    fun getSpacesByHomeId(homeId: Int) {
        viewModelScope.launch {
            try {
                _spacesListState.value = SpaceState.Loading
                val response = repository.getSpacesByHomeId(homeId)
                _spacesListState.value = SpaceState.Success(response)
            } catch (e: Exception) {
                Log.e("DeviceViewModel", "Error fetching spaces: ${e.message}")
                _spacesListState.value = SpaceState.Error(e.message ?: "Danh sach load thất bại!")
            }
        }
    }

    private val _deviceListState = MutableStateFlow<DeviceState>(DeviceState.Idle)
    val deviceListState = _deviceListState.asStateFlow()

    /**
     * Lấy danh sách thiết bị theo Space ID
     */
    fun loadDevices(spaceId: Int) {
        viewModelScope.launch {
            try {
                _deviceListState.value = DeviceState.Loading
                val response = repository.getDevices(spaceId)
                _deviceListState.value = DeviceState.Success(response)
            } catch (e: Exception) {
                Log.e("DeviceViewModel", "Error fetching devices: ${e.message}")
                _deviceListState.value = DeviceState.Error(e.message ?: "Danh sach load thất bại!")
            }
        }
    }

    fun selectSpace(spaceId: Int) {
        loadDevices(spaceId)
    }
}
