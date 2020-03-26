package com.example.learninit;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class Tanulas3Activity extends AppCompatActivity  {
    private Button tanulas3vissza;
    private ImageButton imageBut1;
    private TextView BekerdezendoSzoViev;
    private  ImageButton imageBut2;
    private MediaPlayer helyesMP3, helytelenMP3;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanulas3);
        init();
        final Random rnd = new Random();



        float hetosszes = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("hetosszes", 0)));
        hetosszes++;
        hetosszesSharedPreference(hetosszes);
        float evosszes = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("evosszes", 0)));
        evosszes++;
        evosszesSharedPreference(evosszes);
        float honaposszes = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("honaposszes", 0)));
        honaposszes++;
        honaposszesSharedPreference(honaposszes);
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


        final int[] a = {0};




        final int randomBut= rnd.nextInt(3);

        int r = rnd.nextInt(181);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("szotar").child(String.valueOf(r));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int rndomN=rnd.nextInt(181);

                    DatabaseReference data = FirebaseDatabase.getInstance().getReference().child("szotar").child(String.valueOf(rndomN));
                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                           String idKov = dataSnapshot.child("kep_id").getValue().toString();
                            Log.w("",idKov);
                            if (randomBut == 1){
                                Glide.with(Tanulas3Activity.this).load( getDrawable(getResources().getIdentifier(idKov,"drawable",getPackageName()))).centerCrop().into(imageBut1);
                                a[0] =1;
                            }
                            else {
                                Glide.with(Tanulas3Activity.this).load( getDrawable(getResources().getIdentifier(idKov,"drawable",getPackageName()))).centerCrop().into(imageBut2);
                                a[0] =0;
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                     final String magyar = dataSnapshot.child("magyar").getValue().toString();
                     final String kep_id = dataSnapshot.child("kep_id").getValue().toString();
                    final String angol = dataSnapshot.child("angol").getValue().toString();
                     final String szotarID=dataSnapshot.child("szo_id").getValue().toString();

                     BekerdezendoSzoViev.setText(magyar);
                    if (kep_id!=null){
                        Log.w("",kep_id);
                        if (randomBut==1)
                        {
                            Glide.with(Tanulas3Activity.this).load( getDrawable(getResources().getIdentifier(kep_id,"drawable",getPackageName()))).centerCrop().into(imageBut2);
                            a[0] =1;

                        }else {
                            Glide.with(Tanulas3Activity.this).load( getDrawable(getResources().getIdentifier(kep_id,"drawable",getPackageName()))).centerCrop().into(imageBut1);
                            a[0] =0;

                        }


                        final int finalA = a[0];
                        imageBut2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Random randomom = new Random();
                                final int number= randomom.nextInt(3)+1;

                                int szamlalo = Integer.parseInt(getSharedPreferences("szam", Context.MODE_PRIVATE).getString("szamlalo", ""));
                                szamlalo++;
                                sharedPreference(szamlalo);

                                sharedPreference(szamlalo);
                                if (szamlalo>10){
                                    Intent intent = new Intent(Tanulas3Activity.this,TanulasmenuActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    if (finalA ==1){

                                        Toast.makeText(Tanulas3Activity.this, "Helyes válasz!", Toast.LENGTH_SHORT).show();
                                        float het =Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("het", 0)));
                                        het++;
                                        hetSharedPreference(het);

                                        float honap = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("honap", 0)));
                                        honap++;
                                        haviSharedPreference(honap);

                                        float ev = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("ev", 0)));
                                        ev++;
                                        evSharedPreference(ev);


                                        helyesMP3.start();

                                        if (number == 1) {
                                            Intent intent = new Intent(Tanulas3Activity.this, Tanulas1Activity.class);
                                            startActivity(intent);
                                            finish();

                                        } else if (number == 2) {
                                            Intent intent = new Intent(Tanulas3Activity.this, Tanulas2Activity.class);
                                            startActivity(intent);
                                            finish();

                                        } else if (number == 3) {
                                            Intent intent = new Intent(Tanulas3Activity.this, Tanulas3Activity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }else {

                                        helytelenMP3.start();


                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Tanulas3Activity.this);
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
                                                    Intent intent = new Intent(Tanulas3Activity.this, Tanulas1Activity.class);
                                                    startActivity(intent);
                                                    finish();

                                                } else if (number == 2) {
                                                    Intent intent = new Intent(Tanulas3Activity.this, Tanulas2Activity.class);
                                                    startActivity(intent);
                                                    finish();

                                                } else if (number == 3) {
                                                    Intent intent = new Intent(Tanulas3Activity.this, Tanulas3Activity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }
                                        });





                                    }

                                }

                            }
                        });


                        imageBut1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Random randomom = new Random();
                                final int number = randomom.nextInt(3) + 1;
                                int szamlalo = Integer.parseInt(getSharedPreferences("szam", Context.MODE_PRIVATE).getString("szamlalo", "0"));
                                szamlalo++;
                                sharedPreference(szamlalo);

                                if (szamlalo > 10) {
                                    Intent intent = new Intent(Tanulas3Activity.this, TanulasmenuActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    if (finalA==0) {

                                        helyesMP3.start();

                                        Toast.makeText(Tanulas3Activity.this, "Helyes válasz!", Toast.LENGTH_SHORT).show();
                                        float het = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("het", 0)));
                                        het++;
                                        hetSharedPreference(het);

                                        float honap = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("honap", 0)));
                                        honap++;
                                        haviSharedPreference(honap);

                                        float ev = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("ev", 0)));
                                        ev++;
                                        evSharedPreference(ev);
                                        if (number == 1) {
                                            Intent intent = new Intent(Tanulas3Activity.this, Tanulas1Activity.class);
                                            startActivity(intent);
                                            finish();

                                        } else if (number == 2) {
                                            Intent intent = new Intent(Tanulas3Activity.this, Tanulas2Activity.class);
                                            startActivity(intent);
                                            finish();

                                        } else if (number == 3) {
                                            Intent intent = new Intent(Tanulas3Activity.this, Tanulas3Activity.class);
                                            startActivity(intent);
                                            finish();
                                        }


                                    }else{



                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Tanulas3Activity.this);
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
                                                    Intent intent = new Intent(Tanulas3Activity.this, Tanulas1Activity.class);
                                                    startActivity(intent);
                                                    finish();

                                                } else if (number == 2) {
                                                    Intent intent = new Intent(Tanulas3Activity.this, Tanulas2Activity.class);
                                                    startActivity(intent);
                                                    finish();

                                                } else if (number == 3) {
                                                    Intent intent = new Intent(Tanulas3Activity.this, Tanulas3Activity.class);
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

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tanulas3vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tanulas3Activity.this,TanulasmenuActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
    private void init() {
        tanulas3vissza=findViewById(R.id.tanulas3vissza);
        imageBut1=findViewById(R.id.imageBut1);
        BekerdezendoSzoViev=findViewById(R.id.BekerdezendoSzoViev);
        imageBut2=findViewById(R.id.imageBut2);
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
    @Override
    public void onBackPressed(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Tanulas3Activity.this);
        View alertViev= getLayoutInflater().inflate(R.layout.alert_dialog_style,null);
        Button igenBut=(Button)alertViev.findViewById(R.id.igenBut);
        Button nemBut=(Button)alertViev.findViewById(R.id.nemBut);

        alertDialogBuilder.setView(alertViev);
        final AlertDialog alert = alertDialogBuilder.create();
        alert.show();

        igenBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tanulas3Activity.this, Bejelentkezes.class);
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

}
