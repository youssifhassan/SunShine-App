package com.example.android.retrofitweatherapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Response;

public class List implements Serializable {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("temp")
    @Expose
    private Temp temp;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("weather")
    @Expose
    private ArrayList<Weather> weather = null;
    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private Integer deg;
    @SerializedName("clouds")
    @Expose
    private Integer clouds;
    @SerializedName("rain")
    @Expose
    private double rain;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(Integer rain) {
        this.rain = rain;
    }

    public List() {
    }

    public List(Integer dt, Temp temp, Double pressure, Integer humidity, ArrayList<Weather> weather, Double speed, Integer deg, Integer clouds, double rain) {
        this.dt = dt;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.weather = weather;
        this.speed = speed;
        this.deg = deg;
        this.clouds = clouds;
        this.rain = rain;
    }

    public ArrayList<List> parseList(Response<WeatherResponse> jsonResponse) {
        ArrayList<List> newList = new ArrayList<List>();

        for (int i = 0; i < jsonResponse.body().getList().size(); i++) {

            int dt = jsonResponse.body().getList().get(i).getDt();
            Temp temp = new Temp();
            temp = temp.parseTemp(jsonResponse, i);
            Double pressure = jsonResponse.body().getList().get(i).getPressure();
            int humidity = jsonResponse.body().getList().get(i).getHumidity();
            Weather weather = new Weather();
            ArrayList<Weather> weatherArrayList = weather.parseWeather(jsonResponse, i);
            Double speed = jsonResponse.body().getList().get(i).getSpeed();
            int deg = jsonResponse.body().getList().get(i).getDeg();
            int clouds = jsonResponse.body().getList().get(i).getClouds();
            Double rain = jsonResponse.body().getList().get(i).getRain();

            List list = new List(dt, temp, pressure, humidity, weatherArrayList, speed, deg, clouds, rain);
            newList.add(list);
        }

        return newList;
    }
}