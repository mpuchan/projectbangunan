package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
        Button signup;
        Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

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
                Intent dashboard=new Intent(Login.this, Dashboard.class);
                startActivity(dashboard);
            }
        });


    }

    private void init() {

        signup=findViewById(R.id.signUp);
        login=findViewById(R.id.login);
    }
}
