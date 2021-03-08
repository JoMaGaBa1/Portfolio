package com.example.africajava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Resultados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        TextView txtTotal = findViewById(R.id.txtTotal); //buscamos las etiquetas
        TextView txtAciertos = findViewById(R.id.txtAciertos);
        TextView txtFallos = findViewById(R.id.txtFallos);
        Button btnInicio = findViewById(R.id.btnInicio);
        txtTotal.setText("Total de países: " + getIntent().getIntExtra("total", 0)); //colocamos el texto en las etiquetas
        txtAciertos.setText("Aciertos: " + getIntent().getIntExtra("aciertos", 0));
        txtFallos.setText("Fallos: " + getIntent().getIntExtra("fallos", 0));
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiaInicio(); //volvemos al inicio
            }
        });
    }

    @Override
    public void onBackPressed() {
        cambiaInicio(); //volvemos al inicio
    }

    private void cambiaInicio() {
        Intent i = new Intent(Resultados.this, Inicio.class); //creamos la nueva actividad
        startActivity(i); //arrancamos la nueva actividad
    }
}