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
import com.example.bangunankita.Model.Perhitunganpengecatan1;
import com.example.bangunankita.Model.ResponsePengecatan;
import com.example.bangunankita.Model.ResponsePlesteran;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Lantai_adapter;
import com.example.bangunankita.adapter.Pengecatan_adapter;
import com.example.bangunankita.adapter.Plesteran_adapter;
import com.github.andreilisun.swipedismissdialog.SwipeDismissDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class Perhitunganpengecatan extends AppCompatActivity {
    private ImageView tambahpengecatan,print;
    private TextView totalharga;
    int ProyekID;
    String Ju,mId;
    SessionManager sm;
    int totalPrice=0;
    private Pengecatan_adapter pengecatan_adapter;
    private List<Perhitunganpengecatan1> PengecatanModel = new ArrayList<>();
    private RecyclerView mRecyclerView;
    String token;
    String stringid;
    Context mContext;
    SwipeRefreshLayout swipeRefreshLayout;
    SwipeDismissDialog swipeDismissDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitunganpengecatan);
        tambahpengecatan = findViewById(R.id.tambahcat);
        totalharga = findViewById(R.id.totalpengecatan);
        swipeRefreshLayout = findViewById(R.id.swiperf);
        sm= new SessionManager(Perhitunganpengecatan.this);
        mRecyclerView = findViewById(R.id.rv_pengecatan);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        stringid=(map.get(sm.KEY_ID));
        sm.checkLogin();
        Bundle bundle = getIntent().getExtras();
        mId = bundle.getString("idproyek");

        Toast.makeText(Perhitunganpengecatan.this, "Proyek Id"+mId,
                Toast.LENGTH_SHORT).show();
        Ju = mId;
        mContext = this;
        getperhitunganpengecatan();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                finish();
                startActivity(getIntent());
                customType(Perhitunganpengecatan.this,"fadein-to-fadeout");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        tambahpengecatan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent tambahpengecatan = (new Intent(Perhitunganpengecatan.this, Tambahpengecatan.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek1",mId);
                tambahpengecatan.putExtras(setData);

                startActivity(tambahpengecatan);

            }
        });
    }

    private void getperhitunganpengecatan() {
        String apiKey = "oa00000000app";
        if (!TextUtils.isEmpty(Ju) && TextUtils.isDigitsOnly(Ju)) {
            ProyekID = Integer.parseInt(Ju);
        } else {
            ProyekID =0;
        }


        Call<ResponsePengecatan> call = ApiClient.getRequestInterface().getPerhitunganpengecatan(ProyekID,apiKey,token);
        call.enqueue(new Callback<ResponsePengecatan>() {
            @Override
            public void onResponse(Call<ResponsePengecatan> call, Response<ResponsePengecatan> response) {
//                swipeRefreshLayout.setRefreshing(false);
//                String message = response.body().getMessage();
                if (response.code() == 200 ) {
                    PengecatanModel = response.body().getPerhitunganpengecatan();

                    for (int i = 0; i<PengecatanModel.size(); i++) {
                        totalPrice += PengecatanModel.get(i).getHargatotal();
                    }
                    DecimalFormat formatter = new DecimalFormat("#,###.##");
                    String totalbiaya = formatter.format(totalPrice);
                    totalharga.setText("Rp."+totalbiaya);

                    pengecatan_adapter = new Pengecatan_adapter(Perhitunganpengecatan.this, PengecatanModel);
                    mRecyclerView.setAdapter(pengecatan_adapter);

                } else if (response.code() == 422) {
                    Toast.makeText(Perhitunganpengecatan.this, "Something wrong!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePengecatan> call, Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Perhitunganpengecatan.this, "Oops! Something went wrong!" + t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });

    }
}
