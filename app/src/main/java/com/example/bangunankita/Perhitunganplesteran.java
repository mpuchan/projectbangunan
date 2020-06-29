package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bangunankita.Model.Perhitunganbidang1;
import com.example.bangunankita.Model.Perhitunganplesteran1;
import com.example.bangunankita.Model.ResponseAcian;
import com.example.bangunankita.Model.ResponsePlesteran;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Acian_adapter;
import com.example.bangunankita.adapter.Bidang_adapter;
import com.example.bangunankita.adapter.Plesteran_adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Perhitunganplesteran extends AppCompatActivity {
    ImageView imageples;
    String mId,Ju,token,stringid;
    int ProyekID;
    Context mContext;
    SessionManager sm;
    private Plesteran_adapter plesteran_adapter;
    private List<Perhitunganplesteran1> PlesteranModel = new ArrayList<>();
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitunganplesteran);
        imageples = findViewById(R.id.tambahplester);
        sm= new SessionManager(Perhitunganplesteran.this);
        mRecyclerView = findViewById(R.id.rv_plesteran);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        stringid=(map.get(sm.KEY_ID));
        sm.checkLogin();
        Bundle bundle = getIntent().getExtras();
        mId = bundle.getString("idproyek");


        Toast.makeText(Perhitunganplesteran.this, "Proyek Id"+mId,
                Toast.LENGTH_SHORT).show();
        Ju = mId;
        mContext = this;
        getperhitunganplesteran();
        imageples.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent tambahplster = (new Intent(Perhitunganplesteran.this, Tambahplesteran.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek1",mId);
                tambahplster.putExtras(setData);

                startActivity(tambahplster);

            }
        });
    }

    private void getperhitunganplesteran() {
        String apiKey = "oa00000000app";
        if (!TextUtils.isEmpty(Ju) && TextUtils.isDigitsOnly(Ju)) {
            ProyekID = Integer.parseInt(Ju);
        } else {
            ProyekID =0;
        }


        Call<ResponsePlesteran> call = ApiClient.getRequestInterface().getPerhitunganplesteran(ProyekID,apiKey,token);
        call.enqueue(new Callback<ResponsePlesteran>() {
            @Override
            public void onResponse(Call<ResponsePlesteran> call, Response<ResponsePlesteran> response) {
//                swipeRefreshLayout.setRefreshing(false);
//                String message = response.body().getMessage();
                if (response.code() == 200 ) {
                    PlesteranModel = response.body().getPerhitunganplesteran();

//                    if (proyekModels == null) {
////                        datanull.setText("Data Proyek Masih Kosong");
//                    }else{
////                        datanull.setText(null);
//                    }
                    plesteran_adapter = new Plesteran_adapter(Perhitunganplesteran.this, PlesteranModel);
                    mRecyclerView.setAdapter(plesteran_adapter);

                } else if (response.code() == 422) {
                    Toast.makeText(Perhitunganplesteran.this, "Something wrong!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePlesteran> call, Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Perhitunganplesteran.this, "Oops! Something went wrong!" + t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

}
