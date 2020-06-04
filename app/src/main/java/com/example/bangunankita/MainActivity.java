package com.example.bangunankita;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.bangunankita.Util.SessionManager;

public class MainActivity extends Activity {

    private int waktu_loading=2000;
    ProgressBar prg;
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm= new SessionManager(MainActivity.this);
        initComponents();

        new Thread(new Runnable() {
            public void run() {
                jalan();
                buka();
                finish();
            }
        }).start();
    }

    private void initComponents() {
        prg=findViewById(R.id.progressBar3);
    }

    private void jalan() {
        for (int progress=0; progress<100; progress+=40) {
            try{
                Thread.sleep(waktu_loading);
                prg.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void buka() {
        sm.checkLogin();
        Intent home=new Intent(MainActivity.this, Dashboard.class);
        startActivity(home);

    }


}
