package com.example.africakotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class InicializaBD(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "africa"
        private val TABLA_AFRICA = "paises"
        private val ID_PAIS = "idPais"
        private val KEY_PAIS = "pais"
        private val KEY_CAPITAL = "capital"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLA_PAISES = ("CREATE TABLE " + TABLA_AFRICA + "("
                + ID_PAIS + " INTEGER PRIMARY KEY," + KEY_PAIS + " TEXT," + KEY_CAPITAL + " TEXT" + ")")
        db.execSQL(CREATE_TABLA_PAISES) //creamos la tabla
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_AFRICA) //eliminamos la tabla
    }

    fun resetearBd() {
        val db = this.writableDatabase
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_AFRICA) //eliminamos la tabla
        onCreate(db) //volvemos a crearla
    }

    fun insertaPaises() {
        val mapa = mutableMapOf(
            "Angola" to "Luanda",
            "Argelia" to "Argel",
            "Benín" to "Porto Novo",
            "Botsuana" to "Gaborone",
            "Burkina Faso" to "Uagadugú",
            "Burundi" to "Buyumbura",
            "Cabo Verde" to "Praia",
            "Camerún" to "Yaundé",
            "Chad" to "Yamena"/*,
            "Comoras" to "Moroni",
            "Congo" to "Brazzaville",
            "Costa de Marfil" to "Yamusukro",
            "Egipto" to "El Cairo",
            "Eritrea" to "Asmara",
            "Esuatini" to "Mbabane",
            "Etiopía" to "Adís Abeba",
            "Gabón" to "Libreville",
            "Gambia" to "Banjul",
            "Ghana" to "Acra",
            "Guinea" to "Conakry",
            "Guinea Bissau" to "Bissau",
            "Guinea Ecuatorial" to "Malabo",
            "Kenya" to "Nairobi",
            "Lesoto" to "Maseru",
            "Liberia" to "Monrovia",
            "Libia" to "Trípoli",
            "Madagascar" to "Antananarivo",
            "Malauí" to "Lilongüe",
            "Malí" to "Bamako",
            "Marruecos" to "Rabat",
            "Mauricio" to "Port Louis",
            "Mauritania" to "Nuakchot",
            "Mozambique" to "Maputo",
            "Namibia" to "Windhoek",
            "Níger" to "Niamey",
            "Nigeria" to "Abuya",
            "República Centroafricana" to "Bangui",
            "República Democrática del Congo" to "Kinshasa",
            "Ruanda" to "Kigali",
            "Santo Tomé y Príncipe" to "Santo Tomé",
            "Senegal" to "Dakar",
            "Seychelles" to "Victoria",
            "Sierra Leona" to "Freetown",
            "Somalia" to "Mogadiscio",
            "Sudáfrica" to "Ciudad del Cabo",
            "Sudán" to "Jartum",
            "Sudán del Sur" to  "Yuba",
            "Tanzania" to "Dodoma",
            "Togo" to "Lomé",
            "Túnez" to "Túnez",
            "Uganda" to "Kampala",
            "Yibuti" to "Yibuti",
            "Zambia" to "Lusaka",
            "Zimbabue" to  "Harare"*/
        )
        val db = this.writableDatabase
        val contentValues = ContentValues() //creamos el grupo de datos
        var i = 1
        mapa.forEach{ (k, v) ->
            contentValues.put(ID_PAIS, i) //colocamos los datos
            contentValues.put(KEY_PAIS, k)
            contentValues.put(KEY_CAPITAL, v)
            db.insert(TABLA_AFRICA, null, contentValues) //los insertamos
            i++
        }
        db.close() //cerramos la conexión con la bd
    }

    fun consultarPaises(): MutableMap<String, String> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM " + TABLA_AFRICA, null) //consultamos la bd
        val mapa = mutableMapOf<String, String>()
        while (cursor.moveToNext()) { //nos recorremos el resultado
            mapa.put(cursor.getString(1), cursor.getString(2)) //vamos añadiendo a un mapa
        }
        return mapa
    }
}