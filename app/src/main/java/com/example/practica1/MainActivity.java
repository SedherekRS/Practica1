package com.example.practica1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    private static final String LOGTAG="FECHA";

    EditText etName;
    EditText etLastName;
    EditText etDay;
    EditText etMonth;
    EditText etYear;

    Button btnSummit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        etLastName=findViewById(R.id.etLastName);
        etDay=findViewById(R.id.etDay);
        etMonth=findViewById(R.id.etMonth);
        etYear=findViewById(R.id.etYear);
        btnSummit=findViewById(R.id.btnSummit);

        btnSummit.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(etName.getText().length()!=0 && etLastName.getText().length()!=0 &&
                etDay.getText().length()!=0 && etMonth.getText().length()!=0 &&
                etYear.getText().length()!=0) {
            String day= etDay.getText().toString();
            String month= (etMonth.getText().toString());
            String year = etYear.getText().toString();
            if(okDate(day,month,year)){
                Intent intent = new Intent(MainActivity.this,activity_signo.class);
                Bundle parametros = new Bundle();
                parametros.putString("name",etName.getText().toString());
                parametros.putString("lastName",etLastName.getText().toString());
                parametros.putString("bDay",day+"-"+month+"-"+year);
                intent.putExtras(parametros);
                startActivity(intent);
            }
            else{
                Toast.makeText(MainActivity.this, getResources().getString(R.string.okDate), Toast.LENGTH_LONG).show();
            }

        }
        else{
            Toast.makeText(MainActivity.this, getResources().getString(R.string.voidText), Toast.LENGTH_LONG).show();
        }
    }
    private Boolean okDate(String day,String month,String year){
        boolean result=false;
        ///////////Creador de fecha
        int dayC = Integer.parseInt(day);
        int monthC = Integer.parseInt(month);
        int yearC = Integer.parseInt(year);

        /////////Verificador de fechas
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        cal.set(yearC, monthC - 1, dayC, 0, 0);
        try {
            cal.getTime();
            result=true;
        }
        catch (Exception e) {
            Log.d(LOGTAG, "Invalid date");
            result=false;
        }
        return result;
    }
}
