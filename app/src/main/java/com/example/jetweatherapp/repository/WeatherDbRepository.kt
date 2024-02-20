package com.example.jetweatherapp.repository

import com.example.jetweatherapp.data.WeatherDao
import com.example.jetweatherapp.model.Favorite
import com.example.jetweatherapp.model.UnitTemp
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(
    private val weatherDao: WeatherDao
) {

    fun getFavorites(): Flow<List<Favorite>> = weatherDao.getFavorites()

    suspend fun insertFavorite(favorite: Favorite) = weatherDao.insertFavorite(favorite)

    suspend fun updateFavorite(favorite: Favorite) = weatherDao.updateFavorite(favorite)

    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavorites()

    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.deleteFavorite(favorite)

    suspend fun getFavById(city: String): Favorite = weatherDao.getFavById(city)

    fun getUnits(): Flow<List<UnitTemp>> = weatherDao.getUnits()

    suspend fun insertUnit(unit: UnitTemp) = weatherDao.insertUnit(unit)

    suspend fun updateUnit(unit: UnitTemp) = weatherDao.updateUnit(unit)

    suspend fun deleteAllUnits() = weatherDao.deleteAllUnits()

    suspend fun deleteUnit(unit: UnitTemp) = weatherDao.deleteUnit(unit)
}