package com.moviesplash.app.core.utils

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.moviesplash.app.core.R

fun String.createImageUrl(): String {
    return Constants.MOVIE_ORIGINAL_IMAGE + this
}

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this.context)
        .load(imageUrl.createImageUrl())
        .override(500)
        .error(ContextCompat.getDrawable(this.context, R.drawable.ic_broken_favorite_black))
        .placeholder(ContextCompat.getDrawable(this.context, R.drawable.ic_favorite_black))
        .into(this)
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}
