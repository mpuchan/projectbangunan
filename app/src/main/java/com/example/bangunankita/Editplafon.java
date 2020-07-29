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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangunankita.Model.Material;
import com.example.bangunankita.Model.ResponseMaterial;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Editplafon extends AppCompatActivity {
    private Spinner spintriplek,spinpaku,spinreng;
    private EditText Namapengerjaan,Panjang,Lebar,Luasplafon,Htriplek,Hreng,Hpaku;
    private TextView hasilpaku,hasilpaku1,hasilkayu,hasilkayu1,total,hasilkayu2,luas_plafon,hasiltriplek,hasiltriplek1,hasiltriplek2;
    private Button Hitungb,proses;
    String token;
    private String harpaku,hartriplek,harreng,namapaku,namatriplek,namareng;
    String mId,Ju;
    int ProyekID,thisid;
    private float hasil,luasba,koefplafon,koefpaku;
    Context mContext;
    private String luas1;
    SessionManager sm;
    private List<Material> Triplek = new ArrayList<>();
    private List<Material> Paku = new ArrayList<>();
    private List<Material> Kayu = new ArrayList<>();

    private String totpaku,tottriplek,totkayu;
    private int numberpaku,numbertriplek,numberkayu,hargatotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editplafon);
        init();
        initSpinnerPaku();
        initSpinnerTriplek();
        initSpinnerReng();
        proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String panjang1 = Panjang.getText().toString().trim();
                String lebar1 = Lebar.getText().toString().trim();

                float pb = Float.parseFloat(panjang1);
                float lb = Float.parseFloat(lebar1);
                hasil = (pb*lb);
                Luasplafon.setText(String.valueOf(hasil));
                luas1 = Luasplafon.getText().toString().trim();

            }
        });
        spinpaku.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                harpaku = String.valueOf(Paku.get(position).getHarga());
                namapaku = String.valueOf(Paku.get(position).getNama());
                Hpaku.setText(harpaku);

//                Toast.makeText(mContext, "Kamu memilih Pasir " + selectedName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spintriplek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                hartriplek = String.valueOf(Triplek.get(position).getHarga());
                namatriplek = String.valueOf(Triplek.get(position).getNama());
                Htriplek.setText(hartriplek);
                if (selectedName.contains("Triplek")){
                    koefplafon = 0.375f;
                    koefpaku = 0.03f;
                }else if (selectedName.contains("Gypsum")){
                    koefplafon = 0.364f;
                    koefpaku = 0.11f;
                }else if (selectedName.contains("Kalsiboard")) {
                    koefplafon = 0.364f;
                    koefpaku = 0.03f;
                }else{
                    koefplafon = 0.0375f;
                    koefpaku = 0.03f;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinreng.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                harreng = String.valueOf(Kayu.get(position).getHarga());
                namareng = String.valueOf(Kayu.get(position).getNama());
                Hreng.setText(harreng);

//                Toast.makeText(mContext, "Kamu memilih Pasir " + selectedName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Hitungb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                luasba = Float.parseFloat(luas1);
                luas_plafon.setText(String.valueOf(luasba));
                hitungtriplek();
                hitungkayu();
                hitungpaku();
                hitungtotal();
            }
        });

    }



    private void hitungtotal() {
        hargatotal = numberkayu+numbertriplek+numberpaku;
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(hargatotal);
        total.setText(totalbiaya);


    }

    private void hitungpaku() {
        float hargapku = Float.parseFloat(Hpaku.getText().toString());
        float kebpaku = luasba*koefpaku;
        float hargatotpaku = kebpaku*hargapku;
        DecimalFormat df = new DecimalFormat("#");
        totpaku = df.format(hargatotpaku);
        numberpaku = Integer.parseInt(totpaku);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numberpaku);
        hasilpaku.setText(String.valueOf(kebpaku));
        hasilpaku1.setText(totalbiaya);
    }

    private void hitungkayu() {
        float hargakayu = Float.parseFloat(Hreng.getText().toString());
        float koefkayu = 0.2289f*4;
        float keperluankayu = koefkayu*luasba;
        float pembulatan = (float) Math.ceil(keperluankayu);
        float hargatotkayu = pembulatan*hargakayu;
        DecimalFormat df = new DecimalFormat("#");
        totkayu = df.format(hargatotkayu);
        numberkayu = Integer.parseInt(totkayu);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numberkayu);
        hasilkayu.setText(String.valueOf(keperluankayu));
        hasilkayu1.setText(String.valueOf(pembulatan));
        hasilkayu2.setText(totalbiaya);
    }

    private void hitungtriplek() {
        float hargatriplek = Float.parseFloat(Htriplek.getText().toString());
        float kebtriplek = luasba*koefplafon;
        float pembulatan = (float) Math.ceil(kebtriplek);
        float hargatottriplek = hargatriplek*pembulatan;
        DecimalFormat df = new DecimalFormat("#");
        tottriplek = df.format(hargatottriplek);
        numbertriplek = Integer.parseInt(tottriplek);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numbertriplek);
        hasiltriplek.setText(String.valueOf(kebtriplek));
        hasiltriplek1.setText(String.valueOf(pembulatan));
        hasiltriplek2.setText(totalbiaya);

    }

    private void initSpinnerReng() {
        ApiClient.getRequestInterface().getallkayu().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    Kayu = response.body().getMaterials();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < Kayu.size(); i++){
                        listSpinner.add(Kayu.get(i).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinreng.setAdapter(adapter);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data Reng/Hollow", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMaterial> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initSpinnerTriplek() {
        ApiClient.getRequestInterface().getalltriplek().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    Triplek = response.body().getMaterials();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < Triplek.size(); i++){
                        listSpinner.add(Triplek.get(i).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spintriplek.setAdapter(adapter);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data Triplek", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMaterial> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initSpinnerPaku() {
        ApiClient.getRequestInterface().getallpaku().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    Paku = response.body().getMaterials();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < Paku.size(); i++){
                        listSpinner.add(Paku.get(i).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinpaku.setAdapter(adapter);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data Paku", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMaterial> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;
        proses = findViewById(R.id.prosesbtn);
        hasilpaku = findViewById(R.id.hasilpaku);
        hasilpaku1 = findViewById(R.id.hasilpaku1);
        hasiltriplek = findViewById(R.id.hasiltriplek);
        hasiltriplek1 = findViewById(R.id.hasiltriplek1);
        hasiltriplek2 = findViewById(R.id.hasiltriplek2);
        hasilkayu = findViewById(R.id.hasilreng);
        hasilkayu1 = findViewById(R.id.hasilreng1);
        hasilkayu2 = findViewById(R.id.hasilreng2);
        spintriplek = findViewById(R.id.spintriplek);
        spinpaku = findViewById(R.id.spinpaku);
        spinreng = findViewById(R.id.pilihreng);
        Namapengerjaan = findViewById(R.id.nama_pengerjaan1);
        Panjang = findViewById(R.id.panjangb);
        Lebar = findViewById(R.id.lebarb);
        Luasplafon = findViewById(R.id.luasplafon);
        luas_plafon = findViewById(R.id.luasplafon1);
        Htriplek = findViewById(R.id.htriplek);
        Hpaku = findViewById(R.id.hpaku);
        Hreng = findViewById(R.id.hreng);
        total = findViewById(R.id.total);
        Hitungb = findViewById(R.id.hitungb);
        sm= new SessionManager(Editplafon.this);
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        sm.checkLogin();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            mId = bundle.getString("ProyekId");
        }else{
            mId = "0";
        }

        thisid = Integer.parseInt(bundle.getString("Id"));
        Namapengerjaan.setText(bundle.getString("nama"));
        Panjang.setText(bundle.getString("panjang"));
        Lebar.setText(bundle.getString("lebar"));
        Luasplafon.setText(bundle.getString("luas"));
//        Namapengerjaan.setText(bundle.getString("namatriplek"));
//        Namapengerjaan.setText(bundle.getString("namapaku"));
//        Namapengerjaan.setText(bundle.getString("namareng"));

        Htriplek.setText(bundle.getString("hargatriplek"));
        Hpaku.setText(bundle.getString("hargapaku"));
        Hreng.setText(bundle.getString("hargareng"));
        hasiltriplek.setText(bundle.getString("jumlahtriplek"));
        hasilpaku.setText(bundle.getString("jumlahpaku"));
        float hargatotpaku = Float.parseFloat(bundle.getString("hargapakutotal"));
        DecimalFormat df = new DecimalFormat("#");
        totpaku = df.format(hargatotpaku);
        numberpaku = Integer.parseInt(totpaku);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiayapaku = formatter.format(numberpaku);
        hasilpaku1.setText(totalbiayapaku);
        float hargatotkayu = Float.parseFloat(bundle.getString("hargarengtotal"));
        DecimalFormat df1 = new DecimalFormat("#");
        totkayu = df1.format(hargatotkayu);
        numberkayu = Integer.parseInt(totkayu);
        DecimalFormat formatter1 = new DecimalFormat("#,###.##");
        String totalbiayareng = formatter1.format(numberkayu);
        hasilkayu2.setText(totalbiayareng);
        float hargatottriplek = Float.parseFloat(bundle.getString("hargatriplektotal"));
        DecimalFormat df2 = new DecimalFormat("#");
        tottriplek = df2.format(hargatottriplek);
        numbertriplek = Integer.parseInt(tottriplek);
        DecimalFormat formatter2 = new DecimalFormat("#,###.##");
        String totalbiayatriplek = formatter2.format(numbertriplek);
        hasiltriplek2.setText(totalbiayatriplek);
        hasiltriplek1.setText(bundle.getString("jumlahtripleklembar"));
        hasilkayu1.setText(bundle.getString("jumlahrengbatang"));
        float hargatotal1 = Float.parseFloat(bundle.getString("hargatotal"));
        DecimalFormat formatter3 = new DecimalFormat("#,###.##");
        String totalbiaya = formatter3.format(hargatotal1);
        total.setText(totalbiaya);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mainsavebidang,menu);
        MenuItem item = menu.findItem(R.id.app_bar_savedata);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String apiKey = "oa00000000app";
//                initvalidation();
                HashMap<String, String> map = new HashMap<>();
                map.put("ProyekId", mId);
                map.put("nama", Namapengerjaan.getText().toString());
                map.put("panjang", Panjang.getText().toString());
                map.put("lebar", Lebar.getText().toString());
                map.put("namapaku", namapaku);
                map.put("namareng", namareng);
                map.put("namatriplek", namatriplek);
                map.put("hargatriplek", Htriplek.getText().toString());
                map.put("hargapaku", Hpaku.getText().toString());
                map.put("hargareng", Hreng.getText().toString());
                map.put("jumlahpaku",hasilpaku.getText().toString());
                map.put("jumlahreng",hasilkayu.getText().toString());
                map.put("jumlahrengbatang",hasilkayu1.getText().toString() );
                map.put("jumlahtriplek", hasiltriplek.getText().toString());
                map.put("jumlahtripleklembar", hasiltriplek1.getText().toString());
                map.put("hargatotaltriplek", tottriplek);
                map.put("hargatotalpaku", totpaku);
                map.put("hargatotalreng", totkayu);
                map.put("hargatotal", String.valueOf(hargatotal));

                Call<Void> call = ApiClient.getRequestInterface().actionPutPerhitunganplafon(thisid,apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {
                            Intent Perhitunganplafon = (new Intent(Editplafon.this, Perhitunganplafond.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            Bundle setData = new Bundle();
                            setData.putString("idproyek",mId);
                            Perhitunganplafon.putExtras(setData);
                            startActivity(Perhitunganplafon);
                            Toast.makeText(Editplafon.this,
                                    "Edit Data Perhitungan Plafon Berhasil ",
                                    Toast.LENGTH_LONG).show();

                        } else if (response.code() == 422) {
                            Toast.makeText(Editplafon.this,
                                    "Something Wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Editplafon.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }
    }

