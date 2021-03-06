package com.example.multiplyforkids;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Multiplicacion extends AppCompatActivity implements View.OnClickListener {

    TextView numero1,numero2,numero3, respuesta1,respuesta2,respuesta3,txtCorrectos,txtIncorrectos;
    ImageView imagen1,imagen2,imagen3;
    Button btnRegresar;

    public int numeroRespuesta = 0;
    public int var_iterator = 0,correctos = 0,incorrectos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplicacion);
        numero1 = (TextView)findViewById(R.id.textoNumero1);
        numero2 = (TextView)findViewById(R.id.textoNumero2);
        respuesta1 = (TextView)findViewById(R.id.respuesta1);
        respuesta2 = (TextView)findViewById(R.id.respuesta2);
        respuesta3 = (TextView)findViewById(R.id.respuesta3);
        imagen1 = (ImageView)findViewById(R.id.tuimageview5);
        imagen2 = (ImageView)findViewById(R.id.tuimageview6);
        imagen3 = (ImageView)findViewById(R.id.tuimageview7);
        txtCorrectos = (TextView)findViewById(R.id.txtAciertos);
        txtIncorrectos = (TextView)findViewById(R.id.txtError);
        btnRegresar = (Button)findViewById(R.id.buttonRegresar);
        imagen1.setOnClickListener(this);
        imagen2.setOnClickListener(this);
        imagen3.setOnClickListener(this);
        btnRegresar.setOnClickListener(this);
        try {
            generarNumeros();
        }catch(Exception er){
            Toast.makeText(this,"ERROR " + er.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void generarNumeros(){
        if(var_iterator == 10){
            alta();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Has concluido con tus ejercicios con un puntaje de: " + correctos + "\n ¿Que Deseas hacer?")
                    .setTitle("Fin Problemas");
            builder.setPositiveButton("Reiniciar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button

                    correctos = 0 ;
                    incorrectos = 0 ;
                    var_iterator = 0 ;
                    txtIncorrectos.setText("Incorrectos: " + incorrectos);
                    txtCorrectos.setText("Aciertos: " + correctos);
                    generarNumeros();
                }
            });
            builder.setNegativeButton("Terminar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finish();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {
            Random rd;
            int numeroGenerado = 0;
            int numeroGenerado2 = 0;
            numeroGenerado = (int) (Math.random() * 10) + 1;
            numeroGenerado2 = (int) (Math.random() * 10) + 1;
            numeroRespuesta = numeroGenerado * numeroGenerado2;
            numero1.setText(numeroGenerado + "");
            numero2.setText(numeroGenerado2 + "");
            //en esta parte aremos un pequeño seteo de valores aleatorios a las
            //etiquetas que contienen las respuestas
            numeroGenerado = (int) (Math.random() * 3) + 1;
            if (numeroGenerado == 1) {
                respuesta1.setText(numeroRespuesta + "");
                respuesta2.setText((numeroRespuesta + 2) + "");
                respuesta3.setText((numeroRespuesta - 2) + "");
            } else if (numeroGenerado == 2) {
                respuesta2.setText(numeroRespuesta + "");
                respuesta3.setText((numeroRespuesta + 2) + "");
                respuesta1.setText((numeroRespuesta - 2) + "");
            } else {
                respuesta3.setText(numeroRespuesta + "");
                respuesta1.setText((numeroRespuesta + 2) + "");
                respuesta2.setText((numeroRespuesta - 2) + "");
            }
            var_iterator++;
        }
    }

    public void validarRespuesta(int botonOrigen){
        int miRespuesta = 0 ;
        if(botonOrigen == 1){
            miRespuesta = Integer.parseInt(respuesta1.getText().toString());
        }
        else if(botonOrigen == 2){
            miRespuesta = Integer.parseInt(respuesta2.getText().toString());
        }
        else{
            miRespuesta = Integer.parseInt(respuesta3.getText().toString());
        }

        if(miRespuesta == numeroRespuesta){
            Toast.makeText(this,"Has Respondido Correctamente",Toast.LENGTH_LONG).show();
            correctos++;
        }
        else{
            Toast.makeText(this,"Respuesta Incorrecta",Toast.LENGTH_LONG).show();
            incorrectos++;
        }
        txtIncorrectos.setText("Incorrectos: " + incorrectos);
        txtCorrectos.setText("Aciertos: " + correctos);
        generarNumeros();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tuimageview5){
            validarRespuesta(1);
        }
        if(v.getId() == R.id.tuimageview6){
            validarRespuesta(2);
        }
        if(v.getId() == R.id.tuimageview7){
            validarRespuesta(3);
        }
        if(v.getId() == R.id.buttonRegresar){
            finish();
        }
    }

    public void alta() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("codigoModalidad", 2);
        registro.put("descripcion", "Multiplicacion");
        registro.put("puntaje", correctos);
        bd.insert("puntajes", null, registro);
        bd.close();
        Toast.makeText(this, "Se Guardaron los resultados de la prueba",
                Toast.LENGTH_SHORT).show();
    }
}
