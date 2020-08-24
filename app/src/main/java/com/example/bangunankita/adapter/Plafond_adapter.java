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

import com.example.bangunankita.Editplafon;
import com.example.bangunankita.Model.Perhitunganplafon1;
import com.example.bangunankita.R;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Plafond_adapter extends RecyclerView.Adapter<Plafond_adapter.ViewHolder> {

    private Context context;
    private List<Perhitunganplafon1> plafonds;

    public Plafond_adapter(Context context, List<Perhitunganplafon1> plafonds) {
        this.context=context;
        this.plafonds=plafonds;
    }

    @Override
    public Plafond_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listplafon, viewGroup, false);
        return new Plafond_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Plafond_adapter.ViewHolder viewHolder, final int i) {
        final Perhitunganplafon1 PlafondModel = plafonds.get(i);
        final String id = String.valueOf(plafonds.get(i).getId());
        final String ProyekID = String.valueOf(plafonds.get(i).getProyekId());
        final String Name = String.valueOf(plafonds.get(i).getNama());
        final String Panjang = String.valueOf(plafonds.get(i).getPanjang());
        final String Lebar = String.valueOf(plafonds.get(i).getLebar());
        final String Luas = String.valueOf(plafonds.get(i).getLuas());
        final String Namatriplek = String.valueOf(plafonds.get(i).getNamatriplek());
        final String Namapaku = String.valueOf(plafonds.get(i).getNamapaku());
        final String Namareng = String.valueOf(plafonds.get(i).getNamareng());
        final String HargaPaku = String.valueOf(plafonds.get(i).getHargapaku());
        final String HargaTriplek = String.valueOf(plafonds.get(i).getHargatriplek());
        final String HargaReng = String.valueOf(plafonds.get(i).getHargareng());
        final String Jumlahreng = String.valueOf(plafonds.get(i).getJumlahreng());
        final String Jumlahrengbatang = String.valueOf(plafonds.get(i).getJumlahrengbatang());
        final String Jumlahtriplek = String.valueOf(plafonds.get(i).getJumlahtriplek());
        final String Jumlahtripleklembar = String.valueOf(plafonds.get(i).getJumlahtripleklembar());
        final String Jumlahpaku= String.valueOf(plafonds.get(i).getJumlahpaku());
        final String Hargatotpaku = String.valueOf(plafonds.get(i).getHargatotalpaku());
        final String Hargatotreng = String.valueOf(plafonds.get(i).getHargatotalreng());
        final String Hargatottriplek = String.valueOf(plafonds.get(i).getHargatotaltriplek());
        final String Totalbiaya = String.valueOf(plafonds.get(i).getHargatotal());

        viewHolder.Nama.setText(Name);
        viewHolder.reng.setText(Jumlahrengbatang+"batang");
        viewHolder.plafon.setText(Jumlahtripleklembar+"lembar");
        float total1 = Float.parseFloat(Totalbiaya);
        DecimalFormat df = new DecimalFormat("#");
        String tothitungan = df.format(total1);
        int numbertotal = Integer.parseInt(tothitungan);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totals = formatter.format(numbertotal);
        viewHolder.detailtotal.setText("Rp."+ totals);
//

        viewHolder.editplafond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editplafond = new Intent(context, Editplafon.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle setData = new Bundle();
                setData.putString("ProyekId", ProyekID);
                setData.putString("Id", id);
                setData.putString("nama", Name);
                setData.putString("panjang", Panjang);
                setData.putString("lebar", Lebar);
                setData.putString("luas", Luas);
                setData.putString("namatriplek", Namatriplek);
                setData.putString("namapaku", Namapaku);
                setData.putString("namareng", Namareng);
                setData.putString("hargatriplek", HargaTriplek);
                setData.putString("hargapaku", HargaPaku);
                setData.putString("hargareng", HargaReng);
                setData.putString("jumlahpaku", Jumlahpaku);
                setData.putString("jumlahreng", Jumlahreng);
                setData.putString("hargatriplektotal", Hargatottriplek);
                setData.putString("hargapakutotal", Hargatotpaku);
                setData.putString("hargarengtotal", Hargatotreng);
                setData.putString("jumlahtriplek", Jumlahtriplek);
                setData.putString("jumlahtripleklembar", Jumlahtripleklembar);
                setData.putString("jumlahrengbatang", Jumlahrengbatang);
                setData.putString("hargatotal", Totalbiaya);

                editplafond.putExtras(setData);
                context.startActivity(editplafond);

            }
        });
        viewHolder.detailplafond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog detaildialog = new Dialog(context);
                detaildialog.setContentView(R.layout.detail_plafon);
                TextView nama = detaildialog.findViewById(R.id.nama);
                TextView luasplafond = detaildialog.findViewById(R.id.luasplafon);
                TextView namaplafon = detaildialog.findViewById(R.id.namaplafon);
                TextView namapaku = detaildialog.findViewById(R.id.namamurbaut);
                TextView namareng = detaildialog.findViewById(R.id.namarenghollow);
                TextView hargaplafon = detaildialog.findViewById(R.id.hargaplafon);
                TextView hargapakumur = detaildialog.findViewById(R.id.hargamurpaku);
                TextView hargareng = detaildialog.findViewById(R.id.hargareng);
                TextView jumlahplafon = detaildialog.findViewById(R.id.jumlahplafon);
                TextView jumlahpakumur = detaildialog.findViewById(R.id.jumlahmurbaut);
                TextView jumlahreng = detaildialog.findViewById(R.id.jumlahrenghollow);
                TextView hargatotalplafon = detaildialog.findViewById(R.id.hargatotalplafon);
                TextView hargatotalmur = detaildialog.findViewById(R.id.hargatotalmurbaut);
                TextView hargatotalreng = detaildialog.findViewById(R.id.hargatotalreng);
                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);
                nama.setText(Name);
                luasplafond.setText("Panjang "+Panjang+"m "+",Lebar "+Lebar+"m "+
                        ",Luas Plafon "+Luas+"m2 ");
                namaplafon.setText(Namatriplek);
                namapaku.setText(Namapaku);
                namareng.setText(Namareng);
                hargaplafon.setText("Rp"+HargaTriplek);
                hargareng.setText("Rp"+HargaReng);
                hargapakumur.setText("Rp"+HargaPaku);
                jumlahplafon.setText(Jumlahtripleklembar +"lembar");
                jumlahpakumur.setText(Jumlahpaku+"kg");
                jumlahreng.setText(Jumlahrengbatang+"batang");
                hargatotalplafon.setText("Rp."+Hargatottriplek);
                hargatotalmur.setText("Rp."+Hargatotpaku);
                hargatotalreng.setText("Rp."+Hargatotreng);

                totalbiaya.setText("Rp."+Totalbiaya);

                detaildialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                detaildialog.show();
            }
        });
        viewHolder.deleteplafond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog deletedialog = new Dialog(context);
                deletedialog.setContentView(R.layout.delete_dialog);
                TextView deletemessage = deletedialog.findViewById(R.id.textdelete);
                TextView silang = deletedialog.findViewById(R.id.silang);
                Button btndelete = deletedialog.findViewById(R.id.buttonhapus);
                Button btncancel = deletedialog.findViewById(R.id.buttoncancel);
                deletemessage.setText("Yakin ingin menghapus data plafond " +Name);
                int ID = Integer.parseInt(id);
                String apiKey = "oa00000000app";
                SessionManager sm;
                sm= new SessionManager(context);
                HashMap<String,String> map = sm.getDetailLogin();
                String token=(map.get(sm.KEY_TOKEN));
                btndelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Call<Void> call = ApiClient.getRequestInterface().actionDeletePerhitunganplafon(ID,apiKey,token);
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
        return plafonds.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nama,edit,detailtotal,plafon,reng;
        private ImageView image;
        private Button editplafond,deleteplafond,detailplafond;
        Dialog deletedialog;

        public ViewHolder(View view) {
            super(view);
            Nama = (TextView)view.findViewById(R.id.Nama1);
            plafon = (TextView)view.findViewById(R.id.plafon);
            reng = (TextView)view.findViewById(R.id.reng);
            detailtotal = view.findViewById(R.id.detailtotal);
//
            editplafond = view.findViewById(R.id.editplafon);
            deleteplafond = view.findViewById(R.id.Deleteplafon);
            detailplafond = view.findViewById(R.id.detailplafon);




        }
    }



}
