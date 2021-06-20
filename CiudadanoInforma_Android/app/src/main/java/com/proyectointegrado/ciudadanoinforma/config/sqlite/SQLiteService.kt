package com.proyectointegrado.ciudadanoinforma.config.sqlite

import android.content.Context

class SQLiteService {
    //Clase para el uso del Token
    companion object {
        fun insertaToken(context: Context, tipo: String, token: String): Long? {
            val db = SQLiteBD(context, null, 1)
            return db.insertaToken(db.writableDatabase, tipo, token)
        }

        fun eliminaToken(context: Context, token: String): Int? {
            val db = SQLiteBD(context, null, 1)
            return db.eliminaToken(db.writableDatabase, token)
        }

        fun getToken(context: Context): String {
            return extraeTipo(context) + " " + extraeToken(context)
        }

        fun extraeTipo(context: Context): String {
            val db = SQLiteBD(context, null, 1)
            return db.extraeTipo(db.readableDatabase);
        }

        fun extraeToken(context: Context): String {
            val db = SQLiteBD(context, null, 1)
            return db.extraeToken(db.readableDatabase);
        }
    }
}