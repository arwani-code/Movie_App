package com.moviesplash.app.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moviesplash.app.R
import com.moviesplash.app.core.data.Resource
import com.moviesplash.app.core.domain.model.MovieHome
import com.moviesplash.app.core.ui.MovieListAdapter
import com.moviesplash.app.core.ui.MovieListPlayingAdapter
import com.moviesplash.app.core.utils.Constants
import com.moviesplash.app.core.utils.hide
import com.moviesplash.app.core.utils.show
import com.moviesplash.app.databinding.FragmentHomeBinding
import com.moviesplash.app.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var movieListPopular: MovieListAdapter
    private lateinit var movieListUpcoming: MovieListAdapter
    private lateinit var moviePlayingNow: MovieListPlayingAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            movieListPopular = MovieListAdapter { moveToDetail(it) }
            movieListUpcoming = MovieListAdapter { moveToDetail(it) }
            moviePlayingNow = MovieListPlayingAdapter { moveToDetail(it) }
            observerData()
        }

        setupRv()
    }

    override fun onDetach() {
        super.onDetach()
        FrameLayout.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRv() {
        with(binding.rvPopular) {
            adapter = movieListPopular
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
        }
        with(binding.rvUpcoming) {
            adapter = movieListUpcoming
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
        }
        with(binding.rvMovieList){
            adapter = moviePlayingNow
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), Constants.GRID_SPAN_COUNT)
        }
    }

    private fun moveToDetail(movie: MovieHome){
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(Constants.GET_MOVIE_ID, movie)
        startActivity(intent)
    }

    private fun observerData() {
        homeViewModel.moviePopular.observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> binding.loadingSpinnerPopular.show()
                    is Resource.Success -> {
                        binding.loadingSpinnerPopular.hide()
                        binding.tvPopular.show()
                        val listMoviePopular = ArrayList<MovieHome>()
                        movie.data?.map {
                            if (it.movieAction == Constants.ACTION_POPULAR) {
                                listMoviePopular.add(it)
                            }
                        }
                        movieListPopular.submitList(listMoviePopular)
                    }
                    is Resource.Error -> {
                        binding.loadingSpinnerPopular.hide()
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            movie.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        }

        homeViewModel.movieUpComing.observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> binding.loadingSpinnerUpcoming.show()
                    is Resource.Success -> {
                        binding.loadingSpinnerUpcoming.hide()
                        binding.tvUpcoming.show()
                        val listUpcoming = ArrayList<MovieHome>()
                        movie.data?.map {
                            if (it.movieAction == Constants.ACTION_UPCOMING) {
                                listUpcoming.add(it)
                            }
                        }
                        movieListUpcoming.submitList(listUpcoming)
                    }
                    is Resource.Error -> {
                        binding.loadingSpinnerUpcoming.show()
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            movie.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        }

        homeViewModel.moviePlayingNow.observe(viewLifecycleOwner){ movie ->
            if(movie != null){
                when (movie) {
                    is Resource.Loading -> binding.loadingSpinnerPlaying.show()
                    is Resource.Success -> {
                        binding.loadingSpinnerPlaying.hide()
                        binding.tvPlayingNow.show()
                        val listPlaying = ArrayList<MovieHome>()
                        movie.data?.map {
                            if (it.movieAction == Constants.ACTION_PLAYING_NOW) {
                                listPlaying.add(it)
                            }
                        }
                        moviePlayingNow.submitList(listPlaying)
                    }
                    is Resource.Error -> {
                        binding.loadingSpinnerPlaying.hide()
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            movie.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        }
    }
}