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

import com.example.bangunankita.Model.Perhitunganlantai1;
import com.example.bangunankita.Model.Perhitunganplesteran1;
import com.example.bangunankita.Model.ResponseLantai;
import com.example.bangunankita.Model.ResponsePlesteran;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Lantai_adapter;
import com.example.bangunankita.adapter.Plesteran_adapter;
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

public class Perhitunganlantai extends AppCompatActivity {
    private ImageView tambahkeramik,print;
    private TextView totalharga;
    private ImageView rincian,panduanlantai;
    int ProyekID;
    String Ju,mId;
    SessionManager sm;
    private Lantai_adapter lantai_adapter;
    private List<Perhitunganlantai1> LantaiModel = new ArrayList<>();
    private RecyclerView mRecyclerView;
    String token;
    ProgressDialog pd;
    String stringid;
    String nama1;
    int totalPrice = 0;
    SwipeRefreshLayout swipeRefreshLayout;
    SwipeDismissDialog swipeDismissDialog;


    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitunganlantai);
        tambahkeramik = findViewById(R.id.tambahlantai);
        totalharga = findViewById(R.id.totallantai);
        pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();
        nama1 = "Panduan Perhitungan Lantai";
        swipeRefreshLayout = findViewById(R.id.swiperf);
        rincian = findViewById(R.id.rincianlantai);
        panduanlantai = findViewById(R.id.panduanlantai);
        sm= new SessionManager(Perhitunganlantai.this);
        mRecyclerView = findViewById(R.id.rv_lantai);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        stringid=(map.get(sm.KEY_ID));
        sm.checkLogin();
        Bundle bundle = getIntent().getExtras();
        mId = bundle.getString("idproyek");
        Ju = mId;
        mContext = this;
        getperhitunganlantai();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                finish();
                startActivity(getIntent());
                customType(Perhitunganlantai.this,"fadein-to-fadeout");
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

        panduanlantai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent panduan = (new Intent(Perhitunganlantai.this, Panduanapl.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.luasplafon);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                setData.putString("ini",nama1);
                panduan.putExtra("picture", byteArray);
                panduan.putExtras(setData);
                startActivity(panduan);
            }
        });
        tambahkeramik.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent tambahlantai = (new Intent(Perhitunganlantai.this, Tambahlantai.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek1",mId);
                tambahlantai.putExtras(setData);
                startActivity(tambahlantai);
            }
        });
    }

    private void getperhitunganlantai() {
        String apiKey = "oa00000000app";
        if (!TextUtils.isEmpty(Ju) && TextUtils.isDigitsOnly(Ju)) {
            ProyekID = Integer.parseInt(Ju);
        } else {
            ProyekID =0;
        }

        Call<ResponseLantai> call = ApiClient.getRequestInterface().getPerhitunganlantai(ProyekID,apiKey,token);
        call.enqueue(new Callback<ResponseLantai>() {
            @Override
            public void onResponse(Call<ResponseLantai> call, Response<ResponseLantai> response) {
//                swipeRefreshLayout.setRefreshing(false);
//                String message = response.body().getMessage();
                if (response.code() == 200 ) {
                    pd.hide();
                    LantaiModel = response.body().getPerhitunganlantai();

                    for (int i = 0; i<LantaiModel.size(); i++) {
                        totalPrice += LantaiModel.get(i).getHargatotal();
                    }
                    DecimalFormat formatter = new DecimalFormat("#,###.##");
                    String totalbiaya = formatter.format(totalPrice);
                    totalharga.setText("Rp."+totalbiaya);

                    lantai_adapter = new Lantai_adapter(Perhitunganlantai.this, LantaiModel);
                    mRecyclerView.setAdapter(lantai_adapter);

                } else if (response.code() == 422) {
                    Toast.makeText(Perhitunganlantai.this, "Something wrong!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLantai> call, Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Perhitunganlantai.this, "Oops! Something went wrong!" + t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
}
