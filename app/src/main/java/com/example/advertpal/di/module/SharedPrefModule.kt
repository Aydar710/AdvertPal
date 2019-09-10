package com.example.advertpal.di.module

import android.content.Context
import com.example.advertpal.utils.SharedPrefWrapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPrefModule {

    @Singleton
    @Provides
    fun provideSharedPrefWrapper(context: Context): SharedPrefWrapper =
        SharedPrefWrapper(context)
}
