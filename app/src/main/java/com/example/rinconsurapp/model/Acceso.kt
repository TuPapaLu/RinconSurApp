package com.example.rinconsurapp.model
data class Acceso(
    val id: Int,
    val nombre: String,
    val descripcion: String
)

object Accesos {
    val lista = listOf(
        Acceso(1, "Acceso 1", "Pasillo principal del mall"),
        Acceso(2, "Acceso 2", "Sector patio de comidas"),
        Acceso(3, "Acceso 3", "Conexión con tienda vecina"),
        Acceso(4, "Acceso 4", "Estacionamiento")
    )

    fun porId(id: Int): Acceso? = lista.firstOrNull { it.id == id }
}