package com.example.wheatherapitest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SimpleDateFormat hourPattern, dayPattern, yearPattern_tv, dayPattern_tv, timePattern_tv;
    private String PTY, TMP;

    private String today, hour, year_tv, day_tv, time_tv;
    private TextView tv_year, tv_day, tv_time, tv_weather, tv_tmp;
    private ImageView iv_weather;
    private List<WeatherModel.Item> curItems = new ArrayList<>();

    /*
    * POP : 강수확률
    * PTY : 강수형태 ( 없음 - 0, 비 - 1, 비/눈 - 2, 눈 - 3, 소나기 - 4 )
    * REH : 습도
    * SKY : 하늘상태 ( 맑음 - 1, 구름많음 - 3, 흐림 - 4 )
    * TMP : 1시간 기온
    * TMN : 일 최저기온
    * TMX : 일 최고기온
    * SNO : 1시간 신적설
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        tv_time = findViewById(R.id.tv_time);
        tv_day = findViewById(R.id.tv_day);
        tv_year = findViewById(R.id.tv_year);
        tv_tmp = findViewById(R.id.tv_tmp);
        tv_weather = findViewById(R.id.tv_weather);
        iv_weather = findViewById(R.id.iv_weather);



        String baseUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/";
        String key = "ycB6cum8m9+wV0RkfzcAu4/Y9B4zTTbPDgnmoHFNTUtfKzEHJSyjm+n414xytjiU0nv5S/1wVLJfbz17PIAE0w==";

        yearPattern_tv = new SimpleDateFormat("yyyy");
        dayPattern_tv = new SimpleDateFormat("MM월dd일");
        timePattern_tv = new SimpleDateFormat("HH:mm:ss");
        dayPattern = new SimpleDateFormat("yyyyMMdd");
        hourPattern = new SimpleDateFormat("HH00");


        today = dayPattern.format(System.currentTimeMillis());
        hour = hourPattern.format(System.currentTimeMillis());

        year_tv = yearPattern_tv.format(System.currentTimeMillis());
        day_tv = dayPattern_tv.format(System.currentTimeMillis());
        time_tv = timePattern_tv.format(System.currentTimeMillis());
        Log.e("msgmsg", "today : " + today + " hour : " + hour);
        Log.e("msgmsg", "year_tv : " + year_tv + " day_tv : " + day_tv + " time_tv : " + time_tv);

        tv_year.setText(year_tv);
        tv_day.setText(day_tv);
        tv_time.setText(time_tv);





        WeatherApi weatherApi = RetrofitFactory.create(baseUrl);
//        weatherApi.getWeather(key, "1", "121", "JSON", today, "0800", "55", "77")
//                .enqueue(new Callback<ObjectResult>() {
//                    @Override
//                    public void onResponse(Call<ObjectResult> call, Response<ObjectResult> response) {
//                        Log.e("msgmsg", "code : " + response.code());
//                        Log.e("msgmsg", "code : " + response.body().toString());
//                        ObjectResult jsonObject = response.body();
//                        Log.e("msgmsg", "code : " + jsonObject.object.get("body").getAsJsonObject().get("items").getAsJsonObject().get("item"));
//                        JsonArray bodyList = jsonObject.object.get("body").getAsJsonObject().get("items").getAsJsonObject().get("item").getAsJsonArray();
//                        Log.e("msgmsg", "bodyList.size() : " + bodyList.size());
//                        for (int i = 0; i < bodyList.size(); i++) {
//                            Log.e("msgmsg", "fcstTime : " + bodyList.get(i));
//                            Log.e("msgmsg", "fcstTime : " + bodyList.get(i).getAsJsonObject().get("fcstTime").toString());
////                            if (hour.equals(bodyList.get(i).getAsJsonObject().get("fcstTime").toString())) {
////                                Log.e("msgmsg", "hour : " + hour );
////                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ObjectResult> call, Throwable t) {
//
//                    }
//                });

        weatherApi.getWeather(key, "1", "121", "JSON", today, "0800", "55", "77")
                .enqueue(new Callback<WeatherModel>() {
                    @Override
                    public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                        if(!response.isSuccessful()){ // 서버 요청 실패시
                            Log.e("msgmsg", "code : " + response.code());
                            return;
                        }
                        Log.e("msgmsg", "code : " + response.code());
                        Log.e("msgmsg", "code : " + response.body());

                        WeatherModel weatherModel = response.body();

                        // Log.e("msgmsg", "code : " + weatherModel.response.getBody().getItems().getItem());
                        List<WeatherModel.Item> items = weatherModel.response.getBody().getItems().getItem();


                        // Log.d("msgmsg", "itmes.size() : " + items.size());
                        for (int i = 0; i < items.size(); i++){
                            //Log.d("msgmsg", "i : " + i);
                            if (hour.equals(items.get(i).getFcstTime())){
                                Log.e("msgmsg", "i : " + items.get(i));
                                curItems.add(items.get(i));
                            }
                        }
                        Log.e("msgmsg", "curItems : " + curItems);
                        weatherUpdate();
                    }

                    @Override
                    public void onFailure(Call<WeatherModel> call, Throwable t) {
                        Log.e("msgmsg", "onFailure: " + t );
                    }
                });

    }

    public void weatherUpdate(){
        for (int i = 0; i < curItems.size(); i++){
            String category = curItems.get(i).getCategory();
            if (category.equals("TMP")){
                Log.e("msgmsg", "온도 : " + curItems.get(i).getFcstValue());
                tv_tmp.setText(curItems.get(i).getFcstValue() + "도");
            }

            if (curItems.get(i).getCategory().equals("PTY")){
                Log.e("msgmsg", "강수형태 : " + curItems.get(i).getFcstValue());
                String weather = curItems.get(i).getFcstValue();
                switch (weather){
                    case "0":
                        //Toast.makeText(MainActivity.this, "맑음", Toast.LENGTH_SHORT).show();
                        for (int j = 0; j < curItems.size(); j++) {
                            if (curItems.get(j).getCategory().equals("SKY")){
                                Log.e("msgmsg", "하늘상태 : " + curItems.get(j).getFcstValue());
                                String sky = curItems.get(j).getFcstValue();
                                switch (sky) {
                                    case "0":
                                        Toast.makeText(MainActivity.this, "맑음", Toast.LENGTH_SHORT).show();
                                        tv_weather.setText("맑음");
                                        iv_weather.setImageResource(R.drawable.wea_icon01);
                                        break;
                                    case "3":
                                        Toast.makeText(MainActivity.this, "구름많음", Toast.LENGTH_SHORT).show();
                                        tv_weather.setText("구름많음");
                                        iv_weather.setImageResource(R.drawable.wea_icon03);
                                        break;
                                    case "4":
                                        Toast.makeText(MainActivity.this, "흐림", Toast.LENGTH_SHORT).show();
                                        tv_weather.setText("흐림");
                                        iv_weather.setImageResource(R.drawable.wea_icon05);
                                        break;
                                }
                            }
                        }
                        break;
                    case "1":
                        Toast.makeText(MainActivity.this, "비", Toast.LENGTH_SHORT).show();
                        break;
                    case "2":
                        Toast.makeText(MainActivity.this, "비/눈", Toast.LENGTH_SHORT).show();
                        break;
                    case "3":
                        Toast.makeText(MainActivity.this, "눈", Toast.LENGTH_SHORT).show();
                        break;
                    case "4":
                        Toast.makeText(MainActivity.this, "소나기", Toast.LENGTH_SHORT).show();
                        break;
                }


            }
        }
    }
}