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

import com.example.bangunankita.Editbidang;
import com.example.bangunankita.Model.Perhitunganbidang1;
import com.example.bangunankita.Perhitunganbidang;
import com.example.bangunankita.R;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
        final String id = String.valueOf(bidangs.get(i).getId());
        final String Proyekid = String.valueOf(bidangs.get(i).getProyekId());
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
        final String Namabatako = String.valueOf(bidangs.get(i).getNamaBatako());
        final String Namapasir = String.valueOf(bidangs.get(i).getNamaPasir());
        final String Namasemen = String.valueOf(bidangs.get(i).getNamaSemen());
        final String Hargabatakototal = String.valueOf(bidangs.get(i).getHargabatakototal());
        final String Hargapasirtotal = String.valueOf(bidangs.get(i).getHargapasirtotal());
        final String Hargasementotal = String.valueOf(bidangs.get(i).getHargasementotal());
        final String Totalbiaya = String.valueOf(bidangs.get(i).getHargatotal());
        Double price = bidangs.get(i).getHargatotal();
        for (int j =0; j<price; j++){

    }

        final String Semen = String.valueOf(bidangs.get(i).getJumlahkeperluansemen());
        final String Pasir = String.valueOf(bidangs.get(i).getJumlahkeperluanpasir());
        final String Batako = String.valueOf(bidangs.get(i).getJumlahkeperluanbatako());
        final String Luas = String.valueOf(bidangs.get(i).getLuasBidang());

        float total1 = Float.parseFloat(Totalbiaya);
        DecimalFormat df = new DecimalFormat("#");
        String tothitungan = df.format(total1);
        int numbertotal = Integer.parseInt(tothitungan);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totals = formatter.format(numbertotal);


        viewHolder.Nama.setText(Name);
        viewHolder.semen.setText(Semen+"sak");
        viewHolder.pasir.setText(Pasir+"m3");
        viewHolder.batako.setText(Batako+"buah");
        viewHolder.detailtotal.setText("Rp."+totals);

        viewHolder.editbidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editbidang = new Intent(context, Editbidang.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle setData = new Bundle();
                setData.putString("id", id);
                setData.putString("ProyekId", Proyekid);
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
                setData.putString("nama_batako", Namabatako);
                setData.putString("nama_pasir", Namapasir);
                setData.putString("nama_semen", Namasemen);
                setData.putString("hargabatakototal", Hargabatakototal);
                setData.putString("hargapasirtotal", Hargapasirtotal);
                setData.putString("hargasementotal", Hargasementotal);
                setData.putString("hargatotal", Totalbiaya);
                editbidang.putExtras(setData);
                context.startActivity(editbidang);
            }
        });
        viewHolder.detailbidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog detaildialog = new Dialog(context);
                detaildialog.setContentView(R.layout.detail_bidang);
                Button keluar = detaildialog.findViewById(R.id.keluar);
                ImageView exit = detaildialog.findViewById(R.id.exit);
                TextView nama = detaildialog.findViewById(R.id.nama);
                TextView luasbidang = detaildialog.findViewById(R.id.luasbidang);
                TextView Batako1 = detaildialog.findViewById(R.id.jumlahbatako);
                TextView Semen1 = detaildialog.findViewById(R.id.jumlahsemen);
                TextView Pasir1 = detaildialog.findViewById(R.id.jumlahpasir);
                TextView namapasir = detaildialog.findViewById(R.id.namapasir);
                TextView namasemen = detaildialog.findViewById(R.id.namasemen);
                TextView namabatako = detaildialog.findViewById(R.id.namabatako);
                TextView hargatotalbatako = detaildialog.findViewById(R.id.Batako);
                TextView hargatotalsemen = detaildialog.findViewById(R.id.semen1);
                TextView hargatotalpasir = detaildialog.findViewById(R.id.pasir);
                TextView sathargab = detaildialog.findViewById(R.id.hargab);
                TextView sathargas = detaildialog.findViewById(R.id.hargas);
                TextView sathargap = detaildialog.findViewById(R.id.hargap);
                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);
                String resulthargab = null;
                resulthargab = Hargabatako.replace(".0","");
                String resulthargas = null;
                resulthargas = Hargasemen.replace(".0","");
                String resulthargap = null;
                resulthargap = Hargapasir.replace(".0","");

                String resulthargabtotal = null;
                resulthargabtotal = Hargabatakototal.replace(".0","");
                String resulthargastotal = null;
                resulthargastotal = Hargasementotal.replace(".0","");
                String resulthargaptotal = null;
                resulthargaptotal = Hargapasirtotal.replace(".0","");
                nama.setText(Name);
                luasbidang.setText("Panjang "+Panjangbid+"m "+",Tinggi "+Tinggibid+"m "+"" +
                        ",Panjang panel pintu "+Panjangpin+"m "+",Tinggi panel pintu "+Tinggipin+
                        ",Panjang panel jendela "+Panjangjen+"m "+",Tinggi panel jendela "+Tinggijen+"m "+
                        ",Luas Bidang "+Luas+"m ");

                hargatotalbatako.setText("Rp."+resulthargabtotal);
                hargatotalsemen.setText("Rp."+resulthargastotal);
                hargatotalpasir.setText("Rp."+resulthargaptotal);
                namabatako.setText(Namabatako);
                namasemen.setText(Namasemen);
                namapasir.setText(Namapasir);
                Batako1.setText(Batako+"buah");
                Semen1.setText(Semen+"kg");
                Pasir1.setText(Pasir+"m3");
                sathargab.setText("Rp."+resulthargab);
                sathargas.setText("Rp."+resulthargas);
                sathargap.setText("Rp."+resulthargap);
                totalbiaya.setText("Rp."+Totalbiaya);

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
                int ID = Integer.parseInt(id);
                String apiKey = "oa00000000app";
                SessionManager sm;
                sm= new SessionManager(context);
                HashMap<String,String> map = sm.getDetailLogin();
                String token=(map.get(sm.KEY_TOKEN));

                btndelete.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Call<Void> call = ApiClient.getRequestInterface().actionDeletePerhitunganbidang(ID,apiKey,token);
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
                        deletedialog.hide();
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
    public double getTotal(ArrayList<Perhitunganbidang1> list){

        double total=0.0;
        for(int i=0;i<list.size();i++){
            total=total+Double.parseDouble(String.valueOf(list.get(i).getHargatotal()));
        }
        return total;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nama,semen,pasir,batako,edit,detailtotal;
        private ImageView image;
        private Button editbidang,deletebidang,detailbidang;
        Dialog deletedialog;

        public ViewHolder(View view) {
            super(view);
            Nama = (TextView)view.findViewById(R.id.Nama);
            semen = (TextView)view.findViewById(R.id.semen);
            pasir = (TextView)view.findViewById(R.id.pasir);
            detailtotal = view.findViewById(R.id.detailtotal);
            batako = (TextView)view.findViewById(R.id.Batako);
            editbidang = view.findViewById(R.id.editbidang);
            deletebidang = view.findViewById(R.id.Deletebidang);
            detailbidang = view.findViewById(R.id.detailbidang);
        }
    }
}