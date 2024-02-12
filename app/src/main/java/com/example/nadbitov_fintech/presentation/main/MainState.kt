package com.example.nadbitov_fintech.presentation.main

sealed class MainState {
    object Loading : MainState()

    data class Success(val movies: List<FilmUi>, val onlyFavourites: Boolean) : MainState()

    data class Error(val errorMessage: String) : MainState()
}