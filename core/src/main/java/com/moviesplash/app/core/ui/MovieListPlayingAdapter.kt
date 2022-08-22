package com.moviesplash.app.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moviesplash.app.core.databinding.ItemListMoviePlayingBinding
import com.moviesplash.app.core.domain.model.MovieHome
import com.moviesplash.app.core.utils.AdapterUtil.DIFF_CALLBACK
import com.moviesplash.app.core.utils.loadImage

class MovieListPlayingAdapter(private val clickListener: (MovieHome) -> Unit):
    ListAdapter<MovieHome, MovieListPlayingAdapter.MyViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListMoviePlayingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie, clickListener)
    }

    inner class MyViewHolder(private val binding: ItemListMoviePlayingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieHome, clickListener: (MovieHome) -> Unit) {
                binding.apply {
                    textTitle.text = movie.title
                    ivMovieCover.loadImage(movie.posterPath)

                }

            itemView.setOnClickListener {
                clickListener(movie)
            }
        }
    }
}