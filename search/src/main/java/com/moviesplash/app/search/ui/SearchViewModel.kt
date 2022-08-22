package com.moviesplash.app.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.moviesplash.app.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {

    fun getSearchMovie(search: String?) = movieUseCase.getListMovie(search).asLiveData()

}