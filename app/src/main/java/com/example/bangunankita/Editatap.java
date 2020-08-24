package com.example.bangunankita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangunankita.Model.Material;
import com.example.bangunankita.Model.Perhitunganatap1;
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

public class Editatap extends AppCompatActivity {
    private EditText nama, luasatap, panjangnokh, hargapasir, hargagenteng, hargasemen, hargabubungan;
    private Spinner spinsemen, spinpasir, spingenteng, spinbubungan;
    private Button hitung;
    private TextView hasilluas;
    private TextView hasilnok;
    private TextView hasilsemen;
    private TextView hasilpasir;
    private TextView hasilgenteng;
    private TextView hasilbubungan;
    ProgressDialog pd;
    private TextView hasilgenteng1;
    private TextView hasilbubungan1;
    private TextView total;
    private TextView hasilsemen1;
    private TextView hasilpasir1;
    private TextView hasilsemen2;
    private TextView hasilpasir2;
    private String totbubungan1,totpasir1,totsemen1,totgenteng1,tothitung1;
    private String harpasir1,harsemen1,hargenteng1,harbubungan1,namasemen1,namagenteng1,namabubungan1,namapasir1;
    private  int id,numberbubungan,numberpasir,numbersemen,numbergenteng,numberhitung;
    Context mContext;
    SessionManager sm;
    private String hp,hs,hb,luasatap1,panjangnok,namasemen,namapasir,hg,namagenteng,jum,berats;
    String token,jumlahbubungan,namabubungan;
    String mId, Ju;
    int ProyekID;
    private List<Perhitunganatap1> AtapModel = new ArrayList<>();
    private List<Material> Semen = new ArrayList<>();
    private List<Material> Pasir = new ArrayList<>();
    private List<Material> Bubungan = new ArrayList<>();
    private List<Material> Genteng = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editatap);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;
        pd = new ProgressDialog(this);
        nama = findViewById(R.id.nama_pengerjaan1);
        luasatap = findViewById(R.id.luasatap);
        panjangnokh = findViewById(R.id.panjangnok);
        hargapasir = findViewById(R.id.hargapasir);
        hargagenteng = findViewById(R.id.hargagenteng);
        hargabubungan = findViewById(R.id.hargabubungan);
        hargasemen = findViewById(R.id.hargasemen);
        spinbubungan = findViewById(R.id.spinbubungan);
        spingenteng = findViewById(R.id.spingenteng);
        spinpasir = findViewById(R.id.spinpasir);
        spinsemen = findViewById(R.id.spinsemen);
        hitung = findViewById(R.id.hitungb);
        hasilluas = findViewById(R.id.hasilbid);
        hasilnok = findViewById(R.id.hasilnok);
        hasilsemen = findViewById(R.id.hasils);
        hasilpasir = findViewById(R.id.hasilp2);
        hasilsemen1 = findViewById(R.id.hasils1);
        hasilpasir1 = findViewById(R.id.hasilp);
        hasilsemen2 = findViewById(R.id.hasils2);
        hasilpasir2 = findViewById(R.id.hasilp1);
        hasilgenteng = findViewById(R.id.hasilgenteng);
        hasilbubungan = findViewById(R.id.hasilbubungan);
        hasilgenteng1 = findViewById(R.id.hasilgenteng1);
        hasilbubungan1 = findViewById(R.id.hasilbubungan1);
        total = findViewById(R.id.total);
        sm = new SessionManager(Editatap.this);
        HashMap<String, String> map = sm.getDetailLogin();
        token = (map.get(sm.KEY_TOKEN));
        sm.checkLogin();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mId = bundle.getString("ProyekId");
            Ju = mId;
        } else {
            mId = "0";
        }
        pd.setMessage("loading");
        pd.show();
        id = Integer.parseInt(bundle.getString("id"));
        nama.setText(bundle.getString("Nama"));
        hasilluas.setText(bundle.getString("Luasatap"));
        luasatap.setText(bundle.getString("Luasatap"));
        panjangnokh.setText(bundle.getString("Panjangnok"));
        hasilnok.setText(bundle.getString("Panjangnok"));
        namasemen1= bundle.getString("Namasemen");
        namagenteng1=bundle.getString("Namagenteng");
        namabubungan1 =bundle.getString("Namabubungan");
        namapasir1 =bundle.getString("Namapasir");
        hasilsemen.setText(bundle.getString("Jumlahsemen"));
        hasilpasir.setText(bundle.getString("Jumlahpasir"));
        hasilgenteng.setText(bundle.getString("Jumlahgenteng"));
        hasilbubungan.setText(bundle.getString("Jumlahbubungan"));
        hasilsemen1.setText(bundle.getString("Jumlahdalamsak"));
        harpasir1=(bundle.getString("Hargapasir"));
        harsemen1=(bundle.getString("Hargasemen"));
        hargenteng1=(bundle.getString("Hargagenteng"));
        harbubungan1=(bundle.getString("Hargabubungan"));

        float hargapasir1 = Float.parseFloat(bundle.getString("Hargapasirtotal"));
        DecimalFormat df1 = new DecimalFormat("#");
        totpasir1 = df1.format(hargapasir1);
        numberpasir = Integer.parseInt(totpasir1);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiayapasir = formatter.format(numberpasir);
        hasilpasir2.setText(totalbiayapasir);

        float hargasemen1 = Float.parseFloat(bundle.getString("Hargasementotal"));
        DecimalFormat df = new DecimalFormat("#");
        totsemen1 = df.format(hargasemen1);
        numbersemen = Integer.parseInt(totsemen1);
        DecimalFormat formatter1 = new DecimalFormat("#,###.##");
        String totalbiayasemen = formatter1.format(numberpasir);
        hasilsemen2.setText(totalbiayasemen);

        float totalhargagenteng  = Float.parseFloat(bundle.getString("Hargagentengtotal"));

        DecimalFormat df2 = new DecimalFormat("#");
        totgenteng1 = df2.format(totalhargagenteng);
        numbergenteng = Integer.parseInt(totgenteng1);
        DecimalFormat formatter2 = new DecimalFormat("#,###.##");
        String totalbiayagenteng = formatter2.format(numbergenteng);
        hasilgenteng1.setText(totalbiayagenteng);

        float totalhargabubungan = Float.parseFloat(bundle.getString("Hargabubungantotal"));
        DecimalFormat df3 = new DecimalFormat("#");
        totbubungan1 = df3.format(totalhargabubungan);
        numberbubungan = Integer.parseInt(totbubungan1);
        DecimalFormat formatter3 = new DecimalFormat("#,###.##");
        String totalbiayabubungan = formatter3.format(numberbubungan);
        hasilbubungan1.setText(totalbiayabubungan);

        float hitungs = Float.parseFloat(bundle.getString("Totalbiaya"));
        DecimalFormat df4 = new DecimalFormat("#");
        tothitung1 = df4.format(hitungs);
        numberhitung = Integer.parseInt(tothitung1);
        DecimalFormat formatter4 = new DecimalFormat("#,###.##");
        String totalbiaya = formatter4.format(numberhitung);
        total.setText(totalbiaya);


        Toast.makeText(Editatap.this, "Proyek Id" + mId,
                Toast.LENGTH_SHORT).show();
        initSpinnerSemen();
        initSpinnerPasir();
        initSpinnerGenteng();
        initSpinnerBubungan();

        spinsemen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedName = parent.getItemAtPosition(position).toString();

                if (selectedName.equalsIgnoreCase(namasemen1)){
                    namasemen = namasemen1;
                    berats = String.valueOf(Semen.get(position).getBerat());
                    hargasemen.setText(harsemen1);
                }else{
                    hs = String.valueOf(Semen.get(position).getHarga());
                    berats = String.valueOf(Semen.get(position).getBerat());
                    namasemen = String.valueOf(Semen.get(position).getNama());
                    hargasemen.setText(hs);
                }

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

                if (selectedName.equalsIgnoreCase(namapasir1)){
                    namapasir = namapasir1;
                    hargapasir.setText(harpasir1);
                }else{
                    hp = String.valueOf(Pasir.get(position).getHarga());
                    namapasir = String.valueOf(Pasir.get(position).getNama());
                    hargapasir.setText(hp);
                }

                Toast.makeText(mContext, "Kamu memilih Pasir " + selectedName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spingenteng.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();

                if (selectedName.equalsIgnoreCase(namagenteng1)){
                    namagenteng = namagenteng1;
                    hargagenteng.setText(hargenteng1);
                    jum = String.valueOf(Genteng.get(position).getJumlah());
                }else{
                    hg = String.valueOf(Genteng.get(position).getHarga());
                    namagenteng = String.valueOf(Genteng.get(position).getNama());
                    hargagenteng.setText(hg);
                    jum = String.valueOf(Genteng.get(position).getJumlah());
                }
                Toast.makeText(mContext, "Kamu memilih Pasir " + selectedName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinbubungan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();


                if (selectedName.equalsIgnoreCase(namabubungan1)){
                    namabubungan = namabubungan1;
                    hargabubungan.setText(harbubungan1);
                    jum = String.valueOf(Bubungan.get(position).getJumlah());
                }else{
                    hb = String.valueOf(Bubungan.get(position).getHarga());
                    namabubungan = String.valueOf(Bubungan.get(position).getNama());
                    hargabubungan.setText(hb);
                    jum = String.valueOf(Bubungan.get(position).getJumlah());
                }
                Toast.makeText(mContext, "Kamu memilih Pasir " + selectedName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panjangnok = panjangnokh.getText().toString();
                luasatap1 = luasatap.getText().toString();
                hasilluas.setText(luasatap1);
                hasilnok.setText(panjangnok);
                initgenteng();
                initsemen();
                initpasir();
                initbubungan();
                inittotal();
            }
        });
    }

    private void inittotal() {
        float hitung = numberbubungan+numbergenteng+numberpasir+numbersemen;
        DecimalFormat df1 = new DecimalFormat("#");
        tothitung1 = df1.format(hitung);
        numberhitung = Integer.parseInt(tothitung1);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numberhitung);
        total.setText(String.valueOf(totalbiaya));
    }

    private void initbubungan() {
        float nok = Float.parseFloat(panjangnok);
        float hbubungan= Float.parseFloat(hargabubungan.getText().toString());
        float atap = Float.parseFloat(luasatap1);
        float jumlahbubungan1 =nok*5f;
        float totalhargabubungan = hbubungan*jumlahbubungan1;

        DecimalFormat df1 = new DecimalFormat("#");
        totbubungan1 = df1.format(totalhargabubungan);
        numberbubungan = Integer.parseInt(totbubungan1);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numberbubungan);
        hasilbubungan.setText(String.valueOf(jumlahbubungan1));
        hasilbubungan1.setText(String.valueOf(totalbiaya));

    }

    private void initpasir() {
        float nok = Float.parseFloat(panjangnok);
        float hpasir = Float.parseFloat(hargapasir.getText().toString());
        float atap = Float.parseFloat(luasatap1);
        float jumlahpasir =nok*0.032f;
        float hargapasir1 = jumlahpasir*hpasir;
        DecimalFormat df1 = new DecimalFormat("#");
        totpasir1 = df1.format(hargapasir1);
        numberpasir = Integer.parseInt(totpasir1);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numberpasir);
        hasilpasir.setText(String.valueOf(jumlahpasir));
        hasilpasir2.setText(String.valueOf(totalbiaya));
    }

    private void initsemen() {
        float nok = Float.parseFloat(panjangnok);
        float berats1 = Float.parseFloat(berats);
        float hsemen = Float.parseFloat(hargasemen.getText().toString());
        float atap = Float.parseFloat(luasatap1);
        float jumlahsemen =nok*8f;
        float jumlahakhir = jumlahsemen/berats1;
        float hargasemen = jumlahakhir*hsemen;
        DecimalFormat df1 = new DecimalFormat("#");
        totsemen1 = df1.format(hargasemen);
        numbersemen = Integer.parseInt(totsemen1);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numbersemen);

        hasilsemen.setText(String.valueOf(jumlahsemen));
        hasilsemen1.setText(String.valueOf(jumlahakhir));
        hasilsemen2.setText(String.valueOf(totalbiaya));
    }

    private void initgenteng() {
        float luasgenteng = Float.parseFloat(luasatap1);
        float hgenteng= Float.parseFloat(hargagenteng.getText().toString());
        float jumlahgenteng1 =luasgenteng*25f;
        float totalhargagenteng  = hgenteng*jumlahgenteng1;

        DecimalFormat df1 = new DecimalFormat("#");
        totgenteng1 = df1.format(totalhargagenteng);
        numbergenteng = Integer.parseInt(totgenteng1);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numbergenteng);
        hasilgenteng.setText(String.valueOf(jumlahgenteng1));
        hasilgenteng1.setText(String.valueOf(totalbiaya));

    }

    private void initSpinnerBubungan() {
        ApiClient.getRequestInterface().getallbubungan().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {

                    Bubungan = response.body().getMaterials();
                    int i=0;
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namabubungan1;
                    for (int j = 0; j < Bubungan.size(); j++){
                        if (data.equalsIgnoreCase(String.valueOf(Bubungan.get(j).getNama()))) {
                            i = j;
                        }
                        listSpinner.add(Bubungan.get(j).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinbubungan.setAdapter(adapter);
                    spinbubungan.setSelection(i);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data Bubungan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMaterial> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initSpinnerGenteng() {
        ApiClient.getRequestInterface().getallgenteng().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    Genteng = response.body().getMaterials();
                    int i=0;
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namagenteng1;
                    for (int j = 0; j < Genteng.size(); j++){
                        if (data.equalsIgnoreCase(String.valueOf(Genteng.get(j).getNama()))) {
                            i = j;
                        }
                        listSpinner.add(Genteng.get(j).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spingenteng.setAdapter(adapter);
                    spingenteng.setSelection(i);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data Genteng", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(mContext, "Gagal mengambil data Pasir", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMaterial> call, Throwable t) {
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
                    Toast.makeText(mContext, "Gagal mengambil data Semen", Toast.LENGTH_SHORT).show();
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
                map.put("luasatap",luasatap.getText().toString());
                map.put("panjangnok",panjangnokh.getText().toString());
                map.put("namasemen",namasemen);
                map.put("namagenteng",namagenteng);
                map.put("namabubungan",namabubungan);
                map.put("namapasir",namapasir);
                map.put("jumlahsemen",hasilsemen.getText().toString());
                map.put("jumlahpasir",hasilpasir.getText().toString());
                map.put("jumlahgenteng",hasilgenteng.getText().toString());
                map.put("jumlahbubungan",hasilbubungan.getText().toString());
                map.put("jumlahsemendalamsak",hasilsemen1.getText().toString());
                map.put("hargapasir",hargapasir.getText().toString());
                map.put("hargasemen",hargasemen.getText().toString());
                map.put("hargagenteng",hargagenteng.getText().toString());
                map.put("hargabubungan",hargabubungan.getText().toString());
                map.put("hargatotalpasir", String.valueOf(numberpasir));
                map.put("hargatotalsemen", String.valueOf(numbersemen));
                map.put("hargatotalgenteng", String.valueOf(numbergenteng));
                map.put("hargatotalbubungan", String.valueOf(numberbubungan));
                map.put("hargatotal", String.valueOf(numberhitung));

                Call<Void> call = ApiClient.getRequestInterface().actionPutPerhitunganatap(id,apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {
                            Intent Perhitunganatap = (new Intent(Editatap.this, Perhitunganatap.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            Bundle setData = new Bundle();
                            setData.putString("idproyek",mId);
                            Perhitunganatap.putExtras(setData);
                            startActivity(Perhitunganatap);
                            Toast.makeText(Editatap.this,
                                    "Tambah Data Perhitungan Atap Berhasil ditambahkan",
                                    Toast.LENGTH_LONG).show();

                        } else if (response.code() == 422) {
                            Toast.makeText(Editatap.this,
                                    "Something Wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Editatap.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    private void initvalidation() {
        if (luasatap.getText().toString().length() == 0) {
            luasatap.setError("Data ini harus diisi");
            luasatap1 = "0";
        }else if (panjangnokh.getText().toString().length() == 0) {
            panjangnokh.setError("Data ini harus diisi");
            panjangnok = "0";
        } else if (hasilsemen.getText().toString().length() == 0) {
            hitung.setError("");
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Editatap.this);
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
