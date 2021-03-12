package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.project.R;
import com.example.project.adapters.MyAdapterCircuits;
import com.example.project.async.AsyncJSONCircuits;

public class CircuitActivity extends AppCompatActivity {

    private MyAdapterCircuits adapter;
    private EditText editTextYear;
    private Button btnYear;
    private Button btnback3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circuit);

        btnYear = findViewById(R.id.btnYear4);
        btnback3 = findViewById(R.id.btnback3);
        editTextYear = findViewById(R.id.editTextYear4);

        //for check if the user come from compareActivity
        Bundle extras = getIntent().getExtras();
        ListView list = findViewById(R.id.list_InfoCircuits);
        adapter = new MyAdapterCircuits();
        list.setAdapter(adapter);
        list.setDivider(null);


        btnYear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String texteditTextYear = editTextYear.getText().toString() ;
                if(!texteditTextYear.matches("")){
                    AsyncJSONCircuits task = new AsyncJSONCircuits(adapter);
                    String url = "https://ergast.com/api/f1/"+texteditTextYear+"/results.json?limit=1000";
                    task.execute(url);
                    //we need to upgrade the limit in order to display all the circuits
                    //for this url it was better to pass with another url but it was
                    //impossible to access to the result of the circuit by his id even with number because the display order was not the same
                }


            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), InfoCircuitActivity.class);
                String texteditTextYear = editTextYear.getText().toString() ;
                Object data = adapter.getItemName(position);
                String data2 = data+"";
                intent.putExtra("Race","not last");
                intent.putExtra("year",texteditTextYear);
                intent.putExtra("position",String.valueOf(position));
                intent.putExtra("Racename",data2);
                startActivity(intent);
            }
        });

        btnback3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}