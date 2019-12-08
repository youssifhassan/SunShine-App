package com.example.android.retrofitweatherapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.android.retrofitweatherapp.Adapter.ChangeCountryAdapter;
import com.example.android.retrofitweatherapp.Adapter.ClickListenerAdapter;
import com.example.android.retrofitweatherapp.Api.OnItemClickListener;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static com.example.android.retrofitweatherapp.Utilities.Constants.COUNTRIES_IDS;
import static com.example.android.retrofitweatherapp.Utilities.Constants.COUNTRIES_NAMES;
import static com.example.android.retrofitweatherapp.Utilities.Constants.COUNTRY_Data_KEY;
import static com.example.android.retrofitweatherapp.Utilities.Constants.COUNTRY_ID_KEY;

public class ChangeCountryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_country);

        prepareView();
    }

    private void prepareView() {
        setupToolbar();
        setupRecyclerView();
        getCountriesIdsAndNames();
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

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getCountriesIdsAndNames() {
        setAdapterOfRecyclerView(COUNTRIES_IDS, COUNTRIES_NAMES);
    }

    private void setAdapterOfRecyclerView(final int[] countries_Ids, String[] countries_Names) {
        recyclerView.setAdapter(new ChangeCountryAdapter(countries_Names));

        recyclerView.addOnItemTouchListener(
                new ClickListenerAdapter(this, new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        saveCountryId(countries_Ids[position]);
                        backToMainActivityWithNewSelectedCountry();
                    }
                })
        );

    }

    private void backToMainActivityWithNewSelectedCountry(){
        Intent intent = new Intent(ChangeCountryActivity.this, MainActivity.class);
        intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void saveCountryId(int countryId) {
        SharedPreferences sharedPreferences = getSharedPreferences(COUNTRY_Data_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(COUNTRY_ID_KEY, countryId);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}