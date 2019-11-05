package com.aydar.advertpal.di.module

import android.content.Context
import com.aydar.advertpal.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app : App) {

    @Singleton
    @Provides
    fun provieApp(): Context = app
}