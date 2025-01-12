package com.example.ungdungquanlyvadieukhieniot_homeconnect.service.notification

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.ungdungquanlyvadieukhieniot_homeconnect.R
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.UserRepository
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
        prefs.edit().putString("FCM_TOKEN", token).apply()
        // Gửi token này lên server của bạn
        sendTokenToServer(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        // Xử lý thông báo nhận được
        handleNotification(remoteMessage)
    }

    private fun sendTokenToServer(token: String) {
        val repository = UserRepository(this)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.sendToken(token)
                withContext(Dispatchers.Main) {
                    Log.i("MyFirebaseMessagingService", response.message)
                }
            } catch (e: Exception) {
                Log.e("MyFirebaseMessagingService", "sendTokenToServer error: ${e.message}")
            }
        }
    }

    private fun handleNotification(remoteMessage: RemoteMessage) {
        remoteMessage.notification?.let {
            // Kiểm tra quyền gửi thông báo trước khi hiển thị
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                showAlert(it.title, it.body)
            } else {
                Log.w("MyFirebaseMessaging", "⚠️ Chưa cấp quyền gửi thông báo.")
            }
        }
    }

    private fun showAlert(title: String?, body: String?) {
        val soundUri: Uri =
            Uri.parse("android.resource://com.example.ungdungquanlyvadieukhieniot_homeconnect/raw/alert")
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.alert)
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // Tạo thông báo với hình ảnh
        val notificationBuilder = NotificationCompat.Builder(this, "homeconnect_warning")
            .setContentTitle("Tiêu đề thông báo")
            .setContentText("Nội dung thông báo")
            .setSmallIcon(R.drawable.alert)
            .setLargeIcon(bitmap)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(bitmap)
            )
            .setSound(soundUri)
            .setAutoCancel(true)


        notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder.build())
    }
}