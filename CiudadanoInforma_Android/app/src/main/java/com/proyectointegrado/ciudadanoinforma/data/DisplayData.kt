package com.proyectointegrado.ciudadanoinforma.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proyectointegrado.ciudadanoinforma.R
import com.proyectointegrado.ciudadanoinforma.data.model.IncidenciasUser
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


// Clase para mostrar las incidencias en un reciclerView.
class DisplayData(items: ArrayList<IncidenciasUser>, var clickListener: ClickListener) :
    RecyclerView.Adapter<DisplayData.ViewHolder>() {
    var viewHolder: ViewHolder? = null
    var items: ArrayList<IncidenciasUser> = arrayListOf()

    init {
        this.items = items
    }

    interface ClickListener {
        fun onClick(vista: View, position: Int, ID: TextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_item, parent, false)
        viewHolder = ViewHolder(vista, clickListener)
        return viewHolder!!
    }

    //Función para mostrar los datos en los TextView.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parseo = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val parseo2 = SimpleDateFormat("dd/MM/yyyy  HH:mm")
        val item = items.get(position)
        val url = "http://80.59.11.241:8088/storage/"
        holder.ID.text = item.id.toString()
        holder.nombreIncidencia.text = item.titulo
        val fecha: String = item.created_at
        val fechaInci: Date = parseo.parse(fecha)
        val fechaNueva: String = parseo2.format(fechaInci)
        holder.fechaHoraIncidencia.text = fechaNueva
        holder.ubicacion.text = item.ubicacion
        if (item.archivo == null) {
            Picasso.get().load(url + "cat" + item.categoria.id + ".png")
                .error(R.mipmap.ic_launcher_round).fit().centerInside().into(holder.imagenView3)
        } else {
            Picasso.get().load(url + item.archivo).error(R.mipmap.ic_launcher_round).fit()
                .centerInside().into(holder.imagenView3)
        }

        holder.numComentario.text = item.comentarios.size.toString()

    }


    class ViewHolder(itemView: View, listener: ClickListener) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var vista = itemView
        var ID: TextView
        var nombreIncidencia: TextView
        var fechaHoraIncidencia: TextView
        var ubicacion: TextView
        var numComentario: TextView
        var imagenView3: ImageView
        var lyIncidencias: ClickListener? = null

        init {
            ID = itemView.findViewById(R.id.ID)
            nombreIncidencia = itemView.findViewById(R.id.nombreIncidencia)
            fechaHoraIncidencia = itemView.findViewById(R.id.fechaHoraIncidencia)
            ubicacion = itemView.findViewById(R.id.ubicacion)
            numComentario = itemView.findViewById(R.id.numComentarios)
            imagenView3 = itemView.findViewById(R.id.imageView3)
            this.lyIncidencias = listener
            vista.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            this.lyIncidencias?.onClick(v!!, adapterPosition, ID)
        }
    }

    override fun getItemCount(): Int = this.items.count()


}
