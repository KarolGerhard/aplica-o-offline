package com.example.poctnoffline.remote

import com.example.poctnoffline.model.Usuario
import retrofit2.http.*

interface UsuarioService {
    @GET("/usuarios")
    fun getUsuario(
        @Query("rowVersion") rowVersion: Long
    ): retrofit2.Call<List<Usuario>>

    @GET("/usuarios/{id}")
    fun getDetalhes(
        @Path("id") usuarioId: Int
    ): retrofit2.Call<Usuario>

    @PUT("usuarios/{id}")
    fun postEnviar(
        @Path("id") usuarioId: Int,
        @Body usuario: Usuario
    ): retrofit2.Call<Any>
}
