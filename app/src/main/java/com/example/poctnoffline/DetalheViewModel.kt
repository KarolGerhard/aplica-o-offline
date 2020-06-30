package com.example.poctnoffline

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.poctnoffline.model.Usuario
import com.example.poctnoffline.repository.UsuarioRepository
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class DetalheViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var repository: UsuarioRepository

    @Inject
    lateinit var appExecutors: AppExecutors



    init {
        getApplication<App>().component.inject(this)
    }


    private val _usuarioId = MutableLiveData<Int>()
    val usuarioId: LiveData<Int>
        get() = _usuarioId

    private val _nome = MutableLiveData<String>()
    val nome: LiveData<String>
        get() = _nome

    private val _cpf = MutableLiveData<String>()
    val cpf: LiveData<String>
        get() = _cpf

    private val _rowVersion = MutableLiveData<Long>()
    val rowVersion: LiveData<Long>
        get() = _rowVersion

    private val _ultimaAlteracao = MutableLiveData<Date>()
    val ultimaAlteracao: LiveData<Date>
        get() = _ultimaAlteracao

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean>
        get() = _success

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _detalheUsuario = MutableLiveData<Usuario>()


    fun salvar() = viewModelScope.launch {
        var detalhesUsuario = _detalheUsuario.value
        if (detalhesUsuario != null) {
            detalhesUsuario.nome = _nome.value ?: ""
            detalhesUsuario.rowVersion = 0
            _rowVersion.postValue(0)
            repository.salvarLocalmente(detalhesUsuario)
        }
    }


    fun getDetalhe(usuarioId: Int) {
        _isLoading.value = true
        appExecutors.networkIO.execute {
            var local = repository.getDetalhesLocal(usuarioId)
            val response = repository.getDetalhes(usuarioId)
            if (response != null) {
                _usuarioId.postValue(response.id)
                _cpf.postValue(response.cpf)
                _rowVersion.postValue(
                    local?.rowVersion ?: response.rowVersion
                )
                _nome.postValue(
                    local?.nome ?: response.nome
                )
                _detalheUsuario.postValue(response)
            }

            _isLoading.postValue(false)
        }
    }

    fun enviarServidor() {
        var detalhesUsuario = _detalheUsuario.value
        appExecutors.networkIO.execute {
            if (detalhesUsuario != null) {
                detalhesUsuario.nome = _nome.value ?: ""
                detalhesUsuario.rowVersion = 0
                var rowVersioValue =  repository.postEnviar(detalhesUsuario)
                _rowVersion.postValue(rowVersioValue)

            }
        }

    }

    fun setNome(nomeAtual: String) {
        _nome.value = nomeAtual
    }
}




