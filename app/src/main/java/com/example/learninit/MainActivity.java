package com.example.learninit;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private Button RegisztracioBut;
    private Button BejelentkezesBut;
    private ProgressBar progressMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //Regisztrációs felületre való átirányítás//
        RegisztracioBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressMain.setVisibility(View.VISIBLE);
                Intent intent = new Intent(MainActivity.this,Regisztracio.class);
                startActivity(intent);
                progressMain.setVisibility(View.GONE);
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
        progressMain=findViewById(R.id.progressMain);

    }
}

