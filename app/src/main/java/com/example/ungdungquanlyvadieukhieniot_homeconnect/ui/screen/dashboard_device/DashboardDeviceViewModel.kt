import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DailyAverageSensorResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.DailyPowerUsageResponse
import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository.StatisticsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// State class để biểu diễn các trạng thái của dữ liệu
sealed class StatisticsState {
    object Loading : StatisticsState()
    data class DailyAveragesSuccess(val data: DailyAverageSensorResponse) : StatisticsState()
    data class DailyPowerUsageSuccess(val data: DailyPowerUsageResponse) : StatisticsState()
    data class Error(val message: String) : StatisticsState()
    object Idle : StatisticsState()
}

class DashboardViewModel(
    application: Application,
    private val context: Context
) : ViewModel() {

    // Repository để tương tác với dữ liệu từ API/DB
    private val repository = StatisticsRepository(context)

    // StateFlow để quản lý trạng thái dữ liệu của màn hình Dashboard
    private val _statisticsState = MutableStateFlow<StatisticsState>(StatisticsState.Idle)
    val statisticsState: StateFlow<StatisticsState> get() = _statisticsState

    // Hàm lấy thống kê trung bình cảm biến theo khoảng thời gian
    fun getDailyAveragesSensor(deviceId: Int, startDate: String, endDate: String) {
        viewModelScope.launch {
            try {
                // Đặt trạng thái là Loading
                _statisticsState.value = StatisticsState.Loading

                // Gọi repository để lấy dữ liệu từ API hoặc DB
                val response: DailyAverageSensorResponse = repository.getDailyAveragesSensor(deviceId, startDate, endDate)

                // Cập nhật trạng thái thành công
                _statisticsState.value = StatisticsState.DailyAveragesSuccess(response)
            } catch (e: Exception) {
                // Xử lý lỗi và cập nhật trạng thái thất bại
                _statisticsState.value = StatisticsState.Error(e.message ?: "Unknown error")
            }
        }
    }

    // Hàm lấy dữ liệu tiêu thụ điện theo ngày
    fun getDailyPowerUsages(deviceId: Int, startDate: String, endDate: String) {
        viewModelScope.launch {
            try {
                Log.d("DEBUG", "Fetching Power Usage: deviceId=$deviceId, start=$startDate, end=$endDate")
                // Đặt trạng thái là Loading
                _statisticsState.value = StatisticsState.Loading

                // Gọi repository để lấy dữ liệu từ API hoặc DB
                val response: DailyPowerUsageResponse = repository.getDailyPowerUsages(deviceId, startDate, endDate)

                Log.d("DEBUG", "Fetched Power Usage successfully: $response")
                // Cập nhật trạng thái thành công
                _statisticsState.value = StatisticsState.DailyPowerUsageSuccess(response)
            } catch (e: Exception) {
                // Xử lý lỗi và cập nhật trạng thái thất bại
                Log.e("DEBUG", "Error fetching Power Usage: ${e.message}")
                _statisticsState.value = StatisticsState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
