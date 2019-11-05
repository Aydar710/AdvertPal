package com.aydar.advertpal.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.aydar.advertpal.base.ViewModelFactory
import com.aydar.advertpal.base.ViewModelKey
import com.aydar.advertpal.features.groups.GroupsViewModel
import com.aydar.advertpal.features.works.WorksViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GroupsViewModel::class)
    internal abstract fun bindGroupsViewModel(
        viewModel: GroupsViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WorksViewModel::class)
    internal abstract fun bindWorksViewModel(
        viewModel: WorksViewModel
    ): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
}