package com.example.africajava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class InicializaBD extends SQLiteOpenHelper {
    private String TABLA_AFRICA = "paises";
    private String ID_PAIS = "idPais";
    private String KEY_PAIS = "pais";
    private String KEY_CAPITAL = "capital";

    public InicializaBD(@Nullable Context context) {
        super(context, "africa", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + TABLA_AFRICA + "("
                + ID_PAIS + " INTEGER PRIMARY KEY," + KEY_PAIS + " TEXT," + KEY_CAPITAL + " TEXT" + ")";
        db.execSQL(create); //creamos la tabla
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_AFRICA); //borramos la tabla
    }

    public void resetearBd() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_AFRICA); //borramos la tabla
        onCreate(db); //volvemos a crearla
    }

    public void insertaPaises() {
        HashMap<String, String> mapa = new HashMap<>();
        mapa.put("Angola", "Luanda");
        mapa.put("Argelia", "Argel");
        mapa.put("Benín", "Porto Novo");
        mapa.put("Botsuana", "Gaborone");
        mapa.put("Burkina Faso", "Uagadugú");
        mapa.put("Burundi", "Buyumbura");
        mapa.put("Cabo Verde", "Praia");
        mapa.put("Camerún", "Yaundé");
        mapa.put("Chad", "Yamena");
        mapa.put("Comoras", "Moroni");
        mapa.put("Congo", "Brazzaville");
        mapa.put("Costa de Marfil", "Yamusukro");/*
        mapa.put("Egipto", "El Cairo");
        mapa.put("Eritrea", "Asmara");
        mapa.put("Esuatini", "Mbabane");
        mapa.put("Etiopía", "Adís Abeba");
        mapa.put("Gabón", "Libreville");
        mapa.put("Gambia", "Banjul");
        mapa.put("Ghana", "Acra");
        mapa.put("Guinea", "Conakry");
        mapa.put("Guinea Bissau", "Bissau");
        mapa.put("Guinea Ecuatorial", "Malabo");
        mapa.put("Kenya", "Nairobi");
        mapa.put("Lesoto", "Maseru");
        mapa.put("Liberia", "Monrovia");
        mapa.put("Libia", "Trípoli");
        mapa.put("Madagascar", "Antananarivo");
        mapa.put("Malauí", "Lilongüe");
        mapa.put("Malí", "Bamako");
        mapa.put("Marruecos", "Rabat");
        mapa.put("Mauricio", "Port Louis");
        mapa.put("Mauritania", "Nuakchot");
        mapa.put("Mozambique", "Maputo");
        mapa.put("Namibia", "Windhoek");
        mapa.put("Níger", "Niamey");
        mapa.put("Nigeria", "Abuya");
        mapa.put("República Centroafricana", "Bangui");
        mapa.put("República Democrática del Congo", "Kinshasa");
        mapa.put("Ruanda", "Kigali");
        mapa.put("Santo Tomé y Príncipe", "Santo Tomé");
        mapa.put("Senegal", "Dakar");
        mapa.put("Seychelles", "Victoria");
        mapa.put("Sierra Leona", "Freetown");
        mapa.put("Somalia", "Mogadiscio");
        mapa.put("Sudáfrica", "Ciudad del Cabo");
        mapa.put("Sudán", "Jartum");
        mapa.put("Sudán del Sur", "Yuba");
        mapa.put("Tanzania", "Dodoma");
        mapa.put("Togo", "Lomé");
        mapa.put("Túnez", "Túnez");
        mapa.put("Uganda", "Kampala");
        mapa.put("Yibuti", "Yibuti");
        mapa.put("Zambia", "Lusaka");
        mapa.put("Zimbabue", "Harare");*/
        SQLiteDatabase db = this.getWritableDatabase(); //abrimos la bd
        ContentValues valores = new ContentValues(); //creamos el grupo de datos
        int i = 1;
        for (HashMap.Entry<String, String> entry : mapa.entrySet()) { //nos recorremos el mapa
            valores.put(ID_PAIS, i); //colocamos los datos
            valores.put(KEY_PAIS, entry.getKey());
            valores.put(KEY_CAPITAL, entry.getValue());
            db.insert(TABLA_AFRICA, null, valores); //los insertamos
            i++;
        }
        db.close(); //cerramos la conexión con la bd
    }

    public HashMap<String, String> consultarPaises() {
        SQLiteDatabase db = this.getReadableDatabase(); //abrimos la bd
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLA_AFRICA, null); //consultamos la información
        HashMap mapa = new HashMap<String, String>();
        while (cursor.moveToNext()) { //nos recorremos el resultado
            mapa.put(cursor.getString(1), cursor.getString(2)); //vamos añadiendo al mapa
        }
        return mapa;
    }
}
