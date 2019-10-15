package com.example.advertpal.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import com.example.advertpal.utils.NetworkStateChangeReceiver

abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var networkSnackBar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerReceiver()
    }

    protected abstract fun showData()

    protected fun initNetworkSnackBar(activityRes : Int) {
        networkSnackBar = Snackbar.make(
            findViewById(activityRes),
            "Нет соединения",
            Snackbar.LENGTH_INDEFINITE
        )
    }


    private fun registerReceiver() {
        val intentFilter = IntentFilter(NetworkStateChangeReceiver.NETWORK_AVAILABLE_ACTION)
        LocalBroadcastManager.getInstance(this).registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val isNetworkAvailable =
                    intent.getBooleanExtra(NetworkStateChangeReceiver.IS_NETWORK_AVAILABLE, false)
                if (!isNetworkAvailable)
                    networkSnackBar.show()
                else{
                    networkSnackBar.dismiss()
                    showData()
                }
            }
        }, intentFilter)
    }
}