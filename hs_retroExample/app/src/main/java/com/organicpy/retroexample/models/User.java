package com.organicpy.retroexample.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 01-02-2021
 */
public class User {
    @SerializedName("categoryId")
    @Expose()
    private String categoryId;

    @SerializedName("description")
    @Expose()
    private String description;

    @SerializedName("email")
    @Expose()
    private String email;

    @SerializedName("name")
    @Expose()
    private String name;

    @SerializedName("phoneNumber")
    @Expose()
    private String phoneNumber;

    public User(String categoryId, String description, String email, String name, String phoneNumber) {
        this.categoryId = categoryId;
        this.description = description;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
