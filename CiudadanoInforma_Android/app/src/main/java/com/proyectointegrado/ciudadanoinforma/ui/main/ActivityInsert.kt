package com.proyectointegrado.ciudadanoinforma.ui.main

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.proyectointegrado.ciudadanoinforma.R
import com.proyectointegrado.ciudadanoinforma.config.api.Retrofit
import com.proyectointegrado.ciudadanoinforma.config.sqlite.SQLiteService
import com.proyectointegrado.ciudadanoinforma.data.model.IncidenciasUser
import com.proyectointegrado.ciudadanoinforma.data.model.MostrarSpinner
import com.squareup.picasso.Picasso
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

//Clase para insertar una incidencia.
class ActivityInsert : AppCompatActivity() {

    var datosCategoria: ArrayList<String> = ArrayList()
    var datosEquipos: ArrayList<String> = ArrayList()
    var hashCategoria: HashMap<Int, String> = HashMap()
    var hashEquipos: HashMap<Int, String> = HashMap()
    var imagenPrueba: ImageView? = null
    var editEquipo: Spinner? = null
    var editCategoria: Spinner? = null
    var itemCategoria = 0
    var itemEquipo = 0
    private val RESULT_LOAD_IMAGE: Int = 1

    var imagen: Uri? = null
    var pathImagen: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        llamadaCategoria()
        llamadaEquipo()

        imagenPrueba = findViewById(R.id.ImagenPrueba)
        var editTitulo: TextView = findViewById(R.id.editTitulo)
        var editUbic: TextView = findViewById(R.id.editUbic)
        var editComentario: TextView = findViewById(R.id.editComentario)
        var btnAceptar: Button = findViewById(R.id.btnInsertar)
        var btnCancelar: Button = findViewById(R.id.btnCancelar)
        var btnInsertar: Button = findViewById(R.id.btnFoto)


        var intent = Intent(this@ActivityInsert, MainDashboard::class.java)

        //Función del botón para insertar una imagen.
        btnInsertar.setOnClickListener {
            var intent2 = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent2.setType("*/*")
            startActivityForResult(
                Intent.createChooser(intent2, "Seleccione su fotografía"),
                RESULT_LOAD_IMAGE
            )

        }

        //Función del botón para añadir la incidencia.
        btnAceptar.setOnClickListener {
            var titulo: String = editTitulo.text.toString()
            var ubicacion: String = editUbic.text.toString()
            var comentario: String = editComentario.text.toString()
            var files: MultipartBody.Part? = null
            if (titulo != null && titulo.length > 0 && ubicacion != null && ubicacion.length > 0 && comentario != null && comentario.length > 0) {
                var categoria_id: Int = itemCategoria
                var equipo_id: Int = itemEquipo
                var estado_id = 1

                var tit: RequestBody = RequestBody.create(MultipartBody.FORM, titulo)
                var ubi: RequestBody = RequestBody.create(MultipartBody.FORM, ubicacion)
                var categoria: RequestBody =
                    RequestBody.create(MultipartBody.FORM, categoria_id.toString())
                var equipo: RequestBody =
                    RequestBody.create(MultipartBody.FORM, equipo_id.toString())
                var estado: RequestBody =
                    RequestBody.create(MultipartBody.FORM, estado_id.toString())
                var comentarios: RequestBody = RequestBody.create(MultipartBody.FORM, comentario)
                val api = Retrofit.buildService()


                try {
                    Picasso.get().load(imagen).error(R.mipmap.ic_launcher_round).fit()
                        .centerInside()
                        .into(imagenPrueba)
                    var archivo = File(pathImagen)
                    var filePart: RequestBody =
                        RequestBody.create("multipart/form-data".toMediaTypeOrNull(), archivo)

                    files = MultipartBody.Part.createFormData("archivo", archivo.name, filePart)
                } catch (e: Throwable) {
                    files = MultipartBody.Part.createFormData("archivo", null.toString())
                }
                val request = api.getIncidenciasCrear(
                    SQLiteService.getToken(this),
                    tit,
                    ubi,
                    comentarios,
                    categoria,
                    equipo,
                    estado,
                    files!!
                )
                request.enqueue(object : Callback<IncidenciasUser> {
                    override fun onResponse(
                        call: Call<IncidenciasUser>,
                        response: Response<IncidenciasUser>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "Incidencia Insertada",
                                Toast.LENGTH_LONG
                            ).show()
                            startActivity(intent)
                        }

                    }

                    override fun onFailure(call: Call<IncidenciasUser>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
            } else {
                Toast.makeText(
                    applicationContext,
                    "Revise los datos insertados",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
        btnCancelar.setOnClickListener {
            startActivity(intent)

        }
    }

    //Función para rellenar el spinner con los datos de categoria.
    fun llamadaCategoria() {
        val api = Retrofit.buildService()
        val request = api.getListaCategoria(SQLiteService.getToken(this))
        request.enqueue(object : Callback<List<MostrarSpinner>> {
            override fun onResponse(
                call: Call<List<MostrarSpinner>>,
                response: Response<List<MostrarSpinner>>
            ) {
                if (response.isSuccessful) {
                    var posts = response.body()!!
                    if (posts != null) {
                        for (post in posts) {
                            hashCategoria.put(post.id, post.nombre)
                        }

                    }
                    datosCategoria = ArrayList(hashCategoria.values)

                    editCategoria = findViewById(R.id.editCategoria)
                    var adaptadorCategoria: ArrayAdapter<CharSequence> = ArrayAdapter(
                        this@ActivityInsert,
                        android.R.layout.simple_spinner_item,
                        datosCategoria as List<String>
                    )
                    adaptadorCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    editCategoria?.adapter = adaptadorCategoria
                    editCategoria?.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                var item = parent?.getItemAtPosition(position).toString()
                                for ((key, value) in hashCategoria) {
                                    if (item == value) {
                                        itemCategoria = key
                                    }
                                }

                            }

                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                TODO("Not yet implemented")
                            }

                        }

                }

            }

            override fun onFailure(call: Call<List<MostrarSpinner>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    //Función para llenar el spinner con los datos de equipo
    fun llamadaEquipo() {
        val api = Retrofit.buildService()
        val request = api.getListaEquipos(SQLiteService.getToken(this))
        request.enqueue(object : Callback<List<MostrarSpinner>> {
            override fun onResponse(
                call: Call<List<MostrarSpinner>>,
                response: Response<List<MostrarSpinner>>
            ) {
                if (response.isSuccessful) {
                    var posts = response.body()!!
                    if (posts != null) {
                        for (post in posts) {
                            hashEquipos.put(post.id, post.nombre)
                        }
                    }
                    datosEquipos = ArrayList(hashEquipos.values)
                    editEquipo = findViewById(R.id.editEquipo)
                    var adaptadorEquipos: ArrayAdapter<CharSequence> = ArrayAdapter(
                        this@ActivityInsert,
                        android.R.layout.simple_spinner_item,
                        datosEquipos as List<String>
                    )

                    adaptadorEquipos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    editEquipo?.adapter = adaptadorEquipos

                    editEquipo?.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                var item = parent?.getItemAtPosition(position).toString()
                                for ((key, value) in hashEquipos) {
                                    if (item == value) {
                                        itemEquipo = key
                                    }
                                }
                            }

                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                TODO("Not yet implemented")
                            }

                        }
                }

            }

            override fun onFailure(call: Call<List<MostrarSpinner>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    //Función para obtener la dirección de la imagen, la miniatura se mostrará a la derecha del botón.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            val selectedImage = data.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor: Cursor? = contentResolver.query(
                selectedImage!!,
                filePathColumn, null, null, null
            )
            cursor!!.moveToFirst()
            val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
            val picturePath: String = cursor.getString(columnIndex)
            imagenPrueba?.setImageURI(selectedImage)
            imagen = selectedImage
            pathImagen = picturePath
            println(pathImagen)

            cursor.close()
        }
    }

}