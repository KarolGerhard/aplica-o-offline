package com.example.poctnoffline.di

import com.example.poctnoffline.App
import com.example.poctnoffline.DetalheViewModel
import com.example.poctnoffline.UsuarioViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DbModule::class, RemoteModule::class])
interface AppComponent {
    fun inject(viewModel: UsuarioViewModel)
    fun inject(viewModel: DetalheViewModel)
    fun inject(app: App)
}