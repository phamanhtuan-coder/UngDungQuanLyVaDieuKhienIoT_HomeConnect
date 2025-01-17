package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_detail

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AttributeRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.PowerUsageData2
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SensorData
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ToggleRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ToggleResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.DeviceRepository
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.StatisticsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

sealed class AttributeState {
    object Idle : AttributeState()               // Chưa làm gì
    object Loading : AttributeState()           // Đang loading
    data class Success(val message: String, val device: DeviceResponse) :AttributeState()
    data class Error(val error: String) : AttributeState()
}

sealed class UnlinkState {
    object Idle : UnlinkState()               // Chưa làm gì
    object Loading : UnlinkState()           // Đang loading
    data class Success(val message: String) : UnlinkState()
    data class Error(val error: String) : UnlinkState()
}

sealed class CalculationState {
    object Idle : CalculationState()
    object Loading : CalculationState()

    // Trả về SensorData (thay vì SensorData2)
    data class AverageSensorSuccess(
        val message: String,
        val data: SensorData
    ) : CalculationState()

    // Trả về PowerUsageData (thay vì PowerUsageData2)
    data class PowerUsageSuccess(
        val message: String,
        val data: PowerUsageData2
    ) : CalculationState()

    data class Error(val error: String) : CalculationState()
}


class DeviceDetailViewModel(application: Application, context: Context) : AndroidViewModel(application) {
    private val repository = DeviceRepository(context) // Repository để quản lý cả Spaces và Devices
    private val repository2 = StatisticsRepository(context)

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

    private val _attributeState = MutableStateFlow<AttributeState>(AttributeState.Idle)
    val attributeState = _attributeState.asStateFlow()

    fun attributeDevice(deviceId: Int, attribute: AttributeRequest) {
        viewModelScope.launch {
            try {
                _attributeState.value = AttributeState.Loading
                val response = repository.postAttributeDevice(deviceId, attribute)
                _attributeState.value = AttributeState.Success(response.message, response.device)
            } catch (e: Exception) {
                Log.e("DeviceDetailViewModel", "Error fetching attribute: ${e.message}")
                _attributeState.value = AttributeState.Error(e.message ?: "Load thất bại!")
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

    // ---------------- Tính toán thống kê ---------------- //
    private val _calculationState = MutableStateFlow<CalculationState>(CalculationState.Idle)
    val calculationState: StateFlow<CalculationState> = _calculationState.asStateFlow()

    fun calculateDailyAverageSensor(deviceId: Int, date: String) {
        _calculationState.value = CalculationState.Loading
        viewModelScope.launch {
            try {
                val response = repository2.calculateDailyAverageSensor(deviceId, date)
                // response.data bây giờ là kiểu SensorData
                _calculationState.value = CalculationState.AverageSensorSuccess(
                    message = response.message,
                    data = response.data
                )
            } catch (e: Exception) {
                _calculationState.value = CalculationState.Error(e.message ?: "Đã xảy ra lỗi không xác định")
            }
        }
    }

    fun calculateDailyPowerUsage(deviceId: Int, date: String) {
        _calculationState.value = CalculationState.Loading
        viewModelScope.launch {
            try {
                val response = repository2.calculateDailyPowerUsage(deviceId, date)
                // response.data bây giờ là kiểu PowerUsageData
                _calculationState.value = CalculationState.PowerUsageSuccess(
                    message = response.message,
                    data = response.data
                )
            } catch (e: Exception) {
                _calculationState.value = CalculationState.Error(e.message ?: "Đã xảy ra lỗi không xác định")
            }
        }
    }
}