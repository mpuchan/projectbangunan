package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangunankita.Model.Proyek_model;
import com.example.bangunankita.Model.ResponseModel;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Retrovit.RequestInterface;
import com.example.bangunankita.adapter.Proyek_adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
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
    SwipeRefreshLayout swipeRefreshLayout;
    private String TAG = "Dashboard";
    private String token;
    private String idpengembang;
    private int id;
    int PengembangID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        name = findViewById(R.id.namekita);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        mProgressBar=(ProgressBar)findViewById(R.id.progress_bar);
//        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView = findViewById(R.id.rv_proyek);

        Fabadd = findViewById(R.id.fab);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout = findViewById(R.id.swiperf);
        swipeRefreshLayout.setRefreshing(true);

//        proyekModels = new ArrayList<ResponseModel>(Collections.singleton(response.body()));

        name.setText("" + getIntent().getStringExtra("nama"));

       token = getIntent().getStringExtra("accessToken");
        getProyek();
//        idpengembang =getIntent().getStringExtra("id");
//        PengembangID =Integer.parseInt(idpengembang);


        Fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Add_Proyek.class));
            }
        });
    }


    private void getProyek() {
        String apiKey = "oa00000000app";
        Call <ResponseModel> call = ApiClient.getRequestInterface().getProyek(2,apiKey,token);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                swipeRefreshLayout.setRefreshing(false);
                String message = response.body().getMessage();
                if (response.code() == 200 && message != null) {

                    proyekModels = response.body().getProyek();
                    proyek_adapter = new Proyek_adapter(Dashboard.this,proyekModels);
                    mRecyclerView.setAdapter(proyek_adapter);
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