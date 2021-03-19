package com.example.project.activities;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.project.R;
import com.example.project.adapters.MyAdapteInfoDriverCircuit;
import com.example.project.adapters.MyAdapteInfoTeamCircuit;
import com.example.project.async.AsyncJSONResultsCircuit;

public class Fragment_TeamResult extends Fragment {

    private MyAdapteInfoTeamCircuit adapter = new MyAdapteInfoTeamCircuit();

    public Fragment_TeamResult() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_result_, container, false);
        ListView list = view.findViewById(R.id.list_TeamInfo);

        list.setAdapter(adapter);
        list.setDivider(null);

        return view;
    }

    public void startAsync(String url)
    {
        AsyncJSONResultsCircuit task = new AsyncJSONResultsCircuit(adapter = new MyAdapteInfoTeamCircuit());
        task.execute(url);
    }
}