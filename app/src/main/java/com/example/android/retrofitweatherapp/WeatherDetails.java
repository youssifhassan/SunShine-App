package com.example.android.retrofitweatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.retrofitweatherapp.Model.WeatherResponse;
import com.squareup.picasso.Picasso;

import static com.example.android.retrofitweatherapp.Utilities.Constants.DAYS;
import static com.example.android.retrofitweatherapp.Utilities.Constants.PATH_OF_IMAGES;
import static com.example.android.retrofitweatherapp.Utilities.Constants.WEATHER_DETAILS_DATA;
import static com.example.android.retrofitweatherapp.Utilities.Constants.WEATHER_DETAILS_DATA_POSITION;

public class WeatherDetails extends AppCompatActivity {

    private TextView day,weather,maxTemp,minTemp,pressure,humidity,clouds;
    private ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);

        prepareMainView();
        setReceivedDataFromMainActivity();
    }

    private void prepareMainView() {
        setupToolbar();
        day = findViewById(R.id.date);
        weather = findViewById(R.id.weather);
        maxTemp = findViewById(R.id.highTemp);
        minTemp = findViewById(R.id.lowTemp);
        icon = findViewById(R.id.icon);
        pressure = findViewById(R.id.pressure);
        humidity = findViewById(R.id.humidity);
        clouds = findViewById(R.id.clouds);
    }

    private void setReceivedDataFromMainActivity() {
        WeatherResponse weatherResponse = (WeatherResponse) getIntent().getSerializableExtra(WEATHER_DETAILS_DATA);
        int position = (int) getIntent().getSerializableExtra(WEATHER_DETAILS_DATA_POSITION);

        setData(weatherResponse,position);
    }

    private void setupToolbar() {
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setNavigationIcon(R.drawable.ic_arrow_back);

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setData(WeatherResponse response, int position) {
        day.setText(DAYS[position]);
        weather.setText(response.getList().get(position).getWeather().get(0).getMain());
        maxTemp.setText(response.getList().get(position).getTemp().getMaxCelsuis() + "°");
        minTemp.setText(response.getList().get(position).getTemp().getMinCelsuis()+ "°");
        Picasso.with(this).load(PATH_OF_IMAGES + response.getList().get(0).getWeather().get(0).getIcon() +".png").into(icon);
        humidity.setText(response.getList().get(position).getHumidity()+ "%");
        pressure.setText(response.getList().get(position).getPressure()+ " HPS");
        clouds.setText(response.getList().get(position).getClouds()+ " KM/H NW");
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
