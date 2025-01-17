package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.add_device

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LinkedDevice
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.DeviceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DeviceState {
    object Idle : DeviceState()
    object Loading : DeviceState()
    data class LinkSuccess(val message: String, val linkedDevice: LinkedDevice) : DeviceState()
    data class Error(val error: String) : DeviceState()
}

class AddDeviceViewModel(application: Application, context: Context) : AndroidViewModel(application) {

    private val repository = DeviceRepository(context)
    private val _deviceState = MutableStateFlow<DeviceState>(DeviceState.Idle)
    val deviceState = _deviceState.asStateFlow()

    fun linkDevice(deviceId: String, spaceId: String, deviceName: String) {
        _deviceState.value = DeviceState.Loading
        viewModelScope.launch {
            try {
                val response = repository.linkDevice(deviceId, spaceId, deviceName)
                _deviceState.value = DeviceState.LinkSuccess(
                    message = response.message,
                    linkedDevice = response.device
                )
                Log.d("AddDeviceViewModel", "Liên kết thiết bị thành công: ${response.device}")
            } catch (e: Exception) {
                val errorMessage = if (e is retrofit2.HttpException) {
                    e.response()?.errorBody()?.string() ?: "Lỗi không xác định"
                } else {
                    e.message ?: "Lỗi không xác định"
                }
                _deviceState.value = DeviceState.Error(errorMessage)
                Log.e("AddDeviceViewModel", "Lỗi khi liên kết thiết bị: $errorMessage")
            }
        }
    }
}