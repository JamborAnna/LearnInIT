package com.example.learninit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

public class Tanulas1Activity extends AppCompatActivity {
private Button tanulas1vissza;
private TextView bekerendoSzoview;
private EditText bekertszoedit;
private  Button tanulas1Ellenorzesbut;
private DatabaseReference databaseReference;

private MediaPlayer helyesMP3, helytelenMP3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanulas1);
        init();


        float hetosszes = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("hetosszes", 0)));
        hetosszes++;
        hetosszesSharedPreference(hetosszes);
        float evosszes = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("evosszes", 0)));
        evosszes++;
        evosszesSharedPreference(evosszes);
        float honaposszes = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("honaposszes", 0)));
        honaposszes++;
        honaposszesSharedPreference(honaposszes);
        final Random rnd = new Random();
        final int random= rnd.nextInt(182);

        if (hetosszes>=70){
            hetosszesSharedPreference(0);
            hetSharedPreference(0);
        }
        else if(honaposszes>=300){
            honaposszesSharedPreference(0);
            haviSharedPreference(0);
        }
        else  if(evosszes>=3650){
            evosszesSharedPreference(0);
            evosszesSharedPreference(0);
        }
        databaseReference= FirebaseDatabase.getInstance().getReference().child("szotar").child(String.valueOf(random));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                if (dataSnapshot.exists()){



                    String magyar= dataSnapshot.child("magyar").getValue().toString();
                    final String angol= dataSnapshot.child("angol").getValue().toString();
                    bekerendoSzoview.setText(magyar);

                    tanulas1Ellenorzesbut.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Random randomom = new Random();
                            final int number= randomom.nextInt(3)+1;

                            int szamlalo = Integer.parseInt(getSharedPreferences("szam", Context.MODE_PRIVATE).getString("szamlalo", ""));
                            szamlalo++;
                            sharedPreference(szamlalo);


                            if (szamlalo>=10){
                                Intent intent = new Intent(Tanulas1Activity.this, TanulasmenuActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                String valaszhasonlitashoz=angol.toLowerCase();
                                String helyesValasz=bekertszoedit.getText().toString().toLowerCase();
                                //Log.w("",bekerendoSzoview.getText().toString());
                                if (helyesValasz.equals(valaszhasonlitashoz) && szamlalo<10) {

                                    Toast.makeText(Tanulas1Activity.this, "Helyes válasz!", Toast.LENGTH_SHORT).show();
                                    helyesMP3.start();

                                    float het =Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("het", 0)));
                                    het++;
                                    hetSharedPreference(het);

                                    float honap = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("honap", 0)));
                                    honap++;
                                    haviSharedPreference(honap);

                                    float ev = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("ev", 0)));
                                    ev++;
                                    evSharedPreference(ev);


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

                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Tanulas1Activity.this);
                                    View alertViev= getLayoutInflater().inflate(R.layout.valasz_alert,null);
                                    Button okBut=(Button)alertViev.findViewById(R.id.okBut);
                                    TextView valasz= alertViev.findViewById(R.id.valasz);
                                    helytelenMP3.start();
                                    valasz.setText(angol);
                                    alertDialogBuilder.setView(alertViev);
                                    final AlertDialog alert = alertDialogBuilder.create();
                                    alert.show();
                                    okBut.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            alert.dismiss();

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
                                    });



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

        tanulas1vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tanulas1Activity.this,TanulasmenuActivity.class);
                startActivity(intent);
                finish();



            }
        });


    }
    public void onBackPressed(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Tanulas1Activity.this);
        View alertViev= getLayoutInflater().inflate(R.layout.alert_dialog_style,null);
        Button igenBut=(Button)alertViev.findViewById(R.id.igenBut);
        Button nemBut=(Button)alertViev.findViewById(R.id.nemBut);

        alertDialogBuilder.setView(alertViev);
        final AlertDialog alert = alertDialogBuilder.create();
        alert.show();

        igenBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tanulas1Activity.this, Bejelentkezes.class);
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
        tanulas1vissza=findViewById(R.id.tanulas1vissza);
        bekerendoSzoview=findViewById(R.id.bekerendoSzoview);
        bekertszoedit=findViewById(R.id.bekertszoedit);
        tanulas1Ellenorzesbut=findViewById(R.id.tanulas1Ellenorzesbut);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        helyesMP3=MediaPlayer.create(this,R.raw.dicseret);
        helytelenMP3=MediaPlayer.create(this,R.raw.helytelen);
    }
    private void sharedPreference(int szamlalo) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putString("szamlalo", String.valueOf(szamlalo)).apply();
    }
    private void hetSharedPreference(double het) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putFloat("het", (float) het).apply();

    }
    private void haviSharedPreference(double honap) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putFloat("honap", (float) honap).apply();

    }
    private void evSharedPreference(double ev) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putFloat("ev", (float) ev).apply();

    }

    private void hetosszesSharedPreference(double hetosszes) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putFloat("hetosszes", (float) hetosszes).apply();

    }
    private void honaposszesSharedPreference(double honaposszes) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putFloat("honaposszes", (float) honaposszes).apply();

    }
    private void evosszesSharedPreference(double evosszes) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putFloat("evosszes", (float) evosszes).apply();

    }
}


