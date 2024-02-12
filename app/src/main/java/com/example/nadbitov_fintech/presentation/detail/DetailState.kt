package com.example.nadbitov_fintech.presentation.detail

import com.example.nadbitov_fintech.data.api.Detail

sealed class DetailState {
    object Loading : DetailState()
    data class Success(val repository: Detail) : DetailState()
    data class Error(val errorMessage: String) : DetailState()
}