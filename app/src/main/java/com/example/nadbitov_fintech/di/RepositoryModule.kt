package com.example.nadbitov_fintech.di

import com.example.nadbitov_fintech.data.MovieRepositoryImpl
import com.example.nadbitov_fintech.domain.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRepo(impl: MovieRepositoryImpl): MovieRepository
}