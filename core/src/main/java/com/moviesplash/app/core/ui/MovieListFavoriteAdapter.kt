package com.moviesplash.app.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moviesplash.app.core.databinding.FavoriteListAdapterBinding
import com.moviesplash.app.core.domain.model.MovieHome
import com.moviesplash.app.core.utils.AdapterUtil
import com.moviesplash.app.core.utils.loadImage


class MovieListFavoriteAdapter(private val clickListener: (MovieHome) -> Unit):
    ListAdapter<MovieHome, MovieListFavoriteAdapter.MyViewHolder>(AdapterUtil.DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = FavoriteListAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie, clickListener)
    }

    inner class MyViewHolder(private val binding: FavoriteListAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieHome, clickListener: (MovieHome) -> Unit) {
            binding.apply {
                tvTitle.text = movie.title
                img.loadImage(movie.posterPath)

            }

            itemView.setOnClickListener {
                clickListener(movie)
            }
        }
    }
}