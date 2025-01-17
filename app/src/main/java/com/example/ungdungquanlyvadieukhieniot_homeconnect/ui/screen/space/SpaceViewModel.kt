package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.space

import Space2
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.CreatedSpace
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.SpaceResponse2
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.SpaceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class SpaceState {
    object Idle : SpaceState()
    object Loading : SpaceState()
    data class Success(val spaces: List<SpaceResponse2>) : SpaceState()
    data class UpdateSuccess(val message: String, val updatedSpace: Space2) : SpaceState()
    data class CreateSuccess(val message: String, val createdSpace: CreatedSpace) : SpaceState()
    data class Error(val error: String) : SpaceState()
}


class SpaceViewModel (private val repository: SpaceRepository) : ViewModel() {

    private val _spaceState = MutableStateFlow<SpaceState>(SpaceState.Idle)
    val spaceState = _spaceState.asStateFlow()

    fun getSpaces(houseId: Int) {
        _spaceState.value = SpaceState.Loading
        viewModelScope.launch {
            try {
                Log.d("SpaceViewModel", "Bắt đầu lấy dữ liệu Spaces cho HouseID: $houseId")
                val spaces = repository.getSpaces(houseId)
                _spaceState.value = SpaceState.Success(spaces)
                Log.d("SpaceViewModel", "Lấy thành công Spaces: $spaces")
            } catch (e: Exception) {
                _spaceState.value = SpaceState.Error(e.message ?: "Đã xảy ra lỗi")
                Log.e("SpaceViewModel", "Lỗi khi lấy Spaces: ${e.message}")
            }
        }
    }

    // Cập nhật Space
    fun updateSpace(spaceId: Int, name: String) {
        _spaceState.value = SpaceState.Loading
        viewModelScope.launch {
            try {
                val response = repository.updateSpace(spaceId, name)
                _spaceState.value = SpaceState.UpdateSuccess(
                    message = response.message,
                    updatedSpace = response.space
                )
                Log.d("SpaceViewModel", "Cập nhật Space thành công: ${response.space}")
            } catch (e: Exception) {
                _spaceState.value = SpaceState.Error(e.message ?: "Đã xảy ra lỗi khi cập nhật Space")
                Log.e("SpaceViewModel", "Lỗi khi cập nhật Space: ${e.message}")
            }
        }
    }

    fun createSpace(houseId: Int, name: String) {
        _spaceState.value = SpaceState.Loading
        viewModelScope.launch {
            try {
                val response = repository.createSpace(houseId, name)
                _spaceState.value = SpaceState.CreateSuccess(
                    message = response.message,
                    createdSpace = response.space
                )
                Log.d("SpaceViewModel", "Tạo Space thành công: ${response.space}")
            } catch (e: Exception) {
                _spaceState.value = SpaceState.Error(e.message ?: "Đã xảy ra lỗi khi tạo Space")
                Log.e("SpaceViewModel", "Lỗi khi tạo Space: ${e.message}")
            }
        }
    }
}