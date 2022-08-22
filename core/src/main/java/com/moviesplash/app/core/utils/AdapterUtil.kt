package com.moviesplash.app.core.utils

import android.graphics.*
import androidx.recyclerview.widget.DiffUtil
import com.moviesplash.app.core.domain.model.MovieHome

object AdapterUtil {
     val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieHome>() {
        override fun areItemsTheSame(
            oldItem: MovieHome,
            newItem: MovieHome
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieHome,
            newItem: MovieHome
        ): Boolean {
            return oldItem == newItem
        }
    }
}