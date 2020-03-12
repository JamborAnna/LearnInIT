package com.example.learninit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class KijelentkezesActivity extends AppCompatActivity {
private TextView bucsuview;
private Button Bejelentkezeshez;
    private LottieAnimationView lottiAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        lottiAnim.setVisibility(View.VISIBLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kijelentkezes);
        init();
        Bejelentkezeshez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KijelentkezesActivity.this,Bejelentkezes.class);
                startActivity(intent);
                finish();
                lottiAnim.setVisibility(View.GONE);

            }
        });
    }
    public void onBackPressed(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(KijelentkezesActivity.this);
        View alertViev= getLayoutInflater().inflate(R.layout.alert_dialog_style,null);
        Button igenBut=(Button)alertViev.findViewById(R.id.igenBut);
        Button nemBut=(Button)alertViev.findViewById(R.id.nemBut);

        alertDialogBuilder.setView(alertViev);
        final AlertDialog alert = alertDialogBuilder.create();
        alert.show();

        igenBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KijelentkezesActivity.this, Bejelentkezes.class);
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
        bucsuview=findViewById(R.id.bucsuview);
        Bejelentkezeshez=findViewById(R.id.Bejelentkezeshez);

    }
}
