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

import com.airbnb.lottie.LottieAnimationView;

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
    public static int szamlalo=0;
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
                if (szamlalo==15){
                    tanulhat=false;
                }
                sharedPreference( 0);
                sharedPreference(szamlalo+1);



                //szamlalo++;
                // a napi egyszeri tanulás megengedésére szolgál
                Calendar jelenlegiDatum = Calendar.getInstance();
                Calendar tanulasDatuma=Calendar.getInstance();
              // tanulasDatuma.set(Calendar.HOUR,Calendar.MINUTE); //nem szabad oldani

                if (tanulasDatuma.get(Calendar.DAY_OF_YEAR)>365){
                    oraSharedPreference(0);
                }



               SharedPreferences setora =getSharedPreferences("ora", Context.MODE_PRIVATE);
                oraSharedPreference(tanulasDatuma.get(Calendar.DAY_OF_YEAR));

                //kiolvasas
                SharedPreferences getora =getSharedPreferences("szam", Context.MODE_PRIVATE);
                int oraido=getora.getInt("ora",0);


               jelenlegiDatum.get(Calendar.DAY_OF_YEAR);


                //if (oraSharedPreference()
                if (tanulasDatuma.get(Calendar.DAY_OF_YEAR)<tanulasDatuma.get(Calendar.DAY_OF_YEAR+1)){

                    tanulhat=true;
                    Toast.makeText(TanulasmenuActivity.this, "beleptem a truba", Toast.LENGTH_LONG).show();

                }
                else {
                    tanulhat=false;
                    Toast.makeText(TanulasmenuActivity.this, "beleptem a falséba", Toast.LENGTH_LONG).show();
                }



                if (tanulhat==false != szamlalo>15) {


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
                else if (tanulhat==true  && szamlalo<15){


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

                                        Intent intent = new Intent(TanulasmenuActivity.this, fomenu.class);
                                        startActivity(intent);
                                        finish();

                                }

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
