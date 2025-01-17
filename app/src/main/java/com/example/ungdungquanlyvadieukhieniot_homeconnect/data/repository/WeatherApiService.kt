package com.example.ungdungquanlyvadieukhieniot_homeconnect.data.repository

import com.example.ungdungquanlyvadieukhieniot_homeconnect.data.remote.dto.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    // Endpoint cho Current Weather
    @GET("current.json")
    fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") location: String
    ): Call<WeatherResponse>
}
