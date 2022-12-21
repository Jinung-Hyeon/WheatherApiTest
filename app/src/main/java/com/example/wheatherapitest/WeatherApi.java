package com.example.wheatherapitest;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("getVilageFcst")
    Call<WeatherModel> getWeather(
            @Query("serviceKey") String serviceKey,
            @Query("pageNo") String pageNo,
            @Query("numOfRows") String numOfRows,
            @Query("dataType") String dataType,
            @Query("base_date") String base_date,
            @Query("base_time") String base_time,
            @Query("nx") String nx,
            @Query("ny") String ny);
}
