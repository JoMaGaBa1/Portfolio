package com.proyectointegrado.ciudadanoinforma.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

import com.proyectointegrado.ciudadanoinforma.R
import com.proyectointegrado.ciudadanoinforma.data.LoginDataSource
import com.proyectointegrado.ciudadanoinforma.data.LoginRepository
import com.proyectointegrado.ciudadanoinforma.ui.login.LoginActivity


//Clase que muestra los botones para cada tipo de incidencia, todas, añadir y cerrar sesión
class MainDashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dashboard)
        val Incidencias = findViewById<Button>(R.id.btnTodos)
        val Incendios = findViewById<ImageButton>(R.id.btnIncendios)
        val LogOutBtn = findViewById<Button>(R.id.btnLogOut)
        val Inundacion = findViewById<ImageButton>(R.id.btnInundacion)
        val MaltratoAnimal = findViewById<ImageButton>(R.id.btnMaltrato)
        val Vandalismo = findViewById<ImageButton>(R.id.btnVandalismo)
        val ParqueyJardin = findViewById<ImageButton>(R.id.btnParques)
        val Agresion = findViewById<ImageButton>(R.id.btnAgresion)
        val RoboAtraco = findViewById<ImageButton>(R.id.btnRobo)
        val Otros = findViewById<ImageButton>(R.id.btnOtros)
        var btnInsertarInci = findViewById<ImageButton>(R.id.btnInsertarIncidencia)
        val intent = Intent(this, ActivityIncidencias::class.java)
        var categoria: String?

        btnInsertarInci.setOnClickListener {
            val intent2 = Intent(this, ActivityInsert::class.java)
            startActivity(intent2)
        }
        Incidencias.setOnClickListener {
            categoria = "todasincidencias"
            intent.putExtra("TipoIncidencia", categoria)
            startActivity(intent)
        }

        Incendios.setOnClickListener {
            categoria = "incendio"
            intent.putExtra("TipoIncidencia", categoria)
            startActivity(intent)
        }

        Inundacion.setOnClickListener {
            categoria = "inundacion"
            intent.putExtra("TipoIncidencia", categoria)
            startActivity(intent)
        }

        MaltratoAnimal.setOnClickListener {
            categoria = "maltrato animal"
            intent.putExtra("TipoIncidencia", categoria)
            startActivity(intent)
        }

        Vandalismo.setOnClickListener {
            categoria = "vandalismo"
            intent.putExtra("TipoIncidencia", categoria)
            startActivity(intent)
        }

        ParqueyJardin.setOnClickListener {
            categoria = "parques y jardines"
            intent.putExtra("TipoIncidencia", categoria)
            startActivity(intent)
        }

        Agresion.setOnClickListener {
            categoria = "agresion"
            intent.putExtra("TipoIncidencia", categoria)
            startActivity(intent)
        }

        RoboAtraco.setOnClickListener {
            categoria = "robo/atraco"
            intent.putExtra("TipoIncidencia", categoria)
            startActivity(intent)
        }

        Otros.setOnClickListener {
            categoria = "otros"
            intent.putExtra("TipoIncidencia", categoria)
            startActivity(intent)
        }

        LogOutBtn.setOnClickListener {
            val repo = LoginRepository(LoginDataSource())
            repo.logout(this)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }


}