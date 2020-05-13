package com.example.bangunankita.Retrovit;

import com.example.bangunankita.Model.Pengembang_model;
import com.example.bangunankita.Model.Proyek_model;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RequestInterface {
        @POST("pengembang/signin")
        Call<Pengembang_model>actionLogin(@Body HashMap<String, String> map);

        @POST("pengembang/register")
        Call<Void>actionRegisterMobile(@Body HashMap<String, String> map);

        @GET("/api/pengembang/{id}")
        Call<Pengembang_model> getUser(@Path("id") Long id);

        @GET("proyek.json")
        Call<List<Proyek_model>> getJson();
    }
