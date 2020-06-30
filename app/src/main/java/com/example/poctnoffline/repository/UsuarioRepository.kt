package com.example.poctnoffline.repository

import androidx.annotation.WorkerThread
import com.example.poctnoffline.database.UsuarioDao
import com.example.poctnoffline.model.Usuario
import com.example.poctnoffline.remote.UsuarioService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


class UsuarioRepository @Inject constructor(
    private val usuarioDao: UsuarioDao,
    private val service: UsuarioService
) {

    //update
   fun salvarLocalmente(usuario: Usuario){
       runBlocking (IO){
           usuarioDao.salvar(usuario)
       }
    }

    init {
        runBlocking (IO){}
    }

    @WorkerThread
    fun getUsuarioRemote(): List<Usuario> {
        var result = listOf<Usuario>()

       var ultimaVersao = usuarioDao.getUltimaVersao() ?: 0

        try {
            val response = service.getUsuario(ultimaVersao).execute()

            if (response.isSuccessful) {
                response.body()?.let {
                    result = it
                    salvarLocalmente(result)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    private fun salvarLocalmente(usuarios: List<Usuario>) {
        usuarioDao.salvarTodos(usuarios)
    }

    fun getUsuarioLocal(): List<Usuario>{
      lateinit var usuarios : List<Usuario>
        runBlocking (IO) {
            usuarios = usuarioDao.getUsuarios()
        }
        return usuarios
    }


    @WorkerThread
    fun getDetalhes(usuarioId: Int): Usuario? {
        var result : Usuario? = null

        try {
            val response = service.getDetalhes(usuarioId).execute()

            if (response.isSuccessful) {
                response.body()?.let {
                    result = it
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    @WorkerThread
    fun postEnviar(usuario: Usuario): Long {
        try {
            val response =
                service.postEnviar(usuario.id, usuario).execute()
            if (response.isSuccessful) {
                var usuarioLocal = getDetalhes(usuario.id)
                if (usuarioLocal != null){
                    salvarLocalmente(usuarioLocal)
                    return usuarioLocal.rowVersion
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0
    }

    fun getDetalhesLocal(usuarioId: Int): Usuario? {
        var usuario : Usuario? = null
        runBlocking (IO) {
            usuario = usuarioDao.getDetalheUsuario(usuarioId)
        }
        return usuario
    }

}