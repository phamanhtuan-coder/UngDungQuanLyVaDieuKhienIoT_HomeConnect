package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.signup

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.RegisterRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.User
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.AuthRepository
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.login.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class SignUpState {
    object Idle : SignUpState()               // Chưa làm gì
    object Loading : SignUpState()            // Đang loading
    data class Success(val message: String,val user: RegisterRequest) : SignUpState()
    data class Error(val error: String) : SignUpState()
}

class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AuthRepository()

    // StateFlow cho UI lắng nghe
    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState = _signUpState.asStateFlow()

    // Hàm login
    fun signUp(user: RegisterRequest) {
        // Reset state
        _signUpState.value = SignUpState.Loading

        viewModelScope.launch {
            try {
                val response = repository.register(user)
                Log.e("User",response.user.toString())
                Log.e("message",response.message)
                _signUpState.value = SignUpState.Success(response.message,response.user)
            } catch (e: Exception) {
                // Bắt lỗi (VD: 401, Network error, v.v.)
                Log.e("LoginViewModel", "Login error: ${e.message}")
                _signUpState.value = SignUpState.Error(e.message ?: "Đăng nhập thất bại!")
            }
        }
    }
}