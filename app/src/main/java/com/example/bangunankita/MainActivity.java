package com.example.bangunankita;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

    private int waktu_loading=2000;
    ProgressBar prg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Intent home=new Intent(MainActivity.this, Login.class);
        startActivity(home);

    }


}
