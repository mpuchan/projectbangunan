package com.example.bangunankita.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.bangunankita.Dashboard;
import com.example.bangunankita.Edit_Proyek;
import com.example.bangunankita.MenuProyek;
import com.example.bangunankita.Model.Proyek_model;
import com.example.bangunankita.Model.ResponseModel;
import com.example.bangunankita.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.stream.IntStream.builder;


public class Proyek_adapter extends RecyclerView.Adapter<Proyek_adapter.ViewHolder> {

    private Context context;
    private List<Proyek_model> proyeks;
    public String[] mColors = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };

    public Proyek_adapter(Context context,List<Proyek_model> proyeks) {
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
        final Proyek_model proyekModel = proyeks.get(i);
        final String id_proyek= String.valueOf(proyeks.get(i).getId());
        final String nama_proyek=proyeks.get(i).getNamaProyek();
        final String lokasi =proyeks.get(i).getLokasi();
        final String tanggal =proyeks.get(i).getTanggal();

        String firstCharNamaProyek= nama_proyek.substring(0,1);
        TextDrawable drawable = TextDrawable.builder().beginConfig()
                .withBorder(16) /* thickness in px */
                .endConfig()
                .buildRound(firstCharNamaProyek, getColor());
        viewHolder.image.setImageDrawable(drawable);
        viewHolder.nama_proyek.setText(nama_proyek);
        viewHolder.lokasi_proyek.setText(lokasi);
//        viewHolder.tanggal.setText(tanggal);

        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(context, Edit_Proyek.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle setData = new Bundle();
                setData.putString("idproyek",id_proyek);
                setData.putString("namaproyek",nama_proyek);
                setData.putString("lokasi",lokasi);
                edit.putExtras(setData);
                context.startActivity(edit);

            }
        });
        viewHolder.list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(context, MenuProyek.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle setData = new Bundle();
                setData.putString("idproyek",id_proyek);
                setData.putString("namaproyek",nama_proyek);
                setData.putString("lokasi",lokasi);
                detail.putExtras(setData);
                context.startActivity(detail);
            }
        });

    }
    @Override
    public int getItemCount() {
        return proyeks.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nama_proyek,lokasi_proyek,edit,tanggal;
        private ImageView image;
        private CardView list;


        public ViewHolder(View view) {
            super(view);
            nama_proyek = (TextView)view.findViewById(R.id.nameproyek);
            lokasi_proyek = (TextView)view.findViewById(R.id.lokasiproyek);
            tanggal = view.findViewById(R.id.tanggal);
            image = view.findViewById(R.id.picture1);
            edit = view.findViewById(R.id.editproyek);
            list = view.findViewById(R.id.user_layout);
            Animation anim = AnimationUtils.loadAnimation(context, R.anim.itemproyekanim);
            view.startAnimation(anim);

        }
    }
    public int getColor() {
        String color;

        // Randomly select a fact
        Random randomGenerator = new Random(); // Construct a new Random number generator
        int randomNumber = randomGenerator.nextInt(mColors.length);

        color = mColors[randomNumber];
        int colorAsInt = Color.parseColor(color);

        return colorAsInt;
    }



}