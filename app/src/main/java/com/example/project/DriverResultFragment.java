package com.example.project;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class DriverResultFragment extends Fragment
{
    View view;
    Button driverButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.textviewlayout_driverresultrace, container, false);
        // get the reference of Button
        driverButton = (Button) view.findViewById(R.id.btnSwitchToDrivers);
        // perform setOnClickListener on first Button
        driverButton.setOnClickListener(new View.OnClickListener() {
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
