package com.example.jetweatherapp.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweatherapp.model.UnitTemp
import com.example.jetweatherapp.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherSettingsViewModel @Inject constructor(
    private val repository: WeatherDbRepository
): ViewModel() {
    private val _unitList = MutableStateFlow<List<UnitTemp>>(emptyList())
    val unitList = _unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUnits()
                .distinctUntilChanged()
                .collect { listOfUnits ->
                    if (listOfUnits.isEmpty()) {
                        Log.d("TAG", ":Empty list ")
                    }else {
                        _unitList.value = listOfUnits

                    }
                }
        }
    }

    fun insertUnit(unit: UnitTemp) = viewModelScope.launch { repository.insertUnit(unit) }
    fun updateUnit(unit: UnitTemp) = viewModelScope.launch { repository.updateUnit(unit) }
    fun deleteUnit(unit: UnitTemp) = viewModelScope.launch { repository.deleteUnit(unit) }
    fun deleteAllUnits() = viewModelScope.launch { repository.deleteAllUnits() }


}