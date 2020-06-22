package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Perhitunganurugan extends AppCompatActivity {
    ImageView imageurugan;
    String mId,Ju;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitunganurugan);
        imageurugan = findViewById(R.id.tambahurugan);
        Bundle bundle = getIntent().getExtras();
        mId = bundle.getString("idproyek");

        Toast.makeText(Perhitunganurugan.this, "Proyek Id"+mId,
                Toast.LENGTH_SHORT).show();
        Ju = mId;
        imageurugan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent tambahurugan = (new Intent(Perhitunganurugan.this, Tambahurugan.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                Bundle setData = new Bundle();
                setData.putString("idproyek1",mId);
                tambahurugan.putExtras(setData);

                startActivity(tambahurugan);

            }
        });
    }
}
