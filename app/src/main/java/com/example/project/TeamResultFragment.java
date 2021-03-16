package com.example.project;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class TeamResultFragment extends Fragment
{
    View view;
    Button teamButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.textviewlayout_teamresultrace, container, false);
        // get the reference of Button
        teamButton = (Button) view.findViewById(R.id.btnSwitchToTeams);
        // perform setOnClickListener on first Button
        teamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // display a message by using a Toast
                Toast.makeText(getActivity(), "First Fragment", Toast.LENGTH_LONG).show();  //TODO
            }
        });
        return view;
    }
}
