package com.example.ungdungquanlyvadieukhieniot_homeconnect

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.navigation.NavigationGraph
import com.example.ungdungquanlyvadieukhieniot_homeconnect.ui.theme.AppTheme


class MainActivity : ComponentActivity() {
    override fun onResume() {
        super.onResume()
        if (areMediaPermissionsGranted()) {
            accessMedia()
        }
    }

    // Launcher cho việc yêu cầu quyền POST_NOTIFICATIONS
    private lateinit var requestNotificationPermissionLauncher: ActivityResultLauncher<String>

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Khai báo biến navController
        lateinit var navController: NavHostController

        // Đăng ký trình khởi chạy để yêu cầu nhiều quyền cùng một lúc
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions() // Sử dụng contract yêu cầu nhiều quyền
        ) { permissions ->
            // Kiểm tra trạng thái cấp quyền cho từng quyền trong danh sách
            val granted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
                    permissions[Manifest.permission.ACCESS_WIFI_STATE] == true

            // Nếu không được cấp đủ quyền, thông báo cho người dùng
            if (!granted) {
                println("Quyền cần thiết không được cấp.") // In ra thông báo khi quyền bị từ chối
            }
        }

        // Khởi chạy yêu cầu cấp quyền cho các quyền cần thiết
        requestPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION, // Quyền truy cập vị trí chính xác
                Manifest.permission.ACCESS_WIFI_STATE     // Quyền truy cập trạng thái Wi-Fi
            )
        )

        // Tạo kênh thông báo Warning
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val soundUri: Uri =
                Uri.parse("android.resource://com.example.ungdungquanlyvadieukhieniot_homeconnect/raw/alert")
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.alert)
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()

            val channelId = "homeconnect_warning"
            val channelName = "Cảnh báo"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = "Kênh thông báo cảnh báo nguy hiểm/ khẩn cấp"
                setSound(soundUri, audioAttributes)
                enableLights(true)
                enableVibration(true)
                setShowBadge(true)
                lockscreenVisibility = 1
                setBypassDnd(true)


            }
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }


        // Khởi tạo launcher để yêu cầu quyền POST_NOTIFICATIONS
        requestNotificationPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                Log.i("MainActivity", "Quyền gửi thông báo đã được cấp.")
            } else {
                Log.e("MainActivity", "Quyền gửi thông báo bị từ chối.")
            }
        }



        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            AppTheme {
                navController = rememberNavController()
                NavigationGraph(navController)
            }
        }

        // Kiểm tra và xử lý quyền truy cập hình ảnh
        if (areMediaPermissionsGranted()) {
            // Quyền đã được cấp, tiếp tục truy cập phương tiện
            accessMedia()
        } else {
            // Yêu cầu quyền truy cập nếu chưa được cấp
            if (shouldShowRationale()) {
                // Hiển thị lý do yêu cầu quyền nếu có thể
                showPermissionRationaleDialog()
            } else {
                // Yêu cầu cấp quyền
                requestMediaPermissions()
            }
        }

        // Yêu cầu quyền gửi thông báo
        requestNotificationPermission()


    }

    private fun areMediaPermissionsGranted(): Boolean {
        val permissions = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            else -> {
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            }
        }

        return permissions.all {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }
    }




    private fun requestMediaPermissions() {
        val permissions = when {
            // Quyền truy cập hình ảnh cho Android 13+
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
            }
            // Quyền truy cập file cho Android 10+
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            // Quyền truy cập file cho Android 9 và thấp hơn
            else -> {
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            }
        }

        // Kích hoạt launcher yêu cầu quyền
        requestPermissionLauncher.launch(permissions)
    }

    /**
     * Launcher để xử lý kết quả sau khi yêu cầu quyền.
     */
    private val requestPermissionLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            // Kiểm tra quyền có được cấp hay không
            val isImagePermissionGranted = when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                    permissions[Manifest.permission.READ_MEDIA_IMAGES] == true
                }
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                    permissions[Manifest.permission.READ_EXTERNAL_STORAGE] == true
                }
                else -> {
                    permissions[Manifest.permission.READ_EXTERNAL_STORAGE] == true &&
                            permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true
                }
            }

            // Nếu quyền đã được cấp
            if (isImagePermissionGranted) {
                accessMedia()
            } else {
                // Nếu người dùng từ chối cấp quyền
                if (shouldShowRationale()) {
                    showPermissionRationaleDialog()
                } else {
                    showSettingsRedirectDialog()
                }
            }
        }

    /**
     * Phương thức kiểm tra xem có cần hiển thị lý do yêu cầu quyền không.
     * @return true nếu cần hiển thị lý do, false nếu không.
     */
    private fun shouldShowRationale(): Boolean {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_IMAGES)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            else -> {
                shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) ||
                        shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
    }

    /**
     * Hiển thị dialog lý do yêu cầu quyền nếu người dùng từ chối.
     */
    private fun showPermissionRationaleDialog() {
        AlertDialog.Builder(this)
            .setTitle("Yêu cầu quyền truy cập")
            .setMessage("Ứng dụng cần quyền truy cập vào ảnh và tệp để hoạt động chính xác. Vui lòng cấp quyền.")
            .setPositiveButton("Cấp quyền") { _, _ ->
                requestMediaPermissions()
            }
            .setNegativeButton("Hủy") { _, _ ->
                Toast.makeText(this, "Quyền bị từ chối. Không thể tiếp tục.", Toast.LENGTH_SHORT).show()
            }
            .setCancelable(false)
            .show()
    }

    /**
     * Hiển thị dialog hướng dẫn mở cài đặt nếu quyền bị từ chối vĩnh viễn.
     */
    private fun showSettingsRedirectDialog() {
        AlertDialog.Builder(this)
            .setTitle("Quyền bị từ chối vĩnh viễn")
            .setMessage("Bạn đã từ chối quyền cần thiết. Hãy bật quyền trong cài đặt ứng dụng để tiếp tục.")
            .setPositiveButton("Mở cài đặt") { _, _ ->
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", packageName, null)
                )
                startActivity(intent)
            }
            .setNegativeButton("Hủy") { _, _ ->
                Toast.makeText(this, "Quyền bị từ chối. Không thể tiếp tục.", Toast.LENGTH_SHORT).show()
            }
            .setCancelable(false)
            .show()
    }

    /**
     * Phương thức xử lý logic sau khi quyền được cấp thành công.
     */
    private fun accessMedia() {
        Toast.makeText(this, "Đã cấp quyền truy cập hình ảnh!", Toast.LENGTH_SHORT).show()
    }

    /**
     * Yêu cầu quyền gửi thông báo.
     */
    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                // Xin quyền thông báo
                requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

}