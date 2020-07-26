package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
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

import com.example.bangunankita.Model.Campuran;
import com.example.bangunankita.Model.Jenispengerjaan;
import com.example.bangunankita.Model.Material;
import com.example.bangunankita.Model.Perhitunganbidang1;
import com.example.bangunankita.Model.ResponseBidang;
import com.example.bangunankita.Model.ResponseMaterial;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Campuran_adapter;
import com.example.bangunankita.adapter.Jenispengerjaan_adapter;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Editplesteran extends AppCompatActivity {
    Spinner spindinding,spinsemen,spinpasir,campuran,spinjenis;
    EditText panjang,tinggi,sisi,tebal,hargasemen,hargapasir,volume;
    TextView hasilse,hasilse1,hasilse2,hasilb,hasilpas,hasilpas1,hasilpas2,hasilvol;
    Button prosesbtn1,hitung;
    float hasil,luasba,totpasir,hargasementot;
    Context mContext;
    SessionManager sm;
    String token,nama,p,t,namapengerjaan,namasemen1,namapenger,idperhitunganplesteran,namasemen,namadinding,namapasir
    ,panjang1,jenis,tinggi1,metode,tebal1,sisi1,hargasemen1,hargapasir1,namapasir1;
    String mId,Ju;
    int ProyekID,idplesteran;
    public String hs,ids,hp,idp,berats,pc,pp;
    private List<Perhitunganbidang1> BidangModel = new ArrayList<>();
    private List<Material> Semen = new ArrayList<>();
    private List<Material> Pasir = new ArrayList<>();

    Campuran[] campurans ={
            new Campuran("1:1", 15.504,0.016,0.0),
            new Campuran("1:2", 10.224, 0.020,0.0),
            new Campuran("1:3", 7.776,0.023,0.0),
            new Campuran("1:4", 6.240,0.024,0.0),
            new Campuran("1:5", 5.184,0.026,0.0),
            new Campuran("1:6", 4.416,0.027,0.0),
            new Campuran("1:7", 3.936,0.028,0.0),
            new Campuran("1:8", 3.456,0.029,0.0)
    };
    Jenispengerjaan[] jenispengerjaans  ={
            new Jenispengerjaan("Bangunan rumah"),
            new Jenispengerjaan("Tembok pagar"),
            new Jenispengerjaan("Sekat kamar mandi"),
            new Jenispengerjaan("Sekat kamar"),
            new Jenispengerjaan("Lainnya"),
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editplesteran);
        init();
        initSpinnerDinding();
        initSpinnerSemen();
        initSpinnerPasir();
        prosesbtn1.setOnClickListener(new View.OnClickListener() {
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
        spinpasir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                if (selectedName.equalsIgnoreCase(namapasir)){
                    namapasir1 = namapasir;
                    hargapasir.setText(hargapasir1);
                }else{
                    namapasir1= String.valueOf(Pasir.get(position).getNama());
                    hp = String.valueOf(Semen.get(position).getHarga());
                    hargapasir.setText(hp);

                }

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
                metode = String.valueOf(obj.getCampuran());

//                Toast.makeText(mContext, "Kamu memilih Campuran " + pc, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinjenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Jenispengerjaan je = (Jenispengerjaan) (parent.getItemAtPosition(position));
                jenis = String.valueOf(je.getJenispengerjaan());

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
        String hargapasi = hargapasir.getText().toString().trim();
        float hargapas = Float.parseFloat(hargapasi);
        float hitpp = ppasir*luasba;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        float d = hitpp;
        String n = df.format(d);
        String result = null;
        result = n.replace(",",".");
        float hitungp = Float.parseFloat(result);
        totpasir = hitungp*hargapas;
        Toast.makeText(mContext, "Gagal mengambil data"+ppasir, Toast.LENGTH_SHORT).show();
        hasilpas2.setText(Float.toString(totpasir));
        hasilpas1.setText(Float.toString(hitungp));
        hasilpas.setText(Float.toString(hitungp));
    }

    private void hitungsemen() {
        float psemen = Float.parseFloat(pc);
        float beratsemen = Float.parseFloat(berats);
        String hargasem = hargasemen.getText().toString().trim();
        float hargasemen = Float.parseFloat(hargasem);
        float hitpc = psemen*luasba;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        float d = hitpc;
        String n = df.format(d);
        String result = null;
        result = n.replace(",",".");
        float hitungs = Float.parseFloat(result);
        float totpc = hitungs/beratsemen;
        hargasementot = totpc*hargasemen;

        hasilse.setText(Float.toString(hitungs));
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
                    int i =0;
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namapenger;
                    for (int j = 0; j < BidangModel.size(); j++){
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
                    Toast.makeText(mContext, "Gagal mengambil data Bidang", Toast.LENGTH_SHORT).show();
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
                    int i=0;
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
    private void initSpinnerPasir() {
        ApiClient.getRequestInterface().getallpasir().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    Pasir = response.body().getMaterials();
                    int i = 0;
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namapasir;
                    for (int j = 0; j < Pasir.size(); j++){
                        if (data.equalsIgnoreCase(String.valueOf(Pasir.get(j).getNama()))) {
                            i = j;
                        }
                        listSpinner.add(Pasir.get(j).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinpasir.setAdapter(adapter);
                    spinpasir.setSelection(i);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data Pasir", Toast.LENGTH_SHORT).show();
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
                idplesteran = Integer.parseInt(idperhitunganplesteran);
                String apiKey = "oa00000000app";
                HashMap<String, String> map = new HashMap<>();
                map.put("ProyekId", mId);
                map.put("nama", namapenger);
                map.put("jenis_pengerjaan", jenis);
                map.put("panjangdin", panjang.getText().toString());
                map.put("tinggidin", tinggi.getText().toString());
                map.put("tebal", tebal.getText().toString());
                map.put("sisi", sisi.getText().toString());
                map.put("volume", volume.getText().toString());
                map.put("nama_semen", namasemen1);
                map.put("nama_pasir", namapasir1);
                map.put("jumlahkeperluanpasir", hasilpas1.getText().toString());
                map.put("Jumlahkeperluansemen", hasilse.getText().toString());
                map.put("jumlahdalamsak", hasilse1.getText().toString());
                map.put("metode", metode);
                map.put("hargapasirtotal",hasilpas2.getText().toString());
                map.put("hargasementotal",hasilse2.getText().toString());
                map.put("hargapasir", hargapasir.getText().toString());
                map.put("hargasemen", hargasemen.getText().toString());
                map.put("hargatotal", hasilb.getText().toString());
                Call<Void> call = ApiClient.getRequestInterface().actionPutPerhitunganplesteran(idplesteran,apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {
                            Intent Perhitunganplesteran = (new Intent(Editplesteran.this, Perhitunganplesteran.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            Bundle setData = new Bundle();
                            setData.putString("idproyek",mId);
                            Perhitunganplesteran.putExtras(setData);
                            startActivity(Perhitunganplesteran);
                            Toast.makeText(Editplesteran.this,
                                    "Edit Data Perhitungan Plesteran Berhasil",
                                    Toast.LENGTH_LONG).show();

                        } else if (response.code() == 422) {
                            Toast.makeText(Editplesteran.this,
                                    "Something Wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Editplesteran.this, t.getMessage(),
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
        prosesbtn1 = findViewById(R.id.prosesbtn);
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
        hasilvol = findViewById(R.id.hasilvol);
        hasilb = findViewById(R.id.totalbiaya);
        volume = findViewById(R.id.volume);
        hitung = findViewById(R.id.hitungb);
        campuran = findViewById(R.id.spinner22);
        spinjenis = findViewById(R.id.spinjenis);
        Jenispengerjaan_adapter jenispengerjaan_adapter =
                new Jenispengerjaan_adapter(Editplesteran.this,
                        android.R.layout.simple_spinner_item, jenispengerjaans);
        spinjenis.setAdapter(jenispengerjaan_adapter);
        sm= new SessionManager(Editplesteran.this);
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
        Toast.makeText(Editplesteran.this, "Proyek Id"+mId,
                Toast.LENGTH_SHORT).show();

        Campuran_adapter campuran_adapter =
                new Campuran_adapter(Editplesteran.this,
                        android.R.layout.simple_spinner_item, campurans);
        campuran.setAdapter(campuran_adapter);

        idperhitunganplesteran= bundle.getString("id");
        namapenger=(bundle.getString("nama"));
        bundle.getString("jenis_pengerjaan");
        panjang1 =bundle.getString("panjangdin");
        tinggi1 =bundle.getString("tinggidin");
        sisi.setText(bundle.getString("sisi"));
        tebal.setText(bundle.getString("tebal"));
        volume.setText(bundle.getString("volume"));
        hasilse.setText(bundle.getString("Jumlahkeperluansemen"));
        hasilpas.setText(bundle.getString("jumlahkeperluanpasir"));
        hasilpas1.setText(bundle.getString("jumlahkeperluanpasir"));
        metode= bundle.getString("metode");
        hasilse1.setText(bundle.getString("jumlahdalamsak"));
        hargasemen1=bundle.getString("hargasemen");
        hargapasir1 =bundle.getString("hargapasir");
        namasemen= bundle.getString("nama_semen");
        namapasir= bundle.getString("nama_pasir");
        hasilpas2.setText(bundle.getString("hargapasirtotal"));
        hasilse2.setText(bundle.getString("hargasementotal"));
        hasilvol.setText(bundle.getString("volume"));
        hasilb.setText(bundle.getString("hargatotal"));
    }

}
