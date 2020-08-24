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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.bangunankita.Dashboard;
import com.example.bangunankita.Edit_Proyek;
import com.example.bangunankita.MenuProyek;
import com.example.bangunankita.Model.Proyek_model;
import com.example.bangunankita.Model.ResponseBidang;
import com.example.bangunankita.Model.ResponseModel;
import com.example.bangunankita.Perhitunganbidang;
import com.example.bangunankita.R;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.util.stream.IntStream.builder;


public class Proyek_adapter extends RecyclerView.Adapter<Proyek_adapter.ViewHolder> implements Filterable {

    private Context context;
    private List<Proyek_model> proyeks;
    private List<Proyek_model> proyeks1;
    public String[] mColors = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };

    public Proyek_adapter(Context context,List<Proyek_model> proyeks) {
        this.context=context;
        this.proyeks=proyeks;
        proyeks1 =new ArrayList<>(proyeks);
    }

    @Override
    public Proyek_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitemproyek, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Proyek_adapter.ViewHolder viewHolder, final int i) {
        final Proyek_model proyekModel = proyeks.get(i);
        final String id_proyek= String.valueOf(proyeks.get(i).getId());
        final String nama_proyek=proyeks.get(i).getNamaProyek();
        final String luastanah = String.valueOf(proyeks.get(i).getLuas_tanah());
        final String luasbangunan = String.valueOf(proyeks.get(i).getLuas_bangunan());
        final String lokasi =proyeks.get(i).getLokasi();
        final String Tanggal =proyeks.get(i).getTanggal();

        String firstCharNamaProyek= nama_proyek.substring(0,1);
        TextDrawable drawable = TextDrawable.builder().beginConfig()
                .withBorder(16) /* thickness in px */
                .endConfig()
                .buildRound(firstCharNamaProyek, getColor());
        viewHolder.image.setImageDrawable(drawable);
        viewHolder.nama_proyek.setText(nama_proyek);
        viewHolder.lokasi_proyek.setText(lokasi);

        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("dd/MMM/yyyy");
        String inputDateStr=Tanggal;
        Date date = null;
        try {
            date = inputFormat.parse(inputDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String outputDateStr = outputFormat.format(date);

        viewHolder.tanggal.setText(outputDateStr);

        viewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(context, Edit_Proyek.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle setData = new Bundle();
                setData.putString("idproyek",id_proyek);
                setData.putString("namaproyek",nama_proyek);
                setData.putString("luastanah",luastanah);
                setData.putString("luasbangunan",luasbangunan);
                setData.putString("lokasi",lokasi);
                setData.putString("tanggal",Tanggal );
                edit.putExtras(setData);
                context.startActivity(edit);

            }
        });
        viewHolder.list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(context, MenuProyek.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle setData = new Bundle();
                setData.putString("idproyek",id_proyek);
                setData.putString("namaproyek",nama_proyek);
                setData.putString("lokasi",lokasi);
                setData.putString("luastanah",luastanah);
                setData.putString("luasbangunan",luasbangunan);
                detail.putExtras(setData);
                context.startActivity(detail);
            }
        });
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog deletedialog = new Dialog(context);
                deletedialog.setContentView(R.layout.delete_dialog);
                TextView deletemessage = deletedialog.findViewById(R.id.textdelete);
                TextView silang = deletedialog.findViewById(R.id.silang);
                Button btndelete = deletedialog.findViewById(R.id.buttonhapus);
                Button btncancel = deletedialog.findViewById(R.id.buttoncancel);
                deletemessage.setText("Yakin ingin menghapus data " +nama_proyek);
                int ProyekID = Integer.parseInt(id_proyek);
                String apiKey = "oa00000000app";
                SessionManager sm;
                sm= new SessionManager(context);
                HashMap<String,String> map = sm.getDetailLogin();
                String token=(map.get(sm.KEY_TOKEN));
                btndelete.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Call<Void> call = ApiClient.getRequestInterface().actionDeleteProyek(ProyekID,apiKey,token);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
//                swipeRefreshLayout.setRefreshing(false);
//                String message = response.body().getMessage();
                                if (response.code() == 200 ) {
                                    deletedialog.hide();
                                    Toast.makeText(context, "Sukses hapus!",
                                            Toast.LENGTH_SHORT).show();
//                    if (proyekModels == null) {
////                        datanull.setText("Data Proyek Masih Kosong");
//                    }else{
////                        datanull.setText(null);
//

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
                        deletedialog.hide();
                    }
                });
                deletedialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                deletedialog.show();
            }
        });

    }
    @Override
    public int getItemCount() {
        return proyeks.size();
    }

    @Override
    public Filter getFilter() {
        return proyekfilter;
    }
private Filter proyekfilter = new Filter() {
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        List<Proyek_model> filterproyek = new ArrayList<>();
        if (constraint == null || constraint.length() == 0){
            filterproyek.addAll(proyeks1);

        }else {
            String filternpattern = constraint.toString().toLowerCase().trim();
            for (Proyek_model proyek: proyeks1 ){
                if (proyek.getNamaProyek().toLowerCase().contains((filternpattern))){
                    filterproyek.add(proyek);
                }
            }
        }
        FilterResults results = new FilterResults();
        results.values = filterproyek;
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        proyeks.clear();
        proyeks.addAll((List)results.values);
        notifyDataSetChanged();
    }
};

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nama_proyek,lokasi_proyek,edit,tanggal,delete;
        private ImageView image;
        private CardView list;
        Dialog deletedialog;
        private SimpleDateFormat dateFormatter;

        public ViewHolder(View view) {
            super(view);

            nama_proyek = (TextView)view.findViewById(R.id.nameproyek);
            lokasi_proyek = (TextView)view.findViewById(R.id.lokasiproyek);
            tanggal = view.findViewById(R.id.dateproyek);
            image = view.findViewById(R.id.picture1);
            edit = view.findViewById(R.id.editproyek);
            delete = view.findViewById(R.id.hapusproyek);
            list = view.findViewById(R.id.user_layout);
            Animation anim = AnimationUtils.loadAnimation(context, R.anim.itemproyekanim);
            view.startAnimation(anim);

        }
    }
    public int getColor() {
        String color;

        // Randomly select a fact
        Random randomGenerator = new Random(); // Construct a new Random number generator
        int randomNumber = randomGenerator.nextInt(mColors.length);

        color = mColors[randomNumber];
        int colorAsInt = Color.parseColor(color);

        return colorAsInt;
    }



}