package com.example.sportotosuperlig.api_rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static ApiInterface getJSONClient(URL url) {

        Gson gson = new GsonBuilder().setLenient().create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url.getURL())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();


        return retrofit.create(ApiInterface.class);

    }
}
