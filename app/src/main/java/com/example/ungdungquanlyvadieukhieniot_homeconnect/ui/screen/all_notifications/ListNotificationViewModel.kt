package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.all_notifications

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.AlertResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.AlertRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class NotificationState {
    object Idle : NotificationState()               // Chưa làm gì
    object Loading : NotificationState()            // Đang loading
    data class Success(val alertList: List<AlertResponse>) : NotificationState()
    data class Error(val error: String) : NotificationState()
}


class ListNotificationModel(application: Application, context: Context) :
    AndroidViewModel(application) {
    private val repository = AlertRepository(context)
    private val _alertListState = MutableStateFlow<NotificationState>(NotificationState.Idle)
    val alertListState = _alertListState.asStateFlow()

    /**
     * Lấy danh sách thông báo
     */
    fun getAllByUser() {
        viewModelScope.launch {
            try {
                _alertListState.value = NotificationState.Loading
                val response = repository.getAllByUser()
                _alertListState.value = NotificationState.Success(response)
            } catch (e: Exception) {
                Log.e("ListNotificationModel", "Error fetching alerts: ${e.message}")
                _alertListState.value =
                    NotificationState.Error(e.message ?: "Danh sach load thất bại!")
            }
        }
    }
}