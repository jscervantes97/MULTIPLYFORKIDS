package com.example.multiplyforkids;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button button2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);
    }

    public void iniciarViaje(){
        Intent i = new Intent(this, IntroduccionActivity.class );
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() ==  R.id.button2){
            iniciarViaje();
        }

    }
}
