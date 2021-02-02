package com.organicpy.myapplicationkt.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData


/**
 * @author Mohd Hussain
 * @version 1.0
 * @since 18-01-2021
 */
class ConnectionDetection(val context: Context) : LiveData<Boolean>() {
    val TAG = "CONNTRA"
    fun isNetworkAvailable():  Boolean {
        val connectivityManager : ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.d(TAG, "isNetworkAvailable: ${connectivityManager.activeNetwork}")
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    override fun onActive() {
        super.onActive()
        postValue(isNetworkAvailable())
    }
}