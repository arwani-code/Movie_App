package com.moviesplash.app.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.moviesplash.app.core.databinding.ItemListMovieBinding
import com.moviesplash.app.core.domain.model.MovieHome
import com.moviesplash.app.core.utils.AdapterUtil.DIFF_CALLBACK

class MovieListAdapter(private val clickListener: (MovieHome) -> Unit):
    ListAdapter<MovieHome, MovieViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie, clickListener)
    }
}