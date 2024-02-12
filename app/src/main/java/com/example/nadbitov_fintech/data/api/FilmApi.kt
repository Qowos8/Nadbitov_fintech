package com.example.nadbitov_fintech.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularResponse (
    @SerialName("pagesCount")
    val pagesCount: Int,
    @SerialName("films")
    val items: List<FilmApi>,
)

@Serializable
data class FilmApi(
    @SerialName("filmId")
    val filmId: Int,
    @SerialName("nameRu")
    val nameRu: String,
    @SerialName("nameEn")
    val nameEn: String? = null,
    @SerialName("countries")
    val countries: List<CountryApi>,
    @SerialName("genres")
    val genres: List<GenresApi>,
    @SerialName("year")
    val year: Int,
    @SerialName("posterUrl")
    val posterUrl: String,
    @SerialName("posterUrlPreview")
    val posterUrlPreview: String,
)

@Serializable
data class CountryApi(
    @SerialName("country")
    val country: String
)

@Serializable
data class GenresApi(
    @SerialName("genre")
    val genre: String
)