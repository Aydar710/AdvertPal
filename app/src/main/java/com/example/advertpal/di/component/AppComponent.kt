package com.example.advertpal.di.component

import android.content.Context
import com.example.advertpal.di.module.*
import com.example.advertpal.ui.MainActivity
import com.example.advertpal.ui.AddWorkActivity
import com.example.advertpal.utils.PostWorker
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetModule::class,
        ServiceModule::class,
        RepositoryModule::class,
        SharedPrefModule::class
    ]
)
interface AppComponent {

    fun provideApp() : Context

    fun inject(postWorker: PostWorker)

    fun inject (workActivity: AddWorkActivity)

    fun inject (mainActivity: MainActivity)
}