package com.example.bangunankita.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bangunankita.Model.Perhitunganbidang1;
import com.example.bangunankita.R;

import java.util.ArrayList;
import java.util.List;


public class Reportbidang extends RecyclerView.Adapter<Reportbidang.ViewHolder> {

    private Context context;
    private List<Perhitunganbidang1> bidangs1;

    public Reportbidang(Context context, List<Perhitunganbidang1> bidangs1, ArrayList<String> list) {
        this.context=context;
        this.bidangs1=bidangs1;
    }
    @Override
    public Reportbidang.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.table_list_item, viewGroup, false);
        return new Reportbidang.ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(Reportbidang.ViewHolder viewHolder, final int i) {


        if (i == 0) {

            viewHolder.txtRank.setBackgroundResource(R.drawable.row_table);
            viewHolder.txtMovieName.setBackgroundResource(R.drawable.row_table);
            viewHolder.txtYear.setBackgroundResource(R.drawable.row_table);
            viewHolder.txtCost.setBackgroundResource(R.drawable.row_table);

            viewHolder.txtRank.setText("Rank");
            viewHolder.txtMovieName.setText("Name");
            viewHolder.txtYear.setText("Year");
            viewHolder.txtCost.setText("Budget");
        } else {
            Perhitunganbidang1 modal = bidangs1.get(i - 1);
            viewHolder.txtRank.setBackgroundResource(R.drawable.rounded);
            viewHolder.txtMovieName.setBackgroundResource(R.drawable.rounded);
            viewHolder.txtYear.setBackgroundResource(R.drawable.rounded);
            viewHolder.txtCost.setBackgroundResource(R.drawable.rounded);

            viewHolder.txtRank.setText(modal.getNama());
            viewHolder.txtMovieName.setText(modal.getNamaBatako());
            viewHolder.txtYear.setText(modal.getNamaPasir());
            viewHolder.txtCost.setText(modal.getMetode());
        }


//        final Perhitunganbidang1 BidangModel = bidangs1.get(i);
//        final String id = String.valueOf(bidangs1.get(i).getId());
//        final String Proyekid = String.valueOf(bidangs1.get(i).getProyekId());
//        final String Name = String.valueOf(bidangs1.get(i).getNama());
//        final String Panjangbid = String.valueOf(bidangs1.get(i).getPanjangbid());
//        final String Tinggibid = String.valueOf(bidangs1.get(i).getTinggibid());
//        final String Panjangjen = String.valueOf(bidangs1.get(i).getPanjangjen());
//        final String Tinggijen = String.valueOf(bidangs1.get(i).getTinggijen());
//        final String Panjangpin = String.valueOf(bidangs1.get(i).getPanjangpin());
//        final String Tinggipin = String.valueOf(bidangs1.get(i).getTinggipin());
//        final String Jumlahdalamsak = String.valueOf(bidangs1.get(i).getJumlahdalamsak());
//        final String Metode = String.valueOf(bidangs1.get(i).getMetode());
//        final String Hargabatako = String.valueOf(bidangs1.get(i).getHargabatako());
//        final String Hargapasir = String.valueOf(bidangs1.get(i).getHargapasir());
//        final String Hargasemen = String.valueOf(bidangs1.get(i).getHargasemen());
//        final String Namabatako = String.valueOf(bidangs1.get(i).getNamaBatako());
//        final String Namapasir = String.valueOf(bidangs1.get(i).getNamaPasir());
//        final String Namasemen = String.valueOf(bidangs1.get(i).getNamaSemen());
//        final String Hargabatakototal = String.valueOf(bidangs1.get(i).getHargabatakototal());
//        final String Hargapasirtotal = String.valueOf(bidangs1.get(i).getHargapasirtotal());
//        final String Hargasementotal = String.valueOf(bidangs1.get(i).getHargasementotal());
//        final String Totalbiaya = String.valueOf(bidangs1.get(i).getHargatotal());
//        float sum;
//        Double price = bidangs1.get(i).getHargatotal();
//        for (int j = 0; j < price; j++) {
//
//        }
//
//
//        final String Semen = String.valueOf(bidangs1.get(i).getJumlahkeperluansemen());
//        final String Pasir = String.valueOf(bidangs1.get(i).getJumlahkeperluanpasir());
//        final String Batako = String.valueOf(bidangs1.get(i).getJumlahkeperluanbatako());
//        final String Luas = String.valueOf(bidangs1.get(i).getLuasBidang());



    }


    @Override
    public int getItemCount() {
        return bidangs1.size()+1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtRank;
        TextView txtMovieName;
        TextView txtYear;
        TextView txtCost;
        public ViewHolder(View view) {
            super(view);
            txtRank = view.findViewById(R.id.txtRank);
            txtMovieName = view.findViewById(R.id.txtMovieName);
            txtYear = view.findViewById(R.id.txtYear);
            txtCost = view.findViewById(R.id.txtCost);
        }
    }
}
