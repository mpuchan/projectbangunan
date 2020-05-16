package com.example.bangunankita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class Login extends AppCompatActivity {
    private RequestInterface requestInterface;
    private Button signup;
    private Button login;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        requestInterface = ApiClient.getClient().create(RequestInterface.class);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home=new Intent(Login.this, Register.class);
                startActivity(home);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, String> map = new HashMap<>();

                map.put("username",email.getText().toString());
                map.put("password",password.getText().toString());

                Call<Pengembang_model> call = requestInterface.actionLogin(map);

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
                            Integer id = result.getId();
                            String accessToken = result.getAccessToken();

                            builder1.setMessage("Login Success");
                            builder1.show();
                            Intent dashboard=new Intent(Login.this, Dashboard.class);
                            dashboard.putExtra("nama",nama);
                            dashboard.putExtra("id",id);
                            dashboard.putExtra("accessToken",accessToken);

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


    }

    private void init() {
        // EDIT TEXTBOX
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        // BUTTON
        signup = findViewById(R.id.signUp);
        login = findViewById(R.id.login);

    }
}
