package com.example.bangunankita.Retrovit;

import com.example.bangunankita.Model.Pengembang_model;
import com.example.bangunankita.Model.ResponseAcian;
import com.example.bangunankita.Model.ResponseBeton;
import com.example.bangunankita.Model.ResponseBidang;
import com.example.bangunankita.Model.ResponseLantai;
import com.example.bangunankita.Model.ResponseMaterial;
import com.example.bangunankita.Model.ResponseModel;
import com.example.bangunankita.Model.ResponsePengecatan;
import com.example.bangunankita.Model.ResponsePlafon;
import com.example.bangunankita.Model.ResponsePlesteran;
import com.example.bangunankita.Model.ResponsePondasi;
import com.example.bangunankita.Model.ResponseUrugan;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface RequestInterface {

        //User Regis and Login
        @POST("pengembang/register")
        Call<Void>actionRegisterMobile(@Body HashMap<String, String> map);

        @POST("pengembang/signin")
        Call<Pengembang_model>actionLogin(@Body HashMap<String, String> map);

        @PUT("forgotpass/{email}")
        Call<Void>actionresetPass(@Path("email") String email);

        @PUT("pengembang/updateprofile/{id}")
        Call<Void>actionUpdateprofile(@Path("id") int id,
                                      @Query("apiKey") String apiKey,
                                      @Query("accessToken")String accessToken,
                                      @Body HashMap<String, String> map);


        //GET DATA MATERIAL
        @GET("viewsemen")
        Call<ResponseMaterial> getallsemen();
        @GET("viewbatako")
        Call<ResponseMaterial> getallbatako();
        @GET("viewpasir")
        Call<ResponseMaterial> getallpasir();
        @GET("viewsemennat")
        Call<ResponseMaterial> getallsemennat();
        @GET("viewkeramik")
        Call<ResponseMaterial> getallkeramik();
        @GET("viewgenteng")
        Call<ResponseMaterial> getallgenteng();
        @GET("viewtriplek")
        Call<ResponseMaterial> getalltriplek();
        @GET("viewbatu")
        Call<ResponseMaterial> getallbatu();
        @GET("viewkayu")
        Call<ResponseMaterial> getallkayu();
        @GET("viewcat")
        Call<ResponseMaterial> getallcat();
        @GET("viewplamur")
        Call<ResponseMaterial> getallplamur();
        @GET("viewbesi")
        Call<ResponseMaterial> getallbesi();
        @GET("viewlispapan")
        Call<ResponseMaterial> getalllispapan();
        @GET("viewpaku")
        Call<ResponseMaterial> getallpaku();
        @GET("viewpengikat")
        Call<ResponseMaterial> getallpengikat();
        //CRUD PROYEK

        @GET("proyek/{PengembangId}/")
        Call<ResponseModel> getProyek(@Path("PengembangId") int PengembangId,
                                            @Query("apiKey") String apiKey,
                                            @Query("accessToken")String accessToken);

        @POST("proyek/")
        Call<Void>actionCreateProyek(@Query("apiKey") String apiKey,
                                              @Query("accessToken")String accessToken,
                                              @Body HashMap<String, String> map);
        @PUT("proyek/{id}")
        Call<Void>actionPutProyek(@Path("id") int id,
                                              @Query("apiKey") String apiKey,
                                              @Query("accessToken")String accessToken,
                                              @Body HashMap<String, String> map);

        @PATCH("proyek/{id}")
        Call<ResponseModel>actionUpdateProyek(@Path("id") int id,
                                              @Query("apiKey") String apiKey,
                                              @Query("accessToken")String accessToken,
                                              @Body HashMap<String, String> map);

        @DELETE("proyek/{id}/")
        Call<Void>actionDeleteProyek(@Path("id") int id,
                                              @Query("apiKey") String apiKey,
                                              @Query("accessToken")String accessToken);


        //CRUD PERHITUNGAN BIDANG BANGUNAN
        @GET("perhitunganbidang/{ProyekId}/")
        Call<ResponseBidang> getPerhitunganbidang(@Path("ProyekId") int ProyekId,
                                                  @Query("apiKey") String apiKey,
                                                  @Query("accessToken")String accessToken);
        @GET("perhitunganbidang/export/{ProyekId}/")
        @Streaming
        Call<ResponseBody> getexportbidang(@Path("ProyekId") int ProyekId,
                                           @Query("apiKey") String apiKey,
                                           @Query("accessToken")String accessToken);


        @POST("perhitunganbidang/")
        Call<Void> actionCreatebidang(@Query("apiKey") String apiKey,
                                                @Query("accessToken")String accessToken,
                                                @Body HashMap<String, String> map);
        @PUT("perhitunganbidang/{id}")
        Call<Void>actionPutPerhitunganbidang(@Path("id") int id,
                                  @Query("apiKey") String apiKey,
                                  @Query("accessToken")String accessToken,
                                  @Body HashMap<String, String> map);
        @DELETE("perhitunganbidang/{id}/")
        Call<Void>actionDeletePerhitunganbidang(@Path("id") int id,
                                              @Query("apiKey") String apiKey,
                                              @Query("accessToken")String accessToken);

        //CRUD PERHITUNGAN PLESTERAN BANGUNAN
        @GET("perhitunganplesteran/{ProyekId}/")
        Call<ResponsePlesteran> getPerhitunganplesteran(@Path("ProyekId") int ProyekId,
                                                     @Query("apiKey") String apiKey,
                                                     @Query("accessToken")String accessToken);

        @POST("perhitunganplesteran/")
        Call<Void> actionCreateplesteran(@Query("apiKey") String apiKey,
                                      @Query("accessToken")String accessToken,
                                      @Body HashMap<String, String> map);
        @PUT("perhitunganplesteran/{id}")
        Call<Void>actionPutPerhitunganplesteran(@Path("id") int id,
                                             @Query("apiKey") String apiKey,
                                             @Query("accessToken")String accessToken,
                                             @Body HashMap<String, String> map);
        @DELETE("perhitunganplesteran/{id}/")
        Call<Void>actionDeletePerhitunganplesteran(@Path("id") int id,
                                                @Query("apiKey") String apiKey,
                                                @Query("accessToken")String accessToken);

        //CRUD PERHITUNGAN URUGAN BANGUNAN
        @GET("perhitunganurugan/{ProyekId}/")
        Call<ResponseUrugan> getPerhitunganurugan(@Path("ProyekId") int ProyekId,
                                                  @Query("apiKey") String apiKey,
                                                  @Query("accessToken")String accessToken);

        @POST("perhitunganurugan/")
        Call<Void> actionCreateurugan(@Query("apiKey") String apiKey,
                                         @Query("accessToken")String accessToken,
                                         @Body HashMap<String, String> map);
        @PUT("perhitunganurugan/{id}")
        Call<Void>actionPutPerhitunganurugan(@Path("id") int id,
                                            @Query("apiKey") String apiKey,
                                            @Query("accessToken")String accessToken,
                                            @Body HashMap<String, String> map);
        @DELETE("perhitunganurugan/{id}/")
        Call<Void>actionDeletePerhitunganurugan(@Path("id") int id,
                                               @Query("apiKey") String apiKey,
                                               @Query("accessToken")String accessToken);


        //CRUD PERHITUNGAN ACIAN BANGUNAN
        @GET("perhitunganacian/{ProyekId}/")
        Call<ResponseAcian> getPerhitunganacian(@Path("ProyekId") int ProyekId,
                                                    @Query("apiKey") String apiKey,
                                                    @Query("accessToken")String accessToken);

        @POST("perhitunganacian/")
        Call<Void> actionCreateacian(@Query("apiKey") String apiKey,
                                         @Query("accessToken")String accessToken,
                                         @Body HashMap<String, String> map);

        @PUT("perhitunganacian/{id}")
        Call<Void>actionPutPerhitunganacian(@Path("id") int id,
                                             @Query("apiKey") String apiKey,
                                             @Query("accessToken")String accessToken,
                                             @Body HashMap<String, String> map);
        @DELETE("perhitunganacian/{id}/")
        Call<Void>actionDeletePerhitunganacian(@Path("id") int id,
                                                @Query("apiKey") String apiKey,
                                                @Query("accessToken")String accessToken);


        //CRUD PERHITUNGAN Lantai
        @GET("perhitunganlantai/{ProyekId}/")
        Call<ResponseLantai> getPerhitunganlantai(@Path("ProyekId") int ProyekId,
                                                  @Query("apiKey") String apiKey,
                                                  @Query("accessToken")String accessToken);
        @POST("perhitunganlantai/")
        Call<Void>actionCreatelantai(@Query("apiKey") String apiKey,
                                              @Query("accessToken")String accessToken,
                                              @Body HashMap<String, String> map);
        @PUT("perhitunganlantai/{id}")
        Call<Void>actionPutPerhitunganlantai(@Path("id") int id,
                                            @Query("apiKey") String apiKey,
                                            @Query("accessToken")String accessToken,
                                            @Body HashMap<String, String> map);
        @DELETE("perhitunganlantai/{id}/")
        Call<Void>actionDeletePerhitunganlantai(@Path("id") int id,
                                               @Query("apiKey") String apiKey,
                                               @Query("accessToken")String accessToken);

        //CRUD PERHITUNGAN Pengecatan
        @GET("perhitunganpengecatan/{ProyekId}/")
        Call<ResponsePengecatan> getPerhitunganpengecatan(@Path("ProyekId") int ProyekId,
                                                          @Query("apiKey") String apiKey,
                                                          @Query("accessToken")String accessToken);
        @POST("perhitunganpengecatan/")
        Call<Void>actionCreatepengecatan(@Query("apiKey") String apiKey,
                                              @Query("accessToken")String accessToken,
                                              @Body HashMap<String, String> map);

        @PUT("perhitunganpengecatan/{id}")
        Call<Void>actionPutPerhitunganpengecatan(@Path("id") int id,
                                             @Query("apiKey") String apiKey,
                                             @Query("accessToken")String accessToken,
                                             @Body HashMap<String, String> map);

        @DELETE("perhitunganpengecatan/{id}/")
        Call<Void>actionDeletePerhitunganpengecatan(@Path("id") int id,
                                               @Query("apiKey") String apiKey,
                                               @Query("accessToken")String accessToken);

        //CRUD PERHITUNGAN Pondasi
        @GET("perhitunganpondasi/{ProyekId}/")
        Call<ResponsePondasi> getPerhitunganpondasi(@Path("ProyekId") int ProyekId,
                                                       @Query("apiKey") String apiKey,
                                                       @Query("accessToken")String accessToken);
        @POST("perhitunganpondasi/")
        Call<Void>actionCreatepondasi(@Query("apiKey") String apiKey,
                                         @Query("accessToken")String accessToken,
                                         @Body HashMap<String, String> map);

        @PUT("perhitunganpondasi/{id}")
        Call<Void>actionPutPerhitunganpondasi(@Path("id") int id,
                                                 @Query("apiKey") String apiKey,
                                                 @Query("accessToken")String accessToken,
                                                 @Body HashMap<String, String> map);

        @DELETE("perhitunganpondasi/{id}/")
        Call<Void>actionDeletePerhitunganpondasi(@Path("id") int id,
                                                    @Query("apiKey") String apiKey,
                                                    @Query("accessToken")String accessToken);
        //CRUD PERHITUNGAN Beton
        @GET("perhitunganbeton/{ProyekId}/")
        Call<ResponseBeton> getPerhitunganbeton(@Path("ProyekId") int ProyekId,
                                                @Query("apiKey") String apiKey,
                                                @Query("accessToken")String accessToken);
        @POST("perhitunganbeton/")
        Call<Void>actionCreatebeton(@Query("apiKey") String apiKey,
                                      @Query("accessToken")String accessToken,
                                      @Body HashMap<String, String> map);

        @PUT("perhitunganbeton/{id}")
        Call<Void>actionPutPerhitunganbeton(@Path("id") int id,
                                              @Query("apiKey") String apiKey,
                                              @Query("accessToken")String accessToken,
                                              @Body HashMap<String, String> map);

        @DELETE("perhitunganbeton/{id}/")
        Call<Void>actionDeletePerhitunganbeton(@Path("id") int id,
                                                 @Query("apiKey") String apiKey,
                                                 @Query("accessToken")String accessToken);


        //CRUD PERHITUNGAN Plafon
        @GET("perhitunganplafon/{ProyekId}/")
        Call<ResponsePlafon> getPerhitunganplafon(@Path("ProyekId") int ProyekId,
                                                  @Query("apiKey") String apiKey,
                                                  @Query("accessToken")String accessToken);
        @POST("perhitunganplafon/")
        Call<Void>actionCreateplafon(@Query("apiKey") String apiKey,
                                    @Query("accessToken")String accessToken,
                                    @Body HashMap<String, String> map);

        @PUT("perhitunganplafon//{id}")
        Call<Void>actionPutPerhitunganplafon(@Path("id") int id,
                                            @Query("apiKey") String apiKey,
                                            @Query("accessToken")String accessToken,
                                            @Body HashMap<String, String> map);

        @DELETE("perhitunganplafon/{id}/")
        Call<Void>actionDeletePerhitunganplafon(@Path("id") int id,
                                               @Query("apiKey") String apiKey,
                                               @Query("accessToken")String accessToken);

}
