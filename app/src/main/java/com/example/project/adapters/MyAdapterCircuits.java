package com.example.project.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.project.Circuits;
import com.example.project.R;

import java.util.Vector;

public class MyAdapterCircuits extends BaseAdapter {
    private Vector<Circuits> vector;
    public MyAdapterCircuits() {
        vector = new Vector<Circuits>();
    }

    @Override
    public int getCount() {
        return vector.size();
    }

    @Override
    public Object getItem(int position) {
        return vector.get(position);
    }

    public Object getItemName(int position) {
        return vector.get(position).getName();
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
            convertView = inflater.inflate(R.layout.textviewlayout_circuits, parent, false);
        }
        TextView text = ((TextView)convertView.findViewById(R.id.textView2));
        text.setText(vector.get(position).getName()+" | "+vector.get(position).getLocality()+" | "+vector.get(position).getCountry());
        return convertView;
    }

    public  void dd(Circuits info){
        vector.add(info);
        Log.i("JFL", "Adding to adapter: " + info.getName());
    }

}

