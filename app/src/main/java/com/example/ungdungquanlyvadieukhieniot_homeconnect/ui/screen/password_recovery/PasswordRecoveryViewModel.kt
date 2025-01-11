package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.password_recovery

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.AuthRepository
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.OTPRepository
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.login.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class CheckEmailState {
    object Idle : CheckEmailState()               // Chưa làm gì
    object Loading : CheckEmailState()            // Đang loading
    data class Success( val exists:Boolean, val message: String) : CheckEmailState()
    data class Error(val success:Boolean, val message: String) : CheckEmailState()
}

class PasswordRecoveryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = OTPRepository()

    // StateFlow cho UI lắng nghe
    private val _checkEmailState = MutableStateFlow<CheckEmailState>(CheckEmailState.Idle)
    val checkEmailState = _checkEmailState.asStateFlow()

    // Hàm login
    fun checkEmail(email: String) {
        // Reset state
        _checkEmailState.value = CheckEmailState.Loading

        viewModelScope.launch {
            try {
                val response = repository.checkEmail(email)
                _checkEmailState.value = CheckEmailState.Success( response.exists, response.message)
            } catch (e: Exception) {
                // Bắt lỗi (VD: 401, Network error, v.v.)
                Log.e("PasswordRecoveryViewModel", "Login error: ${e.message}")
                _checkEmailState.value = CheckEmailState.Error(false,e.message ?: "Đăng nhập thất bại!")
            }
        }
    }


}