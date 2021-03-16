package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project.DriverResultFragment;
import com.example.project.R;
import com.example.project.TeamResultFragment;
import com.example.project.adapters.MyAdapteInfoCircuit;
import com.example.project.async.AsyncJSONResultsCircuit;

public class InfoCircuitActivity extends AppCompatActivity {

    public AppCompatActivity myActivity;
    public static TextView textnamecircuit;
    public static TextView textplacecircuit;
    public static TextView dategrandprix;
    private TextView text_circuit;
    private ListView list;
    private MyAdapteInfoCircuit adapter;
    private Button btnadd;

    Button driverFragment, teamFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_circuit);

        textnamecircuit = findViewById(R.id.textnamecircuit);
        textplacecircuit = findViewById(R.id.textplacecircuit);
        dategrandprix = findViewById(R.id.dateCircuit);
        btnadd = findViewById(R.id.btnAdd);
        list = findViewById(R.id.list_InfoCircuits);

        driverFragment = (Button) findViewById(R.id.btnSwitchToDrivers);
        teamFragment = (Button) findViewById(R.id.btnSwitchToTeams);

        //get the info pass by the Activity Intent
        Bundle extras = getIntent().getExtras();
        String race = new String(extras.getString("Race"));

        adapter = new MyAdapteInfoCircuit();
        list.setAdapter(adapter);
        list.setDivider(null);



        driverFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // load First Fragment
                loadFragment(new DriverResultFragment());
            }
        });
        // perform setOnClickListener event on Second Button
        teamFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // load Second Fragment
                loadFragment(new TeamResultFragment());
            }
        });


        String url;
        if(race.equals("last"))        //If it's the last race
        {
            url = "https://ergast.com/api/f1/current/last/results.json";
        }
        else
        {
            String year = new String(extras.getString("year"));
            String position = new String(extras.getString("position"));
            String Racename = new String(extras.getString("Racename"));

            int positionInt = Integer.parseInt(position);
            positionInt++;

            url = "https://ergast.com/api/f1/"+year+"/"+positionInt+"/results.json";
        }

        AsyncJSONResultsCircuit task = new AsyncJSONResultsCircuit(adapter);
        task.execute(url);
    }


    private void loadFragment(Fragment fragment)
    {
        // create a FragmentManager
        FragmentManager fm = getFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayoutResultsRace, fragment);
        fragmentTransaction.commit(); // save the changes
    }
}