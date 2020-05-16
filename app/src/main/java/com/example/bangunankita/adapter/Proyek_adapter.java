package com.example.bangunankita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bangunankita.Model.Proyek_model;
import com.example.bangunankita.R;

import java.util.ArrayList;


public class Proyek_adapter extends RecyclerView.Adapter<Proyek_adapter.ViewHolder> {
    private ArrayList<Proyek_model> proyeks;
    private Context context;

    public Proyek_adapter(ArrayList<Proyek_model> proyeks, Context context) {
        this.context=context;
        this.proyeks=proyeks;
    }

    @Override
    public Proyek_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitemproyek, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Proyek_adapter.ViewHolder viewHolder, final int i) {

        final String nama_proyek=proyeks.get(i).getNamaProyek();
        final String lokasi =proyeks.get(i).getLokasi();
        final String tanggal =proyeks.get(i).getTanggal();

        viewHolder.nama_proyek.setText(nama_proyek);
        viewHolder.lokasi_proyek.setText(lokasi);

    }

    @Override
    public int getItemCount() {
        return proyeks.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nama_proyek,lokasi_proyek;


        public ViewHolder(View view) {
            super(view);
            nama_proyek = (TextView)view.findViewById(R.id.nameproyek);
            lokasi_proyek = (TextView)view.findViewById(R.id.lokasiproyek);
            Animation anim = AnimationUtils.loadAnimation(context, R.anim.itemproyekanim);
            view.startAnimation(anim);

        }
    }

}