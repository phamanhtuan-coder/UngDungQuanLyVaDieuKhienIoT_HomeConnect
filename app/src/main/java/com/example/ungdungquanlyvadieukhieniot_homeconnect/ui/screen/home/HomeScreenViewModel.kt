package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.home

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SharedWithResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class SharedWithState {
    object Idle : SharedWithState()
    object Loading : SharedWithState()
    data class Success(val sharedWithResponse: List<SharedWithResponse>) : SharedWithState()
    data class Error(val error: String) : SharedWithState()
}

class SharedWithViewModel(application: Application, context: Context) : AndroidViewModel(application) {
    private val repository = UserRepository(context)

    private val _sharedWithState = MutableStateFlow<SharedWithState>(SharedWithState.Idle)
    val sharedWithState: StateFlow<SharedWithState> = _sharedWithState

    fun fetchSharedWith(userId: Int) {
        _sharedWithState.value = SharedWithState.Loading
        viewModelScope.launch {
            try {
                val response = repository.getSharedWith(userId)
                _sharedWithState.value = SharedWithState.Success(response)
            } catch (e: Exception) {
                _sharedWithState.value = SharedWithState.Error(e.message ?: "Unknown error")
                Log.e("sharedWithState.value", SharedWithState.Error(e.message ?: "Unknown error").toString())
            }
        }
    }
}
