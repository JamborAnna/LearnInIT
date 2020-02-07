package com.example.learninit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProvider;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Random;

public class TanulasmenuActivity extends AppCompatActivity {

    private Button visszatanulas;
    private  Button napibut;
    private  Button tanultBut;
    private SharedPreferences sharedPreferences;
    private  AlertDialog alertDialog;
    private String jelenlegiDatum="";
    private  Calendar tanulasDatuma;
    public static int szamlalo;
   private Boolean tanulhat=true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanulasmenu);

        init();

        visszatanulas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TanulasmenuActivity.this,fomenu.class);
                startActivity(intent);
                finish();
            }
        });

        //---------------------------------------------------------------------------------------------------------------

        //itt kezdődik majd a tanulás

        napibut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreference(0);

                // a napi egyszeri tanulás megengedésére szolgál
                Calendar jelenlegiDatum = Calendar.getInstance();
                Calendar tanulasDatuma=Calendar.getInstance();
                //TanulasmenuActivity.szamlalo++;
               tanulasDatuma.set(Calendar.HOUR,Calendar.MINUTE);
               SharedPreferences setora =getSharedPreferences("ora", Context.MODE_PRIVATE);
               // SharedPreferences.Editor editor=setora.edit();
               //editor.putInt("szam",tanulasDatuma.get(Calendar.HOUR));
               // editor.apply();
                oraSharedPreference(tanulasDatuma.get(Calendar.HOUR));

                //kiolvasas
                SharedPreferences getora =getSharedPreferences("szam", Context.MODE_PRIVATE);
                int oraido=getora.getInt("ora",0);


                jelenlegiDatum.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);

                if (tanulasDatuma.get(Calendar.HOUR)==0){
                    tanulhat=true;
                }
                if (tanulhat==false && szamlalo>15) {




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
                else if (tanulhat==true){


                    //Random Activity választó
                    Random randomom = new Random();
                    int number = randomom.nextInt(3) + 1;

                    //int szamlalo = 0;

                            for (int i = 0; i < 1; i++) {
                                if (number == 1) {
                                    sharedPreference(szamlalo);
                                    szamlalo++;
                                    Intent intent = new Intent(TanulasmenuActivity.this, Tanulas1Activity.class);
                                    startActivity(intent);
                                    finish();


                                } else if (number == 2) {
                                    szamlalo++;
                                    sharedPreference(szamlalo);
                                    Intent intent = new Intent(TanulasmenuActivity.this, Tanulas2Activity.class);
                                    startActivity(intent);
                                    finish();

                                } else if (number == 3) {
                                    szamlalo++;
                                    sharedPreference(szamlalo);
                                    Intent intent = new Intent(TanulasmenuActivity.this, Tanulas3Activity.class);
                                    startActivity(intent);
                                    finish();

                                } else {

                                        Intent intent = new Intent(TanulasmenuActivity.this, fomenu.class);
                                        startActivity(intent);
                                        finish();

                                }
                                //sharedPreference(szamlalo);
                            }


                     }
                }
        });
        //-------------------------------------------------------------------------------------------------------------------------
        tanultBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TanulasmenuActivity.this,TanultActivity.class);
                startActivity(intent);
                finish();
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
    private void oraSharedPreference(int ora) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putInt("ora", ora).apply();
    }
}
