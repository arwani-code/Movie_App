package com.moviesplash.app.favorite.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.moviesplash.app.core.ui.MovieListFavoriteAdapter
import com.moviesplash.app.core.utils.Constants
import com.moviesplash.app.core.utils.show
import com.moviesplash.app.di.FavoriteModuleDependency
import com.moviesplash.app.favorite.R
import com.moviesplash.app.favorite.databinding.ActivityFavoriteBinding
import com.moviesplash.app.favorite.di.DaggerFavoriteComponent
import com.moviesplash.app.favorite.utils.ViewModelFactory
import com.moviesplash.app.ui.detail.DetailActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels { factory }

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteAdapter: MovieListFavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependency::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.favorite_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        favoriteAdapter = MovieListFavoriteAdapter { movie ->
            val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
            intent.putExtra(Constants.GET_MOVIE_ID, movie)
            startActivity(intent)
            finish()
        }

        binding.rvFavorite.apply {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
        }

        viewModel.movie.observe(this) { movie ->
            if (movie.isNotEmpty()) favoriteAdapter.submitList(movie) else binding.tvEmptyData.show()
        }
    }
}