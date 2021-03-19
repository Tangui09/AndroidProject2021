package com.example.project.async;

import android.os.AsyncTask;
import android.util.Log;

import com.example.project.InfoCircuitTeam;
import com.example.project.activities.InfoCircuitActivity;
import com.example.project.adapters.MyAdapteInfoDriverCircuit;
import com.example.project.adapters.MyAdapteInfoTeamCircuit;
import com.example.project.InfoCircuitDriver;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AsyncJSONResultsCircuit  extends AsyncTask<String, Void, JSONObject> {

    private MyAdapteInfoDriverCircuit myadapterdriver;
    private MyAdapteInfoTeamCircuit myadapterteam;
    private int condFragment;

    public AsyncJSONResultsCircuit(MyAdapteInfoDriverCircuit adapter) {
        this.myadapterdriver = adapter;
        condFragment = 0;
    }

    public AsyncJSONResultsCircuit(MyAdapteInfoTeamCircuit adapter) {
        this.myadapterteam = adapter;
        condFragment = 1;
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
            LocalDate grandprix_date = LocalDate.parse(entry.getString("date"));
            JSONObject Locationarray = circuitarray.getJSONObject("Location");
            String circuit_place = Locationarray.getString("locality");


            JSONArray Resultarray = entry.getJSONArray("Results");

            InfoCircuitActivity.textnamecircuit.setText(circuit_name);
            InfoCircuitActivity.textplacecircuit.setText(circuit_place);
            InfoCircuitActivity.dategrandprix.setText(String.valueOf(grandprix_date.getDayOfMonth() + "/" + grandprix_date.getMonthValue() + "/" + grandprix_date.getYear()));

            if(condFragment == 0)
            {
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

                    InfoCircuitDriver resultcircuit = new InfoCircuitDriver(number,position,driver, FirstName, FamilyName, Integer.parseInt(points));

                    myadapterdriver.dd(resultcircuit);// add it to Myadapter()

                }
                myadapterdriver.notifyDataSetChanged();
            }
            else
            {
                HashMap<String, Integer> teamVector = new HashMap<String, Integer>(); //key = name of constructor | value = number of points

                for (int i = 0; i<Resultarray.length(); i++)//in order to get all the item
                {
                    JSONObject entry2 = Resultarray.getJSONObject(i);
                    int points = Integer.parseInt(entry2.getString("points"));//get the item name : position

                    JSONObject Constructorarray = entry2.getJSONObject("Constructor");
                    String Constructor_name = Constructorarray.getString("name");//get the item name : name
                    Log.i("CIO", "URL media: " + Constructor_name);


                    if(teamVector.containsKey(Constructor_name))
                    {
                        teamVector.replace(Constructor_name, teamVector.get(Constructor_name) + points);
                    }
                    else
                    {
                        teamVector.put(Constructor_name, points);
                    }
                }

                HashMap<String, Integer> orderedTeamVector = sortHashMapByValues(teamVector);

                int pos = 1;
                for ( Object key : orderedTeamVector.keySet() )
                {
                    InfoCircuitTeam info = new InfoCircuitTeam(String.valueOf(pos), String.valueOf(key), orderedTeamVector.get(key));
                    myadapterteam.dd(info);// add it to Myadapter()
                    pos++;
                }

                myadapterteam.notifyDataSetChanged();
            }


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


    public HashMap<String, Integer> sortHashMapByValues( HashMap<String, Integer> passedMap )
    {
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Integer> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

        for (int val : mapValues)
        {
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Integer comp1 = passedMap.get(key);
                Integer comp2 = val;

                if (comp1 < comp2) {
                    keyIt.remove();
                    sortedMap.put(key, comp2);
                    break;
                }
            }
        }
        return sortedMap;
    }
}


