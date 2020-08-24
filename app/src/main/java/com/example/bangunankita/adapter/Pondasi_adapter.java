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

import com.example.bangunankita.Editpondasi;
import com.example.bangunankita.Model.Perhitunganpondasi1;
import com.example.bangunankita.R;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pondasi_adapter extends RecyclerView.Adapter<Pondasi_adapter.ViewHolder>  {

    private Context context;
    private List<Perhitunganpondasi1> pondasis;

    public Pondasi_adapter(Context context, List<Perhitunganpondasi1> pondasis) {
        this.context=context;
        this.pondasis=pondasis;
    }

    @Override
    public Pondasi_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listpondasi, viewGroup, false);
        return new Pondasi_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Pondasi_adapter.ViewHolder viewHolder, final int i) {
        final Perhitunganpondasi1 PondasiModel = pondasis.get(i);
        final String Id = String.valueOf(pondasis.get(i).getId());
        final String ProyekId = String.valueOf(pondasis.get(i).getProyekId());
        final String Nama = String.valueOf(pondasis.get(i).getNama());
        final String a = String.valueOf(pondasis.get(i).getA());
        final String b = String.valueOf(pondasis.get(i).getB());
        final String t = String.valueOf(pondasis.get(i).getT());
        final String p = String.valueOf(pondasis.get(i).getP());
        final String luas = String.valueOf(pondasis.get(i).getLuas());
        final String Namasemen= String.valueOf(pondasis.get(i).getNamasemen());
        final String Namabatu= String.valueOf(pondasis.get(i).getNamabatu());
        final String Namapasir = String.valueOf(pondasis.get(i).getNamapasir());
        final String Jumlahbatu = String.valueOf(pondasis.get(i).getJumlahbatu());
        final String Jumlahbatutruk= String.valueOf(pondasis.get(i).getJumlahbatutruk());
        final String Jumlahpasir = String.valueOf(pondasis.get(i).getJumlahpasir());
        final String Jumlahsemen = String.valueOf(pondasis.get(i).getJumlahsemen());
        final String Jumlahsemendalamsak = String.valueOf(pondasis.get(i).getJumlahsemendalamsak());
        final String Hargabatukali = String.valueOf(pondasis.get(i).getHargabatukali());
        final String Hargabatutotal= String.valueOf(pondasis.get(i).getHargabatutotal());
        final String Hargapasir = String.valueOf(pondasis.get(i).getHargapasir());
        final String Hargapasirtotal = String.valueOf(pondasis.get(i).getHargapasirtotal());
        final String Hargasemen = String.valueOf(pondasis.get(i).getHargasemen());
        final String Hargasementotal= String.valueOf(pondasis.get(i).getHargasementotal());
        final String Hargatotal = String.valueOf(pondasis.get(i).getHargatotal());
        final String Campuran = String.valueOf(pondasis.get(i).getMetode());

        float total1 = Float.parseFloat(Hargatotal);
        DecimalFormat df = new DecimalFormat("#");
        String tothitungan = df.format(total1);
        int numbertotal = Integer.parseInt(tothitungan);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totals = formatter.format(numbertotal);
        viewHolder.Nama.setText(Nama);
        viewHolder.detailtotal.setText("Rp."+totals);
        viewHolder.semen.setText(Jumlahsemendalamsak+"sak");
        viewHolder.pasir.setText(Jumlahpasir+"m3");


        viewHolder.editpondasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editpondasi = new Intent(context, Editpondasi.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle setData = new Bundle();
                setData.putString("ProyekId", ProyekId);
                setData.putString("id", Id);
                setData.putString("nama", Nama);
                setData.putString("a", a);
                setData.putString("b", b);
                setData.putString("t", t);
                setData.putString("p", p);
                setData.putString("luas",luas);
                setData.putString("namasemen", Namasemen);
                setData.putString("metode", Campuran);
                setData.putString("namabatu", Namabatu);
                setData.putString("namapasir", Namapasir);
                setData.putString("jumlahbatu", Jumlahbatu);
                setData.putString("jumlahsemen", Jumlahsemen);
                setData.putString("jumlahpasir", Jumlahpasir);
                setData.putString("hargabatu", Hargabatukali);
                setData.putString("jumlahbatutruk", Jumlahbatutruk);
                setData.putString("jumlahsemendalamsak", Jumlahsemendalamsak);
                setData.putString("hargasemen", Hargasemen);
                setData.putString("hargapasir", Hargapasir);
                setData.putString("hargabatutotal", Hargabatutotal);
                setData.putString("hargasementotal", Hargasementotal);
                setData.putString("hargapasirtotal", Hargapasirtotal);
                setData.putString("hargatotal", Hargatotal);

                editpondasi.putExtras(setData);
                context.startActivity(editpondasi);

            }
        });
        viewHolder.detailpondasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog detaildialog = new Dialog(context);
                detaildialog.setContentView(R.layout.detail_pondasi);
                TextView nama = detaildialog.findViewById(R.id.nama);
                TextView luaspondasi = detaildialog.findViewById(R.id.volumepon);
                TextView namabatu1 = detaildialog.findViewById(R.id.namabatu);
                TextView Batu1 = detaildialog.findViewById(R.id.jumlahbatu);
                TextView HargaBatu= detaildialog.findViewById(R.id.hargabatu);
                TextView TotalBatu= detaildialog.findViewById(R.id.totalbatu);
                Button keluar = detaildialog.findViewById(R.id.keluar);
                ImageView exit = detaildialog.findViewById(R.id.exit);
                TextView namasemen1 = detaildialog.findViewById(R.id.namasemen);
                TextView jumlahsemen1 = detaildialog.findViewById(R.id.jumlahsemen);
                TextView hargasemen1 = detaildialog.findViewById(R.id.hargas);
                TextView Semen1 = detaildialog.findViewById(R.id.semen1);
                TextView namapasir = detaildialog.findViewById(R.id.namapasir);
                TextView jumlah1 = detaildialog.findViewById(R.id.jumlahpasir);
                TextView hargap= detaildialog.findViewById(R.id.hargap);
                TextView Pasir1 = detaildialog.findViewById(R.id.pasir);
                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);
                float total1 = Float.parseFloat(Hargabatukali);
                float total2 = Float.parseFloat(Hargabatutotal);
                float total3 = Float.parseFloat(Hargasemen);
                float total4 = Float.parseFloat(Hargasementotal);
                float total5 = Float.parseFloat(Hargapasir);
                float total6 = Float.parseFloat(Hargapasirtotal);
                float total7 = Float.parseFloat(Hargatotal);
                DecimalFormat df = new DecimalFormat("#");
                String tothitungan = df.format(total1);
                String tothitungan1 = df.format(total2);
                String tothitungan2 = df.format(total3);
                String tothitungan3 = df.format(total4);
                String tothitungan4 = df.format(total5);
                String tothitungan5 = df.format(total6);
                String tothitungan6 = df.format(total7);
                int numbertotal = Integer.parseInt(tothitungan);
                int numbertotal1 = Integer.parseInt(tothitungan1);
                int numbertotal2 = Integer.parseInt(tothitungan2);
                int numbertotal3 = Integer.parseInt(tothitungan3);
                int numbertotal4 = Integer.parseInt(tothitungan4);
                int numbertotal5 = Integer.parseInt(tothitungan5);
                int numbertotal6 = Integer.parseInt(tothitungan6);

                DecimalFormat formatter = new DecimalFormat("#,###.##");
                String totals = formatter.format(numbertotal);
                String totals1 = formatter.format(numbertotal1);
                String totals2 = formatter.format(numbertotal2);
                String totals3 = formatter.format(numbertotal3);
                String totals4 = formatter.format(numbertotal4);
                String totals5 = formatter.format(numbertotal5);
                String totals6 = formatter.format(numbertotal6);


                nama.setText(Nama);
                luaspondasi.setText("a = "+a+"m "+",b = "+b+"m "+"" +
                        ",t = "+t+"m "+",p = "+p+"m "+
                        "Volume Pondasi "+luas+"m3 ");
                namabatu1.setText(Namabatu);
                HargaBatu.setText("Rp."+totals);
                TotalBatu.setText("Rp."+totals1);

                namasemen1.setText(Namasemen);
                jumlahsemen1.setText(Jumlahsemendalamsak+"sak");
                hargasemen1.setText("Rp."+totals2);
                namapasir.setText(Namapasir);
                jumlah1.setText(Jumlahpasir+"m3");
                hargap.setText("Rp."+totals4);

                Semen1.setText("Rp."+totals3);
                Pasir1.setText("Rp."+ totals5);
                Batu1.setText(Jumlahbatu+"m3");
                totalbiaya.setText("Rp."+totals6);

                detaildialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                detaildialog.show();

                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        detaildialog.hide();
                    }
                });
                keluar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        detaildialog.hide();
                    }
                });
            }
        });
        viewHolder.deletepondasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog deletedialog = new Dialog(context);
                deletedialog.setContentView(R.layout.delete_dialog);
                TextView deletemessage = deletedialog.findViewById(R.id.textdelete);
                TextView silang = deletedialog.findViewById(R.id.silang);
                Button btndelete = deletedialog.findViewById(R.id.buttonhapus);
                Button btncancel = deletedialog.findViewById(R.id.buttoncancel);
                deletemessage.setText("Yakin ingin menghapus data pondasi " +Nama);
                int ID = Integer.parseInt(Id);
                String apiKey = "oa00000000app";
                SessionManager sm;
                sm= new SessionManager(context);
                HashMap<String,String> map = sm.getDetailLogin();
                String token=(map.get(sm.KEY_TOKEN));

                btndelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Call<Void> call = ApiClient.getRequestInterface().actionDeletePerhitunganpondasi(ID,apiKey,token);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.code() == 200 ) {
                                    deletedialog.hide();

                                    Toast.makeText(context, "Sukses hapus!",
                                            Toast.LENGTH_SHORT).show();

//                                    Intent finish= new Intent(context, Perhitunganbidang.class)
//                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    context.startActivity(finish);

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


                deletedialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                deletedialog.show();
                btncancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deletedialog.hide();
                    }
                });
            }
        });

    }
//
    @Override
    public int getItemCount() {
        return pondasis.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nama,semen,pasir,edit,detailtotal;
        private ImageView image;
        private Button editpondasi,deletepondasi,detailpondasi;
        Dialog deletedialog;

        public ViewHolder(View view) {
            super(view);
            semen = view.findViewById(R.id.semen);
            pasir = view.findViewById(R.id.pasir);
            Nama = (TextView)view.findViewById(R.id.Nama1);
            editpondasi = view.findViewById(R.id.editpondasi);
            deletepondasi = view.findViewById(R.id.Deletepondasi);
            detailpondasi = view.findViewById(R.id.detailpondasi);
            detailtotal = view.findViewById(R.id.detailtotal);




        }
    }




}
