package com.example.learninit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class Tanulas1Activity extends AppCompatActivity {
private Button tanulas1vissza;
private TextView bekerendoSzoview;
private EditText bekertszoedit;
private  Button tanulas1Ellenorzesbut;
private DatabaseReference databaseReference;
private Random randomom = new Random();
private szotar Szotar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanulas1);
        init();





        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String data= dataSnapshot.child("szotar").getValue(String.class);
                bekerendoSzoview.setText(data);
                if (dataSnapshot.exists()){
                    szotar s;


                    int randomSzam= randomom.nextInt((int) dataSnapshot.getChildrenCount());

                    for (DataSnapshot item :dataSnapshot.getChildren()) {

                        s=item.getValue(szotar.class);
                        if (s.szo_id.equals(String.valueOf(randomSzam))){
                            bekerendoSzoview.setText(s.magyar);
                            bekertszoedit.setText(s.angol);
                            Toast.makeText(Tanulas1Activity.this, "ize", Toast.LENGTH_SHORT).show();

                        }


                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        tanulas1vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tanulas1Activity.this,TanulasmenuActivity.class);
                startActivity(intent);
                finish();



            }
        });
      /*  bekertszoedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (bekertszoedit.getText().toString().isEmpty()) {
                    Toast.makeText(Tanulas1Activity.this, "Nem írtál be semmit!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (bekertszoedit==bekerendoSzoview){

                }
            }
        });*/

        tanulas1Ellenorzesbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random randomom = new Random();
                int number= randomom.nextInt(3)+1;

                int szamlalo = Integer.parseInt(getSharedPreferences("szam", Context.MODE_PRIVATE).getString("szamlalo", ""));
                    sharedPreference(szamlalo);
                    szamlalo++;

                if (szamlalo>15){
                    Intent intent = new Intent(Tanulas1Activity.this, TanulasmenuActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    if (bekerendoSzoview == bekertszoedit&& szamlalo<15) {
                        if (number == 1) {

                            Intent intent = new Intent(Tanulas1Activity.this, Tanulas1Activity.class);
                            startActivity(intent);
                            finish();



                        } else if (number == 2) {

                            Intent intent = new Intent(Tanulas1Activity.this, Tanulas2Activity.class);
                            startActivity(intent);
                            finish();


                        } else if (number == 3) {

                            Intent intent = new Intent(Tanulas1Activity.this, Tanulas3Activity.class);
                            startActivity(intent);
                            finish();

                        }

                    } else {
                        Toast.makeText(Tanulas1Activity.this, "Rossz válasz!", Toast.LENGTH_SHORT).show();
                        if (number == 1) {
                            Intent intent = new Intent(Tanulas1Activity.this, Tanulas1Activity.class);
                            startActivity(intent);
                            finish();

                        } else if (number == 2) {
                            Intent intent = new Intent(Tanulas1Activity.this, Tanulas2Activity.class);
                            startActivity(intent);
                            finish();

                        } else if (number == 3) {
                            Intent intent = new Intent(Tanulas1Activity.this, Tanulas3Activity.class);
                            startActivity(intent);
                            finish();
                        }


                    }


                }
            }
        });
    }



    private void init() {
        tanulas1vissza=findViewById(R.id.tanulas1vissza);
        bekerendoSzoview=findViewById(R.id.bekerendoSzoview);
        bekertszoedit=findViewById(R.id.bekertszoedit);
        tanulas1Ellenorzesbut=findViewById(R.id.tanulas1Ellenorzesbut);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        Szotar=new szotar();

    }
    private void sharedPreference(int szamlalo) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putString("szamlalo", String.valueOf(szamlalo)).apply();
    }

}
