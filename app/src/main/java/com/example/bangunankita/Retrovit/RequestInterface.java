package com.example.bangunankita.Retrovit;

import com.example.bangunankita.Model.Proyek_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {
        @GET("proyek.json")
        Call<List<Proyek_model>> getJson();
    }
