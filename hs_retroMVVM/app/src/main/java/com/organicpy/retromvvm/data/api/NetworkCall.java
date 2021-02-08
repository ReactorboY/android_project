package com.organicpy.retromvvm.data.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 07-02-2021
 */
public class NetworkCall {
    static String BASE_URL = "https://simplifiedcoding.net/demos/";

    public static IRetro getHeroes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(IRetro.class);

    }

}
