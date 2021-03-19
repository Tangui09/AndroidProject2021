package com.example.project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.async.AsyncJSONInfoDriver;
import com.example.project.R;

import static com.example.project.utils.Constant.*;

public class InfoDriverActivity extends AppCompatActivity {

    public AppCompatActivity myActivity;
    public static TextView textName;
    public static TextView textNumber;
    public static TextView textNationality;
    public static TextView textDOB;
    public static TextView textInfo;
    private Button btnadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_driver);

        //set info in order to set them in AsyncJSONInfoDriver
        textName = findViewById(R.id.textName2);
        textNumber = findViewById(R.id.textNumber2);
        textNationality = findViewById(R.id.textNationality2);
        textDOB = findViewById(R.id.textDOB2);
        textInfo = findViewById(R.id.textInformation2);
        btnadd = findViewById(R.id.btnAdd);

        //get the info pass by the Activity Intent
        Bundle extras = getIntent().getExtras();
        String data = new String(extras.getString("name"));
        String Activity = new String(extras.getString("Activity"));
        System.out.println(data);
        String[] datasplit = data.split(" ");//split the full name in order to have just the name for http request

        AsyncJSONInfoDriver task = new AsyncJSONInfoDriver();
        String url = "https://ergast.com/api/f1/drivers/"+datasplit[0]+".json";
        task.execute(url);

        btnadd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREF_COMPARE, Context.MODE_PRIVATE);
                String compare = sharedPref.getString(PREF_DRIVERS,"");//get string of user current on compare list
                compare += datasplit[0]+",";// add to the string the current driver and after split string
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(PREF_DRIVERS, compare);
                editor.apply();
                System.out.println(compare);
                if(Activity.equals("CompareActivity")){
                    Intent intent = new Intent(getApplicationContext(), CompareActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), DriverActivity.class);
                    intent.putExtra("Activity"," ");
                    startActivity(intent);
                }
                Toast.makeText(getApplicationContext(),"Driver add",Toast.LENGTH_SHORT).show();
            }
        });
    }
}