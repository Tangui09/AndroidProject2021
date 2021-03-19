package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.adapters.MyAdapter;

public class InfoCircuitActivity extends AppCompatActivity {

    public static TextView textnamecircuit;
    public static TextView textplacecircuit;
    public static TextView dategrandprix;

    private Button btnadd;

    private String url;

    Button driverFragment, teamFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_circuit);

        textnamecircuit = findViewById(R.id.textnamecircuit);
        textplacecircuit = findViewById(R.id.textplacecircuit);
        dategrandprix = findViewById(R.id.dateCircuit);
        btnadd = findViewById(R.id.btnAdd);

        driverFragment = (Button) findViewById(R.id.btnSwitchToDrivers);
        teamFragment = (Button) findViewById(R.id.btnSwitchToTeams);

        Fragment_DriverResult firstFragment = new Fragment_DriverResult();
        Fragment_TeamResult secondFragment = new Fragment_TeamResult();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayoutFragment, firstFragment);
        fragmentTransaction.commit();

        //get the info pass by the Activity Intent
        Bundle extras = getIntent().getExtras();
        String race = new String(extras.getString("Race"));


        driverFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.FrameLayoutFragment, firstFragment);
//                fragmentTransaction.addToBackStack(null);   //Click back to previous fragment
                fragmentTransaction.commit();
                firstFragment.startAsync(url);
            }
        });
        // perform setOnClickListener event on Second Button
        teamFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.FrameLayoutFragment, secondFragment);
//                fragmentTransaction.addToBackStack(null);   //Click back to previous fragment
                fragmentTransaction.commit();
                secondFragment.startAsync(url);
            }
        });

        if(race.equals("last"))        //If it's the last race
        {
            url = "https://ergast.com/api/f1/current/last/results.json";
        }
        else
        { // we get all the info pass by the other Activity intent
            String year = new String(extras.getString("year"));
            String position = new String(extras.getString("position"));
            String Racename = new String(extras.getString("Racename"));

            int positionInt = Integer.parseInt(position);
            positionInt++;

            url = "https://ergast.com/api/f1/"+year+"/"+positionInt+"/results.json";
        }

        firstFragment.startAsync(url);
    }
}