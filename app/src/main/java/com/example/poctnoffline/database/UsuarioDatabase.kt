package com.example.poctnoffline.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.poctnoffline.RoomConverter
import com.example.poctnoffline.model.Usuario

@Database(entities = [Usuario::class], version = 1, exportSchema = false)
@TypeConverters(RoomConverter::class)
abstract class UsuarioDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
}