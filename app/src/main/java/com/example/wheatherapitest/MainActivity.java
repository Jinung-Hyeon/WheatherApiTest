package com.example.wheatherapitest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String baseUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/";
        String key = "ycB6cum8m9+wV0RkfzcAu4/Y9B4zTTbPDgnmoHFNTUtfKzEHJSyjm+n414xytjiU0nv5S/1wVLJfbz17PIAE0w==";

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("\"HH00\"");



        Log.e("msgmsg", "date : " + simpleDateFormat.format(System.currentTimeMillis()));



        WeatherApi weatherApi = RetrofitFactory.create(baseUrl);
        weatherApi.getWeather(key, "1", "121", "JSON", "20221220", "0800", "55", "77")
                .enqueue(new Callback<ObjectResult>() {
                    @Override
                    public void onResponse(Call<ObjectResult> call, Response<ObjectResult> response) {
                        Log.e("msgmsg", "code : " + response.code());
                        Log.e("msgmsg", "code : " + response.body().toString());
                        ObjectResult jsonObject = response.body();
                        Log.e("msgmsg", "code : " + jsonObject.object.get("body").getAsJsonObject().get("items").getAsJsonObject().get("item"));
                        JsonArray bodyList = jsonObject.object.get("body").getAsJsonObject().get("items").getAsJsonObject().get("item").getAsJsonArray();
                        //Log.e("msgmsg", "item : " + bodyList);
                        for (int i = 0; i < bodyList.size(); i++) {
                            //Log.e("msgmsg", "item : " + bodyList.get(i).getAsJsonObject().get("fcstTime").toString());
                            if (simpleDateFormat.format(System.currentTimeMillis()).equals(bodyList.get(i).getAsJsonObject().get("fcstTime").toString())) {
                                Log.e("msgmsg", "onResponse: " + simpleDateFormat.format(System.currentTimeMillis()) );
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ObjectResult> call, Throwable t) {

                    }
                });

    }
}