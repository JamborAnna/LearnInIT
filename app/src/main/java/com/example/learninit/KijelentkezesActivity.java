package com.example.learninit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class KijelentkezesActivity extends AppCompatActivity {
private TextView bucsuview;
private Button Bejelentkezeshez;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kijelentkezes);
        init();
        Bejelentkezeshez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KijelentkezesActivity.this,Bejelentkezes.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void init() {
        bucsuview=findViewById(R.id.bucsuview);
        Bejelentkezeshez=findViewById(R.id.Bejelentkezeshez);

    }
}
