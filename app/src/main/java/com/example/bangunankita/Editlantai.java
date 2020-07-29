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

import com.example.bangunankita.Model.Campuran;
import com.example.bangunankita.Model.Jenispengerjaan;
import com.example.bangunankita.Model.Material;
import com.example.bangunankita.Model.ResponseMaterial;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Campuran_adapter;
import com.example.bangunankita.adapter.Jenispengerjaan_adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Editlantai extends AppCompatActivity {
    Spinner spindinding,spinsemen,spinjenis,campuran,spinkeramik,spinnat,spinpasir;
    EditText panjang,lebar,hargasemen,luas,nama_pengerjaan,hkeramik,hnat,hpasir,namapengerjaan;
    TextView luaslan,hasilse,hasilse1,hasilse2,hasilpas,hasilpas1,hasilpas2,total,
            hasilker,hasilker1,hasilnat,hasilnat1,hasilker2;
    Button prosesbtn,hitung;
    float hasil,luasba,hargapasirparse,hargakeramikparse,harganatparse,hargasemen1,hargasemenparse,kebutuhansemensak,kebutuhanpasir,kebutuhannat,
            hargapasir1,hargakeramik1,harganat1,totpasir,hargasementot,getluaskeramik,kebutuhankeramik,kebutuhansemen,keb1kardus;
    Context mContext;
    SessionManager sm;
    String token,nama,p,t,pw,namasemen1,idperhitunganlantai,jenis,namanat1,harkeramik1,namaker1,metode,namapas,namasemen,namapasir,harsemen1,harpasir1,harker1,harnat1;
    String mId,Ju;
    int ProyekID,idperhitunganlantai1;
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
        setContentView(R.layout.activity_editlantai);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;
        sm= new SessionManager(Editlantai.this);
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        sm.checkLogin();
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
                new Campuran_adapter(Editlantai.this,
                        android.R.layout.simple_spinner_item, campurans);
        campuran.setAdapter(campuran_adapter);
        Jenispengerjaan_adapter jenispengerjaan_adapter =
                new Jenispengerjaan_adapter(Editlantai.this,
                        android.R.layout.simple_spinner_item, jenispengerjaans);
        spinjenis.setAdapter(jenispengerjaan_adapter);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            mId = bundle.getString("ProyekId");
        }else{
            mId = "0";
        }
        float luas1;
        prosesbtn = findViewById(R.id.prosesbtn);

        idperhitunganlantai= bundle.getString("id");
        nama_pengerjaan.setText(bundle.getString("nama"));
        bundle.getString("jenis_pengerjaan");
        panjang.setText(bundle.getString("panjanglan"));
        lebar.setText(bundle.getString("lebarlan"));
        luaslan.setText(bundle.getString("luas_lantai"));
        hasilker.setText(bundle.getString("jumlahkeperluankeramik"));
        hasilker2.setText(bundle.getString("jumlahkeperluankeramikdus"));
        hasilse.setText(bundle.getString("Jumlahkeperluansemen"));
        hasilpas.setText(bundle.getString("jumlahkeperluanpasir"));
        hasilpas1.setText(bundle.getString("jumlahkeperluanpasir"));
        hasilnat.setText(bundle.getString("jumlahkeperluannat"));
        metode= bundle.getString("metode");
        hasilse1.setText(bundle.getString("jumlahdalamsak"));
        harsemen1= bundle.getString("hargasemen");
        harpasir1 =bundle.getString("hargapasir");
        harnat1 =bundle.getString("harganat");
        luas.setText(bundle.getString("luas_lantai"));
        harkeramik1 =bundle.getString("hargakeramik");
        namasemen= bundle.getString("nama_semen");
        namakeramik= bundle.getString("nama_keramik");
        namanat= bundle.getString("nama_semennat");
//        namasemen= bundle.getString("metode");
        namapas= bundle.getString("nama_pasir");
        hasilker1.setText(bundle.getString("hargakeramiktotal"));
        hasilpas2.setText(bundle.getString("hargapasirtotal"));
        hasilnat1.setText(bundle.getString("harganattotal"));
        hasilse2.setText(bundle.getString("hargasementotal"));
        total.setText(bundle.getString("hargatotal"));

        initSpinnerSemen();
        initSpinnerPasir();
        initSpinnerSemenNat();
        initSpinnerKeramik();
        prosesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String panjang1 = panjang.getText().toString().trim();
                String lebar1 = lebar.getText().toString().trim();

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

                if (selectedName.equalsIgnoreCase(namasemen)){
                    namasemen1 = namasemen;
                    berats = String.valueOf(Semen.get(position).getBerat());
                    hargasemen.setText(harsemen1);
                }else{
                    namasemen1= String.valueOf(Semen.get(position).getNama());
                    hs = String.valueOf(Semen.get(position).getHarga());
                    hargasemen.setText(hs);
                    berats = String.valueOf(Semen.get(position).getBerat());

                }

                Toast.makeText(mContext, "Kamu memilih Semen " + selectedName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();

                if (selectedName.equalsIgnoreCase(namanat)){
                    namanat1 = namanat;
                    hnat.setText(harnat1);
                }else{
                    namanat1= String.valueOf(Semennat.get(position).getNama());
                    hnt = String.valueOf(Semennat.get(position).getHarga());
                    hnat.setText(hnt);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinkeramik.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();

                if (selectedName.equalsIgnoreCase(namakeramik)){
                    namaker1 = namakeramik;
                    pker = String.valueOf(Keramik.get(position).getPanjang());
                    lker= String.valueOf(Keramik.get(position).getLebar());
                    jmlker = String.valueOf(Keramik.get(position).getJumlah());
                    hkeramik.setText(harkeramik1);
                }else{
                    namaker1= String.valueOf(Keramik.get(position).getNama());
                    hk = String.valueOf(Keramik.get(position).getHarga());
                    pker = String.valueOf(Keramik.get(position).getPanjang());
                    lker= String.valueOf(Keramik.get(position).getLebar());
                    jmlker = String.valueOf(Keramik.get(position).getJumlah());
                    hkeramik.setText(hk);
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

                if (selectedName.equalsIgnoreCase(namapas)){
                    namapasir = namapas;
                    hpasir.setText(harpasir1);
                }else{
                    namapasir= String.valueOf(Pasir.get(position).getNama());
                    hp = String.valueOf(Semen.get(position).getHarga());
                    hpasir.setText(hp);

                }


                hp = String.valueOf(Pasir.get(position).getHarga());
                idp = String.valueOf(Pasir.get(position).getId());
                namapasir = String.valueOf(Pasir.get(position).getNama());
                hpasir.setText(hp);

                Toast.makeText(mContext, "Kamu memilih Pasir " + selectedName, Toast.LENGTH_SHORT).show();
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

                Toast.makeText(mContext, "Kamu memilih Campuran " + pc, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String luas2 = luas.getText().toString().trim();
                luasba = Float.parseFloat(luas2);
                String hargapasir = hpasir.getText().toString().trim();
                hargapasirparse = Float.parseFloat(hargapasir);
                String hargasemen1 = hargasemen.getText().toString().trim();
                hargasemenparse = Float.parseFloat(hargasemen1);
                String hargakeramik = hkeramik.getText().toString().trim();
                hargakeramikparse =Float.parseFloat(hargakeramik);
                String harganat = hnat.getText().toString().trim();
                harganatparse =Float.parseFloat(harganat);
                hitungkeramik();
                hitungsemen();
                htungsemennat();
                hitungpasir();
            }
        });
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

        hasilker.setText(String.valueOf(kebutuhankeramik));
        hasilker1.setText(String.valueOf(hargakeramik1));
        hasilker2.setText(String.valueOf(keb1kardus));

    }

    private void hitungpasir() {
        float koefpasir = Float.parseFloat(pp);
        kebutuhanpasir = luasba*koefpasir;
        hargapasir1 = kebutuhanpasir*hargapasirparse;
        hasilpas.setText(String.valueOf(kebutuhanpasir));
        hasilpas1.setText(String.valueOf(kebutuhanpasir));
        hasilpas2.setText(String.valueOf(hargapasir1));

    }

    private void htungsemennat() {
        float koefnat = Float.parseFloat(pw);
        kebutuhannat = luasba*koefnat;
        harganat1 = kebutuhannat*harganatparse;
        hasilnat.setText(String.valueOf(kebutuhannat));
        hasilnat1.setText(String.valueOf(harganat1));

    }

    private void hitungsemen() {
        float koefsemen = Float.parseFloat(pc);
        float beratsak = Float.parseFloat(berats);
        kebutuhansemen = luasba*koefsemen;
        kebutuhansemensak = kebutuhansemen/beratsak;
        hargasemen1 = kebutuhansemensak*hargasemenparse;
        hasilse.setText(String.valueOf(kebutuhansemen));
        hasilse1.setText(String.valueOf(kebutuhansemensak));
        hasilse2.setText(String.valueOf(hargasemen1));

    }

    private void initSpinnerKeramik() {
        ApiClient.getRequestInterface().getallkeramik().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    int i = 0;
                    Keramik = response.body().getMaterials();
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namakeramik;
                    for (int j = 0; j < Keramik.size(); j++) {
                        if (data.equalsIgnoreCase(String.valueOf(Keramik.get(j).getNama()))) {
                            i = j;
                        }
                        listSpinner.add(Keramik.get(i).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinkeramik.setAdapter(adapter);
                    spinkeramik.setSelection(i);
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

    private void initSpinnerSemenNat() {
        ApiClient.getRequestInterface().getallsemennat().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    Semennat = response.body().getMaterials();
                    int i=0;
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namanat;
                    for (int j = 0; j < Semennat.size(); j++) {
                        if (data.equalsIgnoreCase(String.valueOf(Semennat.get(j).getNama()))) {
                            i = j;
                        }
                        listSpinner.add(Semennat.get(j).getNama());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnat.setAdapter(adapter);
                    spinnat.setSelection(i);
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
                    Semen = response.body().getMaterials();
                    int i=0;
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namasemen;
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
                    Pasir = response.body().getMaterials();
                    int i = 0;
                    List<String> listSpinner = new ArrayList<String>();
                    String data = namapas;
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mainsavebidang,menu);
        MenuItem item = menu.findItem(R.id.app_bar_savedata);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String apiKey = "oa00000000app";
                HashMap<String, String> map = new HashMap<>();
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
                map.put("jumlahkeperluansemen", hasilse.getText().toString());
                map.put("jumlahkeperluannat", hasilnat.getText().toString());
                map.put("jumlahdalamsak", hasilse1.getText().toString());
                map.put("hargakeramik", hkeramik.getText().toString());
                map.put("hargapasir", hpasir.getText().toString());
                map.put("hargasemen", hargasemen.getText().toString());
                map.put("harganat", hnat.getText().toString());
                map.put("hargakeramiktotal", hasilker1.getText().toString());
                map.put("hargasementotal", hasilse2.getText().toString());
                map.put("hargapasirtotal", hasilpas2.getText().toString());
                map.put("harganattotal", hasilnat1.getText().toString());
                Call<Void> call = ApiClient.getRequestInterface().actionPutPerhitunganlantai(ProyekID,apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            Intent Perhitunganlantai = (new Intent(Editlantai.this, Perhitunganlantai.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            Bundle setData = new Bundle();
                            setData.putString("idproyek",mId);
                            Perhitunganlantai.putExtras(setData);
                            startActivity(Perhitunganlantai);
                            Toast.makeText(Editlantai.this,
                                    "Edit Data Perhitungan Lantai Berhasil",
                                    Toast.LENGTH_LONG).show();

                        } else if (response.code() == 422) {
                            Toast.makeText(Editlantai.this,
                                    "Something Wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Editlantai.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }
}
