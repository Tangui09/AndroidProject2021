package com.example.project.async;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.activities.InfoDriverActivity;

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

public class AsyncJSONInfoDriver extends AsyncTask<String, Void, JSONObject> {

    private AppCompatActivity myActivity;
    private TextView textName;
    private TextView textNumber;
    private TextView textNationality;
    private TextView textDOB;
    private TextView textInfo;

    public AsyncJSONInfoDriver() {
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
            JSONObject items2 = items.getJSONObject("DriverTable");
            JSONArray items3 = items2.getJSONArray("Drivers");
            for (int i = 0; i<items3.length(); i++)//in order to get all the item
            {
                JSONObject entry = items3.getJSONObject(i);//get the i item
                String driverId = entry.getString("driverId");//get the item name : driverid
                driverId += " "+ entry.getString("givenName");

                String permanentNumber = "...";
                if (entry.has("permanentNumber")){//verif if driver has a permanent number
                permanentNumber = entry.getString("permanentNumber");//get the item name : permanent number
                }

                String nationality = entry.getString("nationality");//get the item name : nationality
                String dateOfBirth = entry.getString("dateOfBirth");//get the item name : DOB
                String url = entry.getString("url");//get the item name : media
                Log.i("CIO", "URL media: " + driverId);

               setRes(driverId,permanentNumber,nationality,dateOfBirth,url);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setRes(String... text) {
        InfoDriverActivity.textName.setText(text[0]);
        InfoDriverActivity.textNumber.setText(text[1]);
        InfoDriverActivity.textNationality.setText(text[2]);
        InfoDriverActivity.textDOB.setText(text[3]);
        InfoDriverActivity.textInfo.setText(text[4]);
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
