package com.aydar.advertpal.di.module

import com.aydar.advertpal.data.repositories.FireStoreRepository
import com.aydar.advertpal.data.repositories.VkRepository
import com.aydar.advertpal.data.services.VkService
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

    @Singleton
    @Provides
    fun provideFirestoreRepository(): FireStoreRepository = FireStoreRepository()
}
