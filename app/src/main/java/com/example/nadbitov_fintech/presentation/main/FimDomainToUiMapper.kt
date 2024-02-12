package com.example.nadbitov_fintech.presentation.main

import com.example.nadbitov_fintech.domain.entity.Film
import javax.inject.Inject

class FimDomainToUiMapper @Inject constructor() {

    fun map(domain: List<Film>): List<FilmUi> {
        return domain.map {
            FilmUi(
                id = it.id,
                nameRu = it.nameRu,
                countries = it.countries.map {countryDomain ->
                    CountryUi(
                        name = countryDomain.name,
                    )
                },
                genres = it.genres.map {genreDomain ->
                    GenresUi(
                        name = genreDomain.name,
                    )
                },
                year = "(${it.year})",
                posterUrl = it.posterUrl,
                isFavourite = it.isFavourite,
            )
        }
    }
}