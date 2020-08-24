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

import com.example.bangunankita.Model.Campuran;
import com.example.bangunankita.Model.Jenispengerjaan;
import com.example.bangunankita.Model.Material;
import com.example.bangunankita.Model.Perhitunganbidang1;
import com.example.bangunankita.Model.ResponseMaterial;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Campuran_adapter;
import com.example.bangunankita.adapter.Jenispengerjaan_adapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tambahlantai extends AppCompatActivity {
    Spinner spindinding,spinsemen,spinjenis,campuran,spinkeramik,spinnat,spinpasir;
    EditText panjang,lebar,hargasemen,luas,nama_pengerjaan,hkeramik,hnat,hpasir,namapengerjaan;
    TextView luaslan,hasilse,hasilse1,hasilse2,hasilpas,hasilpas1,hasilpas2,total,
            hasilker,hasilker1,hasilnat,hasilnat1,hasilker2,hasilbid;
    Button prosesbtn,hitung;
    ProgressDialog pd;
    float hasil,luasba,hargapasirparse,hargakeramikparse,harganatparse,hargasemenparse,kebutuhansemensak,kebutuhanpasir,kebutuhannat,hargasemen1,
            hargapasir1,hargakeramik1,harganat1,totpasir,hargasementot,getluaskeramik,kebutuhankeramik,kebutuhansemen,keb1kardus;
    Context mContext;
    SessionManager sm;
    String token,nama,p,t,pw,namasemen1,jenis,metode,namasemen,namapasir;
    String mId,Ju;
    private int numberkeramik,numberpasir,numbersemen,numbernat,numbertotal;
    private String totkeramik1,totsemen1,totpasir1,tothitungan,totnat1;
    int ProyekID;
    public String hs,ids,hp,idp,berats,pc,pp,hk,namakeramik,hnt,namanat,pker,lker,jmlker;
    private List<Material> Semen = new ArrayList<>();
    private List<Material> Semennat = new ArrayList<>();
    private List<Material> Pasir = new ArrayList<>();
    private List<Material> Keramik = new ArrayList<>();
    Campuran[] campurans ={
            new Campuran("keramik ukuran 20*20", 10.4,0.045,1.620),
            new Campuran("keramik ukuran 30*30", 10, 0.045,1.5),
            new Campuran("keramik ukuran 40*40", 8.190,0.045,1.620)
    };
    Jenispengerjaan[] jenispengerjaans  ={
            new Jenispengerjaan("ruang utama"),
            new Jenispengerjaan("kamar"),
            new Jenispengerjaan("kamar mandi"),
            new Jenispengerjaan("teras"),
            new Jenispengerjaan("ruang tamu"),
            new Jenispengerjaan("ruang makan"),
            new Jenispengerjaan("dapur"),
            new Jenispengerjaan("Lainnya"),
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahlantai);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;
        sm= new SessionManager(Tambahlantai.this);
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        sm.checkLogin();
        pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();
        hasilbid = findViewById(R.id.hasilbid);
        nama_pengerjaan = findViewById(R.id.nama_pengerjaan1);
        spinkeramik = findViewById(R.id.spinkeramik);
        spinsemen = findViewById(R.id.spinsemen);
        spinnat = findViewById(R.id.semennatpilih);
        spinpasir = findViewById(R.id.spinpasir);
        spinjenis = findViewById(R.id.spinjenis);
        campuran = findViewById(R.id.spinner22);
        panjang = findViewById(R.id.panjangb);
        lebar = findViewById(R.id.lebarb);
        hkeramik= findViewById(R.id.hkeramik);
        hnat= findViewById(R.id.hsemenat);
        hpasir= findViewById(R.id.hargapasir);
        hargasemen= findViewById(R.id.hargasemen);
        luas = findViewById(R.id.luaslantai);
        hitung = findViewById(R.id.hitungb);
        luaslan = findViewById(R.id.hasilbid);
        hasilse = findViewById(R.id.hasils);
        hasilse1 = findViewById(R.id.hasils1);
        hasilse2 = findViewById(R.id.hasils2);
        hasilpas = findViewById(R.id.hasilp2);
        hasilpas1 = findViewById(R.id.hasilp);
        hasilpas2 = findViewById(R.id.hasilp1);
        hasilker = findViewById(R.id.hasilker);
        hasilker1 = findViewById(R.id.hasilker1);
        hasilker2 = findViewById(R.id.hasilker2);
        hasilnat = findViewById(R.id.hasilnat);
        hasilnat1 = findViewById(R.id.hasilnat1);
        total = findViewById(R.id.total);

        Campuran_adapter campuran_adapter =
                new Campuran_adapter(Tambahlantai.this,
                        android.R.layout.simple_spinner_item, campurans);
        campuran.setAdapter(campuran_adapter);
        Jenispengerjaan_adapter jenispengerjaan_adapter =
                new Jenispengerjaan_adapter(Tambahlantai.this,
                        android.R.layout.simple_spinner_item, jenispengerjaans);
        spinjenis.setAdapter(jenispengerjaan_adapter);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            mId = bundle.getString("idproyek1");
        }else{
            mId = "0";
        }
        float luas1;
       prosesbtn = findViewById(R.id.prosesbtn);
        initSpinnerSemen();
        initSpinnerPasir();
        initSpinnerSemenNat();
        initSpinnerKeramik();


        prosesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String panjang1 = panjang.getText().toString().trim();
                String lebar1 = lebar.getText().toString().trim();
                if (panjang.getText().toString().length() == 0) {
                    panjang.setError("Data ini harus diisi");
                    panjang1 = "0";
                }else if (lebar.getText().toString().length() == 0) {
                    lebar.setError("Data ini harus diisi");
                    lebar1 = "0";
                }
                float pb = Float.parseFloat(panjang1);
                float lb = Float.parseFloat(lebar1);
                hasil = (pb*lb);
                luas.setText(String.valueOf(hasil));
                String luas1 = luas.getText().toString().trim();
                luasba = Float.parseFloat(luas1);
            }
        });
        spinsemen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                berats = String.valueOf(Semen.get(position).getBerat());
                hs = String.valueOf(Semen.get(position).getHarga());
                namasemen = String.valueOf(Semen.get(position).getNama());
                ids = String.valueOf(Semen.get(position).getId());
                hargasemen.setText(hs);

//                Toast.makeText(mContext, "Kamu memilih Semen " + selectedName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                hnt = String.valueOf(Semennat.get(position).getHarga());
                namanat = String.valueOf(Semennat.get(position).getNama());
                hnat.setText(hnt);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinkeramik.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                hk = String.valueOf(Keramik.get(position).getHarga());
                namakeramik = String.valueOf(Keramik.get(position).getNama());
                pker = String.valueOf(Keramik.get(position).getPanjang());
                lker= String.valueOf(Keramik.get(position).getLebar());
                jmlker = String.valueOf(Keramik.get(position).getJumlah());
                hkeramik.setText(hk);
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
                hpasir.setText(hp);

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


//                Toast.makeText(mContext, "Kamu memilih Campuran " + pc, Toast.LENGTH_SHORT).show();
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
                pw = String.valueOf(obj.getPw());
                metode = String.valueOf(obj.getCampuran());

//                Toast.makeText(mContext, "Kamu memilih Campuran " + pc, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String luas2 = luas.getText().toString().trim();
                String hargapasir = hpasir.getText().toString().trim();
                String hargasemen1 = hargasemen.getText().toString().trim();
                String hargakeramik = hkeramik.getText().toString().trim();
                String harganat = hnat.getText().toString().trim();
                luasba = Float.parseFloat(luas2);
                hasilbid.setText(luas2);
                if (hpasir.getText().toString().length() == 0) {
                    hpasir.setError("Data ini harus diisi");
                    hargapasir = "0";
                }else if (hargasemen.getText().toString().length() == 0) {
                    hargasemen.setError("Data ini harus diisi");
                    hargasemen1 = "0";

                }else if (hkeramik.getText().toString().length() == 0) {
                    hkeramik.setError("Data ini harus diisi");
                    hargakeramik = "0";
                }
                else if (hnat.getText().toString().length() == 0) {
                    hnat.setError("Data ini harus diisi");
                    harganat = "0";
                }
                hargapasirparse = Float.parseFloat(hargapasir);
                hargasemenparse = Float.parseFloat(hargasemen1);
                hargakeramikparse =Float.parseFloat(hargakeramik);
                harganatparse =Float.parseFloat(harganat);
                hitungkeramik();
                hitungsemen();
                htungsemennat();
                hitungpasir();
                hitungtotal();
            }
        });
    }

    private void hitungtotal() {
        float tothitung = numberkeramik+numbernat+numbersemen+numberpasir;
        DecimalFormat df = new DecimalFormat("#");
        tothitungan = df.format(tothitung);
        numbertotal = Integer.parseInt(tothitungan);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numbertotal);
        total.setText(totalbiaya);
    }

    private void hitungkeramik() {
        float panjangkeramik = Float.parseFloat(pker);
        float lebarkeramik = Float.parseFloat(lker);
        float jumlahker = Float.parseFloat(jmlker);

        float m2 = 10000f;
        getluaskeramik = (panjangkeramik*lebarkeramik)/m2;

        Double kebker = Double.valueOf(luasba/getluaskeramik);
        Double kebkerpembulatan = Math.ceil(kebker);
        String keb = String.valueOf(kebkerpembulatan);
        kebutuhankeramik = Float.parseFloat(keb);
        keb1kardus = kebutuhankeramik/jumlahker;
        hargakeramik1 = keb1kardus*hargakeramikparse;

        DecimalFormat df1 = new DecimalFormat("#");
        totkeramik1 = df1.format(hargakeramik1);
        numberkeramik = Integer.parseInt(totkeramik1);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numberkeramik);

        hasilker.setText(String.valueOf(kebutuhankeramik));
        hasilker1.setText(totalbiaya);
        hasilker2.setText(String.valueOf(keb1kardus));

    }

    private void hitungpasir() {
        float koefpasir = Float.parseFloat(pp);
        kebutuhanpasir = luasba*koefpasir;
        hargapasir1 = kebutuhanpasir*hargapasirparse;
        DecimalFormat df1 = new DecimalFormat("#");
        totpasir1 = df1.format(hargapasir1);
        numberpasir = Integer.parseInt(totpasir1);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numberpasir);

        hasilpas.setText(String.valueOf(kebutuhanpasir));
        hasilpas1.setText(String.valueOf(kebutuhanpasir));
        hasilpas2.setText(totalbiaya);

    }

    private void htungsemennat() {
        float koefnat = Float.parseFloat(pw);
        kebutuhannat = luasba*koefnat;
        harganat1 = kebutuhannat*harganatparse;
        DecimalFormat df1 = new DecimalFormat("#");
        totnat1 = df1.format(harganat1);
        numbernat = Integer.parseInt(totnat1);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numbernat);
        hasilnat.setText(String.valueOf(kebutuhannat));
        hasilnat1.setText(totalbiaya);
    }

    private void hitungsemen() {
        float koefsemen = Float.parseFloat(pc);
        float beratsak = Float.parseFloat(berats);
        kebutuhansemen = luasba*koefsemen;
        kebutuhansemensak = kebutuhansemen/beratsak;
        hargasemen1 = kebutuhansemensak*hargasemenparse;
        DecimalFormat df1 = new DecimalFormat("#");
        totsemen1 = df1.format(hargasemen1);
        numbersemen = Integer.parseInt(totsemen1);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numbersemen);
        hasilse.setText(String.valueOf(kebutuhansemen));
        hasilse1.setText(String.valueOf(kebutuhansemensak));
        hasilse2.setText(totalbiaya);

    }

    private void initSpinnerKeramik() {
        ApiClient.getRequestInterface().getallkeramik().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    pd.hide();
                    Keramik = response.body().getMaterials();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < Keramik.size(); i++) {
                        listSpinner.add(Keramik.get(i).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinkeramik.setAdapter(adapter);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data Keramik", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMaterial> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSpinnerSemenNat() {
        ApiClient.getRequestInterface().getallsemennat().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    pd.hide();
                    Semennat = response.body().getMaterials();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < Semennat.size(); i++) {
                        listSpinner.add(Semennat.get(i).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnat.setAdapter(adapter);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data Semenat", Toast.LENGTH_SHORT).show();
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
                    pd.hide();
                    Semen = response.body().getMaterials();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < Semen.size(); i++) {
                        listSpinner.add(Semen.get(i).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinsemen.setAdapter(adapter);
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
                    Toast.makeText(mContext, "Gagal mengambil data Pasir", Toast.LENGTH_SHORT).show();
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
                map.put("nama",nama_pengerjaan.getText().toString());
                map.put("jenis_pengerjaan", jenis);
                map.put("panjanglan", panjang.getText().toString());
                map.put("lebarlan", lebar.getText().toString());
                map.put("luas_lantai", luas.getText().toString());
                map.put("metode", metode);
                map.put("nama_keramik", namakeramik);
                map.put("nama_semen", namasemen);
                map.put("nama_semennat", namanat);
                map.put("nama_pasir", namapasir);
                map.put("jumlahkeperluankeramik", hasilker.getText().toString());
                map.put("jumlahkeperluankeramikdus", hasilker2.getText().toString());
                map.put("jumlahkeperluanpasir", hasilpas.getText().toString());
                map.put("Jumlahkeperluansemen", hasilse.getText().toString());
                map.put("jumlahkeperluannat", hasilnat.getText().toString());
                map.put("jumlahdalamsak", hasilse1.getText().toString());
                map.put("hargakeramik", hkeramik.getText().toString());
                map.put("hargapasir", hpasir.getText().toString());
                map.put("hargasemen", hargasemen.getText().toString());
                map.put("harganat", hnat.getText().toString());
                map.put("hargakeramiktotal", String.valueOf(numberkeramik));
                map.put("hargasementotal", String.valueOf(numbersemen));
                map.put("hargapasirtotal", String.valueOf(numberpasir));
                map.put("harganattotal", String.valueOf(numbernat));
                Call<Void> call = ApiClient.getRequestInterface().actionCreatelantai(apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            Intent Perhitunganlantai = (new Intent(Tambahlantai.this, Perhitunganlantai.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            Bundle setData = new Bundle();
                            setData.putString("idproyek",mId);
                            Perhitunganlantai.putExtras(setData);
                            startActivity(Perhitunganlantai);
                            Toast.makeText(Tambahlantai.this,
                                    "Tambah Data Perhitungan Lantai Berhasil",
                                    Toast.LENGTH_LONG).show();

                        } else if (response.code() == 422) {
                            Toast.makeText(Tambahlantai.this,
                                    "Something Wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Tambahlantai.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    private void initvalidation() {
        if (nama_pengerjaan.getText().toString().length() == 0) {
            nama_pengerjaan.setError("field ini harus diisi!");

        } else if (panjang.getText().toString().length() == 0) {
            panjang.setError("field ini harus diisi!");

        } else if (lebar.getText().toString().length() == 0) {
            lebar.setError("field ini harus diisi!");
        } else if (hargasemen.getText().toString().length() == 0) {
            hargasemen.setError("field ini harus diisi!");

        } else if (hkeramik.getText().toString().length() == 0) {
            hkeramik.setError("field ini harus diisi!");

        } else if (hnat.getText().toString().length() == 0) {
            hnat.setError("field ini harus diisi!");

        } else if (hpasir.getText().toString().length() == 0) {
            hpasir.setError("field ini harus diisi!");
        } else if (hasilbid.getText().toString().length() == 0) {
            hitung.setError("");
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Tambahlantai.this);
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
