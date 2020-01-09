package com.example.learninit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Tanulas3Activity extends AppCompatActivity {
    private Button tanulas3vissza;
    private ImageButton imageBut1;
    private TextView BekerdezendoSzoViev;
    private  ImageButton imageBut2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanulas3);
        init();

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
    }
}
