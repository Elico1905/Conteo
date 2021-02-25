package com.example.conteo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skydoves.elasticviews.ElasticImageView;

public class MainActivity extends AppCompatActivity {

    ImageView boton,boton2;
    TextView caja;
    int c=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton=(ImageView)findViewById(R.id.boton);
        boton2=(ImageView)findViewById(R.id.boton2);
        caja=(TextView)findViewById(R.id.caja);
        leer();
        caja.setText(c+"\ndias");
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c++;
                caja.setText(c+"\ndias");
                escribir();
            }
        });
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });
    }


    public void leer(){
        SharedPreferences preferences = getSharedPreferences("diario", Context.MODE_PRIVATE);
        c = preferences.getInt("c",0);
    }

    public void escribir(){
        SharedPreferences preferences = getSharedPreferences("diario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("c",c);
        editor.commit();
    }


    public void limpiar(){
        SharedPreferences preferences = getSharedPreferences("diario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        c = 0;
        caja.setText("0\ndias");
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}