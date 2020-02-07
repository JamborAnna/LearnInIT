package com.example.learninit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Tanulas1Activity extends AppCompatActivity {
private Button tanulas1vissza;
private TextView bekerendoSzoview;
private EditText bekertszoedit;
private  Button tanulas1Ellenorzesbut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanulas1);
        init();
        tanulas1vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tanulas1Activity.this,TanulasmenuActivity.class);
                startActivity(intent);
                finish();



            }
        });

        tanulas1Ellenorzesbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random randomom = new Random();
                int number= randomom.nextInt(3)+1;

                int szamlalo = Integer.parseInt(getSharedPreferences("szam", Context.MODE_PRIVATE).getString("szamlalo", ""));



                if (szamlalo==15){
                    Intent intent = new Intent(Tanulas1Activity.this, TanulasmenuActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    if (bekerendoSzoview == bekertszoedit) {
                        if (number == 1) {
                            Intent intent = new Intent(Tanulas1Activity.this, Tanulas1Activity.class);
                            startActivity(intent);
                            finish();


                        } else if (number == 2) {
                            Intent intent = new Intent(Tanulas1Activity.this, Tanulas2Activity.class);
                            startActivity(intent);
                            finish();

                        } else if (number == 3) {
                            Intent intent = new Intent(Tanulas1Activity.this, Tanulas3Activity.class);
                            startActivity(intent);
                            finish();
                        }

                    } else {
                        Toast.makeText(Tanulas1Activity.this, "Rossz válasz!", Toast.LENGTH_SHORT).show();
                        if (number == 1) {
                            szamlalo++;
                            sharedPreference(szamlalo);
                            Intent intent = new Intent(Tanulas1Activity.this, Tanulas1Activity.class);
                            startActivity(intent);
                            finish();

                        } else if (number == 2) {
                            szamlalo++;
                            sharedPreference(szamlalo);
                            Intent intent = new Intent(Tanulas1Activity.this, Tanulas2Activity.class);
                            startActivity(intent);
                            finish();

                        } else if (number == 3) {
                            szamlalo++;
                            sharedPreference(szamlalo);
                            Intent intent = new Intent(Tanulas1Activity.this, Tanulas3Activity.class);
                            startActivity(intent);
                            finish();
                        }


                    }


                }
            }
        });
    }



    private void init() {
        tanulas1vissza=findViewById(R.id.tanulas1vissza);
        bekerendoSzoview=findViewById(R.id.bekerendoSzoview);
        bekertszoedit=findViewById(R.id.bekertszoedit);
        tanulas1Ellenorzesbut=findViewById(R.id.tanulas1Ellenorzesbut);



    }
    private void sharedPreference(int szamlalo) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putString("szamlalo", String.valueOf(szamlalo)).apply();
    }

   /* private void oraSharedPreference(int ora) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putString("ora", String.valueOf(ora)).apply();
    }*/
}
