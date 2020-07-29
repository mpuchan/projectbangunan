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

import com.example.bangunankita.Model.Perhitunganlantai1;
import com.example.bangunankita.Model.Perhitunganpondasi1;
import com.example.bangunankita.Model.ResponsePlesteran;
import com.example.bangunankita.Model.ResponsePondasi;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Lantai_adapter;
import com.example.bangunankita.adapter.Plesteran_adapter;
import com.example.bangunankita.adapter.Pondasi_adapter;
import com.github.andreilisun.swipedismissdialog.SwipeDismissDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class PerhitunganPondasi extends AppCompatActivity {
    private ImageView tambahpondasi,print;
    private TextView totalharga;
    int ProyekID;
    int totalPrice1=0;
    String Ju,mId;
    SessionManager sm;
    String token;
    String stringid;
    private Pondasi_adapter pondasi_adapter;
    private List<Perhitunganpondasi1> PondasiModel = new ArrayList<>();
    private RecyclerView mRecyclerView;
    Context mContext;
    SwipeRefreshLayout swipeRefreshLayout;
    SwipeDismissDialog swipeDismissDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitungan_pondasi);
        totalharga = findViewById(R.id.totalpondasi);
        tambahpondasi = findViewById(R.id.tambahpondasi);
        swipeRefreshLayout = findViewById(R.id.swiperf);
        sm= new SessionManager(PerhitunganPondasi.this);
        mRecyclerView = findViewById(R.id.rv_pondasi);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        stringid=(map.get(sm.KEY_ID));
        sm.checkLogin();
        Bundle bundle = getIntent().getExtras();
        mId = bundle.getString("idproyek");

        Toast.makeText(PerhitunganPondasi.this, "Proyek Id"+mId,
                Toast.LENGTH_SHORT).show();
        Ju = mId;
        mContext = this;
        getperhitunganpondasi();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                finish();
                startActivity(getIntent());
                customType(PerhitunganPondasi.this,"fadein-to-fadeout");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        tambahpondasi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent tambahpondasi = (new Intent(PerhitunganPondasi.this, Tambahpondasi.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek1",mId);
                tambahpondasi.putExtras(setData);
                startActivity(tambahpondasi);

            }
        });
    }

    private void getperhitunganpondasi() {
        String apiKey = "oa00000000app";
        if (!TextUtils.isEmpty(Ju) && TextUtils.isDigitsOnly(Ju)) {
            ProyekID = Integer.parseInt(Ju);
        } else {
            ProyekID =0;
        }


        Call<ResponsePondasi> call = ApiClient.getRequestInterface().getPerhitunganpondasi(ProyekID,apiKey,token);
        call.enqueue(new Callback<ResponsePondasi>() {
            @Override
            public void onResponse(Call<ResponsePondasi> call, Response<ResponsePondasi> response) {
//                swipeRefreshLayout.setRefreshing(false);
//                String message = response.body().getMessage();
                if (response.code() == 200 ) {
                    PondasiModel = response.body().getPerhitunganpondasi();

                    for (int i = 0; i<PondasiModel.size(); i++) {
                        totalPrice1 += PondasiModel.get(i).getHargatotal();
                    }
                    DecimalFormat formatter = new DecimalFormat("#,###.##");
                    String totalbiaya = formatter.format(totalPrice1);
                    totalharga.setText("Rp."+totalbiaya);

                    pondasi_adapter = new Pondasi_adapter(PerhitunganPondasi.this, PondasiModel);
                    mRecyclerView.setAdapter(pondasi_adapter);

                } else if (response.code() == 422) {
                    Toast.makeText(PerhitunganPondasi.this, "Something wrong!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePondasi> call, Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(PerhitunganPondasi.this, "Oops! Something went wrong!" + t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

    }
