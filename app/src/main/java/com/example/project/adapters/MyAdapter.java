package com.example.project.adapters;



import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.Driver;
import com.example.project.R;

import java.util.Vector;

public class MyAdapter extends BaseAdapter
{
    private Vector<Driver> vector;

    public MyAdapter() {
        vector = new Vector<Driver>();
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
            text.setText(vector.get(position).getFirstname() + " " + vector.get(position).getName());


        return convertView;
    }

    public void dd(Driver driver){
        vector.add(driver);
        Log.i("JFL", "Adding to adapter: " + driver.getId());
    }

    public String getId(int position) {
        return vector.get(position).getId();
    }

}

