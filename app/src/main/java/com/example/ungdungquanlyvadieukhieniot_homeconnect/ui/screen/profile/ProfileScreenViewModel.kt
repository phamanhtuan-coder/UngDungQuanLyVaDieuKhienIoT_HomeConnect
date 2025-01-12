package com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.screen.profile

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import com.example.ungdungquanlyvadieukhieniot_homeconnect.MainActivity
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.UserRepository

sealed class ProfileState {
    object Idle : ProfileState()
    object Loading : ProfileState()
    data class Success(val success: Boolean, val message: String) : ProfileState()
    data class Error(val success: Boolean, val message: String) : ProfileState()
}

class ProfileScreenViewModel(application: Application, context: Context) :
    AndroidViewModel(application) {
    private val repository = UserRepository(context)


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


}