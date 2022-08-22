package com.moviesplash.app.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.moviesplash.app.core.data.Resource
import com.moviesplash.app.core.utils.hide
import com.moviesplash.app.core.utils.show
import com.moviesplash.app.databinding.FragmentSlideshowBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SlideshowFragment : Fragment() {

    private val slideshowViewModel: SlideshowViewModel by viewModels()
    private var _binding: FragmentSlideshowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycle.addObserver(binding.youtubePlayerView)

        slideshowViewModel.movieVideo.observe(requireActivity()){
            if(it != null){
                when(it){
                    is Resource.Loading -> binding.loadingSpinner.show()
                    is Resource.Success -> {
                        binding.loadingSpinner.hide()
                        it.data?.map { video ->
                            binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                                override fun onReady(youTubePlayer: YouTubePlayer) {
                                    super.onReady(youTubePlayer)
                                    youTubePlayer.loadVideo(video.key, 0F)
                                }
                            })
                        }
                    }
                    is Resource.Error -> {
                        binding.loadingSpinner.hide()
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}