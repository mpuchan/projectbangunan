package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangunankita.Model.Campuran;
import com.example.bangunankita.Model.Jenispengerjaan;
import com.example.bangunankita.Model.Material;
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

public class Editbidang extends AppCompatActivity {
    private EditText panjangb,tinggib,panjangp,tinggip,panjangj,tinggij,luasb;
    private EditText hargab,hargas,hargap,namapengerjaan;
    private TextView hasilb,hasilbid,hasilbat,hasilbat1,hasilse,hasilse1,
            hasilse2,hasilpas,hasilpas1,hasilpas2;
    private Button btnproses,hitung;
    private Spinner spinbatako,campuran,spinsemen,spinpasir,spinjenis;
    public String pbatako,tbatako,Jenis,hb,idb,hs,ids,hp,idp,berats,pc,pp,hsemen,hpasir,hbatako,namasemen1,namabatako1,namapasir1,
            token,metode,mId,namasemen,namabatako,namapasir,idperhitunganbidang;
    float hasilm,luasba;
    ProgressDialog pd;
    float totpasir;
    float htotbatako;
    private int numberbatako,numberpasir,numbersemen,numbertotal;
    private String namajenis1, totbatako,totsemen,totpasir1,tothitungan;
    int idbidang;
    float hargasementot,hargapasirparse,hargasemenparse,hargabatakoparse;
    public float hasil;
    SessionManager sm;
    private List<Material> Batako = new ArrayList<>();
    private List<Material> Semen = new ArrayList<>();
    private List<Material> Pasir = new ArrayList<>();
    private List<Jenispengerjaan> jensi = new ArrayList<>();
    Context mContext;
    Campuran[] campurans ={
            new Campuran("1:3", 15.16,0.0364,0.0),

    };
//    Jenispengerjaan[] jenispengerjaans  ={
//            new Jenispengerjaan("Bangunan rumah"),
//            new Jenispengerjaan("Tembok pagar"),
//            new Jenispengerjaan("Sekat kamar mandi"),
//            new Jenispengerjaan("Sekat kamar"),
//            new Jenispengerjaan("Lainnya"),
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editbidang);
        init();

        pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();
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
                if (panjangb.getText().toString().length() == 0) {
                    panjangb.setError("Data ini harus diisi");
                    panjang1 = "0";
                }else if (panjangp.getText().toString().length() == 0) {
                    panjangp.setError("Data ini harus diisi");
                    panjang2 = "0";
                }else if (panjangj.getText().toString().length() == 0) {
                    panjangj.setError("Data ini harus diisi/dibiarkan 0");
                    panjang3 = "0";
                }else if (tinggib.getText().toString().length() == 0) {
                    tinggib.setError("Data ini harus diisi/dibiarkan 0");
                    tinggi1 = "0";
                }else if (tinggip.getText().toString().length() == 0) {
                    tinggip.setError("Data ini harus diisi/dibiarkan 0");
                    tinggi2 = "0";
                }else if (tinggij.getText().toString().length() == 0) {
                    tinggij.setError("Data ini harus diisi/dibiarkan 0");
                    tinggi3 = "0";
                }
                float pb = Float.parseFloat(panjang1);
                float tb = Float.parseFloat(tinggi1);
                float pp = Float.parseFloat(panjang2);
                float tp = Float.parseFloat(tinggi2);
                float pj = Float.parseFloat(panjang3);
                float tj = Float.parseFloat(tinggi3);
                hasil = (pb*tb)-(pp*tp)- (pj*tj);
                luasb.setText(String.valueOf(hasil));
            }
        });


        spinbatako.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                if (selectedName.equalsIgnoreCase(namabatako1)){
                    namabatako = namabatako1;
                    hargab.setText(hbatako);
                    pbatako = String.valueOf(Batako.get(position).getPanjang());
                    tbatako = String.valueOf(Batako.get(position).getTinggi());
                }else {
                    hb = String.valueOf(Batako.get(position).getHarga());
                    idb = String.valueOf(Batako.get(position).getId());
                    namabatako = String.valueOf(Batako.get(position).getNama());
                    pbatako = String.valueOf(Batako.get(position).getPanjang());
                    tbatako = String.valueOf(Batako.get(position).getTinggi());
                    hargab.setText(hb);
                }

//                Toast.makeText(mContext, "Kamu memilih Batako " + selectedName, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinjenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                Jenis = String.valueOf(jensi.get(position).getJenispengerjaan());

//                Toast.makeText(mContext, "Kamu memilih Campuran " + pc, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinsemen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                if (selectedName.equalsIgnoreCase(namasemen1)){
                    namasemen = namasemen1;
                    hargas.setText(hsemen);
                    berats = String.valueOf(Semen.get(position).getBerat());
                }else {
                    berats = String.valueOf(Semen.get(position).getBerat());
                    hs = String.valueOf(Semen.get(position).getHarga());
                    namasemen = String.valueOf(Semen.get(position).getNama());
                    hargas.setText(hs);
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
                if (selectedName.equalsIgnoreCase(namapasir1)){
                    namapasir = namapasir1;
                    hargap.setText(hpasir);
                }else {
                    namapasir = String.valueOf(Pasir.get(position).getNama());
                    hp = String.valueOf(Pasir.get(position).getHarga());
                    hargap.setText(hp);
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

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String luas = luasb.getText().toString().trim();
                luasba = Float.parseFloat(luas);
                hasilbid.setText(Float.toString(luasba));
                Toast.makeText(mContext, "Kamu memilih Campuran " + luas, Toast.LENGTH_SHORT).show();
                String hargapasir = hargap.getText().toString().trim();
                hargapasirparse = Float.parseFloat(hargapasir);
                String hargasemen = hargas.getText().toString().trim();
                hargasemenparse = Float.parseFloat(hargasemen);
                String hargabatako = hargab.getText().toString().trim();
                hargabatakoparse =Float.parseFloat(hargabatako);

                hbatako = hargabatako;
                hpasir = hargapasir;
                hsemen = hargasemen;

                hitungbatako();
                hitungsemen();
                hitungpasir();
                hitungtot();
            }
        });
    }

    private void hitungtot() {

        float tothitung = numberbatako+numberpasir+numbersemen;
        DecimalFormat df = new DecimalFormat("#");
        tothitungan = df.format(tothitung);
        numbertotal = Integer.parseInt(tothitungan);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numbertotal);
        hasilb.setText(totalbiaya);
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
        DecimalFormat df1 = new DecimalFormat("#");
        totpasir1 = df1.format(pembulatanhargatotpasir);
        numberpasir = Integer.parseInt(totpasir1);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numberpasir);

        hasilpas2.setText(totalbiaya);
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
        String jumlahsemensak = df.format(totpc);
        String result1 = null;
        result1 = jumlahsemensak.replace(",",".");
        float jumlahsemenfix = Float.parseFloat(result1);
        hargasementot = jumlahsemenfix*hargasemenparse;
        DecimalFormat df1 = new DecimalFormat("#");
        totsemen = df1.format(hargasementot);
        numbersemen = Integer.parseInt(totsemen);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numbersemen);
        hasilse.setText(Float.toString(hitungs));
        hasilse1.setText(Float.toString(jumlahsemenfix));
        hasilse2.setText(totalbiaya);
    }

    private void hitungbatako() {
        float m = 100;
        float p = Float.parseFloat(pbatako);
        float t = Float.parseFloat(tbatako);
        hasilm = m/(p*t)*m;
        DecimalFormat df = new DecimalFormat("#");
        float tot = hasilm*luasba;
        String totalbatako = df.format(tot);
        int numberbat1 = Integer.parseInt(totalbatako);
        htotbatako = numberbat1*hargabatakoparse;
        totbatako = df.format(htotbatako);
        numberbatako = Integer.parseInt(totbatako);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numberbatako);
        hasilbat.setText(String.valueOf(numberbat1));
        hasilbat1.setText(totalbiaya);
    }

    private void initSpinnerSemen() {
        ApiClient.getRequestInterface().getallsemen().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    Semen = response.body().getMaterials();
                    int i = 0;
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namasemen1;
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
                    String data = namapasir1;
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
                    int i = 0;
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namabatako1;
                    for (int j = 0; j < Batako.size(); j++) {
                        if (data.equalsIgnoreCase(String.valueOf(Batako.get(j).getNama()))) {
                            i = j;
                        }
                        listSpinner.add(String.valueOf(Batako.get(j).getNama()));


                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinbatako.setAdapter(adapter);
                    spinbatako.setSelection(i);
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
                idbidang = Integer.parseInt(idperhitunganbidang);
                String apiKey = "oa00000000app";
                HashMap<String, String> map = new HashMap<>();
                map.put("nama", namapengerjaan.getText().toString());
                map.put("jenis_pengerjaan", Jenis);
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
                map.put("nama_batako", namabatako);
                map.put("nama_pasir", namapasir);
                map.put("nama_semen", namasemen);
                map.put("hargabatako", hbatako);
                map.put("hargapasir", hpasir);
                map.put("hargasemen", hsemen);
                map.put("hargabatakototal", String.valueOf(numberbatako));
                map.put("hargapasirtotal", String.valueOf(numberpasir));
                map.put("hargasementotal", String.valueOf(numbersemen));
                map.put("hargatotal", String.valueOf(numbertotal));
                Call<Void> call = ApiClient.getRequestInterface().actionPutPerhitunganbidang(idbidang,apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {

                            Intent perhitunganbidang = (new Intent(Editbidang.this, Perhitunganbidang.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            Bundle setData = new Bundle();
                            setData.putString("idproyek",mId);
                            perhitunganbidang.putExtras(setData);
                            startActivity(perhitunganbidang);
                            Toast.makeText(Editbidang.this,
                                    "Tambah Data Perhitungan Bidang Berhasil",
                                    Toast.LENGTH_LONG).show();

                        } else if (response.code() == 422) {
                            Toast.makeText(Editbidang.this,
                                    "Something Wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Editbidang.this, t.getMessage(),
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
        sm= new SessionManager(Editbidang.this);
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        sm.checkLogin();
        spinjenis = findViewById(R.id.spinjenis);
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
                new Campuran_adapter(Editbidang.this,
                        android.R.layout.simple_spinner_item, campurans);
        campuran.setAdapter(campuran_adapter);
//        Jenispengerjaan_adapter jenispengerjaan_adapter =
//                new Jenispengerjaan_adapter(Editbidang.this,
//                        android.R.layout.simple_spinner_item, jenispengerjaans);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            mId = bundle.getString("ProyekId");
        }else{
            mId = "0";
        }
        idperhitunganbidang= bundle.getString("id");
       namapengerjaan.setText(bundle.getString("nama"));
        namajenis1 = bundle.getString("jenis_pengerjaan");
        panjangb.setText(bundle.getString("panjangbid"));
        tinggib.setText(bundle.getString("tinggibid"));
        panjangp.setText(bundle.getString("panjangpin"));
        tinggip.setText(bundle.getString("tinggipin"));
        panjangj.setText(bundle.getString("panjangjen"));
        tinggij.setText(bundle.getString("tinggijen"));
        luasb.setText(bundle.getString("luas_bidang"));
        hasilbid.setText(bundle.getString("luas_bidang"));
        hasilbat.setText(bundle.getString("jumlahkeperluanbatako"));
        hasilpas.setText(bundle.getString("jumlahkeperluanpasir"));
        hasilpas1.setText(bundle.getString("jumlahkeperluanpasir"));
        hasilse.setText(bundle.getString("Jumlahkeperluansemen"));
        hasilse1.setText(bundle.getString("jumlahdalamsak"));
      namabatako1= bundle.getString("nama_batako");
      namapasir1 = bundle.getString("nama_pasir");
        namasemen1 = bundle.getString("nama_semen");
        bundle.getString("metode");
      hbatako =bundle.getString("hargabatako");
        hpasir =bundle.getString("hargapasir");
        hsemen =bundle.getString("hargasemen");

        htotbatako= Float.parseFloat(bundle.getString("hargabatakototal"));
        DecimalFormat df = new DecimalFormat("#");
        totbatako = df.format(htotbatako);
        numberbatako = Integer.parseInt(totbatako);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiayabatako = formatter.format(numberbatako);
        hasilbat1.setText(totalbiayabatako);

        totpasir = Float.parseFloat(bundle.getString("hargapasirtotal"));
        DecimalFormat df1 = new DecimalFormat("#");
        totpasir1 = df1.format(totpasir);
        numberpasir = Integer.parseInt(totpasir1);
        DecimalFormat formatter1 = new DecimalFormat("#,###.##");
        String totalbiayapasir = formatter1.format(numberpasir);
        hasilpas2.setText(totalbiayapasir);

        hargasementot = Float.parseFloat(bundle.getString("hargasementotal"));
        DecimalFormat df2 = new DecimalFormat("#");
        totsemen = df2.format(hargasementot);
        numbersemen = Integer.parseInt(totsemen);
        DecimalFormat formatter2 = new DecimalFormat("#,###.##");
        String totalbiayasemen = formatter2.format(numbersemen);
       hasilse2.setText(totalbiayasemen);

        float tothitung = Float.parseFloat(bundle.getString("hargatotal"));
        DecimalFormat df3 = new DecimalFormat("#");
        tothitungan = df3.format(tothitung);
        numbertotal = Integer.parseInt(tothitungan);
        DecimalFormat formatter3 = new DecimalFormat("#,###.##");
        String totalbiayaseluruh = formatter3.format(numbertotal);
        hasilb.setText(totalbiayaseluruh);
        jensi.add(new Jenispengerjaan("Bangunan rumah"));
        jensi.add(new Jenispengerjaan("Tembok pagar"));
        jensi.add(new Jenispengerjaan("Sekat kamar mandi"));
        jensi.add(new Jenispengerjaan("Sekat kamar"));
        jensi.add(new Jenispengerjaan("Lainnya"));

        int i = 0;
        List<String> listSpinner = new ArrayList<String>();
        String data = namajenis1;
        for (int j = 0; j <jensi.size(); j++) {
            if (data.equalsIgnoreCase(String.valueOf(jensi.get(j).getJenispengerjaan()))) {
                i = j;
            }
            listSpinner.add(String.valueOf(jensi.get(j).getJenispengerjaan()));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_spinner_item, listSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinjenis.setAdapter(adapter);
        spinjenis.setSelection(i);
        float luas;
        btnproses = findViewById(R.id.prosesbtn);
    }
    }
