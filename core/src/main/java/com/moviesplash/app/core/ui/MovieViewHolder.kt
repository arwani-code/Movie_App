package com.moviesplash.app.core.ui

import androidx.recyclerview.widget.RecyclerView
import com.moviesplash.app.core.databinding.ItemListMovieBinding
import com.moviesplash.app.core.domain.model.MovieHome
import com.moviesplash.app.core.utils.loadImage

class MovieViewHolder(private val binding: ItemListMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MovieHome, clickListener: (MovieHome) -> Unit){
        binding.apply {
            textView.text = movie.title
            imageView.loadImage(movie.posterPath)

        }

        itemView.setOnClickListener {
            clickListener(movie)
        }
    }
}