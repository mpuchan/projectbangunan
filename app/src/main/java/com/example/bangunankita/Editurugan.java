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
import com.example.bangunankita.Model.Perhitunganbidang1;
import com.example.bangunankita.Model.ResponseMaterial;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Editurugan extends AppCompatActivity {
    Spinner spindinding,spinurugan;
    EditText panjang,tinggi,lebar,hargaurugan,vol,nama;
    TextView hasilpas,hasilpas1,hasilpas2,hasilb,totalbiaya,hasilvol;
    Button prosesbtn,hitung;
    float hasil,volumeurugan,totpasir,hargasementot,pembulatanurugan;
    Context mContext;
    SessionManager sm;
    String token,nama1,p,t,idproyek;
    String mId,Ju;
    int ProyekID,idurugan1;
    public String hs,ids,hp,idp,berats,pc,pp,namapasir,hpasir,namapasir1,idurugan;
    private List<Perhitunganbidang1> BidangModel = new ArrayList<>();
    private List<Material> Pasir = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editurugan);
        init();
        initSpinnerPasir();

        prosesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String panjang1 = panjang.getText().toString().trim();
                String tinggi1 = tinggi.getText().toString().trim();
                String lebar1 = lebar.getText().toString().trim();

                float pp = Float.parseFloat(panjang1);
                float tp = Float.parseFloat(tinggi1);
                float lp = Float.parseFloat(lebar1);

                hasil = pp*lp*tp;
                vol.setText(String.valueOf(hasil));

            }
        });

        spinurugan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                 namapasir = String.valueOf(Pasir.get(position).getNama());
                hp = String.valueOf(Pasir.get(position).getHarga());
                idp = String.valueOf(Pasir.get(position).getId());

                hargaurugan.setText(hp);

//                Toast.makeText(mContext, "Kamu memilih Pasir " + selectedName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vol1 = vol.getText().toString().trim();
                volumeurugan = Float.parseFloat(vol1);
                hpasir = hp;
                namapasir1 = namapasir;
                hitungpasir();
                totalbiaya=hasilpas2;
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
        hasilvol.setText(Float.toString(volumeurugan));
        hasilpas2.setText(Float.toString(totpasir));
        hasilpas1.setText(Float.toString(pembulatanurugantruk));
        hasilpas.setText(Float.toString(pembulatanurugan));
        totalbiaya.setText(Float.toString(totpasir));
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
                idurugan1 = Integer.parseInt(idurugan);
                String apiKey = "oa00000000app";
                HashMap<String, String> map = new HashMap<>();
                map.put("ProyekId", mId);
                map.put("nama", nama.getText().toString());
                map.put("jenis_pengerjaan", "jenis");
                map.put("panjang", panjang.getText().toString());
                map.put("lebar", lebar.getText().toString());
                map.put("tinggi", tinggi.getText().toString());
                map.put("volume", vol.getText().toString());
                map.put("volumejadi", hasilvol.getText().toString());
                map.put("nama_pasir", namapasir1);
                map.put("Jumlahkeperluanpasir", hasilpas1.getText().toString());
                map.put("jumlahdalamtruk", hasilpas1.getText().toString());
                map.put("hargapasir", hasilpas2.getText().toString());
                map.put("hargatotal", totalbiaya.getText().toString());
                Call<Void> call = ApiClient.getRequestInterface().actionPutPerhitunganurugan(idurugan1,apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {

                            Intent perhitunganurugan = (new Intent(Editurugan.this, Perhitunganurugan.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            Bundle setData = new Bundle();
                            setData.putString("idproyek",idproyek);
                            perhitunganurugan.putExtras(setData);
                            startActivity(perhitunganurugan);
                            Toast.makeText(Editurugan.this,
                                    "Data Perhitungan Urugan Berhasil di Update",
                                    Toast.LENGTH_LONG).show();

                        } else if (response.code() == 422) {
                            Toast.makeText(Editurugan.this,
                                    "Something Wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Editurugan.this, t.getMessage(),
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
        nama = findViewById(R.id.nama_pengerjaan);
        totalbiaya = findViewById(R.id.totalbiaya);
        hasilvol = findViewById(R.id.hasilvol);

        sm= new SessionManager(Editurugan.this);
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        sm.checkLogin();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            mId = bundle.getString("ProyekID");
            Ju = mId;
        }else{
            mId = "0";
        }
        Toast.makeText(Editurugan.this, "Proyek Id"+mId,
                Toast.LENGTH_SHORT).show();
        idurugan= bundle.getString("Id");
        nama.setText(bundle.getString("nama"));
        idproyek = bundle.getString("ProyekId");
        bundle.getString("jenis_pengerjaan");
        panjang.setText(bundle.getString("panjang"));
        tinggi.setText(bundle.getString("tinggi"));
        lebar.setText(bundle.getString("lebar"));
        vol.setText(bundle.getString("volume"));
        hasilvol.setText(bundle.getString("volumejadi"));
        hasilpas1.setText(bundle.getString("jumlahkeperluanpasir"));
        hasilpas1.setText(bundle.getString("jumlahdalamtruk"));
        namapasir1 = bundle.getString("nama_pasir");
        hpasir= bundle.getString("hargapasir");
        hasilpas2.setText(bundle.getString("hargapasirtotal"));
        totalbiaya.setText(bundle.getString("hargatotal"));
    }
}
