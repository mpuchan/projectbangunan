package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bangunankita.Model.Proyek_model;
import com.example.bangunankita.Retrovit.RequestInterface;
import com.example.bangunankita.adapter.Proyek_adapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dashboard extends AppCompatActivity {

    private Proyek_adapter proyek_adapter;
    private ArrayList<Proyek_model> proyekModels=new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ProgressBar  mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mProgressBar=(ProgressBar)findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView= findViewById(R.id.rv_proyek);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        parseJson();
    }

    private void parseJson() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://proyekbangunan.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<List<Proyek_model>> call1=request.getJson();
        call1.enqueue(new Callback<List<Proyek_model>>() {
            @Override
            public void onResponse(Call<List<Proyek_model>> call, Response<List<Proyek_model>> response) {
                mProgressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body()!=null) {
                    proyekModels = new ArrayList<>(response.body());
                    proyek_adapter=new Proyek_adapter(proyekModels,Dashboard.this);
                    mRecyclerView.setAdapter(proyek_adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Proyek_model>> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(Dashboard.this,"Oops! Something went wrong!",Toast.LENGTH_SHORT).show();
            }

        });
    }

}