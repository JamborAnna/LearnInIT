package com.example.learninit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Tanulas1Activity extends AppCompatActivity {
private Button tanulas1vissza;
private TextView bekerendoSzoview;
private EditText bekertszoedit;
private  Button tanulas1Ellenorzesbut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanulas1);
        init();
        tanulas1vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tanulas1Activity.this,TanulasmenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void init() {
        tanulas1vissza=findViewById(R.id.tanulas1vissza);
        bekerendoSzoview=findViewById(R.id.bekerendoSzoview);
        bekertszoedit=findViewById(R.id.bekertszoedit);
        tanulas1Ellenorzesbut=findViewById(R.id.tanulas1Ellenorzesbut);



    }
}
