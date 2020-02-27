package com.example.learninit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.learninit.TanulasmenuActivity.szamlalo;

public class StatisztikaActivity extends AppCompatActivity {
private Button visszatanulnimenube;
private TextView hetiStatview,haviStatisztikaView,evStatisztikaView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statisztika);
        init();

        int het = Integer.parseInt(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getInt("het", 0)));
        hetSharedPreference(het);
        int honap = Integer.parseInt(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getInt("honap", 0)));
        haviSharedPreference(honap);
        int ev = Integer.parseInt(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getInt("ev", 0)));
        evSharedPreference(ev);
        int osszes = Integer.parseInt(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getInt("osszes", 0)));
        osszesSharedPreference(osszes);
        int osszes1 = Integer.parseInt(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getInt("osszes", 0)));
        osszesSharedPreference(osszes1);
        int osszes2 = Integer.parseInt(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getInt("osszes", 0)));
        osszesSharedPreference(osszes2);


        visszatanulnimenube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisztikaActivity.this, Fomenu.class);
                startActivity(intent);
                finish();
            }
        });

       int eredmenyhet= (int) (osszes/het)*100;
        if (osszes<106){
            if (osszes<105){
                hetiStatview.setText(het+" "+"/"+" "+osszes+"= "+Math.floor(eredmenyhet)+"%");
            }else {
                osszes=0;
                hetiStatview.setText(osszes+" "+"/"+" "+het+"= "+(Math.round(osszes/het))+"%");
            }

        }
        else if (osszes1<=451){
            if (osszes1<450)
                haviStatisztikaView.setText(osszes1+" "+"/"+honap+"="+(Math.round(osszes1/honap)*100)+"%");
            else {
                osszes1=0;
                haviStatisztikaView.setText(osszes1+" "+"/"+honap+"="+(Math.round(osszes1/honap)*100)+"%");
            }
        }
        else if(osszes2<=5476){
            if (osszes2<5475){
                evStatisztikaView.setText(osszes2+" "+"/"+ev+"="+(Math.round(osszes2/ev)*100)+"%");
            }else{
                osszes2=0;
                evStatisztikaView.setText(osszes2+" "+"/"+ev+"="+(Math.round(osszes2/ev)*100)+"%");
            }

        }


    }

    private void init() {

        visszatanulnimenube=findViewById(R.id.visszatanulnimenube);
        hetiStatview=findViewById(R.id.hetiStatview);
        haviStatisztikaView=findViewById(R.id.haviStatisztikaView);
        evStatisztikaView=findViewById(R.id.evStatisztikaView);



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
