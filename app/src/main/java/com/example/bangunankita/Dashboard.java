package com.example.bangunankita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangunankita.Model.Proyek_model;
import com.example.bangunankita.Model.ResponseModel;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Retrovit.RequestInterface;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Proyek_adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity {

    private Proyek_adapter proyek_adapter;
    private List<Proyek_model> proyekModels = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private FloatingActionButton Fabadd;
    //    private ProgressBar  mProgressBar;
    private TextView name;
    private TextView datanull;
    private TextView edit;
    SwipeRefreshLayout swipeRefreshLayout;
    private String TAG = "Dashboard";
    private String token;
    private String stringid;
    private ImageView image;
    int PengembangID;
    SessionManager sm;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        name = findViewById(R.id.namekita);
        datanull =findViewById(R.id.txt_resultadapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edit = findViewById(R.id.editproyek);
        sm= new SessionManager(Dashboard.this);
        image = findViewById(R.id.imageView3);
        mContext = this;
        proyek_adapter = new Proyek_adapter(this, proyekModels);
        mRecyclerView = findViewById(R.id.rv_proyek);
        Fabadd = findViewById(R.id.fab);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout = findViewById(R.id.swiperf);
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        stringid=(map.get(sm.KEY_ID));
        name.setText(map.get(sm.KEY_NAMA));
        sm.checkLogin();

        Fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Add_Proyek.class));
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sm.logout();
                sm.checkLogin();

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                getProyek();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onResume() {

        super.onResume();
        getProyek();

    }
    private void getProyek() {
        String apiKey = "oa00000000app";
        if (!TextUtils.isEmpty(stringid) && TextUtils.isDigitsOnly(stringid)) {
            PengembangID = Integer.parseInt(stringid);
        } else {
            PengembangID =0;
        }

        Call <ResponseModel> call = ApiClient.getRequestInterface().getProyek(PengembangID,apiKey,token);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                swipeRefreshLayout.setRefreshing(false);
//                String message = response.body().getMessage();
                if (response.code() == 200 ) {
                    final List<Proyek_model> proyekModels = response.body().getProyek();
                    Integer ids = response.body().getId();

                    if (proyekModels == null) {
                        datanull.setText("Data Proyek Masih Kosong");
                    }else{
                        datanull.setText(null);
                    }
                    mRecyclerView.setAdapter(new Proyek_adapter(mContext,proyekModels));
                    proyek_adapter.notifyDataSetChanged();
                    Log.d(TAG, "Tes" + proyek_adapter);

                } else if (response.code() == 422) {
                    Toast.makeText(Dashboard.this, "Something wrong!",
                            Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Dashboard.this, "Oops! Something went wrong!" + t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem item = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }


}