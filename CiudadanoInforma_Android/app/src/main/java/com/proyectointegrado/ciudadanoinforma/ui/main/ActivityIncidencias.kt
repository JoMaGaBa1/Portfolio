package com.proyectointegrado.ciudadanoinforma.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proyectointegrado.ciudadanoinforma.R
import com.proyectointegrado.ciudadanoinforma.config.api.Retrofit
import com.proyectointegrado.ciudadanoinforma.config.sqlite.SQLiteService
import com.proyectointegrado.ciudadanoinforma.data.DisplayData
import com.proyectointegrado.ciudadanoinforma.data.model.IncidenciasUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//Clase para mostrar el reciclerView con las incidencias.
class ActivityIncidencias : AppCompatActivity() {
    var inciList: ArrayList<IncidenciasUser> = arrayListOf()
    var recyclerView: RecyclerView? = null
    var ningunaIncidencia: TextView? = null
    var ningunaIncidenciaImagen: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        recyclerView = findViewById(R.id.MostrarTodasInci)
        ningunaIncidencia = findViewById(R.id.ningunaIncidencia)
        ningunaIncidenciaImagen = findViewById(R.id.imagen_no_incidencia)

        val tipo: String? = intent.getStringExtra("TipoIncidencia")
        getIncidenciasList(tipo)

    }

    //Función para listar las incidencias por tipo o todas.
    fun getIncidenciasList(tipo: String?) {
        val api = Retrofit.buildService()
        var request: Call<List<IncidenciasUser>>?
        if (tipo?.equals("todasincidencias") == true) {
            request = api.getListaIncidencias(SQLiteService.getToken(this))
        } else {
            request = api.getIncidenciaXCategoria(SQLiteService.getToken(this), tipo.toString())
        }
        if (request != null) {
            request.enqueue(object : Callback<List<IncidenciasUser>> {
                override fun onResponse(
                    call: Call<List<IncidenciasUser>>,
                    response: Response<List<IncidenciasUser>>
                ) {
                    if (response.isSuccessful) {
                        var posts = response.body()!!
                        for (post in posts) {
                            inciList.add(post)
                            putDataIntoRecyclerView(inciList)
                        }
                        if (posts.isEmpty()) {
                            ningunaIncidencia?.visibility = View.VISIBLE
                            ningunaIncidenciaImagen?.visibility = View.VISIBLE
                        } else {
                            ningunaIncidencia?.visibility = View.INVISIBLE
                            ningunaIncidenciaImagen?.visibility = View.INVISIBLE

                        }
                    }
                }


                override fun onFailure(call: Call<List<IncidenciasUser>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }

    //Función para listar las incidencias y al pulsar en una llevarte a ella.
    fun putDataIntoRecyclerView(InciList: ArrayList<IncidenciasUser>) {
        val adapter2 = DisplayData(InciList, object : DisplayData.ClickListener {
            override fun onClick(vista: View, position: Int, ID: TextView) {
                val intent =
                    Intent(this@ActivityIncidencias, ActivityIncidenciaIndividual::class.java)
                intent.putExtra("id", Integer.parseInt(ID.text.toString()))
                startActivity(intent)
            }
        })
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.adapter = adapter2
    }

    //Recarga la vista cuando se le inserta un comentario
    override fun onRestart() {
        super.onRestart()
        finish()
        startActivity(intent)
    }
}