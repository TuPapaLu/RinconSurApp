package com.example.rinconsurapp.repository

import com.example.rinconsurapp.model.Usuario
import com.example.rinconsurapp.model.validarContraseña
import kotlinx.coroutines.delay

class UsuarioRepository {
    private val usuarios = listOf(
        Usuario("Supervisora Tienda", "supervisor@rinconsur.cl", "Rincon123!"),
        Usuario("Personal Acceso", "personal@rinconsur.cl", "Acceso123!")
    )

    suspend fun login(email: String, contraseña: String): Result<Usuario> {
        delay(800)
        val usuario = usuarios.firstOrNull { it.email.equals(email, ignoreCase = true) }
        return if (usuario != null && usuario.contraseña == contraseña) {
            Result.success(usuario)
        } else {
            Result.failure(IllegalArgumentException("Credenciales inválidas"))
        }
    }
}