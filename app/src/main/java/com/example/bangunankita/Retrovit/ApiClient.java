package com.example.bangunankita.Retrovit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit getRetrofit(){
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.43.163:3000/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();

        return retrofit;
}
    public static RequestInterface getRequestInterface(){
        RequestInterface requestInterface = getRetrofit().create(RequestInterface.class);
        return requestInterface;
    }

}
