package com.example.learninit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class fomenu extends AppCompatActivity {
    private TextView FelhasznaloNeveView;
    private Button kijelentkezesBut;
    private  Button statisztika;
    private  Button tanulni;
    private FirebaseAuth mAuth;
    private FirebaseUser userFire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fomenu);
        init();

        FelhasznaloNeveView.setText(userFire.getEmail());
        kijelentkezesBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fomenu.this,KijelentkezesActivity.class);
                startActivity(intent);
                finish();
            }
        });
        statisztika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fomenu.this,StatisztikaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tanulni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fomenu.this,TanulasmenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        userFire = mAuth.getCurrentUser();
        FelhasznaloNeveView=findViewById(R.id.FelhasznaloNeveView);
        kijelentkezesBut=findViewById(R.id.kijelentkezesBut);
        statisztika=findViewById(R.id.statisztika);
        tanulni=findViewById(R.id.tanulni);
    }
}
