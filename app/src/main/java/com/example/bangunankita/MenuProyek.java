package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bangunankita.Util.Constant;

public class MenuProyek extends AppCompatActivity {
    String mId;
    String mNamaproyek;
    TextView tvNmproyek;
    ImageView bidang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_proyek);
        tvNmproyek = findViewById(R.id.namaproyek);
        bidang = findViewById(R.id.bidang);
        Intent intent = getIntent();
         mId= intent.getStringExtra(Constant.KEY_ID_PROYEK);
        mNamaproyek = intent.getStringExtra(Constant.KEY_NAMA_PROYEK);

        tvNmproyek.setText(mNamaproyek);

        bidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuProyek.this, Perhitunganbidang.class));
            }
        });

    }
}
