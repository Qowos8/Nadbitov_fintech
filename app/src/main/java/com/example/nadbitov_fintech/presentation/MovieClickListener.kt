package com.example.nadbitov_fintech.presentation

import com.example.nadbitov_fintech.data.api.FilmApi

interface MovieClickListener {

    fun onMovieClicked(movie: FilmApi, movieIndex: Int)

}
