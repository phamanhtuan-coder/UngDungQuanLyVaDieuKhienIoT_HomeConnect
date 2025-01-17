package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.PowerUsageData
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SensorAverageData
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.StatisticsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class StatisticsState {
    object Idle : StatisticsState()
    object Loading : StatisticsState()
    data class PowerUsageSuccess(val message: String, val data: List<PowerUsageData>) : StatisticsState()
    data class SensorAveragesSuccess(val message: String, val data: List<SensorAverageData>) : StatisticsState()
    data class Error(val error: String) : StatisticsState()
}

class StatisticsViewModel(val repository: StatisticsRepository) : ViewModel() {

    private val _statisticsState = MutableStateFlow<StatisticsState>(StatisticsState.Idle)
    val statisticsState = _statisticsState.asStateFlow()

    fun fetchDailyRoomPowerUsage(spaceId: Int, startDate: String, endDate: String) {
        _statisticsState.value = StatisticsState.Loading
        viewModelScope.launch {
            try {
                val response = repository.getDailyRoomPowerUsage(spaceId, startDate, endDate)
                _statisticsState.value = StatisticsState.PowerUsageSuccess(message = response.message, response.data)
            } catch (e: Exception) {
                _statisticsState.value = StatisticsState.Error(e.message ?: "Đã xảy ra lỗi không xác định")
            }
        }
    }

    fun fetchDailyRoomAveragesSensor(spaceId: Int, startDate: String, endDate: String) {
        _statisticsState.value = StatisticsState.Loading
        viewModelScope.launch {
            try {
                val response = repository.getDailyRoomAveragesSensor(spaceId, startDate, endDate)
                _statisticsState.value = StatisticsState.SensorAveragesSuccess(response.message, response.data)
            } catch (e: Exception) {
                _statisticsState.value = StatisticsState.Error(e.message ?: "Đã xảy ra lỗi không xác định")
            }
        }
    }
}
