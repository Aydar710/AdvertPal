package com.aydar.advertpal.di.module

import android.content.Context
import com.aydar.advertpal.utils.SharedPrefWrapper
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
