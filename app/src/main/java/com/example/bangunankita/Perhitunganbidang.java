package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bangunankita.Model.Perhitunganbidang1;
import com.example.bangunankita.Model.Proyek_model;
import com.example.bangunankita.Model.ResponseBidang;
import com.example.bangunankita.Model.ResponseModel;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Bidang_adapter;
import com.example.bangunankita.adapter.Proyek_adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Perhitunganbidang extends AppCompatActivity {
    private ImageView rician;
    int ProyekID;
    String Ju;
    SessionManager sm;
    String token;
    String stringid;
    private Bidang_adapter bidang_adapter;
    private List<Perhitunganbidang1> BidangModel = new ArrayList<>();
    private RecyclerView mRecyclerView;
    Context mContext;
    String mId;
    Dialog detaildialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitunganbidang);
        rician = findViewById(R.id.rician);
        sm= new SessionManager(Perhitunganbidang.this);
        detaildialog = new Dialog(this);
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

        rician.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent tambahbidang = (new Intent(Perhitunganbidang.this, Tambahbidang.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek1",mId);
                tambahbidang.putExtras(setData);

                startActivity(tambahbidang);

            }
        });
    }

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
//                swipeRefreshLayout.setRefreshing(false);
//                String message = response.body().getMessage();
                if (response.code() == 200 ) {
                    BidangModel = response.body().getPerhitunganbidang();

//                    if (proyekModels == null) {
////                        datanull.setText("Data Proyek Masih Kosong");
//                    }else{
////                        datanull.setText(null);
//                    }
                    bidang_adapter = new Bidang_adapter(Perhitunganbidang.this, BidangModel);
                    mRecyclerView.setAdapter(bidang_adapter);

                } else if (response.code() == 422) {
                    Toast.makeText(Perhitunganbidang.this, "Something wrong!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBidang> call, Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Perhitunganbidang.this, "Oops! Something went wrong!" + t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });



    }


}
