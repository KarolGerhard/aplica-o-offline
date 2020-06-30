package com.example.poctnoffline

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.poctnoffline.di.AppComponent
import com.example.poctnoffline.di.AppModule
import com.example.poctnoffline.di.DaggerAppComponent
import com.example.poctnoffline.di.RemoteModule

open class App : Application() {

    open val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .remoteModule(RemoteModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }


}




