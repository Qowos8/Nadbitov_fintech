package com.example.nadbitov_fintech.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Detail (
    @SerialName("nameRu")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("year")
    val year: Int,
    @SerialName("genres")
    val genre: List<GenresApi>,
    @SerialName("countries")
    val countries: List<CountryApi>,
    @SerialName("posterUrl")
    val posterUrl: String
)