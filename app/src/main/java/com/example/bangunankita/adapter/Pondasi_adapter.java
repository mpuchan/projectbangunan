package com.example.bangunankita.adapter;

import androidx.recyclerview.widget.RecyclerView;

public class Pondasi_adapter  {
    //    extends RecyclerView.Adapter<Pondasi_adapter.ViewHolder>
//    private Context context;
//    private List<Perhitunganpondasi1> pondasis;
//
//    public Pondasi_adapter(Context context, List<Perhitunganpondasi1> pondasis) {
//        this.context=context;
//        this.pondasis=pondasis;
//    }
//
//    @Override
//    public Pondasi_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listpondasi, viewGroup, false);
//        return new Pondasi_adapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(Pondasi_adapter.ViewHolder viewHolder, final int i) {
//        final Perhitunganpondasi1 PondasiModel = pondasis.get(i);
//        final String Name = String.valueOf(pondasis.get(i).getNama());
//        final String Panjangbid = String.valueOf(pondasis.get(i).getPanjangbid());
//        final String Tinggibid = String.valueOf(pondasis.get(i).getTinggibid());
//        final String Panjangjen = String.valueOf(pondasis.get(i).getPanjangjen());
//        final String Tinggijen = String.valueOf(pondasis.get(i).getTinggijen());
//        final String Panjangpin = String.valueOf(pondasis.get(i).getPanjangpin());
//        final String Tinggipin = String.valueOf(pondasis.get(i).getTinggipin());
//        final String Jumlahdalamsak = String.valueOf(pondasis.get(i).getJumlahdalamsak());
//        final String Metode = String.valueOf(pondasis.get(i).getMetode());
//        final String Hargabatako = String.valueOf(pondasis.get(i).getHargabatako());
//        final String Hargapasir = String.valueOf(pondasis.get(i).getHargapasir());
//        final String Hargasemen = String.valueOf(pondasis.get(i).getHargasemen());
//        final String Totalbiaya = String.valueOf(pondasis.get(i).getHargatotal());
//
//
//
//        final String Semen = String.valueOf(pondasis.get(i).getJumlahkeperluansemen());
//        final String Pasir = String.valueOf(pondasis.get(i).getJumlahkeperluanpasir());
//        final String Batako = String.valueOf(pondasis.get(i).getJumlahkeperluanbatako());
//        final String Luas = String.valueOf(pondasis.get(i).getLuasPondasi());
//
//        viewHolder.Nama.setText(Name);
//        viewHolder.semen.setText(Semen);
//        viewHolder.pasir.setText(Pasir);
//        viewHolder.batako.setText(Batako);
//
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
//    }
//
//    @Override
//    public int getItemCount() {
//        return pondasis.size();
//    }
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        private TextView Nama,semen,pasir,batako,edit;
//        private ImageView image;
//        private Button editpondasi,deletepondasi,detailpondasi;
//        Dialog deletedialog;
//
//        public ViewHolder(View view) {
//            super(view);
//            Nama = (TextView)view.findViewById(R.id.Nama);
//            semen = (TextView)view.findViewById(R.id.semen);
//            pasir = (TextView)view.findViewById(R.id.pasir);
//            batako = (TextView)view.findViewById(R.id.Batako);
//            editpondasi = view.findViewById(R.id.editpondasi);
//            deletepondasi = view.findViewById(R.id.Deletepondasi);
//            detailpondasi = view.findViewById(R.id.detailpondasi);
//
//
//
//
//        }
//    }




}
