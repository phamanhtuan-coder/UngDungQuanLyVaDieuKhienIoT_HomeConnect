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

    // Navigation Controller
    private lateinit var navController: NavHostController

    // Permission Launchers
    private lateinit var requestNotificationPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var requestMediaPermissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var requestLocationWifiPermissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Permission Launchers
        initializePermissionLaunchers()

        // Setup Notification Channel
        setupNotificationChannel()

        // Setup UI Components
        setupUI()

        // Handle Permissions
        handleMediaPermissions()
        handleLocationWifiPermissions()

        // Request Notification Permission
        requestNotificationPermission()
    }

    override fun onResume() {
        super.onResume()
        if (areMediaPermissionsGranted()) {
            accessMedia()
        }
        if (areLocationWifiPermissionsGranted()) {
            accessLocationWifi()
        }
    }

    /**
     * Initializes all permission request launchers.
     */
    private fun initializePermissionLaunchers() {
        // Launcher for Media Permissions
        requestMediaPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            handleMediaPermissionResult(permissions)
        }

        // Launcher for Location and Wi-Fi Permissions
        requestLocationWifiPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            handleLocationWifiPermissionResult(permissions)
        }

        // Launcher for Notification Permission
        requestNotificationPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                Log.i("MainActivity", "Notification permission granted.")
            } else {
                Log.e("MainActivity", "Notification permission denied.")
            }
        }
    }

    /**
     * Sets up the notification channel for warnings.
     */
    @SuppressLint("ObsoleteSdkInt")
    private fun setupNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val soundUri: Uri = Uri.parse("android.resource://${packageName}/raw/alert")
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()

            val channelId = "homeconnect_warning"
            val channelName = "Cảnh báo"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = "Kênh thông báo cảnh báo nguy hiểm/khẩn cấp"
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
    }

    /**
     * Sets up the UI components including splash screen and navigation graph.
     */
    private fun setupUI() {
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            AppTheme {
                navController = rememberNavController()
                NavigationGraph(navController)
            }
        }
    }

    /**
     * Handles the media permissions by checking, requesting, and processing the result.
     */
    private fun handleMediaPermissions() {
        if (areMediaPermissionsGranted()) {
            accessMedia()
        } else {
            if (shouldShowMediaRationale()) {
                showMediaPermissionRationaleDialog()
            } else {
                requestMediaPermissions()
            }
        }
    }

    /**
     * Handles the location and Wi-Fi permissions by checking, requesting, and processing the result.
     */
    private fun handleLocationWifiPermissions() {
        if (areLocationWifiPermissionsGranted()) {
            accessLocationWifi()
        } else {
            if (shouldShowLocationWifiRationale()) {
                showLocationWifiPermissionRationaleDialog()
            } else {
                requestLocationWifiPermissions()
            }
        }
    }

    /**
     * Checks if the necessary media permissions are granted.
     */
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

    /**
     * Requests the necessary media permissions based on Android version.
     */
    private fun requestMediaPermissions() {
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

        requestMediaPermissionLauncher.launch(permissions)
    }

    /**
     * Handles the result of media permission requests.
     */
    private fun handleMediaPermissionResult(permissions: Map<String, Boolean>) {
        val isGranted = when {
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

        if (isGranted) {
            accessMedia()
        } else {
            if (shouldShowMediaRationale()) {
                showMediaPermissionRationaleDialog()
            } else {
                showSettingsRedirectDialog("media")
            }
        }
    }

    /**
     * Determines whether to show a rationale for the media permissions.
     */
    private fun shouldShowMediaRationale(): Boolean {
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
     * Shows a dialog explaining why media permissions are needed.
     */
    private fun showMediaPermissionRationaleDialog() {
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
     * Checks if the necessary location and Wi-Fi permissions are granted.
     */
    private fun areLocationWifiPermissionsGranted(): Boolean {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE
        )

        return permissions.all {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    /**
     * Requests the necessary location and Wi-Fi permissions.
     */
    private fun requestLocationWifiPermissions() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE
        )

        requestLocationWifiPermissionLauncher.launch(permissions)
    }

    /**
     * Handles the result of location and Wi-Fi permission requests.
     */
    private fun handleLocationWifiPermissionResult(permissions: Map<String, Boolean>) {
        val isGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
                permissions[Manifest.permission.ACCESS_WIFI_STATE] == true

        if (isGranted) {
            accessLocationWifi()
        } else {
            if (shouldShowLocationWifiRationale()) {
                showLocationWifiPermissionRationaleDialog()
            } else {
                showSettingsRedirectDialog("location_wifi")
            }
        }
    }

    /**
     * Determines whether to show a rationale for the location and Wi-Fi permissions.
     */
    private fun shouldShowLocationWifiRationale(): Boolean {
        return shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) ||
                shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_WIFI_STATE)
    }

    /**
     * Shows a dialog explaining why location and Wi-Fi permissions are needed.
     */
    private fun showLocationWifiPermissionRationaleDialog() {
        AlertDialog.Builder(this)
            .setTitle("Yêu cầu quyền truy cập")
            .setMessage("Ứng dụng cần quyền truy cập vị trí và trạng thái Wi-Fi để hoạt động chính xác. Vui lòng cấp quyền.")
            .setPositiveButton("Cấp quyền") { _, _ ->
                requestLocationWifiPermissions()
            }
            .setNegativeButton("Hủy") { _, _ ->
                Toast.makeText(this, "Quyền bị từ chối. Không thể tiếp tục.", Toast.LENGTH_SHORT).show()
            }
            .setCancelable(false)
            .show()
    }

    /**
     * Shows a dialog directing the user to app settings to manually grant permissions.
     *
     * @param permissionGroup The group of permissions that were denied ("media" or "location_wifi").
     */
    private fun showSettingsRedirectDialog(permissionGroup: String) {
        val title = when (permissionGroup) {
            "media" -> "Quyền truy cập phương tiện bị từ chối vĩnh viễn"
            "location_wifi" -> "Quyền vị trí/Wi-Fi bị từ chối vĩnh viễn"
            else -> "Quyền bị từ chối"
        }

        val message = when (permissionGroup) {
            "media" -> "Bạn đã từ chối quyền truy cập phương tiện. Hãy bật quyền trong cài đặt ứng dụng để tiếp tục."
            "location_wifi" -> "Bạn đã từ chối quyền vị trí và Wi-Fi. Hãy bật quyền trong cài đặt ứng dụng để tiếp tục."
            else -> "Bạn đã từ chối quyền cần thiết. Hãy bật quyền trong cài đặt ứng dụng để tiếp tục."
        }

        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
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
     * Handles actions after media permissions are granted.
     */
    private fun accessMedia() {
        Toast.makeText(this, "Đã cấp quyền truy cập hình ảnh!", Toast.LENGTH_SHORT).show()
        // Add additional logic to access media as needed
    }

    /**
     * Handles actions after location and Wi-Fi permissions are granted.
     */
    private fun accessLocationWifi() {
        Toast.makeText(this, "Đã cấp quyền truy cập vị trí và Wi-Fi!", Toast.LENGTH_SHORT).show()
        // Add additional logic to access location and Wi-Fi as needed
    }

    /**
     * Requests the POST_NOTIFICATIONS permission if necessary.
     */
    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                // Request notification permission
                requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}