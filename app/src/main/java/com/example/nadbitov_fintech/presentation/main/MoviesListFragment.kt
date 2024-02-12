package com.example.nadbitov_fintech.presentation.main

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nadbitov_fintech.R
import com.example.nadbitov_fintech.databinding.FragmentMoviesListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MoviesListFragment : Fragment() {

    private lateinit var binding: FragmentMoviesListBinding

    private val movieAdapter: MovieListAdapter by lazy(mode = LazyThreadSafetyMode.NONE) {
        MovieListAdapter(::openFilmDetails, viewModel::addToFavourite)
    }

    private val viewModel: MovieListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getMovies()

        binding.recycler.adapter = movieAdapter
        binding.recycler.layoutManager = LinearLayoutManager(context)

        viewModel.responseState
            .flowWithLifecycle(lifecycle)
            .onEach(::render)
            .launchIn(lifecycleScope)
    }

    private fun render(state: MainState) = when (state) {
        is MainState.Success -> {
            binding.recycler.isVisible = true
            binding.errorContainer.isVisible = false
            binding.progressBar.isVisible = false
            movieAdapter.submitList(state.movies)
            if(state.onlyFavourites) {
                binding.categoryPopular.text = getString(R.string.favourites_category)
            } else {
                binding.categoryPopular.text = getString(R.string.popular_category)
            }
            binding.popularButton?.styleButton(!state.onlyFavourites)
            binding.favouritesButton?.styleButton(state.onlyFavourites)
        }

        is MainState.Error -> {
            binding.recycler.isVisible = false
            binding.errorContainer.isVisible = true
            binding.progressBar.isVisible = false
        }

        is MainState.Loading -> {
            binding.recycler.isVisible = false
            binding.errorContainer.isVisible = false
            binding.progressBar.isVisible = true
        }
    }

    private fun Button.styleButton(selected: Boolean) {
        if (selected) {
            backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.light_blue))
            setTextColor(ContextCompat.getColor(context, R.color.blue))
        } else {
            backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(context, R.color.blue))
            setTextColor(ContextCompat.getColor(context, R.color.white))
        }
    }

    private fun openFilmDetails(filmUi: FilmUi) {
        (activity as? MovieDetailsOpener)?.openMovie(filmUi)
    }

    fun showOnlyFavourite(isFavourite: Boolean) {

        viewModel.setOnlyFavourites(isFavourite)
    }
}