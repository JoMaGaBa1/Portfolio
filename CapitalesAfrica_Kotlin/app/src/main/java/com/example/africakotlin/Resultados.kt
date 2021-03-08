package com.example.africakotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Resultados : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)
        val txtTotal = findViewById<TextView>(R.id.txtTotal)
        val txtAciertos  = findViewById<TextView>(R.id.txtAciertos)
        val txtFallos  = findViewById<TextView>(R.id.txtFallos)
        val btnInicio  = findViewById<Button>(R.id.btnInicio)
        txtTotal.text = "Total de países: " + intent.getIntExtra("total", 0) //ponemos el texto en las etiquetas
        txtAciertos.text = "Aciertos: " + intent.getIntExtra("aciertos", 0)
        txtFallos.text = "Fallos: " + intent.getIntExtra("fallos", 0)
        btnInicio.setOnClickListener {
            cambiaInicio() //volvemos al inicio
        }
    }

    override fun onBackPressed() { //si le damos para atrás
        cambiaInicio() //volvemos al inicio
    }

    private fun cambiaInicio() {
        val i = Intent(this@Resultados, Inicio::class.java) //creamos la nueva actividad
        startActivity(i) //iniciamos la nueva actividad
    }
}