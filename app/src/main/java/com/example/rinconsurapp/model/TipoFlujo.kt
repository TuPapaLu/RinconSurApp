package com.example.rinconsurapp.model

object TipoFlujo {
    const val TRANSITO = "TRANSITO"
    const val COMPRA = "COMPRA"

    fun etiqueta(tipo: String): String = if (tipo == TRANSITO) "Tránsito" else "Compra"
}