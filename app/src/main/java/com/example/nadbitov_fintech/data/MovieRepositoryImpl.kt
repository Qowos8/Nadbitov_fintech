package com.example.nadbitov_fintech.data

import com.example.nadbitov_fintech.data.api.NetworkInterface
import com.example.nadbitov_fintech.data.db.FilmsDao
import com.example.nadbitov_fintech.domain.MovieRepository
import com.example.nadbitov_fintech.domain.entity.Film
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class MovieRepositoryImpl @Inject constructor(
    private val networkInterface: NetworkInterface,
    private val dao: FilmsDao,
    private val mapper: FilmDataMapper,
) : MovieRepository {

    override fun getMovies(): Flow<List<Film>> {
        return dao.getAll().map(mapper::mapDbToDomain)
    }

    override suspend fun loadMovies() {
        val films = networkInterface.getPopularFilms()
        dao.insertAll(mapper.mapApiToDb(films.items))
    }

    override suspend fun setFilmFavourite(filmId: Int, isFavourite: Boolean) {
        val filmToUpdate = dao.getFilmsWithId(filmId).first()
        dao.updateMovie(filmToUpdate.copy(isFavourite = isFavourite))
    }
}