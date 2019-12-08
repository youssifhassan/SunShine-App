package com.example.android.retrofitweatherapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.retrofitweatherapp.Adapter.ClickListenerAdapter;
import com.example.android.retrofitweatherapp.Adapter.MainScreenAdapter;
import com.example.android.retrofitweatherapp.Api.OnItemClickListener;
import com.example.android.retrofitweatherapp.Api.WeatherInterface;
import com.example.android.retrofitweatherapp.Model.WeatherResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.android.retrofitweatherapp.Utilities.Constants.BASE_URL;
import static com.example.android.retrofitweatherapp.Utilities.Constants.COUNTRY_Data_KEY;
import static com.example.android.retrofitweatherapp.Utilities.Constants.COUNTRY_ID_KEY;
import static com.example.android.retrofitweatherapp.Utilities.Constants.DEFAULT_COUNTRY_ID;
import static com.example.android.retrofitweatherapp.Utilities.Constants.WEATHER_APP_ID;
import static com.example.android.retrofitweatherapp.Utilities.Constants.WEATHER_DETAILS_DATA;
import static com.example.android.retrofitweatherapp.Utilities.Constants.WEATHER_DETAILS_DATA_POSITION;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareMainView();
    }

    private void prepareMainView() {
        setupToolbar();
        setupRecyclerView();
        progressBar = findViewById(R.id.progressBar);
        recyclerView.setVisibility(View.GONE);
        showProgressBar();
        loadWeatherJsonData();
    }

    private void setupToolbar() {
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setLogo(R.drawable.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);

    }

    private void loadWeatherJsonData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherInterface weatherInterface = retrofit.create(WeatherInterface.class);
        int countryId = readCountryId();
        Call<WeatherResponse> weatherCall = weatherInterface.getWeatherData(countryId, WEATHER_APP_ID);

        weatherCall.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                WeatherResponse weatherResponse = new WeatherResponse();
                ArrayList<WeatherResponse> weatherResponseArrayList;
                weatherResponseArrayList = weatherResponse.ParseWeatherResponse(response);
                setAdapterOfRecyclerView(weatherResponseArrayList);

                recyclerView.setVisibility(View.VISIBLE);
                hideProgressBar();
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    private int readCountryId() {
        SharedPreferences sharedPreferences = getSharedPreferences(COUNTRY_Data_KEY, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(COUNTRY_ID_KEY, DEFAULT_COUNTRY_ID);
    }

    private void setAdapterOfRecyclerView(final ArrayList<WeatherResponse> weatherResponseArrayList) {

        recyclerView.setAdapter(new MainScreenAdapter(weatherResponseArrayList, MainActivity.this));
        recyclerView.addOnItemTouchListener(
                new ClickListenerAdapter(this, new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        startWeatherDetailsIntent(weatherResponseArrayList, position);
                    }
                })
        );
    }

    private void startWeatherDetailsIntent(ArrayList<WeatherResponse> weatherResponseArrayList, int position){
        Intent weatherDetailsIntent = new Intent(MainActivity.this, WeatherDetails.class);
        weatherDetailsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        weatherDetailsIntent.putExtra(WEATHER_DETAILS_DATA, weatherResponseArrayList.get(position));
        weatherDetailsIntent.putExtra(WEATHER_DETAILS_DATA_POSITION, position);
        startActivity(weatherDetailsIntent);
    }

    private void hideProgressBar() {
        if (progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.change_country) {
            Intent intent = new Intent(MainActivity.this, ChangeCountryActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}