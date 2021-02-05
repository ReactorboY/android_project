package com.organicpy.rxretromvp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 04-02-2021
 */
public class NetworkConnectivity {

    public static Observable<Boolean> isInternetOn(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return Observable.just(activeNetworkInfo != null && activeNetworkInfo.isConnected());
    }
    
//    fun hasInternetConnection(): Single<Boolean> {
//        return Single.fromCallable(Callable {
//            val connMgr =   ViyaApp.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                val network = connMgr.activeNetwork ?: return@Callable false
//                val activeNetwork = connMgr.getNetworkCapabilities(network) ?:         return@Callable false
//
//                return@Callable when {
//                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return@Callable true
//                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return@Callable true
//                    else -> return@Callable false
//                }
//            }else {
//                @Suppress("DEPRECATION")
//                val networkInfo = connMgr.activeNetworkInfo ?: return@Callable false
//                @Suppress("DEPRECATION")
//                return@Callable networkInfo.isConnected
//            }
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//    }
}
