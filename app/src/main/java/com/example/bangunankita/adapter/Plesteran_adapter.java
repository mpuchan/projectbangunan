package com.example.bangunankita.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bangunankita.Model.Perhitunganbidang1;
import com.example.bangunankita.Model.Perhitunganplesteran1;
import com.example.bangunankita.Model.Perhitunganurugan1;
import com.example.bangunankita.R;

import java.util.List;

public class Plesteran_adapter extends RecyclerView.Adapter<Plesteran_adapter.ViewHolder> {
    private Context context;
    private List<Perhitunganplesteran1> plesterans;

    public Plesteran_adapter(Context context, List<Perhitunganplesteran1> plesterans) {
        this.context=context;
        this.plesterans=plesterans;
    }
    @NonNull
    @Override
    public Plesteran_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listplesteran, viewGroup, false);
        return new Plesteran_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Plesteran_adapter.ViewHolder viewHolder, final int i) {
        final Perhitunganplesteran1 PlesteranModel = plesterans.get(i);
        final String Nama = String.valueOf(plesterans.get(i).getNama());
        final String Jenispengerjaan = String.valueOf(plesterans.get(i).getJenisPengerjaan());
        final String Panjangdin = String.valueOf(plesterans.get(i).getPanjangdin());
        final String Tinggidin= String.valueOf(plesterans.get(i).getTinggidin());
        final String Tebal = String.valueOf(plesterans.get(i).getTebal());
        final String Sisi = String.valueOf(plesterans.get(i).getSisi());
        final String Volume = String.valueOf(plesterans.get(i).getVolume());
        final String Nama_semen = String.valueOf(plesterans.get(i).getNamaSemen());
        final String Nama_pasir = String.valueOf(plesterans.get(i).getNamaPasir());
        final String Jumlahkeperluanpasir = String.valueOf(plesterans.get(i).getJumlahkeperluanpasir());
        final String Jumlahdalamsak = String.valueOf(plesterans.get(i).getJumlahdalamsak());
        final String Jumlahkeperluansemen = String.valueOf(plesterans.get(i).getJumlahkeperluansemen());
        final String Metode = String.valueOf(plesterans.get(i).getMetode());
        final String Hargapasir = String.valueOf(plesterans.get(i).getHargapasir());
        final String Hargasemen = String.valueOf(plesterans.get(i).getHargasemen());
        final String Hargatotal = String.valueOf(plesterans.get(i).getHargatotal());
        viewHolder.Nama.setText(Nama);
    }

    @Override
    public int getItemCount() {
        return plesterans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nama,semen,pasir,batako,edit;
        private ImageView image;
        private Button editbidang,deletebidang,detailbidang;
        Dialog deletedialog;

        public ViewHolder(View view) {
            super(view);
            Nama = (TextView)view.findViewById(R.id.Nama1);
            semen = (TextView)view.findViewById(R.id.semen);
            pasir = (TextView)view.findViewById(R.id.pasir);
            batako = (TextView)view.findViewById(R.id.Batako);
            editbidang = view.findViewById(R.id.editbidang);
            deletebidang = view.findViewById(R.id.Deletebidang);
            detailbidang = view.findViewById(R.id.detailbidang);




        }
    }
}
