package com.example.android.retrofitweatherapp.Model;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Response;

import static com.example.android.retrofitweatherapp.Utilities.Constants.DAYS;

public class WeatherResponse implements Serializable {

    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Double message;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private ArrayList<List> list = null;

    private int typeItem;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public ArrayList<List> getList() {
        return list;
    }

    public void setList(ArrayList<List> list) {
        this.list = list;
    }

    public void setTypeItem(int typeItem) { this.typeItem = typeItem; }

    public int getTypeItem() { return typeItem; }

    public WeatherResponse() {
    }

    public WeatherResponse(City city, String cod, Double message, Integer cnt, ArrayList<List> list, int typeItem) {
        this.city = city;
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
        this.typeItem = typeItem;
    }

    public ArrayList<WeatherResponse> ParseWeatherResponse(Response<WeatherResponse> jsonResponse) {
        ArrayList<WeatherResponse> weatherResponseArrayList = new ArrayList<>();

        for (int i = 0; i < jsonResponse.body().getList().size(); i++) {

            City newCity = new City();
            newCity = newCity.parseCity(jsonResponse);
            String cod = jsonResponse.body().getCod();
            Double message = jsonResponse.body().getMessage();
            int cnt = jsonResponse.body().getCnt();
            List newList = new List();
            ArrayList<List> listArrayList = newList.parseList(jsonResponse);
            int itemType = configureDays(DAYS, i);
            WeatherResponse weatherResponse = new WeatherResponse(newCity, cod, message, cnt, listArrayList, itemType);
            weatherResponseArrayList.add(weatherResponse);
        }

        return weatherResponseArrayList;
    }

    private int configureDays(String[] days, int position) {
        if (days[position].equals("Today")) {
            return 0;
        } else {
            return 1;
        }
    }
}
