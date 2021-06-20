package com.proyectointegrado.ciudadanoinforma.data.model

import java.util.*

data class Comments(
    var id: Int,
    var contenido: String,
    var incidencia: String,
    var usuario: String,
    var created_at: Date,
    var updated_at: Date
)