package com.organicpy.retromvc.controller;

import com.organicpy.retromvc.model.FaqModelRetro;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 03-02-2021
 */
public interface IRequest {

    @GET("api/ns/faq/v2/getfaq/{id}")
    Single<FaqModelRetro> getFaqs(@Path("id") int id);
    
}
