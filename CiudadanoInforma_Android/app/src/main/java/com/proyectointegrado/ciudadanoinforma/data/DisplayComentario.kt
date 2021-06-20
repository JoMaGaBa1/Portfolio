package com.proyectointegrado.ciudadanoinforma.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proyectointegrado.ciudadanoinforma.R
import com.proyectointegrado.ciudadanoinforma.data.model.Comments
import com.proyectointegrado.ciudadanoinforma.data.model.IncidenciasUser
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


//Clase para mostrar los comentarios de las incidencias.
class DisplayComentario(items: ArrayList<Comments>) :
    RecyclerView.Adapter<DisplayComentario.ViewHolder>() {

    var viewHolder: ViewHolder? = null
    var items: ArrayList<Comments> = arrayListOf()

    init {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_item_comentario, parent, false)
        viewHolder = ViewHolder(vista)
        return viewHolder!!
    }

    //función que muestra los datos en los textView.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parseo: DateFormat = SimpleDateFormat("dd / MM / yyyy    HH:mm")
        parseo.setTimeZone(TimeZone.getTimeZone("GMT+2"))
        val item = items.get(position)
        holder.nombreUsuario.text = item.usuario
        holder.fechaHoraComentario.text = parseo.format(item.created_at).toString()
        holder.comentario.text = item.contenido

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nombreUsuario: TextView
        var fechaHoraComentario: TextView
        var comentario: TextView

        init {
            nombreUsuario = itemView.findViewById(R.id.nombreUsuario)
            fechaHoraComentario = itemView.findViewById(R.id.fechaHoraComentario)
            comentario = itemView.findViewById(R.id.comentario)
        }

    }

    override fun getItemCount(): Int = this.items.count()

}