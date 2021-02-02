package com.organicpy.retroexample.api;

import com.organicpy.retroexample.models.EarthquakeModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 20-01-2021
 */
public interface MyApiInterface {
    @GET("fdsnws/event/1//query?format=geojson&starttime=2020-01-01&endtime=2021-01-19&latitude=20&minmagnitude=4&longitude=78&maxradius=12")
    Call<EarthquakeModel> getItems();
}
