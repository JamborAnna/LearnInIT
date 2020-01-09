package com.example.learninit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bejelentkezes extends AppCompatActivity {

    private Button visszabejelentkezes;
    private EditText FelasznalonevBej;
    private  EditText JelszoBej;
    private  Button BejelentkezesButBej;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bejelentkezes);
        init();

        visszabejelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Bejelentkezes.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void init() {
        visszabejelentkezes=findViewById(R.id.visszabejelentkezes);
    }
}
