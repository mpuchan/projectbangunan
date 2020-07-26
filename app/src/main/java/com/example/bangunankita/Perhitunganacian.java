package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
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
    SessionManager sm;
    int ProyekID;
    String token;
    String stringid;
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

        Toast.makeText(Perhitunganacian.this, "Proyek Id"+mId,
                Toast.LENGTH_SHORT).show();
        Ju = mId;
        mContext = this;
        getperhitunganacian();
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
                    AcianModel = response.body().getPerhitunganacian();
                    int totalPrice = 0;
                    for (int i = 0; i<AcianModel.size(); i++)
                    {
                        totalPrice += AcianModel.get(i).getHargatotal();
                    }
                    totalharga.setText(String.valueOf(totalPrice));
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
