package com.example.advertpal.di.module

import com.example.advertpal.data.repositories.VkRepository
import com.example.advertpal.data.services.VkService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ServiceModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    fun provideVkRepository(vkService: VkService)
            : VkRepository =
        VkRepository(vkService)
}
