package com.example.africajava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private InicializaBD bdd = new InicializaBD(this); //nos creamos la bd
    private boolean jugando = false;
    private int aciertos = 0;
    private int fallos = 0;
    private List<String> paises = new ArrayList<>();
    private List<String> capitales = new ArrayList<>();
    private HashMap<String, String> mapa = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtPais = findViewById(R.id.txtPais); //buscamos los elementos
        RadioGroup grpBotones = findViewById(R.id.grpOpciones);
        TextView txtInfo = findViewById(R.id.txtInfo);
        ImageButton btnPrincipal = findViewById(R.id.btnPrincipal);

        rellenaDatos(); //regeneramos la información

        btnPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtInfo.setText(R.string.otro);
                if(!jugando) {
                    iniciarJuego(txtPais, grpBotones);
                    colocaNuevo(txtPais, grpBotones);
                    jugando = true;
                } else {
                    try {
                        revisaResultado(grpBotones, txtPais.getText().toString());
                        colocaNuevo(txtPais, grpBotones);
                        grpBotones.clearCheck();
                    } catch (NullPointerException e) { //si no se marca ningún radio button
                        Toast.makeText(getApplicationContext(), R.string.marcarObligatorio, Toast.LENGTH_SHORT).show();
                    } catch (IllegalArgumentException e) { //si se acaba el juego
                        jugando = false;
                        acabarJuego(txtInfo);
                    }
                }
            }
        });
    }

    private void rellenaDatos() {
        mapa = bdd.consultarPaises(); //consultamos de la bd
        paises = new ArrayList<>(); //limpiamos la información actual
        capitales = new ArrayList<>();
        for (HashMap.Entry<String, String> entry : mapa.entrySet()) { //nos recorremos el resultado
            paises.add(entry.getKey()); //añadimos a las listas correspondientes
            capitales.add(entry.getValue());
        }
    }

    private void iniciarJuego(TextView txtPais, RadioGroup grpBotones) {
        aciertos = 0; //reseteamos las variables
        fallos = 0;
        txtPais.setVisibility(View.VISIBLE); //cambiamos la visibilidad de los elementos
        grpBotones.setVisibility(View.VISIBLE);
        grpBotones.clearCheck(); //limpiamos la opción marcada
    }

    private void acabarJuego(TextView txtInfo) {
        txtInfo.setText(R.string.fin);
        Intent i = new Intent(MainActivity.this, Resultados.class); //creamos la nueva actividad
        i.putExtra("total", mapa.size()); //añadimos la información necesaria
        i.putExtra("aciertos", aciertos);
        i.putExtra("fallos", fallos);
        startActivity(i); //arrancamos la nueva actividad
    }

    private void colocaNuevo(TextView txtPais, RadioGroup grpBotones) throws IllegalArgumentException {
        int aleatorio = new Random().nextInt(paises.size());
        String pais = paises.get(aleatorio); //generamos un país aleatorio y buscamos su capital
        String capital = mapa.get(pais);
        List<String> posibles = new ArrayList<>(); //duplicamos la lista de capitales
        for (String c: capitales) {
            posibles.add(c);
        }
        posibles.remove(capital); //quitamos la capital como opción
        List<String> opciones = new ArrayList<>(); //creamos la lista de opciones con la capital correcta
        opciones.add(capital);
        for (int i = 0; i < 3; i++) {
            int aleat = new Random().nextInt(posibles.size());
            String opcion = posibles.get(aleat); //generamos opciones aletorias
            posibles.remove(opcion); //la quitamos para que no se repita
            opciones.add(opcion); //la añadimos a las opciones que aparecerán en este turno
        }
        Collections.shuffle(opciones); //barajamos las opciones
        txtPais.setText(pais); //colocamos el país en pantalla
        RadioButton btn1 = findViewById(R.id.optUno); //buscamos los botones
        RadioButton btn2 = findViewById(R.id.optDos);
        RadioButton btn3 = findViewById(R.id.optTres);
        RadioButton btn4 = findViewById(R.id.optCuatro);
        btn1.setText(opciones.get(0)); //colocamos el texto correspondiente
        btn2.setText(opciones.get(1));
        btn3.setText(opciones.get(2));
        btn4.setText(opciones.get(3));
        paises.remove(aleatorio); //quitamos el país para que no se repita
    }

    private boolean revisarCorrecta(RadioGroup grpBotones, String pais) {
        boolean devolver = false;
        int idSeleccionado = grpBotones.getCheckedRadioButtonId(); //recuperamos el botón marcado
        RadioButton seleccionado = findViewById(idSeleccionado);
        String capital = mapa.get(pais); //recuperamos la capital
        if (capital == seleccionado.getText()) { //si es correcto
            devolver = true; //cambiamos la variable a verdadera
        }
        return devolver; //devolvemos el resultado
    }

    private void revisaResultado(RadioGroup grpBotones, String pais) {
        boolean resultado = revisarCorrecta(grpBotones, pais);
        if (resultado) { //si acertamos o fallamos sumaremos a aciertos o a fallos
            aciertos++;
        } else {
            fallos++;
        }
    }
}