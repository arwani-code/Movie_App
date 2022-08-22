package com.moviesplash.app.search.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moviesplash.app.core.domain.usecase.MovieUseCase
import com.moviesplash.app.search.ui.SearchViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val movieUseCase: MovieUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(movieUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}