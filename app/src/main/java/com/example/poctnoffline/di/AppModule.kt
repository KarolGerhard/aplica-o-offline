package com.example.poctnoffline.di

import android.content.Context
import com.example.poctnoffline.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule(private val app: App) {
    @Provides
    @Singleton
    open fun provideApp() = app
//
    @Provides
    @Singleton
    open fun provideContext(): Context = app.applicationContext


}