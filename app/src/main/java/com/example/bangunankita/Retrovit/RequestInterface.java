package com.example.bangunankita.Retrovit;

import com.example.bangunankita.Model.Pengembang_model;
import com.example.bangunankita.Model.Proyek_model;
import com.example.bangunankita.Model.ResponseBatako;
import com.example.bangunankita.Model.ResponseModel;
import com.example.bangunankita.Model.ResponsePasir;
import com.example.bangunankita.Model.ResponseSemen;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.Path;

public interface RequestInterface {

        //User Regis and Login
        @POST("pengembang/register")
        Call<Void>actionRegisterMobile(@Body HashMap<String, String> map);

        @POST("pengembang/signin")
        Call<Pengembang_model>actionLogin(@Body HashMap<String, String> map);

        //GET DATA MATERIAL
        @GET("viewsemen")
        Call<ResponseSemen> getallsemen();
        @GET("viewbatako")
        Call<ResponseBatako> getallbatako();
        @GET("viewpasir")
        Call<ResponsePasir> getallpasir();


        //CRUD PROYEK

        @GET("proyek/{PengembangId}/")
        Call<ResponseModel> getProyek(@Path("PengembangId") int PengembangId,
                                            @Query("apiKey") String apiKey,
                                            @Query("accessToken")String accessToken);

        @POST("proyek/")
        Call<ResponseModel>actionCreateProyek(@Query("apiKey") String apiKey,
                                              @Query("accessToken")String accessToken,
                                              @Body HashMap<String, String> map);
        @PUT("proyek/{id}")
        Call<ResponseModel>actionPutProyek(@Path("id") int id,
                                              @Query("apiKey") String apiKey,
                                              @Query("accessToken")String accessToken,
                                              @Body HashMap<String, String> map);
        @PATCH("proyek/{id}")
        Call<ResponseModel>actionUpdateProyek(@Path("id") int id,
                                              @Query("apiKey") String apiKey,
                                              @Query("accessToken")String accessToken,
                                              @Body HashMap<String, String> map);

//        @DELETE("proyek/{id}/")


        //CRUD PERHITUNGAN BIDANG BANGUNAN
        @GET("perhitunganbidang/{ProyekId}/")
        Call<ResponseModel> getPerhitunganbidang(@Path("ProyekId") int ProyekId,
                                      @Query("apiKey") String apiKey,
                                      @Query("accessToken")String accessToken);

        @POST("perhitunganbidang/")
        Call<ResponseModel>actionCreatebidang(@Query("apiKey") String apiKey,
                                              @Query("accessToken")String accessToken,
                                              @Body HashMap<String, String> map);


        //CRUD PERHITUNGAN Keramik


    }
