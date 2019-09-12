package com.example.advertpal.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.advertpal.base.ViewModelFactory
import com.example.advertpal.base.ViewModelKey
import com.example.advertpal.features.groups.GroupsViewModel
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
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
}