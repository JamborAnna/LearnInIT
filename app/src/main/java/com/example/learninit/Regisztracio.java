package com.example.learninit;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

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

                JelszoText.setBackground(getResources().getDrawable(R.drawable.button_shape));
                FelhasznalonevText.setBackground(getResources().getDrawable(R.drawable.button_shape));
                EmailText.setBackground(getResources().getDrawable(R.drawable.button_shape));
                JelszoIsmText.setBackground(getResources().getDrawable(R.drawable.button_shape));

                progressRegistry.setVisibility(View.VISIBLE);

                RegistryUser registryUser = new RegistryUser();
                registryUser.setEmail(email);
                registryUser.setFelhasznaloNev(felhasznaloNev);



                if (TextUtils.isEmpty(jelszo)) {
                    Toast.makeText(Regisztracio.this, "A két jelszó nem egyezik meg!", Toast.LENGTH_LONG).show();
                    JelszoText.setBackground(getResources().getDrawable(R.drawable.button_color_red));
                    progressRegistry.setVisibility(View.GONE);

                }
                else if(jelszo.length()<=6) {

                    Toast.makeText(Regisztracio.this, "A jelszónak hosszabnak kell lennie 6 karakternél!", Toast.LENGTH_LONG).show();
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


                  mAuth.createUserWithEmailAndPassword(email, jelszo)
                            .addOnCompleteListener(Regisztracio.this,new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressRegistry.setVisibility(View.GONE);

                                    RegistryUser registryUser = new RegistryUser(felhasznaloNev, email);
                                    FirebaseDatabase.getInstance().getReference("Felhasznalo").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(registryUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(Task<Void> task) {
                                            if ( task.isSuccessful()) {
                                                Toast.makeText(Regisztracio.this, "Sikeres Regisztráció!",
                                                        Toast.LENGTH_SHORT).show();
                                                FelhasznalonevText.setText("");
                                                EmailText.setText("");
                                                JelszoText.setText("");
                                                JelszoIsmText.setText("");

                                                Intent intent = new Intent(Regisztracio.this, Bejelentkezes.class);
                                                startActivity(intent);
                                                finish();

                                            } else {

                                                Toast.makeText(Regisztracio.this, "Sikertelen Regisztráció!",
                                                        Toast.LENGTH_SHORT).show();



                                            }
                                        }
                                    });


                                }
                            });
                }

        });
    }
    public void onBackPressed(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Regisztracio.this);
        View alertViev= getLayoutInflater().inflate(R.layout.alert_dialog_style,null);
        Button igenBut=(Button)alertViev.findViewById(R.id.igenBut);
        Button nemBut=(Button)alertViev.findViewById(R.id.nemBut);

        alertDialogBuilder.setView(alertViev);
        final AlertDialog alert = alertDialogBuilder.create();
        alert.show();

        igenBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Regisztracio.this, Bejelentkezes.class);
                startActivity(intent);
                finish();
            }

        });
        nemBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });

    }


    private void init() {

        visszaregisztracios = findViewById(R.id.visszaregisztracios);
        FelhasznalonevText = findViewById(R.id.FelhasznalonevText);
        EmailText = findViewById(R.id.EmailText);
        JelszoText = findViewById(R.id.JelszoText);
        JelszoIsmText = findViewById(R.id.JelszoIsmText);
        RegKuldBut = findViewById(R.id.RegKuldBut);
        mAuth=FirebaseAuth.getInstance();

    }
}
