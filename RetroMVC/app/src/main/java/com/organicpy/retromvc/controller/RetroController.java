package com.organicpy.retromvc.controller;

import com.organicpy.retromvc.BuildConfig;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 03-02-2021
 */
public class RetroController {
    String BASE_URL = "https://wasl-admin-service-uat.reciproci.com/";
    public IRequest retroService() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(120, TimeUnit.SECONDS);
        httpClient.connectTimeout(120, TimeUnit.SECONDS);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        httpClient.addInterceptor(loggingInterceptor);


        httpClient.addInterceptor(chain -> {
            Request request = chain.request().newBuilder()
                    .header("Authorization", "bearer 52d9eb97-57b5-447d-bd2d-f257e238c407")
                    .header("DEVICE_ID", UUID.randomUUID().toString())
                    .header("DEVICE_TYPE", "Android")
                    .header("LANGUAGE", "EN")
                    .build();
            return chain.proceed(request);
        });

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IRequest.class);
    }
}
