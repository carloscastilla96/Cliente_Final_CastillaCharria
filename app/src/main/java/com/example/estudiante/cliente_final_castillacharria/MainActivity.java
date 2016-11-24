package com.example.estudiante.cliente_final_castillacharria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import programaciondmi.per.comunicacion.ComunicacionCliente;
import programaciondmi.per.comunicacion.modelo.Instrumento;


public class MainActivity extends AppCompatActivity {
    ComunicacionCliente com;
    String[] instrumentos;
    Intent i;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                com = new ComunicacionCliente();
                com.start();
                System.out.print("conectado");
                instrumentos = new String[]{"Viento", "Percusión", "Cuerda", "Electronico","Acustico","Urbano","Natural","Astral"};


            }
        }

        );
        t.start();
    }

    public void onComenzar(View v) {
        i= new Intent(this,NatAst.class);
        startActivity(i);

    }

}
