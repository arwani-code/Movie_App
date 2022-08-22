package com.moviesplash.app.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.moviesplash.app.core.ui.MovieGalleryAdapter
import com.moviesplash.app.core.utils.Constants
import com.moviesplash.app.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private lateinit var galleryAdapter: MovieGalleryAdapter
    private var _binding: FragmentGalleryBinding? = null

    private val galleryViewModel: GalleryViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            galleryAdapter = MovieGalleryAdapter()
            galleryViewModel.getListAllMovie.observe(requireActivity()){
                if(it != null){
                    galleryAdapter.submitList(it)
                }
            }
        }

        binding.rvGallery.apply {
            adapter = galleryAdapter
            layoutManager = GridLayoutManager(requireContext(), Constants.GRID_SPAN)
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}