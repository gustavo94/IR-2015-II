package com.example.usuario.practica1;

import android.os.AsyncTask;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.provider.ContactsContract;
import android.text.InputType;
import android.util.Log;
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
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

public class IrlActivity extends Activity {
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
    public TCPClient mTcpClient;
    public String SERVERIP;
    private String messaget;
    public TextView estado;
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

        estado=(TextView)findViewById(R.id.estado);
        estado.setText("En espera...");
        final SeekBar sbmu = (SeekBar)findViewById(R.id.sbmu);
        final SeekBar sbma = (SeekBar)findViewById(R.id.sbma);
        SeekBar sbbr = (SeekBar)findViewById(R.id.sbbr);
        SeekBar sbco = (SeekBar)findViewById(R.id.sbco);
        SeekBar sbho = (SeekBar)findViewById(R.id.sbho);
        final ToggleButton conexion=(ToggleButton)findViewById(R.id.btnConectar);
        final EditText ip=(EditText)findViewById(R.id.txtDirec);
        sbmu.setMax(100);
        sbma.setMax(100);
        sbbr.setMax(100);
        sbco.setMax(100);
        sbho.setMax(100);

        conexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(conexion.isChecked()) {
                    new connectTask().execute("");
                    SERVERIP = ip.getText().toString();
                    estado.setText("Conectado");
                }
                else{
                    try {
                        estado.setText(mTcpClient.stopClient());
                    }catch(Throwable e)
                    {
                        e.printStackTrace();
                    }
                }
 //               if (conexion.isChecked()) {
 //                   if(ip.getText().toString().equals("")) estado.setText("Ingrese una ip");
 //                   else estado.setText("Conectando a: " + ip.getText());
 //               } else {
  //                  estado.setText("En espera...");
  //              }
            }
        });

        sbmu.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gmu = progress;
                mensaje();
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
                mensaje();
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
                mensaje();
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
                mensaje();
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
                mensaje();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }



    public class connectTask extends AsyncTask<String,String,TCPClient>{
        @Override
        protected TCPClient doInBackground(String... message) {
            mTcpClient = new TCPClient(new TCPClient.OnMessageReceived(){
                public void messageReceived(String message){
                    publishProgress(message);
                }
            });
            mTcpClient.IP(SERVERIP);
            mTcpClient.run();
            return null;

        }
    }

    public void mensajeGmu(){
        gradosgmu.setText(gmu+"°");
    }
    public void mensajeGma(){
        gradosgma.setText(gma+"°");
    }
    public void mensajeGbra(){
        gradosgbr.setText(gbr+"°");
    }
    public void mensajeGco(){
        gradosgco.setText(gco+"°");
    }
    public void mensajeGho(){
        gradosgho.setText(gho+"°");
    }

    public void mensaje(){
        gradosgmu.setText(gmu+"°");
        gradosgma.setText(gma+"°");
        gradosgbr.setText(gbr+"°");
        gradosgco.setText(gco+"°");
        gradosgho.setText(gho+"°");

        byte[] b = {(byte) 126, (byte) gmu, (byte) gma,(byte) gbr, (byte) gco, (byte) gho};
        String s="";
        try{
            s=new String(b,"UTF-8");
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        System.out.println(s);
        try {
            System.out.write(b);
        } catch (IOException e){
            e.printStackTrace();
        }
        if(mTcpClient != null){
            mTcpClient.sendMessage(s);
            estado.setText(mTcpClient.conection());

//        byte[] b = {(byte)}
//        if(mTcpClient!= null){nnn
//           mTcpClient.sendMessage(gradosgmu.getText().toString()+"°"+gradosgma.getText().toString()+"°"+gradosgbr.getText().toString()+"°"+gradosgco.getText().toString()+"°"+gradosgho.getText().toString()+"°");

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_irl, menu);
        Log.d("confirmacion","confirmacion");
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
