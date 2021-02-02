package com.organicpy.retroexample.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 20-01-2021
 */
public class EarthquakeModel {

    public List<Features> getFeaturesList() {
        return featuresList;
    }

    @SerializedName("features")
    private List<Features> featuresList;

    public EarthquakeModel(List<Features> features) {
        this.featuresList = features;
    }
}