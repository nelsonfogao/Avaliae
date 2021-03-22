package com.example.avaliae.model

import com.google.firebase.firestore.DocumentId

class Empresa (
    val nome: String? = null,
    val bairro: String? = null,
    val nota: Int? = null,
    val pergunta1: Int? = null,
    val pergunta2: Int? = null,
    val pergunta3: Int? = null,
    val pergunta4: Int? = null,
    val pergunta5: Int? = null,
    val autor: String? = null,
    @DocumentId
    val id: String? = null,
){
    override fun toString(): String = "$nome: $nota"
}