package com.example.learninit;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import static com.example.learninit.TanulasmenuActivity.szamlalo;

public class Tanulas3Activity extends AppCompatActivity  {
    private Button tanulas3vissza;
    private ImageButton imageBut1;
    private TextView BekerdezendoSzoViev;
    private  ImageButton imageBut2;
    private MediaPlayer helyesMP3, helytelenMP3;
    private DatabaseReference databaseReference;
    private TypedArray kepnev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanulas3);
        init();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("szotar").child("0");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    szotar s;
                    //int szoid= (int) dataSnapshot.getChildrenCount();
                    //int rand = new Random().nextInt(szoid);


                    // String szo_id= dataSnapshot.child("szo_id").getValue().toString();


                     final String magyar = dataSnapshot.child("magyar").getValue().toString();
                     String kep_id = dataSnapshot.child("kep_id").getValue().toString();
                    if (kep_id!=null){

                       changeImage();
                       Random random= new Random();
                       int rndomN=random.nextInt(kepnev.length());
                       imageBut2.setBackgroundResource(kepnev.getSourceResourceId(rndomN,0));


                        imageBut2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Random randomom = new Random();
                                int number= randomom.nextInt(3)+1;

                                int szamlalo = Integer.parseInt(getSharedPreferences("szam", Context.MODE_PRIVATE).getString("szamlalo", ""));
                                szamlalo++;
                                sharedPreference(szamlalo);

                                sharedPreference(szamlalo);
                                if (szamlalo>15){
                                    Intent intent = new Intent(Tanulas3Activity.this,TanulasmenuActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    if (magyar.equals(imageBut2)){
                                        Toast.makeText(Tanulas3Activity.this, "Helyes válasz!", Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(Tanulas3Activity.this, "Rossz válasz!", Toast.LENGTH_SHORT).show();
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

                                }

                            }
                        });
                    }
                }
                   /* szo1.setText(angol);
                    szo1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bekertszoedit2.setText(angol);
                        }
                    });*/
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

        imageBut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random randomom = new Random();
                int number= randomom.nextInt(3)+1;
                int szamlalo = Integer.parseInt(getSharedPreferences("szam", Context.MODE_PRIVATE).getString("szamlalo", ""));
                szamlalo++;
                sharedPreference(szamlalo);
                helyesMP3.start();
                Toast.makeText(Tanulas3Activity.this, "Helyes válasz!", Toast.LENGTH_SHORT).show();
                if (szamlalo >15){
                    Intent intent = new Intent(Tanulas3Activity.this,TanulasmenuActivity.class);
                    startActivity(intent);
                    finish();
                }else {

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

            }
        });

    }
    private  void changeImage(){

    }

    private void init() {
        tanulas3vissza=findViewById(R.id.tanulas3vissza);
        imageBut1=findViewById(R.id.imageBut1);
        BekerdezendoSzoViev=findViewById(R.id.BekerdezendoSzoViev);
        imageBut2=findViewById(R.id.imageBut2);
        helyesMP3=MediaPlayer.create(this,R.raw.dicseret);
        helytelenMP3=MediaPlayer.create(this,R.raw.helytelen);
        kepnev=getResources().obtainTypedArray(R.array.kepnev);
    }
    private void sharedPreference(int szamlalo) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putString("szamlalo", String.valueOf(szamlalo)).apply();
    }
}
