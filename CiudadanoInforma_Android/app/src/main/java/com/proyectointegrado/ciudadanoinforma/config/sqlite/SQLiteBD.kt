package com.proyectointegrado.ciudadanoinforma.config.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory

class SQLiteBD(context: Context, factory: CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, "ciudadanoinforma.db", factory, version) {
    //Base de Datos interna donde se guarda el Token para su uso posterior.
    object Tablas {
        const val TABLA_TOKENS = "tokens"
    }

    object ColumnasTokens {
        const val COLUMNA_ID = "id"
        const val COLUMNA_TIPO = "tipo"
        const val COLUMNA_TOKEN = "token"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS " + Tablas.TABLA_TOKENS + "(" + ColumnasTokens.COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ColumnasTokens.COLUMNA_TIPO + " TEXT NOT NULL, " + ColumnasTokens.COLUMNA_TOKEN + " TEXT UNIQUE NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + Tablas.TABLA_TOKENS)
        onCreate(db)
    }

    fun insertaToken(db: SQLiteDatabase?, tipo: String, token: String): Long? {
        onUpgrade(db, 1, 2)
        val nuevo = ContentValues().apply {
            put(ColumnasTokens.COLUMNA_TIPO, tipo)
            put(ColumnasTokens.COLUMNA_TOKEN, token)
        }
        return db?.insert(Tablas.TABLA_TOKENS, null, nuevo)
    }

    fun eliminaToken(db: SQLiteDatabase?, token: String): Int? {
        return db?.delete(Tablas.TABLA_TOKENS, ColumnasTokens.COLUMNA_TOKEN + "=?", arrayOf(token))
    }

    fun extraeTipo(db: SQLiteDatabase?): String {
        val query = "SELECT tipo FROM " + Tablas.TABLA_TOKENS
        val result = db?.rawQuery(query, null)
        var token: String? = null
        if (result != null) {
            if (result.moveToFirst()) {
                do {
                    token = result.getString(0)
                } while (result.moveToNext())
            }
            result.close()
        }
        return token.toString()
    }

    // Función para extraer el token de la base de datos y usarlo posteriormente cuando se es llamado.
    fun extraeToken(db: SQLiteDatabase?): String {
        val query = "SELECT token FROM " + Tablas.TABLA_TOKENS
        val result = db?.rawQuery(query, null)
        var token: String? = null
        if (result != null) {
            if (result.moveToFirst()) {
                do {
                    token = result.getString(0)
                } while (result.moveToNext())
            }
            result.close()
        }
        return token.toString()
    }
}