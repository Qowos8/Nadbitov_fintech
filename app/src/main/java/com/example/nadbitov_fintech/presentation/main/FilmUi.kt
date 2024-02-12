package com.example.nadbitov_fintech.presentation.main

data class FilmUi(
    val id: String,
    val nameRu: String,
    val countries: List<CountryUi>,
    val genres: List<GenresUi>,
    val year: String,
    val posterUrl: String,
    val isFavourite: Boolean,
)

data class CountryUi(
    val name: String,
)

data class GenresUi(
    val name: String,
)