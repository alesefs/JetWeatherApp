package com.example.jetweatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetweatherapp.model.Favorite

@Database(
    entities = [Favorite::class/*, Unit::class*/],
    version = 2,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}