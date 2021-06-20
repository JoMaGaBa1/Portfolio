package com.proyectointegrado.ciudadanoinforma.ui.login

//Clase de datos para almacenar si el inicio de sesion es correcto o no
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)