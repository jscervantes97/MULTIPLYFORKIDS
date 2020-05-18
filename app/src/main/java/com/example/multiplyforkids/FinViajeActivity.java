package com.example.multiplyforkids;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

public class FinViajeActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtEjercicios,respuesta1,respuesta2,respuesta3,txtCorrectos,txtIncorrectos;
    ImageView imagen1,imagen2,imagen3;
    Button btnRegresar;
    public int var_iterator= 0, correctos = 0, incorrectos = 0;
    public String respuesta = "";
    Stack<Integer> pilaEjercicios ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_viaje);
        btnRegresar = (Button)findViewById(R.id.buttonRegresar);
        imagen1 = (ImageView)findViewById(R.id.tuimageview);
        imagen2 = (ImageView)findViewById(R.id.tuimageview2);
        imagen3 = (ImageView)findViewById(R.id.tuimageview3);
        respuesta1 = (TextView)findViewById(R.id.respuesta1);
        respuesta2 = (TextView)findViewById(R.id.respuesta2);
        respuesta3 = (TextView)findViewById(R.id.respuesta3);
        txtEjercicios = (TextView)findViewById(R.id.textoEjercicio);
        txtCorrectos = (TextView)findViewById(R.id.txtAciertos);
        txtIncorrectos = (TextView)findViewById(R.id.txtError);
        btnRegresar.setOnClickListener(this);
        imagen1.setOnClickListener(this);
        imagen2.setOnClickListener(this);
        imagen3.setOnClickListener(this);
        pilaEjercicios = new Stack<>();
        for(int j = 0 ; j < 13 ;j++ ){
            pilaEjercicios.add(j);
        }
        try{
            Collections.shuffle(pilaEjercicios);
            //var_iterator = pilaEjercicios.peek();
            generarProblemas();
        }catch (Exception err){
            Toast.makeText(this,"Error en: " + err.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void generarProblemas(){
        if(pilaEjercicios.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Has concluido con tus ejercicios con un puntaje de: " + correctos + "\n ¿Que Deseas hacer?")
                    .setTitle("Viaje Terminado");
            builder.setPositiveButton("Reiniciar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    for(int j = 0 ; j < 13 ;j++ ){
                        pilaEjercicios.add(j);
                    }
                    correctos = 0 ;
                    incorrectos = 0 ;
                    txtIncorrectos.setText("Incorrectos: " + incorrectos);
                    txtCorrectos.setText("Aciertos: " + correctos);
                    generarProblemas();
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
        else{
            var_iterator = pilaEjercicios.pop();
            Log.d("Var iter",""+var_iterator);
            Log.d("Pila Ejercicios",""+pilaEjercicios);
            int numero1 = 0 ;
            int numero2 = 0 ;
            String cadenaRespuesta1 = "" ;
            String cadenaRespuesta2 = "" ;
            String cadenaRespuesta3 = "" ;
            ArrayList<String> bancoRespuestas = new ArrayList<>();
            //int numeroGenerado = 0;
            //numeroGenerado = (int) (Math.random() * 10) + 1;
            respuesta3.setTextColor(Color.BLACK);
            numero1 = (int) (Math.random() * 10) + 1 ;
            numero2 = (int) (Math.random() * 10) + 1 ;
            switch (var_iterator){
                case 0 :
                case 3 :
                    imagen1.setImageResource(R.drawable.cometa);
                    imagen2.setImageResource(R.drawable.cometa);
                    imagen3.setImageResource(R.drawable.cometa);
                    cadenaRespuesta1 = "" + numero1 ;
                    for(int j = 0 ; j < numero2 - 1; j++){
                        cadenaRespuesta1 += " + " + numero1;
                    }
                    cadenaRespuesta2 = "" + numero1 ;
                    for(int j = 0 ; j < numero2 - 2; j++){
                        cadenaRespuesta2 += " + " + numero1;
                    }
                    cadenaRespuesta3 = "" + numero1 ;
                    for(int j = 0 ; j < numero2 + 2; j++){
                        cadenaRespuesta3 += " + " + numero1;
                    }
                    bancoRespuestas.add(cadenaRespuesta1);
                    bancoRespuestas.add(cadenaRespuesta2);
                    bancoRespuestas.add(cadenaRespuesta3);
                    Collections.shuffle(bancoRespuestas);
                    respuesta = cadenaRespuesta1 ;
                    txtEjercicios.setText("Elige la suma que expresa lo mismo que: " + numero1 + " x " + numero2);
                    respuesta1.setText(bancoRespuestas.get(0).toString());
                    respuesta2.setText(bancoRespuestas.get(1).toString());
                    respuesta3.setText(bancoRespuestas.get(2).toString());
                    break ;
                case 1:
                case 4:
                    imagen1.setImageResource(R.drawable.cometa);
                    imagen2.setImageResource(R.drawable.cometa);
                    imagen3.setImageResource(R.drawable.cometa);
                    cadenaRespuesta1 = "" + numero1 ;
                    for(int j = 0 ; j < numero2 - 1; j++){
                        cadenaRespuesta1 += " + " + numero1;
                    }
                    String aux = numero1 + " x " + numero2 ;
                    cadenaRespuesta2 = (numero1 + 1) + " x " + (numero2+ 1) ;
                    cadenaRespuesta3 = (numero1 + 2) + " x " + (numero2+ 2) ;
                    bancoRespuestas.add(aux);
                    bancoRespuestas.add(cadenaRespuesta2);
                    bancoRespuestas.add(cadenaRespuesta3);
                    Collections.shuffle(bancoRespuestas);
                    respuesta = aux ;
                    txtEjercicios.setText("Escribe en forma de multiplicación la siguiente serie numérica: " + cadenaRespuesta1);
                    respuesta1.setText(bancoRespuestas.get(0).toString());
                    respuesta2.setText(bancoRespuestas.get(1).toString());
                    respuesta3.setText(bancoRespuestas.get(2).toString());
                    break;
                case 2:
                    imagen1.setImageResource(R.drawable.lata);
                    imagen2.setImageResource(R.drawable.lata);
                    imagen3.setImageResource(R.drawable.lata);
                    cadenaRespuesta1 = ((numero1 + 0) * (numero2+ 0))+"";
                    cadenaRespuesta2 = ((numero1 + 1) * (numero2+ 1))+"";
                    cadenaRespuesta3 = ((numero1 + 2) * (numero2+ 2))+"";
                    bancoRespuestas.add(cadenaRespuesta1);
                    bancoRespuestas.add(cadenaRespuesta2);
                    bancoRespuestas.add(cadenaRespuesta3);
                    Collections.shuffle(bancoRespuestas);
                    respuesta = cadenaRespuesta1 ;
                    txtEjercicios.setText("Una señora compró "+numero1+" paquetes con "+numero2+" sodas cada uno, para llevar a una fiesta, ¿Cuántas sodas llevará a la fiesta?");
                    respuesta1.setText(bancoRespuestas.get(0).toString());
                    respuesta2.setText(bancoRespuestas.get(1).toString());
                    respuesta3.setText(bancoRespuestas.get(2).toString());
                    break;
                case 5:
                    imagen1.setImageResource(R.drawable.astronauta);
                    imagen2.setImageResource(R.drawable.astronauta);
                    imagen3.setImageResource(R.drawable.astronauta);
                    cadenaRespuesta1 = ((numero1 + 0) * (7))+"";
                    cadenaRespuesta2 = ((numero1 + 1) * (7))+"";
                    cadenaRespuesta3 = ((numero1 + 2) * (7))+"";
                    bancoRespuestas.add(cadenaRespuesta1 + " dias");
                    bancoRespuestas.add(cadenaRespuesta2 + " dias");
                    bancoRespuestas.add(cadenaRespuesta3 + " dias");
                    Collections.shuffle(bancoRespuestas);
                    respuesta = cadenaRespuesta1 + " dias" ;
                    txtEjercicios.setText("Dentro de "+numero1+" semanas un astronauta que está en un viaje espacial regresara a la tierra. ¿cuántos días tiene que esperar?");
                    respuesta1.setText(bancoRespuestas.get(0).toString());
                    respuesta2.setText(bancoRespuestas.get(1).toString());
                    respuesta3.setText(bancoRespuestas.get(2).toString());
                    break;
                case 6:
                    imagen1.setImageResource(R.drawable.astronauta);
                    imagen2.setImageResource(R.drawable.astronauta);
                    imagen3.setImageResource(R.drawable.astronauta);
                    numero1 = (int) (Math.random() * 10) + 1 ;
                    numero2 = (int) (Math.random() * 10) + 1 ;
                    cadenaRespuesta1 = "" + numero1 ;
                    for(int j = 0 ; j < numero2 - 1; j++){
                        cadenaRespuesta1 += " + " + numero1;
                    }
                    cadenaRespuesta2 = "" + numero1 ;
                    for(int j = 0 ; j < numero2 + 2; j++){
                        cadenaRespuesta2 += " + " + numero1;
                    }
                    cadenaRespuesta3 = "" + numero1 ;
                    for(int j = 0 ; j < numero2 + 1; j++){
                        cadenaRespuesta3 += " + " + numero1;
                    }
                    bancoRespuestas.add(cadenaRespuesta1);
                    bancoRespuestas.add(cadenaRespuesta2);
                    bancoRespuestas.add(cadenaRespuesta3);
                    Collections.shuffle(bancoRespuestas);
                    respuesta = cadenaRespuesta1 ;
                    txtEjercicios.setText("Si van "+ (numero1*numero2) +" Astronautas en la nave espacial y al abandonar la nave son expulsados de "+ numero1 +" en "+numero1 +" hasta " +
                            "que no haya pasajeros.\n" +
                            "¿Como seria esto en Sucesión numérica?");
                    respuesta1.setText(bancoRespuestas.get(0).toString());
                    respuesta2.setText(bancoRespuestas.get(1).toString());
                    respuesta3.setText(bancoRespuestas.get(2).toString());
                    break;
                case 7:
                    imagen1.setImageResource(R.drawable.jupiter);
                    imagen2.setImageResource(R.drawable.jupiter);
                    imagen3.setImageResource(R.drawable.jupiter);
                    cadenaRespuesta1 = ((numero1 + 0) * (numero2))+"";
                    cadenaRespuesta2 = ((numero1 + 1) * (numero2 + 1))+"";
                    cadenaRespuesta3 = ((numero1 + 2) * (numero2 + 2))+"";
                    bancoRespuestas.add(cadenaRespuesta1 + " stickers");
                    bancoRespuestas.add(cadenaRespuesta2 + " stickers");
                    bancoRespuestas.add(cadenaRespuesta3 + " stickers");
                    Collections.shuffle(bancoRespuestas);
                    respuesta = cadenaRespuesta1 + " stickers" ;
                    txtEjercicios.setText("Un paquete contiene "+ numero1 +" stickers de los planetas ¿Cuantos stickers tendré comprando " + numero2 + " " +
                            "paquetes?");
                    respuesta1.setText(bancoRespuestas.get(0).toString());
                    respuesta2.setText(bancoRespuestas.get(1).toString());
                    respuesta3.setText(bancoRespuestas.get(2).toString());
                    break;
                case 8:
                    imagen1.setImageResource(R.drawable.estrellita);
                    imagen2.setImageResource(R.drawable.estrellita);
                    imagen3.setImageResource(R.drawable.estrellita);
                    cadenaRespuesta1 = ((numero1 + 0) * (11))+"";
                    cadenaRespuesta2 = ((numero1 +1) * (11 ))+"";
                    cadenaRespuesta3 = ((numero1 - 1) * (11 ))+"";
                    bancoRespuestas.add(cadenaRespuesta1 + " estrellas");
                    bancoRespuestas.add(cadenaRespuesta2 + " estrellas");
                    bancoRespuestas.add(cadenaRespuesta3 + " estrellas");
                    Collections.shuffle(bancoRespuestas);
                    respuesta = cadenaRespuesta1 + " estrellas" ;
                    txtEjercicios.setText("Por cada meteorito hay 11 estrellas fugaces ¿Cuántas estrellas fugaces habrá en "+ numero1 +" meteoritos?");
                    respuesta1.setText(bancoRespuestas.get(0).toString());
                    respuesta2.setText(bancoRespuestas.get(1).toString());
                    respuesta3.setText(bancoRespuestas.get(2).toString());
                    break;
                case 9:
                    imagen1.setImageResource(R.drawable.ovni3);
                    imagen2.setImageResource(R.drawable.ovni3);
                    imagen3.setImageResource(R.drawable.ovni3);
                    bancoRespuestas.add((numero2) + " veces");
                    bancoRespuestas.add((numero2 + 1) + " veces");
                    bancoRespuestas.add((numero2 - 1)+ " veces");
                    Collections.shuffle(bancoRespuestas);
                    respuesta = numero2 + " veces" ;
                    txtEjercicios.setText("Si un ovni tarda "+ numero1+" días en ir de la tierra a marte, ¿Cuántas veces iría de la tierra a " +
                            "marte en "+(numero1*numero2)+" días?");
                    respuesta1.setText(bancoRespuestas.get(0).toString());
                    respuesta2.setText(bancoRespuestas.get(1).toString());
                    respuesta3.setText(bancoRespuestas.get(2).toString());
                    break;
                case 10:
                    imagen1.setImageResource(R.drawable.lemon);
                    imagen2.setImageResource(R.drawable.lemon);
                    imagen3.setImageResource(R.drawable.lemon);
                    bancoRespuestas.add((numero1 * numero2) + " limones");
                    bancoRespuestas.add((numero1 * numero2+ 1) + " limones");
                    bancoRespuestas.add((numero1 * numero2 - 1)+ " limones");
                    Collections.shuffle(bancoRespuestas);
                    respuesta = (numero2 * numero1) + " limones" ;
                    txtEjercicios.setText("En el parque intergaláctico de la luna hay "+(numero1)+" árboles que proveen el oxígeno. Si cada árbol tiene " +
                            " "+ numero2 +" limones intergalácticas ¿Cuántas limones hay en total por todos los árboles?");
                    respuesta1.setText(bancoRespuestas.get(0).toString());
                    respuesta2.setText(bancoRespuestas.get(1).toString());
                    respuesta3.setText(bancoRespuestas.get(2).toString());
                    break;
                case 11:
                    imagen1.setImageResource(R.drawable.moneda);
                    imagen2.setImageResource(R.drawable.moneda);
                    imagen3.setImageResource(R.drawable.moneda);
                    bancoRespuestas.add("$" + (numero1 * numero2));
                    bancoRespuestas.add("$" + (numero1 * numero2 + 1));
                    bancoRespuestas.add("$" + (numero1 * numero2 - 1));
                    Collections.shuffle(bancoRespuestas);
                    respuesta = "$" + (numero1 * numero2);
                    txtEjercicios.setText("En la estación espacial se llena la zona de oxígeno todos los días. Si cada día que se llena el " +
                            "oxígeno cuesta $"+ numero1 +" Mush (Moneda de Marte).\n" +
                            "¿Cuánto se gastará por " +(numero2)+ " semanas en la estación espacial?");
                    respuesta1.setText(bancoRespuestas.get(0).toString());
                    respuesta2.setText(bancoRespuestas.get(1).toString());
                    respuesta3.setText(bancoRespuestas.get(2).toString());
                    break;
                case 12:
                    ArrayList<String> semana = new ArrayList<>();
                    semana.add("Lunes") ;
                    semana.add("Martes") ;
                    semana.add("Miercoles") ;
                    semana.add("Jueves") ;
                    semana.add("Viernes") ;
                    semana.add("Sabado") ;
                    semana.add("Domingo") ;
                    imagen1.setImageResource(R.drawable.moneda);
                    imagen2.setImageResource(R.drawable.moneda);
                    imagen3.setImageResource(R.drawable.moneda);
                    bancoRespuestas.add("$" + (numero1 * numero2));
                    bancoRespuestas.add("$" + (numero1 * numero2 + 1));
                    bancoRespuestas.add("$" + (numero1 * numero2 - 1));
                    Collections.shuffle(bancoRespuestas);
                    respuesta = "$" + (numero1 * numero2);
                    txtEjercicios.setText("Mi Papá " +
                            "me da $"+numero1+" pesos cada semana \n" +
                            "Si ahorro el dinero que me da cada semana. ¿Cuánto dinero juntare al paso de "+numero2+" semanas?");
                    respuesta1.setText(bancoRespuestas.get(0).toString());
                    respuesta2.setText(bancoRespuestas.get(1).toString());
                    respuesta3.setText(bancoRespuestas.get(2).toString());
                    break;

            }
        }
    }

    public void validarRespuesta(int botonOrigen){
        String miRespuesta = "" ;
        if(botonOrigen == 1){
            miRespuesta = respuesta1.getText().toString();
        }
        else if(botonOrigen == 2){
            miRespuesta = respuesta2.getText().toString();
        }
        else{
            miRespuesta = respuesta3.getText().toString();
        }

        if(miRespuesta.equals(respuesta)){
            Toast.makeText(this,"Has Respondido Correctamente",Toast.LENGTH_LONG).show();
            correctos++;
        }
        else{
            Toast.makeText(this,"Respuesta Incorrecta",Toast.LENGTH_LONG).show();
            incorrectos++;
        }
        txtIncorrectos.setText("Incorrectos: " + incorrectos);
        txtCorrectos.setText("Aciertos: " + correctos);
        generarProblemas();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tuimageview){
            validarRespuesta(1);
        }
        if(v.getId() == R.id.tuimageview2){
            validarRespuesta(2);
        }
        if(v.getId() == R.id.tuimageview3){
            validarRespuesta(3);
        }
        if(v.getId()==R.id.buttonRegresar){
            finish();
        }
    }
}
