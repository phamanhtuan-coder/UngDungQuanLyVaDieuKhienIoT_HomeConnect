package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.weekly

data class WeeklyAverageSensorResponse(
    val weeklyAverage: String, // JSON string
    val date: String
)
{
    fun getWeeklyAverageData(): WeeklyAverageData {
        // parse JSON => WeeklyAverageData
        return com.google.gson.Gson().fromJson(weeklyAverage, WeeklyAverageData::class.java)
    }
}
