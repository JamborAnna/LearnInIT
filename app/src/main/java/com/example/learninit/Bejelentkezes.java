package com.example.learninit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Bejelentkezes extends AppCompatActivity {

    private Button visszabejelentkezes;
    private EditText FelasznalonevBej;
    private  EditText JelszoBej;
    private  Button BejelentkezesButBej;
    private ProgressBar progressLogin;
    private FirebaseAuth mAuth;
    private TextView forgott;
    private LottieAnimationView lottiAnim;
    private RelativeLayout Bejelentkezes;
    private Switch switchFelh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bejelentkezes);
        init();

        switchFelh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                    Toast.makeText(Bejelentkezes.this, "Felhasználó megjegyezve!", Toast.LENGTH_SHORT).show();

                    int felhasznalo = Integer.parseInt(getSharedPreferences("szam", Context.MODE_PRIVATE).getString("felhasznalo", ""));

                    felhsharedPreference(String.valueOf(felhasznalo));
                }
                else{

                }
            }
        });
        visszabejelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Bejelentkezes.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        BejelentkezesButBej.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lottiAnim.setVisibility(View.VISIBLE);
                BejelentkezesButBej.setVisibility(View.GONE);
                visszabejelentkezes.setVisibility(View.GONE);
                forgott.setVisibility(View.GONE);
                FelasznalonevBej.setVisibility(View.GONE);
                JelszoBej.setVisibility(View.GONE);
            mAuth.signInWithEmailAndPassword(FelasznalonevBej.getText().toString(),
                    JelszoBej.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    lottiAnim.setVisibility(View.GONE);
                    BejelentkezesButBej.setVisibility(View.VISIBLE);
                    visszabejelentkezes.setVisibility(View.VISIBLE);
                    forgott.setVisibility(View.VISIBLE);
                    FelasznalonevBej.setVisibility(View.VISIBLE);
                    JelszoBej.setVisibility(View.VISIBLE);
                    if (task.isSuccessful()){
                        startActivity(new Intent(Bejelentkezes.this, Fomenu.class));
                    }
                    else{
                        Toast.makeText(Bejelentkezes.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
            }
        }));

        forgott.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        visszabejelentkezes=findViewById(R.id.visszabejelentkezes);
        FelasznalonevBej=findViewById(R.id.FelasznalonevBej);
        JelszoBej=findViewById(R.id.JelszoBej);
        BejelentkezesButBej=findViewById(R.id.BejelentkezesButBej);
      // Bejelentkezes=findViewById(R.id.Bejelentkezes);
        forgott=findViewById(R.id.forgott);
        lottiAnim = findViewById(R.id.lottieAnimation);
        switchFelh= findViewById(R.id.switchFelh);
    }
    private void felhsharedPreference(String felhasznalo) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putString("felhasznalo", String.valueOf(felhasznalo)).apply();

    }
}
