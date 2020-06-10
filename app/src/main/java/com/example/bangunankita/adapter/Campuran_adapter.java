package com.example.bangunankita.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bangunankita.Model.Campuran;

public class Campuran_adapter  extends ArrayAdapter<Campuran> {
    private Context context;
    private Campuran[] campurans;

    public Campuran_adapter(Context context, int textViewResourceId,
                            Campuran[] campurans) {
        super(context, textViewResourceId, campurans);
        this.context = context;
        this.campurans = campurans;
    }

    public int getCount(){
        return campurans.length;
    }

    public Campuran getItem(int position){
        return campurans[position];
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setText(campurans[position].getCampuran());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setText(campurans[position].getCampuran());
        return label;
    }
}
