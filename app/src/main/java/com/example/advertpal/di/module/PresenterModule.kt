package com.example.advertpal.di.module

import com.example.advertpal.presenters.WorkPresenter
import com.example.advertpal.repositories.VkRepository
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class])
class PresenterModule {

    @Provides
    fun provideWorkPresenter(repository: VkRepository): WorkPresenter =
        WorkPresenter(repository)
}
