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
    private  Button szo1,szo2,szo3,szo4;
    private DatabaseReference databaseReference;
    private MediaPlayer helyesMP3, helytelenMP3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanulas2);
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



        final Random randomom = new Random();
        int rndomN=randomom.nextInt(182);


        Method(rndomN);


        tanulas2vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tanulas2Activity.this,TanulasmenuActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    private void Method(final int rndomN) {
        final Random randomom= new Random();
        final int randomHelyValaszto= randomom.nextInt(4);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("szotar").child(String.valueOf(rndomN));
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String magyar = dataSnapshot.child("magyar").getValue().toString();
                final String angol = dataSnapshot.child("angol").getValue().toString();
                Random rnd= new Random();

                int rndomNum=rnd.nextInt(182);
                while (rndomNum==rndomN){
                     rndomNum=rnd.nextInt(182);
                }


                if (randomHelyValaszto==0){
                 szo1.setText(angol);
                }

                   DatabaseReference data=FirebaseDatabase.getInstance().getReference().child("szotar").child(String.valueOf(rndomN));
                final int finalRndomNum = rndomNum;
                data.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           final String kovSzo0 = dataSnapshot.child("angol").getValue().toString();

                           if (randomHelyValaszto==0){
                               szo1.setText(angol);
                           }
                           else {
                               szo1.setText(kovSzo0);
                           }
                           Random rnd= new Random();
                           int rndomN1=rnd.nextInt(182);
                           while (rndomN1==rndomN || rndomN1== finalRndomNum){
                               rndomN1=rnd.nextInt(182);

                           }

                           DatabaseReference data = FirebaseDatabase.getInstance().getReference().child("szotar").child(String.valueOf(rndomN));
                           final int finalRndomN = rndomN1;
                           final int finalRndomN1 = rndomN1;
                           final int finalRndomN2 = rndomN1;
                           data.addValueEventListener(new ValueEventListener() {
                               @Override
                               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                   final String kovSzo = dataSnapshot.child("angol").getValue().toString();
                                   if (randomHelyValaszto==1){
                                       szo2.setText(angol);
                                   }else{
                                       szo2.setText(kovSzo);
                                   }

                                   Random rnd= new Random();
                                   int rndomN2=rnd.nextInt(182);
                                   while (rndomN2==rndomN || rndomN2== finalRndomNum || rndomN2== finalRndomN1){
                                       rndomN2=rnd.nextInt(182);

                                   }

                                   DatabaseReference data1 = FirebaseDatabase.getInstance().getReference().child("szotar").child(String.valueOf(rndomN));
                                   final int finalRndomN2 = rndomN2;
                                   data1.addValueEventListener(new ValueEventListener() {
                                       @Override
                                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                           final String kovSzo1 = dataSnapshot.child("angol").getValue().toString();
                                           if (randomHelyValaszto==2){
                                               szo3.setText(angol);
                                           }else { szo3.setText(kovSzo1);}


                                           Random rnd= new Random();
                                           int rndomN3=rnd.nextInt(182);
                                           while (rndomN3==rndomN || rndomN3==finalRndomNum || rndomN3== finalRndomN1 || rndomN3==finalRndomN2){
                                               rndomN3=rnd.nextInt(182);

                                           }

                                           DatabaseReference data1 = FirebaseDatabase.getInstance().getReference().child("szotar").child(String.valueOf(rndomN));
                                           data1.addValueEventListener(new ValueEventListener() {
                                               @Override
                                               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                   final String kovSzo2 = dataSnapshot.child("angol").getValue().toString();
                                                   if (randomHelyValaszto==3){
                                                       szo4.setText(angol);

                                                   }else {
                                                       szo4.setText(kovSzo2);
                                                   }

                                                   szo1.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View v) {
                                                           if (randomHelyValaszto==0)
                                                               bekertszoedit2.setText(angol);
                                                           else
                                                               bekertszoedit2.setText(kovSzo0);
                                                       }
                                                   });
                                                   szo2.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View v) {
                                                           if (randomHelyValaszto==1)
                                                               bekertszoedit2.setText(angol);
                                                           else
                                                               bekertszoedit2.setText(kovSzo);

                                                       }
                                                   });
                                                   szo3.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View v) {

                                                           if (randomHelyValaszto==2){
                                                               bekertszoedit2.setText(angol);
                                                           }else { bekertszoedit2.setText(kovSzo1);}

                                                       }
                                                   });
                                                   szo4.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View v) {
                                                           if (randomHelyValaszto==3){
                                                               bekertszoedit2.setText(angol);
                                                           }else {
                                                               bekertszoedit2.setText(kovSzo2);
                                                           }


                                                       }
                                                   });
                                               }

                                               @Override
                                               public void onCancelled(@NonNull DatabaseError databaseError) {

                                               }
                                           });

                                       }

                                       @Override
                                       public void onCancelled(@NonNull DatabaseError databaseError) {

                                       }
                                   });
                               }

                               @Override
                               public void onCancelled(@NonNull DatabaseError databaseError) {

                               }
                           });

                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });





                if (dataSnapshot.exists()) {

                    bekerendoSzoview2.setText(magyar);



                    tanulas1Ellenorzesbut2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Random randomom = new Random();
                            int number= randomom.nextInt(3)+1;

                            int szamlalo = Integer.parseInt(getSharedPreferences("szam", Context.MODE_PRIVATE).getString("szamlalo", ""));
                            szamlalo++;
                            sharedPreference(szamlalo);

                            if (szamlalo>10){
                                Intent intent = new Intent(Tanulas2Activity.this, TanulasmenuActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                if (bekertszoedit2.getText().toString().equals(angol) ) {
                                    helyesMP3.start();
                                    Toast.makeText(Tanulas2Activity.this, "Helyes válasz!", Toast.LENGTH_SHORT).show();
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
    }

    private void init() {
        tanulas2vissza=findViewById(R.id.tanulas2vissza);
        bekerendoSzoview2=findViewById(R.id.bekerendoSzoview2);
        bekertszoedit2=findViewById(R.id.bekertszoedit2);
        tanulas1Ellenorzesbut2=findViewById(R.id.tanulas1Ellenorzesbut2);
        szo1=findViewById(R.id.szo1);
        szo2=findViewById(R.id.szo2);
        szo3=findViewById(R.id.szo3);
        szo4=findViewById(R.id.szo4);
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

