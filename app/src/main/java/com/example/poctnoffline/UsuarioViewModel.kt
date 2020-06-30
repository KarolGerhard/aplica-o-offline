package com.example.poctnoffline

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.poctnoffline.model.Usuario
import com.example.poctnoffline.repository.UsuarioRepository
import javax.inject.Inject

class UsuarioViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var repository: UsuarioRepository

     @Inject
    lateinit var appExecutors: AppExecutors

   private val _usuarios = MutableLiveData<List<Usuario>>()
    val usuarios: LiveData<List<Usuario>>
        get() = _usuarios

    init {
        getApplication<App>().component.inject(this)
        _usuarios.postValue(repository.getUsuarioLocal())
    }


    private val _usuarioId = MutableLiveData<Int>()
    val usuarioId : LiveData<Int>
        get() = _usuarioId

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean>
        get() = _success

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    fun loadRemoteUsuarios() {
       _isLoading.value = true
        appExecutors.networkIO.execute {
            var response= repository.getUsuarioRemote()
                //todo comparar com o BD local para realizar o merge
             reloadLocal()
            _isLoading.postValue(false)
        }
    }

    fun reloadLocal() {
        _usuarios.postValue(repository.getUsuarioLocal())
    }

}