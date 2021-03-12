package com.example.project.async;

import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.Circuits;
import com.example.project.adapters.MyAdapter;
import com.example.project.adapters.MyAdapterCircuits;

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

public class AsyncJSONCircuits  extends AsyncTask<String, Void, JSONObject> {

    private MyAdapterCircuits myadapter;

    public AsyncJSONCircuits(MyAdapterCircuits adapter) {
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

            for (int i = 0; i<items3.length(); i++)//in order to get all the item
            {

                JSONObject entry = items3.getJSONObject(i);//get the i item
                JSONObject circuitarray = entry.getJSONObject("Circuit");
                String circuitId = circuitarray.getString("circuitId");//get the item name : driverid
                String circuitName = circuitarray.getString("circuitName");//get the item name : media
                JSONObject Locationarray = circuitarray.getJSONObject("Location");
                String location = Locationarray.getString("locality");
                String country = Locationarray.getString("country");
                Log.i("CIO", "URL media: " + circuitId);

                Circuits circuit = new Circuits(circuitId,circuitName,location,country);

                myadapter.dd(circuit);// add it to Myadapter()

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

