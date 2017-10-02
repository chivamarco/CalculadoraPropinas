package com.example.extermination.obtenpropina;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.text.SimpleDateFormat;


public class MainActivity extends AppCompatActivity {
    EditText porcentaje;
    EditText cantidad;
    Double valor;
    Double propina;
    String datarow;
    Long timeStamp;
    Date fecha;
    SimpleDateFormat sdf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Seteo de fragment
        Lista listcontainer = new Lista();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.contenedorfrag, listcontainer).commit();

        //Seteo de botones y controles
        porcentaje = (EditText)findViewById(R.id.porcentaje);
        cantidad = (EditText)findViewById(R.id.valor);
        final Button limpiarbtn = (Button)findViewById(R.id.clearlist);
       limpiarbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Lista.limpiarLista();
            }
        });
        final Button calcularbtn = (Button)findViewById(R.id.calcpropina);
        calcularbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                datarow = "Propina en: " + getTimeStamp() + ": $" + calcularProp();
                Lista.agregarProp(datarow);
                limpiarCampos();
            }
        });
        final Button suma = (Button)findViewById(R.id.mas);
        suma.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                masprop();
            }
        });
        final Button resta = (Button)findViewById(R.id.menos);
        resta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                menosprop();
            }
        });
    }

    public String getTimeStamp(){
        timeStamp = System.currentTimeMillis();
        sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        fecha = new Date(timeStamp);
        return sdf.format(fecha);
    }
    public String calcularProp(){
        validardatos();
            propina = convanum(cantidad.getText().toString());
            propina = (propina * convanum(porcentaje.getText().toString())) / 100;

        return convastr(propina);
    }
    public double convanum(String data){
        if (data==""){
            data = "10";
        }
        double num = 0;
        num = Double.parseDouble(data);
        return num;
    }

    public String convastr(Double num){
        String data = "";
        data = String.valueOf(num);
        return data;
    }
    public void menosprop(){
        if (porcentaje.getText().toString()==""){
            porcentaje.setText("10");
        }
        try{
            valor = convanum(porcentaje.getText().toString());
            valor --;
            porcentaje.setText(convastr(valor));
        } catch (Exception e){
            porcentaje.setText("10");
        }

    }
    public void masprop(){
        if (porcentaje.getText().toString()==""){
            porcentaje.setText("10");
        }
        if (cantidad.getText().toString()==""){
            cantidad.setText("100");
        }
        try{
            valor = convanum(porcentaje.getText().toString());
            valor ++;
            porcentaje.setText(convastr(valor));
        } catch (Exception e){
            porcentaje.setText("10");
        }
    }
    public void validardatos(){
        try {
            if (porcentaje.getText().toString() == "") {
                porcentaje.setText("10");
            }
            if (cantidad.getText().toString() == "") {
                Toast.makeText(this, "Ingrese una cantidad.", Toast.LENGTH_SHORT).show();
            }
            try {
                propina = convanum(porcentaje.getText().toString());
                propina = convanum(cantidad.getText().toString());
            } catch (Exception e) {
                Toast.makeText(this, "Revisa los datos ingresados.", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this, "Eror.", Toast.LENGTH_SHORT).show();
        }
    }
    public void limpiarCampos(){
        cantidad.setText("");
        porcentaje.setText("");
    }
}
