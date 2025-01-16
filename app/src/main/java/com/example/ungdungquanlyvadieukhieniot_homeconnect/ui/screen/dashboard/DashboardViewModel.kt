package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.dashboard

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DailySensorRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DeviceResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.RangeSensorRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.WeeklySensorRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.StatisticsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DashboardState {
    object Idle : DashboardState()               // Chưa làm gì
    object Loading : DashboardState()            // Đang loading
    data class Success(val deviceList: List<DeviceResponse>) :DashboardState()
    data class Error(val error: String) : DashboardState()
}

sealed class DailyStatisticsState {
    object Idle : DailyStatisticsState()
    object Loading : DailyStatisticsState()
    data class Success<T>(val data: T) : DailyStatisticsState()
    data class Error(val error: String) : DailyStatisticsState()
}

sealed class WeeklyStatisticsState {
    object Idle : WeeklyStatisticsState()
    object Loading : WeeklyStatisticsState()
    data class Success<T>(val data: T) : WeeklyStatisticsState()
    data class Error(val error: String) : WeeklyStatisticsState()
}

sealed class RangeStatisticsState {
    object Idle : RangeStatisticsState()
    object Loading : RangeStatisticsState()
    data class Success<T>(val data: T) : RangeStatisticsState()
    data class Error(val error: String) : RangeStatisticsState()
}

class DashboardViewModel(application: Application, context: Context) : AndroidViewModel(application) {
    private val repository = StatisticsRepository(context)

    private val _dailyStatisticsState = MutableStateFlow<DailyStatisticsState>(DailyStatisticsState.Idle)
    val dailyStatisticsState = _dailyStatisticsState.asStateFlow()

    private val _weeklyStatisticsState = MutableStateFlow<WeeklyStatisticsState>(WeeklyStatisticsState.Idle)
    val weeklyStatisticsState = _weeklyStatisticsState.asStateFlow()

    private val _rangeStatisticsState = MutableStateFlow<RangeStatisticsState>(RangeStatisticsState.Idle)
    val rangeStatisticsState = _rangeStatisticsState.asStateFlow()

    fun calculateDailyAverageSensor(request: DailySensorRequest) {
        _dailyStatisticsState.value = DailyStatisticsState.Loading
        viewModelScope.launch {
            try {
                val response = repository.calculateDailyAverageSensor(request)
                _dailyStatisticsState.value = DailyStatisticsState.Success(response)
            } catch (e: Exception) {
                _dailyStatisticsState.value = DailyStatisticsState.Error(e.message ?: "Error calculating daily average sensor")
            }
        }
    }

    fun calculateWeeklyAverageSensor(request: WeeklySensorRequest) {
        _weeklyStatisticsState.value = WeeklyStatisticsState.Loading
        viewModelScope.launch {
            try {
                val response = repository.calculateWeeklyAverageSensor(request)
                _weeklyStatisticsState.value = WeeklyStatisticsState.Success(response)
            } catch (e: Exception) {
                _weeklyStatisticsState.value = WeeklyStatisticsState.Error(e.message ?: "Error calculating weekly average sensor")
            }
        }
    }

    fun calculateAverageSensorForRange(request: RangeSensorRequest) {
        _rangeStatisticsState.value = RangeStatisticsState.Loading
        viewModelScope.launch {
            try {
                val response = repository.calculateAverageSensorForRange(request)
                _rangeStatisticsState.value = RangeStatisticsState.Success(response)
            } catch (e: Exception) {
                _rangeStatisticsState.value = RangeStatisticsState.Error(e.message ?: "Error calculating average sensor for range")
            }
        }
    }

    fun getWeeklyAverageSensor(deviceId: Int) {
        _weeklyStatisticsState.value = WeeklyStatisticsState.Loading
        viewModelScope.launch {
            try {
                val response = repository.getWeeklyAverageSensor(deviceId)
                _weeklyStatisticsState.value = WeeklyStatisticsState.Success(response)
            } catch (e: Exception) {
                _weeklyStatisticsState.value = WeeklyStatisticsState.Error(e.message ?: "Error fetching weekly average sensor")
            }
        }
    }
}