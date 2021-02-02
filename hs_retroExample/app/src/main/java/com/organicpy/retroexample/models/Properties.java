package com.organicpy.retroexample.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 20-01-2021
 */
public class Properties {

    public String getPlace() {
        return place;
    }

    @SerializedName("place")
    private String place;

    public String getMag() {
        return mag;
    }

    @SerializedName("mag")
    private String mag;

    @SerializedName("title")
    private String title;

    public Properties(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
