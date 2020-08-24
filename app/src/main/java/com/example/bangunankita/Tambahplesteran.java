package com.example.bangunankita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class Tambahplesteran extends AppCompatActivity {
    Spinner spindinding,spinsemen,spinpasir,campuran,spinjenis;
    EditText panjang,tinggi,tebal,sisi,hargasemen,hargapasir,volume;
    TextView hasilpas,hasilpas1,hasilpas2,hasilse,hasilse1,hasilse2,hasilb,hasilvol;
    Button prosesbtn,hitung;
    float hasil,luasba,totpasir,hargasementot;
    ProgressDialog pd;
    Context mContext;
    SessionManager sm;
    String token,nama,p,t,namadinding,jenis,namasemen,namapasir,metode;
    int ProyekID;
    private  int numberpasir,numbersemen,numbertotal;
    private String totpasir1,totsemen1,tothargatot;
    String mId,Ju;
    public String hs,ids,hp,idp,berats,pc,pp;
    private List<Material> Semen = new ArrayList<>();
    private List<Material> Pasir = new ArrayList<>();
    private List<Perhitunganbidang1> BidangModel = new ArrayList<>();
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
        setContentView(R.layout.activity_tambahplesteran);
        init();
        pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();
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

                if (panjang.getText().toString().length() == 0) {
                    panjang.setError("Data ini harus diisi");
                    panjang1 = "0";
                }else if (tinggi.getText().toString().length() == 0) {
                    tinggi.setError("Data ini harus diisi");
                    tinggi1 = "0";
                }else if (sisi.getText().toString().length() == 0) {
                    sisi.setError("Data ini harus diisi");
                    sisi1 = "0";
                } else if (tebal.getText().toString().length() == 0) {
                    tebal.setError("field ini harus diisi!");
                    tebal1 = "0";
                }

                float pp = Float.parseFloat(panjang1);
                float tp = Float.parseFloat(tinggi1);
                float tebal = Float.parseFloat(tebal1);
                float sisi = Float.parseFloat(sisi1);

                hasil = pp*tp*tebal*sisi;
                volume.setText(String.valueOf(hasil));

            }
        });


        spindinding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                namadinding = String.valueOf(BidangModel.get(position).getNama());
                jenis = String.valueOf(BidangModel.get(position).getJenisPengerjaan());
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
                namasemen = String.valueOf(Semen.get(position).getNama());
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
                namapasir = String.valueOf(Pasir.get(position).getNama());

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
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String luas = volume.getText().toString().trim();
                luasba = Float.parseFloat(luas);
                hasilvol.setText(luas);
                hitungsemen();
                hitungpasir();
                hitungtot();
            }
        });
    }

    private void hitungtot() {
        float tothitung = numberpasir+numbersemen;
        DecimalFormat df = new DecimalFormat("#");
        tothargatot = df.format(tothitung);
        numbertotal = Integer.parseInt(tothargatot);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numbertotal);
        hasilb.setText(totalbiaya);
    }

    private void hitungpasir() {
        float ppasir = Float.parseFloat(pp);
        float hargapas = Float.parseFloat(hp);
        float hitpp = ppasir*luasba;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        float d = hitpp;
        String n = df.format(d);
        String result = null;
        result = n.replace(",",".");
        float hitungp = Float.parseFloat(result);
        totpasir = hitungp*hargapas;
        DecimalFormat df1 = new DecimalFormat("#");
        totpasir1 = df1.format(totpasir);
        numberpasir = Integer.parseInt(totpasir1);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numberpasir);

        Toast.makeText(mContext, "Gagal mengambil data"+ppasir, Toast.LENGTH_SHORT).show();
        hasilpas2.setText(totalbiaya);
        hasilpas1.setText(Float.toString(hitungp));
        hasilpas.setText(Float.toString(hitungp));
    }

    private void hitungsemen() {
        float psemen = Float.parseFloat(pc);
        float beratsemen = Float.parseFloat(berats);
        float hargasemen = Float.parseFloat(hs);
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
        DecimalFormat df1 = new DecimalFormat("#");
        totsemen1 = df1.format(hargasementot);
        numbersemen = Integer.parseInt(totsemen1);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numbersemen);


        hasilse.setText(Float.toString(hitungs));
        hasilse1.setText(Float.toString(totpc));
        hasilse2.setText(totalbiaya);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mainsavebidang,menu);
        MenuItem item = menu.findItem(R.id.app_bar_savedata);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String apiKey = "oa00000000app";
                initvalidation();
                HashMap<String, String> map = new HashMap<>();
                map.put("ProyekId", mId);
                map.put("nama", namadinding);
                map.put("jenis_pengerjaan", jenis);
                map.put("panjangdin", panjang.getText().toString());
                map.put("tinggidin", tinggi.getText().toString());
                map.put("tebal", tebal.getText().toString());
                map.put("sisi", sisi.getText().toString());
                map.put("volume", volume.getText().toString());
                map.put("nama_semen", namasemen);
                map.put("nama_pasir", namapasir);
                map.put("jumlahkeperluanpasir", hasilpas1.getText().toString());
                map.put("Jumlahkeperluansemen", hasilse.getText().toString());
                map.put("jumlahdalamsak", hasilse1.getText().toString());
                map.put("metode", metode);
                map.put("hargapasirtotal", String.valueOf(numberpasir));
                map.put("hargasementotal", String.valueOf(numbersemen));
                map.put("hargapasir", hargapasir.getText().toString());
                map.put("hargasemen", hargasemen.getText().toString());
                Call<Void> call = ApiClient.getRequestInterface().actionCreateplesteran(apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            Intent Perhitunganplesteran = (new Intent(Tambahplesteran.this, Perhitunganplesteran.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            Bundle setData = new Bundle();
                            setData.putString("idproyek",mId);
                            Perhitunganplesteran.putExtras(setData);
                            startActivity(Perhitunganplesteran);
                            Toast.makeText(Tambahplesteran.this,
                                    "Tambah Data Perhitungan Plesteran Berhasil",
                                    Toast.LENGTH_LONG).show();

                        } else if (response.code() == 422) {
                            Toast.makeText(Tambahplesteran.this,
                                    "Something Wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Tambahplesteran.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    private void initvalidation() {
        if (panjang.getText().toString().length() == 0) {
            panjang.setError("field ini harus diisi!");

        } else if (tinggi.getText().toString().length() == 0) {
            tinggi.setError("field ini harus diisi!");
        } else if (sisi.getText().toString().length() == 0) {
            sisi.setError("field ini harus diisi!");

        } else if (tebal.getText().toString().length() == 0) {
        tebal.setError("field ini harus diisi!");

        }else if (hargasemen.getText().toString().length() == 0) {
            hargasemen.setError("field ini harus diisi!");

        } else if (hasilb.getText().toString().length() == 0) {
            hitung.setError("");
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Tambahplesteran.this);
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
        hasilvol = findViewById(R.id.hasilvol);
        hasilse2 = findViewById(R.id.hasilsemen2);
        hasilb = findViewById(R.id.totalbiaya);
        volume = findViewById(R.id.volume);
        hitung = findViewById(R.id.hitungb);
        campuran = findViewById(R.id.spinner22);
        spinjenis = findViewById(R.id.spinjenis);
        Jenispengerjaan_adapter jenispengerjaan_adapter =
                new Jenispengerjaan_adapter(Tambahplesteran.this,
                        android.R.layout.simple_spinner_item, jenispengerjaans);
        spinjenis.setAdapter(jenispengerjaan_adapter);
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
    }
}
