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
import java.nio.channels.FileLock;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tambahpondasi extends AppCompatActivity {
    private EditText a,b,t,p,volume;
    private EditText hargab,hargas,hargap,namapengerjaan;
    private TextView hasilb,hasilbid,hasilbat2,hasilbat,hasilvol,hasilbat1,hasilse,hasilse1,
            hasilse2,hasilpas,hasilpas1,hasilpas2,totalbiaya;
    private Button btnproses,hitung;
    ProgressDialog pd;
    private Spinner spinbatu,campuran,spinsemen,spinpasir;
    public String hb,idb,hs,ids,hp,idp,berats,pc,pp,jenis,token,metode,mId,namasemen,namapasir,namabatu;
    public String a1,b1,t1,p1,vol;
    private int numberbatako,numberpasir,numbersemen,numbertotal,numberbatu;
    private String totbatako,totsemen1,totpasir1,tothitungan,totbatu;
    float hasilm,vol1;
    float totpasir;
    float htotbatu,hasilbt;
    float hargasementot,hargapasirparse,hargasemenparse,hargabatuparse;
    public float hasil;
    SessionManager sm;
    private List<Material> Batu = new ArrayList<>();
    private List<Material> Semen = new ArrayList<>();
    private List<Material> Pasir = new ArrayList<>();
    Context mContext;
    Campuran[] campurans ={
            new Campuran("1:3", 202,0.485,0),
            new Campuran("1:4", 163, 0.52,0),
            new Campuran("1:5", 136,0.544,0),
            new Campuran("1:6", 117,0.561,0),
            new Campuran("1:8", 91,0.584,0)
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahpondasi);
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
                volume.setText(String.valueOf(hasil));
                String vol = volume.getText().toString().trim();
                vol1 = Float.parseFloat(vol);

            }
        });

        spinbatu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                hb = String.valueOf(Batu.get(position).getHarga());
                idb = String.valueOf(Batu.get(position).getId());
                namabatu = String.valueOf(Batu.get(position).getNama());
                hargab.setText(hb);

//                Toast.makeText(mContext, "Kamu memilih Batako " + selectedName, Toast.LENGTH_SHORT).show();
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
                namasemen = String.valueOf(Semen.get(position).getNama());
                ids = String.valueOf(Semen.get(position).getId());
                hargas.setText(hs);

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
                namapasir = String.valueOf(Pasir.get(position).getNama());
                hargap.setText(hp);

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
                String vol = volume.getText().toString().trim();
                vol1 = Float.parseFloat(vol);
                hasilvol.setText(vol);
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
//        Toast.makeText(mContext, "Gagal mengambil data"+ppasir, Toast.LENGTH_SHORT).show();
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
        hargasementot = totpc*hargasemenparse;
        DecimalFormat df1 = new DecimalFormat("#");
        totsemen1 = df1.format(hargasementot);
        numbersemen = Integer.parseInt(totsemen1);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numbersemen);

        hasilse.setText(Float.toString(hitungs));
        hasilse1.setText(Float.toString(totpc));
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
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < Semen.size(); i++){
                        listSpinner.add(Semen.get(i).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinsemen.setAdapter(adapter);
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
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < Pasir.size(); i++){
                        listSpinner.add(Pasir.get(i).getNama());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinpasir.setAdapter(adapter);
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

                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < Batu.size(); i++){
                        listSpinner.add(String.valueOf(Batu.get(i).getNama()));


                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinbatu.setAdapter(adapter);
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
                map.put("ProyekId", mId);
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


                Call<Void> call = ApiClient.getRequestInterface().actionCreatepondasi(apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if (response.code() == 200) {
                            pd.hide();
                            Intent Perhitunganbidang = (new Intent(Tambahpondasi.this, PerhitunganPondasi.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            Bundle setData = new Bundle();
                            setData.putString("idproyek",mId);
                            Perhitunganbidang.putExtras(setData);
                            startActivity(Perhitunganbidang);
                            Toast.makeText(Tambahpondasi.this,
                                    "Tambah Data Perhitungan Pondasi Berhasil",
                                    Toast.LENGTH_LONG).show();

                        } else if (response.code() == 422) {
                            Toast.makeText(Tambahpondasi.this,
                                    "Something Wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Tambahpondasi.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    private void initvalidation() {
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

        } else if (hasilbat1.getText().toString().length() == 0) {
            hitung.setError("");
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Tambahpondasi.this);
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
        sm= new SessionManager(Tambahpondasi.this);
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
        Campuran_adapter campuran_adapter =
                new Campuran_adapter(Tambahpondasi.this,
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
