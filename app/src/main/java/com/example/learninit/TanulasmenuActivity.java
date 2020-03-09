package com.example.learninit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class TanulasmenuActivity extends AppCompatActivity {

    private Button visszatanulas;
    private  Button napibut;
    private  Button tanultBut;
    private SharedPreferences sharedPreferences;
    private  AlertDialog alertDialog;
    private String jelenlegiDatum="";
    private  Calendar tanulasDatuma;
    private TextView jelenlegiDatumWIEV,tanulaDatumaWiev;
    public static int szamlalo=0;
    private Boolean tanulhat=true;
    private File file;
    private String TanultDatum="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanulasmenu);

        init();

        visszatanulas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TanulasmenuActivity.this, Fomenu.class);
                startActivity(intent);
                finish();
            }
        });

        //---------------------------------------------------------------------------------------------------------------

        float het =Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("het", 0)));
        hetSharedPreference(het);
        float honap = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("honap", 0)));
        haviSharedPreference(honap);
        float ev = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("ev", 0)));
        evSharedPreference(ev);

        float hetosszes = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("hetosszes", 0)));
        hetosszesSharedPreference(hetosszes);
        float evosszes = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("evosszes", 0)));
        evosszesSharedPreference(evosszes);
        float honaposszes = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("honaposszes", 0)));
        honaposszesSharedPreference(honaposszes);
        if (hetosszes>=70){
            hetosszesSharedPreference(0);
            hetSharedPreference(0);
        }
        else if(honaposszes>=300){
            honaposszesSharedPreference(0);
            haviSharedPreference(0);
        }
        else  if(evosszes>=3650){
            evosszesSharedPreference(0);
            evosszesSharedPreference(0);
        }

        Date date = Calendar.getInstance().getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss ");
          jelenlegiDatum = dateFormat.format(date);

        try {
            file= new File(Environment.getExternalStorageDirectory().getPath(),"belepesDatum.csv");

            BufferedReader bufferedReader=new BufferedReader(new FileReader(file),1024);
            String sor;
          while ((sor=bufferedReader.readLine())!=null)
          {
              TanultDatum=sor;
          }

            Toast.makeText(TanulasmenuActivity.this, "Kiolvasas sikeres volt!", Toast.LENGTH_SHORT).show();
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        //itt kezdődik majd a tanulás

        napibut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (szamlalo==10){
                    tanulhat=false;
                }
                sharedPreference( 0);
                sharedPreference(szamlalo+1);




                file=new File(Environment.getExternalStorageDirectory(),("belepesDatum.csv"));
                try{

                    Date date = Calendar.getInstance().getTime();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss ");
                    String formatedDate = dateFormat.format(date);
                    String text =  formatedDate+ "\r\n";
                    BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file,true),1024);
                    bufferedWriter.write(String.valueOf(text));
                    Toast.makeText(TanulasmenuActivity.this, "Kiírtatás sikeres volt!", Toast.LENGTH_SHORT).show();
                    bufferedWriter.close();
                }catch (IOException e){
                    tanulasKezdete();
                    return;


                }




                //if (oraSharedPreference()

                if (TanultDatum.equals(jelenlegiDatum)){

                    tanulhat=true;
                    Toast.makeText(TanulasmenuActivity.this, "beleptem a truba", Toast.LENGTH_LONG).show();
                    tanulasDatuma.get(Calendar.DAY_OF_YEAR+1);

                }
                else {
                    tanulhat=true;
                    Toast.makeText(TanulasmenuActivity.this, "beleptem a falséba", Toast.LENGTH_LONG).show();
                }



                if (tanulhat==false != szamlalo>10) {


                    //sharedPreference(0);

                   AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TanulasmenuActivity.this);

                    alertDialogBuilder.setTitle("Ma már tanultál")
                                .setMessage("Ma már tanultál, térj vissza holnap!")
                                .setNegativeButton("Oké", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                        AlertDialog alert = alertDialogBuilder.create();
                        alert.show();

                   //Toast.makeText(TanulasmenuActivity.this, "Térj vissza holnap!", Toast.LENGTH_LONG).show();
                }
                else if (tanulhat==true  && szamlalo<10) {
                    tanulasKezdete();
                }
            }
        });
        //-------------------------------------------------------------------------------------------------------------------------
        tanultBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TanulasmenuActivity.this, TanuloSzotarActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void tanulasKezdete() {
        //Random Activity választó
        Random randomom = new Random();
        int number = randomom.nextInt(3) + 1;


        for (int i = 0; i < 1; i++) {
            if (number == 1) {


                Intent intent = new Intent(TanulasmenuActivity.this, Tanulas1Activity.class);
                startActivity(intent);
                finish();


            } else if (number == 2) {


                Intent intent = new Intent(TanulasmenuActivity.this, Tanulas2Activity.class);
                startActivity(intent);
                finish();

            } else if (number == 3) {


                Intent intent = new Intent(TanulasmenuActivity.this, Tanulas3Activity.class);
                startActivity(intent);
                finish();

            } else {

                    Intent intent = new Intent(TanulasmenuActivity.this, Fomenu.class);
                    startActivity(intent);
                    finish();

            }

        }
    }

    private void init() {
        visszatanulas=findViewById(R.id.visszatanulas);
        napibut=findViewById(R.id.napibut);
        tanultBut=findViewById(R.id.tanultBut);
        tanulaDatumaWiev=findViewById(R.id.tanulaDatumaWiev);




    }

    private void sharedPreference(int szamlalo) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putString("szamlalo", String.valueOf(szamlalo)).apply();
    }
    private void oraSharedPreference(int ora) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putInt("ora", ora).apply();

    }
    private void hetosszesSharedPreference(double hetosszes) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putFloat("hetosszes", (float) hetosszes).apply();

    }
    private void honaposszesSharedPreference(double honaposszes) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putFloat("honaposszes", (float) honaposszes).apply();

    }
    private void evosszesSharedPreference(double evosszes) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putFloat("evosszes", (float) evosszes).apply();

    }
    private void hetSharedPreference(float het) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putFloat("het",  het).apply();

    }
    private void haviSharedPreference(float honap) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putFloat("honap",  honap).apply();

    }
    private void evSharedPreference(float ev) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putFloat("ev", ev).apply();

    }

}
