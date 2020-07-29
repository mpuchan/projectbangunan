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

import com.example.bangunankita.Editurugan;
import com.example.bangunankita.Model.Perhitunganacian1;
import com.example.bangunankita.Model.Perhitunganurugan1;
import com.example.bangunankita.Perhitunganurugan;
import com.example.bangunankita.R;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        final String Id = String.valueOf(urugans.get(i).getId());
        final String ProyekId = String.valueOf(urugans.get(i).getProyekId());
        final String Name = String.valueOf(urugans.get(i).getNama());
        final String jenis = String.valueOf(urugans.get(i).getJenisPengerjaan());
        final String Panjangdin = String.valueOf(urugans.get(i).getPanjang());
        final String Tinggidin = String.valueOf(urugans.get(i).getTinggi());
        final String Lebar = String.valueOf(urugans.get(i).getLebar());
        final String volume = String.valueOf(urugans.get(i).getVolume());
        final String volumejadi = String.valueOf(urugans.get(i).getVolumejadi());
        final String Jumlahdalamtruk= String.valueOf(urugans.get(i).getJumlahdalamtruk());
        final String Hargapasir = String.valueOf(urugans.get(i).getHargapasir());
        final String Hargapasirtotal = String.valueOf(urugans.get(i).getHargapasirtotal());
        final String Namapasir = String.valueOf(urugans.get(i).getNamaPasir());
        final String Totalbiaya = String.valueOf(urugans.get(i).getHargatotal());
        final String Pasir = String.valueOf(urugans.get(i).getJumlahkeperluanpasir());

        float total1 = Float.parseFloat(Totalbiaya);
        DecimalFormat df = new DecimalFormat("#");
        String tothitungan = df.format(total1);
        int numbertotal = Integer.parseInt(tothitungan);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totals = formatter.format(numbertotal);

        viewHolder.Nama.setText(Name);
        viewHolder.pasir.setText(Pasir +"m3");
        viewHolder.detailtotal.setText("Rp."+totals);


        viewHolder.editurugan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editurugan = new Intent(context, Editurugan.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle setData = new Bundle();
                setData.putString("Id", Id);
                setData.putString("ProyekId", ProyekId);
                setData.putString("nama", Name);
                setData.putString("jenis_pengerjaan", jenis);
                setData.putString("panjang", Panjangdin);
                setData.putString("tinggi", Tinggidin);
                setData.putString("lebar",Lebar);
                setData.putString("volume",volume);
                setData.putString("volumejadi",volumejadi);
                setData.putString("jumlahkeperluanpasir", Pasir);
                setData.putString("jumlahdalamtruk", Jumlahdalamtruk);
                setData.putString("nama_pasir", Namapasir);
                setData.putString("hargapasir", Hargapasir);
                setData.putString("hargapasirtotal", Hargapasirtotal);
                setData.putString("hargatotal", Totalbiaya);
                editurugan.putExtras(setData);
                context.startActivity(editurugan);
            }
        });
        viewHolder.detailurugan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog detaildialog = new Dialog(context);
                detaildialog.setContentView(R.layout.detail_urugan);
                TextView nama = detaildialog.findViewById(R.id.nama);
                TextView volumeurugan = detaildialog.findViewById(R.id.volume);
                TextView namapasir = detaildialog.findViewById(R.id.namapasir);
                TextView hargatotalpasir = detaildialog.findViewById(R.id.pasir);
                TextView Pasir1 = detaildialog.findViewById(R.id.jumlahpasir);
                TextView sathargap = detaildialog.findViewById(R.id.hargap);
                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);

                String resulthargap = null;
                resulthargap = Hargapasir.replace(".0","");
                String resulthargaptotal = null;
                resulthargaptotal = Hargapasirtotal.replace(".0","");

                nama.setText(Name);
                volumeurugan.setText("Panjang "+Panjangdin+" Lebar " +Lebar +"Tinggi" +Tinggidin+" Volume "+volume+"Volume termasuk pemadatan "+volumejadi);
                Pasir1.setText("Jumlah "+Pasir+"m3"+",Harga Rp."+Hargapasir);
                totalbiaya.setText(",Harga Rp."+Totalbiaya);

                hargatotalpasir.setText("Rp."+resulthargaptotal);
                namapasir.setText(Namapasir);
                Pasir1.setText(Pasir+"m3");
                sathargap.setText("Rp."+resulthargap);
                totalbiaya.setText("Rp."+Totalbiaya);

                detaildialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                detaildialog.show();
            }
        });
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
                int ID = Integer.parseInt(Id);
                String apiKey = "oa00000000app";
                SessionManager sm;
                sm= new SessionManager(context);
                HashMap<String,String> map = sm.getDetailLogin();
                String token=(map.get(sm.KEY_TOKEN));
                btndelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Call<Void> call = ApiClient.getRequestInterface().actionDeletePerhitunganurugan(ID,apiKey,token);
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
        return urugans.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nama,semen,pasir,batako,edit,detailtotal;
        private ImageView image;
        private Button editurugan,deleteurugan,detailurugan;
        Dialog deletedialog;

        public ViewHolder(View view) {
            super(view);
            Nama = (TextView)view.findViewById(R.id.Nama);
            pasir = (TextView)view.findViewById(R.id.pasir);
            editurugan = view.findViewById(R.id.editbidang);
            deleteurugan = view.findViewById(R.id.Deletebidang);
            detailurugan = view.findViewById(R.id.detailbidang);
            detailtotal = view.findViewById(R.id.detailtotal);



        }
    }




}
