package com.proyectointegrado.ciudadanoinforma.data

import android.content.Context
import com.proyectointegrado.ciudadanoinforma.data.model.LoggedInUser


//Clase que solicita la autenticación e información del usuario
class LoginRepository(val dataSource: LoginDataSource) {

    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = null
    }

    fun logout(context: Context) {
        user = null
        dataSource.logout(context)
    }

    //Función para manejo del inicio de sesion
    fun login(username: String, password: String, context: Context): Result<LoggedInUser> {
        val result = dataSource.login(username, password, context)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
    }
}