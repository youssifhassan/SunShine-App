package com.example.android.retrofitweatherapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Response;

public class Weather implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icon")
    @Expose
    private String icon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Weather() {
    }

    public Weather(Integer id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public ArrayList<Weather> parseWeather(Response<WeatherResponse> jsonResponse, int position) {
        ArrayList<Weather> weatherArrayList = new ArrayList<>();

        for (int i = 0; i < jsonResponse.body().getList().get(position).getWeather().size(); i++) {
            Weather weather;

            int id = jsonResponse.body().getList().get(position).getWeather().get(i).getId();
            String main = jsonResponse.body().getList().get(position).getWeather().get(i).getMain();
            String description = jsonResponse.body().getList().get(position).getWeather().get(i).getDescription();
            String icon = jsonResponse.body().getList().get(position).getWeather().get(i).getIcon();

            weather = new Weather(id, main, description, icon);
            weatherArrayList.add(weather);
        }
        return weatherArrayList;
    }
}