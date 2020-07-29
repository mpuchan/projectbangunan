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

import com.example.bangunankita.Editlantai;
import com.example.bangunankita.Model.Perhitunganlantai1;
import com.example.bangunankita.R;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Lantai_adapter extends RecyclerView.Adapter<Lantai_adapter.ViewHolder> {
    private Context context;
    private List<Perhitunganlantai1> lantais;

    public Lantai_adapter(Context context, List<Perhitunganlantai1> lantais) {
        this.context=context;
        this.lantais=lantais;
    }

    @Override
    public Lantai_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listlantai, viewGroup, false);
        return new Lantai_adapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        final Perhitunganlantai1 LantaiModel = lantais.get(i);
        final String id = String.valueOf(lantais.get(i).getId());
        final String ProyekId = String.valueOf(lantais.get(i).getProyekId());
        final String Name = String.valueOf(lantais.get(i).getNama());
        final String Panjangbid = String.valueOf(lantais.get(i).getPanjanglan());
        final String Tinggibid = String.valueOf(lantais.get(i).getLebarlan());
        final String Jumlahdalamsak = String.valueOf(lantais.get(i).getJumlahdalamsak());
        final String Metode = String.valueOf(lantais.get(i).getMetode());
        final String Hargakeramik = String.valueOf(lantais.get(i).getHargakeramik());
        final String Hargapasir = String.valueOf(lantais.get(i).getHargapasir());
        final String Hargasemen = String.valueOf(lantais.get(i).getHargasemen());
        final String Namasemen = String.valueOf(lantais.get(i).getNamaSemen());
        final String  Namasemennat= String.valueOf(lantais.get(i).getNamaSemennat());
        final String Namapasir = String.valueOf(lantais.get(i).getNamaPasir());
        final String Namakeramik = String.valueOf(lantais.get(i).getNamaKeramik());
        final String Harganat = String.valueOf(lantais.get(i).getHarganat());
        final String Harganattot = String.valueOf(lantais.get(i).getHarganattotal());
        final String Hargakeramiktot = String.valueOf(lantais.get(i).getHargakeramiktotal());
        final String Hargapasirtot = String.valueOf(lantais.get(i).getHargapasirtotal());
        final String Hargasementot = String.valueOf(lantais.get(i).getHargasementotal());

        final String Totalbiaya = String.valueOf(lantais.get(i).getHargatotal());

        final String Semen = String.valueOf(lantais.get(i).getJumlahkeperluansemen());
        final String Pasir = String.valueOf(lantais.get(i).getJumlahkeperluanpasir());
        final String Nat = String.valueOf(lantais.get(i).getJumlahkeperluannat());
        final String Keramik = String.valueOf(lantais.get(i).getJumlahkeperluankeramik());
        final String Keramikdus = String.valueOf(lantais.get(i).getJumlahkeperluankeramikdus());
        final String Luas = String.valueOf(lantais.get(i).getLuasLantai());

        float total1 = Float.parseFloat(Totalbiaya);
        DecimalFormat df = new DecimalFormat("#");
        String tothitungan = df.format(total1);
        int numbertotal = Integer.parseInt(tothitungan);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totals = formatter.format(numbertotal);

        viewHolder.Nama.setText(Name);
        viewHolder.semen.setText(Jumlahdalamsak+"sak");
        viewHolder.keramik.setText(Keramikdus+"dus");
        viewHolder.detailtotal.setText("Rp."+totals);



        viewHolder.editlantai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editlantai = new Intent(context, Editlantai.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle setData = new Bundle();
                setData.putString("nama", Name);
                setData.putString("ProyekId", ProyekId);
                setData.putString("id", id);
                setData.putString("jenis_pengerjaan", "bangunan");
                setData.putString("panjanglan", Panjangbid);
                setData.putString("lebarlan", Tinggibid);
                setData.putString("luas_lantai",Luas);
                setData.putString("jumlahkeperluankeramik", Keramik);
                setData.putString("jumlahkeperluankeramikdus", Keramikdus);
                setData.putString("jumlahkeperluanpasir", Pasir);
                setData.putString("Jumlahkeperluansemen", Semen);
                setData.putString("jumlahkeperluannat", Nat);
                setData.putString("jumlahdalamsak", Jumlahdalamsak);
                setData.putString("metode", Metode);
                setData.putString("hargakeramik", Hargakeramik);
                setData.putString("hargapasir", Hargapasir);
                setData.putString("hargasemen", Hargasemen);
                setData.putString("harganat", Harganat);
                setData.putString("nama_semen",Namasemen);
                setData.putString("nama_pasir",Namapasir);
                setData.putString("nama_keramik",Namakeramik);
                setData.putString("nama_semennat",Namasemennat);
                setData.putString("hargakeramiktotal", Hargakeramiktot);
                setData.putString("hargapasirtotal", Hargapasirtot);
                setData.putString("hargasementotal", Hargasementot);
                setData.putString("harganattotal", Harganattot);
                setData.putString("hargatotal", Totalbiaya);


                editlantai.putExtras(setData);
                context.startActivity(editlantai);

            }
        });
        viewHolder.detaillantai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog detaildialog = new Dialog(context);
                detaildialog.setContentView(R.layout.detail_lantai);
                TextView nama = detaildialog.findViewById(R.id.nama);
                TextView luaslantai = detaildialog.findViewById(R.id.luaslantai);
                TextView namkeramik = detaildialog.findViewById(R.id.namakeramik);
                TextView namsemen = detaildialog.findViewById(R.id.namasemen);
                TextView namnat = detaildialog.findViewById(R.id.namanat);
                TextView nampasir = detaildialog.findViewById(R.id.namapasir);
                TextView jmlpasir = detaildialog.findViewById(R.id.jumlahpasir);
                TextView jmlsemen = detaildialog.findViewById(R.id.jumlahsemen);
                TextView jmlkeramik = detaildialog.findViewById(R.id.jumlahkeramik);
                TextView jmlnat = detaildialog.findViewById(R.id.jumlahnat);
                TextView hnt = detaildialog.findViewById(R.id.harganat);
                TextView harganatta = detaildialog.findViewById(R.id.Harganattotal);

                TextView hker = detaildialog.findViewById(R.id.hargaker);
                TextView hsemn = detaildialog.findViewById(R.id.hargas);
                TextView hpsir = detaildialog.findViewById(R.id.hargap);

                TextView Semen1 = detaildialog.findViewById(R.id.semen1);
                TextView keramik1 = detaildialog.findViewById(R.id.keramiktotal);
                TextView Pasir1 = detaildialog.findViewById(R.id.pasir);
                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);

                String resulthargak = null;
                resulthargak = Hargakeramik.replace(".0","");
                String resulthargas = null;
                resulthargas = Hargasemen.replace(".0","");
                String resulthargap = null;
                resulthargap = Hargapasir.replace(".0","");
                String resulthargan = null;
                resulthargan = Harganat.replace(".0","");

                String resulthargaktotal = null;
                resulthargaktotal = Hargakeramiktot.replace(".0","");
                String resulthargantotal = null;
                resulthargantotal = Harganattot.replace(".0","");
                String resulthargastotal = null;
                resulthargastotal = Hargasementot.replace(".0","");
                String resulthargaptotal = null;
                resulthargaptotal = Hargapasirtot.replace(".0","");

                nama.setText(Name);
                luaslantai.setText("Panjang "+Panjangbid+"m "+",Lebar "+Tinggibid+"m "+",Luas lantai "+Luas+"m");
                namkeramik.setText(Namakeramik);
                nampasir.setText(Namapasir);
                namsemen.setText(Namasemen);
                namnat.setText(Namasemennat);
                hker.setText("Rp."+resulthargak);
                hnt.setText("Rp."+resulthargan);
                hpsir.setText("Rp."+resulthargap);
                hsemn.setText("Rp."+resulthargas);
                Semen1.setText("Rp."+resulthargastotal);
                keramik1.setText("Rp."+resulthargaktotal);
                jmlkeramik.setText(Keramik+" buah,"+" atau "+Keramikdus+" Dus");
                jmlpasir.setText(Pasir);
                jmlnat.setText(Nat);
                jmlsemen.setText(Semen);
                harganatta.setText(Harganattot);

                hker.setText("Rp."+resulthargantotal);
                Pasir1.setText("Rp."+resulthargaptotal);
                totalbiaya.setText("Rp."+Totalbiaya);


                detaildialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                detaildialog.show();
            }
        });
        viewHolder.deletelantai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog deletedialog = new Dialog(context);
                deletedialog.setContentView(R.layout.delete_dialog);
                TextView deletemessage = deletedialog.findViewById(R.id.textdelete);
                TextView silang = deletedialog.findViewById(R.id.silang);
                Button btndelete = deletedialog.findViewById(R.id.buttonhapus);
                Button btncancel = deletedialog.findViewById(R.id.buttoncancel);
                deletemessage.setText("Yakin ingin menghapus data " +Name);
                int ID = Integer.parseInt(id);
                String apiKey = "oa00000000app";
                SessionManager sm;
                sm= new SessionManager(context);
                HashMap<String,String> map = sm.getDetailLogin();
                String token=(map.get(sm.KEY_TOKEN));

                btndelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Call<Void> call = ApiClient.getRequestInterface().actionDeletePerhitunganlantai(ID,apiKey,token);
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
        return lantais.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nama,semen,keramik,edit,detailtotal;
        private ImageView image;
        private Button editlantai,deletelantai,detaillantai;
        Dialog deletedialog;

        public ViewHolder(View view) {
            super(view);
            Nama = (TextView)view.findViewById(R.id.Nama);
            semen = (TextView)view.findViewById(R.id.semen);
            keramik = (TextView)view.findViewById(R.id.keramik);
            editlantai = view.findViewById(R.id.editlantai);
            deletelantai = view.findViewById(R.id.Deletelantai);
            detaillantai = view.findViewById(R.id.detaillantai);
            detailtotal = view.findViewById(R.id.detailtotal);


        }
    }


}
