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
import com.example.bangunankita.Model.ResponseMaterial;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Jenispengerjaan_adapter;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Editbeton extends AppCompatActivity {

    private Spinner spinpapan,spinbesi,spinkawat,spinpaku,spinbegel,spinpasir,spinsemen,spinbatu,spinjenis;
    private EditText Nama,panjangbeton,Hpasir,Hsemen,Hpapan,Hbesi,Hkawat,Hpaku,Hbegel,Hbatu;
    private TextView hasils,hasils1,hasils2,hasilp,hasilp1,hasilp2,hasilbatu,hasilbatu12,hasilbatu2,hasilpapan,hasilpapan1,
            hasilpaku,hasilpaku1,hasilkawat,hasilkawat1,hasilbesi,hasilbesi1,hasilbegel,hasilbegel1,hasilpanjang,
            total,hasilbesi2;
    private  int id;
    ProgressDialog pd;
    private String hs,ids,hp,idp,berats,namasemen,namapasir,harpapan,namapapan,harpaku,harkawat,harbesi,
            namapaku,namakawat,namabesi,namabegel,harbegel,harbatu,namabatu;
    private String namajenis1,token,mId,jenis;
    private float pk,kwt,pc,pb,bt;
    private Button Hitung;
    private float panjangbet,pbsi,lbsi;
    private String harsemen1,harpasir1,harpapan1,harpaku1,harkawat1,harbegel1,harbesi1,harbatu1;
    private String namabatu1,namasemen1,namapasir1,namapapan1,namabegel1,namabesi1,namapaku1,namakawat1;
    //beton variabelhasil
    private float jumlahpaku,jumlahkwat,jumlahpapans,jmlsemen,jumlahbesis,jmlpasir,jumlahm3,begelbesi,jmlbatum3,jmlbatu,smndlmsak;
    private String totpaku,totkawat,totpapan,totbesi,totbegel,totsemen,totpasir,totbatu;
    private int total1,numberpaku,numberkawat,numberpapan,numbersemen,numberpasir,numberbesi,numberbatu,numberbegel;
    SessionManager sm;
    private List<Material> Besi = new ArrayList<>();
    private List<Material> Papan = new ArrayList<>();
    private List<Material> Semen = new ArrayList<>();
    private List<Material> Pasir = new ArrayList<>();
    private List<Material> Batu = new ArrayList<>();
    private List<Material> Paku = new ArrayList<>();
    private List<Material> Kawat = new ArrayList<>();
    private List<Jenispengerjaan> jensi = new ArrayList<>();
    Context mContext;
//    Jenispengerjaan[] jenispengerjaans  ={
//            new Jenispengerjaan("Sloof 10/15"),
//            new Jenispengerjaan("Sloof 15/20"),
//            new Jenispengerjaan("Kolom 10/10"),
//            new Jenispengerjaan("Kolom 15/20"),
//            new Jenispengerjaan("Ring balok 10/15")
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editbeton);
       init();
        pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();
        initSpinnerBatu();
        initSpinnerPapan();
        initSpinnerBesi();
        initSpinnerKawat();
        initSpinnerPaku();
        initSpinnerSemen();
        initSpinnerPasir();
        initSpinnerBegel();

        spinsemen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();

                if (selectedName.equalsIgnoreCase(namasemen1)){
                    namasemen = namasemen1;
                    Hsemen.setText(harsemen1);
                    berats = String.valueOf(Semen.get(position).getBerat());
                }else{
                    berats = String.valueOf(Semen.get(position).getBerat());
                    namasemen = String.valueOf(Semen.get(position).getNama());
                    hs = String.valueOf(Semen.get(position).getHarga());
                    Hsemen.setText(hs);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinpasir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();

                if (selectedName.equalsIgnoreCase(namapasir1)){
                    namapasir = namapasir1;
                    Hpasir.setText(harpasir1);
                }else{
                    hp = String.valueOf(Pasir.get(position).getHarga());
                    namapasir = String.valueOf(Pasir.get(position).getNama());
                    Hpasir.setText(hp);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinpapan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();

                if (selectedName.equalsIgnoreCase(namapapan1)){
                    namapapan = namapapan1;
                    Hpapan.setText(harpapan1);
                }else{
                    harpapan = String.valueOf(Papan.get(position).getHarga());
                    namapapan = String.valueOf(Papan.get(position).getNama());
                    Hpapan.setText(harpapan);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinpaku.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();

                if (selectedName.equalsIgnoreCase(namapaku1)){
                    namapaku = namapaku1;
                    Hpaku.setText(harpaku1);
                }else{
                    harpaku = String.valueOf(Paku.get(position).getHarga());
                    namapaku = String.valueOf(Paku.get(position).getNama());
                    Hpaku.setText(harpaku);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinkawat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();

                if (selectedName.equalsIgnoreCase(namakawat1)){
                    namakawat = namakawat1;
                    Hkawat.setText(harkawat1);
                }else{
                    harkawat = String.valueOf(Kawat.get(position).getHarga());
                    namakawat = String.valueOf(Kawat.get(position).getNama());
                    Hkawat.setText(harkawat);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinbesi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                if (selectedName.equalsIgnoreCase(namabesi1)){
                    namabesi = namabesi1;
                    Hbesi.setText(harbesi1);
                }else{
                    harbesi = String.valueOf(Besi.get(position).getHarga());
                    namabesi = String.valueOf(Besi.get(position).getNama());
                    Hbesi.setText(harbesi);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinjenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                jenis = String.valueOf(jensi.get(position).getJenispengerjaan());
                if (jensi.get(position).getJenispengerjaan().equalsIgnoreCase("Sloof 10/15")){
//                    pn = 0.045f;
                    pbsi=10f;
                    lbsi = 15f;
                    pk = 0.02f;
                    kwt = 0.05f;
                    pc = 5.5f;
                    pb = 0.009f;
                    bt=0.015f;
                }else if (jensi.get(position).getJenispengerjaan().equalsIgnoreCase("Sloof 15/20")){
//                    pn = 0.045f;
                    pk = 0.02f;
                    kwt = 0.05f;
                    pc = 326f;
                    pb = 760f;
                    bt=1029f;
                    pbsi=20f;
                    lbsi = 15f;

                }else if (jensi.get(position).getJenispengerjaan().equalsIgnoreCase("Kolom 10/10")){
//                    pn = 0.045f;
                    pk = 0.01f;
                    kwt = 0.45f;
                    pc = 326f;
                    pb = 0.006f;
                    bt=0.009f;
                    pbsi=10f;
                    lbsi = 10f;
                }else if (jensi.get(position).getJenispengerjaan().equalsIgnoreCase("Kolom 15/20")){
                    pk = 0.02f;
                    kwt = 0.05f;
                    pc = 326f;
                    pb = 760f;
                    bt=1029f;
                    pbsi=15f;
                    lbsi = 20f;
                }else if (jensi.get(position).getJenispengerjaan().equalsIgnoreCase("Ring balok 10/15")) {
                    pk = 0.02f;
                    kwt = 0.05f;
                    pc = 5.5f;
                    pb = 0.009f;
                    bt=0.015f;
                    pbsi=15f;
                    lbsi = 10f;
                }
//                Toast.makeText(mContext, "Kamu memilih Pasir " + je, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinbegel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();

                if (selectedName.equalsIgnoreCase(namabegel1)){
                    namabegel = namabegel1;
                    Hbegel.setText(harbegel1);
                }else{
                    harbegel = String.valueOf(Besi.get(position).getHarga());
                    namabegel = String.valueOf(Besi.get(position).getNama());
                    Hbegel.setText(harbegel);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinbatu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();

                if (selectedName.equalsIgnoreCase(namabatu1)){
                    namabatu = namabatu1;
                    Hbatu.setText(harbatu1);
                }else{
                    harbatu = String.valueOf(Batu.get(position).getHarga());
                    namabatu = String.valueOf(Batu.get(position).getNama());
                    Hbatu.setText(harbatu);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pj = panjangbeton.getText().toString().trim();
                panjangbet= Float.parseFloat(pj);
                hasilpanjang.setText(String.valueOf(panjangbet));
                hitungbesi();
                hitungpaku();
                hitungkawat();
                hitungbegel();
                hitungpapan();
                hitungsemen();
                hitungpasir();
                hitungbatu();
                hitungtotal();
            }
        });

    }
    private void hitungtotal() {
        total1 = numberbatu+numberbegel+numberpasir+numbersemen+numberkawat+numberpaku+numberbesi+numberpapan;
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(total1);
        total.setText(totalbiaya);
    }

    private void hitungpaku() {

        float hargapku = Float.parseFloat(Hpaku.getText().toString());
        float jumpaku = panjangbet*pk;
        DecimalFormat df1 = new DecimalFormat("#.##");
        df1.setRoundingMode(RoundingMode.CEILING);
        String n = df1.format(jumpaku);
        String result = null;
        result = n.replace(",",".");
        jumlahpaku = Float.parseFloat(result);

        float hargatotpaku = jumlahpaku*hargapku;
        DecimalFormat df = new DecimalFormat("#");
        totpaku = df.format(hargatotpaku);
        numberpaku = Integer.parseInt(totpaku);
        hasilpaku.setText(String.valueOf(jumlahpaku));
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numberpaku);
        hasilpaku1.setText(totalbiaya);
    }

    private void hitungkawat() {
        float hargakwat = Float.parseFloat(Hkawat.getText().toString());
        float jumkwat = panjangbet*kwt;
        DecimalFormat df1 = new DecimalFormat("#.##");
        df1.setRoundingMode(RoundingMode.CEILING);
        String n = df1.format(jumkwat);
        String result = null;
        result = n.replace(",",".");
        jumlahkwat = Float.parseFloat(result);
        float hargakawaat = jumlahkwat*hargakwat;
        DecimalFormat df = new DecimalFormat("#");
        totkawat = df.format(hargakawaat);
        numberkawat = Integer.parseInt(totkawat);
        hasilkawat.setText(String.valueOf(jumlahkwat));
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numberkawat);
        hasilkawat1.setText(totalbiaya);

    }

    private void hitungpapan() {
        float hargappan = Float.parseFloat(Hpapan.getText().toString());
        jumlahpapans = (panjangbet*2)/4;
        float hargapapan = jumlahpapans*hargappan;
        DecimalFormat df = new DecimalFormat("#");
        totpapan = df.format(hargapapan);
        numberpapan = Integer.parseInt(totpapan);
        hasilpapan.setText(String.valueOf(jumlahpapans));
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numberpapan);
        hasilpapan1.setText(String.valueOf(totalbiaya));
    }

    private void hitungbesi() {
        float hargabsi = Float.parseFloat(Hbesi.getText().toString());
        float jumlahbsi = (panjangbet*4)/12;
        DecimalFormat df1 = new DecimalFormat("#.##");
        df1.setRoundingMode(RoundingMode.CEILING);
        String n = df1.format(jumlahbsi);
        String result = null;
        result = n.replace(",",".");
        jumlahbesis = Float.parseFloat(result);
        DecimalFormat df2 = new DecimalFormat("#");
        df2.setRoundingMode(RoundingMode.CEILING);
        String pembulatanbesi = df2.format(jumlahbesis);

        float hargabesi1 = hargabsi*jumlahbesis;
        DecimalFormat df = new DecimalFormat("#");
        totbesi = df.format(hargabesi1);
        numberbesi = Integer.parseInt(totbesi);
        hasilbesi.setText(String.valueOf(jumlahbesis));
        hasilbesi1.setText(pembulatanbesi);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numberbesi);
        hasilbesi2.setText(totalbiaya);

    }

    private void hitungsemen() {
        float hargasemen = Float.parseFloat(Hsemen.getText().toString());
        float berats1 = Float.parseFloat(berats);
        if(pc >=100){
            float b=pbsi/100;
            float h = lbsi/100;
            float volume = b*h*panjangbet;
            jmlsemen = volume*pc;
            smndlmsak = jmlsemen/berats1;
            float hargatotsmen = smndlmsak*hargasemen;
            DecimalFormat df = new DecimalFormat("#");
            totsemen= df.format(hargatotsmen);
            numbersemen = Integer.parseInt(totsemen);
            hasils.setText(String.valueOf(jmlsemen));
            hasils1.setText(String.valueOf(smndlmsak));
            DecimalFormat formatter = new DecimalFormat("#,###.##");
            String totalbiaya = formatter.format(numbersemen);
            hasils2.setText(totalbiaya);

        }else{
            jmlsemen = panjangbet*pc;
            smndlmsak = jmlsemen/berats1;
            float hargatotsmen = smndlmsak*hargasemen;
            DecimalFormat df = new DecimalFormat("#");
            totsemen = df.format(hargatotsmen);
            numbersemen = Integer.parseInt(totsemen);
            hasils.setText(String.valueOf(jmlsemen));
            hasils1.setText(String.valueOf(smndlmsak));
            DecimalFormat formatter = new DecimalFormat("#,###.##");
            String totalbiaya = formatter.format(numbersemen);
            hasils2.setText(String.valueOf(totalbiaya));

        }
    }
    private void hitungpasir() {
        float hargapasir= Float.parseFloat(Hpasir.getText().toString());
        if(pb >=100){
            float b=pbsi/100;
            float h = lbsi/100;
            float volume = b*h*panjangbet;
            jmlpasir = volume*pb;
            float jumlahm = jmlpasir/1000;

            DecimalFormat df1 = new DecimalFormat("#.##");
            df1.setRoundingMode(RoundingMode.CEILING);
            String n = df1.format(jumlahm);
            String result = null;
            result = n.replace(",",".");
            jumlahm3 = Float.parseFloat(result);

            float hargatotpasir= jumlahm3*hargapasir;
            DecimalFormat df = new DecimalFormat("#");
            totpasir = df.format(hargatotpasir);
            numberpasir = Integer.parseInt(totpasir);
            hasilp.setText(String.valueOf(jmlpasir));
            hasilp1.setText(String.valueOf(jumlahm3));
            DecimalFormat formatter = new DecimalFormat("#,###.##");
            String totalbiaya = formatter.format(numberpasir);
            hasilp2.setText(totalbiaya);

        }else{
            float jumlpasir = panjangbet*pb;
            DecimalFormat df1 = new DecimalFormat("#.##");
            df1.setRoundingMode(RoundingMode.CEILING);
            String n = df1.format(jumlpasir);
            String result = null;
            result = n.replace(",",".");
            jmlpasir = Float.parseFloat(result);
            float jmlpasirkg = jmlpasir*1000;
            float hargatotpasir = jmlpasir*hargapasir;
            DecimalFormat df = new DecimalFormat("#");
            totpasir = df.format(hargatotpasir);
            numberpasir = Integer.parseInt(totpasir);
            hasilp.setText(String.valueOf(jmlpasirkg));
            hasilp1.setText(String.valueOf(jmlpasir));
            DecimalFormat formatter = new DecimalFormat("#,###.##");
            String totalbiaya = formatter.format(numberpasir);
            hasilp2.setText(totalbiaya);

        }
    }
    private void hitungbegel() {
        float hargabgel = Float.parseFloat(Hbegel.getText().toString());
        float kebbegel = (2*pbsi)+(2*lbsi)-(2*2)+(2*2)+5;
        float hasildalamm = kebbegel/100;
        float begels = panjangbet/0.2f;
        float totalbegelx = begels*hasildalamm;
        float begelbesi1 = totalbegelx/12;
        DecimalFormat df1 = new DecimalFormat("#.##");
        df1.setRoundingMode(RoundingMode.CEILING);
        String n = df1.format(begelbesi1);
        String result = null;
        result = n.replace(",",".");
        begelbesi = Float.parseFloat(result);
        float hargabgeltot = begelbesi*hargabgel;
        DecimalFormat df = new DecimalFormat("#");
        totbegel = df.format(hargabgeltot);
        numberbegel = Integer.parseInt(totbegel);
        hasilbegel.setText(String.valueOf(begelbesi));
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numberbegel);
        hasilbegel1.setText(totalbiaya);
    }
    private void hitungbatu() {
        float hargabatu= Float.parseFloat(Hbatu.getText().toString());
        if(bt >=100){
            float b=pbsi/100;
            float h = lbsi/100;
            float volume = b*h*panjangbet;
            jmlbatu = volume*bt;
            float jumlbatus = jmlbatu/1000;
            DecimalFormat df1 = new DecimalFormat("#.##");
            df1.setRoundingMode(RoundingMode.CEILING);
            String n = df1.format(jumlbatus);
            String result = null;
            result = n.replace(",",".");
            jmlbatum3 = Float.parseFloat(result);

//            float jmlbatutruk = jmlbatum3/7;
            float hargatotbatu= jmlbatum3*hargabatu;
            DecimalFormat df = new DecimalFormat("#");
            totbatu = df.format(hargatotbatu);
            numberbatu = Integer.parseInt(totbatu);
            hasilbatu.setText(String.valueOf(jmlbatu));
            hasilbatu12.setText(String.valueOf(jmlbatum3));
            DecimalFormat formatter = new DecimalFormat("#,###.##");
            String totalbiaya = formatter.format(numberbatu);
            hasilbatu2.setText(totalbiaya);


        }else{
            float jmlbatu1 = panjangbet*bt;
            DecimalFormat df1 = new DecimalFormat("#.##");
            df1.setRoundingMode(RoundingMode.CEILING);
            String n = df1.format(jmlbatu1);
            String result = null;
            result = n.replace(",",".");
            jmlbatu = Float.parseFloat(result);
            jmlbatum3 = jmlbatu*1000;
            float hargatotbatu = jmlbatu*hargabatu;
            DecimalFormat df = new DecimalFormat("#");
            totbatu = df.format(hargatotbatu);
            numberbatu = Integer.parseInt(totbatu);
            hasilbatu.setText(String.valueOf(jmlbatum3));
            hasilbatu12.setText(String.valueOf(jmlbatu));
            DecimalFormat formatter = new DecimalFormat("#,###.##");
            String totalbiaya = formatter.format(numberbatu);
            hasilbatu2.setText(totalbiaya);


        }
    }


    ///---------------------------spinner init
    private void initSpinnerSemen() {
        ApiClient.getRequestInterface().getallsemen().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    pd.hide();
                    Semen = response.body().getMaterials();
                    int i=0;
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namasemen1;
                    for (int j = 0; j < Semen.size(); j++){
                        if (data.equalsIgnoreCase(String.valueOf(Semen.get(j).getNama()))) {
                            i = j;
                        }
                        listSpinner.add(Semen.get(j).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinsemen.setAdapter(adapter);
                    spinsemen.setSelection(i);

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
                    int i=0;
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
                    spinpasir.setAdapter(adapter);
                    spinpasir.setSelection(i);

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

    private void initSpinnerPaku() {
        ApiClient.getRequestInterface().getallpaku().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    pd.hide();
                    Paku= response.body().getMaterials();
                    int i=0;
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namapaku1;
                    for (int j = 0; j < Paku.size(); j++){
                        if (data.equalsIgnoreCase(String.valueOf(Paku.get(j).getNama()))) {
                            i = j;
                        }
                        listSpinner.add(Paku.get(j).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinpaku.setAdapter(adapter);
                    spinpaku.setSelection(i);

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

    private void initSpinnerKawat() {
        ApiClient.getRequestInterface().getallpengikat().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    pd.hide();
                    Kawat = response.body().getMaterials();
                    int i=0;
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namakawat1;
                    for (int j = 0; j < Kawat.size(); j++){
                        if (data.equalsIgnoreCase(String.valueOf(Kawat.get(j).getNama()))) {
                            i = j;
                        }
                        listSpinner.add(Kawat.get(j).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinkawat.setAdapter(adapter);
                    spinkawat.setSelection(i);

                } else {
                    Toast.makeText(mContext, "Gagal mengambil data Kawat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMaterial> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSpinnerBesi() {
        ApiClient.getRequestInterface().getallbesi().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    pd.hide();
                    Besi = response.body().getMaterials();
                    int i=0;
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namabesi1;
                    for (int j = 0; j < Besi.size(); j++){
                        if (data.equalsIgnoreCase(String.valueOf(Besi.get(j).getNama()))) {
                            i = j;
                        }
                        listSpinner.add(Besi.get(j).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinbesi.setAdapter(adapter);
                    spinbesi.setSelection(i);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data Besi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMaterial> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void initSpinnerBegel() {
        ApiClient.getRequestInterface().getallbesi().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    pd.hide();
                    Besi = response.body().getMaterials();
                    int i=0;
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namabegel1;
                    for (int j = 0; j < Besi.size(); j++){
                        if (data.equalsIgnoreCase(String.valueOf(Besi.get(j).getNama()))) {
                            i = j;
                        }
                        listSpinner.add(Besi.get(j).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinbegel.setAdapter(adapter);
                    spinbegel.setSelection(i);

                } else {
                    Toast.makeText(mContext, "Gagal mengambil data Besi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMaterial> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initSpinnerPapan() {
        ApiClient.getRequestInterface().getalllispapan().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    pd.hide();
                    Papan = response.body().getMaterials();
                    int i=0;
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namapapan1;
                    for (int j = 0; j < Papan.size(); j++){
                        if (data.equalsIgnoreCase(String.valueOf(Papan.get(j).getNama()))) {
                            i = j;
                        }
                        listSpinner.add(Papan.get(j).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinpapan.setAdapter(adapter);
                    spinpapan.setSelection(i);

                } else {
                    Toast.makeText(mContext, "Gagal mengambil data Papan", Toast.LENGTH_SHORT).show();
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
                    int i=0;
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namabatu1;
                    for (int j = 0; j < Batu.size(); j++){
                        if (data.equalsIgnoreCase(String.valueOf(Batu.get(j).getNama()))) {
                            i = j;
                        }
                        listSpinner.add(Batu.get(j).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinbatu.setAdapter(adapter);
                    spinbatu.setSelection(i);

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

                String apiKey = "oa00000000app";
                initvalidation();
                pd.show();
                HashMap<String, String> map = new HashMap<>();
                map.put("ProyekId", mId);
                map.put("nama", Nama.getText().toString());
                map.put("pilihanbeton", jenis);
                map.put("panjangbeton", String.valueOf(panjangbet));
                map.put("namapapan", namapapan);
                map.put("namapaku", namapaku);
                map.put("namabesi", namabesi);
                map.put("namabegel", namabegel);
                map.put("namakawat", namakawat);
                map.put("namapasir", namapasir);
                map.put("namasemen", namasemen);
                map.put("namabatu", namabatu);
                map.put("hargapapan", Hpapan.getText().toString());
                map.put("hargabesi",   Hbesi.getText().toString());
                map.put("hargabegel", Hbegel.getText().toString());
                map.put("hargakawat", Hkawat.getText().toString());
                map.put("hargapasir", Hpasir.getText().toString());
                map.put("hargasemen", Hsemen.getText().toString());
                map.put("hargabatu", Hpapan.getText().toString());
                map.put("hargapaku", Hpaku.getText().toString());
                map.put("jumlahbesi", hasilbesi.getText().toString());
                map.put("jumlahbesibatang", hasilbesi1.getText().toString());
                map.put("jumlahbegel", hasilbegel.getText().toString());
                map.put("jumlahkawat", hasilkawat.getText().toString());
                map.put("jumlahpasir", hasilp.getText().toString());
                map.put("jumlahsemen", hasils.getText().toString());
                map.put("jumlahpasirtruk", hasilp1.getText().toString());
                map.put("jumlahsemendalamsak", hasils1.getText().toString());
                map.put("jumlahbatu", hasilbatu.getText().toString());
                map.put("jumlahpapan", hasilpapan.getText().toString());
                map.put("jumlahbatudalamtruk", hasilbatu12.getText().toString());
                map.put("jumlahpaku", hasilpaku.getText().toString());
                map.put("hargatotalpapan", totpapan);
                map.put("hargatotalbesi", totbesi);
                map.put("hargatotalbegel", totbegel);
                map.put("hargatotalkawat",totkawat);
                map.put("hargatotalpasir", totpasir);
                map.put("hargatotalsemen", totsemen);
                map.put("hargatotalbatu", totbatu);
                map.put("hargatotalpaku", totpaku);
                map.put("hargatotal", String.valueOf(total1));

                Call<Void> call = ApiClient.getRequestInterface().actionPutPerhitunganbeton(id,apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {
                            pd.hide();
                            Intent Perhitunganbeton = (new Intent(Editbeton.this, Perhitunganbeton.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            Bundle setData = new Bundle();
                            setData.putString("idproyek",mId);
                            Perhitunganbeton.putExtras(setData);
                            startActivity(Perhitunganbeton);
                            Toast.makeText(Editbeton.this,
                                    "Tambah Data Perhitungan Beton Berhasil ditambahkan",
                                    Toast.LENGTH_LONG).show();

                        } else if (response.code() == 422) {
                            Toast.makeText(Editbeton.this,
                                    "Something Wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Editbeton.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }
    private void init(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;
        sm= new SessionManager(Editbeton.this);
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        sm.checkLogin();

        Nama = findViewById(R.id.nama_pengerjaan);
        panjangbeton = findViewById(R.id.panjangb);
        spinpapan = findViewById(R.id.spinpapan);
        spinbegel = findViewById(R.id.spinbegel);
        spinpaku = findViewById(R.id.spinpaku);
        spinkawat = findViewById(R.id.spinkawat);
        spinpasir = findViewById(R.id.spinpasir);
        spinsemen = findViewById(R.id.spinsemen);
        spinbatu = findViewById(R.id.spinbatu);
        spinbesi = findViewById(R.id.spinbesi);
        spinjenis = findViewById(R.id.spinjenis);
        Hpapan =findViewById(R.id.hpapan);
        Hpaku= findViewById(R.id.hpaku);
        Hbesi=findViewById(R.id.hbesi);
        Hkawat=findViewById(R.id.hkawat);
        Hbegel = findViewById(R.id.hbegel);
        Hpasir = findViewById(R.id.hpasir);
        Hsemen = findViewById(R.id.hsemen);
        Hbatu = findViewById(R.id.hbatu);
        Hitung = findViewById(R.id.hitungb);

        hasils = findViewById(R.id.hasils);
        hasils1 = findViewById(R.id.hasils1);
        hasils2 = findViewById(R.id.hasils2);
        hasilp = findViewById(R.id.hasilp);
        hasilp1 = findViewById(R.id.hasilp1);
        hasilp2 = findViewById(R.id.hasilp2);
        hasilpapan = findViewById(R.id.hasilpapan);
        hasilpapan1 = findViewById(R.id.hasilpapan1);
        hasilpaku = findViewById(R.id.hasilpaku);
        hasilpaku1 = findViewById(R.id.hasilpaku1);
        hasilbatu = findViewById(R.id.hasilbatu);
        hasilbatu12 = findViewById(R.id.hasil11);
        hasilbatu2 = findViewById(R.id.hasilbatu21);
        hasilkawat = findViewById(R.id.hasilkawat);
        hasilkawat1 = findViewById(R.id.hasilkawat1);
        hasilbegel = findViewById(R.id.hasilbegel);
        hasilbegel1 = findViewById(R.id.hasilbegel1);
        hasilbesi = findViewById(R.id.hasilbesi);
        hasilbesi1 = findViewById(R.id.hasilbesi1);
        hasilbesi2 = findViewById(R.id.hasilbesi2);
        hasilpanjang = findViewById(R.id.hasilpanjang);
        total = findViewById(R.id.total);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            mId = bundle.getString("ProyekId");
        }else{
            mId = "0";
        }
        panjangbeton.setText(bundle.getString("panjangbeton"));
        id = Integer.parseInt(bundle.getString("id"));
        Nama.setText(bundle.getString("nama"));
        namajenis1 = bundle.getString("pilihanbeton");
        hasilpanjang.setText(bundle.getString("panjangbeton"));
        namapapan1= bundle.getString("namapapan");
       namapaku1= bundle.getString("namapaku");
        namabesi1=bundle.getString("namabesi");
        namabegel1=bundle.getString("namabegel");
        namakawat1=bundle.getString("namakawat");
        namapasir1=bundle.getString("namapasir");
        namasemen1 =bundle.getString("namasemen");
        namabatu1 =bundle.getString("namabatu");
        harpapan1=bundle.getString("hargapapan");
        harpaku1 =bundle.getString("hargapaku");
        harbesi1 =bundle.getString("hargabesi");
        harbegel1 =bundle.getString("hargabegel");
        harkawat1 =bundle.getString("hargakawat");
        harpasir1 =bundle.getString("hargapasir");
        harsemen1 = bundle.getString("hargasemen");
        harbatu1=bundle.getString("hargabatu");
        hasilpapan.setText(bundle.getString("jumlahpapan"));
        hasilpaku.setText(bundle.getString("jumlahpaku"));
        hasilbesi.setText( bundle.getString("jumlahbesi"));
        hasilbesi1.setText(bundle.getString("jumlahbesibatang"));
        hasils1.setText(bundle.getString("jumlahsemensak"));
        hasilp1.setText(bundle.getString("jumlahpasirtruk"));
        hasilbatu12.setText(bundle.getString("jumlahbatutruk"));
        hasilbegel.setText(bundle.getString("jumlahbegel"));
        hasilkawat.setText(bundle.getString("jumlahkawat"));
        hasilp.setText(bundle.getString("jumlahpasir"));
        hasils.setText(bundle.getString("jumlahsemen"));
        hasilbatu.setText(bundle.getString("jumlahbatu"));
        DecimalFormat df = new DecimalFormat("#");
        DecimalFormat formatter = new DecimalFormat("#,###.##");



        float hargatotpapan = Float.parseFloat(bundle.getString("hargatotalpapan"));
        totpapan = df.format(hargatotpapan);
        numberpapan = Integer.parseInt(totpapan);
        String totalbiayapapan = formatter.format(numberpapan);
        hasilpapan1.setText(totalbiayapapan);

        float hargatotpaku = Float.parseFloat(bundle.getString("hargatotalpaku"));
        totpaku = df.format(hargatotpaku);
        numberpaku = Integer.parseInt(totpaku);
        String totalbiayapaku = formatter.format(numberpaku);
        hasilpaku1.setText(totalbiayapaku);

        float hargatotbesi = Float.parseFloat(bundle.getString("hargatotalbesi"));
        totbesi = df.format(hargatotbesi);
        numberbesi = Integer.parseInt(totbesi);
        String totalbiayabesi = formatter.format(numberbesi);
        hasilbesi2.setText(totalbiayabesi);

        float hargatotbegel = Float.parseFloat(bundle.getString("hargatotalbegel"));
        totbegel = df.format(hargatotbegel);
        numberbegel = Integer.parseInt(totbegel);
        String totalbiayabegel = formatter.format(numberbegel);
        hasilbegel1.setText(totalbiayabegel);

        float hargatotpasir = Float.parseFloat(bundle.getString("hargatotalpasir"));
        totpasir = df.format(hargatotpasir);
        numberpasir = Integer.parseInt(totpasir);
        String totalbiayapasir = formatter.format(numberpasir);
        hasilp2.setText(totalbiayapasir);

        float hargatotsemen = Float.parseFloat(bundle.getString("hargatotalsemen"));
        totsemen = df.format(hargatotsemen);
        numbersemen = Integer.parseInt(totsemen);
        String totalbiayasemen = formatter.format(numbersemen);
        hasils2.setText(totalbiayasemen);

        float hargatotbatu = Float.parseFloat(bundle.getString("hargatotalbatu"));
        totbatu = df.format(hargatotbatu);
        numberbatu = Integer.parseInt(totbatu);
        String totalbiayabatu = formatter.format(numberbatu);
        hasilbatu2.setText(totalbiayabatu);

        float hargatotkawat = Float.parseFloat(bundle.getString("hargatotalkawat"));
        totkawat = df.format(hargatotkawat);
        numberkawat = Integer.parseInt(totkawat);
        String totalbiayakawat = formatter.format(numberkawat);
        hasilkawat1.setText(totalbiayakawat);

        float hargatotal = Float.parseFloat(bundle.getString("hargatotal"));
        String tot= df.format(hargatotal);
        total1 = Integer.parseInt(tot);
        String totalbiaya = formatter.format(total1);
        total.setText(totalbiaya);

        jensi.add(new Jenispengerjaan("Sloof 10/15"));
        jensi.add(new Jenispengerjaan("Sloof 15/20"));
        jensi.add(new Jenispengerjaan("Kolom 10/10"));
        jensi.add(new Jenispengerjaan("Kolom 15/20"));
        jensi.add(new Jenispengerjaan("Ring balok 10/15"));

        int i = 0;
        List<String> listSpinner = new ArrayList<String>();
        String data = namajenis1;
        for (int j = 0; j <jensi.size(); j++) {
            if (data.equalsIgnoreCase(String.valueOf(jensi.get(j).getJenispengerjaan()))) {
                i = j;
            }
            listSpinner.add(String.valueOf(jensi.get(j).getJenispengerjaan()));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_spinner_item, listSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinjenis.setAdapter(adapter);
        spinjenis.setSelection(i);
    }

    private void initvalidation() {

    }
}
