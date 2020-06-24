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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listbidang, viewGroup, false);
        return new Plesteran_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Plesteran_adapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nama,semen,pasir,batako,edit;
        private ImageView image;
        private Button editbidang,deletebidang,detailbidang;
        Dialog deletedialog;

        public ViewHolder(View view) {
            super(view);
            Nama = (TextView)view.findViewById(R.id.Nama);
            semen = (TextView)view.findViewById(R.id.semen);
            pasir = (TextView)view.findViewById(R.id.pasir);
            batako = (TextView)view.findViewById(R.id.Batako);
            editbidang = view.findViewById(R.id.editbidang);
            deletebidang = view.findViewById(R.id.Deletebidang);
            detailbidang = view.findViewById(R.id.detailbidang);




        }
    }
}
