package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.otp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.OTPRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class OTPState {
    object Idle : OTPState()               // Chưa làm gì
    object Loading : OTPState()            // Đang loading
    data class Success(val success: Boolean, val exists: Boolean?, val message: String) : OTPState()
    data class Error(val success: Boolean, val message: String) : OTPState()
}

class OTPViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = OTPRepository()

    // StateFlow cho UI lắng nghe
    private val _otpState = MutableStateFlow<OTPState>(OTPState.Idle)
    val otpState = _otpState.asStateFlow()

    // Hàm login
    fun checkEmail(email: String) {
        // Reset state
        _otpState.value = OTPState.Loading

        viewModelScope.launch {
            try {
                val response = repository.checkEmail(email)

                // Bắn state về UI

            } catch (e: Exception) {
                // Bắt lỗi (VD: 401, Network error, v.v.)
                Log.e("LoginViewModel", "Login error: ${e.message}")
                _otpState.value = OTPState.Error(false, e.message ?: "Đăng nhập thất bại!")
            }
        }
    }
}