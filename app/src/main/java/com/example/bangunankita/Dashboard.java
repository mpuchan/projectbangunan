package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangunankita.Model.Proyek_model;
import com.example.bangunankita.Retrovit.RequestInterface;
import com.example.bangunankita.adapter.Proyek_adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dashboard extends AppCompatActivity {

    private Proyek_adapter proyek_adapter;
    private ArrayList<Proyek_model> proyekModels = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private FloatingActionButton Fabadd;
    //    private ProgressBar  mProgressBar;
    private TextView name;
    SwipeRefreshLayout swipeRefreshLayout;

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
        name.setText("" + getIntent().getStringExtra("nama"));
        parseJson();

        Fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Add_Proyek.class));
            }
        });
    }


    private void parseJson() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://proyekbangunan.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<List<Proyek_model>> call1 = request.getJson();
        call1.enqueue(new Callback<List<Proyek_model>>() {
            @Override
            public void onResponse(Call<List<Proyek_model>> call,
                                   Response<List<Proyek_model>> response) {
                swipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    proyekModels = new ArrayList<>(response.body());
                    proyek_adapter = new Proyek_adapter(proyekModels, Dashboard.this);
                    mRecyclerView.setAdapter(proyek_adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Proyek_model>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Dashboard.this, "Oops! Something went wrong!",
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