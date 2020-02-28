package com.example.learninit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Fomenu extends AppCompatActivity {
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
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Felhasznalo").child(String.valueOf(mAuth.getUid())).child("felhasznaloNev");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FelhasznaloNeveView.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        kijelentkezesBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fomenu.this,KijelentkezesActivity.class);
                startActivity(intent);
                finish();
            }
        });
        statisztika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fomenu.this,StatisztikaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tanulni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fomenu.this,TanulasmenuActivity.class);
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
