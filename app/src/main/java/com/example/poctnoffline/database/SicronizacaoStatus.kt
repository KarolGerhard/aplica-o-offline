package com.example.poctnoffline.database

import com.google.gson.annotations.SerializedName

enum class SicronizacaoStatus {

    @SerializedName("Atualizado")
    ATUALIZADO,

    @SerializedName("Atualizar")
    ATUALIZAR,

    @SerializedName("Offline")
    OFFLINE
}