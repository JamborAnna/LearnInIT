package com.example.learninit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TanulasmenuActivity extends AppCompatActivity {

    private Button visszatanulas;
    private  Button napibut;
    private  Button tanultBut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanulasmenu);
        init();
        visszatanulas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TanulasmenuActivity.this,fomenu.class);
                startActivity(intent);
                finish();
            }
        });
        //itt kezdődik majd a tanulás
        napibut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TanulasmenuActivity.this,fomenu.class);
                startActivity(intent);
                finish();
            }
        });
        tanultBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TanulasmenuActivity.this,TanultActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void init() {
        visszatanulas=findViewById(R.id.visszatanulas);
        napibut=findViewById(R.id.napibut);
        tanultBut=findViewById(R.id.tanultBut);
    }
}
