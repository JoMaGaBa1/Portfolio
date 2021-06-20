package com.proyectointegrado.ciudadanoinforma.ui.main

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proyectointegrado.ciudadanoinforma.R
import com.proyectointegrado.ciudadanoinforma.config.api.Retrofit
import com.proyectointegrado.ciudadanoinforma.config.sqlite.SQLiteService
import com.proyectointegrado.ciudadanoinforma.data.DisplayComentario
import com.proyectointegrado.ciudadanoinforma.data.model.Comments
import com.proyectointegrado.ciudadanoinforma.data.model.DataClassParse
import com.proyectointegrado.ciudadanoinforma.data.model.InsertComent
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


//Clase para mostrar cada una de las incidencias detalladamente
class ActivityIncidenciaIndividual : AppCompatActivity() {
    var InciList: ArrayList<Comments> = arrayListOf()
    var grupoIncidencia: TextView? = null
    var idIncidencia: TextView? = null
    var tituloIncidencia: TextView? = null
    var categoriaIncidencia: TextView? = null
    var ubicacionIncidencia: TextView? = null
    var fechaHoraIncidencia: TextView? = null
    var estadoIncidencia: TextView? = null
    var recyclerView: RecyclerView? = null
    var numComentario3: TextView? = null
    var imagenIncidencia: ImageView? = null
    val url = "http://80.59.11.241:8088/storage/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incidencia_individual)
        grupoIncidencia = findViewById(R.id.grupoIncidencia)
        idIncidencia = findViewById(R.id.idIncidencia)
        tituloIncidencia = findViewById(R.id.tituloIncidencia)
        categoriaIncidencia = findViewById(R.id.categoriaIncidencia)
        fechaHoraIncidencia = findViewById(R.id.fechaYHora)
        numComentario3 = findViewById(R.id.numComentarios3)
        ubicacionIncidencia = findViewById(R.id.ubicacionText)
        estadoIncidencia = findViewById(R.id.estadoText)
        imagenIncidencia = findViewById(R.id.imagenIncidencia)
        var insertComentario: ImageButton = findViewById(R.id.sendBtn)
        val textComentario: EditText = findViewById(R.id.enviarComentario)
        recyclerView = findViewById(R.id.mostrarComentarios)
        getMostrarIncidencia()

        //Botón para insertar el comentario
        insertComentario.setOnClickListener {
            if (textComentario.text.trim().length != 0) {
                var idPrueba = intent.getIntExtra("id", 0)
                var nuevoComentario: String = textComentario.text.toString()
                var insertarComentario = InsertComent(nuevoComentario)


                val api = Retrofit.buildService()
                val request =
                    api.InsertarComentario(
                        SQLiteService.getToken(this),
                        idPrueba,
                        insertarComentario
                    )
                request.enqueue(object : Callback<DataClassParse> {
                    override fun onResponse(
                        call: Call<DataClassParse>,
                        response: Response<DataClassParse>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "Comentario insertado",
                                Toast.LENGTH_LONG
                            ).show()
                            textComentario.text.clear()
                            InciList.clear()
                            getMostrarIncidencia()
                        }
                    }

                    override fun onFailure(call: Call<DataClassParse>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
            } else {
                Toast.makeText(
                    applicationContext,
                    "Inserte comentario",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    //Función para mostrar la incidencia.
    fun getMostrarIncidencia() {
        var idPrueba = intent.getIntExtra("id", 0)
        val api = Retrofit.buildService()
        val request = api.getIncidencia(SQLiteService.getToken(this), idPrueba)
        request.enqueue(object : Callback<DataClassParse> {
            override fun onResponse(
                call: Call<DataClassParse>,
                response: Response<DataClassParse>
            ) {
                if (response.isSuccessful) {
                    val parseo: DateFormat = SimpleDateFormat("dd / MM / yyyy    HH:mm ")
                    parseo.setTimeZone(TimeZone.getTimeZone("GMT+2"))

                    var posts = response.body()!!
                    idIncidencia?.setText(posts.id.toString())
                    tituloIncidencia?.setText(posts.titulo)
                    ubicacionIncidencia?.setText("Ubicación: " + posts.ubicacion)
                    estadoIncidencia?.setText("Estado: " + posts.estado.nombre)
                    categoriaIncidencia?.setText("Categoría: " +posts.categoria.nombre)
                    grupoIncidencia?.setText("Equipo encargado: " + posts.equipo.nombre)
                    fechaHoraIncidencia?.setText(parseo.format(posts.created_at))
                    numComentario3?.text = posts.comentarios.size.toString()
                    if (posts.archivo == null) {
                        Picasso.get().load(url + "cat" + posts.categoria.id + ".png")
                            .error(R.mipmap.ic_launcher_round).fit().centerInside()
                            .into(imagenIncidencia)
                    } else {
                        Picasso.get().load(url + posts.archivo).error(R.mipmap.ic_launcher_round)
                            .fit().centerInside().into(imagenIncidencia)
                    }

                    var lista = ArrayList<DataClassParse>()
                    lista.add(posts)
                    for (post in posts.comentarios) {
                        InciList.add(post)
                        putDataIntoRecyclerView(InciList)
                    }
                }
            }


            override fun onFailure(call: Call<DataClassParse>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }

    //Función para llamar a DisplayComentario y ponerlo en una lista.
    fun putDataIntoRecyclerView(InciList: ArrayList<Comments>) {
        val adapter2 = DisplayComentario(InciList)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.adapter = adapter2
    }
}
