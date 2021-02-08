package com.organicpy.retromvvm.data.api;

import com.organicpy.retromvvm.data.model.Hero;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 07-02-2021
 */
public interface IRetro {

    @GET("marvel")
    Single<List<Hero>> getHeroes();
}
