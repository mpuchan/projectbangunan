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

public class Tambahurugan extends AppCompatActivity {
    Spinner spindinding,spinurugan;
    EditText panjang,tinggi,lebar,hargaurugan,vol;
    TextView hasilpas,hasilpas1,hasilpas2,hasilb;
    Button prosesbtn,hitung;
    float hasil,volumeurugan,totpasir,hargasementot;
    Context mContext;
    SessionManager sm;
    String token,nama,p,t;
    String mId,Ju;
    int ProyekID;
    public String hs,ids,hp,idp,berats,pc,pp;
    private List<Perhitunganbidang1> BidangModel = new ArrayList<>();
    private List<Material> Pasir = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahurugan);
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
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vol1 = vol.getText().toString().trim();
                volumeurugan = Float.parseFloat(vol1);
                hitungpasir();
            }
        });
        spinurugan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                hp = String.valueOf(Pasir.get(position).getHarga());
                idp = String.valueOf(Pasir.get(position).getId());

                hargaurugan.setText(hp);

//                Toast.makeText(mContext, "Kamu memilih Pasir " + selectedName, Toast.LENGTH_SHORT).show();
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
        hasilpas2.setText(Float.toString(totpasir));
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


}
