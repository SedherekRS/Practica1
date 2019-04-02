package com.example.practica1;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class activity_signo extends AppCompatActivity {
    private static final String LOGTAG="PARAMETROS";

    TextView tvCompleteName;
    TextView tvEdad;
    TextView tvSigno;
    ImageView ivSigno;

    String name="";
    String lastName="";
    String bDay="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signo);
        tvCompleteName=findViewById(R.id.tvCompleteName);
        tvEdad=findViewById(R.id.tvEdad);
        tvSigno=findViewById(R.id.tvSigno);
        ivSigno=findViewById(R.id.ivSigno);


        Bundle parametros = getIntent().getExtras();

        if(parametros!=null){
            name= parametros.getString("name","Usuario");
            lastName= parametros.getString("lastName","Usuario");
            bDay=parametros.getString("bDay");
            tvCompleteName.setText(name+" "+lastName);
            Log.d(LOGTAG, "nombre Recibido: "+name+" "+lastName);
            tvEdad.setText(edad(bDay));
            Log.d(LOGTAG, edad(bDay));
            Map<String,String> signoInfo = new HashMap<>();
            signoInfo=signo(bDay);
            tvSigno.setText("Tu signo es: "+signoInfo.get("signo"));
            Log.d(LOGTAG, signoInfo.get("signo"));
            ///Beta poner imagen custom

            //ivSigno.setImageResource(Integer.parseInt(signoInfo.get("imagen")));
            ///
        }

    }


    private String edad(String fecha){
        String result="";
        String[] data = fecha.split("-", 3);
        int day = Integer.parseInt(data[0]);
        int month = Integer.parseInt(data[1]);
        int year = Integer.parseInt(data[2]);
        //
        Calendar fechaNacimiento = new GregorianCalendar(year, month, day);
        Calendar fechaActual = Calendar.getInstance();

        //Calcula diferencias.
        int years = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int months = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int days = fechaActual.get(Calendar.DAY_OF_MONTH) - fechaNacimiento.get(Calendar.DAY_OF_MONTH);

        if(months < 0 //Aun no es mes de cumpleaños.
                || (months == 0 && days < 0)){//Es el mes pero no ha llegado el día.
            years--; //Se resta 1 a la diferencia de años.
        }
        //
        result = "Tu edad es: "+years;
        return result;
    }

    private Map<String,String> signo (String fecha){
        Map<String,String> info = new HashMap<>();
        String signo="";

        /////Fecha de Nacimiento
        String[] data = fecha.split("-", 3);
        int dia = Integer.parseInt(data[0]);
        int mes = Integer.parseInt(data[1]);
        //////////Signo zodiacal
        if (mes == 1) {
            if (dia >= 21) {
                //acuario
                signo= "acuario";
            } else {
                //capricornio
                signo= "capricornio";
            }
        }
        if (mes == 2) {
            if (dia >= 19) {
                signo= "piscis";
            } else {
                signo= "acuario";
            }
        }
        if (mes == 3) {
            if (dia >= 20) {
                //acuario
                signo= "aries";
            } else {
                //capricornio
                signo= "piscis";
            }
        }
        if (mes == 4) {
            if (dia >= 20) {
                signo= "tauro";
            } else {
                signo= "Aries";
            }
        }
        if (mes == 5) {
            if (dia >= 21) {
                //acuario
                signo= "geminis";
            } else {
                //capricornio
                signo= "tauro";
            }
        }
        if (mes == 6) {
            if (dia >= 20) {
                signo= "cancer";
            } else {
                signo= "geminis";
            }
        }
        if (mes == 7) {
            if (dia >= 22) {
                //acuario
                signo= "leo";
            } else {
                //capricornio
                signo= "cancer";
            }
        }
        if (mes == 8) {
            if (dia >= 21) {
                signo= "virgo";
            } else {
                signo= "leo";
            }
        }
        if (mes == 9) {
            if (dia >= 22) {
                //acuario
                signo= "libra";
            } else {
                //capricornio
                signo= "virgo";
            }
        }
        if (mes == 10) {
            if (dia >= 22) {
                signo= "escorpion";
            } else {
                signo= "libra";
            }
        }
        if (mes == 11) {
            if (dia >= 21) {
                //acuario
                signo= "sagitario";
            } else {
                //capricornio
                signo= "escorpion";
            }
        }
        if (mes == 12) {
            if (dia >= 21) {
                signo= "capricornio";
            } else {
                signo= "sagitario";
            }
        }

        /////////////////////////
        info.put("signo",signo);

        return info;
    }

}
