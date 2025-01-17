package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.activity_history

import android.app.Application
import android.content.Context

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ActionData
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.FormattedLightDetails
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.FormattedLog
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.FormattedSensorDetails
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LightAttribute
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LightDetails
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LogResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SensorDetails
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.LogRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ActivityHistoryViewModel(application: Application, context: Context) : AndroidViewModel(application) {
    private val repository = LogRepository(context)
    private val gson = Gson()

    private val _logsState = MutableStateFlow<LogsState>(LogsState.Idle)
    val logsState = _logsState.asStateFlow()

    private val _selectedLogState = MutableStateFlow<SelectedLogState>(SelectedLogState.Idle)


    fun getDeviceLogs(deviceId: Int) {
        Log.d("ActivityHistoryVM", "Getting logs for device: $deviceId")
        viewModelScope.launch {
            try {
                _logsState.value = LogsState.Loading
                val response = repository.getDeviceLogs(deviceId)
                Log.d("ActivityHistoryVM", "Got logs: ${response.size}")
                val formattedLogs = response.map { formatLog(it) }
                _logsState.value = LogsState.Success(formattedLogs)
            } catch (e: Exception) {
                Log.e("ActivityHistoryVM", "Error: ${e.message}")
                _logsState.value = LogsState.Error(e.message ?: "Không thể tải lịch sử hoạt động!")
            }
        }
    }

    private fun formatLog(log: LogResponse): FormattedLog {
        val actionData = try {
            gson.fromJson(log.Action, ActionData::class.java)
        } catch (e: Exception) {
            ActionData(false, "unknown")
        }

        val formattedDetails = when (log.Device.TypeID) {
            1 -> { // Báo cháy
                try {
                    val details = gson.fromJson(log.Details, SensorDetails::class.java)
                    FormattedSensorDetails(
                        gas = details.gas,
                        temperature = details.temperature,
                        humidity = details.humidity
                    )
                } catch (e: Exception) {
                    null
                }
            }
            2 -> { // Đèn
                try {
                        val details = gson.fromJson(log.Details, LightDetails::class.java)
                        val attribute = if (!details.attribute.isNullOrEmpty()) {
                            gson.fromJson(details.attribute, LightAttribute::class.java)
                        } else {
                            // Thử lấy từ Device.Attribute nếu có
                            if (!log.Device.Attribute.isNullOrEmpty()) {
                                gson.fromJson(log.Device.Attribute, LightAttribute::class.java)
                            } else null
                        }

                        FormattedLightDetails(
                            action = details.action,
                            powerStatus = details.powerStatus,
                            brightness = attribute?.brightness,
                            color = attribute?.color
                        )

                } catch (e: Exception) {
                    null
                }
            }
            else -> null
        }

        return FormattedLog(
            id = log.LogID,
            deviceName = log.Device.Name,
            deviceType = log.Device.TypeID,
            actionType = actionData.type,
            timestamp = log.Timestamp,
            details = formattedDetails
        )
    }

    fun selectLog(log: FormattedLog) {

        _selectedLogState.value = SelectedLogState.Selected(log)
        Log.d("ViewModel", "selectedLogState: ${_selectedLogState.value}")
    }
}

sealed class LogsState {
    object Idle : LogsState()
    object Loading : LogsState()
    data class Success(val logs: List<FormattedLog>) : LogsState()
    data class Error(val error: String) : LogsState()
}

sealed class SelectedLogState {
    object Idle : SelectedLogState()
    data class Selected(val log: FormattedLog) : SelectedLogState()
}