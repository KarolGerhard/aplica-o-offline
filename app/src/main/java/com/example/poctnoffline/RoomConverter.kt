package com.example.poctnoffline

import androidx.room.TypeConverter
import java.util.*

class RoomConverter {
    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }
}