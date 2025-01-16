package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.update_password

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.AndroidViewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.ChangePasswordRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.AuthRepository
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class UpdatePasswordState {
    object Idle : UpdatePasswordState()               // Chưa làm gì
    object Loading : UpdatePasswordState()            // Đang loading
    data class Success(val message: String) : UpdatePasswordState()
    data class Error(val error: String) : UpdatePasswordState()
}

class UpdatePasswordViewModel(application: Application, context: Context) : AndroidViewModel(application) {
    private val repository = UserRepository(context)

    // StateFlow cho UI lắng nghe
    private val _updatePasswordState = MutableStateFlow<UpdatePasswordState>(UpdatePasswordState.Idle)
    val updatePasswordState = _updatePasswordState.asStateFlow()

    // Hàm login
    fun updatePassword(userId: Int, changePasswordRequest: ChangePasswordRequest) {
        // Reset state
        _updatePasswordState.value = UpdatePasswordState.Loading

        viewModelScope.launch {
            try {
                val response = repository.putChangePassword(userId, changePasswordRequest)
                _updatePasswordState.value = UpdatePasswordState.Success(response.message)
            } catch (e: Exception) {
                // Bắt lỗi (VD: 401, Network error, v.v.)
                Log.e("LoginViewModel", "Login error: ${e.message}")
                _updatePasswordState.value = UpdatePasswordState.Error(e.message ?: "Đăng nhập thất bại!")
            }
        }
    }
}