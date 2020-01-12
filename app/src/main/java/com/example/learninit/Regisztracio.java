package com.example.learninit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Regisztracio extends AppCompatActivity {

    private Button visszaregisztracios;
    private EditText FelhasznalonevText;
    private EditText EmailText;
    private EditText JelszoText;
    private EditText JelszoIsmText;
    private Button RegKuldBut;
    private FirebaseAuth mAuth;
    private ProgressBar progressRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisztracio);
        init();
        mAuth = FirebaseAuth.getInstance();
        visszaregisztracios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Regisztracio.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });


        RegKuldBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressRegistry.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(EmailText.getText().toString(),
                        JelszoText.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {
                                progressRegistry.setVisibility(View.GONE);
                                if (task.isSuccessful() ) {
                                    Toast.makeText(Regisztracio.this, "Sikeres Regisztráció!",
                                            Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(Regisztracio.this, "Sikertelen Regisztráció!",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });
    }


    private void init() {
        progressRegistry= findViewById(R.id.progressRegistry);

        visszaregisztracios = findViewById(R.id.visszaregisztracios);
        FelhasznalonevText = findViewById(R.id.FelhasznalonevText);
        EmailText = findViewById(R.id.EmailText);
        JelszoText = findViewById(R.id.JelszoText);
        JelszoIsmText = findViewById(R.id.JelszoIsmText);
        RegKuldBut = findViewById(R.id.RegKuldBut);
    }
}
