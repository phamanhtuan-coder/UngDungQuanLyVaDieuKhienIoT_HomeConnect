package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail_for_fire_alarm

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LogLastest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ToggleRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ToggleResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.DeviceRepository
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.LogRepository
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

sealed class UnlinkState {
    object Idle : UnlinkState()               // Chưa làm gì
    object Loading : UnlinkState()           // Đang loading
    data class Success(val message: String) : UnlinkState()
    data class Error(val error: String) : UnlinkState()
}

sealed class LogLastestState {
    object Idle : LogLastestState()               // Chưa làm gì
    object Loading : LogLastestState()           // Đang loading
    data class Success(val log: LogLastest) : LogLastestState()
    data class Error(val error: String) : LogLastestState()
}



class FireAlarmDetailViewModel(application: Application, context: Context) : AndroidViewModel(application) {
    private val repository = DeviceRepository(context) // Repository để quản lý cả Spaces và Devices

    private val repositoryLog = LogRepository(context) // Repository để quản lý cả Log
    private val _logLastestState = MutableStateFlow<LogLastestState>(LogLastestState.Idle)
    val logLastestState = _logLastestState.asStateFlow()

    private val _toggleLogState = MutableStateFlow<LogLastestState>(LogLastestState.Idle)
    val toggleLogState = _toggleLogState.asStateFlow()

    fun getLatestToggle(DeviceID: Int) {
        viewModelScope.launch {
            try {
                _toggleLogState.value = LogLastestState.Loading
                val response = repositoryLog.getLastestToggle(DeviceID)
                _toggleLogState.value = LogLastestState.Success(response)
            } catch (e: Exception) {
                Log.e("DeviceViewModel", "Error fetching spaces: ${e.message}")
                _toggleLogState.value = LogLastestState.Error(e.message ?: "Load Toggle thất bại!")
            }
        }
    }

    fun getLastestSensor(DeviceID: Int) {
        viewModelScope.launch {
            try {
                _logLastestState.value = LogLastestState.Loading
                val response = repositoryLog.getLatestSensor(DeviceID)
                _logLastestState.value = LogLastestState.Success(response)
            } catch (e: Exception) {
                Log.e("DeviceViewModel", "Error fetching spaces: ${e.message}")
                _logLastestState.value = LogLastestState.Error(e.message ?: "Load Sensor thất bại!")
            }
        }
    }

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

    private val _unlinkState = MutableStateFlow<UnlinkState>(UnlinkState.Idle)
    val unlinkState = _unlinkState.asStateFlow()

    fun unlinkDevice(deviceId: Int) {
        viewModelScope.launch {
            try {
                _unlinkState.value = UnlinkState.Loading
                val response = repository.postUnlink(deviceId)
                _unlinkState.value = UnlinkState.Success(response.message)
            } catch (e: Exception) {
                Log.e("DeviceDetailViewModel", "Error fetching unlink: ${e.message}")
                _unlinkState.value = UnlinkState.Error(e.message ?: "Load thất bại!")
            }
        }
    }
}