package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.device_sharing_list

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SharedUser
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.SharedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DeviceSharingState {
    object Idle : DeviceSharingState()// Chưa làm gì
    object Loading : DeviceSharingState()            // Đang loading
    data class Success(val sharedUsers: List<SharedUser>) : DeviceSharingState()
    data class Error(val error: String) : DeviceSharingState()
}

sealed class DeviceSharingActionState {
    object Idle : DeviceSharingActionState()               // Chưa làm gì
    object Loading : DeviceSharingActionState()            // Đang loading
    data class Success(val success: String) : DeviceSharingActionState()
    data class Error(val error: String) : DeviceSharingActionState()
}

class DeviceSharingViewModel(application: Application, context: Context) :
    AndroidViewModel(application) {
    private val repository = SharedRepository(context)
    private val _sharedUsersState = MutableStateFlow<DeviceSharingState>(DeviceSharingState.Idle)
    val sharedUsersState = _sharedUsersState.asStateFlow()

    fun getSharedUsers(DeviceID: Int) {
        viewModelScope.launch {
            try {
                _sharedUsersState.value = DeviceSharingState.Loading
                val response = repository.getSharedUsers(DeviceID)
                _sharedUsersState.value = DeviceSharingState.Success(response)
            } catch (e: Exception) {
                _sharedUsersState.value =
                    DeviceSharingState.Error(e.message ?: "Lỗi load danh sách người dùng chia sẻ!")
            }
        }
    }

    internal val _addSharedUserState =
        MutableStateFlow<DeviceSharingActionState>(DeviceSharingActionState.Idle)
    val addSharedUserState = _addSharedUserState.asStateFlow()

    fun addSharedUser(DeviceID: Int, SharedWithUser: String) {
        viewModelScope.launch {
            try {
                _addSharedUserState.value = DeviceSharingActionState.Loading
                val response = repository.addSharedUser(DeviceID, SharedWithUser)
                _addSharedUserState.value =
                    DeviceSharingActionState.Success("Thêm người dùng chia sẻ thành công!")
            } catch (e: Exception) {
                _addSharedUserState.value =
                    DeviceSharingActionState.Error(e.message ?: "Lỗi thêm người dùng chia sẻ!")
            }
        }
    }

    private val _revokePermissionState =
        MutableStateFlow<DeviceSharingActionState>(DeviceSharingActionState.Idle)
    val revokePermissionState = _revokePermissionState.asStateFlow()

    fun revokePermission(PermissionID: Int) {
        viewModelScope.launch {
            try {
                _revokePermissionState.value = DeviceSharingActionState.Loading
                val response = repository.revokePermission(PermissionID)
                _revokePermissionState.value =
                    DeviceSharingActionState.Success("Thu hồi quyền chia sẻ thành công!")
            } catch (e: Exception) {
                _revokePermissionState.value =
                    DeviceSharingActionState.Error(e.message ?: "Lỗi thu hồi quyền chia sẻ!")
            }
        }
    }

}