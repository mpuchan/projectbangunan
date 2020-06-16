package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangunankita.Util.Constant;
import com.example.bangunankita.Util.SessionManager;

import java.util.HashMap;

public class MenuProyek extends AppCompatActivity {
    String mId;
    String mNamaproyek;
    TextView tvNmproyek;
    ImageView bidang;
    SessionManager sm;
    String token;
    String stringid;
    String tangkapidproyek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_proyek);
        tvNmproyek = findViewById(R.id.namaproyek);
        bidang = findViewById(R.id.bidang);
        Intent intent = getIntent();
        sm= new SessionManager(MenuProyek.this);
         mId= intent.getStringExtra(Constant.KEY_ID_PROYEK);
        mNamaproyek = intent.getStringExtra(Constant.KEY_NAMA_PROYEK);

        tangkapidproyek = mId;
        tvNmproyek.setText(mNamaproyek);


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

    }
}
