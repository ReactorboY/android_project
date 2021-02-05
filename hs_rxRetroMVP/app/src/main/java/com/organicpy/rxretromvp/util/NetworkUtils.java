package com.organicpy.rxretromvp.util;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 04-02-2021
 */
public class NetworkUtils {
    public static IRetro iRetro() {
        return RetroAdapter.retrofitInstance().create(IRetro.class);
    }
}
