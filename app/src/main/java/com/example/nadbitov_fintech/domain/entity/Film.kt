package com.example.nadbitov_fintech.domain.entity

class Film(
    val id: String,
    val nameRu: String,
    val countries: List<Country>,
    val genres: List<Genres>,
    val year: Int,
    val posterUrl: String,
    val isFavourite: Boolean,
)

class Country(
    val name: String,
)

class Genres(
    val name: String,
)