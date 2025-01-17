package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.otp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.EmailResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.OTPRepository
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class OTPState {
    object Idle : OTPState()               // Chưa làm gì
    object Loading : OTPState()            // Đang loading
    data class Success(val success: Boolean, val exists: Boolean?, val message: String) : OTPState()
    data class Error(val success: Boolean, val message: String) : OTPState()
}

sealed class VerifyEmailState {
    object Idle : VerifyEmailState()               // Chưa làm gì
    object Loading : VerifyEmailState()            // Đang loading
    data class Success(val emailResponse: EmailResponse) : VerifyEmailState()
    data class Error(val error: String) : VerifyEmailState()
}


class OTPViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = OTPRepository()

    // StateFlow cho UI lắng nghe

    private val _verifyOtpState = MutableStateFlow<OTPState>(OTPState.Idle)
    val verifyOtpState = _verifyOtpState.asStateFlow()
    fun verifyOTP(email: String, otp: String) {
        // Reset state
        _verifyOtpState.value = OTPState.Loading
        viewModelScope.launch {
            try {
                val response = repository.verifyOTP(email, otp)
                // Bắn state về UI
                _verifyOtpState.value = OTPState.Success(response.success, true, response.message)
            } catch (e: Exception) {
                // Bắt lỗi (VD: 401, Network error, v.v.)
                Log.e("OTPViewModel", "OTP verify error: ${e.message}")
                _verifyOtpState.value = OTPState.Error(false, e.message ?: "Xác thực OTP thất bại!")
            }
        }
    }


    private val _sendOtpState = MutableStateFlow<OTPState>(OTPState.Idle)
    val sendOtpState = _sendOtpState.asStateFlow()

    fun sendOTP(email: String) {
        // Reset state
        _sendOtpState.value = OTPState.Loading
        viewModelScope.launch {
            try {
                val response = repository.sendOTP(email)
                // Bắn state về UI
                _sendOtpState.value = OTPState.Success(response.success, true, response.message)
            } catch (e: Exception) {
                // Bắt lỗi (VD: 401, Network error, v.v.)
                Log.e("OTPViewModel", "OTP send error: ${e.message}")
                _sendOtpState.value = OTPState.Error(false, e.message ?: "Gửi OTP thất bại!")
            }
        }
    }

    private val userRepository = UserRepository(application)
    private val _verifyEmailState = MutableStateFlow<VerifyEmailState>(VerifyEmailState.Idle)
    val verifyEmailState = _verifyEmailState.asStateFlow()

    fun confirmEmail(email: String) {
        // Reset state
        _verifyEmailState.value = VerifyEmailState.Loading
        viewModelScope.launch {
            try {
                val response = userRepository.confirmEmail(email)
                // Bắn state về UI
                _verifyEmailState.value = VerifyEmailState.Success(response)
            } catch (e: Exception) {
                // Bắt lỗi (VD: 401, Network error, v.v.)
                Log.e("OTPViewModel", "OTP send error: ${e.message}")
                _verifyEmailState.value =
                    VerifyEmailState.Error(e.message ?: "Xác thực email thất bại!")
            }
        }
    }
}