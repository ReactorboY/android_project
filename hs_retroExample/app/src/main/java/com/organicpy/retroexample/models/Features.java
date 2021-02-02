package com.organicpy.retroexample.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 20-01-2021
 */
public class Features {

    public Properties getProperties() {
        return properties;
    }

    @SerializedName("properties")
    private Properties properties;

    public Features(Properties properties) {
        this.properties = properties;
    }
}
