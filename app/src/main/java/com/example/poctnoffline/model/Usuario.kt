package com.example.poctnoffline.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "user_table")
data class Usuario(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "cpf") val cpf: String,
    @ColumnInfo(name = "nome") var nome: String,
    @ColumnInfo(name = "rowVersion") var rowVersion: Long,
    @ColumnInfo(name = "ultimaAlteracao") val ultimaAlteracao: Date
    )