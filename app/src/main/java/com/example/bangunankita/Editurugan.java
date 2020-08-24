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

public class Editurugan extends AppCompatActivity {
    Spinner spindinding,spinurugan,spinjenis;
    EditText panjang,tinggi,lebar,hargaurugan,vol,nama;
    TextView hasilpas,hasilpas1,hasilpas2,hasilb,totalbiaya,hasilvol;
    Button prosesbtn,hitung;
    float hasil,volumeurugan,totpasir,hargasementot,pembulatanurugan,hargapasirparse;
    private String totpasir1;
    private int numberpasir;
    Context mContext;
    SessionManager sm;
    String token,nama1,p,t,idproyek,jenis;
    String mId,Ju;
    int ProyekID,idurugan1;
    ProgressDialog pd;
    public String hs,ids,hp,idp,berats,pc,pp,namapasir,hpasir,namapasir1,idurugan;
    private List<Perhitunganbidang1> BidangModel = new ArrayList<>();
    private List<Material> Pasir = new ArrayList<>();
    Jenispengerjaan[] jenispengerjaans  ={
            new Jenispengerjaan("Urugan Taman"),
            new Jenispengerjaan("Urugan Pondasi"),
            new Jenispengerjaan("Lainnya"),
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editurugan);
        init();
        pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();
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

        spinurugan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();

                if (selectedName.equalsIgnoreCase(namapasir1)){
                    namapasir = namapasir1;
                    hargaurugan.setText(hpasir);


                }else {
                    namapasir = String.valueOf(Pasir.get(position).getNama());
                    hp = String.valueOf(Pasir.get(position).getHarga());
                    hargaurugan.setText(hp);
                }

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
                String hargapasir = hargaurugan.getText().toString().trim();
                hargapasirparse = Float.parseFloat(hargapasir);
                namapasir1 = namapasir;
                hitungpasir();
                totalbiaya=hasilpas2;
            }
        });

    }

    private void hitungpasir() {
        float kepadatan = 1.2f;
        float hitpp = kepadatan*volumeurugan;
//        float pembulatanpasir = (float) Math.ceil(hitpp);

        float pembulatanurugan = (float) Math.ceil(hitpp);
        float urugandalamtruk = pembulatanurugan/7f;
        float pembulatanurugantruk = (float) Math.ceil(urugandalamtruk);
        totpasir = pembulatanurugan*hargapasirparse;
        DecimalFormat df1 = new DecimalFormat("#");
        totpasir1 = df1.format(totpasir);
        numberpasir = Integer.parseInt(totpasir1);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiayaurugan = formatter.format(numberpasir);

        hasilvol.setText(Float.toString(volumeurugan));
        hasilvol.setText(Float.toString(volumeurugan));
        hasilpas2.setText(totalbiayaurugan);
        hasilpas1.setText(Float.toString(pembulatanurugantruk));
        hasilpas.setText(Float.toString(pembulatanurugan));
        totalbiaya.setText(totalbiayaurugan);
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
                    spinurugan.setAdapter(adapter);
                    spinurugan.setSelection(i);
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
                map.put("Jumlahkeperluanpasir", hasilpas.getText().toString());
                map.put("jumlahdalamtruk", hasilpas1.getText().toString());
                map.put("hargapasir", hargaurugan.getText().toString());
                map.put("hargapasirtotal", String.valueOf(numberpasir));
                map.put("hargatotal", String.valueOf(numberpasir));
                Call<Void> call = ApiClient.getRequestInterface().actionPutPerhitunganurugan(idurugan1,apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {
                            pd.hide();
                            Intent perhitunganurugan = (new Intent(Editurugan.this, Perhitunganurugan.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            Bundle setData = new Bundle();
                            setData.putString("idproyek",mId);
                            perhitunganurugan.putExtras(setData);
                            startActivity(perhitunganurugan);
                            Toast.makeText(Editurugan.this,
                                    "Data Perhitungan Urugan Berhasil di Update",
                                    Toast.LENGTH_LONG).show();

                        } else if (response.code() == 422) {
                            Toast.makeText(Editurugan.this,
                                    "Something Wrong",
//                                    "Terjadi kesalahan saat menyimpan data, ini bisa terjadi ketika terdapat data yang belum terisi",
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
        spinjenis = findViewById(R.id.spinjenis);
        Jenispengerjaan_adapter jenispengerjaan_adapter =
                new Jenispengerjaan_adapter(Editurugan.this,
                        android.R.layout.simple_spinner_item, jenispengerjaans);
        spinjenis.setAdapter(jenispengerjaan_adapter);

        sm= new SessionManager(Editurugan.this);
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
//        Toast.makeText(Editurugan.this, "Proyek Id"+mId,
//                Toast.LENGTH_SHORT).show();
        idurugan= bundle.getString("Id");
        nama.setText(bundle.getString("nama"));
        bundle.getString("jenis_pengerjaan");
        panjang.setText(bundle.getString("panjang"));
        tinggi.setText(bundle.getString("tinggi"));
        lebar.setText(bundle.getString("lebar"));
        vol.setText(bundle.getString("volume"));
        hasilvol.setText(bundle.getString("volumejadi"));
        hasilpas.setText(bundle.getString("jumlahkeperluanpasir"));
        hasilpas1.setText(bundle.getString("jumlahdalamtruk"));
        namapasir1 = bundle.getString("nama_pasir");
        hpasir= bundle.getString("hargapasir");
        totpasir = Float.parseFloat(bundle.getString("hargapasirtotal"));
        DecimalFormat df1 = new DecimalFormat("#");
        totpasir1 = df1.format(totpasir);
        numberpasir = Integer.parseInt(totpasir1);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiayaurugan = formatter.format(numberpasir);
        hasilpas2.setText(totalbiayaurugan);
        totalbiaya.setText(totalbiayaurugan);
    }
}
