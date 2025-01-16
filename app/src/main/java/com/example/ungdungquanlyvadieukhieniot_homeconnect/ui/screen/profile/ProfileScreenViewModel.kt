package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.profile

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.MainActivity
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.User
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.UserRequest
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.UserResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.AuthRepository
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ProfileState {
    object Idle : ProfileState()
    object Loading : ProfileState()
    data class Success(val success: Boolean, val message: String) : ProfileState()
    data class Error(val success: Boolean, val message: String) : ProfileState()
}

sealed class InfoProfileState{
    object Idle :  InfoProfileState()              // Chưa làm gì
    object Loading :  InfoProfileState()            // Đang loading
    data class Success(val user: User) : InfoProfileState()
    data class Error(val error: String) : InfoProfileState()
}

sealed class PutInfoProfileState{
    object Idle :  PutInfoProfileState()              // Chưa làm gì
    object Loading :  PutInfoProfileState()            // Đang loading
    data class Success(val userResponse: UserResponse) : PutInfoProfileState()
    data class Error(val error: String) : PutInfoProfileState()
}

class ProfileScreenViewModel(application: Application, context: Context) :
    AndroidViewModel(application) {
    private val repository = UserRepository(context)
    private val repository2 = AuthRepository(context)


    //Xóa token để logout
    fun logout(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("jwt_token")
        editor.apply()
    }

    //Hàm logout và tạo Activity mới, chuyển về trang Welcome
    fun logoutAndNavigateToLogin(context: Context) {
        logout(context)
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

    private val _infoProfileState = MutableStateFlow<InfoProfileState>(InfoProfileState.Idle)
    val infoProfileState = _infoProfileState.asStateFlow()

    /**
     * Lấy thông tin profile
     */
    fun getInfoProfile() {
        viewModelScope.launch {
            try {
                _infoProfileState.value = InfoProfileState.Loading
                val response = repository2.getInfoProfile()
                _infoProfileState.value = InfoProfileState.Success(response)
            } catch (e: Exception) {
                Log.e("DeviceViewModel", "Error fetching devices: ${e.message}")
                _infoProfileState.value = InfoProfileState.Error(e.message ?: "Danh sach load thất bại!")
            }
        }
    }

    private val _putInfoProfileState = MutableStateFlow<PutInfoProfileState>(PutInfoProfileState.Idle)
    val putInfoProfileState = _putInfoProfileState.asStateFlow()

    fun putInfoProfile(userId: Int, user: UserRequest) {
        viewModelScope.launch {
            try {
                _putInfoProfileState.value = PutInfoProfileState.Loading
                val response = repository.putInfoProfile(userId, user)
                _putInfoProfileState.value = PutInfoProfileState.Success(response)
                Log.d("putInfoProfile", "Thông tin đã được cập nhật thành công.")
            } catch (e: Exception) {
                Log.e("putInfoProfile", "Lỗi khi cập nhật thông tin: ${e.message}")
                _putInfoProfileState.value = PutInfoProfileState.Error(e.message ?: "Cập nhật thông tin thất bại!")
            }
        }
    }
}