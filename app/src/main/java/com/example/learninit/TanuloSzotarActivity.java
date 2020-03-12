package com.example.learninit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.TextKeyListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TanuloSzotarActivity extends AppCompatActivity {
    private Button visszaTanult;
    private TextView tanuloSZotar;

    private ListView lista;
    private ArrayList<String> arrayList;
    private  CustonAdapter custonAdapter;
   private DatabaseReference databaseReference;
   private FirebaseDatabase firebaseDatabase;
   private  SzotarList szotarLista;
   private  TextView magyarSzo,angolSzo;
   private List<SzotarList>szavak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanuloszotar);
        init();

        //firebaseDatabase=firebaseDatabase.getReference();
       databaseReference=FirebaseDatabase.getInstance().getReference("szotar");
       //lista.setAdapter(arrayAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {

                    szotarLista =ds.getValue(SzotarList.class);
                   szavak.add(szotarLista);
                    //arrayList.add(szotarLista.getMagyar()+"/"+ szotarLista.getAngol());
                }
                custonAdapter= new CustonAdapter(szavak);
                lista.setAdapter(custonAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        visszaTanult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TanuloSzotarActivity.this,TanulasmenuActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
    class  CustonAdapter extends BaseAdapter{
        List<SzotarList> lista;

        public CustonAdapter(List<SzotarList> lista) {
            this.lista = lista;
        }

        @Override
        public int getCount() {
            return (lista.size());
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=getLayoutInflater().inflate(R.layout.szotarinfo,parent,false);
            angolSzo =convertView.findViewById(R.id.angolSzo);
            magyarSzo=convertView.findViewById(R.id.magyarSzo);
            Log.w("",lista.get(position).getMagyar());
            Log.w("",lista.get(position).getAngol());
            String upperStringMagyar = lista.get(position).getMagyar().substring(0, 1).toUpperCase() + lista.get(position).getMagyar().substring(1).toLowerCase();
            String upperStringAngol = lista.get(position).getAngol().substring(0, 1).toUpperCase() + lista.get(position).getAngol().substring(1).toLowerCase();
            magyarSzo.setText(upperStringMagyar);
            angolSzo.setText(upperStringAngol);
            return convertView;
        }

    }
    public void onBackPressed(){
         AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TanuloSzotarActivity.this);
         View alertViev= getLayoutInflater().inflate(R.layout.alert_dialog_style,null);
        Button igenBut=(Button)alertViev.findViewById(R.id.igenBut);
        Button nemBut=(Button)alertViev.findViewById(R.id.nemBut);

        alertDialogBuilder.setView(alertViev);
        final AlertDialog alert = alertDialogBuilder.create();
        alert.show();

        igenBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TanuloSzotarActivity.this, Bejelentkezes.class);
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
        visszaTanult=findViewById(R.id.visszaTanult);
        tanuloSZotar =findViewById(R.id.tanault);
        lista =findViewById(R.id.tanultList);
        szavak= new ArrayList<>();


        arrayList=new ArrayList<>();

        szotarLista =new SzotarList();
        custonAdapter= new CustonAdapter(szavak);
    }

}
