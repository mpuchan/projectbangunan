package com.example.bangunankita.adapter;

import androidx.recyclerview.widget.RecyclerView;

public class Urugan_adapter {
    //    extends RecyclerView.Adapter<Urugan_adapter.ViewHolder>
//    private Context context;
//    private List<Perhitunganurugan1> urugans;
//
//    public Urugan_adapter(Context context, List<Perhitunganurugan1> urugans) {
//        this.context=context;
//        this.urugans=urugans;
//    }
//
//    @Override
//    public Urugan_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listurugan, viewGroup, false);
//        return new Urugan_adapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(Urugan_adapter.ViewHolder viewHolder, final int i) {
//        final Perhitunganurugan1 UruganModel = urugans.get(i);
//        final String Name = String.valueOf(urugans.get(i).getNama());
//        final String Panjangbid = String.valueOf(urugans.get(i).getPanjangbid());
//        final String Tinggibid = String.valueOf(urugans.get(i).getTinggibid());
//        final String Panjangjen = String.valueOf(urugans.get(i).getPanjangjen());
//        final String Tinggijen = String.valueOf(urugans.get(i).getTinggijen());
//        final String Panjangpin = String.valueOf(urugans.get(i).getPanjangpin());
//        final String Tinggipin = String.valueOf(urugans.get(i).getTinggipin());
//        final String Jumlahdalamsak = String.valueOf(urugans.get(i).getJumlahdalamsak());
//        final String Metode = String.valueOf(urugans.get(i).getMetode());
//        final String Hargabatako = String.valueOf(urugans.get(i).getHargabatako());
//        final String Hargapasir = String.valueOf(urugans.get(i).getHargapasir());
//        final String Hargasemen = String.valueOf(urugans.get(i).getHargasemen());
//        final String Totalbiaya = String.valueOf(urugans.get(i).getHargatotal());
//
//
//
//        final String Semen = String.valueOf(urugans.get(i).getJumlahkeperluansemen());
//        final String Pasir = String.valueOf(urugans.get(i).getJumlahkeperluanpasir());
//        final String Batako = String.valueOf(urugans.get(i).getJumlahkeperluanbatako());
//        final String Luas = String.valueOf(urugans.get(i).getLuasUrugan());
//
//        viewHolder.Nama.setText(Name);
//        viewHolder.semen.setText(Semen);
//        viewHolder.pasir.setText(Pasir);
//        viewHolder.batako.setText(Batako);
//
//        viewHolder.editurugan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent editurugan = new Intent(context, Editurugan.class)
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
//                setData.putString("luas_urugan",Luas);
//                setData.putString("jumlahkeperluanbatako", Batako);
//                setData.putString("jumlahkeperluanpasir", Pasir);
//                setData.putString("Jumlahkeperluansemen", Semen);
//                setData.putString("jumlahdalamsak", Jumlahdalamsak);
//                setData.putString("metode", Metode);
//                setData.putString("hargabatako", Hargabatako);
//                setData.putString("hargapasir", Hargapasir);
//                setData.putString("hargasemen", Hargasemen);
//                editurugan.putExtras(setData);
//                context.startActivity(editurugan);
//
//            }
//        });
//        viewHolder.detailurugan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog detaildialog = new Dialog(context);
//                detaildialog.setContentView(R.layout.detail_urugan);
//                TextView nama = detaildialog.findViewById(R.id.nama);
//                TextView luasurugan = detaildialog.findViewById(R.id.luasurugan);
//                TextView Batako1 = detaildialog.findViewById(R.id.Batako);
//                TextView Semen1 = detaildialog.findViewById(R.id.semen1);
//                TextView Pasir1 = detaildialog.findViewById(R.id.pasir);
//                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);
//                nama.setText(Name);
//                luasurugan.setText("Panjang "+Panjangbid+"m "+",Tinggi "+Tinggibid+"m "+"" +
//                        ",Panjang panel pintu "+Panjangpin+"m "+",Tinggi panel pintu "+Tinggipin+
//                        ",Panjang panel jendela "+Panjangjen+"m "+",Tinggi panel jendela "+Tinggijen+"m "+
//                        ",Luas Urugan "+Luas+"m ");
//                Batako1.setText("Jumlah "+Batako+"buah"+",Harga Rp."+Hargabatako);
//                Semen1.setText("Berat "+Semen+"kg"+",Harga Rp."+Hargasemen);
//                Pasir1.setText("Jumlah "+Pasir+"m3"+",Harga Rp."+Hargapasir);
//                totalbiaya.setText(",Harga Rp."+Totalbiaya);
//
//                detaildialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                detaildialog.show();
//            }
//        });
//        viewHolder.deleteurugan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog deletedialog = new Dialog(context);
//                deletedialog.setContentView(R.layout.delete_dialog);
//                TextView deletemessage = deletedialog.findViewById(R.id.textdelete);
//                TextView silang = deletedialog.findViewById(R.id.silang);
//                Button btndelete = deletedialog.findViewById(R.id.buttonhapus);
//                Button btncancel = deletedialog.findViewById(R.id.buttoncancel);
//                deletemessage.setText("Yakin ingin menghapus data urugan " +Name);
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
//        return urugans.size();
//    }
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        private TextView Nama,semen,pasir,batako,edit;
//        private ImageView image;
//        private Button editurugan,deleteurugan,detailurugan;
//        Dialog deletedialog;
//
//        public ViewHolder(View view) {
//            super(view);
//            Nama = (TextView)view.findViewById(R.id.Nama);
//            semen = (TextView)view.findViewById(R.id.semen);
//            pasir = (TextView)view.findViewById(R.id.pasir);
//            batako = (TextView)view.findViewById(R.id.Batako);
//            editurugan = view.findViewById(R.id.editurugan);
//            deleteurugan = view.findViewById(R.id.Deleteurugan);
//            detailurugan = view.findViewById(R.id.detailurugan);
//
//
//
//
//        }
//    }




}
