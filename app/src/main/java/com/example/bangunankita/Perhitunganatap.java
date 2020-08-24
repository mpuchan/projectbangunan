package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangunankita.Model.Perhitunganacian1;
import com.example.bangunankita.Model.Perhitunganatap1;
import com.example.bangunankita.Model.ResponseAcian;
import com.example.bangunankita.Model.ResponseAtap;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Acian_adapter;
import com.example.bangunankita.adapter.Atap_adapter;
import com.github.andreilisun.swipedismissdialog.SwipeDismissDialog;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class Perhitunganatap extends AppCompatActivity {
    ImageView imageatap;
    TextView totalharga;
    String mId,Ju;
    ImageView rincian,panduanatap;
    SessionManager sm;
    int ProyekID;
    ProgressDialog pd;
    String token;
    String stringid;
    private Atap_adapter atap_adapter;
    private List<Perhitunganatap1> AtapModel = new ArrayList<>();
    private RecyclerView mRecyclerView;
    Context mContext;
    String nama;
    String desk;
    SwipeRefreshLayout swipeRefreshLayout;
//    SwipeDismissDialog swipeDismissDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitunganatap);
        pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();
        nama = "Panduan Perhitungan Atap";
        imageatap = findViewById(R.id.tambahatap);
        totalharga = findViewById(R.id.totalseluruh);
        sm= new SessionManager(Perhitunganatap.this);
        swipeRefreshLayout = findViewById(R.id.swiperf);
        rincian = findViewById(R.id.rincianatap);
        panduanatap = findViewById(R.id.panduanatap);
        mRecyclerView = findViewById(R.id.rv_atap);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        stringid=(map.get(sm.KEY_ID));
        sm.checkLogin();
        Bundle bundle = getIntent().getExtras();
        mId = bundle.getString("idproyek");

        Toast.makeText(Perhitunganatap.this, "Proyek Id"+mId,
                Toast.LENGTH_SHORT).show();
        Ju = mId;
        mContext = this;
        getperhitunganatap();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                finish();
                startActivity(getIntent());
                customType(Perhitunganatap.this,"fadein-to-fadeout");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        rincian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String apiKey = "oa00000000app";
                if (!TextUtils.isEmpty(Ju) && TextUtils.isDigitsOnly(Ju)) {
                    ProyekID = Integer.parseInt(Ju);
                } else {
                    ProyekID =0;
                }
                Uri uri = Uri.parse("https://bangunankita.herokuapp.com/api/v1/perhitunganbidang/export/"+ProyekID+"/?apiKey="+apiKey+"&accessToken="+token);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        panduanatap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent panduan = (new Intent(Perhitunganatap.this, Panduanapl.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.luasatap);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                setData.putString("ini",nama);
                setData.putString("deskripsi",desk);
                panduan.putExtra("picture", byteArray);
                panduan.putExtras(setData);
                panduan.putExtras(setData);
                startActivity(panduan);
            }
        });
        imageatap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent tambahatap = (new Intent(Perhitunganatap.this, Tambahatap.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek1",mId);
                tambahatap.putExtras(setData);

                startActivity(tambahatap);

            }
        });

    }
    private void deskripsi() {
        desk = "Penggunaan Aplikasi \n"+"\n"+"1. Untuk mengetahui jumlah material yang diperlukan adalah dengan cara memasukan data Luas keseluruhan atap " +
                ".masukan panjang nok yang sudah dijumlahkan"+"\n" +"\n"+
                "2. Hasil perhitungan akan keluar setelah menekan tombol hitung  pada bagian bawah inputan.";
    }
    private void getperhitunganatap() {
        String apiKey = "oa00000000app";
        if (!TextUtils.isEmpty(Ju) && TextUtils.isDigitsOnly(Ju)) {
            ProyekID = Integer.parseInt(Ju);
        } else {
            ProyekID =0;
        }


        Call<ResponseAtap> call = ApiClient.getRequestInterface().getPerhitunganatap(ProyekID,apiKey,token);
        call.enqueue(new Callback<ResponseAtap>() {
            @Override
            public void onResponse(Call<ResponseAtap> call, Response<ResponseAtap> response) {
//                swipeRefreshLayout.setRefreshing(false);
//                String message = response.body().getMessage();
                if (response.code() == 200 ) {
                    pd.hide();
                    AtapModel = response.body().getPerhitunganatap();
                    int totalPrice = 0;
                    for (int i = 0; i<AtapModel.size(); i++)
                    {
                        totalPrice += AtapModel.get(i).getHargatotal();
                    }
                    DecimalFormat formatter = new DecimalFormat("#,###.##");
                    String totalbiaya = formatter.format(totalPrice);
                    totalharga.setText("Rp."+totalbiaya);
//                    if (proyekModels == null) {
////                        datanull.setText("Data Proyek Masih Kosong");
//                    }else{
////                        datanull.setText(null);
//                    }
                    atap_adapter = new Atap_adapter(Perhitunganatap.this, AtapModel);
                    mRecyclerView.setAdapter(atap_adapter);

                } else if (response.code() == 422) {
                    Toast.makeText(Perhitunganatap.this, "Something wrong!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAtap> call, Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Perhitunganatap.this, "Oops! Something went wrong!" + t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
    }
