package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.login

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.AuthRepository
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.UserRepository
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class LoginUiState {
    object Idle : LoginUiState()               // Chưa làm gì
    object Loading : LoginUiState()            // Đang loading
    data class Success(val token: String) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AuthRepository()
    private val userRepository = UserRepository(context = application)

    // StateFlow cho UI lắng nghe
    private val _loginState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val loginState = _loginState.asStateFlow()

    // Hàm login
    fun login(email: String, password: String) {
        // Reset state
        _loginState.value = LoginUiState.Loading

        viewModelScope.launch {
            try {
                val response = repository.login(email, password)
                // Lưu token
                saveToken(response.token)
                sendFcmTokenToServer()
                // Bắn state về UI
                _loginState.value = LoginUiState.Success(response.token)
            } catch (e: Exception) {
                // Bắt lỗi (VD: 401, Network error, v.v.)
                Log.e("LoginViewModel", "Login error: ${e.message}")
                _loginState.value = LoginUiState.Error(e.message ?: "Đăng nhập thất bại!")
            }
        }
    }

    // Lưu token vào SharedPreferences
    private fun saveToken(token: String) {
        val sharedPrefs = getApplication<Application>()
            .getSharedPreferences("MY_APP_PREFS", Context.MODE_PRIVATE)

        sharedPrefs.edit().putString("JWT_TOKEN", token).apply()
    }

    // Hàm để gửi FCM token tới server
    private fun sendFcmTokenToServer() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("LoginViewModel", "Fetching FCM token failed", task.exception)
                return@addOnCompleteListener
            }

            // Get new FCM registration token
            val fcmToken = task.result
            Log.d("LoginViewModel", "FCM Token: $fcmToken")

            // Gửi FCM token tới server
            viewModelScope.launch {
                try {
                    userRepository.sendToken(fcmToken)
                    Log.d("LoginViewModel", "FCM Token sent to server successfully.")
                } catch (e: Exception) {
                    Log.e("LoginViewModel", "Failed to send FCM token: ${e.message}")
                }
            }
        }
    }
}