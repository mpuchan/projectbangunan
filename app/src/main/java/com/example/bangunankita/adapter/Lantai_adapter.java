package com.example.bangunankita.adapter;

import androidx.recyclerview.widget.RecyclerView;

public class Lantai_adapter  {
    //    extends RecyclerView.Adapter<Lantai_adapter.ViewHolder>
//    private Context context;
//    private List<Perhitunganlantai1> lantais;
//
//    public Lantai_adapter(Context context, List<Perhitunganlantai1> lantais) {
//        this.context=context;
//        this.lantais=lantais;
//    }
//
//    @Override
//    public Lantai_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listlantai, viewGroup, false);
//        return new Lantai_adapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(Lantai_adapter.ViewHolder viewHolder, final int i) {
//        final Perhitunganlantai1 LantaiModel = lantais.get(i);
//        final String Name = String.valueOf(lantais.get(i).getNama());
//        final String Panjangbid = String.valueOf(lantais.get(i).getPanjangbid());
//        final String Tinggibid = String.valueOf(lantais.get(i).getTinggibid());
//        final String Panjangjen = String.valueOf(lantais.get(i).getPanjangjen());
//        final String Tinggijen = String.valueOf(lantais.get(i).getTinggijen());
//        final String Panjangpin = String.valueOf(lantais.get(i).getPanjangpin());
//        final String Tinggipin = String.valueOf(lantais.get(i).getTinggipin());
//        final String Jumlahdalamsak = String.valueOf(lantais.get(i).getJumlahdalamsak());
//        final String Metode = String.valueOf(lantais.get(i).getMetode());
//        final String Hargabatako = String.valueOf(lantais.get(i).getHargabatako());
//        final String Hargapasir = String.valueOf(lantais.get(i).getHargapasir());
//        final String Hargasemen = String.valueOf(lantais.get(i).getHargasemen());
//        final String Totalbiaya = String.valueOf(lantais.get(i).getHargatotal());
//
//
//
//        final String Semen = String.valueOf(lantais.get(i).getJumlahkeperluansemen());
//        final String Pasir = String.valueOf(lantais.get(i).getJumlahkeperluanpasir());
//        final String Batako = String.valueOf(lantais.get(i).getJumlahkeperluanbatako());
//        final String Luas = String.valueOf(lantais.get(i).getLuasLantai());
//
//        viewHolder.Nama.setText(Name);
//        viewHolder.semen.setText(Semen);
//        viewHolder.pasir.setText(Pasir);
//        viewHolder.batako.setText(Batako);
//
//        viewHolder.editlantai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent editlantai = new Intent(context, Editlantai.class)
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
//                setData.putString("luas_lantai",Luas);
//                setData.putString("jumlahkeperluanbatako", Batako);
//                setData.putString("jumlahkeperluanpasir", Pasir);
//                setData.putString("Jumlahkeperluansemen", Semen);
//                setData.putString("jumlahdalamsak", Jumlahdalamsak);
//                setData.putString("metode", Metode);
//                setData.putString("hargabatako", Hargabatako);
//                setData.putString("hargapasir", Hargapasir);
//                setData.putString("hargasemen", Hargasemen);
//                editlantai.putExtras(setData);
//                context.startActivity(editlantai);
//
//            }
//        });
//        viewHolder.detaillantai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog detaildialog = new Dialog(context);
//                detaildialog.setContentView(R.layout.detail_lantai);
//                TextView nama = detaildialog.findViewById(R.id.nama);
//                TextView luaslantai = detaildialog.findViewById(R.id.luaslantai);
//                TextView Batako1 = detaildialog.findViewById(R.id.Batako);
//                TextView Semen1 = detaildialog.findViewById(R.id.semen1);
//                TextView Pasir1 = detaildialog.findViewById(R.id.pasir);
//                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);
//                nama.setText(Name);
//                luaslantai.setText("Panjang "+Panjangbid+"m "+",Tinggi "+Tinggibid+"m "+"" +
//                        ",Panjang panel pintu "+Panjangpin+"m "+",Tinggi panel pintu "+Tinggipin+
//                        ",Panjang panel jendela "+Panjangjen+"m "+",Tinggi panel jendela "+Tinggijen+"m "+
//                        ",Luas Lantai "+Luas+"m ");
//                Batako1.setText("Jumlah "+Batako+"buah"+",Harga Rp."+Hargabatako);
//                Semen1.setText("Berat "+Semen+"kg"+",Harga Rp."+Hargasemen);
//                Pasir1.setText("Jumlah "+Pasir+"m3"+",Harga Rp."+Hargapasir);
//                totalbiaya.setText(",Harga Rp."+Totalbiaya);
//
//                detaildialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                detaildialog.show();
//            }
//        });
//        viewHolder.deletelantai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog deletedialog = new Dialog(context);
//                deletedialog.setContentView(R.layout.delete_dialog);
//                TextView deletemessage = deletedialog.findViewById(R.id.textdelete);
//                TextView silang = deletedialog.findViewById(R.id.silang);
//                Button btndelete = deletedialog.findViewById(R.id.buttonhapus);
//                Button btncancel = deletedialog.findViewById(R.id.buttoncancel);
//                deletemessage.setText("Yakin ingin menghapus data lantai " +Name);
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
//        return lantais.size();
//    }
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        private TextView Nama,semen,pasir,batako,edit;
//        private ImageView image;
//        private Button editlantai,deletelantai,detaillantai;
//        Dialog deletedialog;
//
//        public ViewHolder(View view) {
//            super(view);
//            Nama = (TextView)view.findViewById(R.id.Nama);
//            semen = (TextView)view.findViewById(R.id.semen);
//            pasir = (TextView)view.findViewById(R.id.pasir);
//            batako = (TextView)view.findViewById(R.id.Batako);
//            editlantai = view.findViewById(R.id.editlantai);
//            deletelantai = view.findViewById(R.id.Deletelantai);
//            detaillantai = view.findViewById(R.id.detaillantai);
//
//
//
//
//        }
//    }


}
