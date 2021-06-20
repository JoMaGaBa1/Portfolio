package com.proyectointegrado.ciudadanoinforma.data.model

import java.sql.Timestamp

data class DataClassParse(
    var id: Int,
    var titulo: String,
    var ubicacion: String,
    var archivo: String,
    var usuarios: List<UserData>,
    var categoria: CategoryData,
    var equipo: Equipos,
    var estado: Estados,
    var comentarios: List<Comments>,
    var created_at: Timestamp,
    var update_at: Timestamp
)