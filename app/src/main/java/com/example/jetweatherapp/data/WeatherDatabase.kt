package com.example.jetweatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetweatherapp.model.Favorite
import com.example.jetweatherapp.model.UnitTemp

@Database(
    entities = [Favorite::class, UnitTemp::class],
    version = 3,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}