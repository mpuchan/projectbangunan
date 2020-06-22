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
import com.example.bangunankita.Model.Material;
import com.example.bangunankita.Model.Perhitunganbidang1;
import com.example.bangunankita.Model.ResponseBidang;
import com.example.bangunankita.Model.ResponseMaterial;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Campuran_adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tambahplesteran extends AppCompatActivity {
    Spinner spindinding,spinsemen,spinpasir,campuran;
    EditText panjang,tinggi,tebal,sisi,hargasemen,hargapasir,volume;
    TextView hasilpas,hasilpas1,hasilpas2,hasilse,hasilse1,hasilse2,hasilb;
    Button prosesbtn,hitung;
    float hasil,luasba,totpasir,hargasementot;
    Context mContext;
    SessionManager sm;
    String token,nama,p,t;
    int ProyekID;

    String mId,Ju;
    public String hs,ids,hp,idp,berats,pc,pp;
    private List<Material> Semen = new ArrayList<>();
    private List<Material> Pasir = new ArrayList<>();
    private List<Perhitunganbidang1> BidangModel = new ArrayList<>();
    Campuran[] campurans ={
            new Campuran("1:1", 15.504,0.016),
            new Campuran("1:2", 10.224, 0.020),
            new Campuran("1:3", 7.776,0.023),
            new Campuran("1:4", 6.240,0.024),
            new Campuran("1:5", 5.184,0.026),
            new Campuran("1:6", 4.416,0.027),
            new Campuran("1:7", 3.936,0.028),
            new Campuran("1:8", 3.456,0.029)
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahplesteran);
        mContext = this;
        prosesbtn = findViewById(R.id.prosesbtn);
        spindinding = findViewById(R.id.spindinding);
        spinsemen = findViewById(R.id.spinsemen);
        spinpasir = findViewById(R.id.spinpasir);
        panjang = findViewById(R.id.panjangpl);
        tinggi = findViewById(R.id.tinggipl);
        tebal = findViewById(R.id.tebal);
        sisi = findViewById(R.id.sisi);
        hargasemen = findViewById(R.id.hargasemen);
        hasilpas = findViewById(R.id.hasilpasir);
        hasilpas1 = findViewById(R.id.hasilpasirtruk);
        hasilpas2 = findViewById(R.id.hasilhargap);
        hargapasir = findViewById(R.id.hargapasir);
        hasilse = findViewById(R.id.hasilsemen);
        hasilse1 = findViewById(R.id.hasilsemensak);
        hasilse2 = findViewById(R.id.hasilsemen2);
        hasilb = findViewById(R.id.totalbiaya);
        volume = findViewById(R.id.volume);
        hitung = findViewById(R.id.hitungb);
        campuran = findViewById(R.id.spinner22);
        sm= new SessionManager(Tambahplesteran.this);
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
        Toast.makeText(Tambahplesteran.this, "Proyek Id"+mId,
                Toast.LENGTH_SHORT).show();

        Campuran_adapter campuran_adapter =
                new Campuran_adapter(Tambahplesteran.this,
                        android.R.layout.simple_spinner_item, campurans);
        campuran.setAdapter(campuran_adapter);

        initSpinnerDinding();
        initSpinnerSemen();
        initSpinnerPasir();
        prosesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String panjang1 = panjang.getText().toString().trim();
                String tinggi1 = tinggi.getText().toString().trim();
                String tebal1 = tebal.getText().toString().trim();
                String sisi1 = sisi.getText().toString().trim();

                float pp = Float.parseFloat(panjang1);
                float tp = Float.parseFloat(tinggi1);
                float tebal = Float.parseFloat(tebal1);
                float sisi = Float.parseFloat(sisi1);

                hasil = pp*tp*tebal*sisi;
                volume.setText(String.valueOf(hasil));

            }
        });

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String luas = volume.getText().toString().trim();
                luasba = Float.parseFloat(luas);
                hitungsemen();
                hitungpasir();
                hitungtot();
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
        spinpasir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                hp = String.valueOf(Pasir.get(position).getHarga());
                idp = String.valueOf(Pasir.get(position).getId());

                hargapasir.setText(hp);

//                Toast.makeText(mContext, "Kamu memilih Pasir " + selectedName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        campuran.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Campuran obj = (Campuran) (parent.getItemAtPosition(position));
                pp = String.valueOf(obj.getPp());
                pc = String.valueOf(obj.getPc());

//                Toast.makeText(mContext, "Kamu memilih Campuran " + pc, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void hitungtot() {
        float tothitung = hargasementot+totpasir;
        hasilb.setText(String.valueOf(tothitung));
    }

    private void hitungpasir() {
        float ppasir = Float.parseFloat(pp);
        float hargapas = Float.parseFloat(hp);
        float hitpp = ppasir*luasba;
//        float pembulatanpasir = (float) Math.ceil(hitpp);
        totpasir = hitpp*hargapas;
        Toast.makeText(mContext, "Gagal mengambil data"+ppasir, Toast.LENGTH_SHORT).show();
        hasilpas2.setText(Float.toString(totpasir));
        hasilpas1.setText(Float.toString(hitpp));
        hasilpas.setText(Float.toString(hitpp));
    }

    private void hitungsemen() {
        float psemen = Float.parseFloat(pc);
        float beratsemen = Float.parseFloat(berats);
        float hargasemen = Float.parseFloat(hs);
        float hitpc = psemen*luasba;
        float totpc = hitpc/beratsemen;
        hargasementot = totpc*hargasemen;

        hasilse.setText(Float.toString(hitpc));
        hasilse1.setText(Float.toString(totpc));
        hasilse2.setText(Float.toString(hargasementot));
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
                        listSpinner.add(String.valueOf(BidangModel.get(i).getNama()));
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


    private void initSpinnerSemen() {
        ApiClient.getRequestInterface().getallsemen().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    Semen = response.body().getMaterials();
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
            public void onFailure(Call<ResponseMaterial> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initSpinnerPasir() {
        ApiClient.getRequestInterface().getallpasir().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    Pasir = response.body().getMaterials();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < Pasir.size(); i++) {
                        listSpinner.add(Pasir.get(i).getNama());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinpasir.setAdapter(adapter);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data Batako", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseMaterial> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
