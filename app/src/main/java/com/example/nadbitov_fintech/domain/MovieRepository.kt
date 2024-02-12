package com.example.nadbitov_fintech.domain

import com.example.nadbitov_fintech.domain.entity.Film
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(): Flow<List<Film>>

    suspend fun loadMovies()

    suspend fun setFilmFavourite(filmId: Int, isFavourite: Boolean)
}