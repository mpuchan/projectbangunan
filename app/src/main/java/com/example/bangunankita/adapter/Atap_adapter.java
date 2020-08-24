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

//import com.example.bangunankita.Editatap;
//import com.example.bangunankita.Model.Perhitunganatap1;
import com.example.bangunankita.Editatap;
import com.example.bangunankita.Model.Perhitunganatap1;
import com.example.bangunankita.R;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Atap_adapter extends RecyclerView.Adapter<Atap_adapter.ViewHolder>{

    private Context context;
    private List<Perhitunganatap1> ataps;

    public Atap_adapter(Context context, List<Perhitunganatap1> ataps) {
        this.context=context;
        this.ataps=ataps;
    }

    @Override
    public Atap_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listatap, viewGroup, false);
        return new Atap_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Atap_adapter.ViewHolder viewHolder, final int i) {
        final Perhitunganatap1 AtapModel = ataps.get(i);
        final String Id = String.valueOf(ataps.get(i).getId());
        final String ProyekId = String.valueOf(ataps.get(i).getProyekId());

        final String Name = String.valueOf(ataps.get(i).getNama());
        final String Luasatap = String.valueOf(ataps.get(i).getLuasatap());
        final String Panjangnok = String.valueOf(ataps.get(i).getPanjangnok());
        final String Namasemen= String.valueOf(ataps.get(i).getNamasemen());
        final String Namagenteng= String.valueOf(ataps.get(i).getNamagenteng());
        final String Namabubungan= String.valueOf(ataps.get(i).getNamabubungan());
        final String Namapasir = String.valueOf(ataps.get(i).getNamapasir());
        final String Jumlahsemen= String.valueOf(ataps.get(i).getJumlahsemen());
        final String Jumlahpasir= String.valueOf(ataps.get(i).getJumlahpasir());
        final String Jumlahgenteng= String.valueOf(ataps.get(i).getJumlahgenteng());
        final String Jumlahbubungan= String.valueOf(ataps.get(i).getJumlahbubungan());
        final String Jumlahdalamsak = String.valueOf(ataps.get(i).getJumlahsemendalamsak());
        final String Hargapasir = String.valueOf(ataps.get(i).getHargapasir());
        final String Hargasemen = String.valueOf(ataps.get(i).getHargasemen());
        final String Hargagenteng = String.valueOf(ataps.get(i).getHargagenteng());
        final String Hargabubungan = String.valueOf(ataps.get(i).getHargabubungan());
        final String Hargapasirtotal = String.valueOf(ataps.get(i).getHargatotalpasir());
        final String Hargasementotal = String.valueOf(ataps.get(i).getHargatotalsemen());
        final String Hargagentengtotal = String.valueOf(ataps.get(i).getHargatotalgenteng());
        final String Hargabubungantotal = String.valueOf(ataps.get(i).getHargatotalbubungan());
        final String Totalbiaya = String.valueOf(ataps.get(i).getHargatotal());

        float total1 = Float.parseFloat(Totalbiaya);
        DecimalFormat df = new DecimalFormat("#");
        String tothitungan = df.format(total1);
        int numbertotal = Integer.parseInt(tothitungan);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totals = formatter.format(numbertotal);

        viewHolder.Nama.setText(Name);
        viewHolder.genteng.setText(Jumlahgenteng + " buah" );
        viewHolder.bubungan.setText(Jumlahbubungan + " buah" );
        viewHolder.detailtotal.setText("Rp."+totals);

//

        viewHolder.editatap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editatap = new Intent(context, Editatap.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle setData = new Bundle();
                setData.putString("id",Id);
                setData.putString("ProyekId",ProyekId);
                setData.putString("Nama",Name);
                setData.putString("Luasatap",Luasatap);
                setData.putString("Panjangnok",Panjangnok);
                setData.putString("Namasemen",Namasemen);
                setData.putString("Namagenteng",Namagenteng);
                setData.putString("Namabubungan",Namabubungan);
                setData.putString("Namapasir",Namapasir);
                setData.putString("Jumlahsemen",Jumlahsemen);
                setData.putString("Jumlahpasir",Jumlahpasir);
                setData.putString("Jumlahgenteng",Jumlahgenteng);
                setData.putString("Jumlahbubungan",Jumlahbubungan);
                setData.putString("Jumlahdalamsak",Jumlahdalamsak);
                setData.putString("Hargapasir",Hargapasir);
                setData.putString("Hargasemen",Hargasemen);
                setData.putString("Hargagenteng",Hargagenteng);
                setData.putString("Hargabubungan",Hargabubungan);
                setData.putString("Hargapasirtotal",Hargapasirtotal);
                setData.putString("Hargasementotal",Hargasementotal);
                setData.putString("Hargagentengtotal", Hargagentengtotal);
                setData.putString("Hargabubungantotal",Hargabubungantotal);
                setData.putString("Totalbiaya",Totalbiaya);
                editatap.putExtras(setData);
                context.startActivity(editatap);

            }
        });
        viewHolder.detailatap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog detaildialog = new Dialog(context);
                detaildialog.setContentView(R.layout.detail_atap);
                TextView nama = detaildialog.findViewById(R.id.nama);
                TextView luasatap = detaildialog.findViewById(R.id.luasatap);
                TextView namagenteng = detaildialog.findViewById(R.id.namagenteng);
                TextView jumlahgenteng = detaildialog.findViewById(R.id.jumlahgenteng);
                TextView hargagenteng = detaildialog.findViewById(R.id.hargagenteng);
                TextView hargatotalgenteng = detaildialog.findViewById(R.id.hargatotalgenteng);
                TextView hargatotalbubungan = detaildialog.findViewById(R.id.hargatotalbubungan);
                TextView hargabubungan = detaildialog.findViewById(R.id.hargabubungan);
                TextView namabubungan= detaildialog.findViewById(R.id.namabubungan);
                TextView jumlahbubungan = detaildialog.findViewById(R.id.jumlahbubungan);
                TextView namasemen1 = detaildialog.findViewById(R.id.namasemen);
                TextView jumlahsemen1 = detaildialog.findViewById(R.id.jumlahsemen);
                TextView hargasemen1 = detaildialog.findViewById(R.id.hargas);
                TextView Semen1 = detaildialog.findViewById(R.id.semen1);
                TextView namapasir = detaildialog.findViewById(R.id.namapasir);
                TextView jumlah1 = detaildialog.findViewById(R.id.jumlahpasir);
                TextView hargap= detaildialog.findViewById(R.id.hargap);
                TextView Pasir1 = detaildialog.findViewById(R.id.pasir);
                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);
                nama.setText(Name);
                luasatap.setText("Luas Atap : "+Luasatap+" m2"+", Panjang nok : "+Panjangnok+" m");

                namagenteng.setText(Namagenteng);
                hargagenteng.setText("Rp."+Hargagenteng);
                hargatotalgenteng.setText("Rp."+Hargagentengtotal);
                jumlahgenteng.setText(Jumlahgenteng+"buah");
                namabubungan.setText(Namabubungan);
                hargabubungan.setText("Rp."+Hargabubungan);
                hargatotalbubungan.setText("Rp."+Hargabubungantotal);
                jumlahbubungan.setText(Jumlahbubungan+"buah");

                namasemen1.setText(Namasemen);
                jumlahsemen1.setText(Jumlahdalamsak+"sak");
                hargasemen1.setText("Rp."+Hargasemen);
                namapasir.setText(Namapasir);
                jumlah1.setText(Jumlahpasir+"m3");
                hargap.setText("Rp."+Hargapasir);

                Semen1.setText("Rp."+Hargasementotal);
                Pasir1.setText("Rp."+ Hargapasirtotal);
                totalbiaya.setText("Rp."+Totalbiaya);

                detaildialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                detaildialog.show();
            }
        });
        viewHolder.deleteatap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog deletedialog = new Dialog(context);
                deletedialog.setContentView(R.layout.delete_dialog);
                TextView deletemessage = deletedialog.findViewById(R.id.textdelete);
                TextView silang = deletedialog.findViewById(R.id.silang);
                Button btndelete = deletedialog.findViewById(R.id.buttonhapus);
                Button btncancel = deletedialog.findViewById(R.id.buttoncancel);
                deletemessage.setText("Yakin ingin menghapus data atap " +Name);
                deletemessage.setText("Yakin ingin menghapus data " +Name);
                int ID = Integer.parseInt(Id);
                String apiKey = "oa00000000app";
                SessionManager sm;
                sm= new SessionManager(context);
                HashMap<String,String> map = sm.getDetailLogin();
                String token=(map.get(sm.KEY_TOKEN));
                btndelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Call<Void> call = ApiClient.getRequestInterface().actionDeletePerhitunganatap(ID,apiKey,token);
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


    }

    @Override
    public int getItemCount() {
        return ataps.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nama,genteng,bubungan,detailtotal,edit;
        private ImageView image;
        private Button editatap,deleteatap,detailatap;
        Dialog deletedialog;

        public ViewHolder(View view) {
            super(view);
            Nama = (TextView)view.findViewById(R.id.Nama);
            genteng = (TextView)view.findViewById(R.id.genteng);
            bubungan = (TextView)view.findViewById(R.id.bubungan);
            editatap = view.findViewById(R.id.editatap);
            deleteatap = view.findViewById(R.id.Deleteatap);
            detailatap = view.findViewById(R.id.detailatap);
            detailtotal = view.findViewById(R.id.detailtotal);




        }
    }
}

