package com.example.bangunankita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.bangunankita.Model.ResponseBidang;
import com.example.bangunankita.Model.ResponseMaterial;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Campuran_adapter;
import com.example.bangunankita.adapter.Jenispengerjaan_adapter;
import com.example.bangunankita.adapter.Plesteran_adapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tambahpengecatan extends AppCompatActivity {
    Spinner spincat,spinplamur,spindinding,spinjenis;
    EditText panjang,tinggi,sisi,hargacat,hargaplamur,luas;
    TextView luascat,hasilcat,hasilcat1,hasilluas,hasilcat2,hasilplamur,hasilplamur1,hasilplamur2,total;
    Button prosesbtn,hitung;
    float hasil,luasba,hargacatparse,hargaplamurparse,kebutuhancat,kebutuhancat1,kebutuhanplamir,kebutuhanplamur1,
            hargakebcat,hargakebplamir;
    Context mContext;
    ProgressDialog pd;
    SessionManager sm;
    private int numbercat,numberplamir,numbertotal;
    private String totcat,totplamir,tottotal;
    String token,nama,p,t,pw,namapengerjaan,namasemen1,jenis,metode,jmlplam,jmlcat,namacat,namaplamur;
    String mId,Ju;
    int ProyekID;
    public String hc,ids,hpl,idp,berats,beratp,pc,pp,hk,namakeramik,hnt,namanat,pker,lker,jmlker;
    private List<Material> Cat = new ArrayList<>();
    private List<Material> Plamur = new ArrayList<>();
    private List<Perhitunganbidang1> BidangModel = new ArrayList<>();
    Jenispengerjaan[] jenispengerjaans  ={
            new Jenispengerjaan("Bangunan rumah"),
            new Jenispengerjaan("Tembok pagar"),
            new Jenispengerjaan("Sekat kamar mandi"),
            new Jenispengerjaan("Sekat kamar"),
            new Jenispengerjaan("Lainnya"),
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahpengecatan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;
        pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();
        sm= new SessionManager(Tambahpengecatan.this);
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        sm.checkLogin();
        spindinding = findViewById(R.id.spindinding);
        spincat = findViewById(R.id.spincat);
        spinplamur = findViewById(R.id.spinplamur);
        panjang = findViewById(R.id.panjangpl);
        tinggi= findViewById(R.id.tinggipl);
        sisi = findViewById(R.id.sisi);
        luas = findViewById(R.id.luascat);
        prosesbtn = findViewById(R.id.prosesbtn);
        hargacat = findViewById(R.id.hargacat);
        hargaplamur = findViewById(R.id.hargaplamur);
        luascat = findViewById(R.id.hasilluas);
        hasilcat = findViewById(R.id.hasilcat);
        hasilcat1 = findViewById(R.id.hasilcat1);
        hasilcat2 = findViewById(R.id.hasilcat2);
        hasilplamur = findViewById(R.id.hasilplamur);
        hasilplamur1 = findViewById(R.id.hasilplamur1);
        hasilluas = findViewById(R.id.hasilluas);
        hasilplamur2= findViewById(R.id.hasilplamur2);
        hitung = findViewById(R.id.hitungb);
        total = findViewById(R.id.totalbiaya);
        spinjenis = findViewById(R.id.spinjenis);
        Jenispengerjaan_adapter jenispengerjaan_adapter =
                new Jenispengerjaan_adapter(Tambahpengecatan.this,
                        android.R.layout.simple_spinner_item, jenispengerjaans);
        spinjenis.setAdapter(jenispengerjaan_adapter);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            mId = bundle.getString("idproyek1");
        }else{
            mId = "0";
        }
        initSpinnerCat();
        initSpinnerDinding();
        initSpinnerPlamur();
        prosesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String panjang1 = panjang.getText().toString().trim();
                String tinggi1 = tinggi.getText().toString().trim();
                String sisi1 = sisi.getText().toString().trim();

                if (panjang.getText().toString().length() == 0) {
                    panjang.setError("Data ini harus diisi");
                    panjang1 = "0";
                }else if (tinggi.getText().toString().length() == 0) {
                    tinggi.setError("Data ini harus diisi");
                    tinggi1 = "0";
                }else if (sisi.getText().toString().length() == 0) {
                    sisi.setError("Data ini harus diisi");
                    sisi1 = "0";
                }

                float pb = Float.parseFloat(panjang1);
                float tb = Float.parseFloat(tinggi1);
                float si = Float.parseFloat(sisi1);
                hasil = (pb*tb*si);
                luas.setText(String.valueOf(hasil));
                String luas1 = luas.getText().toString().trim();

                luasba = Float.parseFloat(luas1);
            }
        });

        spindinding.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                namapengerjaan = String.valueOf(BidangModel.get(position).getNama());
                p = String.valueOf(BidangModel.get(position).getPanjangbid());
                t = String.valueOf(BidangModel.get(position).getTinggibid());
                panjang.setText(p);
                tinggi.setText(t);

//                Toast.makeText(mContext, "Kamu memilih Semen " + selectedName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spincat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                berats = String.valueOf(Cat.get(position).getBerat());
                hc = String.valueOf(Cat.get(position).getHarga());
                namacat = String.valueOf(Cat.get(position).getNama());
//                ids = String.valueOf(Cat.get(position).getId());
                hargacat.setText(hc);

                Toast.makeText(mContext, "Kamu memilih Semen " + selectedName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinplamur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                beratp = String.valueOf(Plamur.get(position).getBerat());
                jmlplam = String.valueOf(Plamur.get(position).getJumlah());
                hpl = String.valueOf(Plamur.get(position).getHarga());
                namaplamur = String.valueOf(Plamur.get(position).getNama());
//                ids = String.valueOf(Plamur.get(position).getId());
                hargaplamur.setText(hpl);

                Toast.makeText(mContext, "Kamu memilih Semen " + selectedName, Toast.LENGTH_SHORT).show();
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
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String luas2 = luas.getText().toString().trim();
                hasilluas.setText(luas2);
                luasba = Float.parseFloat(luas2);
                String hargacat1 = hargacat.getText().toString().trim();
                hargacatparse = Float.parseFloat(hargacat1);
                String hargaplam1 = hargaplamur.getText().toString().trim();
                hargaplamurparse = Float.parseFloat(hargaplam1);
                inithitungcat();
                inithitungplamur();
                inittotal();

            }
        });

    }

    private void inittotal() {
        float tothitung = numbercat+numberplamir;
        DecimalFormat df = new DecimalFormat("#");
        tottotal = df.format(tothitung);
        numbertotal = Integer.parseInt(tottotal);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numbertotal);
        total.setText(totalbiaya);

    }

    private void inithitungcat() {
        float beratcat = Float.parseFloat(berats);

        float koefcat = 0.260f;
        kebutuhancat = luasba*koefcat;
        Double kebcat = Double.valueOf(kebutuhancat/beratcat);
        Double kebcatpembulatan = Math.ceil(kebcat);
        String keb = String.valueOf(kebcatpembulatan);
        kebutuhancat1 = Float.parseFloat(keb);
        hargakebcat = kebutuhancat1*hargacatparse;
        DecimalFormat df1 = new DecimalFormat("#");
        totcat = df1.format( hargakebcat);
        numbercat = Integer.parseInt(totcat);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numbercat);
        hasilcat.setText(String.valueOf(kebutuhancat));
        hasilcat1.setText(String.valueOf(kebutuhancat1));
        hasilcat2.setText(String.valueOf(totalbiaya));

    }

    private void inithitungplamur() {
        float beratpl = Float.parseFloat(beratp);
        float koefplamir = 0.100f;
        kebutuhanplamir = luasba*koefplamir;
        Double kebplamur = Double.valueOf(kebutuhanplamir/beratpl);
        Double kebplampembulatan = Math.ceil(kebplamur);
        String keb = String.valueOf(kebplampembulatan);
        kebutuhanplamur1 = Float.parseFloat(keb);
        hargakebplamir = kebutuhanplamur1*hargaplamurparse;
        DecimalFormat df1 = new DecimalFormat("#");
        totplamir = df1.format( hargakebplamir);
        numberplamir = Integer.parseInt(totplamir);
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String totalbiaya = formatter.format(numberplamir);
        hasilplamur.setText(String.valueOf(kebutuhanplamir));
        hasilplamur1.setText(String.valueOf(kebutuhanplamur1));
        hasilplamur2.setText(totalbiaya);

    }
    private void initSpinnerPlamur() {
        ApiClient.getRequestInterface().getallplamur().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {

                    Plamur = response.body().getMaterials();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < Plamur.size(); i++) {
                        listSpinner.add(Plamur.get(i).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinplamur.setAdapter(adapter);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data plamur", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMaterial> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSpinnerCat() {
        ApiClient.getRequestInterface().getallcat().enqueue(new Callback<ResponseMaterial>() {

            @Override
            public void onResponse(Call<ResponseMaterial> call, Response<ResponseMaterial> response) {
                if (response.code() == 200) {
                    Cat = response.body().getMaterials();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < Cat.size(); i++) {
                        listSpinner.add(Cat.get(i).getNama());

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spincat.setAdapter(adapter);
                } else {
                    Toast.makeText(mContext, "Gagal mengambil data Cat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMaterial> call, Throwable t) {
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initSpinnerDinding() {
        String apiKey = "oa00000000app";
        if (!TextUtils.isEmpty(mId) && TextUtils.isDigitsOnly(mId)) {
            ProyekID = Integer.parseInt(mId);
        } else {
            ProyekID =0;
        }


        Call<ResponseBidang> call = ApiClient.getRequestInterface().getPerhitunganbidang(ProyekID,apiKey,token);
        call.enqueue(new Callback<ResponseBidang>() {
            @Override
            public void onResponse(Call<ResponseBidang> call, Response<ResponseBidang> response) {
                if (response.code() == 200 ) {
                    BidangModel = response.body().getPerhitunganbidang();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < BidangModel.size(); i++){
                        listSpinner.add(String.valueOf(BidangModel.get(i).getNama()));
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spindinding.setAdapter(adapter);

                } else {
                    Toast.makeText(mContext, "Gagal mengambil data Bidang", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBidang> call, Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
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
                map.put("nama", namapengerjaan);
                map.put("jenis_pengerjaan", jenis);
                map.put("panjangdin", panjang.getText().toString());
                map.put("tinggidin", tinggi.getText().toString());
                map.put("sisi", sisi.getText().toString());
                map.put("luas_pengecatan", luas.getText().toString());
                map.put("metode", metode);
                map.put("nama_cat", String.valueOf(namacat));
                map.put("nama_plamur", String.valueOf(namaplamur));
                map.put("jumlahkeperluancat", hasilcat.getText().toString());
                map.put("jumlahkeperluancatkaleng", hasilcat1.getText().toString());
                map.put("jumlahkeperluanplamur", hasilplamur.getText().toString());
                map.put("jumlahkeperluanplamursak", hasilplamur1.getText().toString());
                map.put("hargacat", hargacat.getText().toString());
                map.put("hargaplamur", hargaplamur.getText().toString());
                map.put("hargacattotal", String.valueOf(numbercat));
                map.put("hargaplamurtotal", String.valueOf(numberplamir));
                Call<Void> call = ApiClient.getRequestInterface().actionCreatepengecatan(apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            Intent Perhitunganbidang = (new Intent(Tambahpengecatan.this, Perhitunganpengecatan.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            Bundle setData = new Bundle();
                            setData.putString("idproyek",mId);
                            Perhitunganbidang.putExtras(setData);
                            startActivity(Perhitunganbidang);
                            Toast.makeText(Tambahpengecatan.this,
                                    "Tambah Data Perhitungan Pengecatan Berhasil",
                                    Toast.LENGTH_LONG).show();

                        } else if (response.code() == 422) {
                            Toast.makeText(Tambahpengecatan.this,
                                    "Something Wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Tambahpengecatan.this, t.getMessage(),
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
        } else if (sisi.getText().toString().length() == 0) {
            sisi.setError("field ini harus diisi!");

        } else if (hargacat.getText().toString().length() == 0) {
            hargacat.setError("field ini harus diisi!");

        } else if (hasilluas.getText().toString().length() == 0) {
            hitung.setError("");
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Tambahpengecatan.this);
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

