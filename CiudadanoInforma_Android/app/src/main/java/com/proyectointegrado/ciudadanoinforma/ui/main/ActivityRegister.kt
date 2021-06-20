package com.proyectointegrado.ciudadanoinforma.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.proyectointegrado.ciudadanoinforma.R
import com.proyectointegrado.ciudadanoinforma.config.api.Retrofit
import com.proyectointegrado.ciudadanoinforma.config.sqlite.SQLiteService
import com.proyectointegrado.ciudadanoinforma.data.model.RegisterUser
import com.proyectointegrado.ciudadanoinforma.data.model.UserData
import com.proyectointegrado.ciudadanoinforma.ui.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//Clase para el formulario de registro de usuario
class ActivityRegister : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val nombre: TextView = findViewById(R.id.nombreCompleto)
        val email: TextView = findViewById(R.id.email)
        val contrasenia: TextView = findViewById(R.id.password)
        val contrasenia2: TextView = findViewById(R.id.password2)
        var btnRegistrarse: Button = findViewById(R.id.register)


        //Botón para registrar el usuario.
        btnRegistrarse.setOnClickListener {
            var nombreUser: String = nombre.text.toString()
            var email: String = email.text.toString()
            var password: String = contrasenia.text.toString()
            var password2: String = contrasenia2.text.toString()
            if (nombreUser != null && nombreUser.length > 0 && email != null && email.length > 0 && password != null && password.length > 4 && password2 != null && password2.length > 4) {
                if (password == password2) {

                    var registrarUsuario = RegisterUser(
                        nombreUser,
                        email,
                        password
                    )
                    val api = Retrofit.buildService()
                    val request = api.getUsuarioNuevo(registrarUsuario)
                    request.enqueue(object : Callback<UserData> {
                        override fun onResponse(
                            call: Call<UserData>,
                            response: Response<UserData>
                        ) {
                            if (response.isSuccessful) {
                                Toast.makeText(
                                    applicationContext,
                                    "Usuario Registrado",
                                    Toast.LENGTH_LONG
                                ).show()
                                val registroInsertado =
                                    Intent(this@ActivityRegister, LoginActivity::class.java)
                                startActivity(registroInsertado)

                            }

                        }

                        override fun onFailure(call: Call<UserData>, t: Throwable) {
                            t.printStackTrace()
                        }
                    })
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Las contraseñas no coinciden",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "Revise los datos introducidos",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }
}