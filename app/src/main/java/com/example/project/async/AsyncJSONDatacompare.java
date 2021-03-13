package com.example.project.async;

import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.DriverCompare;
import com.example.project.adapters.MyAdapterCompare;

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

public class AsyncJSONDatacompare extends AsyncTask<String, Void, JSONObject> {

    private AppCompatActivity myActivity;
    private String criteria;

    private MyAdapterCompare myadapter;
    public AsyncJSONDatacompare(MyAdapterCompare myadapter,String criteria) {
        this.myadapter = myadapter;
        this.criteria = criteria;
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
            String driverId = items2.getString("driverId");//get the item name : driverid
            JSONArray items3 = items2.getJSONArray("Races");
            Integer number =0;
            for (int i = 0; i<items3.length(); i++)//in order to get all the item
            {
                if(criteria.equals("bestperf")){
                    JSONObject items4 = items3.getJSONObject(i);//we need to acess to the Arrays Results
                    JSONArray items5 = items4.getJSONArray("Results");//array result
                    JSONObject entry = items5.getJSONObject(0);//we only need the first section
                    String position = entry.getString("position");//get the position of the driver
                    if(!position.equals("R")){//case if he has result
                        number+= Integer.parseInt(position);
                    }
                }
                else{
                    number++;
                }
            }
            //to make a ratio race placement
            if(criteria.equals("bestperf")&& (items3.length()>1)){
                number = number/items3.length();
            }

            Log.i("JFL", "add to compare");
            DriverCompare drivercompare = new DriverCompare(driverId,number,criteria,items3.length());
            myadapter.dd(drivercompare);
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

