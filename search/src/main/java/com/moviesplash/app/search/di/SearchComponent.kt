package com.moviesplash.app.search.di

import android.content.Context
import com.moviesplash.app.di.SearchModuleDependency
import com.moviesplash.app.search.ui.SearchActivity
import dagger.BindsInstance
import dagger.Component


@Component(dependencies = [SearchModuleDependency::class])
interface SearchComponent {

    fun inject(activity: SearchActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(searchModuleDependency: SearchModuleDependency): Builder
        fun build(): SearchComponent
    }
}