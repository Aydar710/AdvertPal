package com.example.advertpal.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v4.content.LocalBroadcastManager
import android.util.Log


class NetworkStateChangeReceiver : BroadcastReceiver() {

    companion object {
        val NETWORK_AVAILABLE_ACTION = "com.example.advertpal.NetworkAvailable"
        val IS_NETWORK_AVAILABLE = "isNetworkAvailable"

        fun isConnectedToInternet(context: Context?): Boolean {
            try {
                if (context != null) {
                    val connectivityManager =
                        context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
                    val networkInfo = connectivityManager.activeNetworkInfo
                    return networkInfo != null && networkInfo.isConnected
                }
                return false
            } catch (e: Exception) {
                Log.e(NetworkStateChangeReceiver::class.java.name, e.message)
                return false
            }

        }
    }


    override fun onReceive(context: Context, intent: Intent) {
        val networkStateIntent = Intent(NETWORK_AVAILABLE_ACTION)
        networkStateIntent.putExtra(IS_NETWORK_AVAILABLE, isConnectedToInternet(context))
        LocalBroadcastManager.getInstance(context).sendBroadcast(networkStateIntent)
    }


}