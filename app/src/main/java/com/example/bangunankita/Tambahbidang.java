package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangunankita.Model.Campuran;
import com.example.bangunankita.Model.Material;
import com.example.bangunankita.Model.ResponseBidang;
import com.example.bangunankita.Model.ResponseMaterial;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Campuran_adapter;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tambahbidang extends AppCompatActivity {
    private EditText panjangb,tinggib,panjangp,tinggip,panjangj,tinggij,luasb;
    private EditText hargab,hargas,hargap,namapengerjaan;
    private TextView hasilb,hasilbid,hasilbat,hasilbat1,hasilse,hasilse1,
            hasilse2,hasilpas,hasilpas1,hasilpas2;
    private Button btnproses,hitung;
    private Spinner spinbatako,campuran,spinsemen,spinpasir;
    public String pbatako,tbatako,hb,idb,hs,ids,hp,idp,berats,pc,pp,token,metode,mId;
    float hasilm,luasba;
    float totpasir;
    float htotbatako;
    float hargasementot,hargapasirparse,hargasemenparse,hargabatakoparse;
    public float hasil;
    SessionManager sm;
    private List<Material> Batako = new ArrayList<>();
    private List<Material> Semen = new ArrayList<>();
    private List<Material> Pasir = new ArrayList<>();
    Context mContext;
    Campuran[] campurans ={
            new Campuran("1:3", 3,0.007),
            new Campuran("1:4", 2.4, 0.0075),
            new Campuran("1:5", 2.02,0.0079),
            new Campuran("1:6", 1.74,0.0086),
            new Campuran("1:8", 1.36,0.009)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahbidang);
        init();
        initSpinnerBatako();
        initSpinnerSemen();
        initSpinnerPasir();

        btnproses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String panjang1 = panjangb.getText().toString().trim();
                String panjang2 = panjangp.getText().toString().trim();
                String panjang3 = panjangj.getText().toString().trim();
                String tinggi1 = tinggib.getText().toString().trim();
                String tinggi2 = tinggip.getText().toString().trim();
                String tinggi3 = tinggij.getText().toString().trim();

                    float pb = Float.parseFloat(panjang1);
                    float tb = Float.parseFloat(tinggi1);
                    float pp = Float.parseFloat(panjang2);
                    float tp = Float.parseFloat(tinggi2);
                    float pj = Float.parseFloat(panjang3);
                    float tj = Float.parseFloat(tinggi3);
                    hasil = (pb*tb)-(pp*tp)- (pj*tj);
                    luasb.setText(String.valueOf(hasil));
                    String luas = luasb.getText().toString().trim();
                    luasba = Float.parseFloat(luas);

            }
        });


        spinbatako.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                hb = String.valueOf(Batako.get(position).getHarga());
                idb = String.valueOf(Batako.get(position).getId());
                pbatako = String.valueOf(Batako.get(position).getPanjang());
                tbatako = String.valueOf(Batako.get(position).getTinggi());
                hargab.setText(hb);

                Toast.makeText(mContext, "Kamu memilih Batako " + selectedName, Toast.LENGTH_SHORT).show();
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
                hargas.setText(hs);

                Toast.makeText(mContext, "Kamu memilih Semen " + selectedName, Toast.LENGTH_SHORT).show();
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

                hargap.setText(hp);

                Toast.makeText(mContext, "Kamu memilih Pasir " + selectedName, Toast.LENGTH_SHORT).show();
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
              metode = String.valueOf(obj.getCampuran());

                Toast.makeText(mContext, "Kamu memilih Campuran " + pc, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hargapasir = hargap.getText().toString().trim();
                hargapasirparse = Float.parseFloat(hargapasir);
                hasilbid.setText(String.valueOf(hasil));
                String hargasemen = hargas.getText().toString().trim();
                hargasemenparse = Float.parseFloat(hargasemen);
                String hargabatako = hargab.getText().toString().trim();
                hargabatakoparse =Float.parseFloat(hargabatako);
                hitungbatako();
                hitungsemen();
                hitungpasir();
                hitungtot();
//

            }
        });
    }

    private void hitungtot() {
       float tothitung = htotbatako+hargasementot+totpasir;
        hasilb.setText(String.valueOf(tothitung));
    }

    private void hitungpasir() {
        float ppasir = Float.parseFloat(pp);
        float hitpp = ppasir*luasba;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        float d = hitpp;
        String n = df.format(d);
        String result = null;
        result = n.replace(",",".");
        float hitungp = Float.parseFloat(result);
        totpasir = hitungp*hargapasirparse;

        float pembulatanhargatotpasir = (float) Math.ceil(totpasir);
        Toast.makeText(mContext, "Gagal mengambil data"+ppasir, Toast.LENGTH_SHORT).show();
        hasilpas2.setText(Float.toString(pembulatanhargatotpasir));
        hasilpas1.setText(Float.toString(hitungp));
        hasilpas.setText(Float.toString(hitungp));
    }

    private void hitungsemen() {
        float psemen = Float.parseFloat(pc);
        float beratsemen = Float.parseFloat(berats);
        float hitpc = psemen*luasba;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        float d = hitpc;
        String n = df.format(d);
        String result = null;
        result = n.replace(",",".");
        float hitungs = Float.parseFloat(result);
        float totpc = hitungs/beratsemen;
        hargasementot = totpc*hargasemenparse;

        hasilse.setText(Float.toString(hitungs));
        hasilse1.setText(Float.toString(totpc));
        hasilse2.setText(Float.toString(hargasementot));
    }

    private void hitungbatako() {
        float m = 100;
        float p = Float.parseFloat(pbatako);
        float t = Float.parseFloat(tbatako);
        hasilm = m/(p*t)*m;
        float tot = hasilm*luasba;
        htotbatako = tot*hargabatakoparse;
        hasilbat.setText(Float.toString(tot));
        hasilbat1.setText(Float.toString(htotbatako));
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
                    for (int i = 0; i < Pasir.size(); i++){
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

    private void initSpinnerBatako() {
        ApiClient.getRequestInterface().getallbatako().enqueue(new Callback<ResponseMaterial>() {
            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    Batako = response.body().getMaterials();

                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < Batako.size(); i++){
                        listSpinner.add(String.valueOf(Batako.get(i).getNama()));


                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinbatako.setAdapter(adapter);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mainsavebidang,menu);
        MenuItem item = menu.findItem(R.id.app_bar_savedata);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String apiKey = "oa00000000app";
                HashMap<String, String> map = new HashMap<>();
                map.put("ProyekId", mId);
                map.put("nama", namapengerjaan.getText().toString());
                map.put("jenis_pengerjaan", "bangunan");
                map.put("panjangbid", panjangb.getText().toString());
                map.put("tinggibid", tinggib.getText().toString());
                map.put("panjangpin", panjangp.getText().toString());
                map.put("tinggipin", tinggip.getText().toString());
                map.put("panjangjen", panjangj.getText().toString());
                map.put("tinggijen", tinggij.getText().toString());
                map.put("luas_bidang", luasb.getText().toString());
                map.put("jumlahkeperluanbatako", hasilbat.getText().toString());
                map.put("jumlahkeperluanpasir", hasilpas1.getText().toString());
                map.put("Jumlahkeperluansemen", hasilse.getText().toString());
                map.put("jumlahdalamsak", hasilse1.getText().toString());
                map.put("metode", metode);
                map.put("hargabatako", hasilbat1.getText().toString());
                map.put("hargapasir", hasilpas2.getText().toString());
                map.put("hargasemen", hasilse2.getText().toString());
                Call<Void> call = ApiClient.getRequestInterface().actionCreatebidang(apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            Intent Perhitunganbidang = new Intent(Tambahbidang.this, Perhitunganbidang.class);
                            startActivity(Perhitunganbidang);
                            Toast.makeText(Tambahbidang.this,
                                    "Tambah Data Perhitungan Bidang Berhasil",
                                    Toast.LENGTH_LONG).show();

                        } else if (response.code() == 422) {
                            Toast.makeText(Tambahbidang.this,
                                    "Something Wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Tambahbidang.this, t.getMessage(),
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
        sm= new SessionManager(Tambahbidang.this);
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        sm.checkLogin();
        spinbatako = findViewById(R.id.spinbata);
        spinsemen = findViewById(R.id.spinsemen);
        spinpasir = findViewById(R.id.spinpasir);
        panjangb = findViewById(R.id.panjangb);
        panjangp = findViewById(R.id.ppintu);
        panjangj = findViewById(R.id.panjangj);
        tinggib= findViewById(R.id.tinggib);
        tinggij= findViewById(R.id.tinggij);
        hargab= findViewById(R.id.hbata);
        hargap= findViewById(R.id.hargapasir);
        hargas= findViewById(R.id.hargasemen);
        tinggip = findViewById(R.id.tpintu);
        luasb = findViewById(R.id.luasbidang);
        hitung = findViewById(R.id.hitungb);
        campuran = findViewById(R.id.spinner22);
        hasilbat = findViewById(R.id.hasilbat);
        hasilbat1 = findViewById(R.id.hasilbat1);
        hasilpas = findViewById(R.id.hasilp2);
        hasilpas1 =  findViewById(R.id.hasilp);
        hasilpas2 = findViewById(R.id.hasilp1);
        hasilse = findViewById(R.id.hasils);
        hasilse1 = findViewById(R.id.hasils1);
        hasilse2 = findViewById(R.id.hasils2);
        hasilbid = findViewById(R.id.hasilbid);
        hasilb = findViewById(R.id.total);
        namapengerjaan = findViewById(R.id.nama_pengerjaan);
        Campuran_adapter campuran_adapter =
                new Campuran_adapter(Tambahbidang.this,
                        android.R.layout.simple_spinner_item, campurans);
        campuran.setAdapter(campuran_adapter);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            mId = bundle.getString("idproyek1");
        }else{
            mId = "0";
        }
        float luas;
        btnproses = findViewById(R.id.prosesbtn);
    }

}
