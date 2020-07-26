package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import static maes.tech.intentanim.CustomIntent.customType;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangunankita.Model.Jenispengerjaan;
import com.example.bangunankita.Model.Perhitunganbidang1;
import com.example.bangunankita.Model.Proyek_model;
import com.example.bangunankita.Model.ResponseBidang;
import com.example.bangunankita.Model.ResponseModel;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Bidang_adapter;
import com.example.bangunankita.adapter.Jenispengerjaan_adapter;
import com.example.bangunankita.adapter.Proyek_adapter;
import com.example.bangunankita.adapter.Reportbidang;
import com.github.andreilisun.swipedismissdialog.OnSwipeDismissListener;
import com.github.andreilisun.swipedismissdialog.SwipeDismissDialog;
import com.github.andreilisun.swipedismissdialog.SwipeDismissDirection;
import com.google.android.material.behavior.SwipeDismissBehavior;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Perhitunganbidang extends AppCompatActivity {
    private ImageView rician,print,ricaian;
    private TextView totalharga;
    int ProyekID;
    String Ju;
    SessionManager sm;
    SwipeRefreshLayout swipeRefreshLayout;
    String token;
    String stringid;
    String nama;
    private Bidang_adapter bidang_adapter;
    private List<Perhitunganbidang1> BidangModel = new ArrayList<>();
    private RecyclerView mRecyclerView;
    Context mContext;
    int totalPrice = 0;
    int totalbatako = 0;
    float totalsemen =0f;
    int totalpasir = 0;
    int totalhargabatako = 0;
    int totalhargasemen =0;
    int totalhargapasir = 0;
    String mId;
    SwipeDismissDialog swipeDismissDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitunganbidang);
        rician = findViewById(R.id.rician);
        totalharga = findViewById(R.id.totalharga);
        swipeRefreshLayout = findViewById(R.id.swiperf);
        print = findViewById(R.id.print);
        ricaian =findViewById(R.id.panduan);
        sm= new SessionManager(Perhitunganbidang.this);
        mRecyclerView = findViewById(R.id.rv_bidang);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        stringid=(map.get(sm.KEY_ID));
        sm.checkLogin();
        Bundle bundle = getIntent().getExtras();
        mId = bundle.getString("idproyek");


        Toast.makeText(Perhitunganbidang.this, "Proyek Id"+mId,
                Toast.LENGTH_SHORT).show();
        Ju = mId;
        mContext = this;
        getperhitunganbidang();

        ricaian.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                finish();
                startActivity(getIntent());
                customType(Perhitunganbidang.this,"fadein-to-fadeout");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        rician.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent tambahbidang = (new Intent(Perhitunganbidang.this, Tambahbidang.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek1",mId);
                tambahbidang.putExtras(setData);
                startActivity(tambahbidang);
                customType(Perhitunganbidang.this,"fadein-to-fadeout");

            }
        });
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View detaildialog = LayoutInflater.from(getBaseContext()).inflate(R.layout.dialogdetailbidang, null);
                TextView nama1 = detaildialog.findViewById(R.id.namabatako);
                ImageView print = detaildialog.findViewById(R.id.print);
                swipeDismissDialog = new SwipeDismissDialog.Builder(Perhitunganbidang.this)
                        .setView(detaildialog)
                        .setOnSwipeDismissListener(new OnSwipeDismissListener() {
                            @Override
                            public void onSwipeDismiss(View view, SwipeDismissDirection direction) {
                            }
                        })
                        .build()
                        .show();
                print.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String apiKey = "oa00000000app";
                        Uri uri = Uri.parse("http://192.168.43.163:3000/api/v1/perhitunganbidang/export/1/?apiKey="+apiKey+"&accessToken="+token);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
        }
        });
    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        bidang_adapter.notifyDataSetChanged();
//    }
    private void getperhitunganbidang() {
        String apiKey = "oa00000000app";
        if (!TextUtils.isEmpty(Ju) && TextUtils.isDigitsOnly(Ju)) {
            ProyekID = Integer.parseInt(Ju);
        } else {
            ProyekID =0;
        }


        Call<ResponseBidang> call = ApiClient.getRequestInterface().getPerhitunganbidang(ProyekID,apiKey,token);
        call.enqueue(new Callback<ResponseBidang>() {
            @Override
            public void onResponse(Call<ResponseBidang> call, Response<ResponseBidang> response) {
                swipeRefreshLayout.setRefreshing(false);
//                String message = response.body().getMessage();
                if (response.code() == 200 ) {
                    BidangModel = response.body().getPerhitunganbidang();

                    for (int i = 0; i<BidangModel.size(); i++)
                    {
                        totalPrice += BidangModel.get(i).getHargatotal();
                        totalbatako += BidangModel.get(i).getJumlahkeperluanbatako();
                        totalsemen += BidangModel.get(i).getJumlahdalamsak();
                        totalbatako += BidangModel.get(i).getJumlahkeperluanpasir();
                        totalhargabatako += BidangModel.get(i).getHargabatakototal();
                        totalhargapasir += BidangModel.get(i).getHargapasirtotal();
                        totalhargasemen += BidangModel.get(i).getHargasementotal();
                    }
                    DecimalFormat formatter = new DecimalFormat("#,###.##");
                    String totalbiaya = formatter.format(totalPrice);
                   totalharga.setText("Rp."+totalbiaya);

                    bidang_adapter = new Bidang_adapter(Perhitunganbidang.this, BidangModel);
                    mRecyclerView.setAdapter(bidang_adapter);
                    bidang_adapter.notifyDataSetChanged();

                } else if (response.code() == 422) {
                    Toast.makeText(Perhitunganbidang.this, "Something wrong!",
                            Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBidang> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Perhitunganbidang.this, "Oops! Something went wrong!" + t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });



    }



}
