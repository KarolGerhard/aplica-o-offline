package com.example.poctnoffline.di

import android.content.Context
import androidx.room.Room
import com.example.poctnoffline.database.UsuarioDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DbModule() {
    @Provides
    @Singleton
    fun providesDb(context: Context) =
        Room.databaseBuilder(context, UsuarioDatabase::class.java, "database").build()

    @Provides
    @Singleton
    open fun providesUsuarioDao(db: UsuarioDatabase) = db.usuarioDao()

}