package com.organicpy.retromvvm.viewmodel;

import androidx.lifecycle.ViewModel;

import com.organicpy.retromvvm.data.api.NetworkCall;
import com.organicpy.retromvvm.data.model.Hero;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 07-02-2021
 */
public class HeroesViewModel extends ViewModel {
    String TAG = "RetroBhai";
    String BASE_URL = "https://simplifiedcoding.net/demos/";

    public Single<List<Hero>> getHeroes() {
        return NetworkCall.getHeroes().getHeroes();
    }



}
