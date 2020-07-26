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
import com.example.bangunankita.Model.Perhitunganurugan1;
import com.example.bangunankita.Model.ResponseAcian;
import com.example.bangunankita.Model.ResponseUrugan;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Acian_adapter;
import com.example.bangunankita.adapter.Urugan_adapter;
import com.github.andreilisun.swipedismissdialog.SwipeDismissDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static maes.tech.intentanim.CustomIntent.customType;

public class Perhitunganurugan extends AppCompatActivity {
    ImageView imageurugan;
    String mId,Ju;
    TextView totalharga;
    SessionManager sm;
    int ProyekID;
    String token;
    String stringid;
    int totalPrice=0;
    private Urugan_adapter urugan_adapter;
    private List<Perhitunganurugan1> UruganModel = new ArrayList<>();
    private RecyclerView mRecyclerView;
    Context mContext;
    SwipeRefreshLayout swipeRefreshLayout;
    SwipeDismissDialog swipeDismissDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitunganurugan);
        imageurugan = findViewById(R.id.tambahurugan);
        totalharga = findViewById(R.id.totalurugan);
        swipeRefreshLayout = findViewById(R.id.swiperf);
        sm= new SessionManager(Perhitunganurugan.this);
        mRecyclerView = findViewById(R.id.rv_urugan);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        stringid=(map.get(sm.KEY_ID));
        sm.checkLogin();
        Bundle bundle = getIntent().getExtras();
        mId = bundle.getString("idproyek");

        Toast.makeText(Perhitunganurugan.this, "Proyek Id"+mId,
                Toast.LENGTH_SHORT).show();
        Ju = mId;
        mContext = this;
        getperhitunganurugan();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                finish();
                startActivity(getIntent());
                customType(Perhitunganurugan.this,"fadein-to-fadeout");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        imageurugan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent tambahurugan = (new Intent(Perhitunganurugan.this, Tambahurugan.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek1",mId);
                tambahurugan.putExtras(setData);

                startActivity(tambahurugan);

            }
        });
    }
    private void getperhitunganurugan() {
        String apiKey = "oa00000000app";
        if (!TextUtils.isEmpty(Ju) && TextUtils.isDigitsOnly(Ju)) {
            ProyekID = Integer.parseInt(Ju);
        } else {
            ProyekID =0;
        }

        Call<ResponseUrugan> call = ApiClient.getRequestInterface().getPerhitunganurugan(ProyekID,apiKey,token);
        call.enqueue(new Callback<ResponseUrugan>() {
            @Override
            public void onResponse(Call<ResponseUrugan> call, Response<ResponseUrugan> response) {
//                swipeRefreshLayout.setRefreshing(false);
//                String message = response.body().getMessage();
                if (response.code() == 200 ) {
                    UruganModel = response.body().getPerhitunganurugan();
                    for (int i = 0; i<UruganModel.size(); i++)
                    {
                        totalPrice += UruganModel.get(i).getHargatotal();
//                        totalbatako += BidangModel.get(i).getJumlahkeperluanbatako();
//                        totalsemen += BidangModel.get(i).getJumlahdalamsak();
//                        totalbatako += BidangModel.get(i).getJumlahkeperluanpasir();
//                        totalhargabatako += BidangModel.get(i).getHargabatakototal();
//                        totalhargapasir += BidangModel.get(i).getHargapasirtotal();
//                        totalhargasemen += BidangModel.get(i).getHargasementotal();
                    }
                    DecimalFormat formatter = new DecimalFormat("#,###.##");
                    String totalbiaya = formatter.format(totalPrice);
                    totalharga.setText("Rp."+totalbiaya);
                    urugan_adapter = new Urugan_adapter(Perhitunganurugan.this, UruganModel);
                    mRecyclerView.setAdapter(urugan_adapter);

                } else if (response.code() == 422) {
                    Toast.makeText(Perhitunganurugan.this, "Something wrong!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUrugan> call, Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Perhitunganurugan.this, "Oops! Something went wrong!" + t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

}
