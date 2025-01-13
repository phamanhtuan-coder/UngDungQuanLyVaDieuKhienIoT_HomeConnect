package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.RegisterRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ToggleRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ToggleResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.DeviceRepository
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.SpaceRepository
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device.SpaceState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class getInfoDeviceState {
    object Idle : getInfoDeviceState()               // Chưa làm gì
    object Loading : getInfoDeviceState()            // Đang loading
    data class Success(val device: DeviceResponse) : getInfoDeviceState()
    data class Error(val error: String) : getInfoDeviceState()
}

sealed class toggletate {
    object Idle : toggletate()               // Chưa làm gì
    object Loading : toggletate()            // Đang loading
    data class Success(val toggle: ToggleResponse) : toggletate()
    data class Error(val error: String) : toggletate()
}

class DeviceDetailViewModel(application: Application, context: Context) : AndroidViewModel(application) {
    private val repository = DeviceRepository(context) // Repository để quản lý cả Spaces và Devices

    private val _infoDeviceState = MutableStateFlow<getInfoDeviceState>(getInfoDeviceState.Idle)
    val infoDeviceState = _infoDeviceState.asStateFlow()

    fun getInfoDevice(deviceId: Int) {
        viewModelScope.launch {
            try {
                _infoDeviceState.value = getInfoDeviceState.Loading
                val response = repository.getInfoDevice(deviceId)
                Log.e("API Response", response.toString())
                _infoDeviceState.value = getInfoDeviceState.Success(response)
            } catch (e: Exception) {
                Log.e("DeviceViewModel", "Error fetching spaces: ${e.message}")
                _infoDeviceState.value = getInfoDeviceState.Error(e.message ?: "Danh sach load thất bại!")
            }
        }
    }

    private val _toggleState = MutableStateFlow<toggletate>(toggletate.Idle)
    val toggleState = _toggleState.asStateFlow()

    fun toggleDevice(deviceId: Int, toggle: ToggleRequest) {
        viewModelScope.launch {
            try {
                _toggleState.value = toggletate.Loading
                val response = repository.postToggleDevice(deviceId,toggle)
                _toggleState.value = toggletate.Success(response)
                Log.d("API Response", toggle.toString())
            } catch (e: Exception) {
                Log.e("DeviceDetailViewModel", "Error fetching toggle: ${e.message}")
                _toggleState.value = toggletate.Error(e.message ?: "Load thất bại!")
            }
        }
    }
}