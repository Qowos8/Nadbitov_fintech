package com.example.nadbitov_fintech.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
data class FilmDb(
    @PrimaryKey
    @ColumnInfo("filmId")
    val filmId: Int,
    @ColumnInfo("nameRu")
    val nameRu: String,
    @ColumnInfo("nameEn")
    val nameEn: String? = null,
    @ColumnInfo("countries")
    val countries: List<CountryDb>,
    @ColumnInfo("genres")
    val genres: List<GenresDb>,
    @ColumnInfo("year")
    val year: Int,
    @ColumnInfo("posterUrl")
    val posterUrl: String,
    @ColumnInfo("posterUrlPreview")
    val posterUrlPreview: String,
    @ColumnInfo("isFavourite")
    val isFavourite: Boolean = false,
)

@Serializable
data class CountryDb(
    @SerialName("country")
    @ColumnInfo("country")
    val country: String
)

@Serializable
data class GenresDb(
    @SerialName("genre")
    @ColumnInfo("genre")
    val genre: String
)