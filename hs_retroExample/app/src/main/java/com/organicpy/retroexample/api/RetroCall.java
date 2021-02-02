package com.organicpy.retroexample.api;

import com.organicpy.retroexample.BuildConfig;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 30-01-2021
 */
public final class RetroCall {
    private RetroCall() {}
    static String BASE_URL = "https://wasl-admin-service-uat.reciproci.com/";

    public static RequestApi retroService() {
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
                   .header("Authorization", "bearer 14b100a5-ac1c-4948-994d-019cd10d6e41")
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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(RequestApi.class);

    }
}
