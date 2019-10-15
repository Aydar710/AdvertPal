package com.example.advertpal

import android.app.Application
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import com.example.advertpal.di.component.AppComponent
import com.example.advertpal.di.component.DaggerAppComponent
import com.example.advertpal.di.module.AppModule
import com.example.advertpal.utils.NetworkStateChangeReceiver
import com.facebook.stetho.Stetho
import com.vk.api.sdk.VK


class App : Application() {
    companion object {
        @Suppress("LateinitUsage")
        lateinit var component: AppComponent

        private val WIFI_STATE_CHANGE_ACTION = "android.net.wifi.WIFI_STATE_CHANGED"
    }

    override fun onCreate() {
        super.onCreate()
        VK.initialize(this)
        Stetho.initializeWithDefaults(this)

        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        registerForNetworkChangeEvents(this)
    }

    private fun registerForNetworkChangeEvents(context: Context) {
        val networkStateChangeReceiver = NetworkStateChangeReceiver()
        context.registerReceiver(networkStateChangeReceiver, IntentFilter(CONNECTIVITY_ACTION))
        context.registerReceiver(networkStateChangeReceiver, IntentFilter(WIFI_STATE_CHANGE_ACTION))
    }
}