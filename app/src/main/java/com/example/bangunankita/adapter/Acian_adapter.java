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

import com.example.bangunankita.Editacian;
import com.example.bangunankita.Model.Perhitunganacian1;
import com.example.bangunankita.R;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        final String Id = String.valueOf(acians.get(i).getId());
        final String Idproyek = String.valueOf(acians.get(i).getProyekId());
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
        final String Hargasementotal = String.valueOf(acians.get(i).getHargasementotal());
        final String Luas = String.valueOf(acians.get(i).getLuas());
        final String Namasemen = String.valueOf(acians.get(i).getNamaSemen());
        float total1 = Float.parseFloat(Totalbiaya);
        DecimalFormat df = new DecimalFormat("#");
        String tothitungan = df.format(total1);
        int numbertotal = Integer.parseInt(tothitungan);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totals = formatter.format(numbertotal);

        viewHolder.Nama.setText(Name);
        viewHolder.semen.setText(Jumlahdalamsak +"sak");
        viewHolder.detailtotal.setText("Rp."+ totals);

        viewHolder.editacian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editacian = new Intent(context, Editacian.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle setData = new Bundle();
                setData.putString("id", Id);
                setData.putString("ProyekId", Idproyek);
                setData.putString("nama", Name);
                setData.putString("jenis_pengerjaan", "bangunan");
                setData.putString("panjangdin", Panjangdin);
                setData.putString("tinggidin", Tinggidin);
                setData.putString("sisi", Sisi);
                setData.putString("luas_acian",Luas);
                setData.putString("Jumlahkeperluansemen", Semen);
                setData.putString("jumlahdalamsak", Jumlahdalamsak);
                setData.putString("metode", Metode);
                setData.putString("nama_semen",Namasemen);
                setData.putString("hargasemen", Hargasemen);
                setData.putString("hargasementotal", Hargasementotal);
                setData.putString("totalbiaya", Totalbiaya);
                setData.putString("jenispe", jenis);
                editacian.putExtras(setData);
                context.startActivity(editacian);
            }
        });
        viewHolder.detailacian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog detaildialog = new Dialog(context);
                detaildialog.setContentView(R.layout.detail_acian);
                TextView nama = detaildialog.findViewById(R.id.nama);
                TextView luasacian = detaildialog.findViewById(R.id.luasacian);
                TextView Semen1 = detaildialog.findViewById(R.id.semen1);
                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);
                TextView namasemen = detaildialog.findViewById(R.id.namasemen);
                TextView hargatotalsemen = detaildialog.findViewById(R.id.semen1);
                TextView sathargas = detaildialog.findViewById(R.id.hargas);
                nama.setText(Name);
                luasacian.setText("Panjang "+Panjangdin+"m "+",Tinggi "+Tinggidin+"m "+"Luas Acian "+Luas+"m ");
                String resulthargas = null;
                resulthargas = Hargasemen.replace(".0","");
                String resulthargastotal = null;
                resulthargastotal = Hargasementotal.replace(".0","");
                hargatotalsemen.setText("Rp."+resulthargastotal);
                namasemen.setText(Namasemen);
                Semen1.setText(Semen+"kg");
                sathargas.setText("Rp."+resulthargas);
                totalbiaya.setText("Rp."+Totalbiaya);

                detaildialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                detaildialog.show();
            }
        });
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
                int ID = Integer.parseInt(Id);
                String apiKey = "oa00000000app";
                SessionManager sm;
                sm= new SessionManager(context);
                HashMap<String,String> map = sm.getDetailLogin();
                String token=(map.get(sm.KEY_TOKEN));

                btndelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Call<Void> call = ApiClient.getRequestInterface().actionDeletePerhitunganacian(ID,apiKey,token);
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
        return acians.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nama,semen,pasir,batako,edit,detailtotal;
        private ImageView image;
        private Button editacian,deleteacian,detailacian;
        Dialog deletedialog;

        public ViewHolder(View view) {
            super(view);
            Nama = (TextView)view.findViewById(R.id.Nama);
            semen = (TextView)view.findViewById(R.id.semen);
            editacian = view.findViewById(R.id.editbidang);
            deleteacian = view.findViewById(R.id.Deletebidang);
            detailacian = view.findViewById(R.id.detailbidang);
            detailtotal=view.findViewById(R.id.detailtotal);

        }
    }


}
