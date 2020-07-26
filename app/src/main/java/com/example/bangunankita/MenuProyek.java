package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangunankita.Util.SessionManager;

import java.util.HashMap;

public class MenuProyek extends AppCompatActivity {
    String mId;
    String mNamaproyek;
    TextView tvNmproyek,luastanah,luasbangunan;
    ImageView bidang,print;
    SessionManager sm;
    String token,luasbangunan1,luastanah1;
    String stringid;
    String tangkapidproyek;
    ImageView imageplester,imageacian,imagelantai,imageurugan,imagepengecatan,
            imagepondasi,imageplafon,imagebeton,imageatap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_proyek);
        tvNmproyek = findViewById(R.id.namaproyek);
        bidang = findViewById(R.id.bidang);
        Intent intent = getIntent();
        sm= new SessionManager(MenuProyek.this);
//         mId= intent.getStringExtra(Constant.KEY_ID_PROYEK);
        Bundle bundle = getIntent().getExtras();
        mId = bundle.getString("idproyek");
        mNamaproyek = bundle.getString("namaproyek");
        luastanah1 = bundle.getString("luastanah");
        luasbangunan1 = bundle.getString("luasbangunan");
        imageplester = findViewById(R.id.imageplester);
        imageacian = findViewById(R.id.imageacian);
        imagelantai = findViewById(R.id.imagelantai);
        imageurugan = findViewById(R.id.imageurugan);
        imagepengecatan = findViewById(R.id.imagepengecatan);
        imagepondasi = findViewById(R.id.pondasi);
        imageplafon = findViewById(R.id.plafond);
        imagebeton = findViewById(R.id.imagebeton);
        imageatap = findViewById(R.id.imageatap);
        luasbangunan = findViewById(R.id.luasbangunan);
        luastanah = findViewById(R.id.luastanah);

        tangkapidproyek = mId;
        tvNmproyek.setText(mNamaproyek);
        luasbangunan.setText("Luas Bangunan "+luasbangunan1+"m2");
        luastanah.setText("Luas Tanah "+luastanah1+"m2");

        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        stringid=(map.get(sm.KEY_ID));

//        Toast.makeText(MenuProyek.this, "Proyek Id"+mId,
//                Toast.LENGTH_SHORT).show();

        bidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Perhitunganbidang = (new Intent(MenuProyek.this, Perhitunganbidang.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek",tangkapidproyek);
                Perhitunganbidang.putExtras(setData);

                startActivity(Perhitunganbidang);
            }
        });
        imageplester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Perhitunganplester = (new Intent(MenuProyek.this, Perhitunganplesteran.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek",tangkapidproyek);
                Perhitunganplester.putExtras(setData);

                startActivity(Perhitunganplester);

            }
        });
        imageacian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Perhitunganacian = (new Intent(MenuProyek.this, Perhitunganacian.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek",tangkapidproyek);
                Perhitunganacian.putExtras(setData);

                startActivity(Perhitunganacian);
            }
        });
        imagelantai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Perhitunganlantai = (new Intent(MenuProyek.this, Perhitunganlantai.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek",tangkapidproyek);
                Perhitunganlantai.putExtras(setData);

                startActivity(Perhitunganlantai);
            }
        });
        imageurugan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Perhitunganurugan = (new Intent(MenuProyek.this, Perhitunganurugan.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek",tangkapidproyek);
                Perhitunganurugan.putExtras(setData);

                startActivity(Perhitunganurugan);
            }
        });
        imagepengecatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Perhitunganpengecatan = (new Intent(MenuProyek.this, Perhitunganpengecatan.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek",tangkapidproyek);
                Perhitunganpengecatan.putExtras(setData);

                startActivity(Perhitunganpengecatan);
            }
        });
        imageplafon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Perhitunganplafon = (new Intent(MenuProyek.this, Perhitunganplafond.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek",tangkapidproyek);
                Perhitunganplafon.putExtras(setData);

                startActivity(Perhitunganplafon);
            }
        });
        imagepondasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Perhitunganpondasi = (new Intent(MenuProyek.this, PerhitunganPondasi.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek",tangkapidproyek);
                Perhitunganpondasi.putExtras(setData);

                startActivity(Perhitunganpondasi);
            }
        });
        imagebeton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Perhitunganbeton = (new Intent(MenuProyek.this, Perhitunganbeton.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek",tangkapidproyek);
                Perhitunganbeton.putExtras(setData);

                startActivity(Perhitunganbeton);
            }
        });
        imageatap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Perhitunganatap = (new Intent(MenuProyek.this, Perhitunganatap.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek",tangkapidproyek);
                Perhitunganatap.putExtras(setData);

                startActivity(Perhitunganatap);
            }
        });


    }
}
