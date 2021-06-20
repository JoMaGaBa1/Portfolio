package com.proyectointegrado.ciudadanoinforma.ui.login

//Clase de datos para almacenar datos si el usuario o contraseña es erroneo
data class LoginFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)