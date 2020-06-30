package com.example.poctnoffline.di

import com.example.poctnoffline.App
import com.example.poctnoffline.remote.UsuarioService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
open class RemoteModule(private val app: App) {

    val cacheSize = (5 * 1024 * 1024).toLong()
    val cache = Cache(app.applicationContext.cacheDir, cacheSize)

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .protocols(arrayListOf(Protocol.HTTP_1_1))
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit = Retrofit.Builder()

        .baseUrl("http://192.168.0.80:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    @Provides
    @Singleton
    open fun providesUsuarioService(retrofit: Retrofit): UsuarioService =
        retrofit.create(UsuarioService::class.java)
}