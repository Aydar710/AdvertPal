package com.example.advertpal.di.module

import com.example.advertpal.data.services.VkService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetModule::class])
class ServiceModule {

    @Singleton
    @Provides
    fun provideVkService(retrofit: Retrofit): VkService =
        retrofit.create(VkService::class.java)
}
