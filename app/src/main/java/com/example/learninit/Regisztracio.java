package com.example.learninit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Regisztracio extends AppCompatActivity {

    private Button visszaregisztracios;
    private EditText FelhasznalonevText;
    private EditText EmailText;
    private EditText JelszoText;
    private EditText JelszoIsmText;
    private Button RegKuldBut;
    private FirebaseAuth mAuth;
    private ProgressBar progressRegistry;
    //private DatabaseReference db;
   // private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisztracio);
        init();
            mAuth=FirebaseAuth.getInstance();



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


                final String felhasznaloNev = FelhasznalonevText.getText().toString();
                final String email = EmailText.getText().toString();
                String jelszo = JelszoText.getText().toString();
                String jelszoism = JelszoIsmText.getText().toString();
                //mit fogok felküldeni


                progressRegistry.setVisibility(View.VISIBLE);

                RegistryUser registryUser = new RegistryUser();
                registryUser.setEmail(email);
                registryUser.setFelhasznaloNev(felhasznaloNev);
                //registryUser.setUser_id(id);


                 /*FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if (FelhasznalonevText.getText().toString().isEmpty() ||  JelszoText.getText().toString().isEmpty() || EmailText.getText().toString().isEmpty()|| JelszoIsmText.getText().toString().isEmpty()){
                    Toast.makeText(Regisztracio.this,"Minden mezőt ki kell tölteni",Toast.LENGTH_SHORT).show();
                }
                else
                {
                   /* databaseReference.setValue(FelhasznalonevText);
                    databaseReference.setValue(EmailText);
                    databaseReference.setValue(mAuth.getUid());
                   registryUser.setFelhasznaloNev(FelhasznalonevText.getText().toString());
                   registryUser.setEmail(EmailText.getText().toString());*/
                if (TextUtils.isEmpty(jelszo)) {
                    Toast.makeText(Regisztracio.this, "A két jelszó nem egyezik meg!", Toast.LENGTH_LONG).show();
                    JelszoText.setBackground(getResources().getDrawable(R.drawable.button_color_red));
                    progressRegistry.setVisibility(View.GONE);

                }
                else if (TextUtils.isEmpty(felhasznaloNev)) {
                    Toast.makeText(Regisztracio.this, "A felhasználó név üres!", Toast.LENGTH_LONG).show();
                    FelhasznalonevText.setBackground(getResources().getDrawable(R.drawable.button_color_red));
                    progressRegistry.setVisibility(View.GONE);
                }
                else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Regisztracio.this, "Az email üres!", Toast.LENGTH_LONG).show();
                    EmailText.setBackground(getResources().getDrawable(R.drawable.button_color_red));
                    progressRegistry.setVisibility(View.GONE);
                }
                else if (TextUtils.isEmpty(jelszoism) || (jelszo == jelszoism)) {
                    Toast.makeText(Regisztracio.this, "A két jelszó nem egyezik meg!", Toast.LENGTH_LONG).show();
                    JelszoIsmText.setBackground(getResources().getDrawable(R.drawable.button_color_red));
                    progressRegistry.setVisibility(View.GONE);
                } else

                    mAuth.createUserWithEmailAndPassword(EmailText.getText().toString(),
                            JelszoText.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(Task<AuthResult> task) {
                                    progressRegistry.setVisibility(View.GONE);

                                    RegistryUser registryUser = new RegistryUser(felhasznaloNev, email);
                                    FirebaseDatabase.getInstance().getReference("Felhasznalo").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(registryUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(Task<Void> task) {
                                            if (FelhasznalonevText.getText().toString().isEmpty() || EmailText.getText().toString().isEmpty() || JelszoIsmText.getText().toString().isEmpty() || JelszoText.getText().toString().isEmpty()) {
                                                Toast.makeText(Regisztracio.this, "Sikertelen Regisztráció!",
                                                        Toast.LENGTH_SHORT).show();
                                            } else {

                                                Toast.makeText(Regisztracio.this, "Sikeres Regisztráció!",
                                                        Toast.LENGTH_SHORT).show();
                                                FelhasznalonevText.setText("");
                                                EmailText.setText("");
                                                JelszoText.setText("");
                                                JelszoIsmText.setText("");

                                            }
                                        }
                                    });


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
        mAuth=FirebaseAuth.getInstance();

    }
}
