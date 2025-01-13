package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail_for_fire_alarm

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.DeviceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class getInfoDeviceState {
    object Idle : getInfoDeviceState()               // Chưa làm gì
    object Loading : getInfoDeviceState()            // Đang loading
    data class Success(val device: DeviceResponse) : getInfoDeviceState()
    data class Error(val error: String) : getInfoDeviceState()
}

class FireAlarmDetailViewModel(application: Application, context: Context) : AndroidViewModel(application) {
    private val repository = DeviceRepository(context) // Repository để quản lý cả Spaces và Devices

    private val _infoDeviceState = MutableStateFlow<com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail.getInfoDeviceState>(
        com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail.getInfoDeviceState.Idle)
    val infoDeviceState = _infoDeviceState.asStateFlow()
    /**
     * Lấy danh sách Spaces theo Home ID
     */
    fun getInfoDevice(deviceId: Int) {
        viewModelScope.launch {
            try {
                _infoDeviceState.value = com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail.getInfoDeviceState.Loading
                val response = repository.getInfoDevice(deviceId)
                _infoDeviceState.value = com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail.getInfoDeviceState.Success(response)
            } catch (e: Exception) {
                Log.e("DeviceViewModel", "Error fetching spaces: ${e.message}")
                _infoDeviceState.value = com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail.getInfoDeviceState.Error(e.message ?: "Danh sach load thất bại!")
            }
        }
    }
}