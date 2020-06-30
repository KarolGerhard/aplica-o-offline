package com.example.poctnoffline

data class ServerResponse<out T> (
    val success: Boolean,
    val messages : List<MessageRequestResponse>,
    val result : T?,
    val statusCode : Int
) {
    constructor() : this (
        success = false,
        messages = arrayListOf(MessageRequestResponse(
            type = "error",
            message = "Não foi possível obter resposta do servidor",
            title = "Algo deu errado!"
        )),
        result = null,
        statusCode = 999
    )
}