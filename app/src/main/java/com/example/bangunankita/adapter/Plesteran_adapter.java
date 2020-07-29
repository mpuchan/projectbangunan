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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bangunankita.Editbidang;
import com.example.bangunankita.Editplesteran;
import com.example.bangunankita.Model.Perhitunganbidang1;
import com.example.bangunankita.Model.Perhitunganplesteran1;
import com.example.bangunankita.Model.Perhitunganurugan1;
import com.example.bangunankita.R;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        final String id = String.valueOf(plesterans.get(i).getId());
        final String Proyekid = String.valueOf(plesterans.get(i).getProyekId());
        final String Name = String.valueOf(plesterans.get(i).getNama());
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
        final String Hargapasirtotal = String.valueOf(plesterans.get(i).getHargapasirtotal());
        final String Hargasementotal = String.valueOf(plesterans.get(i).getHargasementotal());
        final String Hargatotal = String.valueOf(plesterans.get(i).getHargatotal());

        float total1 = Float.parseFloat(Hargatotal);
        DecimalFormat df = new DecimalFormat("#");
        String tothitungan = df.format(total1);
        int numbertotal = Integer.parseInt(tothitungan);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totals = formatter.format(numbertotal);
        viewHolder.Nama.setText(Name);
        viewHolder.detailtotal.setText("Rp."+ totals);

        viewHolder.semen.setText(Jumlahkeperluanpasir +"m3");
        viewHolder.pasir.setText(Jumlahdalamsak +"sak");

        viewHolder.editbidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editplesteran = new Intent(context, Editplesteran.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle setData = new Bundle();
                setData.putString("id", id);
                setData.putString("ProyekId", Proyekid);
                setData.putString("nama", Name);
                setData.putString("jenis_pengerjaan", Jenispengerjaan);
                setData.putString("panjangdin", Panjangdin);
                setData.putString("tinggidin", Tinggidin);
                setData.putString("tebal", Tebal);
                setData.putString("sisi", Sisi);
                setData.putString("volume",Volume);
                setData.putString("nama_semen", Nama_semen);
                setData.putString("nama_pasir", Nama_pasir);
                setData.putString("jumlahkeperluanpasir", Jumlahkeperluanpasir);
                setData.putString("Jumlahkeperluansemen", Jumlahkeperluansemen);
                setData.putString("jumlahdalamsak", Jumlahdalamsak);
                setData.putString("metode", Metode);
                setData.putString("hargapasir", Hargapasir);
                setData.putString("hargasemen", Hargasemen);
                setData.putString("hargapasirtotal", Hargapasirtotal);
                setData.putString("hargasementotal", Hargasementotal);
                setData.putString("hargatotal", Hargatotal);
                editplesteran.putExtras(setData);
                context.startActivity(editplesteran);
            }
        });
        viewHolder.detailbidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog detaildialog = new Dialog(context);
                detaildialog.setContentView(R.layout.detail_plesteran);
                TextView nama = detaildialog.findViewById(R.id.nama);
                TextView luasbidang = detaildialog.findViewById(R.id.volume);
                TextView Semen1 = detaildialog.findViewById(R.id.jumlahsemen);
                TextView Pasir1 = detaildialog.findViewById(R.id.jumlahpasir);
                TextView namapasir = detaildialog.findViewById(R.id.namapasir);
                TextView namasemen = detaildialog.findViewById(R.id.namasemen);
                TextView hargatotalsemen = detaildialog.findViewById(R.id.semen1);
                TextView hargatotalpasir = detaildialog.findViewById(R.id.pasir);
                TextView sathargas = detaildialog.findViewById(R.id.hargas);
                TextView sathargap = detaildialog.findViewById(R.id.hargap);
                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);

                String resulthargas = null;
                resulthargas = Hargasemen.replace(".0","");
                String resulthargap = null;
                resulthargap = Hargapasir.replace(".0","");

                String resulthargastotal = null;
                resulthargastotal = Hargasementotal.replace(".0","");
                String resulthargaptotal = null;
                resulthargaptotal = Hargapasirtotal.replace(".0","");
                nama.setText(Name);
//                luasbidang.setText("Panjang "+Panjangbid+"m "+",Tinggi "+Tinggibid+"m "+"" +
////                        ",Panjang panel pintu "+Panjangpin+"m "+",Tinggi panel pintu "+Tinggipin+
////                        ",Panjang panel jendela "+Panjangjen+"m "+",Tinggi panel jendela "+Tinggijen+"m "+
////                        ",Luas Bidang "+Luas+"m ");

                luasbidang.setText("Panjang: "+Panjangdin+ "Tinggi: "+Tinggidin+ "Tebal: "+Tebal +"Sisi: "+Sisi+"Volume Plesteran"+Volume);

                hargatotalsemen.setText("Rp."+resulthargastotal);
                hargatotalpasir.setText("Rp."+resulthargaptotal);
                namasemen.setText(Nama_semen);
                namapasir.setText(Nama_pasir);

                Semen1.setText(Jumlahkeperluansemen+"kg");
                Pasir1.setText(Jumlahkeperluanpasir+"m3");

                sathargas.setText("Rp."+resulthargas);
                sathargap.setText("Rp."+resulthargap);
                totalbiaya.setText("Rp."+Hargatotal);

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
                int ID = Integer.parseInt(id);
                String apiKey = "oa00000000app";
                SessionManager sm;
                sm= new SessionManager(context);
                HashMap<String,String> map = sm.getDetailLogin();
                String token=(map.get(sm.KEY_TOKEN));

                btndelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Call<Void> call = ApiClient.getRequestInterface().actionDeletePerhitunganplesteran(ID,apiKey,token);
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
        return plesterans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nama,semen,pasir,edit,detailtotal;
        private ImageView image;
        private Button editbidang,deletebidang,detailbidang;
        Dialog deletedialog;

        public ViewHolder(View view) {
            super(view);
            Nama = (TextView)view.findViewById(R.id.Nama1);
            semen = (TextView)view.findViewById(R.id.semen);
            pasir = (TextView)view.findViewById(R.id.pasir);
            editbidang = view.findViewById(R.id.editbidang);
            deletebidang = view.findViewById(R.id.Deletebidang);
            detailbidang = view.findViewById(R.id.detailbidang);
            detailtotal = view.findViewById(R.id.detailtotal);




        }
    }
}
