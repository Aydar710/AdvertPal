package com.example.advertpal

import android.app.Application
import com.example.advertpal.di.component.AppComponent
import com.example.advertpal.di.component.DaggerAppComponent
import com.example.advertpal.di.module.AppModule
import com.facebook.stetho.Stetho
import com.vk.api.sdk.VK

class App : Application() {

    companion object {
        @Suppress("LateinitUsage")
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        VK.initialize(this)
        Stetho.initializeWithDefaults(this)

        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }
}