package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device

import android.app.Application
import android.content.Context

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SpaceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ToggleRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ToggleResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.DeviceRepository
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.SpaceRepository
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail.toggletate
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

sealed class ToggleState {
    object Idle : ToggleState()               // Chưa làm gì
    object Loading : ToggleState()            // Đang loading
    data class Success(val toggle: ToggleResponse) : ToggleState()
    data class Error(val error: String) : ToggleState()
}

class DeviceViewModel(application: Application, context: Context) : AndroidViewModel(application) {
    private val repository = SpaceRepository(context) // Repository để quản lý cả Spaces và Devices
    private val repository2 = DeviceRepository(context)

    private val _toggleState = MutableStateFlow<ToggleState>(ToggleState.Idle)
    val toggleState = _toggleState.asStateFlow()

    fun toggleDevice(deviceId: Int, toggle: ToggleRequest) {
        viewModelScope.launch {
            try {
                _toggleState.value = ToggleState.Loading
                val response = repository2.postToggleDevice(deviceId,toggle)
                _toggleState.value = ToggleState.Success(response)
                Log.d("API Response", toggle.toString())
            } catch (e: Exception) {
                Log.e("DeviceViewModel", "Error fetching toggle: ${e.message}")
                _toggleState.value = ToggleState.Error(e.message ?: "Load thất bại!")
            }
        }
    }

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
