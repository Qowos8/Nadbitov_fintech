package com.example.nadbitov_fintech.data

import com.example.nadbitov_fintech.data.api.FilmApi
import com.example.nadbitov_fintech.data.db.CountryDb
import com.example.nadbitov_fintech.data.db.FilmDb
import com.example.nadbitov_fintech.data.db.GenresDb
import com.example.nadbitov_fintech.domain.entity.Country
import com.example.nadbitov_fintech.domain.entity.Film
import com.example.nadbitov_fintech.domain.entity.Genres
import javax.inject.Inject

class FilmDataMapper @Inject constructor() {

    fun mapApiToDb(api: List<FilmApi>): List<FilmDb> {
        return api.map {
            FilmDb(
                filmId = it.filmId,
                nameRu = it.nameRu,
                nameEn = it.nameEn,
                countries = it.countries.map { countryApi ->
                    CountryDb(
                        country = countryApi.country,
                    )
                },
                genres = it.genres.map { genresApi ->
                    GenresDb(
                        genre = genresApi.genre,
                    )
                },
                year = it.year,
                posterUrl = it.posterUrl,
                posterUrlPreview = it.posterUrlPreview,
            )
        }
    }

    fun mapDbToDomain(db: List<FilmDb>): List<Film> {
        return db.map {
            Film(
                id = it.filmId.toString(),
                nameRu = it.nameRu,
                countries = it.countries.map { countryDb ->
                    Country(
                        name = countryDb.country,
                    )
                },
                genres = it.genres.map { genreDb ->
                    Genres(
                        name = genreDb.genre,
                    )
                },
                year = it.year,
                posterUrl = it.posterUrl,
                isFavourite = it.isFavourite,
            )
        }
    }
}