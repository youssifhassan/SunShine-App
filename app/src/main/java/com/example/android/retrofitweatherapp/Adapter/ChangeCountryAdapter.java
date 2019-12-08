package com.example.android.retrofitweatherapp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.retrofitweatherapp.R;

public class ChangeCountryAdapter extends RecyclerView.Adapter<ChangeCountryAdapter.ViewHolder> {

    private String[] countries_names;

    public ChangeCountryAdapter(String[] countries_names) {
        this.countries_names = countries_names;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.countryName.setText(countries_names[position]);
    }

    @Override
    public int getItemCount() {
        return countries_names.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView countryName;

        public ViewHolder(View view) {
            super(view);

            countryName = view.findViewById(R.id.countryName);
        }
    }
}
