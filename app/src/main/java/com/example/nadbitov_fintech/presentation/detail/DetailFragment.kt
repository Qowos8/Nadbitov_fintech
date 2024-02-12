package com.example.nadbitov_fintech.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.nadbitov_fintech.R
import com.example.nadbitov_fintech.databinding.FragmentDetailBinding
import com.example.nadbitov_fintech.presentation.main.MoviesListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieIndex = arguments?.getString(ARG_MOVIE_ID, "-1") ?: -1
        viewModel.setMovieID(movieIndex.toString())
        viewModel.getDetail()

        lifecycleScope.launch {
            viewModel.detailState.collect { state ->
                when (state) {
                    is DetailState.Success -> {
                        binding.apply {
                            name.text = state.repository.name
                            description.text = state.repository.description
                            genreData.text =
                                state.repository.genre.joinToString(separator = ", ") { it.genre }
                            countryData.text = state.repository.countries.first().country
                            Glide.with(banner)
                                .load(state.repository.posterUrl)
                                .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
                                .into(banner)
                            binding.progressBar.isVisible = false
                            binding.shield.isVisible = false
                            backButton()
                        }
                    }

                    is DetailState.Error -> {
                        binding.progressBar.isVisible = false
                        Log.d("Detail", state.errorMessage)
                    }

                    is DetailState.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                }
            }
        }
    }

    private fun backButton() {
        binding.back.setOnClickListener {
            val fragment = MoviesListFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        private const val ARG_MOVIE_ID = "movie_id"

        fun newInstance(movieID: String): DetailFragment {
            val args = Bundle()
            args.putString(ARG_MOVIE_ID, movieID)
            val fragment = DetailFragment()
            fragment.arguments = args
            Log.d("MovieDetailsFragment", "Created new instance with movieID: $movieID")
            return fragment
        }
    }
}
