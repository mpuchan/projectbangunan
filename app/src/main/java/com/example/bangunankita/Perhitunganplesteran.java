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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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

public class Perhitunganplesteran extends AppCompatActivity {
    ImageView imageples;
    String mId,Ju,token,stringid;
    ImageView rincian,panduanplesteran;
    int ProyekID;
    Context mContext;
    String nama1;
    SessionManager sm;
    private Plesteran_adapter plesteran_adapter;
    private List<Perhitunganplesteran1> PlesteranModel = new ArrayList<>();
    private RecyclerView mRecyclerView;
    TextView totalplester;
    ProgressDialog pd;
    int totalPrice = 0;
    SwipeRefreshLayout swipeRefreshLayout;
    SwipeDismissDialog swipeDismissDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitunganplesteran);
        imageples = findViewById(R.id.tambahplester);
        rincian = findViewById(R.id.rincianplesteran);
        pd = new ProgressDialog(this);
        pd.setMessage("loading");
        nama1 = "Panduan Perhitungan Plesteran";
        pd.show();
        panduanplesteran = findViewById(R.id.panduanplesteran);
        sm= new SessionManager(Perhitunganplesteran.this);
        swipeRefreshLayout = findViewById(R.id.swiperf);
        mRecyclerView = findViewById(R.id.rv_plesteran);
        totalplester = findViewById(R.id.totalplester);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        stringid=(map.get(sm.KEY_ID));
        sm.checkLogin();
        Bundle bundle = getIntent().getExtras();
        mId = bundle.getString("idproyek");


//        Toast.makeText(Perhitunganplesteran.this, "Proyek Id"+mId,
//                Toast.LENGTH_SHORT).show();
        Ju = mId;
        mContext = this;
        getperhitunganplesteran();

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
        panduanplesteran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent panduan = (new Intent(Perhitunganplesteran.this, Panduanapl.class)
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
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                finish();
                startActivity(getIntent());
                customType(Perhitunganplesteran.this,"fadein-to-fadeout");
                swipeRefreshLayout.setRefreshing(false);
            }
        });
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
                    pd.hide();
                    PlesteranModel = response.body().getPerhitunganplesteran();

                    for (int i = 0; i<PlesteranModel.size(); i++) {
                        totalPrice += PlesteranModel.get(i).getHargatotal();
                    }
                    DecimalFormat formatter = new DecimalFormat("#,###.##");
                    String totalbiaya = formatter.format(totalPrice);
                    totalplester.setText("Rp."+totalbiaya);

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
