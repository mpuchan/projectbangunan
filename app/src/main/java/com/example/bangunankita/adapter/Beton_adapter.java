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
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bangunankita.Editbeton;
import com.example.bangunankita.Model.Perhitunganbeton1;
import com.example.bangunankita.R;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Beton_adapter  extends RecyclerView.Adapter<Beton_adapter.ViewHolder> {

    private Context context;
    private List<Perhitunganbeton1> betons;

    public Beton_adapter(Context context, List<Perhitunganbeton1> betons) {
        this.context=context;
        this.betons=betons;
    }

    @Override
    public Beton_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listbeton, viewGroup, false);
        return new Beton_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Beton_adapter.ViewHolder viewHolder, final int i) {
        final Perhitunganbeton1 BetonModel = betons.get(i);
        final String Id = String.valueOf(betons.get(i).getId());
        final String ProyekId = String.valueOf(betons.get(i).getProyekId());
        final String Name = String.valueOf(betons.get(i).getNama());
        final String Pilihanbeton = String.valueOf(betons.get(i).getPilihanbeton());
        final String Panjangbeton = String.valueOf(betons.get(i).getPanjangbeton());
        final String Namapapan = String.valueOf(betons.get(i).getNamapapan());
        final String Namapaku = String.valueOf(betons.get(i).getNamapaku());
        final String Namabesi = String.valueOf(betons.get(i).getNamabesi());
        final String Namabegel = String.valueOf(betons.get(i).getNamabegel());
        final String Namakawat = String.valueOf(betons.get(i).getNamakawat());
        final String Namapasir = String.valueOf(betons.get(i).getNamapasir());
        final String Namasemen = String.valueOf(betons.get(i).getNamasemen());
        final String Namabatu = String.valueOf(betons.get(i).getNamabatu());
        final String Hargapapan = String.valueOf(betons.get(i).getHargapapan());
        final String Hargapaku = String.valueOf(betons.get(i).getHargapaku());
        final String Hargabesi = String.valueOf(betons.get(i).getHargabesi());
        final String Hargabegel = String.valueOf(betons.get(i).getHargabegel());
        final String Hargakawat = String.valueOf(betons.get(i).getHargakawat());
        final String Hargapasir = String.valueOf(betons.get(i).getHargapasir());
        final String Hargasemen = String.valueOf(betons.get(i).getHargasemen());
        final String Hargabatu = String.valueOf(betons.get(i).getHargabatu());
        final String Jumlahpapan = String.valueOf(betons.get(i).getJumlahpapan());
        final String Jumlahpaku = String.valueOf(betons.get(i).getJumlahpaku());
        final String Jumlahbesi = String.valueOf(betons.get(i).getJumlahbesi());
        final String Jumlahbegel = String.valueOf(betons.get(i).getJumlahbegel());
        final String Jumlahkawat = String.valueOf(betons.get(i).getJumlahkawat());
        final String Jumlahpasir = String.valueOf(betons.get(i).getJumlahpasir());
        final String Jumlahsemen = String.valueOf(betons.get(i).getJumlahsemen());
        final String Jumlahbatu = String.valueOf(betons.get(i).getJumlahbatu());
        final String Jumlahsemendalamsak = String.valueOf(betons.get(i).getJumlahsemendalamsak());
        final String Jumlahpasirtruk = String.valueOf(betons.get(i).getJumlahpasirtruk());
        final String Jumlahbatudalamtruk = String.valueOf(betons.get(i).getJumlahbatudalamtruk());
        final String Jumlahbesibatang = String.valueOf(betons.get(i).getJumlahbesibatang());
        final String Hargatotpapan = String.valueOf(betons.get(i).getHargatotalpapan());
        final String Hargatotpaku = String.valueOf(betons.get(i).getHargatotalpaku());
        final String Hargatotbesi = String.valueOf(betons.get(i).getHargatotalbesi());
        final String Hargatotbegel = String.valueOf(betons.get(i).getHargatotalbegel());
        final String Hargatotkawat = String.valueOf(betons.get(i).getHargatotalkawat());
        final String Hargatotpasir = String.valueOf(betons.get(i).getHargatotalpasir());
        final String Hargatotsemen = String.valueOf(betons.get(i).getHargatotalsemen());
        final String Hargatotbatu = String.valueOf(betons.get(i).getHargatotalbatu());
        final String Hargatotal = String.valueOf(betons.get(i).getHargatotal());

        float total1 = Float.parseFloat(Hargatotal);
        DecimalFormat df = new DecimalFormat("#");
        String tothitungan = df.format(total1);
        int numbertotal = Integer.parseInt(tothitungan);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totals = formatter.format(numbertotal);
        viewHolder.Nama.setText(Name);
        viewHolder.semen.setText(Jumlahsemendalamsak +"sak");
        viewHolder.besi.setText(Jumlahbesi+ "lonjor");
        viewHolder.detailtotal.setText("Rp."+totals);

//        viewHolder.batako.setText(Batako);
//


//
        viewHolder.editbeton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editbeton = new Intent(context, Editbeton.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle setData = new Bundle();
                setData.putString("ProyekId", ProyekId);
                setData.putString("id", Id);
                setData.putString("nama", Name);
                setData.putString("pilihanbeton", Pilihanbeton);
                setData.putString("panjangbeton", Panjangbeton);
                setData.putString("namapapan", Namapapan);
                setData.putString("namapaku", Namapaku);
                setData.putString("namabesi", Namabesi);
                setData.putString("namabegel", Namabegel);
                setData.putString("namakawat", Namakawat);
                setData.putString("namapasir", Namapasir);
                setData.putString("namasemen", Namasemen);
                setData.putString("namabatu", Namabatu);
                setData.putString("hargapapan", Hargapapan);
                setData.putString("hargapaku", Hargapaku);
                setData.putString("hargabesi", Hargabesi);
                setData.putString("hargabegel", Hargabegel);
                setData.putString("hargakawat", Hargakawat);
                setData.putString("hargapasir", Hargapasir);
                setData.putString("hargasemen", Hargasemen);
                setData.putString("hargabatu", Hargabatu);
                setData.putString("jumlahpapan", Jumlahpapan);
                setData.putString("jumlahpaku", Jumlahpaku);
                setData.putString("jumlahbesi", Jumlahbesi);
                setData.putString("jumlahbesibatang", Jumlahbesibatang);
                setData.putString("jumlahsemensak", Jumlahsemendalamsak);
                setData.putString("jumlahpasirtruk", Jumlahpasirtruk);
                setData.putString("jumlahbatutruk", Jumlahbatudalamtruk);
                setData.putString("jumlahbegel", Jumlahbegel);
                setData.putString("jumlahkawat", Jumlahkawat);
                setData.putString("jumlahpasir", Jumlahpasir);
                setData.putString("jumlahsemen", Jumlahsemen);
                setData.putString("jumlahbatu", Jumlahbatu);

                setData.putString("hargatotalpapan", Hargatotpapan);
                setData.putString("hargatotalpaku", Hargatotpaku);
                setData.putString("hargatotalbesi", Hargatotbesi);
                setData.putString("hargatotalbegel", Hargatotbegel);
                setData.putString("hargatotalkawat", Hargatotkawat);
                setData.putString("hargatotalpasir", Hargatotpasir);
                setData.putString("hargatotalsemen", Hargatotsemen);
                setData.putString("hargatotalbatu", Hargatotbatu);
                setData.putString("hargatotal", Hargatotal);



                editbeton.putExtras(setData);
                context.startActivity(editbeton);

            }
        });
//        viewHolder.detailbeton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog detaildialog = new Dialog(context);
//                detaildialog.setContentView(R.layout.detail_beton);
//                TextView nama = detaildialog.findViewById(R.id.nama);
//                TextView luasbeton = detaildialog.findViewById(R.id.luasbeton);
//                TextView Batako1 = detaildialog.findViewById(R.id.Batako);
//                TextView Semen1 = detaildialog.findViewById(R.id.semen1);
//                TextView Pasir1 = detaildialog.findViewById(R.id.pasir);
//                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);
//                nama.setText(Name);
//                luasbeton.setText("Panjang "+Panjangbid+"m "+",Tinggi "+Tinggibid+"m "+"" +
//                        ",Panjang panel pintu "+Panjangpin+"m "+",Tinggi panel pintu "+Tinggipin+
//                        ",Panjang panel jendela "+Panjangjen+"m "+",Tinggi panel jendela "+Tinggijen+"m "+
//                        ",Luas beton "+Luas+"m ");
//                Batako1.setText("Jumlah "+Batako+"buah"+",Harga Rp."+Hargabatako);
//                Semen1.setText("Berat "+Semen+"kg"+",Harga Rp."+Hargasemen);
//                Pasir1.setText("Jumlah "+Pasir+"m3"+",Harga Rp."+Hargapasir);
//                totalbiaya.setText(",Harga Rp."+Totalbiaya);
//
//                detaildialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                detaildialog.show();
//            }
//        });
        viewHolder.deletebeton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog deletedialog = new Dialog(context);
                deletedialog.setContentView(R.layout.delete_dialog);
                TextView deletemessage = deletedialog.findViewById(R.id.textdelete);
                TextView silang = deletedialog.findViewById(R.id.silang);
                Button btndelete = deletedialog.findViewById(R.id.buttonhapus);
                Button btncancel = deletedialog.findViewById(R.id.buttoncancel);
                deletemessage.setText("Yakin ingin menghapus data beton " +Name);
                int ID = Integer.parseInt(Id);
                String apiKey = "oa00000000app";
                SessionManager sm;
                sm= new SessionManager(context);
                HashMap<String,String> map = sm.getDetailLogin();
                String token=(map.get(sm.KEY_TOKEN));
                btndelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Call<Void> call = ApiClient.getRequestInterface().actionDeletePerhitunganbeton(ID,apiKey,token);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.code() == 200 ) {
                                    deletedialog.hide();

                                    Toast.makeText(context, "Sukses hapus!",
                                            Toast.LENGTH_SHORT).show();



                                } else if (response.code() == 422) {
                                    Toast.makeText(context, "Something wrong!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
                                Toast.makeText(context, "Oops! Something went wrong!" + t.getMessage(),
                                        Toast.LENGTH_SHORT).show();

                            }
                        });
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
//
    }

    @Override
    public int getItemCount() {
        return betons.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nama,semen,besi,edit,detailtotal;
        private ImageView image;
        private Button editbeton,deletebeton,detailbeton;
        Dialog deletedialog;

        public ViewHolder(View view) {
            super(view);
            Nama = (TextView)view.findViewById(R.id.Nama1);
            semen = (TextView)view.findViewById(R.id.semen);
            besi = (TextView)view.findViewById(R.id.besi);
            editbeton = view.findViewById(R.id.editbeton);
            deletebeton = view.findViewById(R.id.Deletebeton);
            detailbeton = view.findViewById(R.id.detailbeton);
            detailtotal=view.findViewById(R.id.detailtotal);




        }
    }


}
