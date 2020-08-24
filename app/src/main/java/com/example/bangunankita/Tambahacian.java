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

import com.example.bangunankita.Model.Jenispengerjaan;
import com.example.bangunankita.Model.Material;
import com.example.bangunankita.Model.Perhitunganbidang1;
import com.example.bangunankita.Model.ResponseBidang;
import com.example.bangunankita.Model.ResponseMaterial;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Jenispengerjaan_adapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tambahacian extends AppCompatActivity {
    Spinner spindinding,spinsemen,spinjenis;
    EditText panjang,tinggi,sisi,hargasemen,luas;
    TextView hasilse,hasilse1,hasilse2,hasilb,hasilvol;
    Button prosesbtn,hitung;
    float hasil,luasba,totpasir,hargasementot,psemen;
    Context mContext;
    SessionManager sm;

    String token,nama,p,t,namapengerjaan,namasemen1,jenis;
    String mId,Ju;
    ProgressDialog pd;
    int ProyekID;
    public String hs,ids,hp,idp,berats,pc,pp;
    private int numbersemen;
    private String totsemen1;
    private List<Perhitunganbidang1> BidangModel = new ArrayList<>();
    private List<Material> Semen = new ArrayList<>();
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
        setContentView(R.layout.activity_tambahacian);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;
        pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();
        spinsemen = findViewById(R.id.spinsemen);
        spindinding = findViewById(R.id.spindinding);
        panjang = findViewById(R.id.panjangpl);
        tinggi = findViewById(R.id.tinggipl);
        sisi = findViewById(R.id.sisi);
        hargasemen = findViewById(R.id.hargasemen);
        hasilvol = findViewById(R.id.hasilvol);
        luas = findViewById(R.id.luas);
        hasilse = findViewById(R.id.hasilsemen);
        hasilse1 = findViewById(R.id.hasilsemensak);
        hasilse2 = findViewById(R.id.hasilsemen2);
        hasilb = findViewById(R.id.totalbiaya);
        prosesbtn = findViewById(R.id.prosesbtn);
        hitung = findViewById(R.id.hitungb);
        spinjenis = findViewById(R.id.spinjenis);
        Jenispengerjaan_adapter jenispengerjaan_adapter =
                new Jenispengerjaan_adapter(Tambahacian.this,
                        android.R.layout.simple_spinner_item, jenispengerjaans);
        spinjenis.setAdapter(jenispengerjaan_adapter);

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

                if (panjang.getText().toString().length() == 0) {
                    panjang.setError("Data ini harus diisi");
                    panjang1 = "0";
                }else if (tinggi.getText().toString().length() == 0) {
                    tinggi.setError("Data ini harus diisi");
                    tinggi1 = "0";
                }else if (sisi.getText().toString().length() == 0) {
                    sisi.setError("Data ini harus diisi");
                    sisi1 = "0";
                }
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
                hasilvol.setText(luas1);
                hitungsemen();
            }
        });


        spindinding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                namapengerjaan = String.valueOf(BidangModel.get(position).getNama());
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
        spinsemen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                berats = String.valueOf(Semen.get(position).getBerat());
                hs = String.valueOf(Semen.get(position).getHarga());
                ids = String.valueOf(Semen.get(position).getId());
                namasemen1= String.valueOf(Semen.get(position).getNama());
                hargasemen.setText(hs);

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
    private void hitungsemen() {
        psemen = 3.25f;
        float beratsemen = Float.parseFloat(berats);
        float hargasemen = Float.parseFloat(hs);
        float hitpc = psemen*luasba;
        float totpc = hitpc/beratsemen;
        hargasementot = totpc*hargasemen;
        DecimalFormat df1 = new DecimalFormat("#");
        totsemen1 = df1.format(hargasementot);
        numbersemen = Integer.parseInt(totsemen1);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numbersemen);
        hasilse.setText(Float.toString(hitpc));
        hasilse1.setText(Float.toString(totpc));
        hasilse2.setText(totalbiaya);
        hasilb.setText(totalbiaya);
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
                        pd.hide();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mainsavebidang,menu);
        MenuItem item = menu.findItem(R.id.app_bar_savedata);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String apiKey = "oa00000000app";
                initvalidation();
                pd.show();
                HashMap<String, String> map = new HashMap<>();
                map.put("ProyekId", mId);
                map.put("nama", namapengerjaan);
                map.put("jenis_pengerjaan", jenis);
                map.put("panjangdin", panjang.getText().toString());
                map.put("tinggidin", tinggi.getText().toString());
                map.put("sisi", sisi.getText().toString());
                map.put("luas", luas.getText().toString());
                map.put("nama_semen", namasemen1);
                map.put("Jumlahkeperluansemen", hasilse.getText().toString());
                map.put("jumlahdalamsak", hasilse1.getText().toString());
                map.put("metode", String.valueOf(psemen));
                map.put("hargasemen", hargasemen.getText().toString());
                map.put("hargasementotal", String.valueOf(numbersemen));
                map.put("hargatotal", String.valueOf(numbersemen));
                Call<Void> call = ApiClient.getRequestInterface().actionCreateacian(apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            pd.hide();
                            Intent Perhitunganacian = (new Intent(Tambahacian.this, Perhitunganacian.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            Bundle setData = new Bundle();
                            setData.putString("idproyek",mId);
                            Perhitunganacian.putExtras(setData);
                            startActivity(Perhitunganacian);
                            Toast.makeText(Tambahacian.this,
                                    "Tambah Data Perhitungan Bidang Berhasil",
                                    Toast.LENGTH_LONG).show();


                        } else if (response.code() == 422) {
                            Toast.makeText(Tambahacian.this,
                                    "Something Wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Tambahacian.this, t.getMessage(),
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

        } else if (hargasemen.getText().toString().length() == 0) {
            hargasemen.setError("field ini harus diisi!");

        } else if (hasilvol.getText().toString().length() == 0) {
            hitung.setError("");
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Tambahacian.this);
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
}
