package com.example.bangunankita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Editpondasi extends AppCompatActivity {
    private EditText a,b,t,p,volume;
    private EditText hargab,hargas,hargap,namapengerjaan;
    private TextView hasilb,hasilbid,hasilbat2,hasilbat,hasilbat1,hasilse,hasilse1,
            hasilse2,hasilpas,hasilpas1,hasilpas2,totalbiaya,hasilvol;
    private Button btnproses,hitung;
    private String Htotbatu1,Htotpasir1,Htotsemen1,totalsemua;
    private String namasemen1,hsemen,namapasir1,namabatu1,hpasir,hbatu;
    private Spinner spinbatu,campuran,spinsemen,spinpasir;
    public String hb,idb,hs,ids,hp,idp,berats,pc,pp,jenis,token,metode,mId,namasemen,namapasir,namabatu;
    public String a1,b1,t1,p1,vol;
    private int numberbatako,numberpasir,numbersemen,numbertotal,numberbatu;
    private String totbatako,totsemen1,totpasir1,tothitungan,totbatu;
    float hasilm,vol1;
    ProgressDialog pd;
    float totpasir;
    float htotbatu,hasilbt;
    float hargasementot,hargapasirparse,hargasemenparse,hargabatuparse;
    public float hasil;
    private int thisid;
    SessionManager sm;
    private List<Material> Batu = new ArrayList<>();
    private List<Material> Semen = new ArrayList<>();
    private List<Material> Pasir = new ArrayList<>();
    private List<Campuran> Campuran1 = new ArrayList<>();
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpondasi);
        init();
        pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();
        initSpinnerBatu();
        initSpinnerSemen();
        initSpinnerPasir();

        btnproses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a1 = a.getText().toString().trim();
                String b1 = b.getText().toString().trim();
                String t1 = t.getText().toString().trim();
                String p1 = p.getText().toString().trim();
                if (a.getText().toString().length() == 0) {
                    a.setError("Data ini harus diisi");
                    a1 = "0";
                }else if (b.getText().toString().length() == 0) {
                    b.setError("Data ini harus diisi");
                    b1 = "0";
                }else if (t.getText().toString().length() == 0) {
                    t.setError("Data ini harus diisi");
                    t1 = "0";
                }else if (p.getText().toString().length() == 0) {
                    p.setError("Data ini harus diisi");
                    p1 = "0";
                }else if (p.getText().toString().length() == 0) {
                    p.setError("Data ini harus diisi");
                    p1 = "0";
                }
                float a2 = Float.parseFloat(a1);
                float b2 = Float.parseFloat(b1);
                float t2 = Float.parseFloat(t1);
                float p2 = Float.parseFloat(p1);
                hasil = (((a2+b2)*t2)/2)*p2;

                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.CEILING);
                float d = hasil;
                String n = df.format(d);
                String result = null;
                result = n.replace(",",".");

                volume.setText(result);


            }
        });

        spinbatu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();

                if (selectedName.equalsIgnoreCase(namabatu1)){
                    namabatu = namabatu1;
                    hargab.setText(hbatu);
                }else {
                    namabatu = String.valueOf(Batu.get(position).getNama());
                    hb = String.valueOf(Batu.get(position).getHarga());
                    hargab.setText(hb);
                }

//                Toast.makeText(mContext, "Kamu memilih Batu " + selectedName, Toast.LENGTH_SHORT).show();
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

//                berats = String.valueOf(Semen.get(position).getBerat());
//                hs = String.valueOf(Semen.get(position).getHarga());
//                namasemen = String.valueOf(Semen.get(position).getNama());
//                ids = String.valueOf(Semen.get(position).getId());
//                hargas.setText(hs);

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


//                Toast.makeText(mContext, "Kamu memilih Pasir " + hpasir, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        campuran.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                pp = String.valueOf(Campuran1.get(position).getPp());
                pc = String.valueOf(Campuran1.get(position).getPc());
                metode = String.valueOf(Campuran1.get(position).getCampuran());

//                Toast.makeText(mContext, "Kamu memilih Campuran " + pc, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vol = volume.getText().toString().trim();
                vol1 = Float.parseFloat(vol);
                String hargapasir = hargap.getText().toString().trim();
                hargapasirparse = Float.parseFloat(hargapasir);
//                hasilbid.setText(String.valueOf(hasil));
                String hargasemen = hargas.getText().toString().trim();
                hargasemenparse = Float.parseFloat(hargasemen);
                String hargabatu = hargab.getText().toString().trim();
                hargabatuparse =Float.parseFloat(hargabatu);
                hitungbatu();
                hitungsemen();
                hitungpasir();
                hitungtot();
//

            }
        });
    }

    private void hitungtot() {
        float tothitung = numbersemen+numberbatu+numberpasir;
        DecimalFormat df = new DecimalFormat("#");
        tothitungan = df.format(tothitung);
        numbertotal = Integer.parseInt(tothitungan);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya1 = formatter.format(numbertotal);
        totalbiaya.setText(totalbiaya1);
    }

    private void hitungpasir() {
        float ppasir = Float.parseFloat(pp);
        float hitpp = ppasir*vol1;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        float d = hitpp;
        String n = df.format(d);
        String result = null;
        result = n.replace(",",".");
        float hitungp = Float.parseFloat(result);
        totpasir = hitungp*hargapasirparse;
        DecimalFormat df1 = new DecimalFormat("#");
        totpasir1 = df1.format(totpasir);
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
        float hitpc = psemen*vol1;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        float d = hitpc;
        String n = df.format(d);
        String result = null;
        result = n.replace(",",".");
        float hitungs = Float.parseFloat(result);
        float totpc = hitungs/beratsemen;
        String totpc1 = df.format(totpc);
        String resul1 = null;
        resul1 = totpc1.replace(",",".");
        float totpcsak = Float.parseFloat(resul1);
        hargasementot = totpcsak*hargasemenparse;
        DecimalFormat df1 = new DecimalFormat("#");
        totsemen1 = df1.format(hargasementot);
        numbersemen = Integer.parseInt(totsemen1);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numbersemen);

        hasilse.setText(Float.toString(hitungs));
        hasilse1.setText(Float.toString(totpcsak));
        hasilse2.setText(totalbiaya);
    }

    private void hitungbatu() {
        float m = 1.2f;
        hasilm = vol1*m;
        htotbatu = hasilm*hargabatuparse;
        float hbt = hasilm/7;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        float d = hbt;
        String n = df.format(d);
        String result = null;
        result = n.replace(",",".");
        hasilbt = Float.parseFloat(result);
        DecimalFormat df1 = new DecimalFormat("#");
        totbatu = df1.format(htotbatu);
        numberbatu = Integer.parseInt(totbatu);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numberbatu);
        hasilbat.setText(Float.toString(hasilm));
        hasilbat1.setText(Float.toString(hasilbt));
        hasilbat2.setText(totalbiaya);
    }

    private void initSpinnerSemen() {
        ApiClient.getRequestInterface().getallsemen().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    pd.hide();
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
                    pd.hide();
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
                    Toast.makeText(mContext, "Gagal mengambil data Pasir", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseMaterial> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSpinnerBatu() {
        ApiClient.getRequestInterface().getallbatu().enqueue(new Callback<ResponseMaterial>() {
            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    pd.hide();
                    Batu = response.body().getMaterials();
                    int i = 0;
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namabatu1;
                    for (int j = 0; j < Batu.size(); j++){
                        if (data.equalsIgnoreCase(String.valueOf(Batu.get(j).getNama()))) {
                            i = j;
                        }
                        listSpinner.add(String.valueOf(Batu.get(j).getNama()));


                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinbatu.setAdapter(adapter);
                    spinbatu.setSelection(i);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data Batu", Toast.LENGTH_SHORT).show();
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
                pd.show();
                String apiKey = "oa00000000app";
                initvalidation();
                HashMap<String, String> map = new HashMap<>();
                map.put("nama", namapengerjaan.getText().toString());
                map.put("a", a.getText().toString());
                map.put("b", b.getText().toString());
                map.put("t", t.getText().toString());
                map.put("p", p.getText().toString());
                map.put("luas", volume.getText().toString());
                map.put("metode",metode);
                map.put("namasemen",namasemen);
                map.put("namapasir",namapasir);
                map.put("namabatu",namabatu);
                map.put("hargasemen",hargas.getText().toString());
                map.put("hargapasir",hargap.getText().toString());
                map.put("hargabatukali",hargab.getText().toString());
                map.put("jumlahsemen",hasilse.getText().toString());
                map.put("jumlahsemendalamsak",hasilse1.getText().toString());
                map.put("jumlahpasir",hasilpas.getText().toString());
                map.put("jumlahbatu",hasilbat.getText().toString());
                map.put("jumlahbatutruk",hasilbat1.getText().toString());
                map.put("hargasementotal", String.valueOf(numbersemen));
                map.put("hargapasirtotal", String.valueOf(numberpasir));
                map.put("hargabatutotal", String.valueOf(numberbatu));
                map.put("hargatotal", String.valueOf(numbertotal));


                Call<Void> call = ApiClient.getRequestInterface().actionPutPerhitunganpondasi(thisid,apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {
                            pd.hide();
                            Intent Perhitunganpondasi = (new Intent(Editpondasi.this, PerhitunganPondasi.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            Bundle setData = new Bundle();
                            setData.putString("idproyek",mId);
                            Perhitunganpondasi.putExtras(setData);
                            startActivity(Perhitunganpondasi);
                            Toast.makeText(Editpondasi.this,
                                    "Edit Data Perhitungan Pondasi Berhasil",
                                    Toast.LENGTH_LONG).show();

                        } else if (response.code() == 422) {
                            Toast.makeText(Editpondasi.this,
                                    "Something Wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Editpondasi.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    private void initvalidation() {
        if (namapengerjaan.getText().toString().length() == 0) {
            namapengerjaan.setError("Data ini harus diisi");
        }
        if (a.getText().toString().length() == 0) {
            a.setError("Data ini harus diisi");
            a1 = "0";
        }else if (b.getText().toString().length() == 0) {
            b.setError("Data ini harus diisi");
            b1 = "0";
        }else if (t.getText().toString().length() == 0) {
            t.setError("Data ini harus diisi");
            t1 = "0";
        }else if (p.getText().toString().length() == 0) {
            p.setError("Data ini harus diisi");
            p1 = "0";
        }else if (p.getText().toString().length() == 0) {
            p.setError("Data ini harus diisi");
            p1 = "0";

        } else if (hasilse.getText().toString().length() == 0) {
            hitung.setError("");
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Editpondasi.this);
            builder1.setTitle("Pesan Error");
            builder1.setMessage("Klik Button hitung dulu!!");
            builder1.setIcon(R.drawable.ic_warning_red_24dp);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;
        sm= new SessionManager(Editpondasi.this);
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        sm.checkLogin();
        namapengerjaan = findViewById(R.id.nama_pengerjaan);
        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        t = findViewById(R.id.t);
        p = findViewById(R.id.p);
        volume = findViewById(R.id.volume);
        campuran = findViewById(R.id.spinner22);
        spinpasir = findViewById(R.id.spinpasir);
        spinbatu = findViewById(R.id.spinbatu);
        spinsemen = findViewById(R.id.spinsemen);
        hargas = findViewById(R.id.hargasemen);
        hargap = findViewById(R.id.hargapasir);
        hargab= findViewById(R.id.hargabatu);
        hitung = findViewById(R.id.hitungb);
        hasilpas = findViewById(R.id.hasilpasir);
        hasilpas1 = findViewById(R.id.hasilpasirtruk);
        hasilpas2 = findViewById(R.id.hasilhargap);
        hasilse = findViewById(R.id.hasilsemen);
        hasilse1 = findViewById(R.id.hasilsemensak);
        hasilse2 = findViewById(R.id.hasilsemen2);
        hasilbat = findViewById(R.id.hasilbatu);
        hasilbat1 = findViewById(R.id.hasilbatu1);
        hasilbat2 = findViewById(R.id.hasilbatu2);
        totalbiaya= findViewById(R.id.totalbiaya);
        hasilvol = findViewById(R.id.hasilvol);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            mId = bundle.getString("ProyekId");
        }else{
            mId = "0";
        }

        thisid= Integer.parseInt(bundle.getString("id"));
        namapengerjaan.setText(bundle.getString("nama"));
        a.setText(bundle.getString("a"));
        b.setText(bundle.getString("b"));
        t.setText(bundle.getString("t"));
        p.setText(bundle.getString("p"));
        metode = bundle.getString("metode");
        volume.setText(bundle.getString("luas"));
        hasilvol.setText(bundle.getString("luas"));
        namasemen1=bundle.getString("namasemen");
        namabatu1=bundle.getString("namabatu");
       namapasir1=bundle.getString("namapasir");
        hasilbat.setText(bundle.getString("jumlahbatu"));
        hasilbat1.setText(bundle.getString("jumlahbatutruk"));
        hasilse.setText(bundle.getString("jumlahsemen"));
        hasilse1.setText(bundle.getString("jumlahsemendalamsak"));
       hasilpas.setText(bundle.getString("jumlahpasir"));
        hasilpas1.setText(bundle.getString("jumlahpasir"));
        hbatu=bundle.getString("hargabatu");
        hsemen =bundle.getString("hargasemen");
        hpasir=bundle.getString("hargapasir");

        htotbatu= Float.parseFloat(bundle.getString("hargabatutotal"));
        DecimalFormat df1 = new DecimalFormat("#");
        totbatu = df1.format(htotbatu);
        numberbatu = Integer.parseInt(totbatu);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        Htotbatu1 = formatter.format(numberbatu);
        hasilbat2.setText(Htotbatu1);

        hargasementot= Float.parseFloat(bundle.getString("hargasementotal"));
        DecimalFormat df2 = new DecimalFormat("#");
        totsemen1= df2.format(hargasementot);
        numbersemen = Integer.parseInt(totsemen1);
        DecimalFormat formatter1 = new DecimalFormat("#,###.##");
        Htotsemen1 = formatter1.format(numbersemen);
        hasilse2.setText(Htotsemen1);

        totpasir= Float.parseFloat(bundle.getString("hargapasirtotal"));
        DecimalFormat df3 = new DecimalFormat("#");
        totpasir1= df3.format(totpasir);
        numberpasir = Integer.parseInt(totpasir1);
        DecimalFormat formatter2 = new DecimalFormat("#,###.##");
        Htotpasir1 = formatter2.format(numberpasir);
        hasilpas2.setText(Htotpasir1);

        float tothitung= Float.parseFloat(bundle.getString("hargatotal"));
        DecimalFormat df = new DecimalFormat("#");
        tothitungan = df.format(tothitung);
        numbertotal = Integer.parseInt(tothitungan);
        DecimalFormat formatter3 = new DecimalFormat("#,###.##");
        totalsemua = formatter3.format(numbertotal);
        totalbiaya.setText(totalsemua);

        Campuran1.add(new Campuran("1:3", 202,0.485,0));
        Campuran1.add(new Campuran("1:4", 163, 0.52,0));
        Campuran1.add(new Campuran("1:5", 136,0.544,0));
        Campuran1.add(new Campuran("1:6", 117,0.561,0));
        Campuran1.add(new Campuran("1:8", 91,0.584,0));

        int i = 0;
        List<String> listSpinner = new ArrayList<String>();
        String data = metode;
        for (int j = 0; j <Campuran1.size(); j++) {
            if (data.equalsIgnoreCase(String.valueOf(Campuran1.get(j).getCampuran()))) {
                i = j;
            }
            listSpinner.add(String.valueOf(Campuran1.get(j).getCampuran()));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_spinner_item, listSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campuran.setAdapter(adapter);
        campuran.setSelection(i);
        float luas;
        btnproses = findViewById(R.id.prosesbtn);





    }

    }

