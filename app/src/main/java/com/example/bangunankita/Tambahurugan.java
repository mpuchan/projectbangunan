package com.example.bangunankita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import com.example.bangunankita.Model.Jenispengerjaan;
import com.example.bangunankita.Model.Material;
import com.example.bangunankita.Model.Perhitunganbidang1;
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

public class Tambahurugan extends AppCompatActivity {
    Spinner spindinding,spinurugan,spinjenis;
    EditText panjang,tinggi,lebar,hargaurugan,vol,nama;
    TextView hasilpas,hasilpas1,hasilpas2,hasilb,hasilvol,totalbiaya;
    Button prosesbtn,hitung;
    float hasil,volumeurugan,totpasir,hargasementot,pembulatanurugan;
    Context mContext;
    SessionManager sm;
    String token,nama1,p,t,jenis;
    String mId,Ju;
    int ProyekID;
    private int numberpasir;
    private String totpasir1;
    public String hs,ids,hp,idp,berats,pc,pp,namaurugan;
    private List<Perhitunganbidang1> BidangModel = new ArrayList<>();
    private List<Material> Pasir = new ArrayList<>();
    Jenispengerjaan[] jenispengerjaans  ={
            new Jenispengerjaan("Urugan Taman"),
            new Jenispengerjaan("Pondasi Urugan"),
            new Jenispengerjaan("Lainnya"),
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahurugan);
        init();
        initSpinnerPasir();
        prosesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String panjang1 = panjang.getText().toString().trim();
                String tinggi1 = tinggi.getText().toString().trim();
                String lebar1 = lebar.getText().toString().trim();

                if (panjang.getText().toString().length() == 0) {
                    panjang.setError("Data ini harus diisi");
                    panjang1 = "0";
                }else if (tinggi.getText().toString().length() == 0) {
                    tinggi.setError("Data ini harus diisi");
                    tinggi1 = "0";
                }else if (lebar.getText().toString().length() == 0) {
                    lebar.setError("Data ini harus diisi");
                    lebar1 = "0";
                }

                float pp = Float.parseFloat(panjang1);
                float tp = Float.parseFloat(tinggi1);
                float lp = Float.parseFloat(lebar1);

                hasil = pp*lp*tp;
                vol.setText(String.valueOf(hasil));

            }
        });
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vol1 = vol.getText().toString().trim();
                volumeurugan = Float.parseFloat(vol1);
                hasilvol.setText(vol1);
                hitungpasir();
            }
        });
        spinurugan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                namaurugan = String.valueOf(Pasir.get(position).getNama());
                hp = String.valueOf(Pasir.get(position).getHarga());
                idp = String.valueOf(Pasir.get(position).getId());

                hargaurugan.setText(hp);

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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void hitungpasir() {
        float kepadatan = 1.2f;
        float hargapas = Float.parseFloat(hp);
        float hitpp = kepadatan*volumeurugan;
//        float pembulatanpasir = (float) Math.ceil(hitpp);

        float pembulatanurugan = (float) Math.ceil(hitpp);
        float urugandalamtruk = pembulatanurugan/7f;
        float pembulatanurugantruk = (float) Math.ceil(urugandalamtruk);
        totpasir = pembulatanurugan*hargapas;
        DecimalFormat df1 = new DecimalFormat("#");
        totpasir1 = df1.format(totpasir);
        numberpasir = Integer.parseInt(totpasir1);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya1 = formatter.format(numberpasir);

        hasilvol.setText(Float.toString(volumeurugan));
        hasilpas2.setText(totalbiaya1);
        totalbiaya.setText(totalbiaya1);
        hasilpas1.setText(Float.toString(pembulatanurugantruk));
        hasilpas.setText(Float.toString(pembulatanurugan));
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
                    spinurugan.setAdapter(adapter);
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
                map.put("nama", nama.getText().toString());
                map.put("jenis_pengerjaan", jenis);
                map.put("panjang", panjang.getText().toString());
                map.put("lebar", lebar.getText().toString());
                map.put("tinggi", tinggi.getText().toString());
                map.put("volume", hasilvol.getText().toString());
                map.put("volumejadi", hasilpas.getText().toString());
                map.put("nama_pasir", namaurugan);
                map.put("Jumlahkeperluanpasir", hasilpas.getText().toString());
                map.put("jumlahdalamtruk", hasilpas1.getText().toString());
                map.put("hargapasir", hargaurugan.getText().toString());
                map.put("hargapasirtotal", String.valueOf(numberpasir));
                map.put("hargatotal", String.valueOf(numberpasir));
                Call<Void> call = ApiClient.getRequestInterface().actionCreateurugan(apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            Intent Perhitunganurugan = (new Intent(Tambahurugan.this, Perhitunganurugan.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            Bundle setData = new Bundle();
                            setData.putString("idproyek",mId);
                            Perhitunganurugan.putExtras(setData);
                            startActivity(Perhitunganurugan);
                            Toast.makeText(Tambahurugan.this,
                                    "Tambah Data Perhitungan Plesteran Berhasil",
                                    Toast.LENGTH_LONG).show();

                        } else if (response.code() == 422) {
                            Toast.makeText(Tambahurugan.this,
                                    "Something Wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Tambahurugan.this, t.getMessage(),
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
        } else if (lebar.getText().toString().length() == 0) {
            lebar.setError("field ini harus diisi!");

        } else if (hargaurugan.getText().toString().length() == 0) {
            hargaurugan.setError("field ini harus diisi!");

        } else if (hasilvol.getText().toString().length() == 0) {
            hitung.setError("");
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Tambahurugan.this);
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
        spinurugan = findViewById(R.id.spinurug);
        panjang = findViewById(R.id.panjangurugan);
        tinggi = findViewById(R.id.tinggiurugan);
        lebar = findViewById(R.id.lebarurugan);
        hargaurugan = findViewById(R.id.hargaurug);
        hitung = findViewById(R.id.hitungb);
        vol = findViewById(R.id.volume);
        hasilpas = findViewById(R.id.hasilurugan);
        hasilpas1 = findViewById(R.id.hasilpasirtruk);
        hasilpas2 = findViewById(R.id.hasilhargap);
        hasilvol = findViewById(R.id.hasilvol);
        totalbiaya = findViewById(R.id.totalbiaya);
        nama = findViewById(R.id.nama_pengerjaan);
        spinjenis = findViewById(R.id.spinjenis);
        Jenispengerjaan_adapter jenispengerjaan_adapter =
                new Jenispengerjaan_adapter(Tambahurugan.this,
                        android.R.layout.simple_spinner_item, jenispengerjaans);
        spinjenis.setAdapter(jenispengerjaan_adapter);
        sm= new SessionManager(Tambahurugan.this);
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
        Toast.makeText(Tambahurugan.this, "Proyek Id"+mId,
                Toast.LENGTH_SHORT).show();

    }

}
