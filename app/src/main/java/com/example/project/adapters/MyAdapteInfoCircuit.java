package com.example.project.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.utils.InfoCircuit;

import java.util.Vector;

public class MyAdapteInfoCircuit extends BaseAdapter {
    private Vector<InfoCircuit> vector;
    public MyAdapteInfoCircuit() {
        vector = new Vector<InfoCircuit>();
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
            convertView = inflater.inflate(R.layout.textviewlayout_driver, parent, false);
        }
        TextView text = ((TextView)convertView.findViewById(R.id.textView));
        text.setText(vector.get(position).getPosition()+" | "+vector.get(position).getNumber()+" | "+vector.get(position).getDriver());
        return convertView;
    }

    public  void dd(InfoCircuit info){
        vector.add(info);
        Log.i("JFL", "Adding to adapter: " + info.getDriver());
    }

}

