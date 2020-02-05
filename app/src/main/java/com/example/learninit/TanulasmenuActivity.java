package com.example.learninit;

import androidx.appcompat.app.AppCompatActivity;

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

        //itt kezdődik majd a tanulás

        napibut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // a napi egyszeri tanulás megengedésére szolgál
                Calendar jelenlegiDatum = Calendar.getInstance();
                Calendar tanulasDatuma=Calendar.getInstance();
                //tanulasDatuma.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
                jelenlegiDatum.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);


                if (jelenlegiDatum.equals(tanulasDatuma)) {
                    Toast.makeText(TanulasmenuActivity.this, "Térj vissza holnap!", Toast.LENGTH_LONG).show();
                } else {




                    //Random Activity választó
                    Random randomom = new Random();
                    int number = randomom.nextInt(3) + 1;

                    int szamlalo = 0;
                    // sharedPreferences= getSharedPreferences("database",MODE_PRIVATE);
                    for (int i = 0; i < 1; i++) {
                        if (number == 1) {
                            Intent intent = new Intent(TanulasmenuActivity.this, Tanulas1Activity.class);
                            startActivity(intent);
                            finish();
                            szamlalo++;
                        } else if (number == 2) {
                            Intent intent = new Intent(TanulasmenuActivity.this, Tanulas2Activity.class);
                            startActivity(intent);
                            finish();
                            szamlalo++;
                        } else if (number == 3) {
                            Intent intent = new Intent(TanulasmenuActivity.this, Tanulas3Activity.class);
                            startActivity(intent);
                            finish();
                            szamlalo++;
                        } else {
                            if (szamlalo == 10) {
                                Intent intent = new Intent(TanulasmenuActivity.this, fomenu.class);
                                startActivity(intent);
                                finish();
                            }
                        }


                    }
                }
            }
        });

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
}
