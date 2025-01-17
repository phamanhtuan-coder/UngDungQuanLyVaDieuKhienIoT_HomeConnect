package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.component

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SpaceDetailResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.SpaceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class SpaceState {
    object Idle : SpaceState()              // Chưa làm gì
    object Loading : SpaceState()            // Đang loading
    data class Success(val space: SpaceDetailResponse) : SpaceState()
    data class Error(val error: String) : SpaceState()
}

class SquareSpaceCardViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = SpaceRepository(application.applicationContext)

    private val _spaceDetailState = MutableStateFlow<SpaceState>(SpaceState.Idle)
    val spaceDetailState = _spaceDetailState.asStateFlow()

    /**
     * Lấy thông tin chi tiết phòng
     */
    fun getSpaceDetail(id: Int) {
        viewModelScope.launch {
            try {
                _spaceDetailState.value = SpaceState.Loading
                val response = repository.getSpaceDetail(id)
                _spaceDetailState.value = SpaceState.Success(space = response)
            } catch (e: Exception) {
                Log.e("DeviceViewModel", "Error fetching spaces: ${e.message}")
                _spaceDetailState.value = SpaceState.Error(e.message ?: "Danh sach load thất bại!")
            }
        }
    }
}