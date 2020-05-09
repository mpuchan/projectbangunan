package com.example.bangunankita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bangunankita.Model.Proyek_model;
import com.example.bangunankita.R;

import java.util.ArrayList;


public class Proyek_adapter extends RecyclerView.Adapter<Proyek_adapter.ViewHolder> {
    private ArrayList<Proyek_model> articles=new ArrayList<>();
    private Context context;

    public Proyek_adapter(ArrayList<Proyek_model> articles, Context context) {
        this.context=context;
        this.articles=articles;
    }

    @Override
    public Proyek_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitemproyek, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Proyek_adapter.ViewHolder viewHolder, final int i) {

        final String nama_proyek=articles.get(i).getNamaProyek();
        final String lokasi_proyek=articles.get(i).getLokasi();

        viewHolder.nama_proyek.setText(nama_proyek);
        viewHolder.lokasi_proyek.setText(lokasi_proyek);

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nama_proyek,lokasi_proyek;


        public ViewHolder(View view) {
            super(view);
            nama_proyek = (TextView)view.findViewById(R.id.nameproyek);
            lokasi_proyek = (TextView)view.findViewById(R.id.lokasiproyek);
        }
    }

}