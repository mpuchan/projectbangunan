package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Settingprofile extends AppCompatActivity {
  private  EditText Nama,email,notelp,passlama,passbaru,repeatpass;
  private Button edit;
    private String token,status,picture1,stringid,name;
    private int Idpengembang = 0;
    SessionManager sm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingprofile);
        sm= new SessionManager(Settingprofile.this);
        edit = findViewById(R.id.editbtn);
        Nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        notelp = findViewById(R.id.notelp);
        passlama= findViewById(R.id.passlama);
        passbaru = findViewById(R.id.passwordbaru);
        repeatpass = findViewById(R.id.repeatpass);
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        status=(map.get(sm.KEY_STATUS));
        picture1=(map.get(sm.KEY_PICTURE));
        stringid=(map.get(sm.KEY_ID));
        name= (map.get(sm.KEY_NAMA));
        Nama.setText(name);
        sm.checkLogin();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String apiKey = "oa00000000app";
                if (!TextUtils.isEmpty(stringid) && TextUtils.isDigitsOnly(stringid)) {
                    Idpengembang= Integer.parseInt(stringid);
                } else {
                    Idpengembang =0;
                }
                HashMap<String, String> map = new HashMap<>();

                map.put("nama", Nama.getText().toString());


                Call<Void> call = ApiClient.getRequestInterface().actionUpdateprofile(Idpengembang,apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {
                            Toast.makeText(Settingprofile.this,
                                    "Edit Data User Berhasil dirubah",
                                    Toast.LENGTH_LONG).show();
                            Intent Dashboard = new Intent(Settingprofile.this, Dashboard.class);
                            startActivity(Dashboard);

                        } else if (response.code() == 422) {
                            Toast.makeText(Settingprofile.this,
                                    "Something Wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Settingprofile.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}
