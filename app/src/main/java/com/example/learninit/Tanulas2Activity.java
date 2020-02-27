package com.example.learninit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
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

public class Tanulas2Activity extends AppCompatActivity {
    private Button tanulas2vissza;
    private TextView bekerendoSzoview2;
    private EditText bekertszoedit2;
    private  Button tanulas1Ellenorzesbut2;
    private  Button szo1;
    private DatabaseReference databaseReference;
    private MediaPlayer helyesMP3, helytelenMP3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanulas2);
        init();

       int osszes = Integer.parseInt(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getInt("osszes", 0)));
        osszes++;
        osszesSharedPreference(osszes);




        databaseReference= FirebaseDatabase.getInstance().getReference().child("szotar").child("0");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Szotar s;
                    //int szoid= (int) dataSnapshot.getChildrenCount();
                    //int rand = new Random().nextInt(szoid);


                    // String szo_id= dataSnapshot.child("szo_id").getValue().toString();


                    String magyar = dataSnapshot.child("magyar").getValue().toString();
                    final String angol = dataSnapshot.child("angol").getValue().toString();
                    bekerendoSzoview2.setText(magyar);
                    szo1.setText(angol);
                    szo1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bekertszoedit2.setText(angol);
                        }
                    });
                    angol.split("");


                    tanulas1Ellenorzesbut2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Random randomom = new Random();
                            int number= randomom.nextInt(3)+1;



                            int szamlalo = Integer.parseInt(getSharedPreferences("szam", Context.MODE_PRIVATE).getString("szamlalo", ""));
                            szamlalo++;
                            sharedPreference(szamlalo);

                            if (szamlalo>15){
                                Intent intent = new Intent(Tanulas2Activity.this,TanulasmenuActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                if (bekertszoedit2.getText().toString().equals(angol) ) {
                                    helyesMP3.start();
                                    Toast.makeText(Tanulas2Activity.this, "Helyes válasz!", Toast.LENGTH_SHORT).show();
                                    int het = Integer.parseInt(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getInt("het", 0)));
                                    het++;
                                    hetSharedPreference(het);
                                    int honap = Integer.parseInt(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getInt("honap", 0)));
                                    honap++;
                                    haviSharedPreference(honap);
                                    int ev = Integer.parseInt(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getInt("ev", 0)));
                                    ev++;
                                    evSharedPreference(ev);

                                    if (number == 1) {
                                        Intent intent = new Intent(Tanulas2Activity.this, Tanulas1Activity.class);
                                        startActivity(intent);
                                        finish();

                                    } else if (number == 2) {
                                        Intent intent = new Intent(Tanulas2Activity.this, Tanulas2Activity.class);
                                        startActivity(intent);
                                        finish();

                                    } else if (number == 3) {
                                        Intent intent = new Intent(Tanulas2Activity.this, Tanulas3Activity.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                } else {
                                    helytelenMP3.start();
                                    Toast.makeText(Tanulas2Activity.this, "Rossz válasz!", Toast.LENGTH_SHORT).show();
                                    if (number == 1) {
                                        Intent intent = new Intent(Tanulas2Activity.this, Tanulas1Activity.class);
                                        startActivity(intent);
                                        finish();

                                    } else if (number == 2) {
                                        Intent intent = new Intent(Tanulas2Activity.this, Tanulas2Activity.class);
                                        startActivity(intent);
                                        finish();

                                    } else if (number == 3) {
                                        Intent intent = new Intent(Tanulas2Activity.this, Tanulas3Activity.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            }
                        }
                    });




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        tanulas2vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tanulas2Activity.this,TanulasmenuActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    private void init() {
        tanulas2vissza=findViewById(R.id.tanulas2vissza);
        bekerendoSzoview2=findViewById(R.id.bekerendoSzoview2);
        bekertszoedit2=findViewById(R.id.bekertszoedit2);
        tanulas1Ellenorzesbut2=findViewById(R.id.tanulas1Ellenorzesbut2);
        szo1=findViewById(R.id.szo1);
        helyesMP3=MediaPlayer.create(this,R.raw.dicseret);
        helytelenMP3=MediaPlayer.create(this,R.raw.helytelen);

    }
    private void sharedPreference(int szamlalo) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putString("szamlalo", String.valueOf(szamlalo)).apply();
    }
    private void hetSharedPreference(int het) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putInt("het", het).apply();

    }
    private void haviSharedPreference(int honap) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putInt("honap", honap).apply();

    }
    private void evSharedPreference(int ev) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putInt("ev", ev).apply();

    }
    private void osszesSharedPreference(int osszes) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putInt("osszes", osszes).apply();

    }
}

