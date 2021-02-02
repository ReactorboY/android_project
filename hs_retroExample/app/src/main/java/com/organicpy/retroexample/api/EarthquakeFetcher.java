package com.organicpy.retroexample.api;

import android.util.Log;

import com.organicpy.retroexample.models.EarthquakeModel;
import com.organicpy.retroexample.models.Features;
import com.organicpy.retroexample.models.Properties;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 20-01-2021
 */
public class EarthquakeFetcher implements Callback<EarthquakeModel> {
    final String TAG = "FTECHER";
    static final String BASE_URL = "https://earthquake.usgs.gov/";
    public void start() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApiInterface apiInterface = retrofit.create(MyApiInterface.class);
        Call<EarthquakeModel> call = apiInterface.getItems();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<EarthquakeModel> call, Response<EarthquakeModel> response) {
        List<Features> featuresList = response.body().getFeaturesList();
        for (int i = 0; i < featuresList.size(); i++) {
            Properties properties = featuresList.get(i).getProperties();
            Log.d(TAG, "onResponse: "+ i + " <-> " + properties.getMag() + " -> " + properties.getPlace());
        }
    }

    @Override
    public void onFailure(Call<EarthquakeModel> call, Throwable t) {
        Log.d(TAG, "onFailure: " + t.getMessage());
    }
}
