package com.moviesplash.app.favorite.di

import android.content.Context
import com.moviesplash.app.di.FavoriteModuleDependency
import com.moviesplash.app.favorite.ui.FavoriteActivity
import dagger.BindsInstance
import dagger.Component


@Component(dependencies = [FavoriteModuleDependency::class])
interface FavoriteComponent {
    fun inject(activity: FavoriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependency: FavoriteModuleDependency): Builder
        fun build(): FavoriteComponent
    }
}