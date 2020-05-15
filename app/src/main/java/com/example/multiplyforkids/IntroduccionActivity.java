package com.example.multiplyforkids;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class IntroduccionActivity extends AppCompatActivity implements View.OnClickListener{

    Button boton1,boton2,boton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduccion);
        boton1 = (Button)findViewById(R.id.button1);
        boton2 = (Button)findViewById(R.id.button2);
        boton3 = (Button)findViewById(R.id.button3);
        boton3.setOnClickListener(this);
        boton1.setOnClickListener(this);
        boton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button1){
            Intent i = new Intent(this, SeriesNumericas.class );
            startActivity(i);
        }
        if(v.getId()==R.id.button2){
            Intent i = new Intent(this, Multiplicacion.class );
            startActivity(i);
        }
        if(v.getId()== R.id.button3){
            Intent i = new Intent(this, FinViajeActivity.class );
            startActivity(i);
        }

    }
}
