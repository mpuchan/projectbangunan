package com.example.bangunankita.adapter;

import androidx.recyclerview.widget.RecyclerView;

public class Plafond_adapter  {
    //    extends RecyclerView.Adapter<Plafond_adapter.ViewHolder>
//    private Context context;
//    private List<Perhitunganplafond1> plafonds;
//
//    public Plafond_adapter(Context context, List<Perhitunganplafond1> plafonds) {
//        this.context=context;
//        this.plafonds=plafonds;
//    }
//
//    @Override
//    public Plafond_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listplafond, viewGroup, false);
//        return new Plafond_adapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(Plafond_adapter.ViewHolder viewHolder, final int i) {
//        final Perhitunganplafond1 PlafondModel = plafonds.get(i);
//        final String Name = String.valueOf(plafonds.get(i).getNama());
//        final String Panjangbid = String.valueOf(plafonds.get(i).getPanjangbid());
//        final String Tinggibid = String.valueOf(plafonds.get(i).getTinggibid());
//        final String Panjangjen = String.valueOf(plafonds.get(i).getPanjangjen());
//        final String Tinggijen = String.valueOf(plafonds.get(i).getTinggijen());
//        final String Panjangpin = String.valueOf(plafonds.get(i).getPanjangpin());
//        final String Tinggipin = String.valueOf(plafonds.get(i).getTinggipin());
//        final String Jumlahdalamsak = String.valueOf(plafonds.get(i).getJumlahdalamsak());
//        final String Metode = String.valueOf(plafonds.get(i).getMetode());
//        final String Hargabatako = String.valueOf(plafonds.get(i).getHargabatako());
//        final String Hargapasir = String.valueOf(plafonds.get(i).getHargapasir());
//        final String Hargasemen = String.valueOf(plafonds.get(i).getHargasemen());
//        final String Totalbiaya = String.valueOf(plafonds.get(i).getHargatotal());
//
//
//
//        final String Semen = String.valueOf(plafonds.get(i).getJumlahkeperluansemen());
//        final String Pasir = String.valueOf(plafonds.get(i).getJumlahkeperluanpasir());
//        final String Batako = String.valueOf(plafonds.get(i).getJumlahkeperluanbatako());
//        final String Luas = String.valueOf(plafonds.get(i).getLuasPlafond());
//
//        viewHolder.Nama.setText(Name);
//        viewHolder.semen.setText(Semen);
//        viewHolder.pasir.setText(Pasir);
//        viewHolder.batako.setText(Batako);
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
//    }
//
//    @Override
//    public int getItemCount() {
//        return plafonds.size();
//    }
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        private TextView Nama,semen,pasir,batako,edit;
//        private ImageView image;
//        private Button editplafond,deleteplafond,detailplafond;
//        Dialog deletedialog;
//
//        public ViewHolder(View view) {
//            super(view);
//            Nama = (TextView)view.findViewById(R.id.Nama);
//            semen = (TextView)view.findViewById(R.id.semen);
//            pasir = (TextView)view.findViewById(R.id.pasir);
//            batako = (TextView)view.findViewById(R.id.Batako);
//            editplafond = view.findViewById(R.id.editplafond);
//            deleteplafond = view.findViewById(R.id.Deleteplafond);
//            detailplafond = view.findViewById(R.id.detailplafond);
//
//
//
//
//        }
//    }



}
