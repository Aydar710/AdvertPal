package com.example.advertpal.di.component

import android.content.Context
import com.example.advertpal.di.module.*
import com.example.advertpal.features.details.DetailsActivity
import com.example.advertpal.features.groups.GroupsActivity
import com.example.advertpal.features.works.WorksActivity
import com.example.advertpal.ui.AddWorkActivity
import com.example.advertpal.ui.MainActivity
import com.example.advertpal.utils.PostWorker
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetModule::class,
        ServiceModule::class,
        RepositoryModule::class,
        SharedPrefModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun provideApp(): Context

    fun inject(postWorker: PostWorker)

    fun inject(workActivity: AddWorkActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(groupsActivity: GroupsActivity)

    fun inject(worksActivity: WorksActivity)

    fun inject(detailsActivity: DetailsActivity)
}