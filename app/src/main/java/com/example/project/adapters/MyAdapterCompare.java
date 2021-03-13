package com.example.project.adapters;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.DriverCompare;
import com.example.project.R;

import java.util.Collections;
import java.util.Vector;

public class MyAdapterCompare extends BaseAdapter {
    private Vector<DriverCompare> vector;
    private int counter = 1 ;
    public MyAdapterCompare() {
        vector = new Vector<DriverCompare>();
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
            convertView = inflater.inflate(R.layout.textviewlayout_universal, parent, false);
        }
        TextView text = ((TextView)convertView.findViewById(R.id.textView_universal));
        if(vector.get(position).getBestperformance().equals("bestperf")){
            text.setText(vector.get(position).getDrivername()+": "+vector.get(position).getNumberwin()+" ratio on "+vector.get(position).getNumberrace()+" races" );
        }
        else if(vector.get(position).getBestperformance().equals("compare list")){ // in order to display only the name
            text.setText(vector.get(position).getDrivername());
        }
        else{
            text.setText(vector.get(position).getDrivername()+": "+vector.get(position).getNumberwin()+" wins");
        }
        counter++;
        return convertView;
    }

    public  void dd(DriverCompare driver){

        vector.add(driver);
        Log.i("JFL", "Adding to adapter: " + driver.getNumberwin());
        Log.i("JFL", "Adding to adapter: " + vector.size()+" "+vector.get(0).getDrivername());
        if(driver.getBestperformance().equals("bestperf")){
            Collections.sort(vector);
        }
        else{
            Collections.sort(vector, Collections.reverseOrder());
        }

    }

}




