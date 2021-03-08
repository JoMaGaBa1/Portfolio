package com.example.africakotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        val bdd = InicializaBD(this) //arrancamos la bd
        val btnJugar = findViewById<Button>(R.id.btnJugar)
        val btnSalir = findViewById<Button>(R.id.btnSalir)
        btnJugar.setOnClickListener {
            bdd.resetearBd() //limpiamos la bd
            bdd.insertaPaises() //insertamos los países
            val i = Intent(this@Inicio, MainActivity::class.java) //creamos la nueva actividad
            startActivity(i) //iniciamos la nueva actividad
        }
        btnSalir.setOnClickListener {
            finishAffinity() //cerramos la app
        }
    }
}