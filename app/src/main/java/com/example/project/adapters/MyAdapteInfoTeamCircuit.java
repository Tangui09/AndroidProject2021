package com.example.project.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.InfoCircuitTeam;

import java.util.Vector;

public class MyAdapteInfoTeamCircuit extends BaseAdapter
{
    private Vector<InfoCircuitTeam> vector;

    public MyAdapteInfoTeamCircuit() {
        vector = new Vector<InfoCircuitTeam>();
    }

    @Override
    public int getCount() {
        return vector.size();
    }

    @Override
    public Object getItem(int position) {
        return vector.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // ConvertView which allows to display all the names on the screen
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.textviewlayout_teamresultrace, parent, false);
        }
        TextView pos = ((TextView)convertView.findViewById(R.id.textViewPosition));
        TextView teamname = ((TextView)convertView.findViewById(R.id.textViewDriverFirstname));
        TextView points = ((TextView)convertView.findViewById(R.id.textViewPoints));

        pos.setText(vector.get(position).getPosition());
        teamname.setText(vector.get(position).getTeamName());
        points.setText(String.valueOf(vector.get(position).getPoints()));
        return convertView;
    }

    public  void dd(InfoCircuitTeam info){
        vector.add(info);
        Log.i("JFL", "Adding to adapter: " + info.getTeamName());
    }

}

