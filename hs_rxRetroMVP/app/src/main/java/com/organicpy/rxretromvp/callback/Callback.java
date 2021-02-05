package com.organicpy.rxretromvp.callback;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 04-02-2021
 */
public abstract class Callback<T> {
    public abstract void onResultReturn(T t);
    public abstract void onErrorReturn(String message);
}
