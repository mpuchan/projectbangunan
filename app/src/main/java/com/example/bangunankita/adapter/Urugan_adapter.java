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

import com.example.bangunankita.Editurugan;
import com.example.bangunankita.Model.Perhitunganacian1;
import com.example.bangunankita.Model.Perhitunganurugan1;
import com.example.bangunankita.Perhitunganurugan;
import com.example.bangunankita.R;

import java.util.List;

public class Urugan_adapter extends RecyclerView.Adapter<Urugan_adapter.ViewHolder>{

    private Context context;
    private List<Perhitunganurugan1> urugans;

    public Urugan_adapter(Context context, List<Perhitunganurugan1> urugans) {
        this.context=context;
        this.urugans=urugans;
    }

    @Override
    public Urugan_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listurugan, viewGroup, false);
        return new Urugan_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Urugan_adapter.ViewHolder viewHolder, final int i) {
        final Perhitunganurugan1 UruganModel = urugans.get(i);
        final String Name = String.valueOf(urugans.get(i).getNama());
        final String jenis = String.valueOf(urugans.get(i).getJenisPengerjaan());
        final String Panjangdin = String.valueOf(urugans.get(i).getPanjang());
        final String Tinggidin = String.valueOf(urugans.get(i).getTinggi());
        final String Lebar = String.valueOf(urugans.get(i).getLebar());
        final String volume = String.valueOf(urugans.get(i).getVolume());
        final String Jumlahdalamtruk= String.valueOf(urugans.get(i).getJumlahdalamtruk());
        final String Hargapasir = String.valueOf(urugans.get(i).getHargapasir());
        final String Totalbiaya = String.valueOf(urugans.get(i).getHargatotal());
        final String Pasir = String.valueOf(urugans.get(i).getJumlahkeperluanpasir());

        viewHolder.Nama.setText(Name);
        viewHolder.pasir.setText(Pasir);


        viewHolder.editurugan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editurugan = new Intent(context, Editurugan.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle setData = new Bundle();
                setData.putString("nama", Name);
                setData.putString("jenis_pengerjaan", jenis);
                setData.putString("panjang", Panjangdin);
                setData.putString("tinggi", Tinggidin);
                setData.putString("lebar",Lebar);
                setData.putString("volume",volume);
                setData.putString("jumlahkeperluanpasir", Pasir);
                setData.putString("jumlahdalamtruk", Jumlahdalamtruk);
                setData.putString("hargapasir", Hargapasir);
                editurugan.putExtras(setData);
                context.startActivity(editurugan);

            }
        });
//        viewHolder.detailurugan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog detaildialog = new Dialog(context);
//                detaildialog.setContentView(R.layout.detail_urugan);
//                TextView nama = detaildialog.findViewById(R.id.nama);
//                TextView luasurugan = detaildialog.findViewById(R.id.luasurugan);
//                TextView Batako1 = detaildialog.findViewById(R.id.Batako);
//                TextView Semen1 = detaildialog.findViewById(R.id.semen1);
//                TextView Pasir1 = detaildialog.findViewById(R.id.pasir);
//                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);
//                nama.setText(Name);
//                luasurugan.setText("Panjang "+Panjangbid+"m "+",Tinggi "+Tinggibid+"m "+"" +
//                        ",Panjang panel pintu "+Panjangpin+"m "+",Tinggi panel pintu "+Tinggipin+
//                        ",Panjang panel jendela "+Panjangjen+"m "+",Tinggi panel jendela "+Tinggijen+"m "+
//                        ",Luas Urugan "+Luas+"m ");
//                Batako1.setText("Jumlah "+Batako+"buah"+",Harga Rp."+Hargabatako);
//                Semen1.setText("Berat "+Semen+"kg"+",Harga Rp."+Hargasemen);
//                Pasir1.setText("Jumlah "+Pasir+"m3"+",Harga Rp."+Hargapasir);
//                totalbiaya.setText(",Harga Rp."+Totalbiaya);
//
//                detaildialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                detaildialog.show();
//            }
//        });
        viewHolder.deleteurugan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog deletedialog = new Dialog(context);
                deletedialog.setContentView(R.layout.delete_dialog);
                TextView deletemessage = deletedialog.findViewById(R.id.textdelete);
                TextView silang = deletedialog.findViewById(R.id.silang);
                Button btndelete = deletedialog.findViewById(R.id.buttonhapus);
                Button btncancel = deletedialog.findViewById(R.id.buttoncancel);
                deletemessage.setText("Yakin ingin menghapus data urugan " +Name);

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
        return urugans.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nama,semen,pasir,batako,edit;
        private ImageView image;
        private Button editurugan,deleteurugan,detailurugan;
        Dialog deletedialog;

        public ViewHolder(View view) {
            super(view);
            Nama = (TextView)view.findViewById(R.id.Nama);
            semen = (TextView)view.findViewById(R.id.semen);
            pasir = (TextView)view.findViewById(R.id.pasir);
            batako = (TextView)view.findViewById(R.id.Batako);
            editurugan = view.findViewById(R.id.editbidang);
            deleteurugan = view.findViewById(R.id.Deletebidang);
            detailurugan = view.findViewById(R.id.detailbidang);




        }
    }




}
