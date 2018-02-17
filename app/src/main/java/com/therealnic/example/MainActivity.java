package com.therealnic.example;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnVisualizza;
    Button btnNextActivity;
    EditText etMessagge;
    Switch swActivate;
    Toast t;
    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("configurazione",MODE_PRIVATE);
        System.out.println("L'ultimo valore salvato è: " + sp.getInt("x",0));
        System.out.println("Nella data: " + sp.getString("data",""));

        if(savedInstanceState!=null) {
            x = savedInstanceState.getInt("Numero");
        }

        btnVisualizza = (Button) findViewById(R.id.btnVisualizza);
        btnNextActivity = (Button) findViewById((R.id.btnNextActivity));
        etMessagge = (EditText) findViewById(R.id.etMessagge);
        swActivate = (Switch) findViewById(R.id.swActivate);

        btnVisualizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(swActivate.isChecked()) {
                    //System.out.println("ME GATU SCHIANSA");
                    Log.d("btn","ME GATU SCHIANSA");
                    x++;
                    String s= x+". "+etMessagge.getText();
                    t=Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
                } else {
                    t=Toast.makeText(getApplicationContext(), getString(R.string.vActivate3), Toast.LENGTH_SHORT);
                }
                t.show();
            }
        });

        btnNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                i.putExtra("x", x);
                startActivity(i);
            }
        });

        swActivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(swActivate.isChecked())
                    t=Toast.makeText(getApplicationContext(), getString(R.string.vActivate1), Toast.LENGTH_SHORT);
                else
                    t=Toast.makeText(getApplicationContext(), getString(R.string.vActivate2), Toast.LENGTH_SHORT);
                t.show();
            }
        });
    }

    @Override
    protected void onStart() {
        Log.i("Ciclo Di Vita","onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.i("Ciclo Di Vita","onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i("Ciclo Di Vita","onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("Ciclo Di Vita","onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        //Salvo configurazione
        SharedPreferences.Editor editor = getSharedPreferences("configurazione", MODE_PRIVATE).edit();
        editor.putInt("x",x);
        editor.putString("data", Calendar.getInstance().toString());
        editor.apply();

        Log.i("Ciclo Di Vita","onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("Ciclo Di Vita","onDestroy");
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("Numero", x);
        super.onSaveInstanceState(outState);
        Log.i("Save","Salvo il Bundle");
    }
}