package com.example.learninit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


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
        float hetosszes = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("hetosszes", 0)));
        hetosszesSharedPreference(hetosszes);
        float evosszes = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("evosszes", 0)));
        evosszesSharedPreference(evosszes);
        float honaposszes = Float.parseFloat(String.valueOf(getSharedPreferences("szam", Context.MODE_PRIVATE).getFloat("honaposszes", 0)));
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


        visszatanulnimenube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisztikaActivity.this, Fomenu.class);
                startActivity(intent);
                finish();
            }
        });
        double eredmenyhet= (het/hetosszes*1.0)*100;
        double eredmenyhonap=(honap/honaposszes*1.0)*100;
        double eredmenyev=(ev/evosszes*1.0)*100;

                hetiStatview.setText(Math.round(het)+" "+"/"+" "+Math.round(hetosszes)+"= "+Math.round(eredmenyhet)+"%");
                haviStatisztikaView.setText(Math.round(honap)+" "+"/"+" "+Math.round(honaposszes)+"= "+Math.round(eredmenyhonap)+"%");
                evStatisztikaView.setText(Math.round(ev)+" "+"/"+" "+Math.round(evosszes)+"= "+Math.round(eredmenyev)+"%");





    }
    public void onBackPressed(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(StatisztikaActivity.this);
        View alertViev= getLayoutInflater().inflate(R.layout.alert_dialog_style,null);
        Button igenBut=(Button)alertViev.findViewById(R.id.igenBut);
        Button nemBut=(Button)alertViev.findViewById(R.id.nemBut);

        alertDialogBuilder.setView(alertViev);
        final AlertDialog alert = alertDialogBuilder.create();
        alert.show();

        igenBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatisztikaActivity.this, Bejelentkezes.class);
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
