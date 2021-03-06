package com.example.learninit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd ");
          jelenlegiDatum = dateFormat.format(date);

        try {
            file= new File(Environment.getExternalStorageDirectory().getPath(),"belepesDatum.csv");
            if (!file.exists()){


            }
            BufferedReader bufferedReader=new BufferedReader(new FileReader(file),1024);
            String sor;
          while ((sor=bufferedReader.readLine())!=null)
          {
              TanultDatum=sor;
          }


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
                }else {
                    sharedPreference( 0);
                    //sharedPreference(szamlalo+1);

                }



                if (!TanultDatum.equals(jelenlegiDatum)){

                    tanulhat=true;

                    file=new File(Environment.getExternalStorageDirectory(),("belepesDatum.csv"));
                    try{

                        Date date = Calendar.getInstance().getTime();


                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd ");
                        String formatedDate = dateFormat.format(date);
                        String text =  formatedDate+ "\r\n";
                        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file,true),1024);
                        bufferedWriter.write(String.valueOf(text));
                        bufferedWriter.close();
                    }catch (IOException e){
                        tanulasKezdete();
                        return;
                    }

                }
                else {
                    tanulhat=false;

                }

                if (tanulhat==false != szamlalo>10) {


                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TanulasmenuActivity.this);
                    View alertViev= getLayoutInflater().inflate(R.layout.valasz_alert,null);
                    Button okBut=(Button)alertViev.findViewById(R.id.okBut);
                     TextView valasz= alertViev.findViewById(R.id.valasz);
                     TextView KilepesView= alertViev.findViewById(R.id.KilepesView);
                    KilepesView.setText("Ma már tanultál!");
                    valasz.setText("Térj vissza holnap!");

                    alertDialogBuilder.setView(alertViev);
                    final AlertDialog alert = alertDialogBuilder.create();
                    alert.show();
                     okBut.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                            alert.dismiss();
                         }
                     });






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
    public void onBackPressed(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TanulasmenuActivity.this);
        View alertViev= getLayoutInflater().inflate(R.layout.alert_dialog_style,null);
        Button igenBut=(Button)alertViev.findViewById(R.id.igenBut);
        Button nemBut=(Button)alertViev.findViewById(R.id.nemBut);

        alertDialogBuilder.setView(alertViev);
        final AlertDialog alert = alertDialogBuilder.create();
        alert.show();

        igenBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TanulasmenuActivity.this, Bejelentkezes.class);
                startActivity(intent);
                finish();
            }

        });
        nemBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });

    }
    private void init() {
        visszatanulas=findViewById(R.id.visszatanulas);
        napibut=findViewById(R.id.napibut);
        tanultBut=findViewById(R.id.tanultBut);





    }
    private void sharedPreference(int szamlalo) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putString("szamlalo", String.valueOf(szamlalo)).apply();
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
