package com.example.learninit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button RegisztracioBut;
    private Button BejelentkezesBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //Regisztrációs felületre való átirányítás//
        RegisztracioBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Regisztracio.class);
                startActivity(intent);
                finish();
            }
        });

        //Bejelentkezés felületre átirányítás//
        BejelentkezesBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Bejelentkezes.class);
                startActivity(intent);
                finish();
            }
        });

        }

    private void init() {
        RegisztracioBut=findViewById(R.id.RegisztracioBut);
        BejelentkezesBut=findViewById(R.id.BejelentkezesBut);

    }
}

