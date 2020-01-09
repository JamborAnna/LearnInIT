package com.example.learninit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class TanultActivity extends AppCompatActivity {
    private Button visszaTanult;
    private TextView tanault;
    private ListView tanultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanult);
        init();


        visszaTanult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TanultActivity.this,TanulasmenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void init() {
        visszaTanult=findViewById(R.id.visszaTanult);
        tanault=findViewById(R.id.tanault);
        tanultList=findViewById(R.id.tanultList);
    }
}
