package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import com.example.project.DriverCompare;
import com.example.project.async.AsyncJSONDatacompare;
import com.example.project.adapters.MyAdapter;
import com.example.project.adapters.MyAdapterCompare;
import com.example.project.R;

import static com.example.project.utils.Constant.PREF_COMPARE;
import static com.example.project.utils.Constant.PREF_DRIVERS;

public class CompareActivity extends AppCompatActivity {

    private Button btnreset;
    private Button btnaddcompare;
    private Button btncompare;
    private Button btnback;
    private Button btnfilter;
    private CheckBox checkwin;
    private CheckBox checkbestperf;
    private EditText editTextYear;
    private EditText editTextCircuits;
    private MyAdapterCompare myadapter;
    private ListView list;
    private String[] datasplit;
    private String ordercompare = "croissant";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        btnreset = findViewById(R.id.btnreset);
        btnaddcompare = findViewById(R.id.btnaddcompare);
        btncompare = findViewById(R.id.btncompare);
        btnback = findViewById(R.id.btnback);
        btnfilter = findViewById(R.id.btnfilter);
        checkwin = findViewById(R.id.checkwin);
        checkbestperf = findViewById(R.id.checkbestperf);
        editTextYear = findViewById(R.id.editTextYear);
        editTextCircuits = findViewById(R.id.editTextCircuits);
        list = findViewById(R.id.listCompare);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREF_COMPARE, Context.MODE_PRIVATE);
        String compare = sharedPref.getString(PREF_DRIVERS," ");//get string of user current on compare list
        datasplit = compare.split(",");//split the full name in order to have just the name for http request

        //display the name of drivers on comparator
        myadapter = new MyAdapterCompare();
        list.setAdapter(myadapter);
        list.setDivider(null);
        for(int i =0;i<datasplit.length;i++){
            DriverCompare driver = new DriverCompare(datasplit[i]);
            myadapter.dd(driver);// add it to Myadapter()
        }
        myadapter.notifyDataSetChanged();

        btnreset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(PREF_DRIVERS, " ");
                editor.apply();
                myadapter = new MyAdapterCompare();
                list.removeAllViewsInLayout();
                myadapter.notifyDataSetChanged();
            }
        });
        btnaddcompare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DriverActivity.class);
                String data = "CompareActivity";
                intent.putExtra("Activity",data);
                startActivity(intent);
            }
        });
        btncompare.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(checkbestperf.isChecked() ) {
                    //clear the list
                    myadapter = new MyAdapterCompare();
                    list.removeAllViewsInLayout();
                    myadapter.notifyDataSetChanged();

                    ordercompare = "croissant";
                    taskcompare("bestperf","");//create a task MyAdapterCompare to obtain best perf results

                }
                else if(checkwin.isChecked()){
                    //clear the list
                    myadapter = new MyAdapterCompare();
                    list.removeAllViewsInLayout();
                    myadapter.notifyDataSetChanged();

                    ordercompare = "decroissant";
                    taskcompare("wins","/1");//create a task MyAdapterCompare to obtain wins races results
                }
            }
        });
        btnback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnfilter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(ordercompare.equals("croissant")){
                    myadapter.sortdecroissant();
                    ordercompare = "decroissant";
                }
                else{
                    myadapter.sortcroissant();
                    ordercompare = "croissant";
                }
                myadapter.notifyDataSetChanged();
            }
        });
    }

    public void taskcompare(String criteria, String wins){
        String texteditTextYear = editTextYear.getText().toString();//if the user enter a year
        String texteeditTextCircuits = editTextCircuits.getText().toString();

        list.setAdapter(myadapter);
        for(int i=0;i<datasplit.length;i++){
            String url;
            AsyncJSONDatacompare task = new AsyncJSONDatacompare(myadapter,criteria);

            if((!(texteditTextYear.matches("")))&&(!(texteeditTextCircuits.matches("")))){
                 url = "https://ergast.com/api/f1/"+texteditTextYear+"/drivers/"+datasplit[i]+"/circuits/"+texteeditTextCircuits+"/results"+wins+".json";
            }
            else if(!texteditTextYear.matches("")){
                 url = "https://ergast.com/api/f1/"+texteditTextYear+"/drivers/"+datasplit[i]+"/results"+wins+".json?limit=100";//add limit=100 because of default 30 limit
            }
            else if(!texteeditTextCircuits.matches("")){
                 url = "https://ergast.com/api/f1/drivers/"+datasplit[i]+"/circuits/"+texteeditTextCircuits+"/results"+wins+".json?limit=100";//add limit=100 because of default 30 limit
            }
            else{
                 url = "https://ergast.com/api/f1/drivers/"+datasplit[i]+"/results"+wins+".json?limit=500";//add limit=500 because of default 30 limit
            }
            task.execute(url);
        }
    }
}