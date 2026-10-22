package com.example.rinconsurapp.viewmodel

import androidx.lifecycle.ViewModel                // Clase base de ViewModel
import androidx.lifecycle.viewModelScope           // Corrutinas ligadas al ViewModel
import com.example.rinconsurapp.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow    // Estado que se puede modificar
import kotlinx.coroutines.flow.StateFlow           // Estado de solo lectura (para la pantalla)
import kotlinx.coroutines.flow.update              // Actualiza el estado con copy()
import kotlinx.coroutines.launch                   // Lanza una corrutina

// ViewModel del Login: recibe lo que el usuario hace en la pantalla,
// usa las validaciones (Validaciones.kt), aplica las reglas de negocio
// y actualiza el estado (LoginUiState). La pantalla solo muestra el estado.
class LoginViewModel : ViewModel() {

    private val repository = UsuarioRepository()            // Origen de datos de los usuarios

    private val _estado = MutableStateFlow(LoginUiState())  // Estado interno (modificable)
    val estado: StateFlow<LoginUiState> = _estado           // Estado público (solo lectura)

    // Se llama cada vez que el usuario escribe en el campo email
    fun onEmailChange(valor: String) {
        _estado.update { it.copy(email = valor, emailError = validarEmail(valor), mensajeError = null) }
        recalcularPuedeEnviar()
    }

    // Se llama cada vez que el usuario escribe en el campo contraseña
    fun onContrasenaChange(valor: String) {
        _estado.update { it.copy(contraseña = valor, contraseñaError = validarContraseña(valor), mensajeError = null) }
        recalcularPuedeEnviar()
    }

    // Regla de negocio: el botón "Entrar" solo se habilita si todo es válido
    private fun recalcularPuedeEnviar() {
        val s = _estado.value
        val puede = s.emailError == null && s.contraseñaError == null &&
                s.email.isNotBlank() && s.contraseña.isNotBlank()
        _estado.update { it.copy(puedeEnviar = puede) }
    }

    // Acción del botón "Entrar"
    fun iniciarSesion() {
        val s = _estado.value
        if (!s.puedeEnviar || s.cargando) return                    // Evita enviar dos veces
        viewModelScope.launch {
            _estado.update { it.copy(cargando = true, mensajeError = null) }
            val resultado = repository.login(s.email.trim(), s.contraseña)
            _estado.update {
                if (resultado.isSuccess) {
                    it.copy(cargando = false, exito = true)         // Login correcto → la pantalla navega
                } else {
                    it.copy(cargando = false,
                        mensajeError = resultado.exceptionOrNull()?.message ?: "Error al iniciar sesión")
                }
            }
        }
    }

    // Deja el formulario limpio después de navegar (o al cerrar sesión)
    fun limpiarFormulario() {
        _estado.value = LoginUiState()
    }
}