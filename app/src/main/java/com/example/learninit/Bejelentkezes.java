package com.example.learninit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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

        BejelentkezesButBej.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lottiAnim.setVisibility(View.VISIBLE);
                BejelentkezesButBej.setVisibility(View.GONE);
                visszabejelentkezes.setVisibility(View.GONE);
                forgott.setVisibility(View.GONE);
            mAuth.signInWithEmailAndPassword(FelasznalonevBej.getText().toString(),
                    JelszoBej.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    lottiAnim.setVisibility(View.GONE);
                    if (task.isSuccessful()){
                        startActivity(new Intent(Bejelentkezes.this,fomenu.class));
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
    }
}
