package com.moviesplash.app.search.ui

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.moviesplash.app.HomeActivity
import com.moviesplash.app.core.data.Resource
import com.moviesplash.app.core.ui.MovieListPlayingAdapter
import com.moviesplash.app.core.utils.Constants
import com.moviesplash.app.core.utils.hide
import com.moviesplash.app.core.utils.show
import com.moviesplash.app.di.SearchModuleDependency
import com.moviesplash.app.search.databinding.ActivitySearchBinding
import com.moviesplash.app.search.di.DaggerSearchComponent
import com.moviesplash.app.search.util.ViewModelFactory
import com.moviesplash.app.ui.detail.DetailActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var searchAdapter: MovieListPlayingAdapter
    private lateinit var binding: ActivitySearchBinding
    private val viewModel: SearchViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerSearchComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    SearchModuleDependency::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.imBack.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        searchAdapter = MovieListPlayingAdapter { movie ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(Constants.GET_MOVIE_ID, movie)
            startActivity(intent)
        }

        setupMovie()
        setupRv()
    }

    private fun setupMovie() {
        binding.svMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchMovie(query)
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    searchMovie(query)
                }
                return true
            }
        })
    }

    private fun searchMovie(query: String) {
        val searchQuery = "%$query%"

        viewModel.getSearchMovie(searchQuery).observe(this) { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> binding.progressBar.show()
                    is Resource.Success -> {
                        binding.tvEmpty.hide()
                        binding.progressBar.hide()
                        searchAdapter.submitList(movies.data)
                        if (movies.data?.isEmpty() == true){
                            binding.tvEmpty.show()
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.hide()
                        binding.tvEmpty.text = movies.message
                    }
                }
            }
        }
    }

    private fun setupRv() {
        binding.rvSearchList.apply {
            adapter = searchAdapter
            layoutManager = GridLayoutManager(this@SearchActivity, Constants.GRID_SPAN_COUNT)
            setHasFixedSize(true)
        }
    }
}