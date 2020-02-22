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
    private Task<Void> databaseReference;
    private RegistryUser registryUser;
    private long id;

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
                   registryUser.setEmail(EmailText.getText().toString());

                    //felküldi az értékeket
                    databaseReference.child(user.getUid()).setValue(registryUser);

                }*/
                final String felhasznaloNev=FelhasznalonevText.getText().toString();
                final String email=EmailText.getText().toString();
                String jelszo=JelszoText.getText().toString();
                String jelszoism=JelszoIsmText.getText().toString();
                //mit fogok felküldeni



               /* if (TextUtils.isEmpty(jelszo) ){
                    Toast.makeText(Regisztracio.this,"A két jelszó nem egyezik meg!",Toast.LENGTH_LONG).show();

                }
                if (TextUtils.isEmpty(felhasznaloNev)  ){
                    Toast.makeText(Regisztracio.this,"A felhasználó név üres!",Toast.LENGTH_LONG).show();

                }
                if (TextUtils.isEmpty(email)  ){
                    Toast.makeText(Regisztracio.this,"Az email üres!",Toast.LENGTH_LONG).show();

                }*/


                progressRegistry.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(EmailText.getText().toString(),
                        JelszoText.getText().toString())
                        .addOnCompleteListener(new  OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {
                                progressRegistry.setVisibility(View.GONE);

                                    RegistryUser registryUser=new RegistryUser(felhasznaloNev,email);
                                    databaseReference=FirebaseDatabase.getInstance().getReference("Felhasznalo").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(registryUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful() ) {
                                                Toast.makeText(Regisztracio.this, "Sikeres Regisztráció!",
                                                        Toast.LENGTH_SHORT).show();
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


    private void init() {
        progressRegistry= findViewById(R.id.progressRegistry);
        visszaregisztracios = findViewById(R.id.visszaregisztracios);
        FelhasznalonevText = findViewById(R.id.FelhasznalonevText);
        EmailText = findViewById(R.id.EmailText);
        JelszoText = findViewById(R.id.JelszoText);
        JelszoIsmText = findViewById(R.id.JelszoIsmText);
        RegKuldBut = findViewById(R.id.RegKuldBut);


        mAuth=FirebaseAuth.getInstance();

       /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    id= dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

    }
}
