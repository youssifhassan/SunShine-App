package com.example.android.retrofitweatherapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import retrofit2.Response;

public class Temp implements Serializable {

    @SerializedName("day")
    @Expose
    private Double day;
    @SerializedName("min")
    @Expose
    private Double min;
    @SerializedName("max")
    @Expose
    private Double max;
    @SerializedName("night")
    @Expose
    private Double night;
    @SerializedName("eve")
    @Expose
    private Double eve;
    @SerializedName("morn")
    @Expose
    private Double morn;

    private int maxCelsuis;

    private int minCelsuis;

    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getNight() {
        return night;
    }

    public void setNight(Double night) {
        this.night = night;
    }

    public Double getEve() {
        return eve;
    }

    public void setEve(Double eve) {
        this.eve = eve;
    }

    public Double getMorn() {
        return morn;
    }

    public void setMorn(Double morn) {
        this.morn = morn;
    }

    public int getMaxCelsuis() {
        return maxCelsuis;
    }

    public void setMaxCelsuis(int maxCelsuis) {
        this.maxCelsuis = maxCelsuis;
    }

    public int getMinCelsuis() {
        return minCelsuis;
    }

    public void setMinCelsuis(int minCelsuis) {
        this.minCelsuis = minCelsuis;
    }

    public Temp() {
    }

    public Temp(Double day, int min, int max, Double night, Double eve, Double morn) {
        this.day = day;
        this.minCelsuis = min;
        this.maxCelsuis = max;
        this.night = night;
        this.eve = eve;
        this.morn = morn;
    }

    public Temp parseTemp(Response<WeatherResponse> jsonResponse, int position) {
        Temp temp;

        Double day = jsonResponse.body().getList().get(position).getTemp().getDay();
        Double min = jsonResponse.body().getList().get(position).getTemp().getMin();
        int minCelsuis = ConvertKelvinToCelsius(min);
        Double max = jsonResponse.body().getList().get(position).getTemp().getMax();
        int maxCelsuis = ConvertKelvinToCelsius(max);
        Double night = jsonResponse.body().getList().get(position).getTemp().getNight();
        Double eve = jsonResponse.body().getList().get(position).getTemp().getEve();
        Double morn = jsonResponse.body().getList().get(position).getTemp().getMorn();

        temp = new Temp(day, minCelsuis, maxCelsuis, night, eve, morn);
        return temp;
    }

    public int ConvertKelvinToCelsius(double temp) {
        int TempInCelsius = ((int) temp - 273);
        return TempInCelsius;
    }
}
