package com.proyectointegrado.ciudadanoinforma.data.model

import java.sql.Timestamp
import java.util.*

data class IncidenciasUser(
    var id: Int,
    var titulo: String,
    var ubicacion: String,
    var archivo: String,
    var usuarios: List<UserData>,
    var categoria: CategoryData,
    var equipo: Equipos,
    var estado: Estados,
    var comentarios: List<Comments>,
    var created_at: String,
    var update_at: Timestamp

)
