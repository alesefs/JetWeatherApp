package com.example.jetweatherapp.repository

import android.util.Log
import com.example.jetweatherapp.data.DataOrException
import com.example.jetweatherapp.model.Weather
import com.example.jetweatherapp.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherAPI
){
    suspend fun getWeather(
        cityQuery: String
    ) : DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery)

        } catch (e: Exception) {
            Log.d("ALELOG", "getWeatherException: $e")
            return DataOrException(e = e)
        }

        Log.d("ALELOG", "getWeather: $response")
        return DataOrException(data = response)
    }
}