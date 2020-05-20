package com.example.multiplyforkids;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class puntajesActivity extends AppCompatActivity implements View.OnClickListener {

    TextView series, multi, finviaje;
    Button botonEliminar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntajes);
        series = (TextView) findViewById(R.id.txtPuntajesSeries);
        multi = (TextView) findViewById(R.id.Multiplicacion);
        finviaje = (TextView) findViewById(R.id.finviaje);
        imprimirResultados1();
        imprimirResultados2();
        imprimirResultados3();
        botonEliminar = (Button)findViewById(R.id.button5);
        botonEliminar.setOnClickListener(this);
    }

    private void imprimirResultados1() {
        int numtotalEjercicios = 0 ;
        int numPuntos = 0;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery(
                "select puntaje from puntajes where codigoModalidad = 1" , null);
        Log.d("Cursor" ,"" + fila);
        int iter = 0 ;
        if(fila.moveToFirst()){
            do{
                iter++ ;
                numtotalEjercicios++ ;
                numPuntos += fila.getInt(0);
                Log.d("Fila","vuelta"+ iter +"  "+fila.getInt(0));
            }while (fila.moveToNext());
        }

        bd.close();
        series.setText("Ejercicios Realizados: " + numtotalEjercicios + " Aciertos en total: " + numPuntos);
    }
    private void imprimirResultados2() {
        int numtotalEjercicios = 0 ;
        int numPuntos = 0;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery(
                "select puntaje from puntajes where codigoModalidad = 2" , null);

        int iter = 0 ;
        if(fila.moveToFirst()){
            do{
                iter++ ;
                numtotalEjercicios++ ;
                numPuntos += fila.getInt(0);
            }while (fila.moveToNext());
        }

        bd.close();
        multi.setText("Ejercicios Realizados: " + numtotalEjercicios + " Aciertos en total: " + numPuntos);
    }
    private void imprimirResultados3() {
        int numtotalEjercicios = 0 ;
        int numPuntos = 0;
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery(
                "select puntaje from puntajes where codigoModalidad = 3" , null);
        Log.d("Cursor" ,"" + fila);
        int iter = 0 ;
        if(fila.moveToFirst()){
            do{
                iter++ ;
                numtotalEjercicios++ ;
                numPuntos += fila.getInt(0);
                Log.d("Fila","vuelta"+ iter +"  "+fila.getInt(0));
            }while (fila.moveToNext());
        }

        bd.close();
        finviaje.setText("Ejercicios Realizados: " + numtotalEjercicios + " Aciertos en total: " + numPuntos);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button5){
            try{
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                        "administracion", null, 1);
                SQLiteDatabase bd = admin.getWritableDatabase();

                int cant = bd.delete("puntajes", "codigoModalidad in (1,2,3) ", null);
                bd.close();

                if (cant == 1)
                    Toast.makeText(this, "Se reestablecieron los resultados",
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Se reestablecieron los resultados",
                            Toast.LENGTH_SHORT).show();

                imprimirResultados1();
                imprimirResultados2();
                imprimirResultados3();
            }catch(Exception err){
                Toast.makeText(this,"ERROR: " + err.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }
}
