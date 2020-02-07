package com.example.learninit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static com.example.learninit.TanulasmenuActivity.szamlalo;

public class Tanulas3Activity extends AppCompatActivity  {
    private Button tanulas3vissza;
    private ImageButton imageBut1;
    private TextView BekerdezendoSzoViev;
    private  ImageButton imageBut2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanulas3);
        init();

        tanulas3vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tanulas3Activity.this,TanulasmenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imageBut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random randomom = new Random();
                int number= randomom.nextInt(3)+1;
                int szamlalo = Integer.parseInt(getSharedPreferences("szam", Context.MODE_PRIVATE).getString("szamlalo", ""));
                szamlalo++;
                sharedPreference(szamlalo);
                if (szamlalo ==15){
                    Intent intent = new Intent(Tanulas3Activity.this,TanulasmenuActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    if (number == 1) {
                        Intent intent = new Intent(Tanulas3Activity.this, Tanulas1Activity.class);
                        startActivity(intent);
                        finish();

                    } else if (number == 2) {
                        Intent intent = new Intent(Tanulas3Activity.this, Tanulas2Activity.class);
                        startActivity(intent);
                        finish();

                    } else if (number == 3) {
                        Intent intent = new Intent(Tanulas3Activity.this, Tanulas3Activity.class);
                        startActivity(intent);
                        finish();
                    }
                    //sharedPreference(szamlalo);
                }

            }
        });
        imageBut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random randomom = new Random();
                int number= randomom.nextInt(3)+1;
                //TanulasmenuActivity.szamlalo++;

                int szamlalo = Integer.parseInt(getSharedPreferences("szam", Context.MODE_PRIVATE).getString("szamlalo", ""));
                szamlalo++;
                sharedPreference(szamlalo);
                if (szamlalo==15){
                    Intent intent = new Intent(Tanulas3Activity.this,TanulasmenuActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(Tanulas3Activity.this, "Rossz válasz!", Toast.LENGTH_SHORT).show();
                    if (number == 1) {
                        Intent intent = new Intent(Tanulas3Activity.this, Tanulas1Activity.class);
                        startActivity(intent);
                        finish();

                    } else if (number == 2) {
                        Intent intent = new Intent(Tanulas3Activity.this, Tanulas2Activity.class);
                        startActivity(intent);
                        finish();

                    } else if (number == 3) {
                        Intent intent = new Intent(Tanulas3Activity.this, Tanulas3Activity.class);
                        startActivity(intent);
                        finish();
                    }
                    //sharedPreference(szamlalo);
                }

            }
        });
    }

    private void init() {
        tanulas3vissza=findViewById(R.id.tanulas3vissza);
        imageBut1=findViewById(R.id.imageBut1);
        BekerdezendoSzoViev=findViewById(R.id.BekerdezendoSzoViev);
        imageBut2=findViewById(R.id.imageBut2);
    }
    private void sharedPreference(int szamlalo) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putString("szamlalo", String.valueOf(szamlalo)).apply();
    }
}
