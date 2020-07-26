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

import com.example.bangunankita.Model.Perhitunganpondasi1;
import com.example.bangunankita.R;

import java.util.List;

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



        viewHolder.Nama.setText(Nama);
//        viewHolder.semen.setText(Namasemen);
//        viewHolder.pasir.setText(Namapasir);
//        viewHolder.batako.setText(Namabatu);

//        viewHolder.editpondasi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent editpondasi = new Intent(context, Editpondasi.class)
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
//                setData.putString("luas_pondasi",Luas);
//                setData.putString("jumlahkeperluanbatako", Batako);
//                setData.putString("jumlahkeperluanpasir", Pasir);
//                setData.putString("Jumlahkeperluansemen", Semen);
//                setData.putString("jumlahdalamsak", Jumlahdalamsak);
//                setData.putString("metode", Metode);
//                setData.putString("hargabatako", Hargabatako);
//                setData.putString("hargapasir", Hargapasir);
//                setData.putString("hargasemen", Hargasemen);
//                editpondasi.putExtras(setData);
//                context.startActivity(editpondasi);
//
//            }
//        });
//        viewHolder.detailpondasi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog detaildialog = new Dialog(context);
//                detaildialog.setContentView(R.layout.detail_pondasi);
//                TextView nama = detaildialog.findViewById(R.id.nama);
//                TextView luaspondasi = detaildialog.findViewById(R.id.luaspondasi);
//                TextView Batako1 = detaildialog.findViewById(R.id.Batako);
//                TextView Semen1 = detaildialog.findViewById(R.id.semen1);
//                TextView Pasir1 = detaildialog.findViewById(R.id.pasir);
//                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);
//                nama.setText(Name);
//                luaspondasi.setText("Panjang "+Panjangbid+"m "+",Tinggi "+Tinggibid+"m "+"" +
//                        ",Panjang panel pintu "+Panjangpin+"m "+",Tinggi panel pintu "+Tinggipin+
//                        ",Panjang panel jendela "+Panjangjen+"m "+",Tinggi panel jendela "+Tinggijen+"m "+
//                        ",Luas Pondasi "+Luas+"m ");
//                Batako1.setText("Jumlah "+Batako+"buah"+",Harga Rp."+Hargabatako);
//                Semen1.setText("Berat "+Semen+"kg"+",Harga Rp."+Hargasemen);
//                Pasir1.setText("Jumlah "+Pasir+"m3"+",Harga Rp."+Hargapasir);
//                totalbiaya.setText(",Harga Rp."+Totalbiaya);
//
//                detaildialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                detaildialog.show();
//            }
//        });
//        viewHolder.deletepondasi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog deletedialog = new Dialog(context);
//                deletedialog.setContentView(R.layout.delete_dialog);
//                TextView deletemessage = deletedialog.findViewById(R.id.textdelete);
//                TextView silang = deletedialog.findViewById(R.id.silang);
//                Button btndelete = deletedialog.findViewById(R.id.buttonhapus);
//                Button btncancel = deletedialog.findViewById(R.id.buttoncancel);
//                deletemessage.setText("Yakin ingin menghapus data pondasi " +Name);
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
//
    @Override
    public int getItemCount() {
        return pondasis.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nama,semen,pasir,batako,edit;
        private ImageView image;
        private Button editpondasi,deletepondasi,detailpondasi;
        Dialog deletedialog;

        public ViewHolder(View view) {
            super(view);
            Nama = (TextView)view.findViewById(R.id.Nama1);
//            semen = (TextView)view.findViewById(R.id.semen);
//            pasir = (TextView)view.findViewById(R.id.pasir);
//            batako = (TextView)view.findViewById(R.id.Batako);
//            editpondasi = view.findViewById(R.id.editpondasi);
//            deletepondasi = view.findViewById(R.id.Deletepondasi);
//            detailpondasi = view.findViewById(R.id.detailpondasi);




        }
    }




}
