package com.example.bangunankita.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.bangunankita.Dashboard;
import com.example.bangunankita.Model.Perhitunganbidang1;
import com.example.bangunankita.Model.Proyek_model;
import com.example.bangunankita.Model.ResponseModel;
import com.example.bangunankita.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.stream.IntStream.builder;


public class Bidang_adapter extends RecyclerView.Adapter<Bidang_adapter.ViewHolder> {


    private Context context;
    private List<Perhitunganbidang1> bidangs;

    public Bidang_adapter(Context context, List<Perhitunganbidang1> bidangs) {
        this.context=context;
        this.bidangs=bidangs;
    }

    @Override
    public Bidang_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listbidang, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Bidang_adapter.ViewHolder viewHolder, final int i) {
        final Perhitunganbidang1 BidangModel = bidangs.get(i);

        final String HargaTotal = String.valueOf(bidangs.get(i).getHargatotal());
        final String Luas = String.valueOf(bidangs.get(i).getLuasBidang());

        viewHolder.luas.setText(Luas);
        viewHolder.Hargatot.setText(HargaTotal);


    }
//    public interface ClickedItem{
//        void ClickedUser(Proyek_model proyekModel);
//    }
    @Override
    public int getItemCount() {
        return bidangs.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Hargatot,luas,edit;
        private ImageView image;


        public ViewHolder(View view) {
            super(view);
            Hargatot = (TextView)view.findViewById(R.id.Harga);
            luas = (TextView)view.findViewById(R.id.Luas);


        }
    }



}