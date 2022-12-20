package com.example.wheatherapitest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    public static WeatherApi create(String url) {

        Gson gson = new GsonBuilder().setLenient().create();
        //Log.e("AlarmTest", "api url is : " + url);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(WeatherApi.class);
    }

}
