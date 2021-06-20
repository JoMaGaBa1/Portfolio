package com.proyectointegrado.ciudadanoinforma.data.model

import java.util.*

data class UserData(
    var id: Int,
    var name: String,
    var email: String,
    var created_at: Date,
    var updated_at: Date
)
