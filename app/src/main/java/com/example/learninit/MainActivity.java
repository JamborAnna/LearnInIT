package com.example.learninit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private Button RegisztracioBut;
    private Button BejelentkezesBut;
    private ProgressBar progressMain;
    private static final int kerelemKod = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        engedelyek();


        if (!vanWifi()){

            Intent intent = new Intent(MainActivity.this, noConnect.class);
            startActivity(intent);
            finish();
        }else {


            //Regisztrációs felületre való átirányítás//
            RegisztracioBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressMain.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(MainActivity.this, Regisztracio.class);
                    startActivity(intent);
                    progressMain.setVisibility(View.GONE);
                    finish();
                }
            });

            //Bejelentkezés felületre átirányítás//
            BejelentkezesBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, Bejelentkezes.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

        }
   /* public void onBackPressed(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        View alertViev= getLayoutInflater().inflate(R.layout.alert_dialog_style,null);
        Button igenBut=(Button)alertViev.findViewById(R.id.igenBut);
        Button nemBut=(Button)alertViev.findViewById(R.id.nemBut);

        alertDialogBuilder.setView(alertViev);
        final AlertDialog alert = alertDialogBuilder.create();
        alert.show();

        igenBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Bejelentkezes.class);
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

    }*/
    private  boolean vanWifi(){
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null;

    }
    private void init() {
        RegisztracioBut=findViewById(R.id.RegisztracioBut);
        BejelentkezesBut=findViewById(R.id.BejelentkezesBut);
        progressMain=findViewById(R.id.progressMain);

    }
    private void engedelyek(){
            Log.d(String.valueOf(MainActivity.this), "Engedély kérése");
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};

           if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    permissions[0]) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    permissions[1]) == PackageManager.PERMISSION_GRANTED){




            }else{
                ActivityCompat.requestPermissions(MainActivity.this,
                        permissions,
                        kerelemKod);

            }



    }
}

