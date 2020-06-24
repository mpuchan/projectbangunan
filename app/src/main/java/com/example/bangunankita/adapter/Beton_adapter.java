package com.example.bangunankita.adapter;

import androidx.recyclerview.widget.RecyclerView;

public class Beton_adapter  {
    //    extends RecyclerView.Adapter<Beton_adapter.ViewHolder>
//    private Context context;
//    private List<Perhitunganbeton1> betons;
//
//    public Beton_adapter(Context context, List<Perhitunganbeton1> betons) {
//        this.context=context;
//        this.betons=betons;
//    }
//
//    @Override
//    public Beton_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listbeton, viewGroup, false);
//        return new Beton_adapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(Beton_adapter.ViewHolder viewHolder, final int i) {
//        final Perhitunganbeton1 BetonModel = betons.get(i);
//        final String Name = String.valueOf(betons.get(i).getNama());
//        final String Panjangbid = String.valueOf(betons.get(i).getPanjangbid());
//        final String Tinggibid = String.valueOf(betons.get(i).getTinggibid());
//        final String Panjangjen = String.valueOf(betons.get(i).getPanjangjen());
//        final String Tinggijen = String.valueOf(betons.get(i).getTinggijen());
//        final String Panjangpin = String.valueOf(betons.get(i).getPanjangpin());
//        final String Tinggipin = String.valueOf(betons.get(i).getTinggipin());
//        final String Jumlahdalamsak = String.valueOf(betons.get(i).getJumlahdalamsak());
//        final String Metode = String.valueOf(betons.get(i).getMetode());
//        final String Hargabatako = String.valueOf(betons.get(i).getHargabatako());
//        final String Hargapasir = String.valueOf(betons.get(i).getHargapasir());
//        final String Hargasemen = String.valueOf(betons.get(i).getHargasemen());
//        final String Totalbiaya = String.valueOf(betons.get(i).getHargatotal());
//
//
//
//        final String Semen = String.valueOf(betons.get(i).getJumlahkeperluansemen());
//        final String Pasir = String.valueOf(betons.get(i).getJumlahkeperluanpasir());
//        final String Batako = String.valueOf(betons.get(i).getJumlahkeperluanbatako());
//        final String Luas = String.valueOf(betons.get(i).getLuasBeton());
//
//        viewHolder.Nama.setText(Name);
//        viewHolder.semen.setText(Semen);
//        viewHolder.pasir.setText(Pasir);
//        viewHolder.batako.setText(Batako);
//
//        viewHolder.editbeton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent editbeton = new Intent(context, Editbeton.class)
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
//                setData.putString("luas_beton",Luas);
//                setData.putString("jumlahkeperluanbatako", Batako);
//                setData.putString("jumlahkeperluanpasir", Pasir);
//                setData.putString("Jumlahkeperluansemen", Semen);
//                setData.putString("jumlahdalamsak", Jumlahdalamsak);
//                setData.putString("metode", Metode);
//                setData.putString("hargabatako", Hargabatako);
//                setData.putString("hargapasir", Hargapasir);
//                setData.putString("hargasemen", Hargasemen);
//                editbeton.putExtras(setData);
//                context.startActivity(editbeton);
//
//            }
//        });
//        viewHolder.detailbeton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog detaildialog = new Dialog(context);
//                detaildialog.setContentView(R.layout.detail_beton);
//                TextView nama = detaildialog.findViewById(R.id.nama);
//                TextView luasbeton = detaildialog.findViewById(R.id.luasbeton);
//                TextView Batako1 = detaildialog.findViewById(R.id.Batako);
//                TextView Semen1 = detaildialog.findViewById(R.id.semen1);
//                TextView Pasir1 = detaildialog.findViewById(R.id.pasir);
//                TextView totalbiaya = detaildialog.findViewById(R.id.totalbiaya);
//                nama.setText(Name);
//                luasbeton.setText("Panjang "+Panjangbid+"m "+",Tinggi "+Tinggibid+"m "+"" +
//                        ",Panjang panel pintu "+Panjangpin+"m "+",Tinggi panel pintu "+Tinggipin+
//                        ",Panjang panel jendela "+Panjangjen+"m "+",Tinggi panel jendela "+Tinggijen+"m "+
//                        ",Luas Beton "+Luas+"m ");
//                Batako1.setText("Jumlah "+Batako+"buah"+",Harga Rp."+Hargabatako);
//                Semen1.setText("Berat "+Semen+"kg"+",Harga Rp."+Hargasemen);
//                Pasir1.setText("Jumlah "+Pasir+"m3"+",Harga Rp."+Hargapasir);
//                totalbiaya.setText(",Harga Rp."+Totalbiaya);
//
//                detaildialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                detaildialog.show();
//            }
//        });
//        viewHolder.deletebeton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Dialog deletedialog = new Dialog(context);
//                deletedialog.setContentView(R.layout.delete_dialog);
//                TextView deletemessage = deletedialog.findViewById(R.id.textdelete);
//                TextView silang = deletedialog.findViewById(R.id.silang);
//                Button btndelete = deletedialog.findViewById(R.id.buttonhapus);
//                Button btncancel = deletedialog.findViewById(R.id.buttoncancel);
//                deletemessage.setText("Yakin ingin menghapus data beton " +Name);
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
//        return betons.size();
//    }
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        private TextView Nama,semen,pasir,batako,edit;
//        private ImageView image;
//        private Button editbeton,deletebeton,detailbeton;
//        Dialog deletedialog;
//
//        public ViewHolder(View view) {
//            super(view);
//            Nama = (TextView)view.findViewById(R.id.Nama);
//            semen = (TextView)view.findViewById(R.id.semen);
//            pasir = (TextView)view.findViewById(R.id.pasir);
//            batako = (TextView)view.findViewById(R.id.Batako);
//            editbeton = view.findViewById(R.id.editbeton);
//            deletebeton = view.findViewById(R.id.Deletebeton);
//            detailbeton = view.findViewById(R.id.detailbeton);
//
//
//
//
//        }
//    }


}
