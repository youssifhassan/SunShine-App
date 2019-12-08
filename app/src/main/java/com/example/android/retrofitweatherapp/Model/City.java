package com.example.android.retrofitweatherapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import retrofit2.Response;

public class City implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("population")
    @Expose
    private Integer population;
    @SerializedName("timezone")
    @Expose
    private Integer timezone;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public City() {
    }

    public City(Integer id, String name, Coord coord, String country, Integer population, Integer timezone) {
        this.id = id;
        this.name = name;
        this.coord = coord;
        this.country = country;
        this.population = population;
        this.timezone = timezone;
    }

    public City parseCity(Response<WeatherResponse> jsonResponse) {

        City newCity;

        int cityId = jsonResponse.body().getCity().getId();
        String cityName = jsonResponse.body().getCity().getName();
        Coord coord = new Coord();
        coord.parseCord(jsonResponse);
        String countryShortcut = jsonResponse.body().getCity().getCountry();
        int population = jsonResponse.body().getCity().getPopulation();
        int timezone = jsonResponse.body().getCity().getTimezone();

        newCity = new City(cityId, cityName, coord, countryShortcut, population, timezone);
        return newCity;
    }

}
