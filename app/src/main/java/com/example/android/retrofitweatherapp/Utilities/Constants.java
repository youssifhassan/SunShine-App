package com.example.android.retrofitweatherapp.Utilities;

public class Constants {

    //API Parameter
    public static final String WEATHER_APP_ID = "7e217a64b9d7d121eb1741146f168e5b";

    //URL
    public static final String BASE_URL = "https://api.openweathermap.org/";
    public static final String PATH_OF_IMAGES = "http://openweathermap.org/img/w/";

    //SharedPreferenceKeys
    public static final String COUNTRY_Data_KEY = "CountryData";
    public static final String COUNTRY_ID_KEY = "CountryId";
    public static final int DEFAULT_COUNTRY_ID = 6252001;

    //ExtraDataNameFromMainActivityToWeatherDetailsActivity
    public static final String WEATHER_DETAILS_DATA = "weatherDetails";
    public static final String WEATHER_DETAILS_DATA_POSITION = "position";

    //IntArrayOfCountries
    public static final int[] COUNTRIES_IDS = {
            1269750,  //Republic of India
            798544,   //Republic of Poland
            6252001,  //United States
            357994,   //Arab Republic of Egypt
            2542007,  //Kingdom of Morocco
            2921044,  //Federal Republic of Germany
            292223,   //Dubai
            102358,   //Kingdom of Saudi Arabia
            5601538,  //Moscow
            4254884,  //Brazil
    };

    //StringArrayOfCountriesNames
    public static final String[] COUNTRIES_NAMES = {
            "Republic of India",
            "Republic of Poland",
            "United States",
            "Arab Republic of Egypt",
            "Kingdom of Morocco",
            "Federal Republic of Germany",
            "Dubai",
            "Kingdom of Saudi Arabia",
            "Moscow",
            "Brazil"
    };

    //StringArrayOfDays
    public static final String[] DAYS = {
            "Today",
            "Tomorrow",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday"
    };
}
