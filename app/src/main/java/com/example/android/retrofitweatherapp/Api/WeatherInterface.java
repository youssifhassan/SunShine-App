package com.example.android.retrofitweatherapp.Api;

import com.example.android.retrofitweatherapp.Model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherInterface {

    @GET("data/2.5/forecast/daily?")
    Call<WeatherResponse> getWeatherData(
            @Query("id") int countryId,
            @Query("APPID") String appId);
}
