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

import com.example.bangunankita.Editacian;
import com.example.bangunankita.Model.Perhitunganacian1;
import com.example.bangunankita.R;

import java.util.List;

public class Acian_adapter  extends RecyclerView.Adapter<Acian_adapter.ViewHolder>{


    private Context context;
    private List<Perhitunganacian1> acians;

    public Acian_adapter(Context context, List<Perhitunganacian1> acians) {
        this.context=context;
        this.acians=acians;
    }

    @Override
    public Acian_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listacian, viewGroup, false);
        return new Acian_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Acian_adapter.ViewHolder viewHolder, final int i) {
        final Perhitunganacian1 AcianModel = acians.get(i);
        final String Name = String.valueOf(acians.get(i).getNama());
        final String jenis = String.valueOf(acians.get(i).getJenisPengerjaan());
        final String Panjangdin = String.valueOf(acians.get(i).getPanjangdin());
        final String Tinggidin = String.valueOf(acians.get(i).getTinggidin());
        final String Sisi = String.valueOf(acians.get(i).getSisi());
        final String Jumlahdalamsak = String.valueOf(acians.get(i).getJumlahdalamsak());
        final String Metode = String.valueOf(acians.get(i).getMetode());
        final String Hargasemen = String.valueOf(acians.get(i).getHargasemen());
        final String Totalbiaya = String.valueOf(acians.get(i).getHargatotal());
        final String Semen = String.valueOf(acians.get(i).getJumlahkeperluansemen());
        final String Luas = String.valueOf(acians.get(i).getLuas());

        viewHolder.Nama.setText(Name);
        viewHolder.semen.setText(Semen);

        viewHolder.editacian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editacian = new Intent(context, Editacian.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle setData = new Bundle();
                setData.putString("nama", Name);
                setData.putString("jenis_pengerjaan", "bangunan");
                setData.putString("panjangdin", Panjangdin);
                setData.putString("tinggidin", Tinggidin);
                setData.putString("sisi", Sisi);
                setData.putString("luas_acian",Luas);
                setData.putString("Jumlahkeperluansemen", Semen);
                setData.putString("jumlahdalamsak", Jumlahdalamsak);
                setData.putString("metode", Metode);
                setData.putString("hargasemen", Hargasemen);
                setData.putString("totalbiaya", Totalbiaya);
                setData.putString("jenispe", jenis);
                editacian.putExtras(setData);
                context.startActivity(editacian);

            }
        });
//        viewHolder.detailacian.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog detaildialog = new Dialog(context);
//                detaildialog.setContentView(R.layout.detail_acian);
//                TextView nama = detaildialog.findViewById(R.id.nama);
//                TextView luasacian = detaildialog.findViewById(R.id.luasacian);
//                TextView Batako1 = detaildialog.findViewById(R.id.Batako);
//                TextView Semen1 = detaildialog.findViewById(R.id.semen1);
//                TextView Pasir1 = detaildialog.findViewById(R.id.pasir);
//                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);
//                nama.setText(Name);
//                luasacian.setText("Panjang "+Panjangbid+"m "+",Tinggi "+Tinggibid+"m "+"" +
//                        ",Panjang panel pintu "+Panjangpin+"m "+",Tinggi panel pintu "+Tinggipin+
//                        ",Panjang panel jendela "+Panjangjen+"m "+",Tinggi panel jendela "+Tinggijen+"m "+
//                        ",Luas Acian "+Luas+"m ");
//                Batako1.setText("Jumlah "+Batako+"buah"+",Harga Rp."+Hargabatako);
//                Semen1.setText("Berat "+Semen+"kg"+",Harga Rp."+Hargasemen);
//                Pasir1.setText("Jumlah "+Pasir+"m3"+",Harga Rp."+Hargapasir);
//                totalbiaya.setText(",Harga Rp."+Totalbiaya);
//
//                detaildialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                detaildialog.show();
//            }
//        });
        viewHolder.deleteacian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog deletedialog = new Dialog(context);
                deletedialog.setContentView(R.layout.delete_dialog);
                TextView deletemessage = deletedialog.findViewById(R.id.textdelete);
                TextView silang = deletedialog.findViewById(R.id.silang);
                Button btndelete = deletedialog.findViewById(R.id.buttonhapus);
                Button btncancel = deletedialog.findViewById(R.id.buttoncancel);
                deletemessage.setText("Yakin ingin menghapus data acian " +Name);

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
        return acians.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nama,semen,pasir,batako,edit;
        private ImageView image;
        private Button editacian,deleteacian,detailacian;
        Dialog deletedialog;

        public ViewHolder(View view) {
            super(view);
            Nama = (TextView)view.findViewById(R.id.Nama);
            semen = (TextView)view.findViewById(R.id.semen);
            pasir = (TextView)view.findViewById(R.id.pasir);
            batako = (TextView)view.findViewById(R.id.Batako);
            editacian = view.findViewById(R.id.editbidang);
            deleteacian = view.findViewById(R.id.Deletebidang);
            detailacian = view.findViewById(R.id.detailbidang);




        }
    }


}
