package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class WeeklyAverageSensorResponse(
    @SerializedName("weeklyAverage") val weeklyAverage: String, // Nhận dưới dạng chuỗi
    @SerializedName("date") val date: String
) {
    // Hàm để phân tích chuỗi JSON thành đối tượng
    fun getWeeklyAverageData(): WeeklyAverageData {
        return Gson().fromJson(weeklyAverage, WeeklyAverageData::class.java)
    }
}

data class WeeklyAverageData(
    @SerializedName("weeklyAverageGas") val weeklyAverageGas: Float,
    @SerializedName("weeklyAverageTemperature") val weeklyAverageTemperature: Float,
    @SerializedName("weeklyAverageHumidity") val weeklyAverageHumidity: Float
)
