package com.example.poctnoffline.database

import androidx.room.*
import com.example.poctnoffline.model.Usuario

@Dao
interface UsuarioDao {


    @Query("SELECT * FROM user_table")
    fun getUsuarios(): List<Usuario>

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getDetalheUsuario(id: Int): Usuario?

    @Update
    fun salvar(
        usuario: Usuario
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvarTodos(
        usuario: List<Usuario>
    )

    @Query("SELECT MAX(rowVersion) FROM user_table")
    fun getUltimaVersao(): Long?

    @Delete
    fun remove(usuario: Usuario?)

    @Update
    fun updateUsuario(usuario: Array<out Usuario?>)

}