package com.example.learninit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class noConnect extends AppCompatActivity {
    private Button kapcsolodasBut;
    private LottieAnimationView lottiAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connect);
        init();

        if (!vanWifi()){
            Toast.makeText(noConnect.this, "Csatlakozz az internethez!", Toast.LENGTH_SHORT).show();
        }
        else   {

            Intent intent = new Intent(noConnect.this,MainActivity.class);
            startActivity(intent);
            finish();
            lottiAnim.setVisibility(View.GONE);
        }

        kapcsolodasBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(noConnect.this,MainActivity.class);
                startActivity(intent);
                finish();
                lottiAnim.setVisibility(View.GONE);
            }
        });


    }

    private void init() {
        kapcsolodasBut=findViewById(R.id.kapcsolodasBut);
        lottiAnim = findViewById(R.id.lottieAnimation);

    }

    private  boolean vanWifi(){
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null;

    }
}
