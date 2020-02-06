package com.example.learninit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Tanulas2Activity extends AppCompatActivity {
    private Button tanulas2vissza;
    private TextView bekerendoSzoview2;
    private EditText bekertszoedit2;
    private  Button tanulas1Ellenorzesbut2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanulas2);
        init();

        tanulas2vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tanulas2Activity.this,TanulasmenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        tanulas1Ellenorzesbut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random randomom = new Random();
                int number= randomom.nextInt(3)+1;
                TanulasmenuActivity.szamlalo++;
                if (TanulasmenuActivity.szamlalo==15){
                    Intent intent = new Intent(Tanulas2Activity.this,TanulasmenuActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    if (bekerendoSzoview2 == bekertszoedit2) {
                        if (number == 1) {
                            Intent intent = new Intent(Tanulas2Activity.this, Tanulas1Activity.class);
                            startActivity(intent);
                            finish();

                        } else if (number == 2) {
                            Intent intent = new Intent(Tanulas2Activity.this, Tanulas2Activity.class);
                            startActivity(intent);
                            finish();

                        } else if (number == 3) {
                            Intent intent = new Intent(Tanulas2Activity.this, Tanulas3Activity.class);
                            startActivity(intent);
                            finish();
                        }

                    } else {

                        Toast.makeText(Tanulas2Activity.this, "Rossz válasz!", Toast.LENGTH_LONG).show();
                        if (number == 1) {
                            Intent intent = new Intent(Tanulas2Activity.this, Tanulas1Activity.class);
                            startActivity(intent);
                            finish();

                        } else if (number == 2) {
                            Intent intent = new Intent(Tanulas2Activity.this, Tanulas2Activity.class);
                            startActivity(intent);
                            finish();

                        } else if (number == 3) {
                            Intent intent = new Intent(Tanulas2Activity.this, Tanulas3Activity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                }
            }
        });



    }

    private void init() {
        tanulas2vissza=findViewById(R.id.tanulas2vissza);
        bekerendoSzoview2=findViewById(R.id.bekerendoSzoview2);
        bekertszoedit2=findViewById(R.id.bekertszoedit2);
        tanulas1Ellenorzesbut2=findViewById(R.id.tanulas1Ellenorzesbut2);

    }
}
