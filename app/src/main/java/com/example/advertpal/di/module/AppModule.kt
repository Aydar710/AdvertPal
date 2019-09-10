package com.example.advertpal.di.module

import android.content.Context
import com.example.advertpal.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app : App) {

    @Singleton
    @Provides
    fun provieApp(): Context = app
}