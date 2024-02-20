package com.example.jetweatherapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "setup_tbl")
data class UnitTemp(
    @PrimaryKey
    @ColumnInfo(name = "unit")
    val unit: String
)
