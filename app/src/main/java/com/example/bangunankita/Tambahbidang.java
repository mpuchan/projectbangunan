package com.example.bangunankita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
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
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangunankita.Model.Campuran;
import com.example.bangunankita.Model.Jenispengerjaan;
import com.example.bangunankita.Model.Material;
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

public class Tambahbidang extends AppCompatActivity {
    private EditText panjangb,tinggib,panjangp,tinggip,panjangj,tinggij,luasb;
    private EditText hargab,hargas,hargap,namapengerjaan;
    private TextView hasilb,hasilbid,hasilbat,hasilbat1,hasilse,hasilse1,
            hasilse2,hasilpas,hasilpas1,hasilpas2;
    private Button btnproses,hitung;
    private Spinner spinbatako,campuran,spinsemen,spinpasir,spinjenis;
    public String pbatako,tbatako,hb,idb,hs,ids,hp,idp,berats,pc,pp,jenis,token,metode,mId,namasemen,namapasir,namabatako;
    float hasilm,luasba;
    ProgressDialog pd;
    float totpasir;
    float htotbatako;
    private int numberbatako,numberpasir,numbersemen,numbertotal;
    private String totbatako,totsemen,totpasir1,tothitungan;
    float hargasementot,hargapasirparse,hargasemenparse,hargabatakoparse;
    public float hasil;
    private String panjang1,panjang2,panjang3,tinggi1,tinggi2,tinggi3;
    SessionManager sm;
    private List<Material> Batako = new ArrayList<>();
    private List<Material> Semen = new ArrayList<>();
    private List<Material> Pasir = new ArrayList<>();
    Context mContext;
    Campuran[] campurans ={
            new Campuran("1:3", 15.16,0.0364,0),

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
        setContentView(R.layout.activity_tambahbidang);
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

                panjang1 = panjangb.getText().toString().trim();
                panjang2 = panjangp.getText().toString().trim();
                panjang3 = panjangj.getText().toString().trim();
                tinggi1 = tinggib.getText().toString().trim();
                tinggi2 = tinggip.getText().toString().trim();
                 tinggi3 = tinggij.getText().toString().trim();

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
                    tinggib.setError("Data ini harus diisi");
                    tinggi1 = "0";
                }else if (tinggip.getText().toString().length() == 0) {
                    tinggip.setError("Data ini harus diisi/dibiarkan 0");
                    tinggi2 = "0";
                }else if (tinggij.getText().toString().length() == 0) {
                    tinggij.setError("Data ini harus diisi/dibiarkan 0");
                    tinggi3 = "0";
                }
                initproses();

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
                namabatako = String.valueOf(Batako.get(position).getNama());
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

                String hargapasir = hargap.getText().toString().trim();
                String hargasemen = hargas.getText().toString().trim();
                String hargabatako = hargab.getText().toString().trim();
                if (hargap.getText().toString().length() == 0) {
                    hargap.setError("Data ini harus diisi");
                    hargapasir = "0";
                }else if (hargas.getText().toString().length() == 0) {
                    hargas.setError("Data ini harus diisi");
                    hargasemen = "0";

                }else if (hargab.getText().toString().length() == 0) {
                    hargab.setError("Data ini harus diisi");
                    hargabatako = "0";
                }
                hargapasirparse = Float.parseFloat(hargapasir);
                hasilbid.setText(String.valueOf(hasil));
                hargasemenparse = Float.parseFloat(hargasemen);
                hargabatakoparse =Float.parseFloat(hargabatako);
                hitungbatako();
                hitungsemen();
                hitungpasir();
                hitungtot();
//

            }
        });
    }

    private void initproses() {
        float pb = Float.parseFloat(panjang1);
        float tb = Float.parseFloat(tinggi1);
        float pp = Float.parseFloat(panjang2);
        float tp = Float.parseFloat(tinggi2);
        float pj = Float.parseFloat(panjang3);
        float tj = Float.parseFloat(tinggi3);
        hasil = (pb * tb) - (pp * tp) - (pj * tj);
        luasb.setText(String.valueOf(hasil));
        String luas = luasb.getText().toString().trim();
        luasba = Float.parseFloat(luas);

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
        String f = df.format(totpc);
        String result1 = null;
        result1 = f.replace(",",".");
        float semensak = Float.parseFloat(result1);
        hargasementot = semensak*hargasemenparse;
        DecimalFormat df1 = new DecimalFormat("#");
        totsemen = df1.format(hargasementot);
        numbersemen = Integer.parseInt(totsemen);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numbersemen);
        hasilse.setText(Float.toString(hitungs));
        hasilse1.setText(Float.toString(semensak));
        hasilse2.setText(totalbiaya);
    }

    private void hitungbatako() {
        float m = 100;
        float p = Float.parseFloat(pbatako);
        float t = Float.parseFloat(tbatako);
        DecimalFormat df = new DecimalFormat("#");
        hasilm = m/(p*t)*m;
        float tot = hasilm*luasba;
        String to1 = df.format(tot);
        float totabatako = Float.parseFloat(to1);
        htotbatako = totabatako*hargabatakoparse;

        totbatako = df.format(htotbatako);
        numberbatako = Integer.parseInt(totbatako);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numberbatako);
        hasilbat.setText(Float.toString(totabatako));
        hasilbat1.setText(totalbiaya);
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
                    pd.hide();
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
                pd.show();
                String apiKey = "oa00000000app";
                initvalidation();
                HashMap<String, String> map = new HashMap<>();
                map.put("ProyekId", mId);
                map.put("nama", namapengerjaan.getText().toString());
                map.put("jenis_pengerjaan", jenis);
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
                map.put("nama_batako", String.valueOf(namabatako));
                map.put("nama_semen", String.valueOf(namasemen));
                map.put("nama_pasir", String.valueOf(namapasir));
                map.put("hargabatako", hargab.getText().toString());
                map.put("hargapasir", hargap.getText().toString());
                map.put("hargasemen", hargas.getText().toString());
                map.put("hargabatakototal", String.valueOf(numberbatako));
                map.put("hargapasirtotal", String.valueOf(numberpasir));
                map.put("hargasementotal", String.valueOf(numbersemen));
                Call<Void> call = ApiClient.getRequestInterface().actionCreatebidang(apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            pd.hide();
                            Intent Perhitunganbidang = (new Intent(Tambahbidang.this, Perhitunganbidang.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            Bundle setData = new Bundle();
                            setData.putString("idproyek",mId);
                            Perhitunganbidang.putExtras(setData);
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

    private void initvalidation() {
        if (namapengerjaan.getText().toString().length() == 0) {
            namapengerjaan.setError("field ini harus diisi!");

        } else if (panjangb.getText().toString().length() == 0) {
            panjangb.setError("field ini harus diisi!");

        } else if (panjangp.getText().toString().length() == 0) {
            panjangp.setError("field ini harus diisi!");
        } else if (panjangj.getText().toString().length() == 0) {
            panjangj.setError("field ini harus diisi!");

        } else if (tinggib.getText().toString().length() == 0) {
            tinggib.setError("field ini harus diisi!");

        } else if (tinggip.getText().toString().length() == 0) {
            tinggip.setError("field ini harus diisi!");

        } else if (tinggij.getText().toString().length() == 0) {
            tinggij.setError("field ini harus diisi!");
        } else if (luasb.getText().toString().length() == 0) {
            luasb.setError("field ini harus diisi!");
        } else if (hasilbat1.getText().toString().length() == 0) {
           hitung.setError("");
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Tambahbidang.this);
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
        spinjenis = findViewById(R.id.spinjenis);
        Campuran_adapter campuran_adapter =
                new Campuran_adapter(Tambahbidang.this,
                        android.R.layout.simple_spinner_item, campurans);
        campuran.setAdapter(campuran_adapter);
        Jenispengerjaan_adapter jenispengerjaan_adapter =
                new Jenispengerjaan_adapter(Tambahbidang.this,
                        android.R.layout.simple_spinner_item, jenispengerjaans);
        spinjenis.setAdapter(jenispengerjaan_adapter);
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
