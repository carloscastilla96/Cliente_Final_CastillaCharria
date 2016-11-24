package com.example.estudiante.cliente_final_castillacharria;

import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import devadvance.circularseekbar.CircularSeekBar;
import devadvance.circularseekbar.CircularSeekBar.OnCircularSeekBarChangeListener;

public class NatAst extends AppCompatActivity {
    // clase para los intrumentos natural y astral
    TextView nota;
    int progreso;
    CircularSeekBar seekbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natast);
        nota = (TextView) findViewById(R.id.nota);
        seekbar = (CircularSeekBar) findViewById(R.id.circularSeekBar1);
        seekbar.setOnSeekBarChangeListener(new CircleSeekBarListener());
    }

    public class CircleSeekBarListener implements OnCircularSeekBarChangeListener {
        @Override
        public void onProgressChanged(CircularSeekBar circularSeekBar, int progress, boolean fromUser) {

            refrescar(progress); //para enviar datos al servidor. cada vez que se acciona la barra circular se cambian los datos


        }

        @Override
        public void onStopTrackingTouch(CircularSeekBar seekBar) {

        }

        @Override
        public void onStartTrackingTouch(CircularSeekBar seekBar) {
            // nota.setText(seekBar.getProgress());//NO FUNCIONA, POR ALGUNA RAZÓN DA ERROR!!!
        }
    }

    public void refrescar(final int pro) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println(pro);
            }
        });
        t.start();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });

    }
}
