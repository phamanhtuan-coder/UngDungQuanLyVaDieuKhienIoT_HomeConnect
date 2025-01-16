package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.activity_detail

import androidx.lifecycle.ViewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.LogDetailNavArg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ActivityHistoryDetailViewModel : ViewModel() {
    private val _logDetail = MutableStateFlow<LogDetailNavArg?>(null)
    val logDetail = _logDetail.asStateFlow()

    fun setLogDetail(detail: LogDetailNavArg) {
        _logDetail.value = detail
    }
}