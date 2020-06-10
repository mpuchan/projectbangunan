package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangunankita.Model.Batako;
import com.example.bangunankita.Model.Campuran;
import com.example.bangunankita.Model.Pasir;
import com.example.bangunankita.Model.Pengembang_model;
import com.example.bangunankita.Model.Proyek_model;
import com.example.bangunankita.Model.ResponseBatako;
import com.example.bangunankita.Model.ResponsePasir;
import com.example.bangunankita.Model.ResponseSemen;
import com.example.bangunankita.Model.Semen;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.adapter.Campuran_adapter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tambahbidang extends AppCompatActivity {
    private EditText panjangb,tinggib,panjangp,tinggip,panjangj,tinggij,luasb;
    private EditText hargab,hargas,hargap;
    private TextView hasilb;
    private Button btnproses,hitung;
    private Spinner spinbatako,campuran,spinsemen,spinpasir;
    public String pbatako,tbatako,hb,idb,hs,ids,hp,idp,berats,pc,pp;
    float hasilm;
    public int hasil;
    private List<Batako> Batako = new ArrayList<>();
    private List<com.example.bangunankita.Model.Semen> Semen = new ArrayList<>();
    private List<com.example.bangunankita.Model.Pasir> Pasir = new ArrayList<>();
    Context mContext;
    Campuran[] campurans ={
            new Campuran("1:3", 14.37,0.04),
            new Campuran("1:4", 11.5,0.043),
            new Campuran("1:5", 9.68,0.045),
            new Campuran("1:6", 8.32,0.049),
            new Campuran("1:8", 6.5,0.05)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahbidang);
        init();
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

                    int pb = Integer.parseInt(panjang1);
                    int tb = Integer.parseInt(tinggi1);
                    int pp = Integer.parseInt(panjang2);
                    int tp = Integer.parseInt(tinggi2);
                    int pj = Integer.parseInt(panjang3);
                    int tj = Integer.parseInt(tinggi3);
                    hasil = (pb*tb)-(pp*tp)- (pj*tj);
                    luasb.setText(String.valueOf(hasil));
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
                hargab.setText(hb);


//                requestDetailDosen(selectedName);
                Toast.makeText(mContext, "Kamu memilih Batako " + selectedName, Toast.LENGTH_SHORT).show();
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
                hargas.setText(hs);

                Toast.makeText(mContext, "Kamu memilih Semen " + selectedName, Toast.LENGTH_SHORT).show();
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

                hargap.setText(hp);

                Toast.makeText(mContext, "Kamu memilih Pasir " + selectedName, Toast.LENGTH_SHORT).show();
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

                Toast.makeText(mContext, "Kamu memilih Campuran " + pc, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//             hitungbatako();
                hitungsemen();
//                hitungpasir();
            }
        });
    }

    private void hitungpasir() {
        float ppasir = Float.parseFloat(pc);
        float hargapas = Float.parseFloat(hp);
        float hitpp = ppasir*hasil;
        float pembulatanpasir = (float) Math.ceil(hitpp);
        float totpasir = pembulatanpasir*hargapas;
        Toast.makeText(mContext, "Gagal mengambil data"+ppasir, Toast.LENGTH_SHORT).show();
        hasilb.setText(Float.toString(ppasir));
    }

    private void hitungsemen() {
        float psemen = Float.parseFloat(pc);
        float beratsemen = Float.parseFloat(berats);
        float hargasemen = Float.parseFloat(hs);
        float hitpc = psemen*hasil;
        float totpc = hitpc/beratsemen;
        float pembulatansemen = (float) Math.ceil(totpc);
        float hargasementot = pembulatansemen*hargasemen;
        hasilb.setText(Float.toString(hargasementot));

    }


    private void hitungbatako() {
        float m = 100;
        float p = Float.parseFloat(pbatako);
        float t = Float.parseFloat(tbatako);
        float hargabatako = Float.parseFloat(hb);
        hasilm = m/(p*t)*m;
        float tot = hasilm*hasil;
        float htotbatako = tot*hargabatako;
//        hasilb.setText(Float.toString(htotbatako));
    }

    private void initSpinnerPasir() {
        ApiClient.getRequestInterface().getallsemen().enqueue(new Callback<ResponseSemen>() {

        @Override
        public void onResponse(Call<ResponseSemen> call, Response<ResponseSemen> response) {
            if (response.code() == 200) {
                Semen = response.body().getSemens();
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
        public void onFailure(Call<ResponseSemen> call, Throwable t) {
            Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
        }
    });
    }
    private void initSpinnerSemen() {
        ApiClient.getRequestInterface().getallpasir().enqueue(new Callback<ResponsePasir>() {

            @Override
            public void onResponse(Call<ResponsePasir> call, Response<ResponsePasir> response) {
                if (response.code() == 200) {
                    Pasir = response.body().getPasirs();
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
            public void onFailure(Call<ResponsePasir> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSpinnerBatako() {
        ApiClient.getRequestInterface().getallbatako().enqueue(new Callback<ResponseBatako>() {

            @Override
            public void onResponse(Call<ResponseBatako> call, Response<ResponseBatako> response) {
                if (response.code() == 200) {
                    Batako = response.body().getBatakos();

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
            public void onFailure(Call<ResponseBatako> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void init() {
        mContext = this;
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
        hasilb = findViewById(R.id.hasilb);
        campuran = findViewById(R.id.spinner22);
        Campuran_adapter campuran_adapter =
                new Campuran_adapter(Tambahbidang.this,
                        android.R.layout.simple_spinner_item, campurans);
        campuran.setAdapter(campuran_adapter);
        float luas;
        btnproses = findViewById(R.id.prosesbtn);
    }

}
