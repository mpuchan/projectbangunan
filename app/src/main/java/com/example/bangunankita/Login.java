package com.example.bangunankita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangunankita.Model.Pengembang_model;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Retrovit.RequestInterface;
import com.example.bangunankita.Util.SessionManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    private Button signup;
    private Button login;
    private EditText email;
    private EditText password;
    private TextView forgot;
    private SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sm = new SessionManager(Login.this);
        Dialog resetpassdialog = new Dialog(Login.this);
        resetpassdialog.setContentView(R.layout.resetpassdialog);
        init();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home=new Intent(Login.this, Register.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(home);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(email.getText().toString().length() == 0){
                        email.setError("data email harus diisi");
                    }
                HashMap<String, String> map = new HashMap<>();

                map.put("username",email.getText().toString());
                map.put("password",password.getText().toString());

                Call<Pengembang_model> call = ApiClient.getRequestInterface().actionLogin(map);

                call.enqueue(new Callback<Pengembang_model>() {
                    @Override
                    public void onResponse(Call<Pengembang_model> call,
                                           Response<Pengembang_model> response) {
                        if (response.code() == 200) {
                            Pengembang_model result = response.body();
                            Toast.makeText(Login.this,
                                    "Signin Succesfully",
                                    Toast.LENGTH_LONG).show();

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Login.this);
                            String nama= result.getNama();
                            Integer status =result.getStatus();
                            String statusk= String.valueOf(status);
                            String Image = result.getPicture();
                            Integer id = result.getId();
                            String idk = String.valueOf(id);
                            String accessToken = result.getAccessToken();
                            sm.storeLogin(idk,result.getUsername(),nama,accessToken,statusk,Image);

                            builder1.setMessage("Login Success"+statusk);
                            builder1.show();
                            Intent dashboard=new Intent(Login.this, Dashboard.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);;
                            startActivity(dashboard);

                        }else if (response.code()== 422){
                            Toast.makeText(Login.this,
                                    "Something Wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Pengembang_model> call, Throwable t) {
                        Toast.makeText(Login.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
            forgot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button confirm = resetpassdialog.findViewById(R.id.keluar);
                    EditText email= resetpassdialog.findViewById(R.id.confirmemail);
                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Call<Void> call = ApiClient.getRequestInterface().actionresetPass(email.getText().toString());
                            call.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if (response.code() == 201) {
                                        resetpassdialog.hide();
                                    } else if (response.code() == 422) {
                                        resetpassdialog.show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    resetpassdialog.hide();
                                }
                            });
                        }
                    });
                    resetpassdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    resetpassdialog.show();
                }
            });

    }

    private void init() {
        // EDIT TEXTBOX
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        // BUTTON
        signup = findViewById(R.id.signUp);
        login = findViewById(R.id.login);
        forgot = findViewById(R.id.forgot);

    }
}
