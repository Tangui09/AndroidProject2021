package com.example.project.async;

import android.icu.text.IDNA;
import android.os.AsyncTask;
import android.util.Log;

import com.example.project.Circuits;
import com.example.project.activities.InfoCircuitActivity;
import com.example.project.adapters.MyAdapteInfoCircuit;
import com.example.project.adapters.MyAdapterCircuits;
import com.example.project.utils.InfoCircuit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;

public class AsyncJSONResultsCircuit  extends AsyncTask<String, Void, JSONObject> {

    private MyAdapteInfoCircuit myadapter;

    public AsyncJSONResultsCircuit(MyAdapteInfoCircuit adapter) {
        this.myadapter = adapter;
    }

    protected JSONObject doInBackground(String... strings) {
        URL url =null;
        JSONObject myJSONObject = null;
        try {
            url = new URL(strings[0]);//get the first url
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();//Open the connection using the url
            System.out.println(url);
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());//Get the content of the urlConnection
                String s = readStream(in);//Read this content as a string
                Log.i("JFL", s);
                myJSONObject = new JSONObject(s);//Create a JSON Object with the string we just translate
                in.close(); //Close the input stream so we free resources
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myJSONObject;
    }




    protected void onPostExecute(JSONObject s) {
        try {
            JSONObject items = s.getJSONObject("MRData");
            JSONObject items2 = items.getJSONObject("RaceTable");
            JSONArray items3 = items2.getJSONArray("Races");
            JSONObject entry = items3.getJSONObject(0);

            JSONObject circuitarray = entry.getJSONObject("Circuit");
            String circuit_name = circuitarray.getString("circuitName");//get the item name : media
//            String grandprix_date = entry.getString("date");
            LocalDate grandprix_date = LocalDate.parse(entry.getString("date"));
            JSONObject Locationarray = circuitarray.getJSONObject("Location");
            String circuit_place = Locationarray.getString("locality");


            JSONArray Resultarray = entry.getJSONArray("Results");

            InfoCircuitActivity.textnamecircuit.setText(circuit_name);
            InfoCircuitActivity.textplacecircuit.setText(circuit_place);
            InfoCircuitActivity.dategrandprix.setText(String.valueOf(grandprix_date.getDayOfMonth() + "/" + grandprix_date.getMonthValue() + "/" + grandprix_date.getYear()));
//            InfoCircuitActivity.dategrandprix.setText(grandprix_date);

            for (int i = 0; i<Resultarray.length(); i++)//in order to get all the item
            {
                JSONObject entry2 = Resultarray.getJSONObject(i);
                String number = entry2.getString("number");//get the item name : number
                String position = entry2.getString("position");//get the item name : position
                String points = "+" + entry2.getString("points");//get the item name : position

                JSONObject Driverarray = entry2.getJSONObject("Driver");
                String FirstName = Driverarray.getString("givenName");//get the item name : givenName
                String FamilyName = Driverarray.getString("familyName");
                String driver = FirstName + " " + FamilyName;
                Log.i("CIO", "URL media: " + driver);

                InfoCircuit resultcircuit = new InfoCircuit(number,position,driver, FirstName, FamilyName, points);

                myadapter.dd(resultcircuit);// add it to Myadapter()

            }
            myadapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i;
            i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();// use replace to remove the begining
        } catch (IOException e) {
            return "";
        }
    }


}


