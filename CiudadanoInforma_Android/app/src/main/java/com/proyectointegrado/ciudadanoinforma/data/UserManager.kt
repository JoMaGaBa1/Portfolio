package com.proyectointegrado.ciudadanoinforma.data

import com.proyectointegrado.ciudadanoinforma.config.api.Retrofit
import com.proyectointegrado.ciudadanoinforma.data.model.LoggedInUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//Clase para extraer el usuario
class UserManager {
    companion object {
        fun extractUser(token: String): LoggedInUser {
            val api = Retrofit.buildService()
            val request = api.getLoggedInUser(token)
            var usuario = LoggedInUser(0, "invalid", "invalid")
            val response: Response<LoggedInUser> = request.execute()
            val cuerpo = response.body()
            if (cuerpo != null) {
                usuario = LoggedInUser(cuerpo.id, cuerpo.name, cuerpo.email)
            }

            return usuario
        }
    }
}