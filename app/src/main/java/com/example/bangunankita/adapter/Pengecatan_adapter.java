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

import com.example.bangunankita.Model.Perhitunganpengecatan1;
import com.example.bangunankita.R;

import java.util.List;

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
        final String Panjangbid = String.valueOf(pengecatans.get(i).getPanjangdin());
        final String Tinggibid = String.valueOf(pengecatans.get(i).getTinggidin());
        final String Jumlahcat = String.valueOf(pengecatans.get(i).getJumlahkeperluancat());
        final String Jumlahcatkaleng = String.valueOf(pengecatans.get(i).getJumlahkeperluancatkaleng());
        final String Jumlahplamur = String.valueOf(pengecatans.get(i).getJumlahkeperluanplamur());
        final String Jumlahplamursak = String.valueOf(pengecatans.get(i).getJumlahkeperluanplamursak());
        final String Hargacat = String.valueOf(pengecatans.get(i).getHargacat());
        final String Hargaplamur = String.valueOf(pengecatans.get(i).getHargaplamur());
        final String Hargatotalcat = String.valueOf(pengecatans.get(i).getHargacattotal());
        final String Hargaplamurcat = String.valueOf(pengecatans.get(i).getHargaplamurtotal());
        final String Totalbiaya = String.valueOf(pengecatans.get(i).getHargatotal());

        final String Luas = String.valueOf(pengecatans.get(i).getLuasPengecatan());

        viewHolder.Nama.setText(Name);
//        viewHolder.semen.setText(Semen);
//        viewHolder.pasir.setText(Pasir);
//        viewHolder.batako.setText(Batako);

//        viewHolder.editpengecatan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent editpengecatan = new Intent(context, Editpengecatan.class)
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
//                setData.putString("luas_pengecatan",Luas);
//                setData.putString("jumlahkeperluanbatako", Batako);
//                setData.putString("jumlahkeperluanpasir", Pasir);
//                setData.putString("Jumlahkeperluansemen", Semen);
//                setData.putString("jumlahdalamsak", Jumlahdalamsak);
//                setData.putString("metode", Metode);
//                setData.putString("hargabatako", Hargabatako);
//                setData.putString("hargapasir", Hargapasir);
//                setData.putString("hargasemen", Hargasemen);
//                editpengecatan.putExtras(setData);
//                context.startActivity(editpengecatan);
//
//            }
//        });
//        viewHolder.detailpengecatan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog detaildialog = new Dialog(context);
//                detaildialog.setContentView(R.layout.detail_pengecatan);
//                TextView nama = detaildialog.findViewById(R.id.nama);
//                TextView luaspengecatan = detaildialog.findViewById(R.id.luaspengecatan);
//                TextView Batako1 = detaildialog.findViewById(R.id.Batako);
//                TextView Semen1 = detaildialog.findViewById(R.id.semen1);
//                TextView Pasir1 = detaildialog.findViewById(R.id.pasir);
//                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);
//                nama.setText(Name);
//                luaspengecatan.setText("Panjang "+Panjangbid+"m "+",Tinggi "+Tinggibid+"m "+"" +
//                        ",Panjang panel pintu "+Panjangpin+"m "+",Tinggi panel pintu "+Tinggipin+
//                        ",Panjang panel jendela "+Panjangjen+"m "+",Tinggi panel jendela "+Tinggijen+"m "+
//                        ",Luas Pengecatan "+Luas+"m ");
//                Batako1.setText("Jumlah "+Batako+"buah"+",Harga Rp."+Hargabatako);
//                Semen1.setText("Berat "+Semen+"kg"+",Harga Rp."+Hargasemen);
//                Pasir1.setText("Jumlah "+Pasir+"m3"+",Harga Rp."+Hargapasir);
//                totalbiaya.setText(",Harga Rp."+Totalbiaya);
//
//                detaildialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                detaildialog.show();
//            }
//        });
//        viewHolder.deletepengecatan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog deletedialog = new Dialog(context);
//                deletedialog.setContentView(R.layout.delete_dialog);
//                TextView deletemessage = deletedialog.findViewById(R.id.textdelete);
//                TextView silang = deletedialog.findViewById(R.id.silang);
//                Button btndelete = deletedialog.findViewById(R.id.buttonhapus);
//                Button btncancel = deletedialog.findViewById(R.id.buttoncancel);
//                deletemessage.setText("Yakin ingin menghapus data pengecatan " +Name);
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

    }

    @Override
    public int getItemCount() {
        return pengecatans.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Nama,semen,pasir,batako,edit;
        private ImageView image;
        private Button editpengecatan,deletepengecatan,detailpengecatan;
        Dialog deletedialog;

        public ViewHolder(View view) {
            super(view);
            Nama = (TextView)view.findViewById(R.id.Nama);
            semen = (TextView)view.findViewById(R.id.semen);
            pasir = (TextView)view.findViewById(R.id.pasir);
            batako = (TextView)view.findViewById(R.id.Batako);
            editpengecatan = view.findViewById(R.id.editpengecatan);
            deletepengecatan = view.findViewById(R.id.Deletepengecatan);
            detailpengecatan = view.findViewById(R.id.detailpengecatan);




        }
    }



}
