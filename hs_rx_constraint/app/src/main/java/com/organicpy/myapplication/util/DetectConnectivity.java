package com.organicpy.myapplication.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Class Used for Transmitting Broadcast to class registering this broadcast,
 * whenever network disconnected broadcast sent & displayed using Snackbar on implementing Activity
 *
 * known_issue - Network info class is deprecated use NetworkCall instead while using in kotlin
 *
 * @permission - ACCESS_NETWORK_STATE & INTERNET permission
 *
 * @author Mohd Hussain
 * @version 1.0
 * @since 13-01-2021
 */
public class DetectConnectivity extends BroadcastReceiver {
    private NetworkStatusCallBack callBack;

    /**
     * empty constructor
     */
    public DetectConnectivity(){}

    /**
     * Constructor
     *
     * @param callBack - NetworkStatusCallback instantiation
     */
    public DetectConnectivity(NetworkStatusCallBack callBack) {
        this.callBack = callBack;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if(!isNetworkAvailable(context)) {
            callBack.onStateChange();
        }
    }

    /**
     *
     * @param context - Context of the Activity
     * @return true if connected else false
     */
    boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * interface to transfer state of network to Implementing activity
     * This is mainly used for Snanckbar working, as it need view to work
     */
    public interface NetworkStatusCallBack {
        void onStateChange();
    }
}
