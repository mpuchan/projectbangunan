package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangunankita.Model.ResponseModel;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Edit_Proyek extends AppCompatActivity {
    private View background;
    private EditText et_tanggal;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;
    private EditText namaproyek,lokasi;
    private TextView idpengembang;
    private Button tambah,cancel;
    private String token;
    private String apiKey;
    SessionManager sm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__proyek);
        et_tanggal = (EditText) findViewById(R.id.tanggal);
        namaproyek = (EditText) findViewById(R.id.nama);
        lokasi = findViewById(R.id.lokasi);
        tambah = findViewById(R.id.add_proyek);
        sm= new SessionManager(Edit_Proyek.this);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        background = findViewById(R.id.background);
        idpengembang = findViewById(R.id.idt);
        et_tanggal.setFocusableInTouchMode(false);
        et_tanggal.setFocusable(false);
        HashMap<String,String> map = sm.getDetailLogin();
        et_tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
//        idpengembang=(map.get(sm.KEY_TOKEN));
        idpengembang.setText(map.get(sm.KEY_ID));
        token=(map.get(sm.KEY_TOKEN));
        apiKey = "oa00000000app";

//        tambah.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                HashMap<String, String> map = new HashMap<>();
//
//                map.put("nama_proyek", namaproyek.getText().toString());
//                map.put("lokasi", lokasi.getText().toString());
//                map.put("tanggal", et_tanggal.getText().toString());
//                map.put("PengembangId", idpengembang.getText().toString());
//
//
//                Call<ResponseModel> call = ApiClient.getRequestInterface().actionCreateProyek(apiKey,token,map);
//                call.enqueue(new Callback<ResponseModel>() {
//                    @Override
//                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                        if (response.code() == 200) {
//                            Toast.makeText(Add_Proyek.this,
//                                    "Tambah Data Proyek Berhasil",
//                                    Toast.LENGTH_LONG).show();
//                            Intent Dashboard = new Intent(Add_Proyek.this, Dashboard.class);
//                            startActivity(Dashboard);
//
//                        } else if (response.code() == 422) {
//                            Toast.makeText(Add_Proyek.this,
//                                    "Something Wrong",
//                                    Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseModel> call, Throwable t) {
//                        Toast.makeText(Add_Proyek.this, t.getMessage(),
//                                Toast.LENGTH_LONG).show();
//                    }
//                });
//
//            }
//        });




        if (savedInstanceState == null) {
            background.setVisibility(View.INVISIBLE);

            final ViewTreeObserver viewTreeObserver = background.getViewTreeObserver();

            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        klikfabeffect();
                        background.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }

                });
            }

        }

    }

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                et_tanggal.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void klikfabeffect() {
        int cx = background.getRight() - getDips(44);
        int cy = background.getBottom() - getDips(44);

        float finalRadius = Math.max(background.getWidth(), background.getHeight());

        Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                background,
                cx,
                cy,
                0,
                finalRadius);

        circularReveal.setDuration(300);
        background.setVisibility(View.VISIBLE);
        circularReveal.start();

    }

    private int getDips(int dps) {
        Resources resources = getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dps,
                resources.getDisplayMetrics());
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int cx = background.getWidth() - getDips(44);
            int cy = background.getBottom() - getDips(44);

            float finalRadius = Math.max(background.getWidth(), background.getHeight());
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(background, cx, cy, finalRadius, 0);

            circularReveal.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    background.setVisibility(View.INVISIBLE);
                    finish();
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            circularReveal.setDuration(300);
            circularReveal.start();
        }
        else {
            super.onBackPressed();
        }
    }
}
