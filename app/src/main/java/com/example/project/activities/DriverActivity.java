package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.project.async.AsyncJSONDrivers;
import com.example.project.adapters.MyAdapter;
import com.example.project.R;

public class DriverActivity extends AppCompatActivity {

    private MyAdapter adapter;
    private EditText editTextYear;
    private EditText editTextName;
    private String Activity;
    private Button btnback2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);


        Button btnYear = findViewById(R.id.btnYear);
        Button btnName = findViewById(R.id.btnName);
        btnback2 = findViewById(R.id.btnback2);
        editTextYear = findViewById(R.id.editTextYear);
        editTextName = findViewById(R.id.editTextName);


        //for check if the user come from compareActivity
        Bundle extras = getIntent().getExtras();
        Activity = new String(extras.getString("Activity"));
        ListView list = findViewById(R.id.list_InfoCircuits);
        adapter = new MyAdapter();
        list.setAdapter(adapter);
        list.setDivider(null);


        btnYear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String texteditTextYear = editTextYear.getText().toString() ;
                if(!texteditTextYear.matches("")){
                    AsyncJSONDrivers task = new AsyncJSONDrivers(adapter);
                    String url = "https://ergast.com/api/f1/"+texteditTextYear+"/drivers.json";
                    task.execute(url);
                }


            }
        });
        btnName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String texteditTextName = editTextName.getText().toString() ;
                if(!texteditTextName.matches("")){
                    AsyncJSONDrivers task = new AsyncJSONDrivers(adapter);
                    String url = "https://ergast.com/api/f1/drivers/"+texteditTextName+".json";
                    task.execute(url);
                }

            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), InfoDriverActivity.class);
                Object data = adapter.getItem(position);
                String data2 = data+"";
                intent.putExtra("name",data2);
                intent.putExtra("Activity",Activity);
                startActivity(intent);
            }
        });

        btnback2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}