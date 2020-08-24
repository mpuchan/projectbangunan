package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import static maes.tech.intentanim.CustomIntent.customType;

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

import com.example.bangunankita.Model.Perhitunganbeton1;
import com.example.bangunankita.Model.Perhitunganpengecatan1;
import com.example.bangunankita.Model.ResponseBeton;
import com.example.bangunankita.Model.ResponsePengecatan;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Beton_adapter;
import com.example.bangunankita.adapter.Pengecatan_adapter;
import com.github.andreilisun.swipedismissdialog.SwipeDismissDialog;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Perhitunganbeton extends AppCompatActivity {
    private ImageView tambahbeton,print;
    private TextView totalharga;
    ImageView rincian,panduanbeton;
    int ProyekID;
    String Ju,mId;
    SessionManager sm;
    int totalPrice=0;
    private Beton_adapter beton_adapter;
    private List<Perhitunganbeton1> BetonModel = new ArrayList<>();
    private RecyclerView mRecyclerView;
    String token;
    String nama;
    String stringid;
    ProgressDialog pd;
    SwipeRefreshLayout swipeRefreshLayout;
    SwipeDismissDialog swipeDismissDialog;
    Context mContext;
    String desk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitunganbeton);
        tambahbeton = findViewById(R.id.tambahbeton);
        pd = new ProgressDialog(this);
        pd.setMessage("loading");
        nama = "beton";
        pd.show();
        totalharga = findViewById(R.id.totalharga);
        sm= new SessionManager(Perhitunganbeton.this);
        mRecyclerView = findViewById(R.id.rv_beton);
        swipeRefreshLayout = findViewById(R.id.swiperf);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        stringid=(map.get(sm.KEY_ID));
        sm.checkLogin();
        Bundle bundle = getIntent().getExtras();
        mId = bundle.getString("idproyek");

//        Toast.makeText(Perhitunganbeton.this, "Proyek Id"+mId,
//                Toast.LENGTH_SHORT).show();
        Ju = mId;
        rincian = findViewById(R.id.rincianbeton);
        panduanbeton = findViewById(R.id.panduanbeton);
        mContext = this;
        getperhitunganbeton();
        deskripsi();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                finish();
                startActivity(getIntent());
                customType(Perhitunganbeton.this,"fadein-to-fadeout");
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
        panduanbeton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent panduan = (new Intent(Perhitunganbeton.this, Panduanapl.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.beton);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                setData.putString("ini",nama);
                setData.putString("deskripsi",desk);
                panduan.putExtra("picture", byteArray);
                panduan.putExtras(setData);
                startActivity(panduan);
            }
        });
        tambahbeton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent tambahbeton = (new Intent(Perhitunganbeton.this, Tambahbeton.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek1",mId);
                tambahbeton.putExtras(setData);
                startActivity(tambahbeton);
                customType(Perhitunganbeton.this,"fadein-to-fadeout");

            }
        });
    }
    private void deskripsi() {
        desk = "Penggunaan Aplikasi \n"+"\n"+"1. Untuk mengetahui jumlah material yang diperlukan adalah dengan cara memasukan data Luas keseluruhan atap " +
                ".masukan panjang nok yang sudah dijumlahkan"+"\n" +"\n"+
                "2. Hasil perhitungan akan keluar setelah menekan tombol hitung  pada bagian bawah inputan.";
    }
    private void getperhitunganbeton() {
        String apiKey = "oa00000000app";
        if (!TextUtils.isEmpty(Ju) && TextUtils.isDigitsOnly(Ju)) {
            ProyekID = Integer.parseInt(Ju);
        } else {
            ProyekID =0;
        }


        Call<ResponseBeton> call = ApiClient.getRequestInterface().getPerhitunganbeton(ProyekID,apiKey,token);
        call.enqueue(new Callback<ResponseBeton>() {
            @Override
            public void onResponse(Call<ResponseBeton> call, Response<ResponseBeton> response) {
//                swipeRefreshLayout.setRefreshing(false);
//                String message = response.body().getMessage();
                if (response.code() == 200 ) {
                    pd.hide();
                    BetonModel = response.body().getPerhitunganbeton();

                    for (int i = 0; i<BetonModel.size(); i++) {
                        totalPrice += BetonModel.get(i).getHargatotal();
                    }
                    DecimalFormat formatter = new DecimalFormat("#,###.##");
                    String totalbiaya = formatter.format(totalPrice);
                    totalharga.setText("Rp."+totalbiaya);

                    beton_adapter = new Beton_adapter(Perhitunganbeton.this, BetonModel);
                    mRecyclerView.setAdapter(beton_adapter);

                } else if (response.code() == 422) {
                    Toast.makeText(Perhitunganbeton.this, "Something wrong!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBeton> call, Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Perhitunganbeton.this, "Oops! Something went wrong!" + t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });

    }
    }
