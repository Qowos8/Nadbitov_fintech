package com.example.nadbitov_fintech.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nadbitov_fintech.di.RetrofitModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {
    private val _detailState = MutableStateFlow<DetailState>(DetailState.Loading)
    val detailState: StateFlow<DetailState> get() = _detailState
    private var index: String = "1"

    fun setMovieID(movie_index: String) {
        index = movie_index
    }

    fun getDetail() {
        viewModelScope.launch {
            try {
                val response = RetrofitModule.instance.getFilmDetails(index.toInt())
                _detailState.emit(DetailState.Success(response))
            } catch (e: Exception) {
                if (e is CancellationException) {
                    throw e
                }
                _detailState.emit(DetailState.Error("Error ${e.message}"))
                e.printStackTrace()
            }
        }
    }
}
