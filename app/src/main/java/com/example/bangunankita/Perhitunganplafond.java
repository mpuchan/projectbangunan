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

import com.example.bangunankita.Model.Perhitunganpengecatan1;
import com.example.bangunankita.Model.Perhitunganplafon1;
import com.example.bangunankita.Model.ResponsePengecatan;
import com.example.bangunankita.Model.ResponsePlafon;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Pengecatan_adapter;
import com.example.bangunankita.adapter.Plafond_adapter;
import com.github.andreilisun.swipedismissdialog.SwipeDismissDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class Perhitunganplafond extends AppCompatActivity {
    private ImageView tambahplafond,print;
    private TextView totalharga;
    int ProyekID;
    String Ju,mId;
    SessionManager sm;
    String token;
    int totalPrice = 0;
    String stringid;
    private Plafond_adapter plafond_adapter;
    private List<Perhitunganplafon1> PlafonModel = new ArrayList<>();
    private RecyclerView mRecyclerView;
    Context mContext;
    SwipeRefreshLayout swipeRefreshLayout;
    SwipeDismissDialog swipeDismissDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitunganplafond);
        tambahplafond = findViewById(R.id.tambahplafon);
        totalharga = findViewById(R.id.totalseluruh);
        swipeRefreshLayout = findViewById(R.id.swiperf);
        sm= new SessionManager(Perhitunganplafond.this);
        mRecyclerView = findViewById(R.id.rv_plafond);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        stringid=(map.get(sm.KEY_ID));
        sm.checkLogin();
        Bundle bundle = getIntent().getExtras();
        mId = bundle.getString("idproyek");
        Ju = mId;
        mContext = this;
        getperhitunganplafon();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                finish();
                startActivity(getIntent());
                customType(Perhitunganplafond.this,"fadein-to-fadeout");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        tambahplafond.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent tambahplafond = (new Intent(Perhitunganplafond.this, Tambahplafon.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek1",mId);
                tambahplafond.putExtras(setData);

                startActivity(tambahplafond);

            }
        });
        
    }

    private void getperhitunganplafon() {
        String apiKey = "oa00000000app";
        if (!TextUtils.isEmpty(Ju) && TextUtils.isDigitsOnly(Ju)) {
            ProyekID = Integer.parseInt(Ju);
        } else {
            ProyekID =0;
        }


        Call<ResponsePlafon> call = ApiClient.getRequestInterface().getPerhitunganplafon(ProyekID,apiKey,token);
        call.enqueue(new Callback<ResponsePlafon>() {
            @Override
            public void onResponse(Call<ResponsePlafon> call, Response<ResponsePlafon> response) {
//                swipeRefreshLayout.setRefreshing(false);
//                String message = response.body().getMessage();
                if (response.code() == 200 ) {
                    PlafonModel = response.body().getPerhitunganplafon();

                    for (int i = 0; i<PlafonModel.size(); i++) {
                        totalPrice += PlafonModel.get(i).getHargatotal();
                    }
                    DecimalFormat formatter = new DecimalFormat("#,###.##");
                    String totalbiaya = formatter.format(totalPrice);
                    totalharga.setText("Rp."+totalbiaya);

                    plafond_adapter = new Plafond_adapter(Perhitunganplafond.this, PlafonModel);
                    mRecyclerView.setAdapter(plafond_adapter);

                } else if (response.code() == 422) {
                    Toast.makeText(Perhitunganplafond.this, "Pastikan seluruh data terisi dengan benar!!!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePlafon> call, Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Perhitunganplafond.this, "Koneksi Anda Bermasalah" + t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
}
