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
import com.example.bangunankita.Model.Perhitunganbidang1;
import com.example.bangunankita.Model.ResponseAcian;
import com.example.bangunankita.Model.ResponseBidang;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Acian_adapter;
import com.example.bangunankita.adapter.Bidang_adapter;
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

public class Perhitunganacian extends AppCompatActivity {
    ImageView imageacian;
    TextView totalharga;
    String mId,Ju;
    ImageView panduanacian,rincianacian;
    SessionManager sm;
    int ProyekID;
    ProgressDialog pd;
    String token;
    String stringid,desk;
    String nama1;
    private Acian_adapter acian_adapter;
    private List<Perhitunganacian1> AcianModel = new ArrayList<>();
    private RecyclerView mRecyclerView;
    Context mContext;
    SwipeRefreshLayout swipeRefreshLayout;
    SwipeDismissDialog swipeDismissDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitunganacian);
        imageacian = findViewById(R.id.tambahacian);
        pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();
        nama1 = "Panduan Perhitungan Acian";
        panduanacian = findViewById(R.id.panduanacian);
        rincianacian = findViewById(R.id.rincianacian);
        totalharga = findViewById(R.id.totalacian);
        sm= new SessionManager(Perhitunganacian.this);
        swipeRefreshLayout = findViewById(R.id.swiperf);
        mRecyclerView = findViewById(R.id.rv_acian);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        stringid=(map.get(sm.KEY_ID));
        sm.checkLogin();
        Bundle bundle = getIntent().getExtras();
        mId = bundle.getString("idproyek");
        deskripsi();
        Toast.makeText(Perhitunganacian.this, "Proyek Id"+mId,
                Toast.LENGTH_SHORT).show();
        Ju = mId;
        mContext = this;
        getperhitunganacian();
        panduanacian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent panduan = (new Intent(Perhitunganacian.this, Panduanapl.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.luasplafon);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                setData.putString("ini",nama1);
                setData.putString("deskripsi",desk);
                panduan.putExtra("picture", byteArray);
                panduan.putExtras(setData);
                startActivity(panduan);
            }
        });
        rincianacian.setOnClickListener(new View.OnClickListener() {
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
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                finish();
                startActivity(getIntent());
                customType(Perhitunganacian.this,"fadein-to-fadeout");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        imageacian.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent tambahacian = (new Intent(Perhitunganacian.this, Tambahacian.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek1",mId);
                tambahacian.putExtras(setData);

                startActivity(tambahacian);

            }
        });

    }

    private void deskripsi() {
        desk = "Penggunaan Aplikasi \n"+"\n"+"1. Untuk mengetahui jumlah material yang diperlukan adalah dengan cara memilih data bidang yang telah di " +
                "hitung pada perhitungan bidang. Kemudian tambahkan data sisi."+"\n" +"\n"+
                "2. Untuk mendapatkan luas dinding yang akan di aci tekan tombol proses" +"\n" +"\n"+
                "3. Hasil perhitungan akan keluar setelah menekan tombol hitung  pada bagian bawah inputan.";
    }

    private void getperhitunganacian() {
        String apiKey = "oa00000000app";
        if (!TextUtils.isEmpty(Ju) && TextUtils.isDigitsOnly(Ju)) {
            ProyekID = Integer.parseInt(Ju);
        } else {
            ProyekID =0;
        }


        Call<ResponseAcian> call = ApiClient.getRequestInterface().getPerhitunganacian(ProyekID,apiKey,token);
        call.enqueue(new Callback<ResponseAcian>() {
            @Override
            public void onResponse(Call<ResponseAcian> call, Response<ResponseAcian> response) {
//                swipeRefreshLayout.setRefreshing(false);
//                String message = response.body().getMessage();
                if (response.code() == 200 ) {
                    pd.hide();
                    AcianModel = response.body().getPerhitunganacian();
                    int totalPrice = 0;
                    for (int i = 0; i<AcianModel.size(); i++)
                    {
                        totalPrice += AcianModel.get(i).getHargatotal();
                    }
                    DecimalFormat formatter = new DecimalFormat("#,###.##");
                    String totalbiaya = formatter.format(totalPrice);
                    totalharga.setText("Rp."+totalbiaya);
//                    if (proyekModels == null) {
////                        datanull.setText("Data Proyek Masih Kosong");
//                    }else{
////                        datanull.setText(null);
//                    }
                    acian_adapter = new Acian_adapter(Perhitunganacian.this, AcianModel);
                    mRecyclerView.setAdapter(acian_adapter);

                } else if (response.code() == 422) {
                    Toast.makeText(Perhitunganacian.this, "Something wrong!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAcian> call, Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Perhitunganacian.this, "Oops! Something went wrong!" + t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
}
