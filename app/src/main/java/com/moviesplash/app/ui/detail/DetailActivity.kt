package com.moviesplash.app.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.Data.Builder
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.moviesplash.app.R
import com.moviesplash.app.core.domain.model.MovieHome
import com.moviesplash.app.core.utils.Constants
import com.moviesplash.app.core.utils.Constants.NOTIFICATION_CHANNEL_ID
import com.moviesplash.app.core.utils.loadImage
import com.moviesplash.app.core.utils.show
import com.moviesplash.app.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {


    private lateinit var workManager: WorkManager
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private var statusFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.movie_detail_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        workManager = WorkManager.getInstance(this)

        val movie = intent.getParcelableExtra<MovieHome>(Constants.GET_MOVIE_ID)
        if (movie != null) showMovie(movie)
    }

    private fun showMovie(movie: MovieHome) {
        statusFavorite = movie.isFavorite
        setStatusFavorite(statusFavorite)
        movieTemplate(movie)
            binding.fab.setOnClickListener {
                actionAddFavorite(movie)
            }

            binding.fabRemove.setOnClickListener {
//               actionRemoveFavorite(movie)
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(movie, statusFavorite)
                val uri = Uri.parse("app://favorite")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
                finish()
            }
    }

//    private fun actionRemoveFavorite(movie: MovieHome) {
//        val alertDialog = AlertDialog.Builder(this@DetailActivity)
//        alertDialog.apply {
//            setTitle(getString(R.string.dialog_title))
//            setMessage(getString(R.string.dialog_message))
//            setPositiveButton(getString(R.string.dialog_yes)) { _, _ ->
//                statusFavorite = !statusFavorite
//                detailViewModel.setFavoriteMovie(movie, statusFavorite)
//                val uri = Uri.parse("app://favorite")
//                val intent = Intent(Intent.ACTION_VIEW, uri)
//                startActivity(intent)
//                finish()
//            }
//            setNegativeButton(getString(R.string.dialog_no)) { _, _ -> }
//            show()
//        }
//    }

    private fun actionAddFavorite(movie: MovieHome) {
        statusFavorite = !statusFavorite
        detailViewModel.setFavoriteMovie(movie, statusFavorite)
//        actionNotification(movie)
        val uri = Uri.parse("app://favorite")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
        finish()
    }
//
//    private fun actionNotification(movie: MovieHome) {
//        val data = Builder()
//            .putString(Constants.movieTitle, movie.title)
//            .putInt(Constants.movieId, movie.movieId)
//            .build()
//        val constraints = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            Constraints.Builder()
//                .setRequiresDeviceIdle(false)
//                .build()
//        } else {
//            Constraints.Builder().build()
//        }
//        val oneWorkRequest = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
//            .setInputData(data)
//            .setConstraints(constraints)
//            .addTag(NOTIFICATION_CHANNEL_ID)
//            .build()
//        workManager.enqueue(oneWorkRequest)
//    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.hide()
        } else {
            binding.fabRemove.hide()
        }
    }

    private fun movieTemplate(movie: MovieHome){
        binding.apply {
            tvTitle.text = movie.title
            tvReleaseDate.text = movie.releaseDate
            tvGenre.text = movie.voteAverage.toString()
            tvAvgVote.text = movie.voteCount.toString()
            tvSynopsis.text = movie.overview
            ivCover.loadImage(movie.posterPath)
            tvReleaseDateLabel.show()
            tvAvgVoteLabel.show()
            tvGenreLabel.show()

        }
    }

}