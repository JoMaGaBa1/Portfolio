package com.example.africakotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.size
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {
    private val bdd = InicializaBD(this) //creamos la bd
    private var jugando = false
    private var aciertos = 0
    private var fallos = 0
    private var paises = mutableListOf<String>()
    private var capitales = mutableListOf<String>()
    private var mapa = mutableMapOf<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtPais = findViewById<TextView>(R.id.txtPais)
        val grpBotones = findViewById<RadioGroup>(R.id.grpOpciones)
        val txtInfo = findViewById<TextView>(R.id.txtInfo)
        val btnPrincipal = findViewById<ImageButton>(R.id.btnPrincipal)

        rellenaDatos() //recogemos los datos de la bd

        btnPrincipal.setOnClickListener {
            txtInfo.setText(R.string.otro)
            if (!jugando) {
                iniciarJuego(txtPais, grpBotones)
                colocaNuevo(txtPais, grpBotones)
                jugando = true
            } else {
                try {
                    revisaResultado(grpBotones, txtPais.text.toString())
                    colocaNuevo(txtPais, grpBotones)
                    grpBotones.clearCheck()
                } catch (e: IllegalStateException) { //si no se marca una opción
                    Toast.makeText(this, R.string.marcarObligatorio, Toast.LENGTH_SHORT).show()
                } catch (e: IllegalArgumentException) { //si se acaba el juego
                    jugando = false
                    acabarJuego(txtInfo)
                }
            }
        }
    }

    private fun rellenaDatos() {
        mapa = bdd.consultarPaises() //nos traemos el mapa de la bd
        paises = mutableListOf() //limpiamos la información que haya actualmente
        capitales = mutableListOf()
        mapa.forEach{ (k, v) -> // vamos insertando según lo que hayamos recogido
            paises.add(k)
            capitales.add(v)
        }
    }

    private fun iniciarJuego(txtPais: TextView, grpBotones: RadioGroup) {
        aciertos = 0 //reiniciamos aciertos y fallos
        fallos = 0
        txtPais.visibility = View.VISIBLE //cambiamos la visibilidad de los elementos
        grpBotones.visibility = View.VISIBLE
        grpBotones.clearCheck() //quitamos la selección del radio button
    }

    private fun acabarJuego(txtInfo: TextView) {
        txtInfo.setText(R.string.fin)
        val i = Intent(this@MainActivity, Resultados::class.java) //creamos la nueva actividad
        i.putExtra("total", mapa.size) //le pasamos la información necesaria
        i.putExtra("aciertos", aciertos)
        i.putExtra("fallos", fallos)
        startActivity(i) //arrancamos la actividad
    }

    @Throws(IllegalArgumentException::class)
    private fun colocaNuevo(txtPais: TextView, grpBotones: RadioGroup) {
        val aleatorio = java.util.Random().nextInt(paises.size)
        val pais = paises[aleatorio] //extraemos un país aleatorio
        val capital = mapa.getValue(pais) //extraemos su capital
        val posibles = capitales.toMutableList()
        posibles.remove(capital) //quitamos la capital de las posibles para que no se repita
        val opciones = mutableListOf(capital) //creamos la lista de las opciones añadiendo la capital
        for (i in 0..grpBotones.size - 2) {
            val opcion = posibles.random() //añadimos otras opciones aleatorias
            posibles.remove(opcion) //las quitamos de las posibles para que no se repitan
            opciones.add(opcion)
        }
        opciones.shuffle() //barajamos las opciones
        txtPais.text = pais //colocamos el país en pantalla
        val btn1 = findViewById<RadioButton>(R.id.optUno) //buscamos los botones
        val btn2 = findViewById<RadioButton>(R.id.optDos)
        val btn3 = findViewById<RadioButton>(R.id.optTres)
        val btn4 = findViewById<RadioButton>(R.id.optCuatro)
        btn1.text = opciones[0] //les ponemos las opciones correspondientes
        btn2.text = opciones[1]
        btn3.text = opciones[2]
        btn4.text = opciones[3]
        paises.removeAt(aleatorio) //quitamos el país para que no se repita más tarde
    }

    private fun revisarCorrecta(grpBotones: RadioGroup, pais: String): Boolean {
        var devolver = false
        val idSeleccionado = grpBotones.checkedRadioButtonId //buscamos el botón seleccionado
        val seleccionado = findViewById<RadioButton>(idSeleccionado)
        val capital = mapa.getValue(pais) //sacamos su capital
        if(capital == seleccionado.text) { //si coincide cambiamos la variable a verdadera
            devolver = true
        }
        return devolver
    }

    private fun revisaResultado(grpBotones: RadioGroup, pais: String) {
        val resultado = revisarCorrecta(grpBotones, pais)
        if (resultado) { //si acertamos o fallamos sumaremos a los aciertos o a los fallos
            aciertos++
        } else {
            fallos++
        }
    }
}