package com.example.rinconsurapp.model

data class LoginUiState(
    val email: String = "",
    val contraseña: String = "",
    val emailError: String? = null,
    val contraseñaError: String? = null,
    val cargando: Boolean = false,
    val puedeEnviar: Boolean = false,
    val exito: Boolean = false,
    val mensajeError: String? = null
)