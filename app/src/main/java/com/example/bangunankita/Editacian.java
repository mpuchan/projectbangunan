package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangunankita.Model.Material;
import com.example.bangunankita.Model.Perhitunganbidang1;
import com.example.bangunankita.Model.ResponseBidang;
import com.example.bangunankita.Model.ResponseMaterial;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Bidang_adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Editacian extends AppCompatActivity {
    Spinner spindinding,spinsemen,campuran;
    EditText panjang,tinggi,sisi,hargasemen,luas;
    TextView hasilse,hasilse1,hasilse2,hasilb,hasilvol;
    Button prosesbtn,hitung;
    float hasil,luasba,hargasementot,psemen;
    Context mContext;
    SessionManager sm;
    String token,nama,p,t,namapengerjaan,namasemen1,namapenger,idperhitunganacian,namasemen,panjang1,tinggi1,hargasemen1;
    String mId,Ju;
    int ProyekID,idacian;
    public String hs,ids,hp,idp,berats,pc,pp;
    private List<Perhitunganbidang1> BidangModel = new ArrayList<>();
    private List<Material> Semen = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editacian);
        init();
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
            }
        });
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String luas1 = luas.getText().toString().trim();
                luasba = Float.parseFloat(luas1);
                hitungsemen();
            }
        });
        spindinding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                if (selectedName.equalsIgnoreCase(namapenger)){
                    namapengerjaan = namapenger;
                    panjang.setText(panjang1);
                    tinggi.setText(tinggi1);
//                    Toast.makeText(mContext, "if jalan ", Toast.LENGTH_SHORT).show();
                }else {
                    namapengerjaan = String.valueOf(BidangModel.get(position).getNama());
                    namapenger = namapengerjaan;
                    p = String.valueOf(BidangModel.get(position).getPanjangbid());
                    t = String.valueOf(BidangModel.get(position).getTinggibid());
                    panjang.setText(p);
                    tinggi.setText(t);
//                    Toast.makeText(mContext, "if tidak jalan" + namapenger,Toast.LENGTH_SHORT).show();
                }

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
                if (selectedName.equalsIgnoreCase(namasemen)){
                    namasemen1 = namasemen;
                    berats = String.valueOf(Semen.get(position).getBerat());
                    hargasemen.setText(hargasemen1);
                }else{
                    namasemen1= String.valueOf(Semen.get(position).getNama());
                    hs = String.valueOf(Semen.get(position).getHarga());
                    hargasemen.setText(hs);
                    berats = String.valueOf(Semen.get(position).getBerat());

                }
//                Toast.makeText(mContext, "Kamu memilih Semen " + selectedName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initSpinnerSemen() {
        ApiClient.getRequestInterface().getallsemen().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    Semen = response.body().getMaterials();
                    int i = 0;
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namasemen;
                    for (int j = 0; j < Semen.size(); j++){
                        if (data.equalsIgnoreCase(String.valueOf(Semen.get(j).getNama()))) {
                            i = j;
                        }
                        listSpinner.add(Semen.get(j).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinsemen.setAdapter(adapter);
                    spinsemen.setSelection(i);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data Semen", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMaterial> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
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
                    int i = 0;
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namapenger;
                    for (int j =0; j < BidangModel.size();j++){
                        if (data.equalsIgnoreCase(String.valueOf(BidangModel.get(j).getNama()))) {
                            i = j;
                        }
                            listSpinner.add(String.valueOf(BidangModel.get(j).getNama()));
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spindinding.setAdapter(adapter);
                    spindinding.setSelection(i);

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
    private void hitungsemen() {
        psemen = 3.25f;
        float beratsemen = Float.parseFloat(berats);
        String hargasem = hargasemen.getText().toString().trim();
        float hargasemen = Float.parseFloat(hargasem);
        float hitpc = psemen*luasba;
        float totpc = hitpc/beratsemen;
        hargasementot = totpc*hargasemen;

        hasilse.setText(Float.toString(hitpc));
        hasilse1.setText(Float.toString(totpc));
        hasilse2.setText(Float.toString(hargasementot));
        hasilb.setText(Float.toString(hargasementot));
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mainsavebidang,menu);
        MenuItem item = menu.findItem(R.id.app_bar_savedata);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                idacian = Integer.parseInt(idperhitunganacian);
                String apiKey = "oa00000000app";
                HashMap<String, String> map = new HashMap<>();
                map.put("nama", namapenger);
                map.put("jenis_pengerjaan", "bangunan");
                map.put("panjangdin", panjang.getText().toString());
                map.put("tinggidin", tinggi.getText().toString());
                map.put("sisi", sisi.getText().toString());
                map.put("luas", luas.getText().toString());
                map.put("nama_semen", namasemen1);
                map.put("Jumlahkeperluansemen", hasilse.getText().toString());
                map.put("jumlahdalamsak", hasilse1.getText().toString());
                map.put("metode", String.valueOf(psemen));
                map.put("hargasemen", hargasemen.getText().toString());
                map.put("hargasementotal", hasilse2.getText().toString());
                map.put("hargatotal", hasilse2.getText().toString());
                Call<Void> call = ApiClient.getRequestInterface().actionPutPerhitunganacian(idacian,apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {

                            Intent perhitunganacian = (new Intent(Editacian.this, Perhitunganacian.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            Bundle setData = new Bundle();
                            setData.putString("idproyek",mId);
                            perhitunganacian.putExtras(setData);
                            startActivity(perhitunganacian);
                            Toast.makeText(Editacian.this,
                                    "Data Perhitungan Acian Berhasil di Update",
                                    Toast.LENGTH_LONG).show();

                        } else if (response.code() == 422) {
                            Toast.makeText(Editacian.this,
                                    "Something Wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Editacian.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }
    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;
        spinsemen = findViewById(R.id.spinsemen);
        spindinding = findViewById(R.id.spindinding);
        panjang = findViewById(R.id.panjangpl);
        tinggi = findViewById(R.id.tinggipl);
        sisi = findViewById(R.id.sisi);
        hargasemen = findViewById(R.id.hargasemen);
        luas = findViewById(R.id.luas);
        hasilvol = findViewById(R.id.hasilvol);
        hasilse = findViewById(R.id.hasilsemen);
        hasilse1 = findViewById(R.id.hasilsemensak);
        hasilse2 = findViewById(R.id.hasilsemen2);
        hasilb = findViewById(R.id.totalbiaya);
        prosesbtn = findViewById(R.id.prosesbtn);
        hitung = findViewById(R.id.hitungb);

        sm= new SessionManager(Editacian.this);
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        sm.checkLogin();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            mId = bundle.getString("ProyekId");
            Ju = mId;
        }else{
            mId = "0";
        }
        Toast.makeText(Editacian.this, "Proyek Id"+mId,
                Toast.LENGTH_SHORT).show();

        idperhitunganacian= bundle.getString("id");
        namapenger=(bundle.getString("nama"));
        bundle.getString("jenis_pengerjaan");
        panjang1 =bundle.getString("panjangdin");
        tinggi1 =bundle.getString("tinggidin");
        sisi.setText(bundle.getString("sisi"));
        luas.setText(bundle.getString("luas_acian"));
        hasilse.setText(bundle.getString("Jumlahkeperluansemen"));
        hasilse1.setText(bundle.getString("jumlahdalamsak"));
        hargasemen1=bundle.getString("hargasemen");
        namasemen= bundle.getString("nama_semen");
        hasilse2.setText(bundle.getString("hargasementotal"));
        hasilvol.setText(bundle.getString("luas_acian"));


    }

}
