package com.danc.rxjavasample.network;

import com.danc.rxjavasample.model.Players.DataX;
import com.danc.rxjavasample.model.Players.PlayersModel;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class RetrofitClient {

     private static RetrofitClient instance;
     public RetrofitService retrofitService;

     public String PLAYERS_BASE_URL = "https://www.balldontlie.io/";

     private RetrofitClient(){
         final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

         HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
         OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(loggingInterceptor).build();
         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl(PLAYERS_BASE_URL)
                 .client(client)
                 .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                 .addConverterFactory(GsonConverterFactory.create(gson))
                 .build();

         retrofitService = retrofit.create(RetrofitService.class);

     }

     public static final RetrofitClient getInstance(){
         if (instance == null){
             instance = new RetrofitClient();
         }

         return instance;
     }

     public Observable<PlayersModel> getAllPlayers(){
         return retrofitService.getAllPlayers();
     }
}
