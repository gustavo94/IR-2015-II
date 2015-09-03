package com.example.usuario.practica1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class IrlActivity extends AppCompatActivity {
    public int gmu;
    public int gma;
    public int gbr;
    public int gco;
    public int gho;
    public TextView gradosgmu;
    public TextView gradosgma;
    public TextView gradosgbr;
    public TextView gradosgco;
    public TextView gradosgho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.irl);

        //Button Conectar = (Button)findViewById(R.id.btnConectar);
        gradosgmu=(TextView)findViewById(R.id.seektext11);
        gradosgma=(TextView)findViewById(R.id.seektext22);
        gradosgbr=(TextView)findViewById(R.id.seektext33);
        gradosgco=(TextView)findViewById(R.id.seektext44);
        gradosgho=(TextView)findViewById(R.id.seektext55);

        final TextView estado=(TextView)findViewById(R.id.estado);
        estado.setText("En espera...");
        final SeekBar sbmu = (SeekBar)findViewById(R.id.sbmu);
        final SeekBar sbma = (SeekBar)findViewById(R.id.sbma);
        SeekBar sbbr = (SeekBar)findViewById(R.id.sbbr);
        SeekBar sbco = (SeekBar)findViewById(R.id.sbco);
        SeekBar sbho = (SeekBar)findViewById(R.id.sbho);
        final ToggleButton conexion=(ToggleButton)findViewById(R.id.btnConectar);
        final EditText ip=(EditText)findViewById(R.id.txtDirec);
        sbmu.setMax(180);
        sbma.setMax(180);
        sbbr.setMax(180);
        sbco.setMax(180);
        sbho.setMax(180);

        conexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conexion.isChecked()) {
                    if(ip.getText().toString().equals("")) estado.setText("Ingrese una ip");
                    else estado.setText("Conectando a" + ip.getText());
                } else {
                    estado.setText("En espera...");
                }
            }
        });

        sbmu.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gmu = progress;
                mensajeGmu();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbma.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gma = progress;
                mensajeGma();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbbr.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gbr = progress;
                mensajeGbra();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbco.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gco = progress;
                mensajeGco();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbho.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gho = progress;
                mensajeGho();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void mensajeGmu(){
        gradosgmu.setText("Grados: "+gmu+"°");
    }
    public void mensajeGma(){
        gradosgma.setText("Grados: "+gma+"°");
    }
    public void mensajeGbra(){
        gradosgbr.setText("Grados: "+gbr+"°");
    }
    public void mensajeGco(){
        gradosgco.setText("Grados: "+gco+"°");
    }
    public void mensajeGho(){
        gradosgho.setText("Grados: "+gho+"°");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_irl, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
