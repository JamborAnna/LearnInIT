package com.example.learninit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StatisztikaActivity extends AppCompatActivity {
private Button visszatanulnimenube;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statisztika);
        init();
        visszatanulnimenube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisztikaActivity.this,TanulasmenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void init() {
        visszatanulnimenube=findViewById(R.id.visszatanulnimenube);
    }
}
