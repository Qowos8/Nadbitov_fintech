package com.example.nadbitov_fintech.presentation.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.nadbitov_fintech.R
import com.example.nadbitov_fintech.databinding.ActivityMainBinding
import com.example.nadbitov_fintech.presentation.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MovieDetailsOpener {

    private lateinit var binding: ActivityMainBinding

    private var popularSelected: Boolean = false
        set(value) {
            if (field != value) {
                field = value
                (supportFragmentManager.fragments.firstOrNull { it is MoviesListFragment }
                        as? MoviesListFragment)?.showOnlyFavourite(field)
            }
        }

    private val isPortraitOrientation: Boolean
        get() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.main_container, MoviesListFragment())
                addToBackStack(null)
            }
        } else {
            popularSelected = savedInstanceState.getBoolean(POPULAR_SELECTED_KEY)
            if(isPortraitOrientation && !savedInstanceState.getBoolean(ORIENTATION_KEY)) {
                supportFragmentManager.findFragmentByTag(MOVIE_DETAILS_TAG)?.let {
                    supportFragmentManager.commit {
                        remove(it)
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(POPULAR_SELECTED_KEY, popularSelected)
        outState.putBoolean(ORIENTATION_KEY, isPortraitOrientation)
    }

    override fun openMovie(film: FilmUi) {
        supportFragmentManager.commit {
            val containerId = if (isPortraitOrientation) {
                R.id.main_container
            } else {
                R.id.sub_container
            }

            replace(containerId, DetailFragment.newInstance(film.id), MOVIE_DETAILS_TAG)
            addToBackStack(null)
        }
    }

    private companion object {
        const val POPULAR_SELECTED_KEY = "POPULAR_SELECTED_KEY"
        const val ORIENTATION_KEY = "ORIENTATION_KEY"
        const val MOVIE_DETAILS_TAG = "MOVIE_DETAILS_TAG"
    }
}