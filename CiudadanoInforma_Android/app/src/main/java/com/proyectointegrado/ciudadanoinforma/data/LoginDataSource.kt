package com.proyectointegrado.ciudadanoinforma.data

import android.content.Context
import android.os.Build
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import com.proyectointegrado.ciudadanoinforma.config.api.Retrofit
import com.proyectointegrado.ciudadanoinforma.config.sqlite.SQLiteService
import com.proyectointegrado.ciudadanoinforma.data.model.LoggedInUser
import com.proyectointegrado.ciudadanoinforma.data.model.LoginRequest
import com.proyectointegrado.ciudadanoinforma.data.model.LoginResponse
import com.proyectointegrado.ciudadanoinforma.data.model.LogoutResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
//Clase para iniciar y cerrar sesion.
class LoginDataSource {

    //Función para iniciar sesión que devuelve el usuario si existe y sino se queda en la misma página.
    fun login(username: String, password: String, context: Context): Result<LoggedInUser> {
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        try {
            val datosIntento = LoginRequest(username, password)
            val api = Retrofit.buildService()
            val request = api.loginUser(datosIntento)
            var usuario = LoggedInUser(0, "", "")
            val response: Response<LoginResponse> = request.execute()
            val cuerpo = response.body()
            if (cuerpo != null) {
                if (cuerpo.access_token != null && cuerpo.token_type != null) {
                    val token = cuerpo.access_token.split("|");
                    val tipo = cuerpo.token_type
                    usuario = UserManager.extractUser(tipo + " " + token[1])
                    SQLiteService.insertaToken(context, tipo, token[1])
                }
            }
            if (usuario.id != 0) {
                return Result.Success(usuario)
            } else {
                throw Throwable()
            }

        } catch (e: Throwable) {
            return Result.Error(IOException("Error al loguearse", e))
        }
    }

    //Función para cerrar sesión.
    fun logout(context: Context) {
        val api = Retrofit.buildService()
        val request = api.logoutUser(SQLiteService.getToken(context))
        request.enqueue(object : Callback<LogoutResponse> {
            override fun onResponse(
                call: Call<LogoutResponse>,
                response: Response<LogoutResponse>
            ) {
                if (response.isSuccessful) {
                    SQLiteService.eliminaToken(context, SQLiteService.extraeToken(context))
                }
            }

            override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                println("Error al cerrar sesión")
            }
        })
    }
}