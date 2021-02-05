package com.organicpy.rxretromvp.util;

import com.organicpy.rxretromvp.BuildConfig;

import java.util.Locale;
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
 * @since 04-02-2021
 */
public class RetroAdapter {
    private RetroAdapter() {}
    private static final String BASE_URL = "https://wasl-admin-service-uat.reciproci.com/";
    static String TAG = "RetroAdapter";

    public static Retrofit retrofitInstance() {

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.readTimeout(120, TimeUnit.SECONDS);
        client.connectTimeout(120, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        client.addInterceptor(chain -> {
            Request request = chain.request().newBuilder()
                    .header("Authorization", "bearer 56ddbb2a-dca5-4c6c-911e-72dcd77cf62c")
                    .header("DEVICE_ID", UUID.randomUUID().toString())
                    .header("LANGUAGE", Locale.getDefault().getLanguage())
                    .header("DEVICE", "ANDROID")
                    .build();
            return chain.proceed(request);
        });

        client.addInterceptor(interceptor);

        OkHttpClient okHttpClient = client.build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
