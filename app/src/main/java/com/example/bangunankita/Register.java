package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bangunankita.Model.Pengembang_model;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Retrovit.RequestInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    private Button regi1,cancel;
    private EditText nama,email,notelp,username,password,repass;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        pd = new ProgressDialog(this);

        regi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();

                map.put("nama", nama.getText().toString());
                map.put("email", email.getText().toString());
                map.put("notelp", notelp.getText().toString());
                map.put("username", username.getText().toString());
                map.put("password", password.getText().toString());
                map.put("retypePassword", repass.getText().toString());


                    Call<Void> call = ApiClient.getRequestInterface().actionRegisterMobile(map);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 200) {
                                Toast.makeText(Register.this,
                                        "Registrasi Succesfully",
                                        Toast.LENGTH_LONG).show();
                                Intent login = new Intent(Register.this, Login.class);
                                startActivity(login);

                            } else if (response.code() == 422) {
                                Toast.makeText(Register.this,
                                        "Something Wrong",
                                        Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(Register.this, t.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

            }
        });

    }


    private void init() {
        regi1 = findViewById(R.id.regis1);
        cancel = findViewById(R.id.cancel);
        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        notelp = findViewById(R.id.notelp);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repass = findViewById(R.id.repeatpass);

    }
}
