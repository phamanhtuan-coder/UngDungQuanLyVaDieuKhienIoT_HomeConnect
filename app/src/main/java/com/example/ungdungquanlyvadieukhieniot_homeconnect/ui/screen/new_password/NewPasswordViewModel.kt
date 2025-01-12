package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.new_password

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class NewPassWordState {
    object Idle : NewPassWordState()               // Chưa làm gì
    object Loading : NewPassWordState()            // Đang loading
    data class Success(val success: Boolean, val exists: Boolean?, val message: String) :
        NewPassWordState()

    data class Error(val success: Boolean, val message: String) : NewPassWordState()
}

class NewPasswordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AuthRepository()

    // StateFlow cho UI lắng nghe

    private val _newPasswordState = MutableStateFlow<NewPassWordState>(NewPassWordState.Idle)
    val newPasswordState = _newPasswordState.asStateFlow()

    fun newPassword(email: String, password: String) {
        // Reset state
        _newPasswordState.value = NewPassWordState.Loading
        viewModelScope.launch {
            try {
//                val response = repository.newPassword(email,password)
//                // Bắn state về UI
//                _newPasswordState.value = NewPassWordState.Success(response.success, true, response.message)
            } catch (e: Exception) {
                // Bắt lỗi (VD: 401, Network error, v.v.)
                Log.e("NewPasswordViewModel", "NewPassword error: ${e.message}")
                _newPasswordState.value =
                    NewPassWordState.Error(false, e.message ?: "Tạo mật khẩu mơí thất bại!")
            }
        }
    }
}