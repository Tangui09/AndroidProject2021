package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project.R;
import com.example.project.activities.CompareActivity;
import com.example.project.activities.DriverActivity;

public class MainActivity extends AppCompatActivity {


    private Button btnlastrace;
    private Button btnsearch_driver;
    private Button btnCompare;
    private Button btnsearch_circuits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlastrace = findViewById(R.id.btnlastcircuit);
        btnsearch_circuits = findViewById(R.id.btnsearch_circuits);
        btnsearch_driver = findViewById(R.id.btnsearch_driver);
        btnCompare = findViewById(R.id.btncompare);


        btnlastrace.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InfoCircuitActivity.class);
                intent.putExtra("Race","last");
                startActivity(intent);
            }
        });
        btnsearch_circuits.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CircuitActivity.class);
                startActivity(intent);
            }
        });
        btnsearch_driver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DriverActivity.class);
                intent.putExtra("Activity"," ");
                startActivity(intent);
            }
        });
        btnCompare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CompareActivity.class);
                startActivity(intent);
            }
        });

    }
    //TODO : faire search circuits + résultats de la course : pos , no , Driver, Constructor , Laps, Grid , Time , Status , Points ex : http://ergast.com/api/f1/current/last/results
    //TODO : trouver d'autres critères de comparaison pour les drivers
    //TODO : pour la comparaison du meilleur perf diviser par le nombre de course effectué
    //TODO : Faire marcher les boutons "BACK"
}