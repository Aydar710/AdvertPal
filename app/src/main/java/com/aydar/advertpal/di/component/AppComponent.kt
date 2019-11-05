package com.aydar.advertpal.di.component

import android.content.Context
import com.aydar.advertpal.di.module.*
import com.aydar.advertpal.features.details.DetailsActivity
import com.aydar.advertpal.features.groups.GroupsActivity
import com.aydar.advertpal.features.works.WorksActivity
import com.aydar.advertpal.ui.AddWorkActivity
import com.aydar.advertpal.ui.MainActivity
import com.aydar.advertpal.utils.PostWorker
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