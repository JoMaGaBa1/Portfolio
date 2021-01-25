package com.example.neuropa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.size
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {
    private var jugando = false
    private var aciertos = 0
    private var fallos = 0
    private var paises = mutableListOf<String>()
    private var capitales = mutableListOf<String>()
    private val mapa = mutableMapOf(
            "Albania" to "Tirana",
            "Alemania" to "Berlín",
            "Andorra" to "Andorra La Vieja",
            "Armenia " to "Ereván",
            "Austria" to "Viena"/*,
            "Azerbaiyán " to "Bakú",
            "Bélgica" to "Bruselas",
            "Bielorrusia" to "Minsk",
            "Bosnia y Herzegovina" to "Sarajevo",
            "Bulgaria" to "Sofía",
            "Chipre" to "Nicosia",
            "Ciudad del Vaticano" to "Ciudad del Vaticano",
            "Croacia" to "Zagreb",
            "Dinamarca" to "Copenhague",
            "Eslovaquia" to "Bratislava",
            "Eslovenia" to "Liubliana",
            "España" to "Madrid",
            "Estonia" to "Tallín",
            "Finlandia" to "Helsinki",
            "Francia" to "París",
            "Georgia" to "Tiflis",
            "Grecia" to "Atenas",
            "Hungría" to "Budapest",
            "Irlanda" to "Dublín",
            "Islandia" to "Reikiavik",
            "Italia" to "Roma",
            "Kazajistán" to "Nursultán",
            "Letonia" to "Riga",
            "Liechtenstein" to "Vaduz",
            "Lituania" to "Vilna",
            "Luxemburgo" to "Luxemburgo",
            "Macedonia del Norte" to "Skopie",
            "Malta" to "La Valeta",
            "Moldavia" to "Chisinau",
            "Mónaco" to "Mónaco",
            "Montenegro" to "Podgorica",
            "Noruega" to "Oslo",
            "Países Bajos" to "Ámsterdam",
            "Polonia" to "Varsovia",
            "Portugal" to "Lisboa",
            "República Checa" to "Praga",
            "Rumanía" to "Bucarest",
            "Rusia" to "Moscú",
            "San Marino" to "San Marino",
            "Serbia" to "Belgrado",
            "Suecia" to "Estocolmo",
            "Suiza" to "Berna",
            "Turquía" to "Ankara",
            "Ucrania" to "Kiev"*/
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtPais = findViewById<TextView>(R.id.txtPais)
        val grpBotones = findViewById<RadioGroup>(R.id.grpOpciones)
        val txtInfo = findViewById<TextView>(R.id.txtInfo)
        val btnPrincipal = findViewById<ImageButton>(R.id.btnPrincipal)

        rellenaDatos()

        btnPrincipal.setOnClickListener {
            txtInfo.setText(R.string.otro)
            if (!jugando) {
                if (paises.isEmpty()) {
                    acabarJuego(txtInfo)
                } else {
                    iniciarJuego(txtPais, grpBotones)
                    colocaNuevo(txtPais, grpBotones)
                    jugando = true
                }
            } else {
                try {
                    revisaResultado(grpBotones, txtPais.text.toString())
                    colocaNuevo(txtPais, grpBotones)
                    grpBotones.clearCheck()
                } catch (e: IllegalStateException) {
                    Toast.makeText(this, "Debes marcar una opción", Toast.LENGTH_SHORT).show()
                } catch (e: IllegalArgumentException) {
                    jugando = false
                    acabarJuego(txtInfo)
                }
            }
        }
    }

    private fun rellenaDatos() {
        paises = mutableListOf()
        capitales = mutableListOf()
        mapa.forEach{ (k, v) ->
            paises.add(k)
            capitales.add(v)
        }
    }

    private fun iniciarJuego(txtPais: TextView, grpBotones: RadioGroup) {
        aciertos = 0
        fallos = 0
        txtPais.visibility = View.VISIBLE
        grpBotones.visibility = View.VISIBLE
        grpBotones.clearCheck()
    }

    private fun acabarJuego(txtInfo: TextView) {
        txtInfo.setText(R.string.fin)
        Toast.makeText(this, mapa.size.toString() + " países, " + aciertos.toString() + " aciertos / " + fallos.toString() + " fallos", Toast.LENGTH_LONG).show()
        rellenaDatos()
    }

    @Throws(IllegalArgumentException::class)
    private fun colocaNuevo(txtPais: TextView, grpBotones: RadioGroup) {
        val aleatorio = java.util.Random().nextInt(paises.size)
        val pais = paises[aleatorio]
        val capital = mapa.getValue(pais)
        val posibles = capitales.toMutableList()
        posibles.remove(capital)
        val opciones = mutableListOf(capital)
        for (i in 0..grpBotones.size - 2) {
            val opcion = posibles.random()
            posibles.remove(opcion)
            opciones.add(opcion)
        }
        opciones.shuffle()
        txtPais.text = pais
        val btn1 = findViewById<RadioButton>(R.id.optUno)
        val btn2 = findViewById<RadioButton>(R.id.optDos)
        val btn3 = findViewById<RadioButton>(R.id.optTres)
        val btn4 = findViewById<RadioButton>(R.id.optCuatro)
        btn1.text = opciones[0]
        btn2.text = opciones[1]
        btn3.text = opciones[2]
        btn4.text = opciones[3]
        paises.removeAt(aleatorio)
    }

    private fun revisarCorrecta(grpBotones: RadioGroup, pais: String): Boolean {
        var devolver = false
        val idSeleccionado = grpBotones.checkedRadioButtonId
        val seleccionado = findViewById<RadioButton>(idSeleccionado)
        val capital = mapa.getValue(pais)
        if(capital == seleccionado.text) {
            devolver = true
        }
        return devolver
    }

    private fun revisaResultado(grpBotones: RadioGroup, pais: String) {
        val resultado = revisarCorrecta(grpBotones, pais)
        val cadena: String
        cadena = if (resultado) {
            aciertos++
            "¡Correcto, muy bien!"
        } else {
            fallos++
            "¡Incorrecta, has fallado!"
        }
        Toast.makeText(this, cadena, Toast.LENGTH_SHORT).show()
    }
}