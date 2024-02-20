package com.example.jetweatherapp.screens.main

import androidx.lifecycle.ViewModel
import com.example.jetweatherapp.data.DataOrException
import com.example.jetweatherapp.model.Weather
import com.example.jetweatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherMainViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    suspend fun getWeatherData(
        city: String,
        units: String
    ) : DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(cityQuery = city, units = units)
    }

    /*val data: MutableState<DataOrException<Weather, Boolean, Exception>> = mutableStateOf(
        DataOrException(null, true, Exception(""))
    )

    init {
        loadWeather()
    }

    private fun loadWeather() {
        getWeather("Seattle")
    }

    private fun getWeather(city: String) {
        viewModelScope.launch {
            if (city.isEmpty()) return@launch
            data.value.loading = true
            data.value = repository.getWeather(cityQuery = city)

            if (data.value.data.toString().isNotEmpty()) data.value.loading = false
        }

        Log.d("ALELOG", "getWeather: ${data.value.data.toString()}")
    }*/

}