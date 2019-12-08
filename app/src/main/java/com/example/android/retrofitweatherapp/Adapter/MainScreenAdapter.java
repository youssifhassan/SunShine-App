package com.example.android.retrofitweatherapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.retrofitweatherapp.Model.WeatherResponse;
import com.example.android.retrofitweatherapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.android.retrofitweatherapp.Utilities.Constants.DAYS;
import static com.example.android.retrofitweatherapp.Utilities.Constants.PATH_OF_IMAGES;

public class MainScreenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<WeatherResponse> weatherResponseArrayList;
    private Context context;
    private static int TYPE_FIRST = 0;

    public MainScreenAdapter(ArrayList<WeatherResponse> weatherResponseArrayList, Context context) {
        this.weatherResponseArrayList = weatherResponseArrayList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (weatherResponseArrayList.get(position).getTypeItem() == 0) {
            return TYPE_FIRST;
        } else {
            int TYPE_LIST = 1;
            return TYPE_LIST;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_FIRST) {
            view = LayoutInflater.from(context).inflate(R.layout.first_item_list, parent, false);
            return new FirstItemView(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
            return new ListItemView(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_FIRST) {
            ((FirstItemView) holder).setFirstItemDetails(weatherResponseArrayList.get(position), position);
        } else {
            ((ListItemView) holder).setListItemDetails(weatherResponseArrayList.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return weatherResponseArrayList.size();
    }


    private class FirstItemView extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView day, weather, maxTemp, minTemp, countryName;

        public FirstItemView(View view) {
            super(view);
            icon = itemView.findViewById(R.id.icon);
            day = itemView.findViewById(R.id.date);
            countryName = itemView.findViewById(R.id.countryName);
            weather = itemView.findViewById(R.id.weather);
            maxTemp = itemView.findViewById(R.id.highTemp);
            minTemp = itemView.findViewById(R.id.lowTemp);
        }

        public void setFirstItemDetails(WeatherResponse response, int position) {
            Picasso.with(context).load(PATH_OF_IMAGES + response.getList().get(position).getWeather().get(0).getIcon() + ".png").into(icon);
            day.setText(DAYS[position] + ",");
            countryName.setText(response.getCity().getName());
            weather.setText(response.getList().get(position).getWeather().get(0).getMain());
            maxTemp.setText(String.valueOf((int) response.getList().get(position).getTemp().getMaxCelsuis()) + "°");
            minTemp.setText(String.valueOf((int) response.getList().get(position).getTemp().getMinCelsuis()) + "°");
        }
    }

    private class ListItemView extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView day, weather, highTemp, lowTemp;

        public ListItemView(View view) {
            super(view);

            icon = itemView.findViewById(R.id.weatherIcon);
            day = itemView.findViewById(R.id.date);
            weather = itemView.findViewById(R.id.theWeather);
            highTemp = itemView.findViewById(R.id.highTemp);
            lowTemp = itemView.findViewById(R.id.lowTemp);
        }

        public void setListItemDetails(WeatherResponse response, int position) {

            Picasso.with(context).load(PATH_OF_IMAGES + response.getList().get(position).getWeather().get(0).getIcon() + ".png").into(icon);
            day.setText(DAYS[position]);
            weather.setText(response.getList().get(position).getWeather().get(0).getMain());
            highTemp.setText(response.getList().get(position).getTemp().getMaxCelsuis() + "°");
            lowTemp.setText(response.getList().get(position).getTemp().getMinCelsuis() + "°");
        }
    }
}
