package com.example.bangunankita.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bangunankita.Model.Perhitunganplafon1;
import com.example.bangunankita.R;

import java.util.List;

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
//

//        viewHolder.editplafond.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent editplafond = new Intent(context, Editplafond.class)
//                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                Bundle setData = new Bundle();
//                setData.putString("nama", Name);
//                setData.putString("jenis_pengerjaan", "bangunan");
//                setData.putString("panjangbid", Panjangbid);
//                setData.putString("tinggibid", Tinggibid);
//                setData.putString("panjangpin", Panjangpin);
//                setData.putString("tinggipin", Tinggipin);
//                setData.putString("panjangjen",Panjangjen);
//                setData.putString("tinggijen", Tinggijen);
//                setData.putString("luas_plafond",Luas);
//                setData.putString("jumlahkeperluanbatako", Batako);
//                setData.putString("jumlahkeperluanpasir", Pasir);
//                setData.putString("Jumlahkeperluansemen", Semen);
//                setData.putString("jumlahdalamsak", Jumlahdalamsak);
//                setData.putString("metode", Metode);
//                setData.putString("hargabatako", Hargabatako);
//                setData.putString("hargapasir", Hargapasir);
//                setData.putString("hargasemen", Hargasemen);
//                editplafond.putExtras(setData);
//                context.startActivity(editplafond);
//
//            }
//        });
//        viewHolder.detailplafond.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog detaildialog = new Dialog(context);
//                detaildialog.setContentView(R.layout.detail_plafond);
//                TextView nama = detaildialog.findViewById(R.id.nama);
//                TextView luasplafond = detaildialog.findViewById(R.id.luasplafond);
//                TextView Batako1 = detaildialog.findViewById(R.id.Batako);
//                TextView Semen1 = detaildialog.findViewById(R.id.semen1);
//                TextView Pasir1 = detaildialog.findViewById(R.id.pasir);
//                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);
//                nama.setText(Name);
//                luasplafond.setText("Panjang "+Panjangbid+"m "+",Tinggi "+Tinggibid+"m "+"" +
//                        ",Panjang panel pintu "+Panjangpin+"m "+",Tinggi panel pintu "+Tinggipin+
//                        ",Panjang panel jendela "+Panjangjen+"m "+",Tinggi panel jendela "+Tinggijen+"m "+
//                        ",Luas Plafond "+Luas+"m ");
//                Batako1.setText("Jumlah "+Batako+"buah"+",Harga Rp."+Hargabatako);
//                Semen1.setText("Berat "+Semen+"kg"+",Harga Rp."+Hargasemen);
//                Pasir1.setText("Jumlah "+Pasir+"m3"+",Harga Rp."+Hargapasir);
//                totalbiaya.setText(",Harga Rp."+Totalbiaya);
//
//                detaildialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                detaildialog.show();
//            }
//        });
//        viewHolder.deleteplafond.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog deletedialog = new Dialog(context);
//                deletedialog.setContentView(R.layout.delete_dialog);
//                TextView deletemessage = deletedialog.findViewById(R.id.textdelete);
//                TextView silang = deletedialog.findViewById(R.id.silang);
//                Button btndelete = deletedialog.findViewById(R.id.buttonhapus);
//                Button btncancel = deletedialog.findViewById(R.id.buttoncancel);
//                deletemessage.setText("Yakin ingin menghapus data plafond " +Name);
//
//                btndelete.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//                btncancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//                deletedialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                deletedialog.show();
//            }
//        });
//
    }

    @Override
    public int getItemCount() {
        return plafonds.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nama,edit;
        private ImageView image;
        private Button editplafond,deleteplafond,detailplafond;
        Dialog deletedialog;

        public ViewHolder(View view) {
            super(view);
            Nama = (TextView)view.findViewById(R.id.Nama1);
//
//            editplafond = view.findViewById(R.id.editplafond);
//            deleteplafond = view.findViewById(R.id.Deleteplafond);
//            detailplafond = view.findViewById(R.id.detailplafond);




        }
    }



}
