package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangunankita.Model.Campuran;
import com.example.bangunankita.Model.Perhitunganbidang1;
import com.example.bangunankita.Model.ResponseBidang;
import com.example.bangunankita.Model.ResponseSemen;
import com.example.bangunankita.Model.Semen;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tambahacian extends AppCompatActivity {
    Spinner spindinding,spinsemen,campuran;
    EditText panjang,tinggi,sisi,hargasemen,luas;
    TextView hasilse,hasilse1,hasilse2,hasilb;
    Button prosesbtn,hitung;
    float hasil,luasba,totpasir,hargasementot;
    Context mContext;
    SessionManager sm;
    String token,nama,p,t;
    String mId,Ju;
    int ProyekID;
    public String hs,ids,hp,idp,berats,pc,pp;
    private List<Perhitunganbidang1> BidangModel = new ArrayList<>();
    private List<com.example.bangunankita.Model.Semen> Semen = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahacian);
        mContext = this;
        spinsemen = findViewById(R.id.spinsemen);
        spindinding = findViewById(R.id.spindinding);
        panjang = findViewById(R.id.panjangpl);
        tinggi = findViewById(R.id.tinggipl);
        sisi = findViewById(R.id.sisi);
        hargasemen = findViewById(R.id.hargasemen);
        luas = findViewById(R.id.luas);
        hasilse = findViewById(R.id.hasilsemen);
        hasilse1 = findViewById(R.id.hasilsemensak);
        hasilse2 = findViewById(R.id.hasilsemen2);
        hasilb = findViewById(R.id.hasilb);
        prosesbtn = findViewById(R.id.prosesbtn);
        hitung = findViewById(R.id.hitungb);

        sm= new SessionManager(Tambahacian.this);
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        sm.checkLogin();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            mId = bundle.getString("idproyek1");
            Ju = mId;
        }else{
            mId = "0";
        }
        Toast.makeText(Tambahacian.this, "Proyek Id"+mId,
                Toast.LENGTH_SHORT).show();
        initSpinnerDinding();
        initSpinnerSemen();
        prosesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String panjang1 = panjang.getText().toString().trim();
                String tinggi1 = tinggi.getText().toString().trim();
                String sisi1 = sisi.getText().toString().trim();

                float pp = Float.parseFloat(panjang1);
                float tp = Float.parseFloat(tinggi1);
                float sisi = Float.parseFloat(sisi1);

                hasil = pp*tp*sisi;
                luas.setText(String.valueOf(hasil));
                String luas1 = luas.getText().toString().trim();
                luasba = Float.parseFloat(luas1);
            }
        });
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitungsemen();
            }
        });

        spindinding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                p = String.valueOf(BidangModel.get(position).getPanjangbid());
                t = String.valueOf(BidangModel.get(position).getTinggibid());
                panjang.setText(p);
                tinggi.setText(t);

//                Toast.makeText(mContext, "Kamu memilih Semen " + selectedName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinsemen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                berats = String.valueOf(Semen.get(position).getBerat());
                hs = String.valueOf(Semen.get(position).getHarga());
                ids = String.valueOf(Semen.get(position).getId());
                hargasemen.setText(hs);

//                Toast.makeText(mContext, "Kamu memilih Semen " + selectedName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void initSpinnerSemen() {
        ApiClient.getRequestInterface().getallsemen().enqueue(new Callback<ResponseSemen>() {

            @Override
            public void onResponse(Call<ResponseSemen> call, Response<ResponseSemen> response) {
                if (response.code() == 200) {
                    Semen = response.body().getSemens();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < Semen.size(); i++){
                        listSpinner.add(Semen.get(i).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinsemen.setAdapter(adapter);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data Batako", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseSemen> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void hitungsemen() {
        float psemen = 3.25f;
        float beratsemen = Float.parseFloat(berats);
        float hargasemen = Float.parseFloat(hs);
        float hitpc = psemen*luasba;
        float totpc = hitpc/beratsemen;
        hargasementot = totpc*hargasemen;

        hasilse.setText(Float.toString(hitpc));
        hasilse1.setText(Float.toString(totpc));
        hasilse2.setText(Float.toString(hargasementot));
        hasilb.setText(Float.toString(hargasementot));
    }
    private void initSpinnerDinding() {
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
                    if (response.code() == 200 ) {
                        BidangModel = response.body().getPerhitunganbidang();
                        List<String> listSpinner = new ArrayList<String>();
                        for (int i = 0; i < BidangModel.size(); i++){
                            listSpinner.add(String.valueOf(BidangModel.get(i).getName()));
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                                android.R.layout.simple_spinner_item, listSpinner);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spindinding.setAdapter(adapter);

                    } else {
                        Toast.makeText(mContext, "Gagal mengambil data Batako", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBidang> call, Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();

                }
            });

    }
}
