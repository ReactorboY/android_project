package com.organicpy.rxretromvp.util;


import com.organicpy.rxretromvp.model.FaqModel;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 04-02-2021
 */
public interface IRetro {
    @GET("api/ns/faq/v2/getfaq/{id}")
    Single<Response<FaqModel>> getFaqs(@Path("id") int id);
}
