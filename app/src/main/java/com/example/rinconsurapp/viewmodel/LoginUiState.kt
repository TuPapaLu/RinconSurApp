package com.example.rinconsurapp.viewmodel

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