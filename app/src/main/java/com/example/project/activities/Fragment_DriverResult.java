package com.example.project.activities;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.project.R;
import com.example.project.adapters.MyAdapteInfoDriverCircuit;
import com.example.project.async.AsyncJSONResultsCircuit;

public class Fragment_DriverResult extends Fragment
{
    private ListView list;
    private MyAdapteInfoDriverCircuit adapter;

    public Fragment_DriverResult() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_driver_result_, container, false);
        list = view.findViewById(R.id.list_DriverInfo);

        adapter = new MyAdapteInfoDriverCircuit();
        list.setAdapter(adapter);
        list.setDivider(null);

        return view;
    }

    public void startAsync(String url)
    {
        AsyncJSONResultsCircuit task = new AsyncJSONResultsCircuit(adapter);
        task.execute(url);
    }
}