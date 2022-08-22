package com.moviesplash.app.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moviesplash.app.core.databinding.ItemGalleryMovieBinding
import com.moviesplash.app.core.domain.model.MovieHome
import com.moviesplash.app.core.utils.AdapterUtil
import com.moviesplash.app.core.utils.loadImage

class MovieGalleryAdapter :
    ListAdapter<MovieHome, MovieGalleryAdapter.MyViewHolder>(AdapterUtil.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemGalleryMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    inner class MyViewHolder(private val binding: ItemGalleryMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieHome) {
            binding.apply {
                ivMovieCover.loadImage(movie.posterPath)
            }
        }
    }
}