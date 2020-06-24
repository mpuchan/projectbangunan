package com.example.bangunankita.adapter;

import androidx.recyclerview.widget.RecyclerView;

public class Pengecatan_adapter  {
    //    extends RecyclerView.Adapter<Pengecatan_adapter.ViewHolder>
//    private Context context;
//    private List<Perhitunganpengecatan1> pengecatans;
//
//    public Pengecatan_adapter(Context context, List<Perhitunganpengecatan1> pengecatans) {
//        this.context=context;
//        this.pengecatans=pengecatans;
//    }
//
//    @Override
//    public Pengecatan_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listpengecatan, viewGroup, false);
//        return new Pengecatan_adapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(Pengecatan_adapter.ViewHolder viewHolder, final int i) {
//        final Perhitunganpengecatan1 PengecatanModel = pengecatans.get(i);
//        final String Name = String.valueOf(pengecatans.get(i).getNama());
//        final String Panjangbid = String.valueOf(pengecatans.get(i).getPanjangbid());
//        final String Tinggibid = String.valueOf(pengecatans.get(i).getTinggibid());
//        final String Panjangjen = String.valueOf(pengecatans.get(i).getPanjangjen());
//        final String Tinggijen = String.valueOf(pengecatans.get(i).getTinggijen());
//        final String Panjangpin = String.valueOf(pengecatans.get(i).getPanjangpin());
//        final String Tinggipin = String.valueOf(pengecatans.get(i).getTinggipin());
//        final String Jumlahdalamsak = String.valueOf(pengecatans.get(i).getJumlahdalamsak());
//        final String Metode = String.valueOf(pengecatans.get(i).getMetode());
//        final String Hargabatako = String.valueOf(pengecatans.get(i).getHargabatako());
//        final String Hargapasir = String.valueOf(pengecatans.get(i).getHargapasir());
//        final String Hargasemen = String.valueOf(pengecatans.get(i).getHargasemen());
//        final String Totalbiaya = String.valueOf(pengecatans.get(i).getHargatotal());
//
//
//
//        final String Semen = String.valueOf(pengecatans.get(i).getJumlahkeperluansemen());
//        final String Pasir = String.valueOf(pengecatans.get(i).getJumlahkeperluanpasir());
//        final String Batako = String.valueOf(pengecatans.get(i).getJumlahkeperluanbatako());
//        final String Luas = String.valueOf(pengecatans.get(i).getLuasPengecatan());
//
//        viewHolder.Nama.setText(Name);
//        viewHolder.semen.setText(Semen);
//        viewHolder.pasir.setText(Pasir);
//        viewHolder.batako.setText(Batako);
//
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
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return pengecatans.size();
//    }
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        private TextView Nama,semen,pasir,batako,edit;
//        private ImageView image;
//        private Button editpengecatan,deletepengecatan,detailpengecatan;
//        Dialog deletedialog;
//
//        public ViewHolder(View view) {
//            super(view);
//            Nama = (TextView)view.findViewById(R.id.Nama);
//            semen = (TextView)view.findViewById(R.id.semen);
//            pasir = (TextView)view.findViewById(R.id.pasir);
//            batako = (TextView)view.findViewById(R.id.Batako);
//            editpengecatan = view.findViewById(R.id.editpengecatan);
//            deletepengecatan = view.findViewById(R.id.Deletepengecatan);
//            detailpengecatan = view.findViewById(R.id.detailpengecatan);
//
//
//
//
//        }
//    }



}
