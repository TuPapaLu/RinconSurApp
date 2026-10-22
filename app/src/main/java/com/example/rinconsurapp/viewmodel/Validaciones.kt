package com.example.rinconsurapp.viewmodel

import android.util.Patterns
import com.example.rinconsurapp.model.Accesos

fun validarEmail(email: String): String? {
    if (email.isBlank()) return "El email es obligatorio"
    val ok = Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
    return if (!ok) "Formato de email inválido" else null
}

fun validarContraseña(contraseña: String): String? {
    if (contraseña.isBlank()) return "La contraseña es obligatoria"
    if (contraseña.length < 6) return "Mínimo 6 caracteres"
    return null
}

fun validarAcceso(id: Int?): String? {
    if (id == null) return "Selecciona un acceso"
    if (Accesos.porId(id) == null) return "El acceso debe ser entre 1 y 4"
    return null
}
