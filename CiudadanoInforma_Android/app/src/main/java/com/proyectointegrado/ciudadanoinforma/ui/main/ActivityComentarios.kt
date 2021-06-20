package com.proyectointegrado.ciudadanoinforma.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.proyectointegrado.ciudadanoinforma.R
import com.proyectointegrado.ciudadanoinforma.data.model.Comments

class ActivityComentarios : AppCompatActivity() {
    var inciList: ArrayList<Comments> = arrayListOf()
    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        recyclerView = findViewById(R.id.mostrarComentarios)

    }

}