package com.example.conteo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skydoves.elasticviews.ElasticImageView;

public class MainActivity extends AppCompatActivity {

    RelativeLayout fondo;
    CardView mensaje;
    Button salir;
    ImageView boton,boton2,cancelar;
    TextView caja;
    int c=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton=(ImageView)findViewById(R.id.boton);
        boton2=(ImageView)findViewById(R.id.boton2);
        caja=(TextView)findViewById(R.id.caja);
        fondo = (RelativeLayout)findViewById(R.id.fondo);
        mensaje = (CardView)findViewById(R.id.cardView);
        cancelar = (ImageView)findViewById(R.id.cancelar);
        salir = (Button)findViewById(R.id.salir);


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
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocultarMensaje();
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        if(fondo.getVisibility() == View.VISIBLE){
            ocultarMensaje();
        }else{
            mostrarMensaje();
        }
    }


    private void mostrarMensaje(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        fondo.startAnimation(animation);
        fondo.setVisibility(View.VISIBLE);

        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.slide_up);
        mensaje.startAnimation(animation2);
        mensaje.setVisibility(View.VISIBLE);
    }

    private void ocultarMensaje(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.fade_out);
        fondo.startAnimation(animation);
        fondo.setVisibility(View.INVISIBLE);

        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.slide_down);
        mensaje.startAnimation(animation2);
        mensaje.setVisibility(View.INVISIBLE);
    }
}