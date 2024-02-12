package com.example.nadbitov_fintech.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nadbitov_fintech.domain.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val mapper: FimDomainToUiMapper,
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _responseState = MutableStateFlow<MainState>(MainState.Loading)
    val responseState: StateFlow<MainState> get() = _responseState
    private val showOnlyFavourites: MutableStateFlow<Boolean> = MutableStateFlow(false)

    init {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                movieRepository.loadMovies()
            } catch (e: Exception) {
                if (e is CancellationException) {
                    throw e
                } else {
                    _responseState.emit(MainState.Error("Error ${e.message}"))
                    e.printStackTrace()
                }
            }
        }
    }

    fun getMovies() {
        viewModelScope.launch(Dispatchers.Default) {
            combine(
                showOnlyFavourites,
                movieRepository.getMovies(),
            ) { onlyFavourites, movies ->
                mapper.map(movies)
                    .filter {
                        if (onlyFavourites) it.isFavourite else true
                    } to onlyFavourites
            }
                .catch {
                    _responseState.emit(MainState.Error("Error ${it.message}"))
                    it.printStackTrace()
                }
                .collect {
                    _responseState.emit(MainState.Success(it.first, it.second))
                }
        }
    }

    fun addToFavourite(filmUi: FilmUi) {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                movieRepository.setFilmFavourite(filmUi.id.toInt(), !filmUi.isFavourite)
            } catch (e: Exception) {
                if (e is CancellationException) {
                    throw e
                }
            }
        }
    }

    fun setOnlyFavourites(onlyFavourites: Boolean) {
        viewModelScope.launch {
            showOnlyFavourites.emit(onlyFavourites)
        }
    }
}