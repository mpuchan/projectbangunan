package com.example.bangunankita.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bangunankita.Editbidang;
import com.example.bangunankita.Model.Perhitunganbidang1;
import com.example.bangunankita.R;

import java.util.List;


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
        final String Name = String.valueOf(bidangs.get(i).getNama());
        final String Panjangbid = String.valueOf(bidangs.get(i).getPanjangbid());
        final String Tinggibid = String.valueOf(bidangs.get(i).getTinggibid());
        final String Panjangjen = String.valueOf(bidangs.get(i).getPanjangjen());
        final String Tinggijen = String.valueOf(bidangs.get(i).getTinggijen());
        final String Panjangpin = String.valueOf(bidangs.get(i).getPanjangpin());
        final String Tinggipin = String.valueOf(bidangs.get(i).getTinggipin());
        final String Jumlahdalamsak = String.valueOf(bidangs.get(i).getJumlahdalamsak());
        final String Metode = String.valueOf(bidangs.get(i).getMetode());
        final String Hargabatako = String.valueOf(bidangs.get(i).getHargabatako());
        final String Hargapasir = String.valueOf(bidangs.get(i).getHargapasir());
        final String Hargasemen = String.valueOf(bidangs.get(i).getHargasemen());
        final String Totalbiaya = String.valueOf(bidangs.get(i).getHargatotal());



        final String Semen = String.valueOf(bidangs.get(i).getJumlahkeperluansemen());
        final String Pasir = String.valueOf(bidangs.get(i).getJumlahkeperluanpasir());
        final String Batako = String.valueOf(bidangs.get(i).getJumlahkeperluanbatako());
        final String Luas = String.valueOf(bidangs.get(i).getLuasBidang());

        viewHolder.Nama.setText(Name);
        viewHolder.semen.setText(Semen);
        viewHolder.pasir.setText(Pasir);
        viewHolder.batako.setText(Batako);

        viewHolder.editbidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editbidang = new Intent(context, Editbidang.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle setData = new Bundle();
                setData.putString("nama", Name);
                setData.putString("jenis_pengerjaan", "bangunan");
                setData.putString("panjangbid", Panjangbid);
                setData.putString("tinggibid", Tinggibid);
                setData.putString("panjangpin", Panjangpin);
                setData.putString("tinggipin", Tinggipin);
                setData.putString("panjangjen",Panjangjen);
                setData.putString("tinggijen", Tinggijen);
                setData.putString("luas_bidang",Luas);
                setData.putString("jumlahkeperluanbatako", Batako);
                setData.putString("jumlahkeperluanpasir", Pasir);
                setData.putString("Jumlahkeperluansemen", Semen);
                setData.putString("jumlahdalamsak", Jumlahdalamsak);
                setData.putString("metode", Metode);
                setData.putString("hargabatako", Hargabatako);
                setData.putString("hargapasir", Hargapasir);
                setData.putString("hargasemen", Hargasemen);
                editbidang.putExtras(setData);
                context.startActivity(editbidang);

            }
        });
        viewHolder.detailbidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog detaildialog = new Dialog(context);
                detaildialog.setContentView(R.layout.detail_bidang);
                TextView nama = detaildialog.findViewById(R.id.nama);
                TextView luasbidang = detaildialog.findViewById(R.id.luasbidang);
                TextView Batako1 = detaildialog.findViewById(R.id.Batako);
                TextView Semen1 = detaildialog.findViewById(R.id.semen1);
                TextView Pasir1 = detaildialog.findViewById(R.id.pasir);
                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);
                nama.setText(Name);
                luasbidang.setText("Panjang "+Panjangbid+"m "+",Tinggi "+Tinggibid+"m "+"" +
                        ",Panjang panel pintu "+Panjangpin+"m "+",Tinggi panel pintu "+Tinggipin+
                        ",Panjang panel jendela "+Panjangjen+"m "+",Tinggi panel jendela "+Tinggijen+"m "+
                        ",Luas Bidang "+Luas+"m ");
                Batako1.setText("Jumlah "+Batako+"buah"+",Harga Rp."+Hargabatako);
                Semen1.setText("Berat "+Semen+"kg"+",Harga Rp."+Hargasemen);
                Pasir1.setText("Jumlah "+Pasir+"m3"+",Harga Rp."+Hargapasir);
                totalbiaya.setText(",Harga Rp."+Totalbiaya);

                detaildialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                detaildialog.show();
            }
        });
        viewHolder.deletebidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog deletedialog = new Dialog(context);
                deletedialog.setContentView(R.layout.delete_dialog);
                TextView deletemessage = deletedialog.findViewById(R.id.textdelete);
              TextView silang = deletedialog.findViewById(R.id.silang);
                Button btndelete = deletedialog.findViewById(R.id.buttonhapus);
                Button btncancel = deletedialog.findViewById(R.id.buttoncancel);
                deletemessage.setText("Yakin ingin menghapus data bidang " +Name);

                btndelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                btncancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                deletedialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                deletedialog.show();
            }
        });

        }

    @Override
    public int getItemCount() {
        return bidangs.size();
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