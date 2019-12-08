package com.example.android.retrofitweatherapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import retrofit2.Response;

public class Coord implements Serializable {

    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("lat")
    @Expose
    private Double lat;

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Coord parseCord(Response<WeatherResponse> jsonResponse) {
        Coord coord = new Coord();

        Double lon = jsonResponse.body().getCity().getCoord().getLon();
        Double lat = jsonResponse.body().getCity().getCoord().getLat();

        coord.setLat(lat);
        coord.setLon(lon);
        return coord;
    }
}
