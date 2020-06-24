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

//import com.example.bangunankita.Editatap;
//import com.example.bangunankita.Model.Perhitunganatap1;
import com.example.bangunankita.R;

import java.util.List;

public class Atap_adapter  {
//    extends RecyclerView.Adapter<Atap_adapter.ViewHolder>
//    private Context context;
//    private List<Perhitunganatap1> ataps;
//
//    public Atap_adapter(Context context, List<Perhitunganatap1> ataps) {
//        this.context=context;
//        this.ataps=ataps;
//    }
//
//    @Override
//    public Atap_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listatap, viewGroup, false);
//        return new Atap_adapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(Atap_adapter.ViewHolder viewHolder, final int i) {
//        final Perhitunganatap1 AtapModel = ataps.get(i);
//        final String Name = String.valueOf(ataps.get(i).getNama());
//        final String Panjangbid = String.valueOf(ataps.get(i).getPanjangbid());
//        final String Tinggibid = String.valueOf(ataps.get(i).getTinggibid());
//        final String Panjangjen = String.valueOf(ataps.get(i).getPanjangjen());
//        final String Tinggijen = String.valueOf(ataps.get(i).getTinggijen());
//        final String Panjangpin = String.valueOf(ataps.get(i).getPanjangpin());
//        final String Tinggipin = String.valueOf(ataps.get(i).getTinggipin());
//        final String Jumlahdalamsak = String.valueOf(ataps.get(i).getJumlahdalamsak());
//        final String Metode = String.valueOf(ataps.get(i).getMetode());
//        final String Hargabatako = String.valueOf(ataps.get(i).getHargabatako());
//        final String Hargapasir = String.valueOf(ataps.get(i).getHargapasir());
//        final String Hargasemen = String.valueOf(ataps.get(i).getHargasemen());
//        final String Totalbiaya = String.valueOf(ataps.get(i).getHargatotal());
//
//
//
//        final String Semen = String.valueOf(ataps.get(i).getJumlahkeperluansemen());
//        final String Pasir = String.valueOf(ataps.get(i).getJumlahkeperluanpasir());
//        final String Batako = String.valueOf(ataps.get(i).getJumlahkeperluanbatako());
//        final String Luas = String.valueOf(ataps.get(i).getLuasAtap());
//
//        viewHolder.Nama.setText(Name);
//        viewHolder.semen.setText(Semen);
//        viewHolder.pasir.setText(Pasir);
//        viewHolder.batako.setText(Batako);
//
//        viewHolder.editatap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent editatap = new Intent(context, Editatap.class)
//                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                Bundle setData = new Bundle();
//                setData.putString("nama", Name);
//                setData.putString("jenis_pengerjaan", "bangunan");
//                setData.putString("panjangbid", Panjangbid);
//                setData.putString("tinggibid", Tinggibid);
//                setData.putString("panjangpin", Panjangpin);
//                setData.putString("tinggipin", Tinggipin);
//                setData.putString("panjangjen",Panjangjen);
//                setData.putString("tinggijen", Tinggijen);
//                setData.putString("luas_atap",Luas);
//                setData.putString("jumlahkeperluanbatako", Batako);
//                setData.putString("jumlahkeperluanpasir", Pasir);
//                setData.putString("Jumlahkeperluansemen", Semen);
//                setData.putString("jumlahdalamsak", Jumlahdalamsak);
//                setData.putString("metode", Metode);
//                setData.putString("hargabatako", Hargabatako);
//                setData.putString("hargapasir", Hargapasir);
//                setData.putString("hargasemen", Hargasemen);
//                editatap.putExtras(setData);
//                context.startActivity(editatap);
//
//            }
//        });
//        viewHolder.detailatap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog detaildialog = new Dialog(context);
//                detaildialog.setContentView(R.layout.detail_atap);
//                TextView nama = detaildialog.findViewById(R.id.nama);
//                TextView luasatap = detaildialog.findViewById(R.id.luasatap);
//                TextView Batako1 = detaildialog.findViewById(R.id.Batako);
//                TextView Semen1 = detaildialog.findViewById(R.id.semen1);
//                TextView Pasir1 = detaildialog.findViewById(R.id.pasir);
//                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);
//                nama.setText(Name);
//                luasatap.setText("Panjang "+Panjangbid+"m "+",Tinggi "+Tinggibid+"m "+"" +
//                        ",Panjang panel pintu "+Panjangpin+"m "+",Tinggi panel pintu "+Tinggipin+
//                        ",Panjang panel jendela "+Panjangjen+"m "+",Tinggi panel jendela "+Tinggijen+"m "+
//                        ",Luas Atap "+Luas+"m ");
//                Batako1.setText("Jumlah "+Batako+"buah"+",Harga Rp."+Hargabatako);
//                Semen1.setText("Berat "+Semen+"kg"+",Harga Rp."+Hargasemen);
//                Pasir1.setText("Jumlah "+Pasir+"m3"+",Harga Rp."+Hargapasir);
//                totalbiaya.setText(",Harga Rp."+Totalbiaya);
//
//                detaildialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                detaildialog.show();
//            }
//        });
//        viewHolder.deleteatap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog deletedialog = new Dialog(context);
//                deletedialog.setContentView(R.layout.delete_dialog);
//                TextView deletemessage = deletedialog.findViewById(R.id.textdelete);
//                TextView silang = deletedialog.findViewById(R.id.silang);
//                Button btndelete = deletedialog.findViewById(R.id.buttonhapus);
//                Button btncancel = deletedialog.findViewById(R.id.buttoncancel);
//                deletemessage.setText("Yakin ingin menghapus data atap " +Name);
//
//                btndelete.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//                btncancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//                deletedialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                deletedialog.show();
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return ataps.size();
//    }
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        private TextView Nama,semen,pasir,batako,edit;
//        private ImageView image;
//        private Button editatap,deleteatap,detailatap;
//        Dialog deletedialog;
//
//        public ViewHolder(View view) {
//            super(view);
//            Nama = (TextView)view.findViewById(R.id.Nama);
//            semen = (TextView)view.findViewById(R.id.semen);
//            pasir = (TextView)view.findViewById(R.id.pasir);
//            batako = (TextView)view.findViewById(R.id.Batako);
//            editatap = view.findViewById(R.id.editatap);
//            deleteatap = view.findViewById(R.id.Deleteatap);
//            detailatap = view.findViewById(R.id.detailatap);
//
//
//
//
//        }
//    }
}

