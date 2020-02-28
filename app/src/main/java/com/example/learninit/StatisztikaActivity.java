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

        float het =Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("het", 0)));
        hetSharedPreference(het);
        float honap = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("honap", 0)));
        haviSharedPreference(honap);
        float ev = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("ev", 0)));
        evSharedPreference(ev);
        float osszes = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("osszes", 0)));
        osszesSharedPreference(osszes);



        visszatanulnimenube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisztikaActivity.this, Fomenu.class);
                startActivity(intent);
                finish();
            }
        });
        double eredmenyhet= (het/osszes*1.0)*100;
        double eredmenyhonap=(honap/osszes*1.0)*100;
        double eredmenyev=(ev/osszes*1.0)*100;

                hetiStatview.setText(Math.round(het)+" "+"/"+" "+Math.round(osszes)+"= "+Math.round(eredmenyhet)+"%");
                haviStatisztikaView.setText(Math.round(honap)+" "+"/"+" "+Math.round(osszes)+"= "+Math.round(eredmenyhonap)+"%");
                evStatisztikaView.setText(Math.round(ev)+" "+"/"+" "+Math.round(osszes)+"= "+Math.round(eredmenyev)+"%");





    }

    private void init() {

        visszatanulnimenube=findViewById(R.id.visszatanulnimenube);
        hetiStatview=findViewById(R.id.hetiStatview);
        haviStatisztikaView=findViewById(R.id.haviStatisztikaView);
        evStatisztikaView=findViewById(R.id.evStatisztikaView);



    }
    private void hetSharedPreference(float het) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putFloat("het",  het).apply();

    }
    private void haviSharedPreference(float honap) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putFloat("honap",  honap).apply();

    }
    private void evSharedPreference(float ev) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putFloat("ev", ev).apply();

    }
    private void osszesSharedPreference(double osszes) {
        SharedPreferences s = getSharedPreferences("szam", Context.MODE_PRIVATE);
        s.edit().putFloat("osszes", (float) osszes).apply();

    }
}
