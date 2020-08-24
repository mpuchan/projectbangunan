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

import com.example.bangunankita.Editpengecatan;
import com.example.bangunankita.Model.Perhitunganpengecatan1;
import com.example.bangunankita.R;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pengecatan_adapter extends RecyclerView.Adapter<Pengecatan_adapter.ViewHolder>  {

    private Context context;
    private List<Perhitunganpengecatan1> pengecatans;

    public Pengecatan_adapter(Context context, List<Perhitunganpengecatan1> pengecatans) {
        this.context=context;
        this.pengecatans=pengecatans;
    }

    @Override
    public Pengecatan_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listpengecatan, viewGroup, false);
        return new Pengecatan_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Pengecatan_adapter.ViewHolder viewHolder, final int i) {
        final Perhitunganpengecatan1 PengecatanModel = pengecatans.get(i);
        final String id = String.valueOf(pengecatans.get(i).getId());
        final String Proyekid = String.valueOf(pengecatans.get(i).getProyekId());
        final String Name = String.valueOf(pengecatans.get(i).getNama());
        final String Jenispengerjaan = String.valueOf(pengecatans.get(i).getJenisPengerjaan());
        final String Panjangbid = String.valueOf(pengecatans.get(i).getPanjangdin());
        final String Tinggibid = String.valueOf(pengecatans.get(i).getTinggidin());
        final String Sisi = String.valueOf(pengecatans.get(i).getSisi());
        final String Namacat = String.valueOf(pengecatans.get(i).getNamaCat());
        final String Namaplamur = String.valueOf(pengecatans.get(i).getNamaPlamur());
        final String Jumlahcat = String.valueOf(pengecatans.get(i).getJumlahkeperluancat());
        final String Jumlahcatkaleng = String.valueOf(pengecatans.get(i).getJumlahkeperluancatkaleng());
        final String Jumlahplamur = String.valueOf(pengecatans.get(i).getJumlahkeperluanplamur());
        final String Jumlahplamursak = String.valueOf(pengecatans.get(i).getJumlahkeperluanplamursak());
        final String Hargacat = String.valueOf(pengecatans.get(i).getHargacat());
        final String Hargaplamur = String.valueOf(pengecatans.get(i).getHargaplamur());
        final String Hargatotalcat = String.valueOf(pengecatans.get(i).getHargacattotal());
        final String Hargaplamurtotal = String.valueOf(pengecatans.get(i).getHargaplamurtotal());
        final String Totalbiaya = String.valueOf(pengecatans.get(i).getHargatotal());

        final String Luas = String.valueOf(pengecatans.get(i).getLuasPengecatan());

        float total1 = Float.parseFloat(Totalbiaya);
        DecimalFormat df = new DecimalFormat("#");
        String tothitungan = df.format(total1);
        int numbertotal = Integer.parseInt(tothitungan);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totals = formatter.format(numbertotal);
        viewHolder.Nama.setText(Name);
        viewHolder.detailtotal.setText("Rp."+ totals);

        viewHolder.plamur.setText(Jumlahplamur +"kg");
        viewHolder.cat.setText(Jumlahcat +"kg");


        viewHolder.editpengecatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editpengecatan = new Intent(context, Editpengecatan.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle setData = new Bundle();
                setData.putString("id", id);
                setData.putString("ProyekId", Proyekid);
                setData.putString("nama", Name);
                setData.putString("jenis_pengerjaan", Jenispengerjaan);
                setData.putString("panjangdin", Panjangbid);
                setData.putString("tinggidin", Tinggibid);
                setData.putString("sisi",Sisi);
                setData.putString("luaspengecatan", Luas);
                setData.putString("namacat", Namacat);
                setData.putString("namaplamur", Namaplamur);
                setData.putString("jumlahkeperluancat", Jumlahcat);
                setData.putString("jumlahkeperluanplamur", Jumlahplamur);
                setData.putString("jumlahkeperluanplamursak", Jumlahplamursak);
                setData.putString("jumlahkeperluancatkaleng", Jumlahcatkaleng);
                setData.putString("hargacat", Hargacat);
                setData.putString("hargaplamur", Hargaplamur);
                setData.putString("hargacattotal", Hargatotalcat);
                setData.putString("hargaplamurtotal", Hargaplamurtotal);
                setData.putString("hargatotal", Totalbiaya);
                editpengecatan.putExtras(setData);
                context.startActivity(editpengecatan);

            }
        });
        viewHolder.detailpengecatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog detaildialog = new Dialog(context);
                detaildialog.setContentView(R.layout.detail_pengecatan);
                TextView nama = detaildialog.findViewById(R.id.nama);
                TextView luaspengecatan = detaildialog.findViewById(R.id.volume);

                TextView namaplamur = detaildialog.findViewById(R.id.namaplamur);
                TextView namacat = detaildialog.findViewById(R.id.namacat);
                TextView jumlahplamur = detaildialog.findViewById(R.id.jumlahplamur);
                TextView jumlahcat = detaildialog.findViewById(R.id.jumlahcat);
                TextView hargacat = detaildialog.findViewById(R.id.hargacat);
                TextView hargaplamur = detaildialog.findViewById(R.id.hargaplamur);
                TextView hargatotalplamur = detaildialog.findViewById(R.id.plamur);
                TextView hargatotalcat = detaildialog.findViewById(R.id.cat);
                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);
                nama.setText(Name);
                luaspengecatan.setText("Luas area cat : " + Luas+"m2");
                namacat.setText(Namacat);
                namaplamur.setText(Namaplamur);
                jumlahcat.setText(Jumlahcatkaleng + "kaleng");
                jumlahplamur.setText(Jumlahplamursak +"kg");
                hargacat.setText("Rp."+Hargacat);
                hargatotalcat.setText("Rp."+Hargatotalcat);
                hargaplamur.setText("Rp."+Hargaplamur);
                hargatotalplamur.setText("Rp."+Hargaplamurtotal);

                totalbiaya.setText("Rp."+Totalbiaya);

                detaildialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                detaildialog.show();
            }
        });
        viewHolder.deletepengecatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog deletedialog = new Dialog(context);
                deletedialog.setContentView(R.layout.delete_dialog);
                TextView deletemessage = deletedialog.findViewById(R.id.textdelete);
                TextView silang = deletedialog.findViewById(R.id.silang);
                Button btndelete = deletedialog.findViewById(R.id.buttonhapus);
                Button btncancel = deletedialog.findViewById(R.id.buttoncancel);
                deletemessage.setText("Yakin ingin menghapus data pengecatan " +Name);
                int ID = Integer.parseInt(id);
                String apiKey = "oa00000000app";
                SessionManager sm;
                sm= new SessionManager(context);
                HashMap<String,String> map = sm.getDetailLogin();
                String token=(map.get(sm.KEY_TOKEN));
                btndelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Call<Void> call = ApiClient.getRequestInterface().actionDeletePerhitunganpengecatan(ID,apiKey,token);
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
        return pengecatans.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nama,semen,pasir,batako,edit,detailtotal,plamur,cat;
        private ImageView image;
        private Button editpengecatan,deletepengecatan,detailpengecatan;
        Dialog deletedialog;

        public ViewHolder(View view) {
            super(view);
            Nama = (TextView)view.findViewById(R.id.Nama);
            plamur = (TextView)view.findViewById(R.id.plamur);
            cat = (TextView)view.findViewById(R.id.cat);
            detailtotal = view.findViewById(R.id.detailtotal);
            batako = (TextView)view.findViewById(R.id.Batako);
            editpengecatan = view.findViewById(R.id.editpengecatan);
            deletepengecatan = view.findViewById(R.id.Deletepengecatan);
            detailpengecatan = view.findViewById(R.id.detailpengecatan);




        }
    }



}
