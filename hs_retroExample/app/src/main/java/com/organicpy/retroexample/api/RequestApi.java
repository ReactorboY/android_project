package com.organicpy.retroexample.api;

import com.organicpy.retroexample.models.Post;

import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 30-01-2021
 */
public interface RequestApi {
    @GET("posts")
    Single<List<Post>> getPosts();

    @POST("api/ns/enquiry/v1/writetous")
    Completable postit(@Body Map<String , String> body);

    @GET("api/ns/faq/v2/getfaq/{id}")
    Single<Post> getPath(@Path("id") int id);
}
