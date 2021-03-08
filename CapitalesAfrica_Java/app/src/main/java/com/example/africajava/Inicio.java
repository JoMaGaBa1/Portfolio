package com.example.africajava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        InicializaBD bdd = new InicializaBD(this); //creamos la bd
        Button btnJugar = findViewById(R.id.btnJugar);
        Button btnSalir = findViewById(R.id.btnSalir);
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bdd.resetearBd(); //limpiamos la bd
                bdd.insertaPaises(); //insertamos los países
                Intent i = new Intent(Inicio.this, MainActivity.class); //creamos la nueva actividad
                startActivity(i); //arrancamos la nueva actividad
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity(); //cerramos la app
            }
        });
    }
}