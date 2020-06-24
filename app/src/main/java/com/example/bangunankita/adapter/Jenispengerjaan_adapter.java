package com.example.bangunankita.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bangunankita.Model.Campuran;
import com.example.bangunankita.Model.Jenispengerjaan;

public class Jenispengerjaan_adapter extends ArrayAdapter<Jenispengerjaan> {
    private Context context;
    private Jenispengerjaan[] jenispengerjaans;

    public Jenispengerjaan_adapter(Context context, int textViewResourceId,
                            Jenispengerjaan[] jenispengerjaans) {
        super(context, textViewResourceId, jenispengerjaans);
        this.context = context;
        this.jenispengerjaans = jenispengerjaans;
    }

    public int getCount(){
        return jenispengerjaans.length;
    }

    public Jenispengerjaan getItem(int position){
        return jenispengerjaans[position];
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setText(jenispengerjaans[position].getJenispengerjaan());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setText(jenispengerjaans[position].getJenispengerjaan());
        return label;
    }
}
